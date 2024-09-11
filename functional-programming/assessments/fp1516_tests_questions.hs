-- @author Pirata
-- @version 2015.02

module Tests_Questions where

import Data.Char (isDigit, toLower)

-- Ex. 1 - Provide a recursive definition for the (predefined) function 'enumFromTo :: Int -> Int -> [Int]', which
-- constructs a list of integers between two limits.
enumFromToo :: Int -> Int -> [Int]
enumFromToo st end = if st <= end then st : enumFromToo (st + 1) end else []

-- Ex. 2 - Provide a recursive definition for the (predefined) function 'enumFromThenTo :: Int -> Int -> Int -> [Int]',
-- which constructs a list of integers between two limits, spaced by a constant value.
enumFromThenToo :: Int -> Int -> Int -> [Int]
enumFromThenToo st nxt end
  | step == 0 = []            -- Infinite loop averted
  | step > 0 && st > end = [] -- positive step but start is greater than the end
  | step < 0 && st < end = [] -- negative step but start is lower than the end
  | step > 0 = takeWhile (<= end) (iterate (+ step) st)
  | otherwise = takeWhile (>= end) (iterate (+ step) st)
  where
    step = nxt - st

-- AUTHOR NOTE: There be a much'easier way to tackle this function fer the test, but I went and solved it more
-- deeply here, aye!

-- Ex. 3 - Provide a recursive definition for the (predefined) function '(++) :: [a] -> [a] -> [a]' that concatenates
-- two lists.
(+++) :: [a] -> [a] -> [a]
(+++) [] l = l
(+++) (h : ts) l2 = h : (+++) ts l2 -- Could just be '(+++) ts list = foldr (:) list ts'

-- Ex. 4 - Provide a recursive definition for the (predefined) function 'last :: [a] -> a', which computes the last
-- element of a non-empty list.
lastt :: [a] -> a
lastt [] = error "Empty list"
lastt [x] = x
lastt (h : ts) = lastt ts

-- Ex. 5 - Provide a recursive definition for the (predefined) function 'init :: [a] -> [a]', which computes a list
-- equal to the given list but without its last element.
initt :: [a] -> [a]
initt [] = error "Empty list"
initt [x] = []
initt (h : ts) = h : initt ts

-- Ex. 6 - Provide a recursive definition for the (predefined) function '(!!) :: [a] -> Int -> a', which, given a list
-- and an integer, computes the element at the specified position in the list (assuming the first element is at position
-- 0). Ignore cases where the position is invalid (Assuming correct indexes and non empty lists).
(!!!) :: [a] -> Int -> a
(!!!) (h : _) 0 = h
(!!!) (_ : ts) n = (!!!) ts (n - 1)

-- Ex. 7 - Provide a recursive definition for the (predefined) function 'reverse :: [a] -> [a]', which computes a list
-- with the elements of the given list in reverse order.
reversee :: [a] -> [a]
reversee list = revAux list []
  where
    revAux [] new = new
    revAux (h : ts) new = revAux ts (h : new)

-- Ex. 8 - Provide a recursive definition for the (predefined) function 'take :: Int -> [a] -> [a]', which computes a
-- list containing at most the first 'n' elements of a given list 'l'. The resulting list will have fewer than 'n'
-- elements if 'l' contains fewer than 'n' elements.
takee :: Int -> [a] -> [a]
takee _ [] = []
takee 0 _ = []
takee x (h : ts) = h : takee (x - 1) ts

-- Ex. 9 - Provide a recursive definition for the (predefined) function 'drop :: Int -> [a] -> [a]', which computes a
-- list excluding at most the first 'n' elements of a given list 'l'. If the list contains 'n' or fewer elements, the
-- result is an empty list.
dropp :: Int -> [a] -> [a]
dropp 0 l = l
dropp _ [] = []
dropp x (_ : ts) = dropp (x - 1) ts

