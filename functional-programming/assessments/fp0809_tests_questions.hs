-- @author Pirata
-- @version 2011.02

module Tests_Questions where

import Data.Char (isDigit, isUpper)
import Data.List (sort)

-- Ex. 1 - Define a function 'countOccurrences :: a -> [a] -> Int' that counts how many elements in a list
-- are equal to a given element.
countOccurrences :: (Eq a) => a -> [a] -> Int
countOccurrences _ [] = 0
countOccurrences x (y : ys)
  | x == y = 1 + countOccurrences x ys
  | otherwise = countOccurrences x ys

-- Ex. 2 - Define a function 'countGreaterThanFirst :: [a] -> Int' that, given a non-empty list,
-- determines how many elements are greater than the first element.
countGreaterThanFirst :: (Ord a) => [a] -> Int
countGreaterThanFirst [] = 0
countGreaterThanFirst [x] = 0
countGreaterThanFirst (x : y : ys)
  | y > x = 1 + countGreaterThanFirst (x : ys)
  | otherwise = countGreaterThanFirst (x : ys)

-- Ex. 3 - Define a function 'countPositives :: [Int] -> Int' that calculates how many elements
-- in a list of integers are positive.

countPositives :: [Int] -> Int
countPositives [] = 0
countPositives (x : xs)
  | x > 0 = 1 + countPositives xs
  | otherwise = countPositives xs

-- or
positives :: [Int] -> Int
positives l = countGreaterThanFirst (0 : l)

-- Ex. 4 - Define a function 'countUppercase :: [Char] -> Int' that determines how many elements in a list of chars
-- are upper case.
countUppercase :: [Char] -> Int
countUppercase [] = 0
countUppercase (x : xs)
  | x >= 'A' && x <= 'Z' = 1 + countUppercase xs
  | otherwise = countUppercase xs

-- Ex. 5 - Define a function 'elementsGreaterThanFirst :: [a] -> [a]' that, given a non-empty list, determines the list
-- of elements that are greater than the first element.
elementsGreaterThanFirst :: (Ord a) => [a] -> [a]
elementsGreaterThanFirst [] = []
elementsGreaterThanFirst [x] = []
elementsGreaterThanFirst (x : y : xs)
  | y > x = y : elementsGreaterThanFirst (x : xs)
  | otherwise = elementsGreaterThanFirst (x : xs)

-- Ex. 6 - Define a function 'positiveNumbers :: [Int] -> [Int]' that, given a list of integers, determines the
-- positive numbers in the list.
positiveNumbers :: [Int] -> [Int]
positiveNumbers [] = []
positiveNumbers (x : xs)
  | x > 0 = x : positiveNumbers xs
  | otherwise = positiveNumbers xs

-- Ex. 7 - Define a function 'oddNumbers :: [Int] -> [Int]' that, given a list of integers, determines the odd numbers
-- in the list.
oddNumbers :: [Int] -> [Int]
oddNumbers [] = []
oddNumbers (x : xs)
  | odd x = x : oddNumbers xs
  | otherwise = oddNumbers xs

-- Ex. 8 - Define a function 'maxElement :: [Int] -> Int' that returns the largest element in a non-empty list of
-- integers.
maxElement :: [Int] -> Int
maxElement [] = error "Empty list supplied to maxElement"
maxElement [x] = x
maxElement (x : y : xs)
  | y > x = maxElement (y : xs)
  | otherwise = maxElement (x : xs)

-- Ex. 9 - Define a function 'onlyConsonants :: String -> String' that removes all vowels from a string.
onlyConsonants :: String -> String
onlyConsonants [] = []
onlyConsonants (x : xs)
  | x `elem` "aeiouAEIOU" = onlyConsonants xs
  | otherwise = x : onlyConsonants xs

-- Ex. 10 - Define a function 'isSorted :: [Int] -> Bool' that checks if a list of integers is sorted.
isSorted :: [Int] -> Bool
isSorted [] = True
isSorted [_] = True
isSorted (x : y : xs)
  | x > y = False
  | otherwise = isSorted (y : xs)

