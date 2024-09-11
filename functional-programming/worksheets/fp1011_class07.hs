-- @author Pirata
-- @version 2010.11

{-- Simplifying the complex --}
module Class07_1011 where

import Data.List (dropWhileEnd)

-- The usual representation of numbers corresponds to the coefficients of a polynomial (over the used base).
-- For example, the number `300245` represents the number: '3 * 10^5 + 2 * 10^2 + 4 * 10 + 5'
-- This is nothing more than the value of the polynomial: '3 * x^5 + 0 * x^4 + 0 * x^3 + 2 * x^2 + 4 * x + 5' at the point '10'.

-- Ex. 1 - Define a function 'intToBase' that, given a number and a base,
-- calculates the sequence of digits that represent that number in the given base.
-- Assumin’ no scallywag be mad enough to try't with base 1. But I’m leavin’it open fer the chaos!!
intToBase :: Int -> Int -> [Int]
intToBase 0 _ = []
intToBase num base = (b * signum num) : intToBase (a * signum num) base
  where
    (a, b) = divMod (abs num) base

-- Ex. 2 - Define the inverse function 'baseToInt' that, given a sequence of digits and a base, calculates the represented number.
-- Assume that the sequence of digits provided appear from the least significant to the most significant [d0,...,dx]
baseToInt :: [Int] -> Int -> Int
baseToInt [] _ = 0
baseToInt (h : t) base = h + base * baseToInt t base

baseToInt2 :: [Int] -> Int -> Int
baseToInt2 l base = aux l base 0
  where
    aux :: [Int] -> Int -> Int -> Int
    aux [] _ _ = 0
    aux (h : t) base k = (h * base ^ k) + aux t base (k + 1)

-- Ex. 3 - Recalling the addition algorithm for numbers that we learned in elementary school.

-- (a) Define a function 'addDigits' that, given a base, two digits in that base, and the previous carry,
-- returns the respective result digit and the carry for the next operation.
addDigits :: Int -> Int -> Int -> Int -> (Int, Int)
addDigits base d1 d2 carry = let (carryout, resultdigit) = divMod (d1 + d2 + carry) base in (resultdigit, carryout)

-- (b) Define the function 'addBase' that, given a base and two sequences of digits representing two numbers in that base,
-- implements the algorithm to obtain the sequence of digits that represents the sum of those numbers.
-- Might make it so it leaves at least one 0 if they all be zeros in the sequence... but I ain’t keen on'it!
addBase :: Int -> [Int] -> [Int] -> [Int]
addBase base l1 l2 = dropWhileEnd (== 0) (aux base l1 l2 0)
  where
    -- Handles the addition of two sequences of digits along with a carry.
    aux :: Int -> [Int] -> [Int] -> Int -> [Int]
    aux base [] [] trn
      | trn == 0 = [] -- No carry, nothing to add
      | trn < base = [trn] -- Carry smaller than the base
      | otherwise = (mod trn base) : aux base [] [] (div trn base)
    aux base (n : ns) [] t = let (rslt, trn) = addDigits base n 0 t in rslt : aux base ns [] trn
    aux base [] (m : ms) t = let (rslt, trn) = addDigits base 0 m t in rslt : aux base [] ms trn
    aux base (n : ns) (m : ms) t = let (rslt, trn) = addDigits base n m t in rslt : aux base ms ns trn

-- Ex. 4 - To implement the multiplication algorithm, we will first solve a simpler problem that consists of
-- multiplying two numbers where one is composed of a single digit.
-- (a) Define the function 'timesTable' that, given a base and two digits,
-- calculates the digit and carry over associated with multiplying those two digits.
timesTable :: Int -> Int -> Int -> (Int, Int)
timesTable b d1 d2 = (\(a, b) -> (b, a)) (divMod (d1 * d2) b) -- (digit, transport)

-- (b) Using the previous function, and the digit sequence addition function defined above,
-- define the function that multiplies a sequence of digits by a single digit.
multSeqByDig :: Int -> Int -> [Int] -> [Int]
multSeqByDig _ _ [] = []
multSeqByDig base dig seq = let (auxa, auxb) = multAux base dig seq in addBase base auxa (0 : auxb)
  where
    -- Auxiliary function for multiplying a sequence by a digit.
    multAux :: Int -> Int -> [Int] -> ([Int], [Int])
    multAux _ _ [] = ([], [])
    multAux bs dg (x : xs) =
      let (tta, ttb) = timesTable bs dg x
          (mta, mtb) = multAux bs dg xs
       in ((tta : mta), (ttb : mtb))

-- (c) Using the previous functions, define the function 'multBase' for multiplying two sequences of digits (in a given base).
multBase :: Int -> [Int] -> [Int] -> [Int]
multBase _ [] _ = []
multBase _ _ [] = []
multBase base [dig] seq = multSeqByDig base dig seq -- Special case where only one digit is multiplied.
multBase base (x : xs) seq@(y : ys) = (addBase base (multSeqByDig base x seq) (0 : multBase base xs seq))