-- Ex. 10 - Provide a recursive definition for the (predefined) function 'zip :: [a] -> [b] -> [(a,b)]', which
-- constructs a list of pairs from two given lists.
zipp :: [a] -> [b] -> [(a, b)]
zipp [] _ = []
zipp _ [] = []
zipp (x : xs) (y : ys) = (x, y) : zipp xs ys

-- Ex. 11 - Provide a recursive definition for the (predefined) function 'elem :: Eq a => a -> [a] -> Bool', which tests
-- if an element occurs in a list.
elemm :: Eq a => a -> [a] -> Bool
elemm _ [] = False
elemm x (h : ts) = (x == h) || elemm x ts

-- Ex. 12 - Provide a recursive definition for the (predefined) function 'replicate :: Int -> a -> [a]', which, given an
-- integer 'n' and an element 'x', constructs a list with 'n' elements, all equal to 'x'.
replicatee :: Int -> a -> [a]
replicatee 0 _ = []
replicatee x item = item : replicatee (x - 1) item

-- Ex. 13 - Provide a recursive definition for the (predefined) function 'intersperse :: a -> [a] -> [a]', which, given
-- an element and a list, constructs a list where the provided element is interspersed between the elements of the given
-- list. Example: 'intersperse 1 [10,20,30]' corresponds to '[10,1,20,1,30]'.
interspercee :: a -> [a] -> [a]
interspercee _ [] = []
interspercee _ [a] = [a]
interspercee x (h : ts) = h : x : interspercee x ts

-- Ex. 14 - Provide a recursive definition for the (predefined) function 'group :: Eq a => [a] -> [[a]]', which groups
-- equal and consecutive elements from a list into sublists.
groupp :: Eq a => [a] -> [[a]]
groupp [] = []
groupp (h : ts) = l1 : groupp l2
  where
    (l1, l2) = span (== h) (h : ts)

-- Ex. 15 - Provide a recursive definition for the (predefined) function 'concat :: [[a]] -> [a]', which concatenates
-- the lists within a list.
concatt :: [[a]] -> [a]
concatt [] = []
concatt (h : ts) = h ++ concatt ts

-- Ex. 16 - Provide a recursive definition for the (predefined) function 'inits :: [a] -> [[a]]', which computes the
-- list of prefixes of a given list.
inits :: [a] -> [[a]]
inits list = initsAux list []
  where
    initsAux [] end = [end]
    initsAux (h : ts) list = list : initsAux ts (list ++ [h])

-- Ex. 17 - Provide a recursive definition for the (predefined) function 'tails :: [a] -> [[a]]', which computes the
-- list of suffixes of a given list.
tails :: [a] -> [[a]]
tails [] = [[]]
tails l@(h : ts) = l : tails ts

-- Ex. 18 - Provide a recursive definition for the (predefined) function 'isPrefixOf :: Eq a => [a] -> [a] -> Bool',
-- which tests if one list is a prefix of another.
isPrefixOf :: Eq a => [a] -> [a] -> Bool
isPrefixOf [] _ = True
isPrefixOf list [] = False
isPrefixOf (x : xs) (y : ys) = (x == y) && isPrefixOf xs ys

-- Ex. 19 - Provide a recursive definition for the (predefined) function 'isSuffixOf :: Eq a => [a] -> [a] -> Bool',
-- which tests if one list is a suffix of another.
isSuffixOf :: Eq a => [a] -> [a] -> Bool
isSuffixOf list1 list2 = reverse list1 `isPrefixOf` reverse list2

-- Ex. 20 - Provide a recursive definition for the (predefined) function 'isSubsequenceOf :: Eq a => [a] -> [a] -> Bool'
-- which tests if the elements of one list occur in another list in the same relative order.
isSubsequenceOf :: Eq a => [a] -> [a] -> Bool
isSubsequenceOf [] _ = True
isSubsequenceOf _ [] = False
isSubsequenceOf (x : xs) (y : ys) = if x == y then isSubsequenceOf xs ys else isSubsequenceOf (x : xs) ys