-- Ex. 11 - Define a function 'evenCount :: [Int] -> Int' that, given a list of integers, determines how many even
-- numbers are in the list.
evenCount :: [Int] -> Int
evenCount [] = 0
evenCount (x : xs) = if even x then 1 + evenCount xs else evenCount xs

-- Ex. 12 - Define a function 'countDigits :: [Char] -> Int' that determines how many elements of a character list are
-- digits (use the function 'isDigit :: Char -> Bool' if necessary).
countDigits :: [Char] -> Int
countDigits [] = 0
countDigits (x : xs) = if isDigit x then 1 + countDigits xs else countDigits xs

-- Ex. 13 - Consider the type 'type Rectangle = (Int, Int)' to represent rectangles.
type Rectangle = (Int, Int)

-- (a) Define a function 'squares :: [Rectangle] -> Int' that, given a list of rectangles,
-- counts how many of them are squares.
squares :: [Rectangle] -> Int
squares [] = 0
squares ((a, b) : tail)
  | a == b = 1 + squares tail
  | otherwise = squares tail

-- (b) Define a function 'totalArea :: [Rectangle] -> Int' that calculates the total area of a list of rectangles.
totalArea :: [Rectangle] -> Int
totalArea [] = 0
totalArea ((a, b) : tail) = (a * b) + totalArea tail

-- or totalArea = foldl (\acc (a, b) -> acc + (a * b)) 0

-- Ex. 14 - Define the function 'decompress :: [(a, Int)] -> [a]' that replicates each element of the input list the
-- specified number of times. For example, 'decompress [('a', 2), ('b', 4)] == "aabbbb"'.
decompress :: [(a, Int)] -> [a]
decompress [] = []
decompress ((a, 1) : tail) = a : decompress tail
decompress ((a, b) : tail) = a : decompress ((a, b - 1) : tail)

-- Ex. 15 - Define the function 'remove :: [a] -> Int -> [a]' that removes the element of the list at the specified
-- position. For example, 'remove [1,2,3,4] 2 == [1,2,4]'.
remove :: [a] -> Int -> [a]
remove [] _ = []
remove (x : xs) 0 = xs
remove (x : xs) y = x : remove xs (y - 1)

-- Could also be done using take and drop, but would run twice.

-- Ex. 16 - Define the function 'copy :: [a] -> [Int] -> [a]' that copies into the result the elements of the first
-- list that are at the positions indicated by the second list. For example, copy "abcde" [1,3,1] == "bdb".
copy :: [a] -> [Int] -> [a]
copy _ [] = []
copy [] _ = []
copy lst (x : xs) = aux lst x : copy lst xs
  where
    aux l n = l !! n

-- Ex. 17 - Define the function 'replicatE :: Int -> a -> [a]' that constructs a list with 'n' copies of an element.
-- For example, 'replicatE 3 19 = [19,19,19]'.
replicatE :: Int -> a -> [a]
replicatE x a
  | x == 1 = [a]
  | x <= 0 = []
  | x > 1 = a : replicatE (x - 1) a

-- Ex. 18 - Without using list comprehensions, define the function 'range :: Int -> Int -> [Int]' that constructs a
-- list of integers between two limits. For example, 'range 3 9 = [3,4,5,6,7,8,9]'.
-- With list comprehensions it would be something like: range a b = [a .. b]
range :: Int -> Int -> [Int]
range a b = if a <= b then if a == b then [a] else a : range (a + 1) b else range b a

-- Ex. 19 - Define the function 'interleave :: a -> [a] -> [a]' that interleaves a given element between every two
-- elements of a list. For example, 'interleave ',' "abc" == "a,b,c"'.
interleave :: a -> [a] -> [a]
interleave _ [] = []
interleave _ [a] = [a]
interleave a (x : xs) = x : a : interleave a xs

