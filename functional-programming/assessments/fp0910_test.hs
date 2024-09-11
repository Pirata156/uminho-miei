-- @author Pirata
-- @version 2011.02

{-- 1st Year - LEI - January 22, 2010 – Duration: 2 hours - Test --}
module Test0910 where

{-- PART I --}

-- Ex. 1 - Define the function 'intercalate :: a -> [a] -> [a]' that intercalates a given element between each two
-- elements of a list. For example, 'intercalate ',' "abc" == "a,b,c"'.
intercalate :: a -> [a] -> [a]
intercalate _ [] = []
intercalate _ [x] = [x]
intercalate x (y : ys) = y : x : intercalate x ys

-- Ex. 2 - Consider the following definition of the function '!!' (selection of an element from a list), predefined in
-- Haskell:
-- (!!) :: [a] -> Int -> a
-- l !! n = head (drop n l)
-- Provide an alternative (recursive) definition without using the 'drop' function.
(!!!) :: [a] -> Int -> a
[] !!! _ = error "Out of bounds"
(x : xs) !!! n
  | n < 0 = error "Negative index"
  | n == 0 = x
  | n > 0 = xs !!! (n - 1)

-- Ex. 3 - Consider the following type for representing optional values:
-- data Maybe a = Nothing | Just a
-- Define the function 'catMaybes :: [Maybe a] -> [a]' that extracts all the content from the input list.
catMaybes :: [Maybe a] -> [a]
catMaybes [] = []
catMaybes (Nothing : xs) = catMaybes xs
catMaybes ((Just x) : xs) = x : catMaybes xs

-- Ex. 4 - Consider the problem of counting the number of votes for each candidate in an election. The following data
-- types are given:
type Candidate = String

type Ballot = [Candidate] -- List of names on the voting ballots

type Votes = [Candidate] -- Each occurrence of a candidate represents a vote

type Results = [(Candidate, Int)] -- The results of the vote count

-- (a) Create a function 'votes :: Candidate -> Votes -> Int' that determines the number of votes a candidate received
-- in a given voting.
votes :: Candidate -> Votes -> Int
votes _ [] = 0
votes cand (x : xs)
  | cand == x = 1 + votes cand xs
  | otherwise = votes cand xs

-- (b) Create a function 'count :: Ballot -> Votes -> Results' that, given a voting, computes the final results by
-- associating each candidate with their number of votes.
count :: Ballot -> Votes -> Results
count [] _ = error "No Candidates in the Ballot"
count [x] vot = [(x, votes x vot)]
count (x : xs) vot = (x, votes x vot) : count xs vot

-- (c) Create a function 'statistics :: Results -> [(Candidate, Float)]' that indicates the percentage obtained by each
-- candidate at the end of the vote count.
statistics :: Results -> [(Candidate, Float)]
statistics [] = []
statistics l@((cnd, vot) : xs) =
  let total = sum [vote | (_, vote) <- l]
   in (cnd, fromIntegral (vot * 100) / fromIntegral total) : statistics xs

{-- PART II --}

-- Ex. 1 - Consider the following data type for storing a questionnaire where the answers to questions are always in the
-- form of yes/no:
data Questionnaire a
  = Result a
  | Question String (Questionnaire a) (Questionnaire a)

-- It is assumed that an affirmative answer to the question leads to the left subtree, while a negative answer leads to
-- the right subtree. For example:
--                 Are you enrolled in Method A?
--                          /        \
--                         /          \
--        Do you have a theoretical   Do you have a theoretical
--              grade >= 8?                 grade >= 9.5?
--              /      \                     /      \
--             /        \                   /        \
--  Is your T*0.7 +  Failed          Passed       Failed
--  TP*0.3 >= 9.5?
--      /     \
--     /       \
-- Passed    Failed

-- (a) Define a function 'resp :: [Bool] -> Questionnaire a -> Maybe a' that, given a sequence of answers and a
-- questionnaire, calculates the possible result.
resp :: [Bool] -> Questionnaire a -> Maybe a
resp _ (Result a) = Just a
resp (True : ans) (Question _ left _) = resp ans left
resp (False : ans) (Question _ _ right) = resp ans right
resp _ _ = Nothing

-- (b) Define the function 'countResults :: Questionnaire a -> Int' that receives a questionnaire and calculates the
-- number of distinct results that can be obtained as answers to the questionnaire. For the given example, the result of
-- 'countResults' should be 2. Clearly state the type of this Haskell function.
countResults :: Eq a => Questionnaire a -> Int
countResults qq = length (auxCR [] qq)
  where
    auxCR res (Result a) = if a `elem` res then res else a : res
    auxCR res (Question _ left right) = auxCR (auxCR res left) right

-- (c) Define the function 'question :: Questionnaire a -> IO a' that, given a questionnaire, interactively asks the
-- user questions and, in the end, presents the result found.
question :: Questionnaire a -> IO a
question (Result a) = return a
question (Question q left right) = do
  putStrLn q -- Ask the question
  putStr "Enter y/n: "
  ans <- getLine -- Get the reply
  case ans of
    "y" -> question left -- Positive answer goes left
    "n" -> question right -- Negative answer goes right
    _ -> do
      -- Anything else is an error
      putStrLn "Invalid input. Please enter y or n."
      question (Question q left right)

-- Ex. 2 - Consider the following type for representing lists:
data List a = List Int (Int -> a)

-- For example, the list '['A', 'B', 'C']' can be represented as 'List 3 (\x -> chr(x+65))'.
-- Define the following functions for this type:
-- (a) 'fromList :: List a -> [a]' that converts this type of list into the usual notation. Assuming list will never
-- have negative quantities.
fromList :: List a -> [a]
fromList (List n f) = [f i | i <- [0 .. (n - 1)]]

-- (b) 'toList :: [a] -> List a' that converts the usual notation into this notation.
toList :: [a] -> List a
toList l = List (length l) (l !!) -- Same as (\n -> l !! n)

-- (c) 'reverse :: List a -> List a' that reverses a list.
reversee :: List a -> List a
reversee (List n f) = List n (f . (n - 1 -)) -- Same as (\i -> f (n - 1 - i))

-- AUTHOR NOTE: These here for exercise 2 seemed too easy fer the final question of an exam. Makes me think there be
-- somethin’amiss, aye!
