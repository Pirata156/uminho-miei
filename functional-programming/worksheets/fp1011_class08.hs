-- @author Pirata
-- @version 2015.12

{-- Algebraic Data Types --}
module Class08_1011 where

import Data.List (minimumBy, nub, sort)

-- Ex. 1 - Consider the following definitions:
type Point = (Float, Float) -- (x-coordinate, y-coordinate)

type Poligonal = [Point] -- poligonal line

type Vector = (Float, Float) -- vector applied at the origin

-- Shape is a custom data type representing geometric shapes: rectangles and triangles.
data Shape
  = Rectangle Point Float Float -- upper-left corner, width, height
  | Triangle Point Point Point -- the 3 vertices

-- distance calculates the Euclidean distance between two points
distance :: Point -> Point -> Float
distance (a, b) (c, d) = sqrt (((c - a) ^ 2) + ((b - d) ^ 2))

-- (a) Define a function that calculates the length of a poligonal line.
-- poliLength computes the total length of a poligonal line by summing the distances between consecutive points
poliLength :: Poligonal -> Float
poliLength [] = 0
poliLength [pt] = 0
poliLength (pt1 : pt2 : tail) = (distance pt1 pt2) + poliLength (pt2 : tail)

-- (b) Define a function that converts an element of type 'Shape' into the corresponding poligonal line.
shapeToPoli :: Shape -> Poligonal
shapeToPoli (Rectangle (x, y) w l) = [(x, y), (x + w, y), (x + w, y - l), (x, y - l), (x, y)]
shapeToPoli (Triangle v1 v2 v3) = [v1, v2, v3, v1]

-- (c) Define functions to:
-- i. Test if a poligonal line is closed.
-- isPoliClosed checks if the first point is equal to the last point in the poligonal line.
isPoliClosed :: Poligonal -> Bool
isPoliClosed poli = head poli == last poli

-- ii. Test if a poligonal line corresponds to a triangle.
-- isPoliTriangle checks if the poligonal line corresponds to a triangle by ensuring there are 4 vertices, the line is closed,
-- and there are exactly 3 unique vertices (one vertex is repeated due to closure).
isPoliTriangle :: Poligonal -> Bool
isPoliTriangle poli = length poli == 4 && isPoliClosed poli && length (nub poli) == 3

-- iii. Test if a poligonal line corresponds to a rectangle.
-- isPoliRectangle checks if the poligonal line corresponds to a rectangle by ensuring there are 5 vertices, the line is closed,
-- and the distances between consecutive points satisfy the properties of a rectangle (equal opposite sides and diagonals).
isPoliRectangle :: Poligonal -> Bool
isPoliRectangle poli@[v1, v2, v3, v4, v5] = isPoliClosed poli && length poli == 5 && (distance v1 v2) == (distance v3 v4) && (distance v2 v3) == (distance v4 v5) && (distance v1 v3) == (distance v2 v4) && (distance v1 v3) > (distance v1 v2) && (distance v1 v3) > (distance v2 v3)
isPoliRectangle _ = False

-- (d) Define a function 'poli2Shape :: Poligonal -> Maybe Shape' which converts a poligonal line into a shape (if possible).
poli2Shape :: Poligonal -> Maybe Shape
poli2Shape poli
  | isPoliTriangle poli = Just (poli2Triangle poli)
  | isPoliRectangle poli = Just (poli2Rectangle poli)
  | otherwise = Nothing
  where
    -- poli2Triangle converts a poligonal line into a Triangle, assuming it has already been validated
    poli2Triangle :: Poligonal -> Shape
    poli2Triangle [v1, v2, v3, _] = Triangle v1 v2 v3
    -- poli2Rectangle converts a poligonal line into a Rectangle, assuming it has already been validated
    poli2Rectangle :: Poligonal -> Shape
    poli2Rectangle [v1, v2, v3, v4, _] = Rectangle topLeft w l
      where
        -- Find the top-left vertex of the rectangle (highest y, and smallest x in case of tie)
        topLeft :: Point
        topLeft = minimumBy (\(x1, y1) (x2, y2) -> if y1 > y2 then LT else if y1 < y2 then GT else compare x1 x2) [v1, v2, v3, v4]
        [p1, p2, p3] = filter (/= topLeft) [v1, v2, v3, v4] -- The rest of the vertices
        -- Sort the distances to determine the width and length of the rectangle
        [w, l, _] = sort [distance topLeft p1, distance topLeft p2, distance topLeft p3]

