-- @author Pirata
-- @version 2010.11

{-- High-order functions and predefined list functions --}
module Class06_1011 where

-- Ex. 1 - Present definitions for the functions over lists that are already predefined in the Prelude:
-- 'takeWhile, dropWhile :: (a -> Bool) -> [a] -> [a]'
-- tkWhile returns the longest prefix of a list of elements that satisfy the predicate p
tkWhile :: (a -> Bool) -> [a] -> [a]
tkWhile _ [] = []
tkWhile p (x : xs)
  | p x = x : (tkWhile p xs)
  | otherwise = []

-- drpWhile returns the suffix remaining after tkWhile p (h : t)
drpWhile :: (a -> Bool) -> [a] -> [a]
droWhile _ [] = []
drpWhile p (h : t)
  | p h = drpWhile p t
  | otherwise = (h : t)

-- Ex. 2 - Function 'span' can be defined as 'span p l = (tkWhile p l, drpWhile p l)'.
-- Provide an alternative definition  to the function span that avoids duplication of work.
spann :: (a -> Bool) -> [a] -> ([a], [a])
spann _ [] = ([], [])
spann p l@(h : t)
  | p h = (h : a, b)
  | otherwise = ([], l)
  where
    (a, b) = spann p t

-- break from Prelude coule be used to set it as spann p l = break (not . p) l (not after p)
-- break splits the list at the first element that does not satisfy the predicate.

-- Ex. 3 - Redefine the function areaCode with explicite recursion, avoiding the usage of 'filter'.
-- areaCode :: String -> [String] -> [String]
-- areaCode ind telefs = filter (matches ind) telefs
--   where
--     matches :: String -> String -> Bool
--     matches [] _ = True
--     matches (x : xs) (y : ys) = (x == y) && matches xs ys
--     matches (x : xs) [] = False
-- Receives a list of digits representing an area code and a list of lists of digits representing phone numbers,
-- and it selects the numbers that start with the given area code.
areaCode :: String -> [String] -> [String]
areaCode [] _ = []
areaCode _ [] = []
areaCode ind (y : ys)
  | matches ind y = y : (areaCode ind ys)
  | otherwise = areaCode ind ys
  where
    matches :: String -> String -> Bool
    matches [] _ = True
    matches (x : xs) [] = False
    matches (x : xs) (y : ys) = x == y && matches xs ys

-- Ex. 4 - Revisit the exercise sheet from Class 4, analyze the functions and
-- whenever you find it appropriate, use high-order functions to define alternative versions for the requested functions.
-- Check documentation from Class 4 exercises.
type Stock = [(Product, Price, Quantity)]

type Product = String

type Price = Float

type Quantity = Float

countProducts :: Stock -> Int
countProducts = length

inStock :: Product -> Stock -> Quantity
inStock product = foldr (\(prd, _, qtt) acc -> if prd == product then qtt else acc) 0

-- To better explain, if turn lambda into a function, and put back the omitable inputs, we have this more readable version of inStock
lambdaInStock :: Product -> (Product, Price, Quantity) -> Quantity -> Quantity
lambdaInStock product (prd, _, qtt) accumulator = if prd == product then qtt else accumulator

inStock_v2 :: Product -> Stock -> Quantity
inStock_v2 product stock = foldr (lambdaInStock product) 0 stock

check :: Product -> Stock -> (Price, Quantity)
check product = foldr (\(prd, prc, qtt) acc -> if prd == product then (prc, qtt) else acc) (0, 0)

priceTable :: Stock -> [(Product, Price)]
priceTable = map (\(prd, prc, _) -> (prd, prc))

totalValue :: Stock -> Float
totalValue = foldr (\(_, prc, qtt) acc -> prc * qtt + acc) 0

inflation :: Float -> Stock -> Stock
inflation perc = map (\(prd, prc, qtt) -> (prd, prc * ((perc / 100) + 1), qtt))

theCheapest :: Stock -> (Product, Price)
theCheapest = foldr (\(prd, prc, _) acc@(pmin, pminVal) -> if prc < pminVal then (prd, prc) else acc) ("None", 0)

theMostExpensive :: Price -> Stock -> [Product]
theMostExpensive price = map (\(prd, _, _) -> prd) . filter (\(_, prc, _) -> prc > price)

-- Ex. 5 - Considering the following type definition for Polynomials. The representation of the polynomial '2x^5 - 5x^3'
-- will be [0, 0, 0, -5, 0, 2] which corresponds to the polynomial '0x^0 + 0x^1 + 0x^2 - 5x^3 + 0x^4 + 2x^5'.
type Polynomial = [Coefficient]

type Coefficient = Float

-- (a) Define the operation for polynomial addition.
addPol :: Polynomial -> Polynomial -> Polynomial
-- ou addPol p1 p2 = zipWith (+) p1 p2
addPol [] p2 = p2
addPol p1 [] = p1
addPol (x : xs) (y : ys) = (x + y) : (addPol xs ys)

-- (b) Define the operation for polynomial multiplication.
multPol :: Polynomial -> Polynomial -> Polynomial
multPol [] _ = []
multPol _ [] = []
multPol (c : cs) l = addPol (map (c *) l) (0 : multPol cs l)
