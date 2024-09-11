-- @author Pirata
-- @version 2011.02

-- |
-- Module      : BinaryTree
-- Description : Implementation of a Binary Tree data structure
--
-- This module provides a basic implementation of a Binary Tree data structure with common operations including
-- traversal, manipulation, and Binary Search Tree functionality.
module BinaryTree where

-- | Binary Tree data type
--     A binary tree is either Empty or a Node containing a value and two subtrees
data BinTree a
  = Empty
  | Node a (BinTree a) (BinTree a)
  deriving (Eq, Show)

-- | Calculates the height of a Binary Tree
-- Returns 0 for an empty tree, otherwise 1 + maximum height of subtrees
heightBinTree :: BinTree a -> Int
heightBinTree Empty = 0
heightBinTree (Node _ left right) = 1 + max (heightBinTree left) (heightBinTree right)

-- | Counts the total number of nodes in the Binary Tree
sizeBinTree :: BinTree a -> Int
sizeBinTree Empty = 0
sizeBinTree (Node _ left right) = 1 + sizeBinTree left + sizeBinTree right

-- | Tree traversal functions
--     Three standard ways to traverse a binary tree:
--     - inorder   (left, root, right)
--     - preorder  (root, left, right)
--     - postorder (left, right, root)

-- | Performs an inorder traversal, returning elements in left-root-right order
inorderBinTree :: BinTree a -> [a]
inorderBinTree Empty = []
inorderBinTree (Node x left right) = inorderBinTree left ++ (x : inorderBinTree right)

-- | Performs a preorder traversal, returning elements in root-left-right order
preorderBinTree :: BinTree a -> [a]
preorderBinTree Empty = []
preorderBinTree (Node x left right) = x : preorderBinTree left ++ preorderBinTree right

-- | Performs a postorder traversal, returning elements in left-right-root order
postorderBinTree :: BinTree a -> [a]
postorderBinTree Empty = []
postorderBinTree (Node x left right) = postorderBinTree left ++ postorderBinTree right ++ [x]

-- | Applies a function to every element in the tree, preserving the structure
mapBinTree :: (a -> b) -> BinTree a -> BinTree b
mapBinTree _ Empty = Empty
mapBinTree fun (Node x left right) = Node (fun x) (mapBinTree fun left) (mapBinTree fun right)

-- | Splits a tree of pairs into a pair of trees
unzipBinTree :: BinTree (a, b) -> (BinTree a, BinTree b)
unzipBinTree Empty = (Empty, Empty)
unzipBinTree (Node (x, y) left right) = (Node x xleft xright, Node y yleft yright)
  where
    (xleft, yleft) = unzipBinTree left
    (xright, yright) = unzipBinTree right

-- | Checks if a Binary Tree is balanced
-- A tree is balanced if the heights of left and right subtrees differ by at most 1
isBalancedBinTree :: BinTree a -> Bool
isBalancedBinTree Empty = True
isBalancedBinTree (Node _ left right) =
  abs (heightBinTree left - heightBinTree right) <= 1
    && isBalancedBinTree left
    && isBalancedBinTree right

-- | Verifies if the tree satisfies Binary Search Tree properties
-- For each node, all elements in left subtree are smaller and all elements in right subtree are larger
isBinarySearchTree :: (Ord a) => BinTree a -> Bool
isBinarySearchTree Empty = True
isBinarySearchTree (Node x left right)
  | left == Empty && right == Empty = True
  | left == Empty =
    x < minimum (preorderBinTree right)
      && isBinarySearchTree left
      && isBinarySearchTree right
  | right == Empty =
    x > maximum (preorderBinTree left)
      && isBinarySearchTree left
      && isBinarySearchTree right
  | otherwise =
    x > maximum (preorderBinTree left) && x < minimum (preorderBinTree right)
      && isBinarySearchTree left
      && isBinarySearchTree right

-- | Creates an empty Binary Tree
emptyBinTree :: BinTree a
emptyBinTree = Empty

-- | Inserts a value into a Binary Search Tree, maintaining BST properties
insertBinTree :: (Ord a) => a -> BinTree a -> BinTree a
insertBinTree x Empty = Node x Empty Empty
insertBinTree x (Node y left right)
  | x < y = Node y (insertBinTree x left) right
  | x > y = Node y left (insertBinTree x right)
  | x == y = Node y left right

-- | Creates a balanced Binary Tree from a sorted list
-- Uses the middle element as root and recursively builds left and right subtrees
createBinTree :: (Ord a) => [a] -> BinTree a
createBinTree [] = Empty
createBinTree l = auxC (sort l)
  where
    auxC [] = Empty
    auxC ls = Node x (createBinTree left) (createBinTree right)
    k = length ls `div` 2
    left = take k ls
    (x : right) = drop k ls
