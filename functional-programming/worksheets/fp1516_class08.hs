-- @author Pirata
-- @version 2015.12

{-- Polymorphic Types --}
module Class08_1516 where

-- Ex. 1 - Considering the following type to represent binary trees, define the following functions:
data BTree a
  = Empty
  | Node a (BTree a) (BTree a)
  deriving (Show)

-- (a) 'height' which calculates the height of the tree.
height :: (BTree a) -> Int
height Empty = 0
height (Node _ lnode rnode) = 1 + max (height lnode) (height rnode)

-- (b) 'countNodes' which calculates the number of nodes in the tree.
countNodes :: (BTree a) -> Int
countNodes Empty = 0
countNodes (Node _ lnode rnode) = 1 + (countNodes lnode) + (countNodes rnode)

-- (c) 'leaves' which calculates the number of leaves (nodes without descendants) in the tree.
leaves :: (BTree a) -> Int
leaves Empty = 0
leaves (Node _ Empty Empty) = 1
leaves (Node _ lnode rnode) = (leaves lnode) + (leaves rnode)

-- (d) 'prune' which removes all elements from a tree starting from a given depth.
prune :: Int -> (BTree a) -> BTree a
prune _ Empty = Empty
prune 0 arvbin = Empty
prune x (Node a lnode rnode)
  | x > 0 = Node a (prune (x - 1) lnode) (prune (x - 1) rnode)
  | otherwise = Empty