-- Ex. 20 - Recursively define the function 'equalLists :: [Int] -> [Int] -> Bool' that tests if two lists are equal.
equalLists :: [Int] -> [Int] -> Bool
equalLists [] [] = True
equalLists _ [] = False
equalLists [] _ = False
equalLists (x : xs) (y : ys) = x == y && equalLists xs ys

-- Ex. 21 - Define the function 'iniT :: [a] -> [a]' recursively, which removes the last element of a list.
-- For example: 'iniT [1,2,3,4] == [1,2,3]'.
iniT :: [a] -> [a]
iniT [] = []
iniT [x] = []
iniT (x : xs) = x : iniT xs

-- Ex. 22 - Define the function 'splitat :: Int -> [a] -> ([a], [a])' recursively, which splits a list at a given
-- position. For example: 'splitat 2 "abcde" == ("ab", "cde")'.
splitat :: Int -> [a] -> ([a], [a])
splitat _ [] = ([], [])
splitat 0 l = ([], l)
splitat x (y : ys) = (y : a, b)
  where
    (a, b) = splitat (x - 1) ys

-- Ex. 23 - In Haskell, a matrix can be represented as a list of lists (one list for each row):
type Matrix = [Row]

type Row = [Float]

-- (a) Define the function 'maxMat :: Matrix -> Float' that calculates the largest element in a (non-empty) matrix.
maxMat :: Matrix -> Float
maxMat m = auxM (map auxM m)
  where
    -- auxM is basically maximum
    auxM :: [Float] -> Float
    auxM [x] = x
    auxM (x : y : xs)
      | x >= y = auxM (x : xs)
      | otherwise = auxM (y : xs)

-- (b) Define the function 'countOccurrences :: Float -> Matrix -> Int' that calculates how many times a given number
-- occurs in a matrix.
countOccurMat :: Float -> Matrix -> Int
countOccurMat _ [] = 0
countOccurMat x m = sum (map (countOccurrences x) m)

-- (c) Define the function 'ok :: Matrix -> Bool' that checks if a matrix is well-formed (all rows have the same
-- length). For example: 'ok [[1,2,3],[3,2,1]] == True' and 'ok [[1,2,3],[3,2]] == False'
ok :: Matrix -> Bool
ok [] = True
ok [x] = True
ok (x : y : xs) = length x == length y && ok (y : xs)

-- (d) Define the function 'zero :: Int -> Int -> Matrix' that, given the desired dimensions, creates a matrix
-- filled with zeros. For example: 'zero 3 2 == [[0,0,0],[0,0,0]]'
zero :: Int -> Int -> Matrix
zero _ 0 = []
zero 0 _ = []
zero x 1 = [replicate x 0]
zero x y = replicate x 0 : zero x (y - 1)

-- Ex. 24 - Consider the following function that calculates the length of the longest string in a list.
-- longestStringLength :: [String] -> Int
-- longestStringLength [s] = length s
-- longestStringLength (s : ss) = max (length s) (longestStringLength ss)
-- Provide an alternative definition of this function using higher-order functions instead of explicit recursion.
-- You may also use the 'maximum' function, which calculates the largest number in a list.
longestStringLength :: [String] -> Int
longestStringLength = maximum . map length

-- Ex. 25 - Consider the following function that counts how many strings in a list are proper names (start with an
-- uppercase letter):
-- properNames :: [String] -> Int
-- properNames [] = 0
-- properNames (h : t)
--   | isProper h = 1 + properNames t
--   | otherwise  = properNames t
isProper (c : _) = isUpper c

-- Provide an alternative definition of this function using higher-order functions instead of explicit recursion.
-- You may use the 'isProper' function defined above.
properNames :: [String] -> Int
properNames = length . filter isProper

