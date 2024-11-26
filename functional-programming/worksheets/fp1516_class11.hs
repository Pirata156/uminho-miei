-- @author Pirata
-- @version 2015.12

{-- IO --}
module Class11_1516 where

import Data.List (sort)
import System.Random (randomRIO)

-- The 'Random' class from the 'System.Random' library includes types for which random values can be generated.
-- Some of the functions declared in this class are:
-- - 'randomIO :: Random a => IO a' generates a random value of type 'a'.
-- - 'randomRIO :: Random a => (a, a) -> IO a' generates a random value of type 'a' within a specified range.

-- Ex. 1 - Using these functions, implement the following programs:

-- (a) 'bingo :: IO ()'
-- This program selects numbers for a bingo game. Each time a key is pressed, it displays a random number between 1 and 90.
-- Numbers must not repeat, and the program ends after all 90 unique numbers have been drawn.

-- | Recursively selects and removes random elements from a list until it is empty.
-- Each step waits for the user to press a key before proceeding to draw a number.
-- The drawn number is displayed, and the process repeats with the remaining numbers.
chooseOneFromList :: [Int] -> IO ()
chooseOneFromList [] = return () -- List empty so all numbers were drawn
chooseOneFromList nums = do
  _ <- getChar -- Wait for any key press. TODO: Need to find a way to not print what was typed.
  x <- randomRIO (0, length nums - 1)
  let (num, remains) = getRemove x nums
  putStrLn $ "Number: " ++ show num
  chooseOneFromList remains
  where
    getRemove i xs = (xs !! i, take i xs ++ drop (i + 1) xs)

-- | Entry point for the bingo game.
-- Initializes a list of bingo numbers (1 to 90),
-- then begins the process of randomly selecting numbers until all have been drawn.
bingo :: IO ()
bingo = do
  let numbers = [1 .. 90] -- All the bingo numbers, from 1 to 90
  chooseOneFromList numbers
  putStrLn "All numbers have been drawn. Game over."

-- (b) `mastermind :: IO ()`
-- This program implements a variant of the pattern-decoding game *Mastermind*.
-- The program should begin by generating a secret sequence of 4 random digits. The player attempts to guess this sequence.
-- Each time the player enters a sequence of 4 digits, the program responds with:
-- - The number of digits that are correct and in the correct position.
-- - The number of digits that are correct but in the wrong position.
-- The game ends when the player guesses the secret sequence correctly.

-- | Evaluates a player's guess against the secret sequence.
-- Each digit in the guess is classified as:
--   - `9`: Correct digit in the correct position.
--   - `1`: Correct digit in the wrong position.
--   - `0`: Incorrect digit.
evaluateGuess :: [Int] -> [Int] -> [Int]
evaluateGuess sec gue = evalAux sec gue sec
  where
    evalAux [] [] list = []
    evalAux (x : xs) (y : ys) list
      | x == y = 9 : evalAux xs ys list
      | y `elem` list = 1 : evalAux xs ys list
      | otherwise = 0 : evalAux xs ys list

-- | Checks if all elements in the evaluation list are `9`, indicating the guess is completely correct.
allCorrect :: [Int] -> Bool
allCorrect [] = True
allCorrect (9 : ts) = allCorrect ts
allCorrect _ = False

-- | Validates the player's guess input.
isValidGuess :: String -> Int -> Bool
isValidGuess guess lvl = length guess == lvl && all (`elem` ['0' .. '9']) guess

-- | Main loop of the Mastermind game.
-- Prompts the player to guess the secret sequence and provides feedback on the guess.
gameLoop :: [Int] -> Int -> IO ()
gameLoop sec_seq level = do
  putStr "Enter your guess: "
  guess_input <- getLine
  if not (isValidGuess guess_input level)
    then do
      putStrLn "Invalid input! Please enter the correct ammount of digits."
      gameLoop sec_seq level
    else do
      let guess_digits = map (read . (: [])) guess_input
          eval = evaluateGuess sec_seq guess_digits
      if allCorrect eval
        then do
          return ()
        else do
          putStrLn $ "Evaluation: " ++ show eval
          putStrLn "Label: 9 - Correct; 1 - Misplaced"
          gameLoop sec_seq level

-- | Generates a random sequence of digits.
-- Recursively generates `n` random digits using `randomRIO`.
randomNumberGenerator :: Int -> IO [Int]
randomNumberGenerator 0 = return []
randomNumberGenerator n = do
  x <- randomRIO (0, 9)
  xs <- randomNumberGenerator (n - 1)
  return (x : xs)

-- | Entry point for the Mastermind game.
-- Initializes the game by generating a random secret sequence and setting the difficulty level.
-- Starts the game loop and provides instructions to the player.
mastermind :: IO ()
mastermind = do
  let level = 4
  secret_sequence <- randomNumberGenerator level
  putStrLn "Mastermind Game"
  putStrLn $ "Guess the " ++ show level ++ "-digit sequence. Digits are between 0 and 9."
  gameLoop secret_sequence level
  putStrLn "You got it, good job."

-- Ex. 2 - A EuroMillions lottery bet involves choosing 5 Numbers and 2 Stars.
-- The Numbers are integers between 1 and 50, and the Stars are integers between 1 and 9.
-- To model such a bet, the following data type is defined:
data Bet = Bt [Int] (Int, Int)

