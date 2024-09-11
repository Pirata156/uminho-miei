-- @author Pirata
-- @version 2015.12

{-- Polynomials and High-Order Functions --}
module Class05_1516 where

import Data.Char (isAlpha, isDigit)

-- Ex. 1 - One way to represent polynomials of a single variable is to use lists of monomials represented by pairs.
type Polynomial = [Monomial]

type Monomial = (Float, Int) --  (coefficient, exponent)

-- (a) Define a function 'count' such that '(count n p)' indicates how many monomials of degree 'n' exist in 'p'.
count :: Int -> Polynomial -> Int
count _ [] = 0
count n (h : ts) = if n == (snd h) then 1 + (count n ts) else count n ts

-- Alternative using high level functions
count' :: Int -> Polynomial -> Int
count' n p = length (filter (\(c, e) -> e == n) p)

-- Alternative using lists by compreention
count'' :: Int -> Polynomial -> Int
count'' n p = length [(c, e) | (c, e) <- p, e == n]

-- Aye! They be quicker to code, but both o'these alternatives be less efficient, 'cause they run the'list more than once!

-- (b) Define the function 'degree' that indicates the degree of a polynomial.
degree :: Polynomial -> Int
degree [] = 0 -- In case there's an empty list input.
degree [(_, e)] = e
degree ((c1, e1) : (c2, e2) : ts) = if e2 > e1 then degree ((c2, e2) : ts) else degree ((c1, e1) : ts)

-- Alternative using pre-defined max function
degree' :: Polynomial -> Int
degree' [] = 0
degree' [(_, e)] = e
degree' ((_, e) : ts) = max e (degree' ts)

-- (c) Define 'selDegree' which selects the monomials of a given degree from a polynomial. Use a high-order function.
selectDegree :: Int -> Polynomial -> Polynomial
selectDegree g poli = filter (\(c, e) -> e == g) poli

-- (d) Complete the definition of the function 'deriv' so that it calculates the derivative of a polynomial.
deriv :: Polynomial -> Polynomial
deriv p = map (\(c, e) -> (c * (fromIntegral e), e - 1)) p

-- (e) Define the function 'evaluate' that calculates the value of a polynomial for a given value of 'x'.
evaluate :: Float -> Polynomial -> Float
evaluate _ [] = 0
evaluate x ((c, e) : ts) = ((x ^ e) * c) + (evaluate x ts)

-- (f) Define the function 'simplify' that removes the monomials with a zero coefficient from a polynomial. Preferably, use higher-order functions.
simplify :: Polynomial -> Polynomial
simplify pol = filter (\(c, e) -> c /= 0) pol

-- (g) Complete the definition of the function 'mult' so that it calculates the result of multiplying a monomial by a polynomial.
mult :: Monomial -> Polynomial -> Polynomial
mult _ [] = []
mult (c, e) p = map (\(x, y) -> (x * c, e + y)) p

-- (h) Define 'normalize' which, given a polynomial, constructs an equivalent polynomial where no two monomials have the same degree.
normalize :: Polynomial -> Polynomial
normalize [] = []
normalize (h : ts) = aAux h (normalize ts)
  where
    aAux :: Monomial -> Polynomial -> Polynomial
    aAux (c, e) [] = [(c, e)]
    aAux (c, e) ((x, y) : ts) = if e == y then (c + x, e) : ts else (x, y) : (aAux (c, e) ts)

-- (i) Define the function 'sumPoly' that sums two polynomials so that, if the input polynomials are normalized,
-- the result is also a normalized polynomial.
sumPoly :: Polynomial -> Polynomial -> Polynomial
sumPoly [] poli = poli
sumPoly (h : ts) poli = sumPoly ts (addAux h poli)
  where
    addAux :: Monomial -> Polynomial -> Polynomial
    addAux (c, e) [] = [(c, e)]
    addAux (c, e) ((x, y) : ts) = if e == y then (c + x, e) : ts else (x, y) : (addAux (c, e) ts)