-- Ex. 26 - Consider the following function that calculates how many times a specific element appears in a list:
-- counts :: Eq a => a -> [a] -> Int
-- counts x [] = 0
-- counts x (h:t)
--   | x == h    = 1 + counts x t
--   | otherwise = counts x t
-- Provide an alternative definition of this function using higher-order functions instead of explicit recursion.
counts :: Eq a => a -> [a] -> Int
counts x l = length (filter (x ==) l)

-- Ex. 27 - Consider the following function that calculates the total area of squares from a list of squares and
-- rectangles:
-- squaresArea :: [(Int, Int)] -> Int
-- squaresArea [] = 0
-- squaresArea (h:t)
--   | isSquare h = area h + squaresArea t
--   | otherwise  = squaresArea t
isSquare (h, v) = h == v

area (h, v) = h * v

-- Provide an alternative definition of this function using higher-order functions instead of explicit recursion.
-- You may also use the 'isSquare' and 'area' functions defined above.
squaresArea :: [(Int, Int)] -> Int
squaresArea l = sum (map area (filter isSquare l))

-- Ex. 28 - Define the function 'allDifferent :: Eq a => [a] -> Bool', which tests whether all elements in a list are
-- different from one another.
allDifferent :: Eq a => [a] -> Bool
allDifferent [] = True
allDifferent [x] = True
allDifferent (x : y : xs) = auxDif x (y : xs) && allDifferent (y : xs)
  where
    auxDif :: Eq a => a -> [a] -> Bool
    auxDif _ [] = True
    auxDif a (b : bs) = a /= b && auxDif a bs

-- Ex. 29 - Define the function 'leq :: String -> String -> Bool', which tests whether the first string precedes the
-- second in lexicographic order.
leq :: String -> String -> Bool
leq [] [] = True
leq [] _ = True
leq _ [] = False
leq (a : as) (c : cs)
  | a < c = True
  | a > c = False
  | a == c = leq as cs

-- Ex. 30 - Assume that the records of minimum and maximum temperatures for several days are stored in the following
-- data structure:
type TempTab = [(Date, TempMin, TempMax)]

type Date = (Int, Int, Int) -- Year, Month, Day

type TempMin = Float

type TempMax = Float

-- (a) Define 'averages :: TempTab -> [(Date, Float)]' that calculates the average temperature for each day.
averages :: TempTab -> [(Date, Float)]
averages [] = []
averages ((dt, mnt, mxt) : tail) = (dt, (mnt + mxt) / 2) : averages tail

-- (b) Define 'minMin :: TempTab -> Float' that calculates the lowest minimum temperature recorded in the table.
minMin :: TempTab -> Float
minMin l@((dt, mnt, mxt) : tail) = minimum (map (\(_, b, _) -> b) l)

-- Ex. 31 - Assume that information about the results of the matches in a round of a football championship is stored in
-- the following data structure:
type Round = [Match]

type Match = ((Team, Goals), (Team, Goals))

type Team = String

type Goals = Int

-- (a) Define the function 'totalGoals :: Round -> Int' that calculates the total number of goals scored in a round.
totalGoals :: Round -> Int
totalGoals j = sum (map aux j)
  where
    aux ((_, x), (_, y)) = x + y

-- (b) Define the function 'points :: Round -> [(Team, Int)]' that calculates the points each team earned in the round
-- (win - 3 points; loss - 0 points; draw - 1 point).
points :: Round -> [(Team, Int)]
points [] = []
points r = auxP r []
  where
    auxP [] res = res
    auxP (((team1, goals1), (team2, goals2)) : tail) res
      | goals1 > goals2 = auxP tail (addPoints team1 3 (addPoints team2 0 res))
      | goals1 == goals2 = auxP tail (addPoints team1 1 (addPoints team2 1 res))
      | otherwise = auxP tail (addPoints team2 3 (addPoints team1 0 res))
    addPoints team points [] = [(team, points)]
    addPoints team points (l@(team0, points0) : ls)
      | team0 == team = (team0, points0 + points) : ls
      | otherwise = l : addPoints team points ls

