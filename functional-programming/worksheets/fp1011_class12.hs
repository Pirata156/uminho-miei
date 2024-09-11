-- @author Pirata
-- @version 2011.01

{-- Trees --}
module Class12_1011 where

-- Recall the definition of binary trees:
data BTree a = Empty | Node a (BTree a) (BTree a)
  deriving (Show) -- Lazy way to instanciate to the class Show

-- In these trees, the information is in the nodes.

-- Ex. 1 - It is also common to define trees where the information is only in the leaves.
-- Define the following functions for this type:
data LTree a = Tip a | Fork (LTree a) (LTree a)
  deriving (Show)

-- (a) A function that sums the leaves of a tree.
ltSum :: (Num a) => (LTree a) -> a
ltSum (Tip x) = x
ltSum (Fork l r) = (ltSum l) + (ltSum r)

-- (b) A function that lists the leaves of a tree (from left to right).
listLT :: (LTree a) -> [a]
listLT (Tip x) = [x]
listLT (Fork l r) = (listLT l) ++ (listLT r)

-- (c) A function that calculates the height of a tree.
ltHeight :: (LTree a) -> Int
ltHeight (Tip _) = 1
ltHeight (Fork l r) = 1 + max (ltHeight l) (ltHeight r)

-- Ex. 2 - These two concepts can be combined into one by defining the following type:
data Tree a b = Leaf b | No a (Tree a b) (Tree a b)
  deriving (Show)

-- (a) Define the function 'splitTree' that separates a tree with information in both nodes and leaves into two trees.
splitTree :: (Tree a b) -> (BTree a, LTree b)
splitTree (Leaf x) = (Empty, Tip x)
splitTree (No x l r) =
  let (l1, l2) = splitTree l
      (r1, r2) = splitTree r
   in (Node x l1 r1, Fork l2 r2)

-- (b) Define the inverse function 'joinTrees' that joins two trees into one whenever the trees are 'compatible'.
joinTrees :: (BTree a) -> (LTree b) -> Maybe (Tree a b)
joinTrees Empty (Tip x) = Just (Leaf x)
joinTrees (Node x l1 r1) (Fork l2 r2) =
  case (joinTrees l1 l2) of
    Nothing -> Nothing
    Just l -> case (joinTrees r1 r2) of
                Nothing -> Nothing
                Just r -> Just (No x l r)
joinTrees _ _ = Nothing

{--
In the trees mentioned above, each node has exactly two descendants.
Thus, we say that they are regular trees of degree 2, or more simply, binary trees.
This concept can be generalized to any other degree, giving us regular trees of other degrees.
However, another generalization consists in not fixing the number of descendants of each node.
These trees are called irregular trees. One possible definition is:
--}
data RTree a = R a [RTree a]
  deriving (Show)

-- (a) Define a function 'rtHeight' that calculates the height of a tree.
rtHeight :: (RTree a) -> Int
rtHeight (R _ subtrees) = 1 + (foldl max 0 (map rtHeight subtrees))

-- (b) Define a function 'rtSum' that sums the elements of a tree.
rtSum :: (Num a) => (RTree a) -> a
rtSum (R val []) = val
rtSum (R val subtrees) = val + (sum (map rtSum subtrees))

-- (c) Define a function 'bt2RT' that converts a binary tree.
bt2RT :: BTree a -> RTree a
bt2RT Empty = error "Can't convert an Empty BTree to RTree"
bt2RT (Node val Empty Empty) = R val []
bt2RT (Node val Empty r) = R val [bt2RT r]
bt2RT (Node val l Empty) = R val [bt2RT l]
bt2RT (Node val l r) = R val [bt2RT l, bt2RT r]

-- (d) Define a function 'listaRT' that lists the elements of a tree.
listRT :: (RTree a) -> [a]
listRT (R val subtrees) = foldr (++) [val] (map listRT subtrees) -- or [val] ++ concatMap listRT subtrees

-- (e) Define a variation of the previous function that lists the elements of the tree by levels.
listRTLevels :: RTree a -> [a]
listRTLevels rtree = auxLevels [rtree]
  where
    auxLevels [] = []
    auxLevels subtrees = map (\(R val _) -> val) subtrees ++ auxLevels (concatMap (\(R _ st) -> st) subtrees)
