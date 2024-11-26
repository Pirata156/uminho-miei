-- @author Pirata
-- @version 2015.12

{-- Poligons and Auxiliary Functions --}
module Class04_1516 where

-- Ex. 1 - Consider the following definitions:
type Point = (Float, Float) -- (x-coordinate, y-coordinate)

type Rectangle = (Point, Float, Float) -- (upper-left corner, width, height)

type Triangle = (Point, Point, Point) -- (vertices)

type Poligonal = [Point]

distance :: Point -> Point -> Float
distance (a, b) (c, d) = sqrt (((c - a) ^ 2) + ((b - d) ^ 2))

-- (a) Define a function that calculates the length of a polygonal line.
poliLength :: Poligonal -> Float
poliLength (x : y : xys) = (distance x y) + poliLength (y : xys)
poliLength _ = 0

-- (b) Define a function that converts an element of type 'Triangle' to the corresponding polygonal line.
triToPoli :: Triangle -> Poligonal
triToPoli (v1, v2, v3) = [v1, v2, v3, v1]

-- (c) Repeat the previous item for elements of type 'Rectangle'.
rectToPoli :: Rectangle -> Poligonal
rectToPoli ((x, y), w, h) = [(x, y), (x + w, y), (x + w, y - h), (x, y - h), (x, y)]

-- (d) Define a function 'closed' that tests whether a given polygonal line is closed or not.
-- A simple definition could be: closed l = head l == last l
closed :: Poligonal -> Bool
closed [x, y] = x == y
closed (x : y : ts) = closed (x : ts)
closed _ = False

-- (e) Define a function 'triangulate' that, given a closed and convex polygonal line,
-- calculates a list of triangles whose sum of areas equals the area enclosed by the polygonal line.
triangulate :: Poligonal -> [Triangle]
triangulate [x, y, w, _] = [(x, y, w)] -- [x, y, w, x]
triangulate (x : y : w : z : ts) = (x, y, w) : (triangulate (x : w : z : ts))
triangulate _ = [] -- for [], [x], [x, y], [x, y, z];

-- (f) Suppose there is a function 'triangleArea' that calculates the area of a triangle.
-- Define a function that calculates the area enclosed by a closed and convex polygonal line.
triangleArea (x, y, z) =
  let a = distance x y
      b = distance y z
      c = distance z x
      s = (a + b + c) / 2 -- semi-perimeter
   in -- Heron's formula
      sqrt (s * (s - a) * (s - b) * (s - c))

-- Using high-order functions: poliArea l = sum (map triangleArea (triangulate l))
poliArea :: Poligonal -> Float
poliArea lista = aPAux (triangulate lista)
  where
    aPAux [] = 0
    aPAux (h : ts) = (triangleArea h) + (aPAux ts)

-- (g) Define a function 'move' that, given a polygonal line and a point, returns a polygonal line identical to the first
-- but with the given point as the new starting point. For example, moving the triangle '[(1,1),(10,10),(10,1),(1,1)]'
-- to point '(1,2)' should result in the triangle '[(1,2),(10,11),(10,2),(1,2)]'.
move :: Poligonal -> Point -> Poligonal
move [] _ = []
move ((x, y) : ts) (a, b) = mAux ((x, y) : ts) (a - x, b - y)
  where
    mAux [] _ = []
    mAux ((vx, vy) : vts) (ab, od) = (vx + ab, vy + od) : (mAux vts (ab, od))

-- (h) Define a function 'zoom2' that, given a polygonal line, returns a similar polygonal line with the same starting point
-- but with each line segment length multiplied by 2. For example, the rectangle '[(1,1),(1,3),(4,3),(4,1),(1,1)]' should be
-- transformed into '[(1,1),(1,5),(7,5),(7,1),(1,1)]'.
zoom2 :: Poligonal -> Poligonal
zoom2 [] = []
zoom2 [x] = [x]
zoom2 (x : y : t) = x : (zoomAux x (y : t))
  where
    zoomAux :: Point -> Poligonal -> Poligonal
    zoomAux _ [] = []
    zoomAux x (y : t) = ((\(a, b) (c, d) -> (a + c, b + d)) ((\(a, b) c -> (a * c, b * c)) ((\(a, b) (c, d) -> (a - c, b - d)) y x) 2) x) : (zoomAux x t)

