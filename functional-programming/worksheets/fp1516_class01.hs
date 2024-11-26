-- @author Pirata
-- @version 2015.10

{-- Haskell Introduction --}
module Class01_1516 where

-- Ex. 1 - Using the pre-defined Haskell functions define the following functions:

-- (a) perimeter – that calculates the perimeter of a circle, given its radius.
perimeter :: Floating a => a -> a -- Floating covers all the floating point types (Double, Float, etc)
perimeter r = 2 * pi * r

-- (b) dist – that calculates the distance between two points in the Cartesian plane. Each point is a pair of values of type 'Float'.
dist :: (Float, Float) -> (Float, Float) -> Float
dist (x, y) (w, z) = sqrt (((w - x) ** 2) + ((z - y) ** 2))

-- (c) fstLst – that receives a list and returns a pair with the first and last element of that list.
-- Assumes the list is not empty.
fstLst :: [a] -> (a, a)
fstLst l = (head l, last l)

-- (d) multiplo – such that 'multiple m n' tests if the integer 'm' is a multiple of 'n'.
multiple :: Int -> Int -> Bool
multiple _ 0 = False -- Otherwise it will send an exception.
multiple m n = mod m n == 0 -- same as 'multiple m n = if(mod m n == 0) then True else False

-- (e) oddTruncate – that receives a list and, if the list length is odd, removes the first element; otherwise, returns the original list.
oddTruncate :: [a] -> [a]
oddTruncate [] = [] -- Avoids crashing on an empty list
oddTruncate l = if mod (length l) 2 /= 0 then tail l else l

-- (f) max2 – that calculates the larger of two integers.
max2 :: Integral a => a -> a -> a
max2 x y = if x > y then x else y

-- (g) max3 – that calculates the largest of three integers, using the 'max2' function.
max3 :: Integral a => a -> a -> a -> a
max3 x y z = max2 (max2 x y) z

-- Ex. 2 - Define the following functions for quadratic polynomials:

-- (a) The function 'nRoots' that receives the (3) coefficients of a quadratic polynomial
-- and calculates the number of (real) roots of the polynomial.
nRoots :: (Floating a, Ord a) => a -> a -> a -> Int
nRoots a b c
  | delta > 0 = 2
  | delta == 0 = 1
  | delta < 0 = 0
  where
    delta = (b ** 2) - (4 * a * c)

-- It's similar to:
nRoots_v2 :: Float -> Float -> Float -> Int
nRoots_v2 a b c =
  let delta = (b ** 2) - (4 * a * c)
   in if delta > 0 then 2 else if delta == 0 then 1 else 0

-- (b) The function 'roots' that receives the coefficients of the polynomial and calculates the list of its real roots.
roots :: (Floating a, Ord a) => a -> a -> a -> [a]
roots a b c
  | nr == 0 = []
  | nr == 1 = [(- b) / (2 * a)]
  | nr == 2 = [((- b) + sqrt ((b ** 2) - (4 * a * c))) / (2 * a), ((- b) - sqrt ((b ** 2) - (4 * a * c))) / (2 * a)]
  where
    nr = nRoots a b c

-- Ex. 3 - We will represent a point by a pair of numbers that represent its coordinates in the Cartesian plane.
type Point = (Float, Float)

-- (a) Define a function that receives 3 points, which are the vertices of a triangle, and returns a tuple with the lengths of its sides.
triSideLength :: Point -> Point -> Point -> (Float, Float, Float)
triSideLength vx vy vz = (dist vx vy, dist vy vz, dist vz vx)

-- (b) Define a function that receives the vertices of a triangle, and calculates the perimeter of the triangle.
triPerimeter :: Point -> Point -> Point -> Float
triPerimeter vx vy vz = (\(a, b, c) -> a + b + c) (triSideLength vx vy vz)

-- (c) Define a function that receives 2 points, which are the vertices of the diagonal of a rectangle parallel to the axes,
-- and constructs a list with the 4 points of that rectangle.
rectPoints :: Point -> Point -> [Point]
rectPoints (x, y) (w, z) = [(x, y), (x, z), (w, z), (w, y)]

-- Ex. 4 - We will represent hours by a pair of integers.
-- Thus, the pair '(0,15)' means midnight and a quarter, and '(13,45)' means a quarter to two.
type Hour = (Int, Int)

-- Define functions to:

-- (a) Test if a pair of integers represents a valid time of day.
isValidTime :: Hour -> Bool
isValidTime (h, m) = (h >= 0) && (h < 24) && (m >= 0) && (m < 60)

-- (b) Test if one time is later than another (compares that the first one (input) is before the second one).
isTimeBefore :: Hour -> Hour -> Bool
isTimeBefore (hx, mx) (hy, my) = (hx < hy) || ((hx == hy) && (mx < my))

-- (c) Convert a time (pair of integers) to minutes (integer).
hour2Mins :: Hour -> Int
hour2Mins (h, m) = (h * 60) + m

-- (d) Convert a number of minutes to hours.
mins2Hour :: Int -> Hour
mins2Hour m = divMod m 60

-- (e) Calculate the difference between two times (the result should be the number of minutes).
hoursDiff :: Hour -> Hour -> Int
hoursDiff sh eh = abs ((hour2Mins eh) - (hour2Mins sh))

-- (f) Add a certain number of minutes to a given time.
addMins :: Hour -> Int -> Hour
addMins h m = mins2Hour ((hour2Mins h) + m)