-- Ex. 32 - Assume that the records of the minimum and maximum temperatures over several days are stored in the data
-- structure 'TempTable'. Consider the following definitions:
type TempTable = [(Date, TempMin, TempMax, Precipitation)]

type Precipitation = Float

-- (a) Define the function 'termAmpl :: TempTable -> [(Date, Float)]' that calculates the temperature amplitude
-- (difference between maximum and minimum temperature) for each day.
termAmpl :: TempTable -> [(Date, Float)]
termAmpl [] = []
termAmpl ((d, x, y, _) : t) = (d, y - x) : termAmpl t

-- (b) Define the function 'maxRain :: TempTable -> (Date, Float)' that calculates the day with the highest
-- precipitation and its value.
maxRain :: TempTable -> (Date, Float)
maxRain [] = error "Cannot calculate maximum rainfall for an empty list"
maxRain ((d, _, _, p) : t) = auxRain (d, p) t
  where
    auxRain r [] = r
    auxRain (d, p) ((d2, _, _, p2) : ts) = if p2 > p then auxRain (d2, p2) ts else auxRain (d, p) ts

-- Ex. 33 - To store the final grades of students, the following types were defined:
type GradeTable = [(StudentID, Grade)]

type StudentID = String

type Grade = Int

-- (a) Define the function 'gradePartition :: GradeTable -> ([StudentID], [StudentID])' that, given the grade table,
-- returns a pair of lists with the IDs of students who failed and passed (grade greater than or equal to 10).
gradePartition :: GradeTable -> ([StudentID], [StudentID])
gradePartition [] = ([], [])
gradePartition ((st, gr) : ts) = if gr < 10 then (a, st : b) else (st : a, b)
  where
    (a, b) = gradePartition ts

-- (b) Define the function 'getGrade :: StudentID -> GradeTable -> Maybe Grade', which retrieves the grade of a given
-- student, if it exists.
getGrade :: StudentID -> GradeTable -> Maybe Grade
getGrade _ [] = Nothing
getGrade sId ((stID, gr) : ts)
  | sId == stID = Just gr
  | otherwise = getGrade sId ts

-- Ex. 34 - Define the function 'splitAtt :: Int -> [a] -> ([a], [a])' that takes an integer 'n' and a list, and
-- produces a pair of lists: the first list contains the first 'n' elements of the input list, and the second list
-- contains the remaining elements.
-- For example: 'splitAtt 3 [5, 3, 4, 2, 1]' == '([5, 3, 4], [2, 1])'
-- There's already a recursive splitAt implementation in exercise 22. Using a different approach here.
splitAtt :: Int -> [a] -> ([a], [a])
splitAtt n ls = (take n ls, drop n ls)

-- Ex. 35 - Define the function 'greaterThan :: Int -> [Int] -> Maybe Int' that, given a number and a sorted list of
-- numbers, returns the first number in the list that is greater than the given number.
greaterThan :: Int -> [Int] -> Maybe Int
greaterThan x [] = Nothing
greaterThan x (y : ys) = if x < y then Just y else greaterThan x ys

-- Ex. 36 - Define a function 'filterPair :: (a -> Bool) -> [a] -> ([a], [a])' that takes a predicate and a list, and
-- returns a pair where the first component contains the elements of the list that satisfy the predicate, and the
-- second component contains the elements that do not.
filterPair :: (a -> Bool) -> [a] -> ([a], [a])
filterPair p [] = ([], [])
filterPair p (x : xs) = if p x then (x : a, b) else (a, x : b)
  where
    (a, b) = filterPair p xs

-- Ex. 37 - Consider the following definitions:
data Evaluation
  = A Float Float -- Test, Practical
  | B Float

type Student = (Int, String, Evaluation) -- ID, Name, Evaluation

type Class = [Student]

