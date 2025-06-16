## Banana Farm Management

A Java console-based application to manage and store banana variety information, including name, season, growth duration, and soil pH requirements. This project uses JDBC to connect to a database securely and perform CRUD operations.

## Features

- Add new banana varieties with detailed attributes
- Store and retrieve data from a relational database using prepared statements
- Input validation and exception handling for database operations
- Easily extensible for future enhancements like GUI or web interface

## Technologies Used

- Java 8+
- JDBC (Java Database Connectivity)
- MySQL/PostgreSQL/SQLite (you can specify your database)
- Maven/Gradle (optional - specify if you use)

## Getting Started

### Prerequisites

- Java Development Kit (JDK) installed
- Database server installed and configured
- IDE (Eclipse, IntelliJ IDEA, VSCode) recommended

### Setup

1. Clone the repository:
   ```bash
   git clone https://github.com/Manickam717/Banana-Farm-Management.git
   ```
2. Configure your database connection in the `getConnection()` method inside your project.
3. Create the required database and table using the following SQL schema:
   ```sql
   CREATE TABLE banana_varieties (
       id INT AUTO_INCREMENT PRIMARY KEY,
       name VARCHAR(100) NOT NULL,
       season VARCHAR(50),
       growth_duration INT,
       min_soil_ph DOUBLE,
       max_soil_ph DOUBLE
   );
   ```
4. Build and run the project.

## Usage

Add banana variety objects via the application, and they will be saved securely in your database.

## Contributing

Feel free to fork this repo, create feature branches, and submit pull requests.
