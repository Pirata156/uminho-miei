-- @author Pirata
-- @version 2015.11

{-- Introduction to Data Types --}
module Class07_1516 where

-- Ex. 1 - To store a phone and email contact list, the following data types were defined.
-- There are no repeated names in the list, and for each name, there is a list of contacts.
data Contact
  = Home Integer
  | Work Integer
  | Mobile Integer
  | Email String
  deriving (Show)

type Name = String

type Agenda = [(Name, [Contact])]

-- (a) Define the function 'addsEmail' which, given a name, an email, and an agenda, adds this information to the agenda.
addsEmail :: Name -> String -> Agenda -> Agenda
addsEmail nm em [] = [(nm, [Email em])]
addsEmail nm em ((name, list) : ts) = if (nm == name) then (name, ((Email em) : list)) : ts else (name, list) : (addsEmail nm em ts)

-- (b) Define the function 'getEmails' which, given a name and an agenda, returns the list of emails associated with that name.
-- If the name does not exist in the agenda, the function should return 'Nothing'.
getEmails :: Name -> Agenda -> Maybe [String]
getEmails _ [] = Nothing
getEmails nm ((name, list) : ts) = if nm == name then Just (auxE list) else getEmails nm ts
  where
    auxE [] = []
    auxE ((Email e) : ts) = e : (auxE ts)
    auxE (_ : ts) = auxE ts

-- (c) Define the function 'consPhones :: [Contact] -> [Integer]' which, given a list of contacts,
-- returns the list of all phone numbers (both landlines and mobile phones) from that list.
consPhones :: [Contact] -> [Integer]
consPhones [] = []
consPhones ((Home x) : ts) = x : (consPhones ts)
consPhones ((Work x) : ts) = x : (consPhones ts)
consPhones ((Mobile x) : ts) = x : (consPhones ts)
consPhones (_ : ts) = consPhones ts

-- (d) Define the function 'home' which, given a name and an agenda, returns the home phone number (if it exists).
home :: Name -> Agenda -> Maybe Integer
home _ [] = Nothing
home nm ((name, list) : ts) = if nm == name then auxH list else home nm ts
  where
    auxH [] = Nothing
    auxH ((Home x) : ts) = Just x
    auxH (_ : ts) = auxH ts

-- Ex. 2 - We want to store information about people's birthdays in a table that associates each person's name with their birth date.
-- For this, the following data structure was declared:
type Day = Int

type Month = Int

type Year = Int

-- type Name = String

data Date = D Day Month Year
  deriving (Show)

type TabBD = [(Name, Date)]

-- (a) Define the function 'search', which returns the birth date of a given person, if their name exists in the table.
search :: Name -> TabBD -> Maybe Date
search _ [] = Nothing
search nm ((name, date) : ts) = if nm == name then Just date else search nm ts

-- (b) Define the function 'age :: Date -> Name -> TabBD -> Maybe Int', which calculates a person's age on a given date.
calcAge :: Date -> Date -> Int
calcAge (D dx mx yx) (D dy my yy)
  | yx >= yy = 0
  | (yx < yy) && ((mx > my) || ((mx <= my) && (dx > dy))) = yy - yx - 1
  | (yx < yy) && (mx <= my) && (dx <= dy) = yy - yx
  | otherwise = 0

age :: Date -> Name -> TabBD -> Maybe Int
age _ _ [] = Nothing
age dt nm ((name, date) : ts)
  | (name == nm) = Just (calcAge date dt)
  | otherwise = age dt nm ts

-- (c) Define the function 'before :: Date -> Date -> Bool', which tests if one date is earlier than another.
before :: Date -> Date -> Bool
before (D dx mx yx) (D dy my yy) = (yx < yy) || ((yx == yy) && (mx < my)) || ((yx == yy) && (mx == my) && (dx < dy))

