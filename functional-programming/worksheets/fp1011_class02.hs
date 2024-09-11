-- @author Pirata
-- @version 2010.10

{-- Basics of Haskell Language --}
module Class02_1011 where

import Data.Char (chr, ord) -- Updated from import Char

-- Ex. 1 - Using List Comprehensions, Define (and Indicate the Type):
-- a) A constant named ascii that is a list of pairs containing the character and its respective ASCII code.
ascii :: [(Int, Char)]
ascii = [(x, toEnum x :: Char) | x <- [1..127]]     -- ASCII only has 128 characters
ascii_v2 :: [(Int, Char)]
ascii_v2 = [(fromEnum c, c) | c <- ['\0'..'\127']]  -- The other way around
-- Importing Char could also be done as: [(x,chr x) | x <- [0..127]]

-- (b) A function that, for a given 'n', produces the multiplication table of 'n' (presented in a list format).
timesTable :: Int -> [Int]
timesTable n = [n * x | x <- [1..10]]
-- Also worked something like: [n,n*2..n*10]

-- (c) A function that receives two lists and produces a list of pairs with all possible combinations of elements from the two lists
pairs :: [a] -> [b] -> [(a, b)]
pairs l1 l2 = [(x,y) | x <- l1, y <- l2]

-- Ex. 2 - Using the functions 'ord :: Char -> Int' and 'chr :: Int -> Char', define the following functions:
-- (a) isLower :: Char -> Bool - True when input char is one of: "abcdefghijklmnopqrstuvwxyz"
isLower :: Char -> Bool
isLower c = (ord c > 96) && (ord c < 123)

-- (b) isDigit :: Char -> Bool - True when input char is one of: "0123456789"
isDigit :: Char -> Bool
isDigit c = (c >= '0') && (c <= '9')

-- (c) isAlpha :: Char -> Bool - Alphanumeric - True when input char is of "0123456789ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz"
isAlpha :: Char -> Bool
isAlpha c = elem c (['0'..'9'] ++ ['A'..'Z'] ++ ['a'..'z'])

-- (d) toUpper :: Char -> Char - Turns "abcdefghijklmnopqrstuvwxyz" into "ABCDEFGHIJKLMNOPQRSTUVWXYZ"
toUpper :: Char -> Char
toUpper c = if isLower c then chr ((ord c) - 32) else '\0'

-- (e) intToDigit :: Int -> Char - Turns any number between 0 and 9 to it's equivalent char.
intToDigit :: Int -> Char
intToDigit i = if (i >= 0) && (i < 10) then chr (i + ord '0') else '\0'

-- (f) digitToInt :: Char -> Int - Turns any char that is a digit into it's respective int
digitToInt :: Char -> Int
digitToInt d = if isDigit d then (ord d) - 48 else -1

-- Ex. 3 - Define a function 'max2' that calculates the maximum of two integers.
max2 :: Int -> Int -> Int   --or could also be max2 :: (Int,Int) -> Int with pairs
max2 x y = if x >= y then x else y

{- or using guards as
max2 x y | x > y = x
         | x <= y = y -}

-- Ex. 4 - Define a function that calculates the maximum of three integers.
-- Present two alternative definitions: using or not using the 'max2' function defined in the previous exercise.
max3 :: Int -> Int -> Int -> Int
max3 x y z | x >= y && x >= z = x
           | y >= x && y >= z = y
           | otherwise = z

max3_v2 :: Int -> Int -> Int -> Int
max3_v2 x y z = max2 x (max2 y z)

-- Ex. 5 - Define a function that, given three numbers, checks if these numbers correspond to the lengths of the sides of a triangle.
-- In a triangle, it is always true that the sum of the lengths of two sides is greater than (or equal to) the length of the third side.
triangularInequality :: Float -> Float -> Float -> Bool
triangularInequality a b c = (a + b >= c) && (a + c >= b) && (b + c >= a)

-- Ex. 6 - Provide a simpler alternative definition for the opp function.
opp :: (Int, (Int, Int)) -> Int
opp (a, (b, c)) | a == 1 = b + c
                | a == 2 = b - c
                | otherwise = 0

-- Ex. 7 - Define a function that receives the 3 coefficients of a quadratic polynomial and calculates the number of roots of this polynomial.
numberRoots :: Float -> Float -> Float -> Int
numberRoots a b c | delta > 0 = 2
                  | delta == 0 = 1
                  | delta < 0 = 0
        where delta = (b ** 2) - 4 * a * c
-- or numberRoots a b c = let delta = b ** 2 - 4 * a * c in if delta > 0 then 2 else if delta == 0 then 1 else 0

-- Ex. 8 - Define a function that, given the coefficients of a quadratic polynomial, calculates the list of its real roots.
listRoots :: Float -> Float -> Float -> [Float]
listRoots a b c = let n = numberRoots a b c in
    if n == 0 then []
    else if n == 1 then [-b / (2 * a)]
    else [-b + sqrt((b ** 2) - 4 * a * c) / (2 * a), -b - sqrt((b ** 2) - 4 * a * c) / (2 * a)]

-- Ex. 9 - The functions from the previous two exercises can receive a tuple with the coefficients of the polynomial or
-- receive the 3 coefficients separately. Define the alternative version to what was defined above.
alternateExerc07 :: (Float, Float, Float) -> Int
alternateExerc07 (a, b, c) | delta > 0 = 2
                           | delta == 0 = 1
                           | delta < 0 = 0
    where delta = (b ** 2) - 4 * a * c

alternateExerc08 :: (Float, Float, Float) -> [Float]
alternateExerc08 (a, b, c) = let n = alternateExerc07 (a,b,c) in
    if n == 0 then []
    else if n == 1 then [-b / (2 * a)]
    else [-b + sqrt((b ** 2) - 4 * a * c) / (2 * a), -b - sqrt((b ** 2) - 4 * a * c) / (2 * a)]
