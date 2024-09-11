# Haskell Prelude Functions

The Haskell Prelude is a standard library automatically imported into every Haskell module. It provides a basic set of functions, types, and type classes essential for Haskell programming. The Prelude functions cover a wide range of common tasks, from arithmetic operations to list processing, I/O, and more.

## 1. Basic Data Types and Type Classes

### **Data Types:**
- **`Bool`** - Represents Boolean values (`True` or `False`).
- **`Char`** - Represents a single character.
- **`Int`** - A fixed-size integer.
- **`Integer`** - An arbitrary-precision integer. Won't overflow.
- **`Float`** - A single-precision floating-point number.
- **`Double`** - A double-precision floating-point number.
- **`Maybe`** - Represents optional values (`Nothing` or `Just value`).
- **`Either`** - Represents a value that can be one of two types (`Left value` or `Right value`).
- **`Ordering`** - Represents the result of a comparison (`LT`, `EQ`, `GT`).
- **`()`** - The unit type, which has only one value: `()`.

### **Type Classes:**
- **`Eq`** - Provides equality (`==`, `/=`).
- **`Ord`** - Provides ordering (`<`, `>`, `<=`, `>=`).
- **`Show`** - Converts values to strings (`show`).
- **`Read`** - Converts strings to values (`read`).
- **`Num`** - Numeric type class (`+`, `-`, `*`, `abs`, `signum`).
- **`Integral`** - For integral types like `Int` and `Integer` (`div`, `mod`).
- **`Fractional`** - For fractional types like `Float` and `Double` (`/`, `fromRational`).
- **`Enum`** - Types that are enumerable, can be used in list ranges (`succ`, `pred`, `toEnum`, `fromEnum`).
- **`Bounded`** - Types that have an upper and lower bound (`minBound`, `maxBound`).
- **`Functor`** - Represents types that can be mapped over (`fmap`).
- **`Monad`** - Represents computations as sequences of steps (`>>=`, `return`).
- **`Applicative`** - A generalized form of `Functor` that applies functions in a context (`pure`, `<*>`).
- **`Foldable`** - Types that can be folded to a summary value (`foldr`, `foldl`, `foldMap`).
- **`Traversable`** - Types that can be traversed, applying a function and collecting results (`traverse`, `sequenceA`).

## 2. Basic Arithmetic Functions

- **`+`, `-`, `*`, `/`** - Standard arithmetic operations.
- **`div`, `mod`** - Integer division and modulus.
- **`quot`, `rem`** - Similar to `div` and `mod` but truncates toward zero.
- **`abs`** - Absolute value.
- **`signum`** - Sign of a number (-1, 0, or 1).
- **`negate`** - Negates a number.
- **`gcd`, `lcm`** - Greatest common divisor and least common multiple.
- **`(^)`** - Exponentiation (with non-negative integer exponent).
- **`(^^)`** - Exponentiation with fractional base and integer exponent.
- **`(**)`** - Exponentiation with fractional base and exponent.
- **`sqrt`** - Square root of a number.
- **`pi`** - The value of pi.

**Example:**
```haskell
gcd 6 8  -- Result: 2
2 ^ 3    -- Result: 8
2 ** 3   -- Result: 8.0
```

## 3. Conversion Functions

- **`fromIntegral`** - Converts an integral number to a more general numeric type.
- **`realToFrac`** - Converts any real number to a more general fractional type.
- **`toEnum`, `fromEnum`** - Convert between an `Enum` type and an `Int`.
- **`floor`, `ceiling`, `round`** - Convert floating-point numbers to the nearest integral value (down, up, or nearest).
- **`truncate`** - Converts a floating-point number to an integral type, discarding the fractional part.

**Example:**
```haskell
fromIntegral 5 :: Double         -- Result: 5.0
realToFrac (3 :: Int) :: Double  -- Result: 3.0
round 3.6                        -- Result: 4
```

## 4. Comparison Functions