-- Ex. 21 - Provide a recursive definition for the (predefined) function 'elemIndexes :: Eq a => a -> [a] -> [Int]',
-- which calculates the list of positions where a given element occurs in a list.
elemIndexes :: Eq a => a -> [a] -> [Int]
elemIndexes = auxEI 0
  where
    auxEI _ _ [] = []
    auxEI i y (x : xs) = if x == y then i : auxEI (i + 1) y xs else auxEI (i + 1) y xs

-- Ex. 22 - Provide a recursive definition for the (predefined) function 'nub :: Eq a => [a] -> [a]', which calculates a
-- list with the same elements as the given list but without duplicates.
nub :: Eq a => [a] -> [a]
nub l = nubAux l []
  where
    nubAux [] new = new
    nubAux (x : xs) new = if not (elem x new) then nubAux xs (new ++ [x]) else nubAux xs new

-- Ex. 23 - Provide a recursive definition for the (predefined) function 'delete :: Eq a => a -> [a] -> [a]', which
-- returns the list resulting from removing (the first occurrence of) a given element from a list.
delete :: Eq a => a -> [a] -> [a]
delete _ [] = []
delete x (h : ts) = if x == h then ts else h : delete x ts

-- Ex. 24 - Provide a recursive definition for the (predefined) function '(\\) :: Eq a => [a] -> [a] -> [a]', which
-- returns the list resulting from removing (the first occurrences of) the elements of the second list from the first.
(\\) :: Eq a => [a] -> [a] -> [a]
(\\) [] _ = []
(\\) lst [] = lst
(\\) lst (x : xs) = (\\) (delete x lst) xs

-- Ex. 25 - Provide a recursive definition for the (predefined) function 'union :: Eq a => [a] -> [a] -> [a]', which
-- returns the list resulting from appending to the first list the elements of the second list that do not occur in the
-- first. Example: 'union [1,1,2,3,4] [1,5]' corresponds to '[1,1,2,3,4,5]'.
union :: Eq a => [a] -> [a] -> [a]
union lst1 lst2 = lst1 ++ (lst2 \\ lst1)

-- Ex. 26 - Provide a recursive definition for the (predefined) function 'intersect :: Eq a => [a] -> [a] -> [a]', which
-- returns the list resulting from removing from the first list the elements that do not belong to the second.
intersect :: Eq a => [a] -> [a] -> [a]
intersect [] _ = []
intersect (h : ts) chList = if elem h chList then h : intersect ts chList else intersect ts chList

-- Ex. 27 - Provide a recursive definition for the (predefined) function 'insert :: Ord a => a -> [a] -> [a]', which,
-- given an element and an ordered list, returns the list resulting from inserting that element in the correct order
-- within the list.
insert :: Ord a => a -> [a] -> [a]
insert x [] = [x]
insert x l@(h : ts) = if x > h then h : insert x ts else x : l

-- Ex. 28 - Provide a recursive definition for the (predefined) function 'maximum :: Ord a => [a] -> a', which, given a
-- non-empty list, returns the largest element in the list.
maximumm :: Ord a => [a] -> a
maximumm lst = auxMx (head lst) lst
  where
    auxMx x [] = x
    auxMx x (h : ts) = if x < h then auxMx h ts else auxMx x ts

-- Ex. 29 - Provide a recursive definition for the (predefined) function 'minimum :: Ord a => [a] -> a', which, given a
-- non-empty list, returns the smallest element in the list.
minimumm :: Ord a => [a] -> a
minimumm list = auxMn (head list) list
  where
    auxMn x [] = x
    auxMn x (h : ts) = if x > h then auxMn h ts else auxMn x ts

-- Ex. 30 - Provide a recursive definition for the (predefined) function 'sum :: Num a => [a] -> a', which, given a
-- list, returns the sum of its elements.
summ :: Num a => [a] -> a
summ [] = 0
summ (h : ts) = h + summ ts

