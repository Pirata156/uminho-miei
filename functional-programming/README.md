# Functional Programming

Functional programming is a programming paradigm that emphasizes computing results through mathematical functions without changing the state of variables or using mutable data. Instead of telling the computer how to do something step by step (as in imperative programming), it focuses on *what* needs to be done.

## Key Concepts in Functional Programming

1. **Immutability**: In functional programming, data is immutable, meaning once a value is created, it cannot be changed. Instead of modifying existing data, new data is created with the required updates. This ensures that data remains consistent throughout the program.

2. **Pure Functions**: A pure function always produces the same output for the same input and has no side effects (such as modifying global variables or interacting with I/O). This predictability makes the code easier to test and reason about.

3. **First-class and Higher-order Functions**: In functional programming, functions are first-class citizens, meaning they can be assigned to variables, passed as arguments, and returned from other functions. Higher-order functions are those that either take functions as arguments or return them as results, promoting reusability and flexibility in code.

4. **Recursion**: Instead of traditional loops, functional programming often uses recursion, where a function calls itself to iterate over data or perform repetitive tasks. Recursion is essential for traversing data structures like lists.

5. **Referential Transparency**: This property means that an expression (or function call) can always be replaced with its value without changing the program's behavior. It implies that functions always return the same result for the same input, further enhancing predictability and reliability.

## Functional Programming Languages

Some common languages that follow the functional programming paradigm include:

- **Haskell**: A purely functional language known for its strong type system and lazy evaluation.
- **Clojure**: A modern, functional dialect of Lisp that runs on the Java Virtual Machine (JVM).
- **Scala**: A hybrid language that combines functional programming with object-oriented programming (OOP), also running on the JVM.
- **Elixir**: A functional programming language designed for building scalable, fault-tolerant systems.

## Practical Uses

Even in languages that aren’t strictly functional, such as **JavaScript** or **Python**, many functional concepts are used. Examples include higher-order functions like `map`, `filter`, and `reduce`. This demonstrates the widespread influence of functional programming, even in multi-paradigm languages.

Functional programming is often preferred for building systems that require parallelism or distribution because immutability and lack of side effects make the code more reliable, scalable, and easier to reason about in concurrent environments.


## Haskell

Haskell is the main programming language we use in this course.
It's a purely functional language, which means everything is done through functions rather than statements that modify data.
The language enforces immutability by default and uses a strong type system that catches many errors at compile time.

Like other programming languages, Haskell comes with a standard library called the Prelude that contains the basic functions and types we'll use frequently.
The [Haskell Prelude](Haskell_Prelude.md) file contains documentation on essential predefined types and functions from Haskell's standard library.


## Exercises

* [**Worksheets**](worksheets) - Practical exercises in Haskell covering various Functional Programming concepts completed throughout the course.
* [**Assessments**](assessments) - Assessment materials including tests, exams and sample preparation exercises for Functional Programming tests or exams.
* [**Sandbox**](sandbox) - A collection of experimental implementations and practice code created while learning Functional Programming concepts.