- **`==`, `/=`** - Equality and inequality.
- **`<`, `>`, `<=`, `>=`** - Comparison operators.
- **`max`, `min`** - Maximum and minimum of two values.
- **`compare`** - Returns `LT`, `EQ`, or `GT` based on comparison.

**Example:**
```haskell
max 3 5      -- Result: 5
compare 3 5  -- Result: LT
```

## 5. Boolean Functions

- **`&&`, `||`** - Logical AND and OR.
- **`not`** - Logical negation.
- **`otherwise`** - A synonym for `True`, often used in guards.
- **`all`** - Returns `True` if all elements of a list satisfy the predicate.
- **`any`** - Returns `True` if any element of a list satisfies the predicate.

**Example:**
```haskell
all (>0) [1,2,3]  -- Result: True
any (<0) [1,2,3]  -- Result: False
```

## 6. List Functions

- **`:`** - Cons operator, adds an element to the front of a list.
- **`++`** - List concatenation.
- **`head`, `tail`** - First element and the rest of the list.
- **`last`, `init`** - Last element and all but the last element of the list.
- **`length`** - Length of a list.
- **`null`** - Checks if a list is empty.
- **`reverse`** - Reverses a list.
- **`map`** - Applies a function to each element of a list.
- **`filter`** - Filters a list based on a predicate.
- **`take`, `drop`** - Takes or drops a specified number of elements from a list.
- **`takeWhile`, `dropWhile`** - Takes or drops elements while a predicate holds true.
- **`span`, `break`** - Splits a list into two parts, based on a predicate.
- **`elem`, `notElem`** - Checks if an element exists in a list.
- **`zip`, `zipWith`** - Combines two lists into a list of pairs or applies a function to corresponding elements.
- **`concat`** - Concatenates a list of lists.
- **`concatMap`** - Maps a function over a list and concatenates the results.
- **`foldl`, `foldr`** - Left and right folds over a list.
- **`foldl'`, `foldr'`** - Strict versions of `foldl` and `foldr`.
- **`scanl`, `scanr`** - Like `foldl` and `foldr`, but returns a list of successive reduced values.
- **`unfoldr`** - Builds a list from a seed value.
- **`cycle`, `repeat`, `replicate`** - Create infinite or repeated lists.
- **`partition`** - Splits a list into two lists based on a predicate.
- **`elemIndex`, `elemIndices`** - Find the index or indices of an element in a list.
- **`find`** - Returns the first element that satisfies a predicate.
- **`lines`, `words`, `unlines`, `unwords`** - Functions for manipulating strings by splitting or joining them based on lines or spaces.

**Example:**
```haskell
take 3 [1,2,3,4,5]         -- Result: [1,2,3]
filter even [1,2,3,4,5]    -- Result: [2,4]
zip [1,2,3] ['a','b','c']  -- Result: [(1,'a'),(2,'b'),(3,'c')]
```

## 7. Tuple Functions

- **`fst`, `snd`** - Access the first and second elements of a pair (2-tuple).
- **`curry`** - Converts a function that takes a pair into a curried function.
- **`uncurry`** - Converts a curried function into a function that takes a pair.

**Example:**
```haskell
fst (1,2)  -- Result: 1
snd (1,2)  -- Result: 2
```

## 8. Enumerator Functions

- **`fromEnum`** - Gives the argument position in an enumeration.
- **`toEnum`** - Converts an `Int` to the corresponding enumerated value.
- **`succ`** - Returns the successor of an enumeration.
- **`pred`** - Returns the predecessor of an enumeration.
- **`enumFrom`** - Generates a list from a starting value to the end of the enumeration.
- **`enumFromTo`** - Generates a list from a starting value to an ending value.
- **`enumFromThen`** - Generates a list from a starting value with a specified step.
- **`enumFromThenTo`** - Generates a list from a starting value with a specified step up to an ending value.

**Example:**
```haskell
succ 'A'  -- Result: 'B'
[1..5]    -- Result: [1,2,3,4,5]
```

