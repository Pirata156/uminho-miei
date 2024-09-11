-- @author Pirata
-- @version 2015.10

{-- Recursion --}
module Class03_1516 where

-- Ex. 1 - Provide the reduction chain for each expression, how the Haskell interpreter evaluates the following expressions:

-- (a) Explain what the value of 'p 5' is, considering the following definition:
-- This is a function that recursively verifies if a number is even.
p :: Int -> Bool
p 0 = True
p 1 = False
p x | x > 1 = p (x - 2)

-- @ghci> p 5
-- @ghci> False
-- p 5 -> Since 5 > 1, it will calculate 'p (5 - 2)'
-- p 3 -> Since 3 > 1, it will calculate 'p (3 - 2)'
-- p 1 -> p 1 = False.

-- (b) Explain what the value of 'f "otrec"' is, considering the following definition:
-- This function reverses the order of the elements in the list.
f l = g [] l

g l [] = l
g l (h : t) = g (h : l) t

-- @ghci> f "otrec"
-- @ghci> "certo"
-- f "otrec" -> g [] "otrec"
-- g [] "otrec" -> g [] 'o':"trec"
-- g [] 'o':"trec" -> g 'o':[] 't':"rec"
-- g 'o':[] 't':"rec" -> g 't':"o" 'r':"ec"
-- g 't':"o" 'r':"ec" -> g 'r':"to" 'e':"c"
-- g 'r':"to" 'e':"c" -> g 'e':"rto" 'c':[]
-- g 'e':"rto" 'c':[] -> g 'c':"erto" []
-- g 'c':"erto" [] -> g "certo" []
-- g "certo" [] -> "certo"

-- (c) Explain what the value of 'fun [1, 2, 3, 4, 5]' is, considering the following definition:
-- This function returns an empty list after skipping every two elements.
fun (x : y : t) = fun t
fun [x] = []
fun [] = []

-- @ghci> fun [1, 2, 3, 4, 5]
-- @ghci> []
-- fun [1, 2, 3, 4, 5] -> fun (1 : 2 : [3, 4, 5])
-- fun [3, 4, 5] -> fun (3 : 4 : [5])
-- fun [5] -> []

-- Ex. 2 - Define the following functions on lists of tuples:

-- (a) 'seconds', a function that calculates the list of the second components of the pairs.
seconds :: [(a, b)] -> [b]
seconds [] = []
seconds ((x, y) : ts) = y : seconds ts

-- (b) 'inFirsts', a function that tests whether an element appears as the first component in any of the pairs in the list.
inFirsts :: (Eq a) => a -> [(a, b)] -> Bool
inFirsts _ [] = False
inFirsts x (h : ts) = if fst h == x then True else inFirsts x ts

-- (c) 'minFst', a function that calculates the smallest first component.
minFst :: (Ord a) => [(a, b)] -> a
minFst list = mAux list (fst (head list))
  where
    mAux [] w = w
    mAux ((x, _) : ts) w = if x < w then mAux ts x else mAux ts w

-- (d) 'sndMinFst', a function that calculates the second component associated with the smallest first component.
sndMinFst :: (Ord a) => [(a, b)] -> b
sndMinFst list = sAux list (head list)
  where
    sAux [] (x, y) = y
    sAux ((w, z) : ts) (x, y) = if w < x then sAux ts (w, z) else sAux ts (x, y)

-- (e) 'sumTriples', a function that sums a list of triples component-wise.
sumTriples :: (Num a, Num b, Num c) => [(a, b, c)] -> (a, b, c)
sumTriples [] = (0, 0, 0)
sumTriples ((x, y, z) : ts) = (\(x, y, z) (xs, ys, zs) -> (x + xs, y + ys, z + zs)) (x, y, z) (sumTriples ts)
-- ou: sumTriples ((x, y, z) : ts) = tAux (x, y, z) (sumTriples ts) where tAux (x, y, z) (xs, ys, zs) = (x + xs, y + ys, z + zs)

-- (f) 'maxTriple', a function that calculates the maximum value obtained from the sum of the components of each triple in the list.
maxTriple :: (Ord a, Num a) => [(a, a, a)] -> a
maxTriple ((x, y, z) : ts) = mAux ts (x + y + z)
  where
    mAux [] big = big
    mAux ((x, y, z) : ts) big = if x + y + z > big then mAux ts (x + y + z) else mAux ts big

