------------------------------------------------------------------------
This is the project README file for Worksheet 04 (Ficha Pratica 04).
Here, you'll find information about the Object-Oriented Programming (OOP) practical exercises.

PROJECT TITLE: Worksheet 04 (Ficha Pratica 04)
PURPOSE OF PROJECT: Object-Oriented Programming (OOP) classes' practical exercises for various classes.
VERSION or DATE: [Insert Date]
AUTHORS: PIRATA (https://github.com/Pirata156)
JAVA VERSION: Java 7

HOW TO START THIS PROJECT:
  1. Open the project in BlueJ or your preferred Java development environment.
  2. Compile each Java class.
  3. Compile Javadoc for documentation.
  4. Use tools provided by your development environment for testing.

USER INSTRUCTIONS:

**Exercise Overview:**

This BlueJ project is a collection of Java classes, each corresponding to a different exercise from Worksheet 04 of the Object-Oriented Programming course.
Each class addresses specific problem statements as follows:

- **TesteArrayList**: A class to understand how ArrayList<E> works. Utilizes an ArrayList<String> and an ArrayList<Point2D>, and invokes ArrayList<E> API methods.
- **Plane**: Represents a model of a Cartesian plane with 2D points with functionalities like point manipulation and displacement.
- **CountryList**: Represents a management of CountrySheet. Methods include counting countries, population-related queries, and modification operations.
- **Stack**: Represents a Stack of strings, a linear structure of the LIFO type with operations such as top(), push(), pop(), empty(), and length().
- **Mail and MailList**: Represents a class for handling received emails. Provides methods for counting, saving, filtering by sender, and managing emails based on dates and subject keywords.

Feel free to explore each class, read the Javadoc documentation, and use this project as a reference for your OOP exercises.

**Notes:**
Ensure that Javadoc documentation is generated for a detailed explanation of each class and its methods.

**Author's Notes:**
Feel free to reach out to the author (PIRATA) on GitHub for any clarifications or assistance.

**Exercises complete description:**

Ex 1: Understanding how ArrayList<E> works
Create a class named TesteArrayList in BlueJ and import the previously developed Ponto2D class.
Then, using Tools/Use Library Class..., create an empty instance of an ArrayList<String> and another of an ArrayList<Point2D>.
Then, with both arraylist instances created, invoke the ArrayList<E> API methods, cf. add(), get(), addAll(), remove(), removeAll(), size(), indexOf(), contains(), retainAll(), etc., and conclude on the semantics of each method using INSPECT after your invocation.

Ex 2. Develop a Plane class that represents a set of 2D points on a Cartesian plane in an ArrayList<Ponto2D>
(the correct representation would be a set but as we have not yet studied how to represent sets in JAVA we will use an arraylist).
Develop, in addition to the usual constructors and methods, methods that implement the following functionalities:
-Determine the total number of points in a plane;
-Add a new point to the plan and remove a point if it exists;
-Given an ArrayList of Ponto2D, join such points to the receiving plane;
-Determine how many points are more to the right or more above (or both) in relation to the Ponto2D given as a parameter;
-Displace all points with a coordinate in XX equal to cx, given as a parameter, by dx units, also given as a parameter (change the points therefore);
-Given a plane as a parameter, determine how many points are common to the two planes;
-Create the list containing the points common to the receiving plane and the parameter plane;
-Create a list containing all points on the plane with a coordinate in XX lower than a value given as a parameter (attention, the aim is to obtain copies of the originals);
-Create a new plane that contains the common points between the receiving plane and a plane given as a parameter;
-Don't forget the equals(), toString() and clone() methods.

Ex 3: An information sheet for a country, CountrySheet, contains 3 attributes: name of the country, continent and population (real, in millions).
Create a CountryList class that allows you to create lists of CountrySheet, in any order, and implements the following methods:
-Determine the total number of countries;
-Determine the number of countries on a given continent;
-Given the name of a country, return its complete form, if any;
-Create a list with the names of countries with a population greater than a given value;
-Determine the list with the names of the continents of countries with a population greater than a given value;
-Determine the sum of populations on a given continent;
-Given a list of CountrySheet, for each country that exists in the list of countries, change its population with the value in the card; if it does not exist, insert the form in the list;
-Given a list of country names, remove their chips;

Ex 4: A Stack is a linear structure of the LIFO (“last in first out”) type, that is, the last element to be inserted is the first to be removed.
A stack thus has only one end for insertion and removal. Implement a Stack of names, with the usual stack operations:
- String top(): which determines the element at the top of the stack;
- void push(String s): inserts at the top;
- void pop(): removes the top element from the stack, if it is not empty;
- boolean empty(): determines whether the stack is empty;
- int length(): determines the length of the stack;

Ex 5: Each email received in a given email account is saved containing the address of the person who sent it, the date sent, the date received, the subject and the text of the email (attachments, etc. are not considered).
Create the Mail class that represents each of the emails received.
Then create a class called MailList that allows you to store all current emails in a given account, and implement the following operations on them:
- Determine the total number of emails saved;
- Save a new email received;
- Determine how many emails originate from a given address;
- Create a list containing the indexes of emails that contain a word in the subject as a parameter (regardless of its position);
- The same as the previous question, but creating a list containing such emails;
- Delete all emails received before a date that is given as a parameter;
- Create a list of the day's emails;
- Given a list of words, delete all emails that contain any of these in their subject (anti-spam);
- Delete all emails prior to a given date;

------------------------------------------------------------------------