-- (e) Define a function 'triangulate' that, given a closed and convex poligonal line,
-- calculates a list of triangles whose total area equals the area enclosed by the poligonal line.
-- triangulate recursively breaks a poligonal line into triangles by selecting the first point and pairing it with subsequent points.
triangulate :: Poligonal -> [Shape]
triangulate (p1 : p2 : p3 : p4 : t) = (Triangle p1 p2 p3) : (triangulate (p1 : p3 : p4 : t))
triangulate _ = []

-- (f) Using this 'triangleArea' function, define a function that calculates the area enclosed by a closed and convex poligonal line.
triangleArea :: (Point, Point, Point) -> Float
triangleArea (x, y, z) =
  let a = distance x y
      b = distance y z
      c = distance z x
      s = (a + b + c) / 2 --semiperimetro
   in sqrt (s * (s - a) * (s - b) * (s - c)) -- formula de Heron

-- poligonalArea calculates the total area of a closed and convex poligonal line by summing the areas of the individual triangles
poligonalArea :: Poligonal -> Float
poligonalArea l = sum (map aux (triangulate l))
  where
    aux (Triangle p1 p2 p3) = triangleArea (p1, p2, p3)

-- (g) Define the function 'translation' that translates the poligonal line according to the given vector.
translation :: Vector -> Poligonal -> Poligonal
translation (x, y) l = map (\(a, b) -> (x + a, y + b)) l

-- (h) Define a function 'zoom2' that, given a poligonal line, produces a similar poligonal line with the same starting point,
-- but where the length of each line segment is multiplied by 2.
zoom2 :: Poligonal -> Poligonal
zoom2 [] = []
zoom2 (x : t) = x : (zoomer x t)
  where
    zoomer :: Point -> Poligonal -> Poligonal
    zoomer _ [] = []
    zoomer x (y : t) = (((y `minus` x) `multiplyBy` 2) `add` x) : (zoomer x t)

-- Auxiliary basic methods for point and vector manipulation
minus (a, b) (c, d) = (a - c, b - d) -- Subtracts two points

multiplyBy (a, b) c = (a * c, b * c) -- Multiplies a point or vector by a scalar

add (a, b) (c, d) = (a + c, b + d)   -- Adds two points or vectors

-- (i) Define a more general version of the previous function,
-- allowing zoom-in or zoom-out of the figure with the desired amplification or reduction factor.
zoomX :: Float -> Poligonal -> Poligonal
zoomX _ [] = []
zoomX _ [x] = [x]
zoomX f (x : t) = x : (zoomify f x t)
  where
    -- zoomify recursively scales the points by a factor 'f' relative to the first point
    zoomify :: Float -> Point -> Poligonal -> Poligonal
    zoomify _ _ [] = []
    zoomify f x (y : t) = (((y `minus` x) `multiplyBy` f) `add` x) : (zoomify f x t)

-- Ex. 2 - An alternative representation for poligonal lines is to represent these lines with an origin point and a sequence of vectors.
-- Thus, the square [(2,1), (5,2), (4,5), (1,4), (2,1)] could be represented by
-- the initial point '(2,1)' and the following list of vectors [(3,1), (-1,3), (-3,-1), (1,-3)]
type Poly = (Point, [Vector])

-- (a) Use the predefined function 'zipWith' to define the function that converts elements of type 'Poligonal' into 'Poly'.
converts :: Poligonal -> Poly
converts [] = ((0, 0), [])
converts (x : t) = (x, zipWith minus t (x : t))

-- (b) Also, define the function that performs the inverse conversion.
reverts :: Poly -> Poligonal
reverts (x, []) = [x]
reverts (x, (y : t)) = x : (reverts ((x `add` y), t))

-- (c) Use these conversion functions to redefine the translation and zoom functions for poligonals.
-- polyTranslation translates a Poly by applying the translation vector to the origin point
polyTranslation :: Vector -> Poly -> Poly
polyTranslation v (x, l) = ((x `add` v), l)

-- polyZoom scales a Poly by scaling all vectors by the given factor
polyZoom :: Float -> Poly -> Poly
polyZoom f (x, l) = (x, (map (`multiplyBy` f) l))