-- Ex. 3 - Recursively define the following functions on non-negative integers:

-- (a) '(><)', a function to multiply two integers by successive additions.
(><) :: Int -> Int -> Int
(><) x y = if (x > y) then mAux x y 0 else mAux y x 0
  where
    mAux _ 0 res = res
    mAux x y res = mAux x (y - 1) (res + x)

-- (b) 'div, mod', functions that calculate integer division and remainder by successive subtractions.
div' :: Int -> Int -> Int
div' x y = if (x < y) then 0 else 1 + (div' (x - y) y)

-- No caso de possibilitar números negativos, seria algo estilo o seguinte:
--div x y
--  | (x < 0) && (y < 0) = if (x < y) then 1 + (div (x - y) y) else 0
--	| (x < 0) && (y > 0) = if ((-x) > y) then (-1) + (div (x + y) y) else 0
--	| (x >= 0) && (y < 0) = if (x > (-y)) then (-1) + (div (x + y) y) else 0
--	| (x >= 0) && (y > 0) = if (x > y) then 1 + (div (x - y) y) else 0

mod' :: Int -> Int -> Int
mod' x y = if x < y then x else mod' (x - y) y

-- (c) 'power', a function that calculates the integer power of a number by successive multiplications.
-- Assuming 'y' is 0 or bigger.
power :: Int -> Int -> Int
power _ 0 = 1
power x y = x * power x (y - 1)

-- Ex. 4 - Assuming that a time is represented by a pair of integers, a trip can be represented as a sequence of stages,
-- where each stage is represented by a pair of times (departure, arrival):
type Time = (Int, Int)

type Stage = (Time, Time)

type Trip = [Stage]

-- From 'Class01_1516':
isValidTime :: Time -> Bool
isValidTime (h, m) = (h >= 0) && (h < 24) && (m >= 0) && (m < 60)

isTimeBefore :: Time -> Time -> Bool
isTimeBefore (hx, mx) (hy, my) = (hx < hy) || ((hx == hy) && (mx < my))

hour2Mins :: Time -> Int
hour2Mins (h, m) = (h * 60) + m

mins2Hour :: Int -> Time
mins2Hour m = divMod m 60

hoursDiff :: Time -> Time -> Int
hoursDiff sh eh = abs ((hour2Mins eh) - (hour2Mins sh))

addMins :: Time -> Int -> Time
addMins h m = mins2Hour ((hour2Mins h) + m)

-- Using the functions on times that you defined in 'Class01_1516', define the following functions:
-- (a) Test if a stage is well-constructed (i.e., the arrival time is later than the departure time, and the times are valid).
isValidStage :: Stage -> Bool
isValidStage (x, y) = (isValidTime x) && (isValidTime y) && (isTimeBefore x y)

-- (b) Test if a trip is well-constructed (i.e., for each stage, the arrival time is later than the departure time,
-- and the next stage starts after the previous one ends).
isValidTrip :: Trip -> Bool
isValidTrip [] = True
isValidTrip [x] = isValidStage x
isValidTrip ((x, xs) : (y, ys) : ts) = if (isValidStage (x, xs)) && (isTimeBefore xs y) then isValidTrip ((y, ys) : ts) else False

-- (c) Calculate the departure and arrival times of a given trip.
tripDepartArrival :: Trip -> (Time, Time)
tripDepartArrival trip = tAux trip (head trip) -- or just: tripDepartArrival l = (fst (head l), snd (last l))
  where
    tAux [] x = x
    tAux ((x, y) : ts) (st, _) = tAux ts (st, y)

-- (d) Given a valid trip, calculate the total effective travel time.
travelTime :: Trip -> Time
travelTime [] = (0, 0)
travelTime ((x, y) : ts) = addMins (travelTime ts) (hoursDiff x y)

-- (e) Calculate the total waiting time.
waitTime :: Trip -> Time
waitTime [] = (0, 0)
waitTime [_] = (0, 0)
waitTime ((x, xs) : (y, ys) : ts) = addMins (waitTime ((y, ys) : ts)) (hoursDiff xs y)

-- (f) Calculate the total time of the trip (the sum of waiting time and effective travel time).
-- It could basically be just travelTime + waitTime.
totalTripTime :: Trip -> Time
totalTripTime trip =
  let (start, end) = tripDepartArrival trip
   in mins2Hour (hoursDiff start end)
