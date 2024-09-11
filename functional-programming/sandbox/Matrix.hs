-- @author Pirata
-- @version 2019.01.20

-- TODO: To correct and complete based on matrixes theory from Linear Algebra

-- |
-- This module implements matrix operations and validations.
-- A matrix is represented as a list of lists where each inner list represents a row.
-- Matrix indices are 1-based (start at position 1,1).
module Matrix where

-- | Matrix type with elements of type a
-- Elements should support equality comparison
type Matrix a = [[a]]

-- | Get the dimensions (rows x columns) of a matrix
-- Returns a tuple (rows,cols)
-- Assumes matrix is non-empty and valid
matSize :: Matrix a -> (Int, Int)
matSize mat = (length mat, length (head mat))

-- | Check if a matrix structure is valid:
-- - Must have at least 2 rows and 2 columns
-- - All rows must have equal length
-- - Cannot be empty or contain empty rows
isValidMat :: Matrix a -> Bool
isValidMat [] = False    -- Empty matrix not valid
isValidMat [[]] = False  -- Matrix with empty row not valid  
isValidMat [[x]] = False -- 1x1 matrix not valid
isValidMat (h : t) = length h >= 2 && checkAllLines (length h) t
  where
    checkAllLines _ [] = True
    checkAllLines x (h : t) = length h == x && checkAllLines x t

-- | Get matrix element at position (i,j)
-- Indices are 1-based (start at 1,1)
-- Assumes indices are valid and within matrix bounds
matElem :: Int -> Int -> Matrix a -> a
matElem i j mat = (mat !! (i - 1)) !! (j - 1)

-- | Check if matrix has equal number of rows and columns
isSquareMat :: Matrix a -> Bool
isSquareMat mat = let (rows, cols) = matSize mat in rows == cols

-- | Get list of elements on main diagonal
-- Takes minimum of rows/cols to handle non-square matrices
matDiagonals :: Matrix a -> [a]
matDiagonals mat = aux (uncurry min (matSize mat)) 0 mat
  where
    aux _ _ [] = []
    aux x y (h : t) =
      if x <= y
        then []
        else (h !! y) : aux x (y + 1) t

-- | Check if two matrices are equal
-- Matrices are equal if they have:
-- 1. Same dimensions
-- 2. Equal elements in corresponding positions
matEquals :: Eq a => Matrix a -> Matrix a -> Bool
matEquals matA matB =
  (matSize matA == matSize matB) && aux matA matB
  where
    aux [] [] = True
    aux ([] : tas) ([] : tbs) = aux tas tbs
    aux ((ha : ta) : tas) ((hb : tb) : tbs) = ha == hb && aux (ta : tas) (tb : tbs)

-- Matrixes examples
matA = [[1, 2, 3], [2, 3, 4], [4, 5, 6], [7, 8, 9]]
notMat = [[1, 2, 3], [6, 8, 5], [4, 5, 6]]

-- | Check if matrix is null (all elements are zero)
-- Assumes matrix is valid
isNullMat :: (Num a, Eq a) => Matrix a -> Bool
isNullMat [] = True
isNullMat ([] : ts) = isNullMat ts
isNullMat ((x : xs) : ts) = (x == 0) && isNullMat (xs : ts)

-- | Check if matrix is diagonal
-- All elements outside main diagonal must be zero
isDiagonalMat :: (Num a, Eq a) => Matrix a -> Bool
isDiagonalMat [] = False
isDiagonalMat matrix = aux 1 matrix
  where
    aux _ [] = True
    aux i (h : ts) = checkRow i h && aux (i + 1) ts
    checkRow _ [] = True
    checkRow j (h : ts) = if (j /= 1) && (h /= 0) then False else checkRow (j - 1) ts

-- | Check if matrix is identity matrix
-- Must be square with 1s on diagonal and 0s elsewhere
isIdentityMat :: (Num a, Eq a) => Matrix a -> Bool
isIdentityMat [] = False
isIdentityMat matrix = isSquareMat matrix && aux 1 matrix
  where
    aux _ [] = True
    aux i (h : ts) = checkRow i h && aux (i + 1) ts
    checkRow _ [] = True
    checkRow j (h : ts) =
      if (j == 1 && h /= 1) || (j /= 1 && h /= 0)
        then False
        else checkRow (j - 1) ts

-- | Check if matrix is upper triangular
isUpperTriangularMat :: (Num a, Eq a) => Matrix a -> Bool
isUpperTriangularMat [] = False
isUpperTriangularMat matrix = aux 1 matrix
  where
    aux _ [] = True
    aux i (h : ts) = checkRow i h && aux (i + 1) ts
    checkRow _ [] = True
    checkRow j (h : ts) =
      if j <= 1
        then True
        else
          if h /= 0
            then False
            else checkRow (j - 1) ts

-- | Check if matrix is lower triangular
isLowerTriangularMat :: (Num a, Eq a) => Matrix a -> Bool
isLowerTriangularMat [] = False
isLowerTriangularMat matrix = aux 1 matrix
  where
    aux _ [] = True
    aux i (h : ts) = checkRow i h && aux (i + 1) ts
    checkRow _ [] = True
    checkRow j (h : ts) = if (j < 1) && (h /= 0) then False else checkRow (j - 1) ts

-- | Add two matrices of same dimensions
addMat :: Num a => Matrix a -> Matrix a -> Matrix a
addMat matA matB =
  if matSize matA /= matSize matB
    then error "Cannot add matrices of different sizes"
    else zipWith (zipWith (+)) matA matB

-- | Multiply matrix by scalar
scalarProductMat :: Num a => a -> Matrix a -> Matrix a
scalarProductMat n = map (map (n *))

-- | Multiply two matrices
multMat :: Num a => Matrix a -> Matrix a -> Matrix a
multMat matA matB =
  if snd (matSize matA) /= fst (matSize matB)
    then error "Incompatible matrix dimensions for multiplication"
    else aux matA (transposeMat matB)
  where
    aux [] _ = []
    aux (h : ts) mB = map (sum . zipWith (*) h) mB : aux ts mB

-- | Get transpose of matrix
transposeMat :: Matrix a -> Matrix a
transposeMat [] = []
transposeMat matrix =
  if length (head matrix) > 1
    then map head matrix : transposeMat (map tail matrix)
    else [map head matrix]

-- | Check if matrix is symmetric
isSymmetricMat :: Eq a => Matrix a -> Bool
isSymmetricMat matrix = matrix == transposeMat matrix

-- | Check if two matrices are inverses of each other
areInverseMats :: (Num a, Eq a) => Matrix a -> Matrix a -> Bool
areInverseMats matA matB = isIdentityMat (multMat matA matB)