-- (a) Define a function 'finalGrade :: Evaluation -> Maybe Int' that calculates the final grade of a student (returns
-- 'Nothing' if they failed). Note that:
-- - In method 'B', the student passes only if the test grade is greater than or equal to 9.5.
-- - In method 'A', the student passes only if the test grade is greater than or equal to 8.0 and the weighted average
-- (0.7 * t + 0.3 * p) is greater than or equal to 9.5.
finalGrade :: Evaluation -> Maybe Int
finalGrade (A t p)
  | (t >= 8.0) && (total > 9.5) = Just (round total)
  | otherwise = Nothing
  where
    total = 0.7 * t + 0.3 * p
finalGrade (B t)
  | t >= 9.5 = Just (round t)
  | otherwise = Nothing

-- (b) Given a function 'finalGrade :: Evaluation -> Maybe Int' that calculates a student's final grade ('Nothing' if
-- failed), define a function 'reportCard :: Class -> [(String, String, String)]' that produces the report card.
reportCard :: Class -> [(String, String, String)]
reportCard [] = []
reportCard ((num, name, gr) : cs) = case finalGrade gr of
  Nothing -> (show num, name, "Rep") : reportCard cs
  Just a -> (show num, name, show a) : reportCard cs

-- (c) Define a function 'bestStudentA :: Class -> Maybe Int' that determines the ID of the student in method 'A' with
-- the highest test grade.
bestStudentA :: Class -> Maybe Int
bestStudentA [] = Nothing
bestStudentA xs = case [(t, num) | (num, _, A t p) <- xs] of
  [] -> Nothing
  ts -> Just (snd $ maximum ts)

-- (d) Knowing that in method 'A', the final grade is calculated as (0.7 * t + 0.3 * p), define a function
-- 'finalGrades :: Class -> [(Int, Float)]' that calculates the grades of students in a class.
finalGrades :: Class -> [(Int, Float)]
finalGrades [] = []
finalGrades ((num, name, grd) : cs) = (num, x) : finalGrades cs
  where
    x = auxGrade grd
    auxGrade (A t p) = 0.7 * t + 0.3 * p
    auxGrade (B t) = t

-- (e) Knowing that in method 'A', the final grade is calculated as (0.7 * t + 0.3 * p) with a minimum test grade of 8,
-- define a function 'passed :: Student -> Bool' that checks if a given student passed.
passed :: Student -> Bool
passed (_, _, grd) = case grd of
  A x y -> (x >= 8) && (auxGrade grd >= 10)
  B x -> x >= 10
  where
    auxGrade (A t p) = 0.7 * t + 0.3 * p
    auxGrade (B t) = t

-- (f) Knowing that there is a function 'passed :: Bool' that checks if a student passed, define a function
-- 'stat :: Class -> (Float, Float)' that calculates the percentage of students who passed in each evaluation method.
stat :: Class -> (Float, Float)
stat [] = (0, 0)
stat (student@(num, name, grd) : cs) = case grd of
  (A x y) -> if passed student then (1 + a, b) else (a, b)
  (B x) -> if passed student then (a, 1 + b) else (a, b)
  where
    (a, b) = stat cs

-- Ex. 38 - Consider the following definition that calculates the length of the longest string in a list:
-- longestLength :: [String] -> Int
-- longestLength [s] = length s
-- longestLength (s:ss) = max (length s) (longestLength ss)
-- Provide an alternative version of this function using 'map' (to calculate the length of all strings) and 'maximum'.
longestLength :: [String] -> Int
longestLength = maximum . map length

-- Ex. 39 - Consider the following definition that counts how many elements in a list of strings are proper nouns
-- (start with an uppercase letter):
-- properNouns :: [String] -> Int
-- properNouns [] = 0
-- properNouns (h:t)
--   | isProper h = 1 + (properNouns t)
--   | otherwise  = properNouns t
-- isProper (c:_) = isUpper c
-- Provide an alternative version of this function using 'filter' and 'length' (you can use the 'isProper' function).
properNouns :: [String] -> Int
properNouns ss = length (filter isProper ss)

