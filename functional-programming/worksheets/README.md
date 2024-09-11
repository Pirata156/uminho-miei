# WorkSheets Resolutions

This folder contains a collection of resolved worksheets from practical exercises in Functional Programming (FP) classes, spanning multiple school years.
These exercises are designed to aid learning and skill development in Haskell.
Note that some exercises may be repeated across different years.
The full exercise descriptions have been included as comments within each implementation file, making it easy to reference the original problems alongside their solutions.

- **Version or Date:** 2010-2016
- **Authors:** PIRATA ([GitHub Profile](https://github.com/Pirata156))
- **Language:** Haskell

### How to Start This Project

To work with these worksheets, follow the steps in one of these options:

1. **Load a Worksheet into GHCi:**
   - Open GHCi (the Glasgow Haskell Compiler interactive environment).
   - Use the `:load` command (or `:l`) followed by the filename to load a `.hs` file. For example: `:load fp1011_class01.hs`.

2. **Compile and Run a Worksheet:**
   - Use the `ghc` command to compile a `.hs` file: `ghc -o Worksheet01 fp1011_class01.hs`.
   - Run the compiled program: `./Worksheet01`.

3. **Import the Worksheet Module:**
   - In GHCi, you can import the module of a `.hs` file by using the `:module` command followed by the module name.
   - In another file, you can import the module of a `.hs` file by using the `import` tag in a `.hs` file.
   - Note: The filename and module name must match for this to work.

---
---

## Worksheets - 2008/2009


#### [Worksheet 7: Binary Number Representation](fp0809_class07.hs)

This worksheet focuses on implementing alternative representations of positive integers using lists of boolean values (bits).
It covers fundamental binary number operations and their implementations using functional programming techniques.
Key topics include converting between integers and bit lists, implementing binary addition with carry operations, and developing multiplication algorithms for binary numbers.

#### [Worksheet 8: Polynomial Operations](fp0809_class08.hs)

This worksheet explores polynomial operations and their implementations in Haskell.
It introduces two different representations for polynomials: one using lists of monomials (coefficient-exponent pairs) and another using coefficient sequences.
The exercises cover implementing basic polynomial operations such as addition and multiplication, as well as polynomial normalization in different bases.

#### [Worksheet 11: Finite Functions and Type Classes](fp0809_class11.hs)

This worksheet delves into finite partial functions and their implementation using different data structures.
It introduces a type class `FF` for finite functions and implements it using two different approaches: ordered lists of pairs and binary search trees.
The exercises focus on implementing core operations like domain calculation, searching, adding, and removing elements.
It also covers Show class instantiation for custom data types and demonstrates how to optimize implementations without using higher-order functions.

#### [Worksheet 13: Expression Trees and Postfix Notation](fp0809_class13.hs)

This worksheet focuses on working with expression trees and postfix notation in Haskell.
It implements functionality to convert postfix notation strings into expression trees (`ExpInt`) and evaluate them.
The exercises include parsing and evaluating basic postfix expressions, and extend to handling a more complex system with variable memorization using the `@` operator.
The worksheet emphasizes tree manipulation, expression evaluation, and maintaining state through recursive processing.

---

## Worksheets - 2010/2011


#### [Worksheet 1: Set and List Definitions by Comprehension](fp1011_class01.hs)

This worksheet focuses on defining sets using comprehension notation and introduces list comprehension in Haskell, demonstrating how to generate lists based on specific conditions.
Exercises include operations on sets, such as finding the `minimum`, `sum`, and `count` of elements, and defining lists based on criteria like numbers, squares, and even numbers.
The worksheet also covers operations on lists, such as determining `length`, `minimum`, and `sum`, along with expressing lists by enumeration and defining them using comprehension.

#### [Worksheet 2: List Comprehensions and Function Definitions](fp1011_class02.hs)

This worksheet delves deeper into Haskell programming, focusing on list comprehensions, basic character manipulation, and foundational algorithms.
It begins with exercises on using list comprehensions to create specific lists, such as ASCII character codes and multiplication tables, and functions that generate all possible pairs from two lists.

#### [Worksheet 3: Introduction to Programming](fp1011_class03.hs)

This worksheet covers basic programming concepts such as custom type definitions, function creation, and list processing.
It involves defining and manipulating a custom Time type, validating time data, and performing time calculations.
Key topics include handling and converting time representations, validating stages and journeys, and aggregating time metrics such as travel and waiting times.

#### [Worksheet 4: Basics of Recursion](fp1011_class04.hs)

This worksheet focuses on stock management with Haskell, covering essential programming concepts.
It includes defining types for stock and shopping lists, implementing functions to manage stock data, calculate total values, and apply price adjustments.
Key topics include recursion, list manipulation, and functional programming techniques for querying and transforming data efficiently.

#### [Worksheet 5: Optimizing Recursion and List Processing](fp1011_class05.hs)

This worksheet focuses on optimizing recursive algorithms and list processing in Haskell.
It covers efficient definitions for integer division and modulus functions, improves the `splitAt` function to avoid redundant traversals, and implements the merge sort algorithm, including splitting and merging sorted lists.
Additionally, it explores string manipulation with custom implementations of words and unwords.
The exercises integrate practical programming skills with theoretical concepts in algorithm optimization and list processing.

#### [Worksheet 6: High-order and Predefined List Functions](fp1011_class06.hs)

This worksheet focuses on optimizing list processing and recursive functions in Haskell.
It begins by redefining `takeWhile` and `dropWhile` and provides an efficient implementation of `span` that avoids redundant traversals.
It reworks the `areaCode` function to use explicit recursion instead of `filter`.
The exercises also include revisiting previous solutions with high-order functions like `map` and `foldr`.
Finally, it covers polynomial operations, defining addition and multiplication for polynomials represented as coefficient lists.

#### [Worksheet 7: Number Representation and Arithmetic](fp1011_class07.hs)

This worksheet focuses on number representation and arithmetic operations within different bases, highlighting the connection to real-life problem-solving through functional programming.
It covers base conversion techniques and the handling of digit-wise arithmetic, such as using functions like `addDigits` for digit addition with carry and `timesTable` for digit multiplication.
The exercises extend these operations to sequences of digits, employing functions like `addBase` for base-specific addition and `multSeqByDig` and `multBase` for sequence multiplication.
Through these tasks, the worksheet reinforces concepts such as recursion, list manipulation, and higher-order functions in Haskell, emphasizing their application in breaking down complex problems into simpler components.

#### [Worksheet 8: Algebraic Data Types](fp1011_class08.hs)

This worksheet delves into geometric transformations and alternative representations of polygonal lines using Haskell.
It covers operations on geometric shapes, including computing lengths, determining shapes, triangulation, and area calculation.
Emphasis is placed on algebraic data types, recursion, and list manipulations.
The use of vector-based representations simplifies transformations like translation and scaling.
Overall, the worksheet enhances understanding of geometric transformations, data type conversions, and functional programming techniques in Haskell.

#### [Worksheet 9: Optimizing Recursion and List Processing](fp1011_class09.hs)

This worksheet explores recursive data types and class instantiation in Haskell.
It involves defining and manipulating mathematical expressions using the `ExpInt` type, with tasks including evaluating expressions, converting them to string representations, and expressing them in postfix notation.
Additionally, it covers representing expressions as sums of products with `ExpN`, focusing on recursion, the `Show` class, and the instantiation of classes for custom types.

#### [Worksheet 10: Polymorphism and Class Instantiation](fp1011_class10.hs)

This worksheet extends the exploration of functional programming with Haskell by generalizing expression types and class instances.
It begins by adapting the definitions from worksheet 9 to handle expressions with any numeric type using the `Num` class.
The tasks involve redefining functions for calculation, string conversion, and normalization to work with these generalized types.
This includes implementing `Num`, `Show`, and `Eq` instances for the new `Exp a` type to support arithmetic operations, string representation, and equality checks.

#### [Worksheet 11: Basics of IO](fp1011_class11.hs)

This worksheet focuses on implementing a EuroMillions betting system in Haskell.
It covers defining custom data types for bets, validating their correctness, and calculating common numbers and stars.
It also involves creating type class instances for equality checks and prize determination, as well as handling user input and interactive gameplay through a console-based menu system.

#### [Worksheet 12: Trees Everywhere](fp1011_class12.hs)

This worksheet focuses on working with various types of trees in Haskell, including binary trees where information is stored in nodes, leaf-only trees, and irregular trees with a variable number of descendants per node.
It covers defining and manipulating these tree structures, performing operations such as summing values, listing elements, calculating heights, and converting between tree types.
The exercises emphasize recursive data processing and the application of tree transformations and traversals.

---

## Worksheets - 2015/2016


#### [Worksheet 1: Introduction to Haskell](fp1516_class01.hs)

This worksheet introduces fundamental concepts of Haskell programming through practical exercises.
It focuses on using pre-defined Haskell functions to implement basic mathematical operations like calculating circle perimeters and distances between points.
The exercises cover working with different numeric types (Float, Int), list operations (head, tail, last), and conditional logic.
Key topics include type declarations with constraints (Floating, Integral), pattern matching with tuples, and implementing functions for tasks like finding multiples, manipulating lists based on length properties, and calculating maximums.
The worksheet concludes with more complex problems involving quadratic polynomials, demonstrating the use of guards and where clauses.

#### [Worksheet 2: List Comprehensions](fp1516_class02.hs)

This worksheet explores basic Haskell functions and recursion through practical exercises.
It begins with analyzing how the Haskell interpreter evaluates expressions by tracing reduction chains for functions like `funA` (calculating sum of squares) and `funB` (filtering even numbers).
The worksheet then covers recursive definitions of list processing functions, including `doubles` for doubling list elements and `numOccur` for counting character occurrences in strings.
The exercises emphasize understanding step-by-step evaluation and implementing fundamental recursive patterns while working with lists and basic data types.

#### [Worksheet 3: Recursion](fp1516_class03.hs)

This worksheet explores recursion in Haskell through a variety of exercises.
It begins with analyzing reduction chains of recursive functions, including examples of number-based recursion, list reversal with accumulators, and list processing patterns.
Students implement recursive functions for common operations like finding list maximums, checking element conditions, and string manipulation tasks such as counting occurrences and finding substrings.
The exercises emphasize proper recursive design through pattern matching, base cases, and step formulation.

#### [Worksheet 4: Polygons and Geometry](fp1516_class04.hs)

This worksheet explores geometric computations using custom types (Point, Rectangle, Triangle, Poligonal) and their operations.
It implements functions for distance calculations, polygonal line processing, shape conversions, and polygon triangulation.
The exercises cover type definitions, recursive algorithms for polygon manipulation, and area calculations through triangle decomposition of closed convex polygons.

#### [Worksheet 5: Lists Comprehension and Higher-Order Functions](fp1516_class05.hs)

This worksheet explores list comprehensions in Haskell for generating sequences and manipulating lists.
Through exercises like finding multiples, divisors, and prime numbers, it demonstrates how list comprehensions offer concise alternatives to explicit recursion.
Key implementations include functions for finding element positions, generating list prefixes, and string manipulation like palindrome checking.
The worksheet emphasizes using list comprehensions as an elegant approach to list operations and filtering tasks.

#### [Worksheet 6: Strings, Matrices and High-Order Functions](fp1516_class06.hs)

This worksheet focuses on string manipulation, number processing and matrix operations in Haskell.
It implements functions for converting between digits and numbers with multiple approaches (zipWith, recursion), and explores matrix operations including validation, addition, transposition, multiplication and rotation.
The exercises demonstrate higher-order functions, type aliases, pattern matching and generic type parameters while working with matrices represented as lists of lists.

#### [Worksheet 7: Algebraic Data Types](fp1516_class07.hs)

This worksheet explores algebraic data types in Haskell through three practical examples: a contact management system (storing phone numbers and emails), a birth date tracking system, and a bank statement processor.
It covers defining custom types, pattern matching, Maybe types for error handling, and both recursive and higher-order function approaches to data manipulation.
The exercises demonstrate real-world applications of algebraic data types while practicing fundamental functional programming concepts.

#### [Worksheet 8: Trees and Recursive Data Structures](fp1516_class08.hs)

This worksheet explores polymorphic binary trees and their applications through two main sections.
The first part focuses on implementing fundamental binary tree operations like calculating height, counting nodes/leaves, pruning at a given depth, following paths, creating mirror images, and working with tree zipping/unzipping functions.
The second part applies binary search trees to manage student records, implementing functions to check enrollment by number/name, list working students, calculate grades and statistics like percentage of missed evaluations and passing ratios.
The exercises demonstrate working with custom data types, recursive algorithms, and practical applications of tree structures.

#### [Worksheet 9: Processing Different Types](fp1516_class09.hs)

This worksheet explores algebraic data types through three main topics: integer expression trees (with calculation and string conversion functions), rose trees (irregular trees with variable descendants), and full trees (combining node and leaf information).
It implements core tree operations like summing elements, calculating heights, pruning, mirroring, and traversals, as well as functions to split and join different tree types.

#### [Worksheet 10: Classes](fp1516_class10.hs)

This worksheet explores type class instantiation through three main topics: implementing a fraction data type as instances of standard classes (Eq, Ord, Show, Num), extending a generic numeric expression type to be an instance of multiple classes, and creating custom class instances for bank statement types to enable date comparison and formatted display.
Key implementations include fraction normalization, expression evaluation, and statement sorting.

#### [Worksheet 11: IO](fp1516_class11.hs)

This worksheet explores IO operations in Haskell through interactive game implementations.
It covers random number generation and user input/output through two main games: a Bingo number drawer and a Mastermind code-breaking game.
The second part implements a EuroMillions lottery system with bet validation, prize checking, and an interactive betting interface.
The exercises demonstrate working with the IO monad, random number generation, and creating interactive command-line applications.
