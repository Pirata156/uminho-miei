------------------------------------------------------------------------
This is the project README file for Worksheet 03 (Ficha Pratica 03).
Here, you'll find information about the Object-Oriented Programming (OOP) practical exercises.

PROJECT TITLE: Worksheet 03 (Ficha Pratica 03)
PURPOSE OF PROJECT: Object-Oriented Programming (OOP) classes' practical exercises for various classes.
VERSION or DATE: 23/05/2012
AUTHORS: PIRATA (https://github.com/Pirata156)
JAVA VERSION: Java 7

HOW TO START THIS PROJECT:
  1. Open the project in BlueJ.
  2. Compile each Java class.
  3. Compile Javadoc for documentation.
  4. Use BlueJ tools for testing.

USER INSTRUCTIONS:

**Exercise Overview:**

This BlueJ project is a collection of Java classes, each corresponding to a different exercise from Worksheet 03 of the Object-Oriented Programming course.
Each class addresses specific problem statements as follows:

- **Pixel**: Represents a pixel with integer x and y coordinates, capable of various operations like moving, changing color, and more.
- **Segment**: Represents a straight line segment with methods to calculate length, slope, and perform other operations.
- **Vehicle**: Represents a motor vehicle with methods to determine mileage, record trips, check for reserve, calculate fuel expenses, and more.
- **ClientCard**: Represents a Customer Card that accumulates bonus points based on purchases, with methods to deduct points, modify cardholder, and update information.
- **Rectangle**: Represents a Rectangle with methods to calculate dimensions, perimeter, area, and displace.
- **Product**: Represents a product with methods to modify quantity, name, sales price, and calculate financial information.
- **Complex**: Represents a complex number with methods for addition, multiplication, conjugate, polar representation, and more.
- **StopwatchDS**: Represents a double-split stopwatch with methods to calculate total time and differences between recorded times.
- **TermAccount**: Represents a Term Bank Account with methods to calculate interest, change account information, and close the account.

Free to explore each class, read the Javadoc documentation, and use this project as a reference for your OOP exercises.

**Notes:**
Ensure that Javadoc documentation is generated for a detailed explanation of each class and its methods.

**Author's Notes:**
Feel free to reach out to the author (PIRATA) on GitHub for any clarifications or assistance.

**Exercises complete description:**

Ex 1: A pixel is a point with integer x and y coordinates but associated with a color from 0 to 255.
Create a Pixel class that allows you to create “pixels”, knowing that, in addition to the usual query operations, each pixel must respond to the following messages:
-Move up, down, left and right of a real value;
-Change its color to a new color of given number (between 0 and 255);
-The color spectrum ranges from 0 - Black to 255 - White, with black being 0 to 2 and white being 253 to 255, between black and white are gray, blue, green, red and yellow, with the space of the remaining 250 colors divided equally between these 5 (50 for each). Example: 3 to 52 are gray and 53 to 102 are blue. Write a method that returns a String corresponding to the current pixel color.
-Don't forget the equals(), toString() and clone() methods.

Ex 2: A straight line segment is representable by two real coordinate points: the beginning and end of the segment.
Write a Segment class that implements the following methods:
-Calculate the length of the segment;
-Determine the slope of the segment, cf. (y2-y1)/(x2-x1);
-Determine whether the segment goes up or down from its beginning (return a String);
-Move the segment dx in XX and dy in the YY axis;
-If the segment is the diameter of a circle, determine its perimeter;

Ex 3: A motor vehicle is characterized by its registration number, total mileage (Km), the total number of liters in its tank (capacity), the number of liters contained in such tank (reservoir included = 10 liters).
Its average consumption per 100 km is also known, which rarely varies. The vehicle also has a trip counter.
Create a Vehicle class that implements instance methods that allow you to obtain the following results:
-Determine how many kilometers it is possible to travel with the fuel in the tank;
-Record a trip of K kilometers and update vehicle data;
-Determine whether the vehicle has already entered the reserve;
-Given an average cost per liter, calculate the total amount spent on fuel;
-Given an average cost per liter, calculate the average cost per km;
-Pour L liters of gasoline, or as much as possible < L, without overflowing.

Ex 4: A Customer Card (currently in vogue) is a shopping card that accumulates bonus points as purchases are recorded, and that has the total cash value of purchases made, an alphanumeric code and a name of the holder.
In a given establishment, the rules are as follows: for each purchase made in Euros, the customer receives a bonus number of points (integer) which is rounded down to 10% of the purchase value.
Whenever a multiple of 50 points is reached, the customer accumulates an additional 5 points for every 50, which are added to those already on the card.
Write a ClientCard class whose instances exhibit this behavior, and also allow:
-Deduct P points from the card due to withdrawing a prize;
-Modify the card holder;
-Modify the premium rate, for example from 10% to 11%;
-Download points from a card to our card;
-Insert information on a new purchase of a certain value on the card, and update data;

Ex 5: A Rectangle with a base parallel to the XX axis is representable by two points with real coordinates, which are the extremes of its diagonal.
Develop a Rectangle class with methods that perform the following operations:
-Calculate the lengths of the base, height and diagonal;
-Calculate the perimeter of the rectangle;
-Calculate the area of the rectangle;
-Displace the rectangle in XX and YY;

Ex 6: A Product from a given stock of products has the following information characteristics: code, name, quantity in stock, minimum quantity, purchase price and retail price.
Develop a Product class and the following instance methods:
-Change the quantity of a product, either by leaving or entering a given quantity of the product in stock;
-Modify the product name;
-Modify the sales price of a given product;
-Determine the total value of the quantity in stock in case of sale;
-Determine the current profit of such stock in case of total sale;
-Given an order of X units of the product, determine the total price of such an order;
-Check if a product is already below the minimum stock level.

Ex 7: A Complex number in its rectangular form is represented by z = a + bi, and has a real part and an imaginary part, with a and b being real numbers that are the real and imaginary coefficients of the number.
Another way of representing complex numbers is called polar representation and has the form z = r(cos Φ + sin Φ i), where r = sqrt( a*a + b*b), r.cos Φ = a, r .sin Φ = b, and the value of Φ = arctan b/a.
Develop a Complex class with the following instance methods:
-Calculation of the sum of two complexes z + w = (a + bi) + (c + di) = (a+c) + (b+d)i giving a new complex number as a result;
-Calculation of the product of two complexes z * w = (a + bi) * (c + di) = (ac-bd) + (bc+ad)i giving a new complex number as a result;
-Calculation of the conjugate of a complex, with conjugate (a+ bi) = a – bi;
-Calculate the polar representation of the complex and return it as a String;
-Methods equal(Complex c) and toString().

Ex 8: A stopwatch marks the differences between two recorded times (start and end).
A “double-split” stopwatch does even more: It starts counting time, makes a first stop, but continues counting time until the second stop is made.
Create a StopwatchDS class that allows you to calculate:
-The total time in minutes, seconds and milliseconds between the start and the first stop;
-The total time in minutes, seconds and milliseconds between the start and the second stop;
-The difference in times between the first and second time stops;
-Determine the absolute time in hours-min-sec-millisec of starting and each stop;


Ex 9: A Term Bank Account is created with a code, a holder, has a starting date (day, month and year) of interest counting that is updated whenever interest is calculated and added to the capital, has a given amount of capital deposited, with a period for calculating interest, and an interest rate of t% for such period, defined at the time of its creation.
Create a TermAccount class that, in addition to the constructors and query methods, allows you to perform the following operations:
-Calculate the number of days passed since account opening;
-Change the account holder or change the interest rate;
-Once the interest deadline has been reached, calculate such interest, add it to the capital, and record the new interest calculation date;
-Check if today is the day to calculate interest;
-Close the account by calculating the total amount payable to the holder (initial capital + interest);

------------------------------------------------------------------------