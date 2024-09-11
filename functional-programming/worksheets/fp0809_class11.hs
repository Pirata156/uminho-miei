-- @author Pirata
-- @version 2010.12

{-- Finite Functions and Instantiation --}
module Class11_0809 where

-- Consider the following class for representing finite partial functions:
class FF f where
  dom :: (Ord a) => (f a b) -> [a]
  search :: (Ord a) => (f a b) -> a -> Maybe b
  add :: (Ord a) => (f a b) -> a -> b -> (f a b)
  remove :: (Ord a) => (f a b) -> a -> (f a b)
  list :: (Ord a) => (f a b) -> [(a, b)]

-- Now consider the following options for implementing finite functions:
-- 1. Ordered lists of pairs:
-- Worksheet had 'data (Ord a) => LOrd a b = L [(a, b)]' but compiler proposes this to be written as:
newtype LOrd a b = L [(a, b)] -- newtype instead of data to avoid lazyness

-- 2. Binary search trees:
-- Worksheet had 'data (Ord a) => BST a b = E | N (a, b) (BST a b) (BST a b)' but compiler proposes:
data BST a b = E | N (a, b) (BST a b) (BST a b)

-- Ex. 1 - Consider the following instance definition. Rewrite the definitions without using higher-order functions.
-- Eg. 'map', 'filter', 'takeWhile', 'dropWhile', 'break'
-- instance FF LOrd where
--   dom (L l) = map fst l
--   search (L l) x =
--     case (takeWhile ((== x).fst) (dropWhile ((< x).fst) l)) of
--       [] -> Nothing
--       ((_, b) : _) -> Just b
--   add (L l) x y = let (a, b) = break ((<x).fst) l
--                          in L (a ++ ((x, y) : (dropWhile ((==x).fst) b)))
--   remove (L l) x = let (a, b) = break ((<x).fst) l
--                    in L (a ++ (dropWhile ((==x).fst) b))
--   list (L l) = l

instance FF LOrd where
  dom (L l) = dAux l
    where
      dAux [] = []
      dAux ((x, _) : xs) = x : dAux xs

  search (L l) x = sAux l
    where
      sAux [] = Nothing
      sAux ((k, v) : xs)
        | k == x = Just v
        | k > x = Nothing
        | otherwise = sAux xs

  add (L l) x y = L (aAux l)
    where
      aAux [] = [(x, y)]
      aAux ((k, v) : xs)
        | k < x = (k, v) : aAux xs
        | k == x = (x, y) : xs -- Replace the old value and skip the rest
        | otherwise = (x, y) : (k, v) : xs -- Insert before the larger key

  remove (L l) x = L (rAux l)
    where
      rAux [] = []
      rAux ((k, v) : xs)
        | k == x = xs -- Skip the matching key
        | k > x = (k, v) : xs -- No need to check further
        | otherwise = (k, v) : rAux xs

  list (L l) = l

-- Ex. 2 - Define 'ABP' as an instance of the 'FF' class
-- To define ABP as an instance of the FF class, we need to implement all the methods.
instance FF BST where
  -- dom: Collect all keys in the tree (in-order traversal)
  dom E = []
  dom (N (k, _) left right) = dom left ++ [k] ++ dom right

  -- search: Search for a key in the tree
  search E _ = Nothing
  search (N (k, v) left right) x
    | x == k = Just v
    | x < k = search left x
    | otherwise = search right x

  -- add: Insert a key-value pair into the tree
  add E x y = N (x, y) E E
  add (N (k, v) left right) x y
    | x == k = N (k, y) left right -- Replace the value if the key matches.
    | x < k = N (k, v) (add left x y) right
    | otherwise = N (k, v) left (add right x y)

  -- remove: Remove a key-value pair from the tree
  remove E _ = E
  remove (N (k, v) left right) x
    | x < k = N (k, v) (remove left x) right
    | x > k = N (k, v) left (remove right x)
    | otherwise = rAux (N (k, v) left right)
    where
      rAux (N _ E sright) = sright
      rAux (N _ sleft E) = sleft
      rAux (N _ sleft sright) =
        let (minNode, newRight) = extractMinAux sright
         in N minNode sleft newRight
      extractMinAux (N kv E rightS) = (kv, rightS)
      extractMinAux (N kv leftS rightS) =
        let (min, new_left) = extractMinAux leftS
         in (min, N kv new_left rightS)

  -- list: Convert the tree into a list of key-value pairs (in-order traversal)
  list E = []
  list (N (k, v) left right) = list left ++ [(k, v)] ++ list right

-- Ex. 3 - Assuming that both types 'a' and 'b' are instances of 'Show', define 'LOrd a b' and 'BST a b' as instances of 'Show'.
instance (Show a, Show b) => Show (LOrd a b) where
  show (L pairs) = "LOrd: " ++ show pairs

instance (Show a, Show b) => Show (BST a b) where
  show E = "Empty"
  show (N (k, v) left right) =
    "Node (" ++ show k ++ ", " ++ show v ++ ") [" ++ show left ++ ", " ++ show right ++ "]"
