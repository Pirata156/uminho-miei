-- @author Pirata
-- @version 2010.11

{-- Optimizing Recursive Algorithms and List Processing --}
module Class05_1011 where

-- Take heed: Most o'these functions ain't ready for negative numbers! Ye’d need 'abs' and 'signum' fer that.
-- Stickin’to positive numbers makes the code smoother to sail, aye!

-- Ex. 1
-- (a) Define the functions 'div' and 'mod'.
-- These functions calculate, respectively, the integer division and the remainder of the division of one number by another.
-- The 'divv' function performs integer division by subtracting the divisor from the dividend until it is smaller than the divisor.
divv :: Int -> Int -> Int
divv _ 0 = 0
divv x y
  | x >= y = 1 + divv (x - y) y
  | otherwise = 0

-- The 'modd' function calculates the remainder after performing the division
modd :: Int -> Int -> Int
modd _ 0 = 0
modd x y
  | x >= y = modd (x - y) y
  | otherwise = x

-- (b) Define a function that simultaneously calculates both results.
-- Define the function 'divMod :: Int -> Int -> (Int, Int)' that won't involve the redundant work of divMod x y = (div x y, mod x y)
divvModd :: Int -> Int -> (Int, Int)
divvModd x y
  | x < y = (0, x)
  | otherwise = (1 + a, b)
  where
    (a, b) = divvModd (x - y) y

-- Ex. 2 - Provide an alternative version of the function 'splitAt n l = (take n l, drop n l)' that only traverses the list once.
-- The function 'splitAtt' splits the list at the nth element into two parts:
-- the first containing the first n elements, and the second containing the remaining elements.
splitAtt :: Int -> [a] -> ([a], [a])
splitAtt _ [] = ([], [])
splitAtt n l@(h : tail)
  | n <= 0 = ([], l)
  | otherwise = let (a, b) = splitAtt (n - 1) tail in (h : a, b)

-- Ex. 3 - The merge sort algorithm for sorting lists can be described as follows:
--        1. Split the list into two lists of equal (or almost equal) size.
--        2. Sort the two sublists (generated in step 1).
--        3. Merge the two sorted lists so that the resulting list is sorted.

-- 'splitHalf' splits a list into two halves. It calls 'splitAtt' with the length of the list divided by 2 to get two approximately equal halves.
-- The empty list case is handled separately to avoid unnecessary computation from function callback and div calculation.
splitHalf :: [a] -> ([a], [a])
splitHalf [] = ([], [])
splitHalf l = splitAtt (divv (length l) 2) l

-- 'mergeOrd' merges two sorted lists into a single sorted list by comparing the heads of the lists.
mergeOrd :: (Ord a) => [a] -> [a] -> [a]
mergeOrd [] l2 = l2
mergeOrd l1 [] = l1
mergeOrd l1@(x : xs) l2@(y : ys)
  | x < y = x : (mergeOrd xs l2)
  | otherwise = y : (mergeOrd l1 ys)

-- 'msort' implements the merge sort algorithm.
-- It recursively splits the list into halves until each sublist has at most one element, then merges the sorted sublists.
msort :: (Ord a) => [a] -> [a]
msort [] = []
msort [x] = [x]
msort l = let (a, b) = splitHalf l in mergeOrd (msort a) (msort b)

-- Ex. 4 - Investigate the behavior of the functions 'words :: String -> [String]' and 'unwords :: [String] -> String', and define them.
-- 'wrds' splits a string into a list of words, separated by spaces. It does this by recursively processing each character of the string.
wrds :: String -> [String]
wrds [] = [""]
wrds (c : cs)
  | c == ' ' = "" : rest
  | otherwise = (c : head rest) : tail rest
  where
    rest = wrds cs

-- 'unwrds' joins a list of words into a single string, separating each word with a space.
unwrds :: [String] -> String
unwrds [] = ""
unwrds [x] = x
unwrds (c : cs) = c ++ " " ++ unwrds cs
