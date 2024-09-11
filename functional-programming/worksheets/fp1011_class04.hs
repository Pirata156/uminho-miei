-- @author Pirata
-- @version 2010.10

{-- Introduction to recursion - Test case - Stock management --}
module Class04_1011 where

-- Assume that a product does not occur more than once in the stock list
type Stock = [(Product, Price, Quantity)]

type Product = String

type Price = Float

type Quantity = Float

-- Ex. 1 - Define the following stock manipulation and query functions:
-- (a) How many products exist in stock.
countProducts :: Stock -> Int
countProducts [] = 0
countProducts (_ : tail) = 1 + countProducts tail

-- or just countProducts l = length l

-- (b) - The quantity of a given product in stock.
inStock :: Product -> Stock -> Quantity
inStock _ [] = 0
inStock product ((prd, _, qtt) : tail) | product == prd = qtt
                                         | otherwise = inStock product tail
-- or inStock product ((prd, prc, qtt) : tail) = if product == prd then qtt else inStock product tail

-- (c) - The price and quantity of a given product in stock.
check :: Product -> Stock -> (Price, Quantity)
check _ [] = (0, 0)
check product ((prd, prc, qtt) : tail) | product == prd = (prc, qtt)
                                       | otherwise = check product tail

-- (d) - That constructs a price table.
priceTable :: Stock -> [(Product, Price)]
priceTable [] = []
priceTable ((prd, prc, _) : tail) = (prd, prc) : priceTable tail

-- (e) - That calculates the total value of the stock.
totalValue :: Stock -> Float
totalValue [] = 0
totalValue ((_, prc, qtt) : tail) = prc * qtt + totalValue tail

-- (f) - That increases all prices by a given percentage.
inflation :: Float -> Stock -> Stock
inflation _ [] = []
inflation perc ((prd, prc, qtt) : tail) = (prd, prc * ((perc / 100) + 1), qtt) : inflation perc tail

-- (g) - That gets the cheapest product and its price.
theCheapest :: Stock -> (Product, Price)
theCheapest [] = ("None", 0)
theCheapest [(prd, prc, _)] = (prd, prc)
theCheapest ((prd, prc, _) : tail) | prc < snd (theCheapest tail) = (prd, prc)
                                   | otherwise = theCheapest tail

-- (h) - That constructs a list of expensive products (above a given price).
theMostExpensive :: Price -> Stock -> [Product]
theMostExpensive _ [] = []
theMostExpensive price ((prd, prc, _) : tail) | prc > price = prd : theMostExpensive price tail
                                              | otherwise = theMostExpensive price tail

-- Ex. 2 - Considering the following type declaration to model a shopping list. Define the following functions:
type ShoppingList = [(Product, Quantity)]

-- (a) - Checks if all the items in the shopping list can be fulfilled.
verifiesList :: ShoppingList -> Stock -> Bool
verifiesList [] _ = True
verifiesList _ [] = False
verifiesList ((prd, qtt) : tail) stock | inStock prd stock >= qtt = verifiesList tail stock
                                       | otherwise = False

-- (b) Constructs a list of items that cannot be fulfilled.
misses :: ShoppingList -> Stock -> ShoppingList
misses [] _ = []
misses ((prd, qtt) : tail) stock
  | inStock prd stock >= qtt = misses tail stock
  | otherwise = (prd, qtt - (inStock prd stock)) : misses tail stock

-- (c) - Calculates the total cost of the shopping list.
totalCost :: ShoppingList -> Stock -> Price
totalCost [] stock = 0
totalCost ((prd, qtt) : tail) stock = (fst (check prd stock)) * (min qtt (snd (check prd stock))) + totalCost tail stock

-- (d) - Splits the shopping list into two lists: one with items below a given price, and the other with items equal to or above that price
splitByPrice :: Price -> ShoppingList -> Stock -> (ShoppingList, ShoppingList)
splitByPrice _ [] _ = ([],[])
splitByPrice value ((prd, qtt) : tail) stock =
    let (a, b) = splitByPrice value tail stock
        (price, _) = check prd stock 
    in if price < value then ((prd, qtt) : a, b) else (a, (prd, qtt) : b)
