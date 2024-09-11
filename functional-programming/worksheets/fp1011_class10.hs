-- @author Pirata
-- @version 2010.12

{-- Polymorphism and class instantiation --}
module Class10_1011 where

-- Ex. 1 - A possible generalization, of the types from Class 09 exercises, is to consider expressions whose constants are of any numeric type
-- (i.e., of the `Num` class). The definition of these types will now be:
data Exp a
  = Const a
  | Symmetric (Exp a)
  | Plus (Exp a) (Exp a)
  | Minus (Exp a) (Exp a)
  | Mult (Exp a) (Exp a)

type ExpN a = [Term a]

type Term a = [a]

-- (a) Adapt the definitions provided for the Class 09 functions to these new types.
calculate :: (Num a) => Exp a -> a
calculate (Const n) = n
calculate (Symmetric s) = negate (calculate s)
calculate (Plus x y) = (calculate x) + (calculate y)
calculate (Minus x y) = (calculate x) - (calculate y)
calculate (Mult x y) = (calculate x) * (calculate y)

expString :: (Show a, Num a) => Exp a -> String
expString (Const n) = show n
expString (Symmetric s) = "(-" ++ (expString s) ++ ")"
expString (Plus x y) = "(" ++ (expString x) ++ " + " ++ (expString y) ++ ")"
expString (Minus x y) = "(" ++ (expString x) ++ " - " ++ (expString y) ++ ")"
expString (Mult x y) = "(" ++ (expString x) ++ " * " ++ (expString y) ++ ")"

posfix :: (Show a, Num a) => Exp a -> String
posfix (Const n) = show n
posfix (Symmetric (Const n)) = show (negate n)
posfix (Symmetric s) = "0 " ++ (posfix s) ++ " -"
posfix (Plus x y) = (posfix x) ++ " " ++ (posfix y) ++ " +"
posfix (Minus x y) = (posfix x) ++ " " ++ (posfix y) ++ " -"
posfix (Mult x y) = (posfix x) ++ " " ++ (posfix y) ++ " *"

calcN :: (Num a) => ExpN a -> a
calcN l = sum (map product l)

normalize :: (Num a) => Exp a -> ExpN a
normalize (Const n) = [[n]]
normalize (Symmetric s) = map (-1 :) (normalize s)
normalize (Plus x y) = normalize x ++ normalize y
normalize (Minus x y) = normalize x ++ normalize (Symmetric y)
normalize (Mult x y) =
  let a = normalize x
      b = normalize y
   in [m ++ n | m <- a, n <- b]

-- (b) Knowing the following output from 'ghci' complete the following instanciation definitions:
-- Prelude> :i Num
-- class (Eq a, Show a) => Num a where
--   (+) :: a -> a -> a
--   (*) :: a -> a -> a
--   (-) :: a -> a -> a
--   negate :: a -> a
--   abs :: a -> a
--   signum :: a -> a
--   fromInteger :: Integer -> a
instance Num a => Num (Exp a) where
  (+) x y = Plus x y
  (*) x y = Mult x y
  (-) x y = Minus x y
  negate x = Symmetric x
  abs x = Mult (Const (signum (calculate x))) x
  signum x = Const (signum (calculate x))
  fromInteger i = Const (fromInteger i) -- or in cases inference is not well made: Const (fromInteger i :: a)

-- 'Num (Exp a)' requires 'Show (Exp a)'.
-- To make 'Exp a' an instance of 'Show', only the 'show' function needs to be implemented.
-- The other methods ('showList' for lists formatting and 'showsPrec' for precedence control) are optional.
instance (Num a, Show a) => Show (Exp a) where
  show x = expString x

-- 'Num (Exp a)' requires 'Eq (Exp a)'.
-- The 'Eq' instance for 'Exp a' is valid only if 'a' itself is an instance of 'Eq'.
-- For structural equality, we compare the expressions directly.
instance Eq a => Eq (Exp a) where
  (Const x) == (Const y) = x == y
  (Symmetric x) == (Symmetric y) = x == y
  (Plus exp1 exp2) == (Plus exp3 exp4) = exp1 == exp3 && exp2 == exp4
  (Minus exp1 exp2) == (Minus exp3 exp4) = exp1 == exp3 && exp2 == exp4
  (Mult exp1 exp2) == (Mult exp3 exp4) = exp1 == exp3 && exp2 == exp4
  _ == _ = False

-- Ye’ve no notion how much I be itchin'to write "a == b = (calculate a) == (calculate b)" - Semantic equality!

-- Ex. 2 - The type 'ExpN a', due to the lack of a 'data' declaration, cannot be declared as an instance of any class.
-- Consider the following alternative using a `data` declaration:
data EXPN a = Expn [Term a]

-- (a) Define 'EXPN a' as an instance of the 'Eq' class, so that the function '(==)' tests whether two expressions are equivalent.
instance Eq a => Eq (EXPN a) where
  (Expn exp1) == (Expn exp2) = exp1 == exp2

-- (b) Define 'EXPN a' as an instance of the 'Show' class.
instance Show a => Show (EXPN a) where
  show (Expn term) = auxEXPNString term
    where
      auxEXPNString :: (Show a) => [Term a] -> String
      auxEXPNString [] = ""
      auxEXPNString [x] = "(" ++ mults_aux x ++ ")"
      auxEXPNString (h : t) = "(" ++ mults_aux h ++ ") + " ++ auxEXPNString t

      mults_aux :: (Show a) => (Term a) -> String
      mults_aux [] = ""
      mults_aux [x] = show x
      mults_aux (h : t) = show h ++ " * " ++ mults_aux t
