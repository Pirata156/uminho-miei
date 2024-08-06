------------------------------------------------------------------------
This is the project README file for Transitarios POO Project.
Here, you'll find information about the Object-Oriented Programming (OOP) practical project related to managing a (ficticious) transportation company.

PROJECT TITLE: Transitarios POO
PURPOSE OF PROJECT: OOP practical group project for managing a transports company.
VERSION or DATE: 2012.05.18
AUTHORS: PIRATA (https://github.com/Pirata156)
JAVA VERSION: Java 7

HOW TO START THIS PROJECT:
1. Open the project in BlueJ.
2. Compile each Java class.
3. Compile Javadoc for documentation.
4. Use BlueJ tools for testing.
5. Run the Main class.

USER INSTRUCTIONS:

**AUTHOR'S NOTE:**
This is an unfinished academic project. I might conclude it for fun eventually.

**Project Overview:**

The "Transitarios POO" project aims to develop a software application for managing the critical information required for the smooth operation of the cargo transport company "Transpost All That is Cargo".
The project involves simulating the company's operations, including managing a diverse fleet of vehicles, various types of cargo, commercial products, and customer information.
The project encompasses key OOP concepts such as inheritance, polymorphism, interfaces, exceptions, and file handling.

The main classes and interfaces in the project include:
- Vehicle and its subclasses (Truck, CargoVan, ReeferVan)
- Cargo and its CargoType
- CommercialProduct and its subclasses (SimpleService, ToAirport, ForDearIMovedHouse, ForProfessionalDistribution, ForSecretServices)
- Client class
- Exception classes for handling specific errors
- TransLEI class for the entry point of all operations
- Main class for textual GUI and running the application

**Project Features:**

1. **Vehicle Class and Subclasses:**
   - Represents the fleet of vehicles, each with unique characteristics.
   - Includes Trucks, CargoVans, and ReeferVans with additional attributes and methods specific to each type.
   - Common attributes: vehicle identification (license plate), cost per kilometer, useful load capacity.

2. **Cargo Class:**
   - Represents different types of cargo: Perishable, Toxic, and Undifferentiated.
   - Each type of cargo has specific handling requirements, such as refrigeration needs and compatibility with other cargo types.

3. **CommercialProduct Class and Subclasses:**
   - Encapsulates the various commercial products offered by the company.
   - Includes products like SimpleService, ToAirport, ForDearIMovedHouse, ForProfessionalDistribution, and ForSecretServices, each with specific service attributes and pricing strategies.

4. **Customer Class:**
   - Manages customer information, including personal details, and service history.
   - Supports both individual and corporate customers.

5. **TransLEI Class:**
   - Entry point for all operations obtained from the UI.
   - Provides methods for obtaining business operation reports and managing the fleet.

6. **Exception Handling:**
   - Custom exception classes to handle specific error scenarios, ensuring robust and error-free application behavior.

7. **File Handling:**
   - Methods for saving and loading the application state, allowing persistent storage and recovery of data.

**How to Test:**
- Open the Main class and run the main method to test the overall functionality of the application.

**Notes:**
Ensure that Javadoc documentation is generated for a detailed explanation of each class, method, and interface. (Not fully documented.)

**Author's Notes:**
Feel free to reach out to the author (PIRATA) on GitHub for any clarifications.

By implementing these features, the "Transitarios POO" project provides a comprehensive simulation of a cargo transport company's operations, emphasizing OOP principles and robust application design.
------------------------------------------------------------------------
