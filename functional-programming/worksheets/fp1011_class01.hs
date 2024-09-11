-- @author Pirata
-- @version 2010.10

{-- Introduction and connection with math --}
module Class01_1011 where

{-- 1. Set Definitions by Comprehension --}

-- Example of a set 'a'
a = [1..100]
-- Example of a set 'b'
b = [30,33..51]

-- Ex.1 - Given two sets A and B, return the subset of A whose elements are smaller than all elements of B.
smallerThanAll_v1 = [x | x <- a, all (> x) b]           -- { x ∈ A ∣ ∀ y ∈ B, y > x }
smallerThanAll_v2 = [x | x <- a, and [y > x | y <- b]]  -- { x ∈ A ∣ ∀ y ∈ B, y > x }
smallerThanAll_v3 = [x | x <- a, and (map (> x) b)]     -- { x ∈ A ∣ ∀ y ∈ B, y > x }
smallerThanAll_v4 = [x | x <- a, x < minimum b]         -- { x ∈ A ∣ x < min(B) }
smallerThanAll_v5 = [x | x <- a, not (or [y <= x | y <- b])]    -- { x ∈ A ∣ ¬(∃ y ∈ B, y ≤ x) }

-- Ex. 2 - Given a set A, return the subset of A whose elements are greater than the average of the elements of A.
greaterThanAverage_v1 = [x | x <- a, x > avg b]     -- { x ∈ A ∣ x > avg(B) }
    where
        avg l = div (sum l) (length l)
greaterThanAverage_v2 = [x | x <- a, x > div (sum b) (length b)]  -- { x ∈ A ∣ x > sum(B) / size(B) }

{-- 2. List Definitions by Comprehension --}

-- Ex. 1 - Express the corresponding list by enumeration for each expression and try to find another way to obtain the same result for each case.
-- (a) List of numbers from 1 to 20 that are divisible by both 2 and 3.
-- Result: [6,12,18]
exec21a_v1 = [x | x <- [1..20], mod x 2 == 0, mod x 3 == 0] -- Original one
exec21a_v2 = [x | x <- [1..20], mod x 6 == 0]               -- Numbers from 1 to 20 that are divisible by 6
exec21a_v3 = [x * 6 | x <- [1..3]]                          -- Numbers 1 to 3 multiplied by 6
exec21a_v4 = [x | x <- [1..20], x `elem` [6,12..1000]]      -- Numbers that belong to the intersection of numbers from 1 to 20 and multiples of 6
exec21a_v5 = [x | x <- [3,6..20], mod x 2 == 0]             -- Numbers from multiples of 3 that are divisible by 2

-- (b) List comprehension nested within another list comprehension for the same result.
-- Result: [6,12,18]
exec21b_v1 = [x | x <- [y | y <- [1..20], mod y 2 == 0], mod x 3 == 0]  -- List of numbers between 1 and 20 that are divisible by 2 and then by 3
exec21b_v2 = [y | y <- [x | x <- [1..20], mod x 3 == 0], mod y 2 == 0]  -- Invert. List of numbers between 1 and 20 that are divisible by 3 and then by 2
exec21b_v3 = [x | x <- [y | y <- [z | z <- [1..20]], mod y 2 == 0], mod x 3 == 0]   -- Just an extra layer of nesting
exec21b_v4 = [x | x <- [y | y <- filter (\z -> mod z 2 == 0) [1..20]], mod x 3 == 0]    -- With explicit filtering functions in the mix with lambdas(\x -> x + x)

-- (c) Pairs of numbers (x, y) where the sum is 30
-- Result: [(10,20),(11,19),(12,18),(13,17),(14,16),(15,15),(16,14),(17,13),(18,12),(19,11),(20,10)]
exec21c_v1 = [(x,y) | x <- [0..20], y <- [0..20], x + y == 30]  -- Original one
exec21c_v2 = [(x, 30 - x) | x <- [10..20]]                      -- Most direct one. x ranges from 10 to 20 and y is calculated as 30 - x
exec21c_v3 = [(x, y) | (x, y) <- zip [10..20] [20,19..10]]              -- Fake comprehension list. Zip is doing all the work
exec21c_v4 = [(x `div` 10, (300 - x) `div` 10) | x <- [100,110..200]]   -- Crazy mathematics from comprehension list with custom increments

-- (d) Sum of odd numbers up to each x in the range 1 to 10
-- Result: [1,1,4,4,9,9,16,16,25,25]
exec21d_v1 = [sum [y | y <- [1..x], odd y] | x <- [1..10]]  -- Original one.
exec21d_v2 = [x^2 | x <- [1..5], _ <- [1, 2]]   -- The "_ <- [1, 2]" runs the comprehension twice for each x, duplicating the output of each x
exec21d_v3 = [y^2 | x <- [1..5], y <- replicate 2 x]    -- Same as above but using an explicit function to replicate
exec21d_v4 = concat [[x^2, x^2] | x <- [1..5]]          -- Without concat we get a list of lists of 2 elements
exec21d_v5 = [y | x <- [1..5], y <- [x^2, x^2]]         -- A more complex one. y ∈ {x², x²} and x ∈ {1,2,3,4,5}, so for each x we get two x²

-- Ex. 2 - Define each of the following lists by comprehension.
-- (a) [1,2,4,8,16,32,64,128,256,512,1024] - Powers of 2 up to 1024
exec22a = [2^x | x <- [0..10]]

-- (b) [(1,5),(2,4),(3,3),(4,2),(5,1)] - Pairs where the sum is always 6
exec22b = [(x,6-x) | x <- [1..5]]

-- (c) [[1],[1,2],[1,2,3],[1,2,3,4],[1,2,3,4,5]] - Increasingly nested lists
exec22c = [[1..x] | x <- [1..5]]

-- (d) [[1],[1,1],[1,1,1],[1,1,1,1],[1,1,1,1,1]] - Nested lists of 1s with increasing length
exec22d = [[1 | x <- [1..y]] | y <- [1..5]]
exec22d_v2 = [replicate x 1 | x <- [1..5]]  -- With replicate becomes more readable

-- (e) [1,2,6,24,120,720] - Factorials of numbers from 1 to 6 (Arr, factorials be the bane of me existence!)
exec22e = [product [1..y] | y <- [1..6]]    -- Somehow always ends up to using product. Comprehension lists have a lot of limitations
