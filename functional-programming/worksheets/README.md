# WorkSheets Resolutions

This folder contains a collection of resolved worksheets from practical exercises in Functional Programming (FP) classes, spanning multiple school years. These exercises are designed to aid learning and skill development in Haskell. Note that some exercises may be repeated across different years.

- **Version or Date:** 2010-2016
- **Authors:** PIRATA ([GitHub Profile](https://github.com/Pirata156))
- **Language:** Haskell

### How to Start This Project

To work with these worksheets, follow one of these steps:

1. **Load a Worksheet into GHCi:**
   - Open GHCi (the Glasgow Haskell Compiler interactive environment).
   - Use the `:load` command (or `:l`) followed by the filename to load a `.hs` file. For example: `:load fp1011_class01.hs`.

2. **Compile and Run a Worksheet:**
   - Use the `ghc` command to compile a `.hs` file: `ghc -o Worksheet01 fp1011_class01.hs`.
   - Run the compiled program: `./Worksheet01`.

3. **Import the Worksheet Module:**
   - In GHCi, you can import the module of a `.hs` file by using the `:module` command followed by the module name.
   - In another file, you can import the module of a `.hs` file by using the `import` tag in a `.hs` file.

---
---

## Worksheets - 2010/2011


#### [Worksheet 1: Set and List Definitions by Comprehension](fp1011_class01.hs)

This worksheet focuses on defining sets using comprehension notation and introduces list comprehension in Haskell, demonstrating how to generate lists based on specific conditions. Exercises include operations on sets, such as finding the `minimum`, `sum`, and `count` of elements, and defining lists based on criteria like numbers, squares, and even numbers. The worksheet also covers operations on lists, such as determining `length`, `minimum`, and `sum`, along with expressing lists by enumeration and defining them using comprehension.

#### [Worksheet 2: List Comprehensions and Function Definitions](fp1011_class02.hs)

This worksheet delves deeper into Haskell programming, focusing on list comprehensions, basic character manipulation, and foundational algorithms. It begins with exercises on using list comprehensions to create specific lists, such as ASCII character codes and multiplication tables, and functions that generate all possible pairs from two lists.

#### [Worksheet 3: Introduction to Programming](fp1011_class03.hs)

This worksheet covers basic programming concepts such as custom type definitions, function creation, and list processing. It involves defining and manipulating a custom Time type, validating time data, and performing time calculations. Key topics include handling and converting time representations, validating stages and journeys, and aggregating time metrics such as travel and waiting times.

#### [Worksheet 4: Basics of Recursion](fp1011_class04.hs)

This worksheet focuses on stock management with Haskell, covering essential programming concepts. It includes defining types for stock and shopping lists, implementing functions to manage stock data, calculate total values, and apply price adjustments. Key topics include recursion, list manipulation, and functional programming techniques for querying and transforming data efficiently.

#### [Worksheet 5: Optimizing Recursion and List Processing](fp1011_class05.hs)

This worksheet focuses on optimizing recursive algorithms and list processing in Haskell. It covers efficient definitions for integer division and modulus functions, improves the `splitAt` function to avoid redundant traversals, and implements the merge sort algorithm, including splitting and merging sorted lists. Additionally, it explores string manipulation with custom implementations of words and unwords. The exercises integrate practical programming skills with theoretical concepts in algorithm optimization and list processing.

#### [Worksheet 6: High-order and Predefined List Functions](fp1011_class06.hs)

This worksheet focuses on optimizing list processing and recursive functions in Haskell. It begins by redefining `takeWhile` and `dropWhile` and provides an efficient implementation of `span` that avoids redundant traversals. It reworks the `areaCode` function to use explicit recursion instead of `filter`. The exercises also include revisiting previous solutions with high-order functions like `map` and `foldr`. Finally, it covers polynomial operations, defining addition and multiplication for polynomials represented as coefficient lists.

#### [Worksheet 7: Number Representation and Arithmetic](fp1011_class07.hs)

This worksheet focuses on number representation and arithmetic operations within different bases, highlighting the connection to real-life problem-solving through functional programming. It covers base conversion techniques and the handling of digit-wise arithmetic, such as using functions like `addDigits` for digit addition with carry and `timesTable` for digit multiplication. The exercises extend these operations to sequences of digits, employing functions like `addBase` for base-specific addition and `multSeqByDig` and `multBase` for sequence multiplication. Through these tasks, the worksheet reinforces concepts such as recursion, list manipulation, and higher-order functions in Haskell, emphasizing their application in breaking down complex problems into simpler components.

#### [Worksheet 8: Algebraic Data Types](fp1011_class08.hs)

This worksheet delves into geometric transformations and alternative representations of polygonal lines using Haskell. It covers operations on geometric shapes, including computing lengths, determining shapes, triangulation, and area calculation. Emphasis is placed on algebraic data types, recursion, and list manipulations. The use of vector-based representations simplifies transformations like translation and scaling. Overall, the worksheet enhances understanding of geometric transformations, data type conversions, and functional programming techniques in Haskell.

#### [Worksheet 9: Optimizing Recursion and List Processing](fp1011_class09.hs)

This worksheet explores recursive data types and class instantiation in Haskell. It involves defining and manipulating mathematical expressions using the `ExpInt` type, with tasks including evaluating expressions, converting them to string representations, and expressing them in postfix notation. Additionally, it covers representing expressions as sums of products with `ExpN`, focusing on recursion, the `Show` class, and the instantiation of classes for custom types.

#### [Worksheet 10: Polymorphism and Class Instantiation](fp1011_class10.hs)

This worksheet extends the exploration of functional programming with Haskell by generalizing expression types and class instances. It begins by adapting the definitions from worksheet 9 to handle expressions with any numeric type using the `Num` class. The tasks involve redefining functions for calculation, string conversion, and normalization to work with these generalized types. This includes implementing `Num`, `Show`, and `Eq` instances for the new `Exp a` type to support arithmetic operations, string representation, and equality checks.

#### [Worksheet 11: Basics of IO](fp1011_class11.hs)

This worksheet focuses on implementing a EuroMillions betting system in Haskell. It covers defining custom data types for bets, validating their correctness, and calculating common numbers and stars. It also involves creating type class instances for equality checks and prize determination, as well as handling user input and interactive gameplay through a console-based menu system.

#### [Worksheet 12: Trees Everywhere](fp1011_class12.hs)

This worksheet focuses on working with various types of trees in Haskell, including binary trees where information is stored in nodes, leaf-only trees, and irregular trees with a variable number of descendants per node. It covers defining and manipulating these tree structures, performing operations such as summing values, listing elements, calculating heights, and converting between tree types. The exercises emphasize recursive data processing and the application of tree transformations and traversals.