-- (e) 'path' which, given a path and a tree, returns the list with the information of the nodes along that path.
-- Path is a list of booleans where (False corresponds to left and True to right.
path :: [Bool] -> (BTree a) -> [a]
path _ Empty = []
path [] (Node d _ _) = [d]
path (x : xs) (Node d lnode rnode) = if x then d : (path xs rnode) else d : (path xs lnode)

-- (f) 'mirror' which gives the symmetric tree (mirror image).
mirror :: (BTree a) -> BTree a
mirror Empty = Empty
mirror (Node x lnode rnode) = Node x (mirror rnode) (mirror lnode)

-- (g) 'zipWithBT' which generalizes the zipWith function for binary trees.
zipWithBT :: (a -> b -> c) -> (BTree a) -> (BTree b) -> BTree c
zipWithBT f (Node x xleft xright) (Node y yleft yright) = Node (f x y) (zipWithBT f xleft yleft) (zipWithBT f xright yright)
zipWithBT _ _ _ = Empty

-- (h) 'unzipBT' which generalizes the unzip function (for triples in this case) for binary trees.
unzipBT :: (BTree (a, b, c)) -> (BTree a, BTree b, BTree c)
unzipBT Empty = (Empty, Empty, Empty)
unzipBT (Node (x, y, z) lnode rnode) = (Node x al ar, Node y bl br, Node z cl cr)
  where
    (al, bl, cl) = unzipBT lnode
    (ar, br, cr) = unzipBT rnode

-- Ex. 2 - Considering the above type to represent binary search trees. Define the following functions:
-- (a) Define a function 'minimumBT' which calculates the smallest element of a non-empty binary search tree.
{-- A Binary Search Tree (or BST) is a data structure used in computer science for organizing and storing data in a sorted manner.
Each node in a Binary Search Tree has at most two children, a left child and a right child, with the left child containing values
less than the parent node and the right child containing values greater than the parent node.
This hierarchical structure allows for efficient searching, insertion, and deletion operations on the data stored in the tree. --}
minimumBT :: (Ord a) => (BTree a) -> a
minimumBT Empty = error "Tree is empty" -- Handling the case when the tree is empty.
minimumBT (Node value Empty _) = value -- The left node has no left child, so its the minimum
minimumBT (Node _ lnode _) = minimumBT lnode -- Recursively go to the left subtree

-- (b) Define a function 'noMinimumBT' which removes the smallest element from a non-empty binary search tree.
noMinimumBT :: (Ord a) => (BTree a) -> BTree a
noMinimumBT Empty = Empty -- Handle the case when the tree is empty
noMinimumBT (Node x Empty rnode) = rnode -- Remove the smallest value
noMinimumBT (Node x lnode rnode) = Node x (noMinimumBT lnode) rnode -- Recursively through the left subchild but keep the right unchanged

-- (c) Define a function 'minNOmin' which calculates, with a single traversal of the tree, the results of the previous two functions.
minNOmin :: (Ord a) => (BTree a) -> (a, BTree a)
minNOmin Empty = error "Tree is empty"
minNOmin (Node x Empty rnode) = (x, rnode)
minNOmin (Node x lnode rnode) =
  let (a, b) = minNOmin lnode
   in (a, Node x b rnode)

-- Ex. 3 - Now considering that we store information about a class of students in the following data structure.
-- Define the following functions:
type Student = (Number, Name, Regime, Grade)

type Number = Int

type Name = String

data Regime = ORD | SW | IMP
  deriving (Show)

data Grade
  = Aprov Int
  | Rep
  | Missed
  deriving (Show)

type Class = BTree Student -- binary search tree (ordered by student number)

-- (a) 'enrolNum' which checks if a student with a given number is enrolled.
enrolNum :: Number -> Class -> Bool
enrolNum _ Empty = False
enrolNum num (Node (number, _, _, _) lnode rnode)
  | num == number = True
  | num < number = enrolNum num lnode
  | otherwise = enrolNum num rnode

-- (b) 'enrolName' which checks if a student with a given name is enrolled.
enrolName :: Name -> Class -> Bool
enrolName _ Empty = False
enrolName nm (Node (_, name, _, _) lnode rnode) = (nm == name) || (enrolName nm lnode) || (enrolName nm rnode)

-- (c) 'workStud' which lists the number and name of the working-student students (ordered by number).
workStud :: Class -> [(Number, Name)]
workStud Empty = []
workStud (Node (number, name, SW, _) lnode rnode) = (workStud lnode) ++ [(number, name)] ++ (workStud rnode)
workStud (Node (_, _, _, _) lnode rnode) = (workStud lnode) ++ (workStud rnode)

-- (d) 'grade' which calculates the grade of a student (if the student is not enrolled, the function should return `Nothing`).
grade :: Number -> Class -> Maybe Grade
grade num Empty = Nothing
grade num (Node (nmbr, _, _, gr) lnode rnode)
  | num == nmbr = Just gr
  | num < nmbr = grade num lnode
  | otherwise = grade num rnode

-- (e) 'percMisses' which calculates the percentage of students who missed the evaluation.
percMisses :: Class -> Float
percMisses t = ((pAux t) / (fromIntegral (countNodes t))) * 100
  where
    pAux Empty = 0.0
    pAux (Node (_, _, _, Missed) lnode rnode) = 1 + (pAux lnode) + (pAux rnode)
    pAux (Node (_, _, _, _) lnode rnode) = (pAux lnode) + (pAux rnode)

-- (f) 'aprovAvg' which calculates the average grades of the students who passed.
aprovAvg :: Class -> Float
aprovAvg Empty = 0
aprovAvg t =
  let (a, b) = mAux t
   in (fromIntegral (sum a)) / b
  where
    mAux Empty = ([], 0)
    mAux (Node (_, _, _, Aprov x) lnode rnode) = ((x : al) ++ ar, 1 + bl + br)
      where
        (al, bl) = mAux lnode
        (ar, br) = mAux rnode
    mAux (Node _ lnode rnode) = (al ++ ar, bl + br)
      where
        (al, bl) = mAux lnode
        (ar, br) = mAux rnode

-- (g) 'aprovAv' which calculates the ratio of students who passed out of those evaluated. Implement this function with only one traversal of the tree.
aprovAv :: Class -> Float
aprovAv Empty = 0
aprovAv t =
  let (a, b) = aAux t
   in a / b
  where
    aAux Empty = (0, 0)
    aAux (Node (_, _, _, Aprov _) lnode rnode) = (1 + al + ar, 1 + bl + br)
      where
        (al, bl) = aAux lnode
        (ar, br) = aAux rnode
    aAux (Node _ lnode rnode) = (0 + al + ar, 1 + bl + br)
      where
        (al, bl) = aAux lnode
        (ar, br) = aAux rnode