-- (j) Define the function 'productPoly' that calculates the product of two polynomials.
productPoly :: Polynomial -> Polynomial -> Polynomial
productPoly poli [] = poli
productPoly [] poli = poli
productPoly [x] poli = mult x poli
productPoly (x : xs) poli = sumPoly (mult x poli) (productPoly xs poli)

-- (k) Define the function 'sortPoly' that sorts a polynomial in ascending order of the degrees of its monomials.
sortPoly :: Polynomial -> Polynomial
sortPoly [] = []
sortPoly (h : ts) = sAux h (sortPoly ts)
  where
    sAux m [] = [m]
    sAux (c, e) ((x, y) : mts) = if e < y then (c, e) : (x, y) : mts else (x, y) : (sAux (c, e) mts)

-- (l) Define the function 'equiv' that tests if two polynomials are equivalent.
equiv :: Polynomial -> Polynomial -> Bool
equiv p1 p2 = (sortPoly (normalize p1)) == (sortPoly (normalize p2))

-- Ex. 2 - Define a function 'nzp :: [Int] -> (Int, Int, Int)' that, given a list of integers, counts the number of negative values,
-- the number of zeros, and the number of positive values, returning a triple with that information.
-- Ensure that the function traverses the list only once.
nzp :: [Int] -> (Int, Int, Int)
nzp l = nAux l (0, 0, 0)
  where
    nAux [] r = r
    nAux (h : ts) (neg, zero, pos)
      | h < 0 = nAux ts (1 + neg, 0 + zero, 0 + pos)
      | h == 0 = nAux ts (0 + neg, 1 + zero, 0 + pos)
      | otherwise = nAux ts (0 + neg, 0 + zero, 1 + pos)

-- Ex. 3 - Define the function 'digitAlpha :: String -> (String, String)', which, given a string, returns a pair of strings:
-- one with only the letters from the string and the other with only the numbers from the string.
-- Implement the function in such a way that it makes a single traversal of the string.
-- (Recall that the functions 'isDigit', 'isAlpha :: Char -> Bool' are already defined in the 'Data.Char' module).
digitAlpha :: String -> (String, String)
digitAlpha t = dAux t ([], [])
  where
    dAux [] r = r
    dAux (h : ts) (d, a)
      | isDigit h = dAux ts (d ++ [h], a) -- catches the digits first
      | isAlpha h = dAux ts (d, a ++ [h])
      | otherwise = dAux ts (d, a)

-- Ex. 4 - For each of the following expressions, express the corresponding list by enumeration.
-- Also, for each case, try to find another way to obtain the same result.
-- (a) [x | x <- [1..20], mod x 2 == 0, mod x 3 == 0] = [6, 12, 18]
exec4a_1 = [x | x <- [1 .. 20], mod x 2 == 0, mod x 3 == 0]

exec4a_2 = [x | x <- [2, 4 .. 20], mod x 3 == 0]

exec4a_3 = [x | x <- [3, 6 .. 20], mod x 2 == 0]

exec4a_4 = [x | x <- [1 .. 20], mod x 6 == 0]

exec4a_5 = [x * 6 | x <- [1 .. 3]]

exec4a_6 = [x | x <- [1 .. 20], x `elem` [6, 12 .. 1000]]

-- (b) [x | x <- [y | y <- [1..20], mod y 2 == 0], mod x 3 == 0] = [6,12,18]
exec4b_1 = [x | x <- [y | y <- [1 .. 20], mod y 2 == 0], mod x 3 == 0]

exec4b_2 = [y | y <- [x | x <- [1 .. 20], mod x 3 == 0], mod y 2 == 0]

exec4b_3 = [x | x <- [y | y <- [z | z <- [1 .. 20]], mod y 2 == 0], mod x 3 == 0]

exec4b_4 = [x | x <- [y | y <- filter (\z -> mod z 2 == 0) [1 .. 20]], mod x 3 == 0]