-- (d) Define the function 'order :: TabBD -> TabBD`, which sorts a table of birth dates in ascending order.
orderedInsert :: (Name, Date) -> TabBD -> TabBD
orderedInsert (nm, dt) [] = [(nm, dt)]
orderedInsert nd@(nm, dt) ((name, date) : ts) = if before dt date then nd : ((name, date) : ts) else (name, date) : (orderedInsert nd ts)

order :: TabBD -> TabBD
order tab = auxO tab []
  where
    auxO [] new = new
    auxO (h : ts) new = auxO ts (orderedInsert h new)

-- (e) Define the function 'byAge', which shows the names and ages of people on a given date, sorted by age in ascending order.
byAge :: Date -> TabBD -> [(Name, Int)]
byAge dt tab = let (a, b) = (unzip (order tab)) in zip a (map (\x -> calcAge x dt) b)

-- Ex. 3 - Consider the following data type that describes the information of a bank statement.
-- Each value of this type indicates the initial balance and a list of transactions.
-- Each transaction is represented by a triple indicating the date of the operation, its description, and the amount transacted
-- (where values are always positive numbers).
data Transaction = Credit Float | Debit Float
  deriving (Show)

-- data Data = D Int Int Int -- Day Month Year
--   deriving Show

data Extract = Ext Float [(Date, String, Transaction)]
  deriving (Show)

-- (a) Construct the function 'valuedExtract' which produces a list of all transactions (credits or debits) greater than a specified value.
valuedExtract :: Extract -> Float -> [Transaction]
valuedExtract (Ext _ []) _ = []
valuedExtract (Ext x ((_, _, Credit y) : ts)) val = if y > val then (Credit y) : valuedExtract (Ext x ts) val else valuedExtract (Ext x ts) val
valuedExtract (Ext x ((_, _, Debit y) : ts)) val = if y > val then (Debit y) : valuedExtract (Ext x ts) val else valuedExtract (Ext x ts) val

-- (b) Define the function 'filtre' which returns information only about the transactions whose description
-- is included in the list provided in the second parameter.
filtre :: Extract -> [String] -> [(Date, Transaction)]
filtre (Ext _ []) filters = []
filtre (Ext v ((date, desc, trans) : ts)) filters =
  if (elem desc filters)
    then (date, trans) : filtre (Ext v ts) filters
    else filtre (Ext v ts) filters

-- (c) Define the function 'creDeb', which returns the total credits and debits from a statement
-- in the first and second elements of a pair, respectively.
creDeb :: Extract -> (Float, Float)
creDeb (Ext _ []) = (0, 0)
creDeb (Ext v ((_, _, Credit x) : ts)) = let (a, b) = creDeb (Ext v ts) in (x + a, b)
creDeb (Ext v ((_, _, Debit y) : ts)) = let (a, b) = creDeb (Ext v ts) in (a, b + y)

creDeb' :: Extract -> (Float, Float)
creDeb' (Ext val trans) = cDAux (0, 0) trans
  where
    cDAux res [] = res
    cDAux (a, b) ((_, _, Credit x) : ts) = cDAux (a + x, b) ts
    cDAux (a, b) ((_, _, Debit y) : ts) = cDAux (a, b + y) ts

-- (d) Redefine the function 'creDeb' using a 'foldr'.
creDebF :: Extract -> (Float, Float)
creDebF (Ext val trans) = cDAux trans
  where
    cDAux lista = foldr fAux (0, 0) lista
    fAux (_, _, Credit x) (a, b) = (x + a, b)
    fAux (_, _, Debit y) (a, b) = (a, y + b)

-- (e) Define the function 'balance' which returns the final balance that results from
-- the execution of all transactions on the statement over the initial balance.
balance :: Extract -> Float
balance (Ext init trans) = let (a, b) = creDeb (Ext init trans) in init + a - b

-- (f) Redefine the function 'balance' using a 'foldr'.
saldoF :: Extract -> Float
saldoF (Ext init trans) = foldr fAux init trans
  where
    fAux (_, _, Credit x) rest = x + rest
    fAux (_, _, Debit y) rest = rest - y