-- Ex. 40 - Consider the following definition of the function '!! :: [a] -> Int -> a' predefined in Haskell, which
-- selects an element from the list: 'l !! n = head (drop n l)'
-- Provide an alternative recursive definition without using the 'drop' function.
(!!!) :: [a] -> Int -> a
[] !!! _ = error "Empty list"
(h : t) !!! n
  | n < 0 = error "Negative index"
  | n == 0 = h
  | otherwise = t !!! (n - 1)

-- Ex. 41 - Consider the following definition of a function that removes an element from a list:
-- allBut :: [a] -> Int -> [a]
-- allBut l n = (a, tail b)
--   where (a, b) = splitAt n l
-- Present an alternative (recursive) definition of the function that does not use 'splitAt'.
allBut :: [a] -> Int -> [a]
allBut (h : t) n
  | n == 0 = t
  | n > 0 = h : allBut t (n - 1)
  | otherwise = h : t

-- Ex. 42 - Recall the definition of the function 'split':
-- split :: a -> [a] -> ([a], [a])
-- split a l = ([x | x <- l, x <= a], [x | x <- l, x > a])
-- This function splits a list into two: one with elements less than or equal to a given value, and the other with
-- elements greater than that value. Present an alternative (recursive) definition of the function that does not use
-- list comprehensions and traverses the list only once.
splitt :: (Ord a) => a -> [a] -> ([a], [a])
splitt _ [] = ([], [])
splitt x (h : t) = if h <= x then (h : a, b) else (a, h : b)
  where
    (a, b) = splitt x t

-- Ex. 43 - Consider the following definition of the function 'extractMultiples':
-- extractMultiples :: [Int] -> Int -> ([Int], [Int])
-- extractMultiples l n = ([x | x <- l, mod x n == 0], [x | x <- l, mod x n /= 0])
-- This function, given a list of integers and a number, computes two lists: one with all elements of the list that are
-- multiples of the given number, and the other with the remaining elements. Present an alternative (recursive)
-- definition of the function that does not use list comprehensions and traverses the list only once.
extractMultiples :: [Int] -> Int -> ([Int], [Int])
extractMultiples [] _ = ([], [])
extractMultiples (h : t) x = if mod h x == 0 then (h : a, b) else (a, h : b)
  where
    (a, b) = extractMultiples t x

-- Ex. 44 - Consider the following type for representing optional values:
-- data Maybe a = Nothing | Just a
-- Define the function 'catMaybes :: [Maybe a] -> [a]' that extracts all the values from the input list.
catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Nothing : t) = catMaybes t
catMaybes ((Just a) : t) = a : catMaybes t

-- Ex. 45 - Consider the following type for representing trees with values at the leaves:
data Tree a = Leaf a | Fork (Tree a) (Tree a)

-- (a) Define the function 'leaves :: Tree a -> [a]' that returns all the leaves of the tree.
leaves :: Tree a -> [a]
leaves (Leaf a) = [a]
leaves (Fork a b) = leaves a ++ leaves b

-- (b) Define the function 'height :: Tree a -> Int' that computes the height of a tree.
height :: Tree a -> Int
height (Leaf a) = 1
height (Fork a b) = 1 + max (height a) (height b)

-- Ex. 46 - Consider the following type for representing natural numbers and define the function 'toInt :: Nat -> Int'
-- that converts a natural number to its corresponding integer.
data Nat = Zero | Succ Nat

toInt :: Nat -> Int
toInt Zero = 0
toInt (Succ a) = 1 + toInt a

-- Ex. 47 - Recall the definition of the function 'lookup':
-- lookup :: (Eq a) => [(a, b)] -> a -> Maybe b
-- lookup _ [] = Nothing
-- lookup x ((a, b) : t)
--   | x == a    = Just b
--   | otherwise = lookup x t
-- Define the function 'lookupSort :: (Ord a) => [(a, b)] -> a -> Maybe b' which produces the same result as 'lookup',
-- but assumes (and takes advantage of) the fact that the list is sorted in ascending order by the first component.
lookupSort :: (Ord a) => [(a, b)] -> a -> Maybe b
lookupSort [] x = Nothing
lookupSort ((a, b) : t) x
  | a < x = lookupSort t x
  | a == x = Just b
  | a > x = Nothing

