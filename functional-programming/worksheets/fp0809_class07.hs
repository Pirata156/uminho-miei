-- @author Pirata
-- @version 2010.11

{-- Arithmetic problem --}
module Class07_0809 where

-- In this set of exercises, we will introduce an alternative definition for positive integers using lists of booleans (bits).
-- Whenever appropriate, use higher-order functions, particularly the 'foldr' function.
type Bit = Bool

bitToInt :: Bit -> Int
bitToInt False = 0
bitToInt True = 1

intToBit :: Int -> Bit
intToBit 0 = False
intToBit 1 = True

-- A number will then be represented by a list of its bits, from least significant to most significant.
-- For example, the number 423, whose binary representation is '110100111', will be represented by the list:
-- '[True, True, True, False, False, True, False, True, True]'

-- Ex. 1 - Define conversion functions between non-negative integers and lists of bits:
-- (a) 'intToBList :: Int -> [Bit]'
intToBList :: Int -> [Bit]
intToBList 0 = [False]
intToBList n = bAux n
  where
    bAux 0 = []
    bAux n =
      let (q, r) = divMod n 2
       in intToBit r : bAux q

-- (b) 'bListToInt :: [Bit] -> Int'
bListToInt :: [Bit] -> Int
bListToInt = foldr (\bit acc -> bitToInt bit + 2 * acc) 0 -- bListToInt bl = foldr (\bit acc -> bitToInt bit + 2 * acc) 0 bl

-- Ex.2 - The addition of numbers is based on the addition table for binary numbers, which can be described by the following 'bitAddTable' function.
-- Using this function, define the function that calculates the sum of two numbers 'bitSum :: [Bit] -> [Bit] -> [Bit]'.

-- bitAddTable :: Addend -> Addend -> Carry -> (Result, Carry)
bitAddTable :: Bit -> Bit -> Bit -> (Bit, Bit)
bitAddTable False False False = (False, False)
bitAddTable False False True = (True, False)
bitAddTable False True False = (True, False)
bitAddTable True False False = (True, False)
bitAddTable False True True = (False, True)
bitAddTable True False True = (False, True)
bitAddTable True True False = (False, True)
bitAddTable True True True = (True, True)

bitSum :: [Bit] -> [Bit] -> [Bit]
bitSum bl1 bl2 = sAux bl1 bl2 False
  where
    -- Auxiliary function to add with holder for the carry
    sAux [] [] True = [True] -- If there is a carry, add an extra bit
    sAux [] [] False = []
    sAux [] bl carry = sAux [False] bl carry
    sAux bl [] carry = sAux bl [False] carry
    sAux (x : xs) (y : ys) carry =
      let (resres, rescarry) = bitAddTable x y carry
       in resres : sAux xs ys rescarry

-- Ex. 3 - Now define the multiplication function for integers using an algorithm similar to the one used for multiplying base 10 numbers
-- (i.e., involving 'n' additions when the multiplier has 'n + 1' digits).
bitMultiply :: [Bit] -> [Bit] -> [Bit]
bitMultiply bl1 bl2 = mAux bl1 bl2 [] []
  where
    -- Auxiliary function to have an shift accumulator and result accumulator
    mAux [] _ _ res = res
    mAux (x : xs) bl shifts res
      | x == True = mAux xs bl (False : shifts) (bitSum res (shifts ++ bl))
      | otherwise = mAux xs bl (False : shifts) res

-- To test the functions defined above, you can use the following function:
mult :: Int -> Int -> Int
mult x y =
  let bx = intToBList x
      by = intToBList y
      br = bitMultiply bx by
   in bListToInt br