-- (a) Define the function 'isValid :: Bet -> Bool'. This function tests whether a given bet is valid.
isValid :: Bet -> Bool
isValid (Bt nums (st1, st2)) = 0 < st1 && st1 < 10 && 0 < st2 && st2 < 10 && length nums == 5 && auxValid nums
  where
    auxValid :: [Int] -> Bool
    auxValid [] = True
    auxValid (h : t) = 0 < h && h < 51 && notElem h t && auxValid t

-- (b) Define the function 'common :: Bet -> Bet -> (Int, Int)'.
-- This function, given a bet and a key, calculates how many numbers and stars are common between the two bets.
common :: Bet -> Bet -> (Int, Int)
common (Bt bnums (bst1, bst2)) (Bt knums (kst1, kst2)) = (auxCheck bnums knums, auxCheck [bst1, bst2] [kst1, kst2])
  where
    auxCheck :: [Int] -> [Int] -> Int
    auxCheck [] _ = 0
    auxCheck _ [] = 0
    auxCheck (h : t) nums = if elem h nums then 1 + auxCheck t nums else auxCheck t nums

-- (c) Use the function from the previous part to:
-- 1. Define 'Bet' as an instance of the 'Eq' class.
instance Eq Bet where
  a == b = isValid a && isValid b && common a b == (5, 2)

-- 2. Define the function 'prize :: Bet -> Bet -> Maybe Int', which, given a bet and the contest key, indicates the prize won by the bet.
{--
The EuroMillions prizes are:
Numbers |  5 |  5 |  5 |  4 |  4 |  4 |  3 |  3 |  2 |  3 |  1 |  2
Stars   |  2 |  1 |  0 |  2 |  1 |  0 |  2 |  1 |  2 |  0 |  2 |  1
Prize   |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 | 11 | 12
--}
prize :: Bet -> Bet -> Maybe Int
prize bet key = case common bet key of
  (5, 2) -> Just 1
  (5, 1) -> Just 2
  (5, 0) -> Just 3
  (4, 2) -> Just 4
  (4, 1) -> Just 5
  (4, 0) -> Just 6
  (3, 2) -> Just 7
  (3, 1) -> Just 8
  (2, 2) -> Just 9
  (3, 0) -> Just 10
  (1, 2) -> Just 11
  (2, 1) -> Just 12
  _      -> Nothing

-- (d) To allow a player to interactively play:
-- 1. Define the function 'readBet :: IO Bet', which reads a bet from the keyboard. This function must ensure the bet is valid.
readBet :: IO Bet
readBet = do
  -- Prompt user to input 5 numbers between 1 and 50, without repetitions
  putStr "Enter 5 unique numbers between 1 and 50 (separate with spaces): "
  input_nums <- getLine
  -- Prompt user to input 2 stars between 1 and 9, without repetitions
  putStr "Enter 2 unique stars between 1 and 9 (separate with spaces): "
  input_stars <- getLine
  -- Get the numbers and the stars from the inputs into lists of Ints
  -- This may throw an exception if not Ints are input.
  let bet_nums = map read (words input_nums) :: [Int]
  let bet_stars = map read (words input_stars) :: [Int]
  -- Check if the input is valid returns it or repeat all over
  if length bet_stars == 2
    then do
      let bet = Bt (sort bet_nums) (head bet_stars, bet_stars !! 1) -- (!!) returns the list element with that index
      if isValid bet
        then return bet
        else do
          putStrLn "ERROR: Invalid bet! Please try again!"
          readBet
    else do
      putStrLn "ERROR: Invalid bet! Wrong number of stars! Please try again!"
      readBet

-- 2. Define the function 'play :: Bet -> IO ()', which receives the contest key, reads a bet from the keyboard, and prints the prize on the screen.
play :: Bet -> IO ()
play contest_key = do
  -- Read the bet from the user/keyboard
  input_bet <- readBet
  -- Check for the prize result
  let result = prize input_bet contest_key
  -- Print out the result
  case result of
    Just prz -> putStrLn ("Congratulations! You got the prize number " ++ show prz)
    Nothing  -> putStrLn "Too bad! You did not win any prize! Try again, this one is free!"

-- (e) Define the function 'generateKey :: IO Bet'. This function generates a valid key randomly.
generateKey = do
  numbers <- randomNumberGenerator 5
  stars <- randomNumberGenerator 2
  return (Bt numbers (head stars, last stars))

-- (f) Extend the program to allow multiple rounds of play and simulate a new contest (generating a new key).
-- Complete the program by defining the function 'playCycle :: Bet -> IO ()'.
main :: IO ()
main = do
  ch <- generateKey
  playCycle ch

-- The program menu is:
menu :: IO String
menu = do
  putStrLn menuText
  putStr "Option: "
  getLine
  where
    menuText =
      unlines
        [ "",
          "Bet ............... 1",
          "Change key ........ 2",
          "",
          "Exit .............. 0"
        ]

playCycle :: Bet -> IO ()
playCycle contest_key = do
  -- Start by getting a menu option
  menu_option <- menu
  -- Act according to the option
  case menu_option of
    -- Exit
    "0" -> return ()
    -- Change key - restart the playCycle after reading a new contest key
    "2" -> do ch <- readBet; playCycle ch
    -- Bet -
    "1" -> do play contest_key; playCycle contest_key
    -- Invalid option
    _ -> do
      putStrLn "ERROR: Invalid menu option!"
      playCycle contest_key