-- Ex. 31. Provide a recursive definition for the (predefined) function 'product :: Num a => [a] -> a', which, given a
-- list, returns the product of its elements.
productt :: Num a => [a] -> a
productt [] = 1
productt (h : ts) = h * (productt ts)

-- Ex. 32 - Provide a recursive definition for the (predefined) function 'and :: [Bool] -> Bool', which, given a list,
-- returns 'True' if all elements in the list are 'True'.
andd :: [Bool] -> Bool
andd [] = True
andd (h : ts) = if h then andd ts else h

-- Ex. 33 - Provide a recursive definition for the (predefined) function 'or :: [Bool] -> Bool', which, given a list,
-- returns 'True' if at least one element in the list is 'True'.
orr :: [Bool] -> Bool
orr [] = False
orr (h : ts) = if h then True else orr ts

-- Ex. 34 - Provide a recursive definition for the (predefined) function 'unwords :: [String] -> String', which
-- concatenates all the strings in a list into a single string, separating them by a space.
unwordss :: [String] -> String
unwordss [] = []
unwordss [x] = x
unwordss (h : ts) = h ++ " " ++ unwordss ts

-- Ex. 35 - Provide a recursive definition for the (predefined) function 'unlines :: [String] -> String', which
-- concatenates all the strings in a list into a single string, separating them by the '\n' character.
unliness :: [String] -> String
unliness [] = []
unliness [x] = x
unliness (h : ts) = h ++ "\n" ++ unliness ts

-- Ex. 36 - Provide a recursive definition for the function 'pLargest :: Ord a => [a] -> Int', which, given a non-empty
-- list, returns the position of the largest element in the list. Positions in the list start at '0', so the function
-- should return '0' if the first element is the largest.
pLargest :: Ord a => [a] -> Int
pLargest lst = auxPL 0 (head lst) 0 lst
  where
    auxPL iLargest largest iNow [] = iLargest
    auxPL iLargest largest iNow (h : ts) =
      if largest < h
        then auxPL iNow h (iNow + 1) ts
        else auxPL iLargest largest (iNow + 1) ts

-- Ex. 37 - Provide a recursive definition for the function 'hasDuplicates :: Eq a => [a] -> Bool', which tests if
-- a list contains duplicate elements.
hasDuplicates :: Eq a => [a] -> Bool
hasDuplicates [] = False
hasDuplicates (h : ts) = elem h ts || hasDuplicates ts

-- Ex. 38 - Provide a recursive definition for the function 'digits :: [Char] -> [Char]', which extracts the digits
-- from a given list of characters.
digits :: String -> String
digits "" = ""
digits (h : ts) = if isDigit h then h : digits ts else digits ts

-- Ex. 39 - Provide a recursive definition for the function 'oddPositions :: [a] -> [a]', which determines the elements
-- of a list that occur at odd positions.
-- Assume that the first element of the list is at position '0', which is considered even.
oddPositions :: [a] -> [a]
oddPositions (h : m : ts) = m : oddPositions ts
oddPositions _ = []

-- Ex. 40 - Provide a recursive definition for the function 'evenPositions :: [a] -> [a]', which determines the elements
-- of a list that occur at even positions.
-- Assume that the first element of the list is at position '0', which is considered even.
evenPositions :: [a] -> [a]
evenPositions [x] = [x]
evenPositions (h : m : ts) = h : evenPositions ts
evenPositions _ = []

-- Ex. 41 - Provide a recursive definition for the function 'isSorted :: Ord a => [a] -> Bool', which checks if a list
-- is sorted in ascending order.
isSorted :: Ord a => [a] -> Bool
isSorted [] = True
isSorted [x] = True
isSorted (h : m : ts) = (h <= m) && isSorted (m : ts)

