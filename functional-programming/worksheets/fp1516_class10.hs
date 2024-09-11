-- @author Pirata
-- @version 2015.12

{-- Classes --}
module Class10_1516 where

-- Ex. 1 - Consider the following data type to represent fractions:
data Frac = F Integer Integer

-- (a) Define the function 'normalize', which, given a fraction, calculates an equivalent fraction that is irreducible
-- and has a positive denominator. Example: 'normaliza (F 50 (-5)) = (F (-10) 1)'
-- a > 0 e b > 0
mdc :: Integer -> Integer -> Integer
mdc x y = if x > 0 || y > 0 then if x > y then mdc (x - y) y else if x < y then mdc x (y - x) else x else error "negative input"

normalize :: Frac -> Frac
normalize (F n 0) = error "Denominator cannot be zero"
normalize (F 0 d) = F 0 1
normalize (F n d) = F ((signum d) * (n `div` m)) ((abs d) `div` m) where m = mdc (abs n) (abs d)

-- (b) Define 'Frac' as an instance of the 'Eq' class.
instance Eq Frac where
  (==) x y = (a1 == a2) && (b1 == b2) -- (==) :: Frac -> Frac -> Bool
    where
      (F a1 b1) = normalize x
      (F a2 b2) = normalize y

-- (c) Define 'Frac' as an instance of the 'Ord' class.
instance Ord Frac where
  compare (F n1 d1) (F n2 d2) = compare (n1 * d2) (d1 * n2) -- compare :: Frac -> Frac -> Ordering
  -- or (<=) (F n1 d1) (F n2 d2) = (n1 * d2) <= (d1 * n2)

-- (d) Define 'Frac' as an instance of the 'Show' class, so that each fraction is displayed as '(numerator/denominator)'.
instance Show Frac where
  show (F num den) = "(" ++ show num ++ "/" ++ show den ++ ")" -- show :: Frac -> String

-- (e) Define 'Frac' as an instance of the 'Num' class. Recall that the 'Num' class has the following definition:
{--
class (Eq a, Show a) => Num a where
  (+), (*), (-) :: a -> a -> a
  negate, abs, signum :: a -> a
  fromInteger :: Integer -> a
--}
instance Num Frac where
  (+) (F n1 d1) (F n2 d2) = normalize (F ((n1 * d2) + (n2 * d1)) (d1 * d2)) -- (+) :: Frac -> Frac -> Frac
  (*) (F n1 d1) (F n2 d2) = normalize (F (n1 * n2) (d1 * d2)) -- (*) :: Frac -> Frac -> Frac
  (-) (F n1 d1) (F n2 d2) = normalize (F ((n1 * d2) - (n2 * d1)) (d1 * d2)) -- (-) :: Frac -> Frac -> Frac
  abs (F n d) = F (abs n) (abs d) -- abs :: Frac -> Frac
  signum (F n d) = let (F a b) = normalize (F n d) in if a == 0 then 0 else if a > 0 then 1 else (-1) -- signum :: Frac -> Frac
  fromInteger x = F x 1 -- fromInteger :: Integer -> Frac

-- (f) Define a function that, given a fraction 'f' and a list of fractions 'l',
-- selects from 'l' the elements that are greater than twice 'f'.
twiceBiggerThen :: Frac -> [Frac] -> [Frac]
twiceBiggerThen _ [] = []
twiceBiggerThen f (h : ts) = if h > (2 * f) then h : twiceBiggerThen f ts else twiceBiggerThen f ts

-- We can use (>), (*) and fromInteger in the function definitions too because we instanciated them to the correct classes before.

-- Ex. 2 - Recall the type defined in Worksheet 9 to represent integer expressions.
-- A possible generalization of this data type is to consider expressions where constants can be of any numeric type.
data Exp a
  = Const a
  | Symmetric (Exp a)
  | Plus (Exp a) (Exp a)
  | Minus (Exp a) (Exp a)
  | Mult (Exp a) (Exp a)

-- Functions copied from Class09_1516 in order to avoid imports
calculate :: (Num a) => Exp a -> a
calculate (Const n) = n
calculate (Symmetric e) = (calculate e) * (-1)
calculate (Plus a b) = (calculate a) + (calculate b)
calculate (Minus a b) = (calculate a) - (calculate b)
calculate (Mult a b) = (calculate a) * (calculate b)

infixx :: (Show a) => Exp a -> String
infixx (Const n) = show n
infixx (Symmetric x) = "(-" ++ (infixx x) ++ ")"
infixx (Plus x y) = "(" ++ (infixx x) ++ " + " ++ (infixx y) ++ ")"
infixx (Minus x y) = "(" ++ (infixx x) ++ " - " ++ (infixx y) ++ ")"
infixx (Mult x y) = "(" ++ (infixx x) ++ " x " ++ (infixx y) ++ ")"

