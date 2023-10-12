## Tienda Java Application
![_15a1efb7-8154-43bb-8183-25b4d3fd5ad9](https://github.com/Luishervic/Tienda-JDBC/assets/83683548/b59d5712-b0b3-4f53-80f0-8adf9ef9464c)


This Java application, Tienda, serves as a store management system allowing users to query product information from a MySQL database. It employs the Java language, MySQL database, and JDBC for database operations.

### Prerequisites

- Java 8
- MySQL database
- Intellij IDEA or any Java development environment
- MySQL JDBC Driver

### Getting Started

1. Clone this repository to your local machine.
2. Import the project into your Java development environment.
3. Set up the MySQL database and run the `tienda.sql` script to create the required tables.\
`src/main/resources/scripts-sql/tienda.sql`
4. Configure your database connection.
5. Ensure you have the MySQL JDBC Driver library added to your project.

### Project Structure

The project is organized into three main packages:

#### 1. `model` Package
- `Product.java`: Represents a product with attributes like code, name, price, and manufacturer code.
- `Manufacturer.java`: Represents a manufacturer with attributes like code and name.

#### 2. `dao` Package
- `ConnectionDB.java`: An abstract class for database connection management.
- `ManufacturerDAO.java`: An interface defining methods for Manufacturer data access.
- `ProductDAO.java`: An interface defining methods for Product data access.
- `ManufacturerDAOImpl.java`: Implements `ManufacturerDAO`, providing methods to interact with manufacturer data.
- `ProductDAOImpl.java`: Implements `ProductDAO`, providing methods to interact with product data.

#### 3. `app` Package
- `Main.java`: The main application class that handles user interactions and menu options for querying, inserting, and updating products and manufacturers using the DAO pattern.

## Instructions
1. Inport the new Java project in your IDE.
2. Ensure you have a MySQL database with the `tienda` schema.
3. Update the database connection details (URL, username, and password) in `ConnectionDB.java`.
4. Run the `Main` class to interact with the application.


### Available Operations

This application provides the following operations to query the database:

1. List all product names in the `producto` table.
2. List product names and prices from the `producto` table.
3. List products with prices between the ranges that you provide.
4. List all laptops from the `producto` table.
5. Find and list the cheapest product's name and price.
6. Insert a new product into the database.
7. Insert a new manufacturer into the database.
8. Edit a product in the database with user-specified data.

### Usage

Run the application and choose the operation you'd like to perform from the menu. Follow the on-screen instructions to complete the operation.

### Improvements and Future Work

- Implement error handling and user confirmation messages for better usability.
- Optimize SQL queries for improved performance.
- Enhance the user interface for a more user-friendly experience.
