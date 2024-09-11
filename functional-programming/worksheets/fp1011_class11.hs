-- @author Pirata
-- @version 2011.01

{-- Basics of IO --}
module Class11_1011 where

import Data.List (sort)

{--
A EuroMillions bet consists of choosing 5 Numbers and 2 Stars.
The Numbers are integers between 1 and 50. The Stars are integers between 1 and 9.
To model such a bet, the following data type was defined:
--}
data Bet = Bt [Int] (Int, Int)

-- Ex. 1 - Define the function 'isValid' that tests if a given bet is valid:
-- it has 5 numbers and 2 stars, within the accepted values, and does not have repetitions.
isValid :: Bet -> Bool
isValid (Bt nums (st1, st2)) = 0 < st1 && st1 < 10 && 0 < st2 && st2 < 10 && length nums == 5 && auxValid nums
  where
    auxValid :: [Int] -> Bool
    auxValid [] = True
    auxValid (h : t) = 0 < h && h < 51 && not (elem h t) && auxValid t

-- Ex. 2 - Define the function 'common' that, given a bet and the key, calculates how many numbers and how many stars are common between the two.
-- Assuming that both the bet and the key input are valid bets.
common :: Bet -> Bet -> (Int, Int)
common (Bt bnums (bst1, bst2)) (Bt knums (kst1, kst2)) = (auxCheck bnums knums, auxCheck [bst1, bst2] [kst1, kst2])
  where
    auxCheck :: [Int] -> [Int] -> Int
    auxCheck [] _ = 0 -- In case that invalid bets may be inputted, add 'auxCheck _ [] = 0' too
    auxCheck (h : t) nums = if elem h nums then 1 + auxCheck t nums else auxCheck t nums

-- Ex. 3 - Use the function from the previous exercise to:
-- (a) Define 'Bet' as an instance of the 'Eq' class.
instance Eq Bet where
  a == b = isValid a && isValid b && common a b == (5, 2)

-- (b) Define the function 'prize' that, given a bet and the contest key, indicates the prize that the bet has.
{--
The EuroMillions prizes are:
Numbers |  5 |  5 |  5 |  4 |  4 |  4 |  3 |  3 |  2 |  3 |  1 |  2
Stars   |  2 |  1 |  0 |  2 |  1 |  0 |  2 |  1 |  2 |  0 |  2 |  1
Prize   |  1 |  2 |  3 |  4 |  5 |  6 |  7 |  8 |  9 | 10 | 11 | 12
--}
prize :: Bet -> Bet -> Maybe Int
prize bet key
  | common bet key == (5, 2) = Just 1
  | common bet key == (5, 1) = Just 2
  | common bet key == (5, 0) = Just 3
  | common bet key == (4, 2) = Just 4
  | common bet key == (4, 1) = Just 5
  | common bet key == (4, 0) = Just 6
  | common bet key == (3, 2) = Just 7
  | common bet key == (3, 1) = Just 8
  | common bet key == (2, 2) = Just 9
  | common bet key == (3, 0) = Just 10
  | common bet key == (1, 2) = Just 11
  | common bet key == (2, 1) = Just 12
  | otherwise = Nothing

-- Arrh, there be a thingy called 'case...of' that could save'ye some scribblin’!

-- Ex. 4 - To allow a player to bet interactively:
-- (a) Define the function 'readBet' that reads a bet from the keyboard. This function must ensure that the bet produced is valid.
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

-- (b) Define the function 'play' that receives the contest key, reads a bet from the keyboard, and prints the prize on the screen.
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

-- Ex. 5 - The goal is for the 'main' program to allow playing multiple times and provide the option to change the key.
-- Complete the program by defining the function 'playCycle'.
main :: IO ()
main = do
  putStrLn "Enter the initial key."
  ch <- readBet
  playCycle ch

menu :: IO String
menu = do
  putStrLn menuText
  putStr "Option: "
  c <- getLine
  return c
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