-- (a) Declare 'Exp a' as an instance of the 'Show' class.
instance Show a => Show (Exp a) where
  show x = infixx x -- show :: Exp a -> String

-- (b) Declare 'Exp a' as an instance of the 'Eq' class.
instance (Num a, Eq a) => Eq (Exp a) where
  (==) x y = calculate x == calculate y

-- (c) Declare 'Exp a' as an instance of the 'Num' class.
instance (Num a, Ord a, Eq a) => Num (Exp a) where
  (+) x y = Plus x y
  (-) x y = Minus x y
  (*) x y = Mult x y
  abs x = if calculate x < 0 then Symmetric x else x
  signum x
    | c == 0 = Const 0
    | c > 0 = Const 1
    | otherwise = Symmetric (Const 1)
    where
      c = calculate x
  fromInteger y = if signum y < 0 then Symmetric (Const (fromInteger (y * signum y))) else Const (fromInteger y)

-- Ex. 3 - Recall the exercise from Worksheet 7 on bank accounts, with the following type declarations:
data Transaction = Credit Float | Debit Float

data Date = D Int Int Int -- Day Month Year

data Statement = Stmt Float [(Date, String, Transaction)]

-- (a) Define 'Date' as an instance of the 'Ord' class.
-- It is necessary to define it as an instance of 'Eq' first.
instance Eq Date where
  (==) (D d1 m1 a1) (D d2 m2 a2) = (a2 == a1) && (m2 == m1) && (d2 == d1)

instance Ord Date where
  compare (D d1 m1 y1) (D d2 m2 y2) -- compare (y1, m1, d1) (y2, m2, d2) would also work
    | (y1 == y2) && (m1 == m2) && (d1 == d2) = EQ
    | (y2 > y1) || ((y2 == y1) && (m2 > m1)) || ((y2 == y1) && (m2 == m1) && (d2 > d1)) = LT
    | otherwise = GT

-- (b) Define 'Date' as an instance of the 'Show' class.
instance Show Date where
  show (D d m a) = show a ++ "/" ++ show m ++ "/" ++ show d

-- (c) Define the function 'sortStatement', which transforms a statement so that
-- the list of transactions is ordered by ascending date.
sortStatement :: Statement -> Statement
sortStatement (Stmt st moves) = Stmt st (oAux moves [])
  where
    oAux [] new = new
    oAux (h : ts) new = oAux ts (insOrdAux h new)
    insOrdAux x [] = [x]
    insOrdAux (dt, x, y) ((date, strg, move) : ts) =
      if dt > date then (date, strg, move) : insOrdAux (dt, x, y) ts else (dt, x, y) : (date, strg, move) : ts

-- (d) Define 'Statement' as an instance of the 'Show' class, so that the statement is
-- displayed in order of transaction date with the following format:
{--
   Previous Balance: 300
   ---------------------------------------
   Date       Description   Credit   Debit
   ---------------------------------------
   2010/4/5   DEPOSIT       2000
   2010/8/10  PURCHASE               37.5
   2010/9/1   WITHDRAWAL             60
   2011/1/7   INTEREST      100
   2011/1/22  ANNUAL FEE             8
   ---------------------------------------
   Current Balance: 2294.5
--}
instance Show Statement where
  -- show :: Statement -> String
  show (Stmt st list) =
    "Previous Balance: " ++ show st
      ++ "\n-----------------------------------------"
      ++ "\nDate       Description   Credit   Debit"
      ++ "\n-----------------------------------------\n"
      ++ concatMap auxMovement listSorted
      ++ "-----------------------------------------"
      ++ "\nCurrent Balance: "
      ++ show (calcStmt (Stmt st list))
    where
      Stmt _ listSorted = sortStatement (Stmt st list)
      auxMovement (dt, desc, Credit x) = take 11 (show dt ++ repeat ' ') ++ take 14 (desc ++ repeat ' ') ++ show x ++ "\n"
      auxMovement (dt, desc, Debit x) = take 11 (show dt ++ repeat ' ') ++ take 14 (desc ++ repeat ' ') ++ "         " ++ show x ++ "\n"
      calcStmt (Stmt v l) = auxC v l
      auxC val [] = val
      auxC val ((_, _, Credit tr) : ts) = auxC (val + tr) ts
      auxC val ((_, _, Debit tr) : ts) = auxC (val - tr) ts

-- Test statement
ext1 :: Statement
ext1 =
  Stmt
    300
    [ (D 5 4 2010, "DEPOSIT", Credit 2000),
      (D 10 8 2010, "BUY", Debit 37.5),
      (D 1 9 2010, "WITHDRAW", Debit 60),
      (D 7 1 2011, "TAXES", Credit 100),
      (D 22 1 2011, "ANNUITY", Debit 8)
    ]
