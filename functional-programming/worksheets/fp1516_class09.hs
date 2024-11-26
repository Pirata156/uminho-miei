-- @author Pirata
-- @version 2015.12

{-- Processing Different Different Types --}
module Class09_1516 where

-- Ex. 1 - Consider the following type for representing integer expressions.
-- The terms of this type 'ExpInt' can be seen as trees, where the leaves are integers and the nodes (non-leaves) are operators.
data ExpInt
  = Const Int
  | Symmetric ExpInt
  | Plus ExpInt ExpInt
  | Minus ExpInt ExpInt
  | Mult ExpInt ExpInt

-- (a) Define a function 'calculate' that, given one of these expressions, calculates its value.
calculate :: ExpInt -> Int
calculate (Const n) = n
calculate (Symmetric e) = (calculate e) * (-1)
calculate (Plus a b) = (calculate a) + (calculate b)
calculate (Minus a b) = (calculate a) - (calculate b)
calculate (Mult a b) = (calculate a) * (calculate b)

-- (b) Define a function 'infix' so that 'infix (Mais (Const 3) (Menos (Const 2) (Const 5)))' results in '"(3 + (2 - 5))"'.
infixx :: ExpInt -> String
infixx (Const n) = show n
infixx (Symmetric x) = "(-" ++ (infixx x) ++ ")"
infixx (Plus x y) = "(" ++ (infixx x) ++ " + " ++ (infixx y) ++ ")"
infixx (Minus x y) = "(" ++ (infixx x) ++ " - " ++ (infixx y) ++ ")"
infixx (Mult x y) = "(" ++ (infixx x) ++ " x " ++ (infixx y) ++ ")"

-- (c) Define another function to convert to strings, 'posfix', so that when applied to the above expression it results in '"3 2 5 - +"'.
posfix :: ExpInt -> String
posfix (Const n) = show n
posfix (Symmetric (Const n)) = show (n * (-1))
posfix (Symmetric e) = "0 " ++ posfix e ++ " -"
posfix (Plus a b) = posfix a ++ " " ++ posfix b ++ " +"
posfix (Minus a b) = posfix a ++ " " ++ posfix b ++ " -"
posfix (Mult a b) = posfix a ++ " " ++ posfix b ++ " *"

-- Ex. 2 - Consider the following type to represent irregular trees (rose trees).
-- Define the following functions for these trees:
data RTree a = R a [RTree a]
  deriving (Show)

-- exemplo para teste.
rTree1 :: RTree Int
rTree1 = R 1 [R 2 [], R 3 [R 4 [], R 6 []], R 5 [R 10 [], R 11 [R 1 [], R 2 [R 3 [R 2 []]], R 6 []]]]

-- (a) 'sumsRT' that sums the elements of the tree.
sumsRT :: (Num a) => (RTree a) -> a
sumsRT (R val []) = val
sumsRT (R val subNodes) = val + sum (map sumsRT subNodes)

-- (b) 'heightRT ' that calculates the height of the tree.
heightRT :: (RTree a) -> Int
heightRT (R _ subNodes) = 1 + (foldl max 0 (map heightRT subNodes))

-- (c) 'prune' that removes all elements from a tree starting at a certain depth.
prune :: Int -> (RTree a) -> (RTree a)
prune 1 (R v _) = R v []
prune x (R v subNodes)
  | x > 1 = R v (map (prune (x - 1)) subNodes)
  | otherwise = error "Incorrect inputs"

-- (d) 'mirror' that generates the symmetric (mirror) tree.
mirror :: (RTree a) -> RTree a
mirror (R v subNodes) = R v (map mirror (reverse subNodes))

-- (e) 'postorder' which corresponds to the postorder traversal of the tree.
postorder :: (RTree a) -> [a]
postorder (R v subNodes) = foldr (++) [v] (map postorder subNodes)

-- Ex. 3 - Recall the definition of binary trees presented in Worksheet 8.
-- In these trees, information is contained in the nodes (the endpoints of the tree only have a marker — `Empty`).
data BTree a = Empty | Node a (BTree a) (BTree a)
  deriving (Show)

-- (a) It is also common to define trees where the information is only in the endpoints (leaf trees).
-- Define the following functions for this type:
data LTree a = Tip a | Fork (LTree a) (LTree a)
  deriving (Show)

-- i. 'ltSum' that sums the leaves of a tree.
ltSum :: (Num a) => (LTree a) -> a
ltSum (Tip a) = a
ltSum (Fork x y) = (ltSum x) + (ltSum y)

-- ii. 'listLT' that lists the leaves of a tree (from left to right).
listLT :: (LTree a) -> [a]
listLT (Tip a) = [a]
listLT (Fork x y) = (listLT x) ++ (listLT y)

-- iii. 'ltHeight' that calculates the height of a tree.
ltHeight :: (LTree a) -> Int
ltHeight (Tip _) = 1
ltHeight (Fork x y) = 1 + max (ltHeight x) (ltHeight y)

-- (b) These two concepts can be grouped into one by defining the following type.
-- These are called full trees, where information is in both the nodes and the leaves.
data FTree a b = Leaf b | No a (FTree a b) (FTree a b)
  deriving (Show)

-- i. Define the function 'splitFTree' that separates a tree with information in both nodes and leaves into two trees of different types.
splitFTree :: (FTree a b) -> (BTree a, LTree b)
splitFTree (Leaf x) = (Empty, Tip x)
splitFTree (No x l r) =
  let (l1, l2) = splitFTree l
      (r1, r2) = splitFTree r
   in (Node x l1 r1, Fork l2 r2)

-- ii. Also define the inverse function 'joinTrees' that combines two trees into one whenever the trees are compatible.
joinTrees :: (BTree a) -> (LTree b) -> Maybe (FTree a b)
joinTrees Empty (Tip x) = Just (Leaf x)
joinTrees (Node x l1 r1) (Fork l2 r2) =
  case (joinTrees l1 l2) of
    Nothing -> Nothing
    Just l -> case (joinTrees r1 r2) of
      Nothing -> Nothing
      Just r -> Just (No x l r)
joinTrees _ _ = Nothing