-- Ex. 2 - Consider the following type definitions to represent a table of temperature records:
type TempTable = [(Date, Temp, Temp)] -- (date, min temperature, max temperature)

type Date = (Int, Int, Int) -- (year, month, day)

type Temp = Float

-- (a) Define the function 'averages' that constructs a list with the average temperatures for each day.
averages :: TempTable -> [(Date, Temp)]
averages [] = []
averages ((dt, minT, maxT) : ts) = (dt, (minT + maxT) / 2) : (averages ts)

-- (b) Define the function 'decreasing' that tests whether the table is sorted in descending date order.
-- (Note: you can use the operator '>' to directly compare two dates.)
decreasing :: TempTable -> Bool
decreasing ((dtx, _, _) : (dty, t1, t2) : ts) = (dtx > dty) && (decreasing ((dty, t1, t2) : ts))
decreasing _ = True -- for [] and [(x, _, _)]

-- (c) Define the function 'count' that, given a list of dates and the temperature record table,
-- counts how many of the dates in the list have a record in the table.
count :: [Date] -> TempTable -> Int
count [] _ = 0
count (dtx : dts) temps = if (apAux dtx temps) then 1 + (count dts temps) else 0 + (count dts temps)
  where
    apAux _ [] = False
    apAux dti ((dtx, _, _) : ts) = (dti == dtx) || (apAux dti ts)

-- high-order functions like map, lambda and elems could have reduced this function's definition

-- Ex. 3 - A multiset is a set that allows repeated elements. It differs from a list because the order of the elements is irrelevant.
-- One way to implement multisets in Haskell is through a list of pairs, where each pair records an element and the respective number of occurrences:
type MSet a = [(a, Int)]

-- (a) Define the function 'union' that computes the union of two multisets.
union :: (Eq a) => MSet a -> MSet a -> MSet a
union ms [] = ms
union [] ms = ms
union ms (h : ts) = union (insAux h ms) ts
  where
    insAux (ex, ox) [] = [(ex, ox)]
    insAux (ex, ox) ((ey, oy) : ts) = if ex == ey then (ex, (ox + oy)) : ts else (ey, oy) : (insAux (ex, ox) ts)

-- (b) Define the function 'intersect' that computes the intersection of two multisets.
intersect :: (Eq a) => MSet a -> MSet a -> MSet a
intersect [] _ = []
intersect _ [] = []
intersect (h : ts) ms =
  let result = intAux h ms
   in if snd result == -1 then intersect ts ms else result : intersect ts ms
  where
    intAux (x, y) [] = (x, -1)
    intAux (x, y) ((a, b) : msts) = if x == a then (x, min y b) else intAux (x, y) msts

-- (c) Define the function 'diff' that computes the difference between two multisets.
diff :: (Eq a) => MSet a -> MSet a -> MSet a
diff [] ms = ms
diff ms [] = ms
diff (h : ts) ms =
  let result = difAux h ms []
   in if (snd (fst result)) == 0 then diff ts (snd result) else (fst result) : diff ts (snd result)
  where
    difAux (x, y) [] new = ((x, y), new)
    difAux (x, y) ((a, b) : msts) new = if x == a then ((x, abs (y - b)), new ++ msts) else difAux (x, y) msts (new ++ [(a, b)])

-- (d) Define the function 'sort' that sorts a multiset by the increasing number of occurrences.
sort :: MSet a -> MSet a
sort [] = []
sort ms = ordAux ms []
  where
    ordAux [] new = new
    ordAux (h : ts) new = ordAux ts (inAux h new)
    inAux el [] = [el]
    inAux (x, y) ((a, b) : mts) = if y > b then (a, b) : (inAux (x, y) mts) else (x, y) : (a, b) : mts

-- (e) Define the function 'mode' that returns the list of elements with the highest number of occurrences.
mode :: MSet a -> [a]
mode [] = []
mode ((x, y) : ts) = mAux ts y [x]
  where
    mAux [] _ new = new
    mAux ((x, y) : ts) hg new = if hg > y then mAux ts hg new else if hg == y then mAux ts hg (new ++ [x]) else mAux ts y [x]