-- Ex. 48 - Consider the following definition of the type of binary trees:
data BTree a = Empty | Node a (BTree a) (BTree a)
  deriving (Eq, Show)

-- (a) Define a function 'lookupBST :: (Ord a) => BTree (a, b) -> a -> Maybe b' that searches for a value in a binary
-- search tree (sorted by the first component).
lookupBST :: (Ord a) => BTree (a, b) -> a -> Maybe b
lookupBST Empty _ = Nothing
lookupBST (Node (key, val) left right) x
  | x < key = lookupBST left x
  | x > key = lookupBST right x
  | x == key = Just val

-- (b) Define a function 'lookupBT :: (Eq a) => BTree (a, b) -> a -> Maybe b' that searches for a value in a binary tree
-- that is not necessarily a search tree.
lookupBT :: (Eq a) => BTree (a, b) -> a -> Maybe b
lookupBT Empty _ = Nothing
lookupBT (Node (key, val) left right) target
  | key == target = Just val
  | otherwise = case lookupBT left target of
    Just result -> Just result
    Nothing -> lookupBT right target

-- (c) Recall the definition of the function 'map :: (a -> b) -> [a] -> [b]'. Define a similar function for binary
-- trees: 'mapBT :: (a -> b) -> BTree a -> BTree b'.
mapBT :: (a -> b) -> BTree a -> BTree b
mapBT p Empty = Empty
mapBT p (Node x l r) = Node (p x) (mapBT p l) (mapBT p r)

-- (d) Define a function 'isBST :: (Ord a) => BTree a -> Bool' that tests whether a binary tree is a binary search tree.
isBST :: (Ord a) => BTree a -> Bool
isBST Empty = True
isBST (Node x l r) = isBST l && isBST r && ((l == Empty) || (x > maximum (inorder l))) && ((r == Empty) || (x < minimum (inorder r)))

inorder :: BTree a -> [a]
inorder Empty = []
inorder (Node x l r) = inorder l ++ [x] ++ inorder r

-- (e) Define a function 'isHeap :: (Ord a) => BTree a -> Bool' that tests whether a binary tree is a heap (a heap is a
-- tree where the root is the smallest element, and its subtrees are also heaps).
isHeap :: (Ord a) => BTree a -> Bool
isHeap Empty = True
isHeap (Node x l r) = isHeap l && isHeap r && ((l == Empty) || (x < minimum (inorder l))) && ((r == Empty) || (x < minimum (inorder r)))

-- (f) Define a function 'isBalanced :: BTree a -> Bool' that tests whether a binary tree is balanced (a tree is
-- balanced if the height difference between its subtrees is at most 1, and those subtrees are also balanced).
isBalanced :: BTree a -> Bool
isBalanced Empty = True
isBalanced (Node x l r) = (auxHeights <= 1) && isBalanced l && isBalanced r
  where
    auxHeights = abs (auxH l - auxH r)
    auxH Empty = 0
    auxH (Node _ lf rt) = 1 + max (auxH lf) (auxH rt)

-- (g) Define a function 'isEquilibrated :: BTree a -> Bool' that tests whether a binary tree is equilibrated (a tree is
-- equilibrated if the difference in the number of elements between its subtrees is at most 1, and those subtrees are
-- also equilibrated).
isEquilibrated :: BTree a -> Bool
isEquilibrated Empty = True
isEquilibrated (Node x e d) = (auxCount <= 1) && isEquilibrated e && isEquilibrated d
  where
    auxCount = abs (auxC e - auxC d)
    auxC Empty = 0
    auxC (Node _ lf rt) = 1 + auxC lf + auxC rt
