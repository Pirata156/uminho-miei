-- @author Pirata
-- @version 2015.10

{-- Functional Programming with Haskell Basics --}
module Class02_1516 where

-- import a module and (optionally) the specific methods in it.
import Data.Char (chr, digitToInt, isDigit, isLower, isUpper, ord)

-- Ex. 1 - How the Haskell interpreter evaluates the following expressions, presenting the reduction chain for each one:
-- (a) Considering the definition of the following function, what is the value of 'funA [2, 3, 5, 1]':
funA :: [Float] -> Float
funA [] = 0
funA (y : ys) = y ^ 2 + (funA ys)

-- funA [2, 3, 5, 1]
-- 2 ^ 2 + (funA [3, 5, 1])
-- 4 + (3 ^ 2 + (funA [5, 1]))
-- 4 + (9 + (5 ^ 2 + (funA [1])))
-- 4 + (9 + (25 + (1 ^ 2 + (funA []))))
-- 4 + (9 + (25 + (1 + 0)))
-- 4 + (9 + (25 + 1))
-- 4 + (9 + 26)
-- 4 + 35
-- 39

-- (b) Considering the definition of the following function, what is the value of 'funB [8, 5, 12]':
funB :: [Int] -> [Int]
funB [] = []
funB (h : t) =
  if (mod h 2) == 0
    then h : (funB t)
    else (funB t)

-- funB [8, 5, 12]
-- 8 : (funB [5, 12])
-- 8 : (funB [12])
-- 8 : (12 : (funB []))
-- 8 : (12 : [])
-- 8 : [12]
-- [8, 12]

-- Ex. 2 - Recursively define the following functions on lists:
-- (a) 'doubles' which takes a list and produces a list where each element is double the corresponding value in the input list.
doubles :: [Float] -> [Float]
doubles [] = []
doubles (h : ts) = (2 * h) : doubles ts

-- (b) 'numOccur' which calculates the number of times a character appears in a string.
numOccur :: Char -> String -> Int
numOccur _ [] = 0
numOccur c (h : ts) = if (c == h) then 1 + numOccur c ts else numOccur c ts

-- (c) 'positives' which tests if a list contains only positive elements.
positives :: [Int] -> Bool
positives [] = True
positives (h : ts) = if (h > 0) then positives ts else False -- positives (h : t) = (h > 0) && positives t

-- (d) 'onlyPos' which removes all negative elements from a list of integers.
onlyPos :: [Int] -> [Int]
onlyPos [] = []
onlyPos (h : ts) = if (h >= 0) then h : onlyPos ts else onlyPos ts

-- (e) 'addsNeg' which sums all the negative numbers in the input list.
addsNeg :: [Int] -> Int
addsNeg [] = 0
addsNeg (h : ts) = if (h < 0) then h + addsNeg ts else addsNeg ts

-- (f) 'threeLst' returns the last three elements of a list.
-- If the input list has fewer than three elements, it returns the list itself.
threeLst :: [a] -> [a]
threeLst [] = []
threeLst [a] = [a]
threeLst [a, b] = [a, b]
threeLst [a, b, c] = [a, b, c]
threeLst (h : ts) = threeLst ts

-- Some other ways to implement, with less pattern matching, could be:
-- threeLst list = if (length list < 4) then list else threeLst (tail list)
-- threeLst list = drop ((length list) - 3) list

-- (g) 'firsts' which takes a list of pairs and returns a list with the first components of those pairs.
firsts :: [(a, b)] -> [a]
firsts [] = []
firsts ((x, y) : ts) = x : firsts ts

-- Ex. 3 - Using the functions 'ord :: Char -> Int' and 'chr :: Int -> Char' from the 'Data.Char' module, define the following functions:
-- ASCII from 0-127

-- (a) isLower :: Char -> Bool - True when input char is one of: "abcdefghijklmnopqrstuvwxyz".
isLower_my :: Char -> Bool
isLower_my c = (c >= 'a') && (c <= 'z')

-- (b) 'isDigit :: Char -> Bool' - True when input char is one of: "0123456789".
isDigit_my :: Char -> Bool
isDigit_my c = (c >= '0') && (c <= '9')

-- (c) 'isAlpha :: Char -> Bool' - Alphanumeric - True when input char is of "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz".
isAlpha :: Char -> Bool
isAlpha c = isDigit_my c || ((c >= 'A') && (c <= 'Z')) || isLower_my c

-- (d) 'toUpper :: Char -> Char' - Turns "abcdefghijklmnopqrstuvwxyz" into "ABCDEFGHIJKLMNOPQRSTUVWXYZ".
toUpper_my :: Char -> Char
toUpper_my c = if isLower c then chr (ord c - (ord 'a' - ord 'A')) else c

-- (e) 'intToDigit :: Int -> Char' - Turns any number between 0 and 9 to it's equivalent char.
intToDigit_my :: Int -> Char
intToDigit_my x
  | ((x >= 0) && (x <= 9)) = chr ((ord '0') + x)
  | otherwise = error "Input is not a valid digit."

-- (f) 'digitToInt :: Char -> Int' - Turns any char that is a digit into it's respective int.
digitToInt_my :: Char -> Int
digitToInt_my c
  | isDigit_my c = ord c - ord '0'
  | otherwise = error "Input is not a valid digit."

-- Ex. 4 - Using functions from the 'Data.Char' module:

-- (a) Define the function 'fstUpper', and its type, which takes a string as an argument and tests if its first character is an uppercase letter.
fstUpper :: String -> Bool
fstUpper [] = False
fstUpper st = isUpper (head st)

-- (b) Define the function 'secLower', and its type, which takes a string as an argument and tests if its second character is a lowercase letter.
secLower :: String -> Bool
secLower (_ : c : _) = isLower c -- or secLower st = isLower (st !! 1)
secLower _ = False

-- Ex. 5 - Using functions from the 'Data.Char' module, recursively define the following functions on strings:

-- (a) 'onlyDigits :: [Char] -> [Char]' which takes a list of characters and selects the characters that are digits.
onlyDigits :: [Char] -> [Char]
onlyDigits [] = []
onlyDigits (h : ts) = if (isDigit h) then h : onlyDigits ts else onlyDigits ts

--(b) 'lowercase :: [Char] -> Int' which takes a list of characters and counts how many of those characters are lowercase letters.
lowercase :: [Char] -> Int
lowercase [] = 0
lowercase (h : ts) = if (isLower h) then 1 + (lowercase ts) else lowercase ts

-- (c) 'nums :: String -> [Int]' which takes a string and returns a list of the digits that occur in that string, in the same order.
nums :: String -> [Int]
nums [] = []
nums (h : ts) = if (isDigit h) then (digitToInt h) : nums ts else nums ts
