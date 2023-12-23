------------------------------------------------------------------------
This is the project README file for Worksheet 05 (Ficha Pratica 05).
Here, you'll find information about the Object-Oriented Programming (OOP) practical exercises.

PROJECT TITLE: Worksheet 05 (Ficha Pratica 05)
PURPOSE OF PROJECT: Object-Oriented Programming (OOP) classes' practical exercises for various classes.
VERSION or DATE: 23/04/2012
AUTHORS: PIRATA (https://github.com/Pirata156)
JAVA VERSION: Java 7

HOW TO START THIS PROJECT:
  1. Open the project in BlueJ.
  2. Compile each Java class.
  3. Compile Javadoc for documentation.
  4. Use BlueJ tools for testing.

USER INSTRUCTIONS:

**Exercise Overview:**

This BlueJ project is a collection of Java classes, each corresponding to a different exercise from Worksheet 05 of the Object-Oriented Programming course.
Each class addresses specific problem statements as follows:

- **Product**: Represents a product with methods to modify quantity, name, sales price, and calculate financial information.
- **Company**: Represents a company with operations to manage employees, compute total salaries, and other related tasks.
- **CapitalSheet**: Represents a capital sheet with methods for calculating total assets, liabilities, and equity.
- **Countries**: Represents a list of countries with operations to find the country with the highest GDP, update population information, and more.
- **TermAccount**: Represents a Term Bank Account with methods to calculate interest, change account information, and close the account.
- **Bank**: Represents a bank with functionalities to manage accounts, compute total interest, and other banking operations.
- **Mail**: Represents an email received in a given account with attributes like sender, dates, subject, and text.
- **MailMap**: Associates all received mail with each sending address and provides various operations to manage and query the email data.

Free to explore each class, read the Javadoc documentation, and use this project as a reference for your OOP exercises.

**Notes:**
Ensure that Javadoc documentation is generated for a detailed explanation of each class and its methods.

**Author's Notes:**
Feel free to reach out to the author (PIRATA) on GitHub for any clarifications or assistance.

**Exercises complete description:**

Ex 1: Develop a Product class that represents the basic information of a product in a given company's warehouse.
About each product the following information is intended:
private String code; // code
private String name; // description
private int quantity; // quantity in stock
private int alarm; // minimum alert – minimum acceptable value

Then create a Company class containing the name of the company in question and a representation of the company's stock,
associating each product code with its information sheet.
In addition to the usual constructors and methods, the Company class must also define the following instance methods:
- Method that returns all current product codes in stock;
- Method that inserts a new product into the company’s stock;
- Method that removes the product with the given code from stock;
- Method that changes the quantity of a product in stock by a given value;
- Method that returns the total quantity of products in stock;
- Method that checks whether the given code product exists;
- Method that creates a list with product codes with quantity <= alarm;
- Method that returns a copy of the stock;
- Method that returns information about a given code product;

Ex 2: Create a Countries class that establishes a correspondence between the name of a given country and information about its capital (CapitalSheet),
namely: name of the city, population, number of vehicles, average salary (real) and cost of living average monthly (real).
Implement the following methods in the Countries class:
- Determine the total number of countries;
- Return the names of countries with capitals with populations above a given value;
- Given the name of a country, return a complete form of its capital;
- Change the population of the capital of a given country;
- Insert information about a new country;
- Create a list with the names of all registered capitals;
- Determine the sum of all capital populations;
- Given a Map of country name for CapitalSheet, for each country that exists in the list of countries change its capital form and for each new country insert its information.
- Given a set of country names, remove their capital tokens;

Ex 3: Considering the TermAccount class previously developed, now create a Bank class that associates a Term Account with each account code.
The Bank class must implement methods that perform the following operations:
- Enter a new account;
- Determine the set of account codes belonging to a given holder;
- Same as the previous one but for a set of holder names;
- Determine the codes of accounts with capital greater than a given value;
- Create a Map of accounts with an interest rate higher than a given value;
- Set of account codes that earn interest today;
- Given a list of account codes increase their interest rates by an amount X;
- Return the names of all account holders;
- Create a Map that associates the total value of capital invested in their various accounts with each existing holder name (use auxiliary methods).

Ex 4: Each email received in a given email account is saved containing the address of the person who sent it,
the date sent, the date received, the subject and the text of the email (attachments, etc. are not considered).
Create a MailMap class that associates all received mail with each sending address (cf. Mail class) and implements the following operations:
- Determine the total number of addresses from which mail was received;
- Save a new email received;
- Determine how many emails originate from a given address;
- Create a list containing all addresses that sent emails containing in their subject a list of words given as a parameter;
- The same as the previous question, but creating a set containing the emails;
- Delete all emails received before a date that is given as a parameter;
- Create a list of addresses that sent emails today;
- Given a list of words, delete all emails from a given address that contain any of these in their subject (anti-spam);
- Delete all emails prior to a given date;
- Create a list with all email addresses from Portugal;

------------------------------------------------------------------------