## 9. I/O Functions

- **`putStrLn`** - Prints a string followed by a newline.
- **`putStr`** - Prints a string without a newline.
- **`print`** - Converts a value to a string (using `show`) and prints it.
- **`getLine`** - Reads a line of input from the user.
- **`getChar`** - Reads a single character from the user.
- **`readFile`** - Reads the entire content of a file as a string.
- **`writeFile`** - Writes a string to a file, replacing the file's contents.
- **`appendFile`** - Appends a string to the end of a file.
- **`interact`** - Takes a function of type `String -> String` and applies it to input from stdin.

**Example:**
```haskell
main = do
    putStrLn "Enter name:"
    name <- getLine
    putStrLn ("Heya " ++ name ++ "!")
```

## 10. Maybe and Either Functions

- **`Maybe` type:** Represents optional values (`Nothing` or `Just value`).
- **`Either` type:** Represents a value that can be one of two types (`Left value` or `Right value`).

**Maybe Functions:**
- **`isNothing`** - Checks if a `Maybe` value is `Nothing`.
- **`isJust`** - Checks if a `Maybe` value is `Just`.
- **`fromMaybe`** - Extracts the value from a `Maybe`, with a default if it's `Nothing`.
- **`maybe`** - Applies a function to the value inside a `Maybe`, with a default if it's `Nothing`.

**Either Functions:**
- **`either`** - Applies one of two functions depending on whether the `Either` value is `Left` or `Right`.

**Example:**
```haskell
safeCheck :: [a] -> Maybe a
safeCheck [] = Nothing
safeCheck (x:_) = Just x

divide :: Double -> Double -> Either String Double
divide _ 0 = Left "Cannot divide by zero"
divide x y = Right (x / y)
```

## 11. Higher-Order Functions

Higher-order functions are functions that take other functions as arguments or return functions as results. Many of the list functions mentioned above (`map`, `filter`, `foldl`, `foldr`) are higher-order functions.

- **`($)`** - Function application operator, often used to avoid parentheses.
- **`(.)`** - Function composition operator.
- **`flip`** - Flips the order of the arguments of a function.
- **`id`** - The identity function, returns its argument unchanged.
- **`const`** - Returns a constant function, which ignores its second argument.

**Example:**
```haskell
map (*2) [1, 2, 3]                        -- Result: [2, 4, 6]
filter (>2) [1, 2, 3, 4]                  -- Result: [3, 4]
sum $ map (*2) [1, 2, 3]                  -- Result: 12
(map (*2) . filter (>3)) [1, 2, 3, 4, 5]  -- Result: [8, 10]
```

## 12. Function Utilities

- **`error`** - Causes a runtime error with the given message.
- **`undefined`** - A placeholder for an undefined value, causes a runtime error if evaluated.
- **`seq`** - Forces evaluation of its first argument before returning its second.
- **`($!)`** - Strict version of function application.

**Example:**
```haskell
error "This is an error!"  -- Causes a runtime error.
undefined                  -- Causes a runtime error if evaluated.
```

## 13. Monadic Functions

Monads are a key concept in Haskell for dealing with side effects and sequencing computations.

- **`return`** - Wraps a value in a monad.
- **`>>=`** - Monadic bind operator, sequences monadic computations.
- **`>>`** - Sequences two monadic actions, discarding the result of the first.
- **`fail`** - Encodes a failure in a monad.
- **`sequence`** - Takes a list of monadic actions and returns a monadic action that returns a list of results.
- **`mapM`** - Applies a monadic function to each element of a list and returns a monadic action that returns a list.
- **`forM`** - Like `mapM`, but with the arguments flipped.
- **`liftM`** - Lifts a function to work within a monad.

**Example:**
```haskell
main = do
    xs <- sequence [getLine, getLine]
    print xs
```

---

**More info:** Check for package preludo API in https://hackage.haskell.org

---
