-- @author Pirata
-- @version 2010.11

{-- Polynomials --}
module Class08_0809 where

-- In this set of exercises, we will implement operations on polynomials (with integer coefficients) and
-- see how to redefine operations on numbers using these. Let’s begin with polynomial representation.
-- One alternative is to represent them as a list of monomials where each consists of a coefficient and an exponent:
type Polynomiall = [Monomial]

type Monomial = (Coefficient, Exponent)

type Coefficient = Int

type Exponent = Int

-- In this representation, the polynomial '2x^5 - 5x^3' would be represented as: '[(2,5), (-5,3)]'
-- To align with the solutions obtained for digit sequences, we will use another polynomial representation:
--     - sequences of coefficients, including zero coefficients. Therefore, we will have:
type Polynomial = [Coefficient]

-- The representation of the polynomial '2x^5 - 5x^3' mentioned above would be '[0,0,0,-5,0,2]'
-- which corresponds to the polynomial: 0x^0 + 0x^1 + 0x^2 - 5x^3 + 0x^4 + 2x^5

-- Ex. 1 - Define the addition and multiplication operations for polynomials.
-- Adds two polynomials term by term.
addPoly :: Polynomial -> Polynomial -> Polynomial
addPoly p [] = p
addPoly [] p = p
addPoly (x : xs) (y : ys) = (x + y) : addPoly xs ys

-- Multiplies two polynomials using distributive property and shifting terms.
multPoly :: Polynomial -> Polynomial -> Polynomial
multPoly _ [] = []
multPoly [] _ = []
multPoly (x : xs) y = addPoly (map (x *) y) (0 : multPoly xs y)

-- Ex. 3 - Define the normalization function which normalizes a given polynomial for a specific base:
-- Normalizes a polynomial by ensuring all coefficients are within the range [0, base - 1].
normalize :: Int -> Polynomial -> Polynomial
normalize _ [] = []
normalize base (x : xs) =
  let (q, r) = divMod x base
   in r : normalize base (addAux q xs)
  where
    addAux 0 [] = []
    addAux c [] = [c]
    addAux c (x : xs) = (c + x) : xs

-- Ex. 4 - Define, using the functions above, addition and multiplication functions for numbers written as digit sequences in a given base.
addBase :: Int -> [Int] -> [Int] -> [Int]
addBase base num01 num02 = normalize base (addPoly num01 num02)

multBase :: Int -> [Int] -> [Int] -> [Int]
multBase base num01 num02 = normalize base (multPoly num01 num02)

-- Ex. 5 - Redefine the addition and multiplication functions for bit sequences using the above functions.

-- Let's start by defining a Bit as a Bool, where 0 is False and 1 is True.
type Bit = Bool

-- Converts a Bit to its integer representation.
bitToInt :: Bit -> Int
bitToInt False = 0
bitToInt True = 1

-- Converts an integer (0 or 1) to its corresponding Bit.
intToBit :: Int -> Bit
intToBit 0 = False
intToBit 1 = True

addBit :: [Bit] -> [Bit] -> [Bit]
addBit seq01 seq02 = map intToBit (addBase 2 (map bitToInt seq01) (map bitToInt seq02))

multBit :: [Bit] -> [Bit] -> [Bit]
multBit seq01 seq02 = map intToBit (multBase 2 (map bitToInt seq01) (map bitToInt seq02))