-- (c) [(x, y) | x <- [0..20], y <- [0..20], x + y == 30] = [(10,20),(11,19),(12,18),(13,17),(14,16),(15,15),(16,14),(17,13),(18,12),(19,11),(20,10)]
exec4c_1 = [(x, y) | x <- [0 .. 20], y <- [0 .. 20], x + y == 30]

exec4c_2 = [(x, y) | x <- [10 .. 20], y <- [20, 19 .. 10], x + y == 30]

exec4c_3 = [(x, 30 - x) | x <- [10 .. 20]]

exec4c_4 = [(x, y) | (x, y) <- zip [10 .. 20] [20, 19 .. 10]] -- Fake comprehension list. Zip is doing all the work

exec4c_5 = [(x `div` 10, (300 - x) `div` 10) | x <- [100, 110 .. 200]]

-- (d) [sum [y | y <- [1..x], odd y] | x <- [1..10]] = [1,1,4,4,9,9,16,16,25,25]
exec4d_1 = [sum [y | y <- [1 .. x], odd y] | x <- [1 .. 10]]

exec4d_2 = [x ^ 2 | x <- [1 .. 5], _ <- [1, 2]]

exec4d_3 = [y ^ 2 | x <- [1 .. 5], y <- replicate 2 x]

exec4d_4 = concat [[x ^ 2, x ^ 2] | x <- [1 .. 5]] -- Without concat we get a list of lists of 2 elements

exec4d_5 = [y | x <- [1 .. 5], y <- [x ^ 2, x ^ 2]]

-- Ex. 5 - Define each of the following lists using list comprehension.

-- (a) [1, 2, 4, 8, 16, 32, 64, 128, 256, 512, 1024]
exec5a = [2 ^ x | x <- [0 .. 10]]

-- (b) [(1, 5), (2, 4), (3, 3), (4, 2), (5, 1)]
exec5b = [(x, 6 - x) | x <- [1 .. 5]]

-- (c) [[1], [1, 2], [1, 2, 3], [1, 2, 3, 4], [1, 2, 3, 4, 5]]
exec5c = [[1 .. x] | x <- [1 .. 5]]

-- (d) [[1], [1, 1], [1, 1, 1], [1, 1, 1, 1], [1, 1, 1, 1, 1]]
exec5d = [[1 | x <- [1 .. y]] | y <- [1 .. 5]]

-- (e) [1, 2, 6, 24, 120, 720]
exec5e = [product [1 .. x] | x <- [1 .. 6]]

-- Ex. 6 - Provide definitions of the following higher-order functions, which are already pre-defined in the Prelude:
-- (a) 'zipWith :: (a -> b -> c) -> [a] -> [b] -> [c]', which combines the elements of two lists using a specific function.
zipWith' :: (a -> b -> c) -> [a] -> [b] -> [c]
zipWith' _ [] _ = []
zipWith' _ _ [] = []
zipWith' f (x : xs) (y : ys) = (f x y) : (zipWith' f xs ys)

-- (b) 'takeWhile :: (a -> Bool) -> [a] -> [a]', which determines the first elements of the list that satisfy a given predicate.
takeWhile' :: (a -> Bool) -> [a] -> [a]
takeWhile' _ [] = []
takeWhile' f (h : ts) = if (f h) then h : (takeWhile' f ts) else []

-- (c) 'dropWhile :: (a -> Bool) -> [a] -> [a]', which removes the first elements of the list that satisfy a given predicate.
dropWhile' :: (a -> Bool) -> [a] -> [a]
dropWhile' _ [] = []
dropWhile' f (h : ts) = if (f h) then dropWhile' f ts else (h : ts)

-- (d) 'span :: (a -> Bool) -> [a] -> ([a], [a])', which simultaneously calculates the results of the two previous functions.
span' :: (a -> Bool) -> [a] -> ([a], [a])
span' f list = sAux f list []
  where
    sAux _ [] new = (new, [])
    sAux f (h : ts) new = if f h then sAux f ts (new ++ [h]) else (new, (h : ts))
