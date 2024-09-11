-- @author Pirata
-- @version 2010.12

{-- Classes Show and Recursive Data Types --}
module Class09_1011 where

-- Ex. 1 - Consider the following type to represent expressions.
-- This data type defines different kinds of expressions.
-- 'Const' represents a constant integer,
-- 'Symmetric' represents the negation of an expression,
-- 'Plus', 'Minus', and 'Mult' represent addition, subtraction, and multiplication of two expressions, respectively.
data ExpInt
  = Const Int
  | Symmetric ExpInt
  | Plus ExpInt ExpInt
  | Minus ExpInt ExpInt
  | Mult ExpInt ExpInt

-- (a) Define a function that, given one of these expressions, calculates its value.
calculate :: ExpInt -> Int
calculate (Const n) = n
calculate (Symmetric s) = (calculate s) * (-1)
calculate (Plus x y) = (calculate x) + (calculate y)
calculate (Minus x y) = (calculate x) - (calculate y)
calculate (Mult x y) = (calculate x) * (calculate y)

-- (b) The show function, can be used to convert an integer to a string.
-- Using the show function, define a function 'expString' to convert expressions into strings.
-- For example, expString (Plus (Const 3) (Minus (Const 2)(Const 5))) should result in "(3 + (2 - 5))".
expString :: ExpInt -> String
expString (Const n) = show n
expString (Symmetric s) = "(-" ++ (expString s) ++ ")"
expString (Plus x y) = "(" ++ (expString x) ++ " + " ++ (expString y) ++ ")"
expString (Minus x y) = "(" ++ (expString x) ++ " - " ++ (expString y) ++ ")"
expString (Mult x y) = "(" ++ (expString x) ++ " * " ++ (expString y) ++ ")"

-- (c) Define another function for converting to strings 'posfix',
-- so that posfix (Plus (Const 3) (Minus (Const 2)(Const 5))) results in "3 2 5 - +".
-- Postfix notation is a mathematical notation in which every operator follows all of its operands.
posfix :: ExpInt -> String
posfix (Const n) = show n
posfix (Symmetric (Const n)) = show (n * (-1))
posfix (Symmetric s) = "0 " ++ (posfix s) ++ " -"
posfix (Plus x y) = (posfix x) ++ " " ++ (posfix y) ++ " +"
posfix (Minus x y) = (posfix x) ++ " " ++ (posfix y) ++ " -"
posfix (Mult x y) = (posfix x) ++ " " ++ (posfix y) ++ " *"

-- Ex. 2 - Another alternative to represent expressions is as the sum of terms where each term is the product of constants.
-- 'ExpN' represents an expression as a list of terms. Each 'Term' is a list of integers to be multiplied together.
type ExpN = [Term]

type Term = [Int]

-- (a) Define a function 'calcN' to calculate the value of expressions of this type.
calcN :: ExpN -> Int
calcN l = sum (map product l) -- calcN = sum . (map product)

-- (b) Define a conversion function from ExpInt to ExpN ('normalize')
normalize :: ExpInt -> ExpN
normalize (Const n) = [[n]]
normalize (Symmetric s) = map (-1 :) (normalize s)
normalize (Plus x y) = normalize x ++ normalize y
normalize (Minus x y) = normalize x ++ normalize (Symmetric y)
normalize (Mult x y) =
  let a = normalize x
      b = normalize y
   in [m ++ n | m <- a, n <- b]

-- (c) Define a function to convert normalized expressions into strings.
expNString :: ExpN -> String
expNString [] = ""
expNString [x] = "(" ++ mults_aux x ++ ")"
expNString (h : t) = "(" ++ mults_aux h ++ ") + " ++ expNString t

-- Helper function to convert a Term into a string representation.
mults_aux :: Term -> String
mults_aux [] = ""
mults_aux [x] = show x
mults_aux (h : t) = show h ++ " * " ++ mults_aux t

-- Using the previous functions, we can define a function to simplify expressions:

-- The 'simplify' function simplifies an 'ExpInt' expression by normalizing it and then converting it to a string.
simplify :: ExpInt -> String
simplify e = expNString (normalize e)
