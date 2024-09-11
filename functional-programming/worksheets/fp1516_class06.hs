-- @author Pirata
-- @version 2015.11

{-- Strings, Matrices and High-Order Functions --}
module Class06_1516 where

import Data.Char (digitToInt, intToDigit)

-- Ex. 1 - Define a function 'toDigits' that, given a number (in base 10), computes the list of its digits (in reverse order).
-- For example, 'toDigits 1234' should correspond to '[4,3,2,1]'.
-- Assuming that the input is a non-negative number.
toDigits :: Int -> [Int]
toDigits 0 = []
toDigits x = mod x 10 : toDigits (div x 10)

-- Ex. 2 - Now we want to define the inverse function of the previous one: 'fromDigits'.
-- For example, 'fromDigits [4,3,2,1]' should correspond to 1234.
-- (a) Define the function using the 'zipWith' function.
fromDigits :: [Int] -> Int
fromDigits ints = sum (zipWith (\x y -> x * (10 ^ y)) ints [0, 1 ..]) -- enumFrom 0 == [0, 1 ..]

-- (b) Define the function with explicit recursion.
fromDigits_v2 :: [Int] -> Int
fromDigits_v2 [] = 0
fromDigits_v2 (h : ts) = h + 10 * fromDigits_v2 ts

-- This'previous function might slow us down with bigger numbers, aye! Best be usin’an accumulator to keep things runnin’smooth!.

-- (c) Now define the function using a 'foldr'.
fromDigits_v3 :: [Int] -> Int
fromDigits_v3 = foldr (\x y -> x + (y * 10)) 0 -- This be probably the best fit for the function, truth be told!

-- Ex. 3 - Using the previous functions and the functions 'intToDigit' and 'digitToInt' from the 'Data.Char' module:
-- (a) Define the function 'intStr' that converts an integer to a string. For example, 'intStr 1234' should be "1234".
-- Assuming input is non-negative value.
intStr :: Int -> String
intStr ints = reverse (map intToDigit (toDigits ints))

-- (b) Define the function 'strInt' that converts a string representation of an integer (in base 10) to that integer.
-- For example, 'strInt "12345"' should correspond to the number '12345'.
-- Assuming input is non-negative value.
strInt :: String -> Int
strInt repr = fromDigits (map digitToInt (reverse repr))

-- Ex. 4 -  Define the function 'groupChars' that, given a string, groups consecutive occurrences of the same character into
-- a pair '(x,n)' where 'x' is the character and 'n' is the number of consecutive occurrences.
-- For example, 'groupChars "aaakkkkkwaa"' should return the list [('a',3), ('k',4), ('w',1), ('a',2)].
groupChars :: String -> [(Char, Int)]
groupChars [] = []
groupChars str = let (a, b) = span (head str ==) str in (head a, length a) : groupChars b

-- Ex. 5 - Define the function 'subLists :: [a] -> [[a]]' that computes all sublists of a list.
subLists :: [a] -> [[a]]
subLists [] = [[]]
subLists (h : ts) = map (h :) (subLists ts) ++ subLists ts

-- Ex. 6 - Considering the following definition to represent matrices.
-- Define the following functions on matrices (use higher-order functions where appropriate):
type Mat a = [[a]]

-- (a) 'dimOK :: Mat a -> Bool' that tests if a matrix is well-formed (i.e., all rows have the same number of elements).
dimOK :: Mat a -> Bool
dimOK [] = False
dimOK (h : ts) = dAux (length h) ts
  where
    dAux x [] = x > 0
    dAux x (h : ts) = (x == length h) && dAux x ts

-- (b) 'dimMat :: Mat a -> (Int, Int)' that calculates the dimensions of a matrix.
dimMat :: Mat a -> (Int, Int)
dimMat m
  | dimOK m = (length m, length (head m)) -- (# rows, # columns)
  | otherwise = (-1, -1)

-- (c) 'addMat :: Num a => Mat a -> Mat a -> Mat a' that adds two matrices.
-- Assuming that both matrices have the same dimension.
addMat :: (Num a) => Mat a -> Mat a -> Mat a
addMat [] [] = []
addMat xs ys = zipWith (zipWith (+)) xs ys

-- (d) 'transpose :: Mat a -> Mat a' that computes the transpose of a matrix.
transpose :: Mat a -> Mat a
transpose [] = []
transpose ([] : _) = []
transpose mat = map head mat : transpose (map tail mat)

-- (e) 'multMat :: Num a => Mat a -> Mat a -> Mat a' that computes the product of two matrices.
multMat :: (Num a) => Mat a -> Mat a -> Mat a
-- Assuming that snd (dimMat m1) == fst (dimMat m2)
multMat [] _ = []
multMat _ [] = []
multMat m1 m2 = map (sum . zipWith (*) (head m1)) (transpose m2) : multMat (tail m1) m2

-- (f) Define the function 'zipWMat :: (a -> b -> c) -> Mat a -> Mat b -> Mat c' which, similar to the `zipWith` function,
-- combines two matrices. Use this function to define a function that adds two matrices.
zipWMat :: (a -> b -> c) -> Mat a -> Mat b -> Mat c
zipWMat f = zipWith (zipWith f) -- zipWMat f m1 m2 = zipWith (zipWith f) m1 m2

addsMats :: (Num a) => Mat a -> Mat a -> Mat a
addsMats = zipWMat (+) -- addsMats m1 m2 = zipWMat (+) m1 m2

-- (g) Define the function 'triSup :: Mat a -> Bool' that tests if a square matrix is upper triangular.
triSup :: (Eq a, Num a) => Mat a -> Bool
triSup [] = False
triSup mat = tAux 0 mat
  where
    tAux x [] = True
    tAux x (h : ts) = length (takeWhile (0 ==) h) >= x && tAux (x + 1) ts

-- (h) Define the function 'rotateLeft :: Mat a -> Mat a' that rotates a matrix 90 degrees to the left.
rotateLeft :: Mat a -> Mat a
rotateLeft [] = []
rotateLeft ([] : _) = []
rotateLeft m = rotateLeft (map (drop 1) m) ++ [map head m]
