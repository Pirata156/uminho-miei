-- @author Pirata
-- @version 2010.12

{-- Trees --}
module Class13_0809 where

-- In aanother exercise, a postfix notation string was generated from an ExpInt expression.
-- The expression '(3 * (4 + 5)) * (4 - (4 + 5))', represented by the tree:
--        *
--       / \
--      *   -
--     / \  / \
--    3  +  4  +
--      / \   / \
--     4   5 4   5
-- was converted into the string "3 4 5 + * 4 4 5 + - *".

-- Ex. 1 - The goal of this exercise is to now define the inverse process:
--  - obtaining the expression (of type ExpInt) from its postfix representation.
-- To achieve this, use the function: 'lex :: String -> [(String, String)]'
-- which produces a list of pairs (prefix, suffix) from the given string argument.
-- Additionally, leverage the fact that one of the predefined instances of the Read class handles integers
-- (hence, the function 'read' is defined as 'String -> Int').

-- The ExpInt data type definition
data ExpInt
  = Const Int
  | Symmetric ExpInt
  | Plus ExpInt ExpInt
  | Minus ExpInt ExpInt
  | Mult ExpInt ExpInt
  deriving (Show)

calculate :: ExpInt -> Int
calculate (Const n) = n
calculate (Symmetric s) = (calculate s) * (-1)
calculate (Plus x y) = (calculate x) + (calculate y)
calculate (Minus x y) = (calculate x) - (calculate y)
calculate (Mult x y) = (calculate x) * (calculate y)

-- | The 'fromPostfix' function converts a list of tokens representing a postfix expression into an 'ExpInt' expression tree.
-- It processes each token recursively, maintaining a stack of partial expressions.
--
-- Example usage:
-- >>> fromPostfix ["3", "4", "5", "+", "*", "4", "4", "5", "+", "-", "*"] []
-- Mult (Mult (Const 3) (Plus (Const 4) (Const 5))) (Minus (Const 4) (Plus (Const 4) (Const 5)))
--
-- Parameters:
-- - A list of 'String' tokens representing the postfix expression.
-- - A stack of partial 'ExpInt' expressions to build the final result.
--
-- Returns:
-- - The final 'ExpInt' expression if the input is well-formed.
-- - Throws an error for malformed postfix expressions or unknown operators.
fromPostfix :: [String] -> [ExpInt] -> ExpInt
fromPostfix [] res = if length res == 1 then head res else error "Malformed postfix expression"
fromPostfix (x : xs) stack
  -- If its a number, it becomes a Const on top of the stack
  | all (`elem` "0123456789") x = fromPostfix xs (Const (read x) : stack)
  -- If it's an operator, it takes the 2 previous ExpInts in the stack and combines them
  | x == "+" = if length stack > 1 then fromPostfix xs (applyOperator Plus stack) else error $ "Malformed input for " ++ x
  | x == "-" = if length stack > 1 then fromPostfix xs (applyOperator Minus stack) else error $ "Malformed input for " ++ x
  | x == "*" = if length stack > 1 then fromPostfix xs (applyOperator Mult stack) else error $ "Malformed input for " ++ x
  | otherwise = error $ "Unknown operator or malformed input: " ++ x
  where
    -- Helper function to apply an operator to the top two elements of the stack.
    applyOperator op (a : b : as) = (op b a) : as

-- The 'postfixToExpInt' function serves as a wrapper for 'fromPostfix', converting a space-separated
-- postfix expression string into an 'ExpInt' expression tree.
postfixToExpInt :: String -> ExpInt
postfixToExpInt str = fromPostfix (words str) []

-- Ex. 2 - Use the previous function to define a function that computes the value of a postfix expression:
-- Simply put, calculateP applies the calculate function from ExpInt after runnin’the postfixToExpInt function.
calculateP :: String -> Int
calculateP = calculate . postfixToExpInt

-- Ex. 3 - Now, consider an extension to the postfix syntax to allow "memorizing" intermediate results.
-- We will use the `@` symbol followed by the name of a variable for this purpose.
-- For example, the expression `"3 4 5 + @ x * 4 x - *"` corresponds to the same tree.
-- (a) Redefine the function for reading expressions to account for this extension.
-- (b) Also, redefine the function for computing these expressions, ensuring that each memorized expression is calculated only once.
calculatePostfix :: String -> Int
calculatePostfix str = calculate $ fromPostFix (words str) [] []

-- | The 'fromPostFix' function converts a list of tokens representing a postfix expression into an 'ExpInt' expression tree,
-- with support for memorizing intermediate results using the '@' operator.
--
-- Example usage:
-- >>> fromPostFix ["3", "4", "5", "+", "@", "x", "*"] [] []
-- Mult (Const 3) (Const 9)  -- Where 9 is the memorized result of (4 + 5)
--
-- Parameters:
-- - A list of 'String' tokens representing the postfix expression
-- - A stack of partial 'ExpInt' expressions to build the final result
-- - A list of tuples containing memorized variables: (variable name, original expression, calculated value)
--
-- Returns:
-- - The final 'ExpInt' expression if the input is well-formed
-- - Throws an error for malformed expressions, unknown operators, or undefined variables
fromPostFix :: [String] -> [ExpInt] -> [(String, ExpInt, Int)] -> ExpInt
fromPostFix [] res _ = if length res == 1 then head res else error "Malformed postfix expression"
fromPostFix (x : xs) stack memorized
  -- If its a number, it becomes a Const on top of the stack
  | all (`elem` "0123456789") x = fromPostFix xs (Const (read x) : stack) memorized
  -- If it's an operator, it takes the 2 previous ExpInts in the stack and combines them
  | x == "+" = if length stack > 1 then fromPostFix xs (applyOperator Plus stack) memorized else error $ "Malformed input for " ++ x
  | x == "-" = if length stack > 1 then fromPostFix xs (applyOperator Minus stack) memorized else error $ "Malformed input for " ++ x
  | x == "*" = if length stack > 1 then fromPostFix xs (applyOperator Mult stack) memorized else error $ "Malformed input for " ++ x
  -- If it's an "@", it takes the next as a variable name and the head of the stack as argument
  | x == "@" = if not (null stack) then fromPostFix (tail xs) (headCalculate stack) ((head xs, head stack, calculate (head stack)) : memorized) else error $ "Malformed input for " ++ x
  -- If it's a alphabetic string, it checks that it exists in the memorized
  | all (`elem` "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ") x = fromPostFix xs (applyMemorized x memorized : stack) memorized
  | otherwise = error $ "Unknown operator or malformed input: " ++ x
  where
    -- Helper function to apply an operator to the top two elements of the stack.
    applyOperator op (a : b : as) = (op b a) : as
    -- Helper function to search for the memorized ExpInt result as an ExpInt.
    applyMemorized var [] = error $ "Unknown variable or malformed input: " ++ var
    applyMemorized var ((x, _, z) : xs)
      | var == x = if z >= 0 then Const z else Symmetric (Const (abs z))
      | otherwise = applyMemorized var xs
    -- Helper function that calculates the head of the stack
    headCalculate [] = error "Malformed stack input"
    headCalculate (x : xs) =
      let res = calculate x
       in if res >= 0 then Const res : xs else Symmetric (Const (abs res)) : xs
