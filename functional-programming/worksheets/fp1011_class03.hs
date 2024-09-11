-- @author Pirata
-- @version 2010.10

{-- Basics of programming - Lego pieces - Test case - Time --}
module Class03_1011 where

-- Time type definition
type Time = (Int, Int)

-- Ex. 1 - Define some basic functions related to time:

-- (a) Test if a pair of integers represents a valid time of day.
-- Checks if the hour is between 0 and 23 and if the minute is between 0 and 59.
isValidTime :: Time -> Bool
isValidTime (hour, min) = (hour >= 0) && (hour < 24) && (min >= 0) && (min < 60)

-- (b) Test if one time is after another.
-- Compares two Time values and returns True if the first is after the second.
-- Assumes both Times are valid.
isAfter :: Time -> Time -> Bool
isAfter (h1, m1) (h2, m2) = (h1 > h2) || (h1 == h2 && m1 > m2)

-- Note: For tuples, direct comparison (isAfter time1 time2 = time1 > time2) also works.

-- (c) Convert a Time value to minutes.
-- Converts a Time value (hours, minutes) into the total number of minutes since midnight.
-- Assumes the Time is valid.
time2Mins :: Time -> Int
time2Mins (hours, mins) = hours * 60 + mins

-- (d) Convert a value in minutes to a Time.
-- Converts a given number of minutes since midnight into a Time value (hours, minutes).
-- Assumes the input is a non-negative integer.
mins2Time :: Int -> Time
mins2Time mins = (div mins 60, mod mins 60)

-- (e) Compute the difference between two times (result should be the number of minutes).
-- Calculates the absolute difference in minutes between two Time values.
-- Assumes both Time inputs are valid.
timeDiff :: Time -> Time -> Int
timeDiff tm1 tm2 = abs ((time2Mins tm1) - (time2Mins tm2))

-- Ex. 2 - Write a function to calculate the number of stages in a journey.
-- Stage = (Time, Time) & Journey = [Stage]
-- -- This function calculates the length of the journey, the number of stages.
numberOfStages :: [(Time, Time)] -> Int
numberOfStages [] = 0
numberOfStages (h : t) = 1 + numberOfStages t

-- Ex. 3 - Write a function to calculate the departure time of a journey, and another function to calculate the arrival time.
-- Returns the departure time of the first stage in the journey.
-- Assumes the journey is not an empty list.
departureTime :: [(Time, Time)] -> Time
departureTime journey = fst (head journey)

-- Returns the arrival time of the last stage in the journey.
-- Assumes the journey is not an empty list.
arrivalTime :: [(Time, Time)] -> Time
arrivalTime journey = snd (last journey)

-- Ex. 4 - Write a function to check if a stage is well-constructed (if the arrival time is after the departure time and the times are valid).
-- Checks if the stage is valid by ensuring that both the departure and arrival are valid times,
-- and that the arrival time is after the departure time.
isValidStage :: (Time, Time) -> Bool
isValidStage (dep, arr) = (isValidTime dep) && (isValidTime arr) && (isAfter arr dep)

-- Ex. 5 - Write a function to check if a journey is well-constructed (if the stages are valid and each subsequent stage starts after the previous stage has ended).
-- Checks if a journey is valid by ensuring that each stage is valid and that each stage starts after the previous one ends.
-- The journey is considered invalid if it is empty.
isValidJourney :: [(Time, Time)] -> Bool
isValidJourney [] = False
isValidJourney [stage] = isValidStage stage
isValidJourney ((dep1, arr1) : (dep2, arr2) : t) = isValidStage (dep1, arr1) && (isAfter dep2 arr1) && isValidJourney ((dep2, arr2) : t)

-- Ex. 6 - Write a function that, given a valid journey, calculates the total effective travel time.
-- Sums up the duration of all stages (the difference between the departure and arrival times) in a valid journey.
-- Assumes a valid journey is given.
travelingTime :: [(Time, Time)] -> Int
travelingTime [] = 0
travelingTime ((dep, arr) : t) = (timeDiff dep arr) + travelingTime t

-- Ex. 7 - Write a function to calculate the total waiting time.
-- Sums up the waiting time between consecutive stages in a valid journey.
-- Waiting time is the difference between the arrival time of one stage and the departure time of the next stage.
-- Assumes a valid journey as input.
waitingTime :: [(Time, Time)] -> Int
waitingTime [] = 0
waitingTime [stage] = 0
waitingTime ((dep1, arr1) : (dep2, arr2) : t) = (timeDiff arr1 dep2) + waitingTime ((dep2, arr2) : t)

-- Ex. 8 - Write a function to calculate the total journey time (the sum of waiting time and effective travel time)
-- Returns the total journey time, which is the difference between the departure time of the first stage and the arrival time of the last stage.
-- If the journey is invalid, an error is raised.
journeyTime :: [(Time, Time)] -> Int
journeyTime journey = if isValidJourney journey
                      then timeDiff (departureTime journey) (arrivalTime journey)
                      else error "Invalid journey input."

-- or: journeyTime jn = travelingTime jn + waitingTime jn