-- Ex. 42 - Provide a recursive definition for the function 'iSort :: Ord a => [a] -> [a]', which calculates the result
-- of sorting a list. Assume, if needed, the existence of the function 'insert :: Ord a => a -> [a] -> [a]', which,
-- given an element and a sorted list, returns the list resulting from inserting the element in sorted order.
iSort :: Ord a => [a] -> [a]
iSort lst = auxS lst []
  where
    auxS [] new = new
    auxS (h : ts) new = auxS ts (insert h new)

-- Ex. 43 - Provide a recursive definition for the function 'smaller :: String -> String -> Bool', which, given two
-- strings, returns 'True' if and only if the first is lexicographically smaller than the second (dictionary order).
-- Example: 'smaller "sai" "saiu"' returns 'True', while 'smaller "programacao" "funcional"' returns 'False'.
smaller :: String -> String -> Bool
smaller "" _ = True
smaller _ "" = False
smaller (x : xs) (y : ys)
  | toLower x > toLower y = False
  | toLower x == toLower y = smaller xs ys
  | toLower x < toLower y = True

-- Ex. 44 - Assume the type '[(a, Int)]' is used to represent multisets of elements of type 'a'. Additionally, assume
-- these lists do not have pairs with the same first component, nor pairs where the second component is less than or
-- equal to zero.
-- Define the function 'elemMSet :: Eq a => a -> [(a, Int)] -> Bool', which checks if an element belongs to a multiset.
elemMSet :: Eq a => a -> [(a, Int)] -> Bool
elemMSet _ [] = False
elemMSet z ((x, _) : ts) = (z == x) || elemMSet z ts

-- Ex. 45 - Define the function 'lengthMSet :: [(a, Int)] -> Int', which calculates the size of a multiset.
lengthMSet :: [(a, Int)] -> Int
lengthMSet [] = 0
lengthMSet ((_, y) : ts) = y + lengthMSet ts

-- Ex. 46 - Define the function 'convertMSet :: [(a, Int)] -> [a]', which converts a multiset into a list of elements.
convertMSet :: [(a, Int)] -> [a]
convertMSet [] = []
convertMSet ((x, y) : ts) = if y > 0 then x : convertMSet ((x, y - 1) : ts) else convertMSet ts

-- Ex. 47 - Define the function 'insertMSet :: Eq a => a -> [(a, Int)] -> [(a, Int)]',
-- which adds an element to a multiset.
insertMSet :: Eq a => a -> [(a, Int)] -> [(a, Int)]
insertMSet z [] = [(z, 1)]
insertMSet z ((x, y) : ts) = if z == x then (x, y + 1) : ts else (x, y) : insertMSet z ts

-- Ex. 48 - Define the function 'removeMSet :: Eq a => a -> [(a, Int)] -> [(a, Int)]', which removes an element from a
-- multiset. If the element does not exist, the given multiset should be returned unchanged.
removeMSet :: Eq a => a -> [(a, Int)] -> [(a, Int)]
removeMSet _ [] = []
removeMSet z ((x, y) : ts) =
  if z == x
    then
      if y > 1
        then (x, y - 1) : ts
        else ts
    else (x, y) : removeMSet z ts

-- Ex. 49 - Define the function 'calculateMSet :: Ord a => [a] -> [(a, Int)]', which, given a list sorted in ascending
-- order, calculates the multiset of its elements.
calculateMSet :: Ord a => [a] -> [(a, Int)]
calculateMSet [] = []
calculateMSet lst = auxCMS (tail lst) (head lst) 1 []
  where
    auxCMS [] curr qts new = new ++ [(curr, qts)]
    auxCMS (h : ts) curr qts new =
      if h == curr
        then auxCMS ts curr (qts + 1) new
        else auxCMS ts h 1 (new ++ [(curr, qts)])

-- Ex. 50. Provide a recursive definition for the function 'sumEvens :: [Int] -> Int', which sums the even numbers in a
-- list of integers. Example: 'sumEvens [2,4,5,3,4,2]' returns '12'.
sumEvens :: [Int] -> Int
sumEvens [] = 0
sumEvens (h : ts) = if mod h 2 == 0 then h + sumEvens ts else sumEvens ts
