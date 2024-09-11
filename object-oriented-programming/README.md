# Object-Oriented Programming

Object-Oriented Programming (OOP) is a programming paradigm where the focus is on *objects* rather than functions or procedures. It models real-world entities and organizes code around these objects, which have both data (attributes) and behaviors (methods). This approach makes it easier to think about programs in terms of how things interact with each other, rather than just executing a series of steps.

## Key Concepts in Object-Oriented Programming

1. **Classes and Objects**: 
   - A **class** is like a blueprint for creating objects. It defines the properties and methods that each object will have.
   - An **object** is an instance of a class. So, once you define a class, you can create multiple objects based on it, each with their own set of values.

2. **Encapsulation**: This is all about bundling data (variables) and methods (functions) that operate on that data into one unit, which is the class. Encapsulation hides the internal workings of an object and only exposes the necessary parts. This is useful because it means you can control how the object’s data is accessed or modified, usually through getter and setter methods.

3. **Inheritance**: Inheritance allows one class (the child or subclass) to inherit properties and methods from another class (the parent or superclass). This helps with code reuse and allows us to build upon existing classes without rewriting everything from scratch. Plus, you can override the parent class’s methods to provide more specific functionality in the child class.

4. **Polymorphism**: Polymorphism allows objects to be treated as instances of their parent class. There are two main types:
   - **Method Overloading**: This happens when multiple methods share the same name but have different parameters.
   - **Method Overriding**: This is when a subclass provides its own version of a method that is already defined in the parent class.
   
   This makes it easier to write code that can handle different types of objects through a common interface.

5. **Abstraction**: Abstraction is about simplifying complex systems by focusing only on the relevant details and ignoring the rest. You can define abstract classes or interfaces that just specify what methods a class should have, without worrying about how these methods are implemented. Subclasses will then handle the actual implementation.

## Object-Oriented Programming Languages

Some languages that are commonly used for OOP include:

- **Java**: One of the most popular languages for OOP, especially in large-scale enterprise systems.
- **C++**: Extends C with object-oriented features, and is used in systems where performance matters, like game development or real-time applications.
- **Python**: Python is multi-paradigm, but it’s often used in an object-oriented way because of its simplicity and flexibility.
- **Ruby**: Ruby takes OOP very seriously, where everything, even numbers and strings, are objects.

## Practical Uses

OOP is widely used in all sorts of applications. Since it encourages reusability and makes code more modular, it's great for building large systems that need to be maintained or expanded over time. Some common areas where OOP shines are:

- **Game Development**: Objects are a natural way to model game entities like characters, enemies, or items, with their own attributes (like health or damage) and behaviors (like attacking or moving).
- **GUI Applications**: In GUI apps, components like buttons, text boxes, and windows can be modeled as objects with their own events and properties.
- **Enterprise Software**: Large-scale systems in businesses use OOP to manage complex data structures and operations while keeping the codebase organized.

## Exercises

Exercises completed in Java that cover key OOP concepts like classes, inheritance, and polymorphism.
They were assigned as part of the course, and completing them was helpful for understanding how OOP works in practice.

- **Worksheets** - Quick worksheets focusing on specific topics each time.
    - [JAVA introduction and fundamentals](worksheet01)
    - [Arrays lab](worksheet02)
    - [Simple classes and fundamentals](worksheet03)
    - [ArrayList<E> and Collections fundamentals](worksheet04)
    - [List<E>, Set<E>, Map<E> and Collections](worksheet05)

- [**Practical Project**](employees) - Covered a wide range of OOP concepts while simulate managing employees in a company setting.

- [**Group Project**](translei) - Final group project for OOP class simulating a managing system for a fictitious transportation company.