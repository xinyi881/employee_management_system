# Employee Management System

An Employee Management System built with Spring Boot, providing RESTful APIs for managing employees, departments, and projects.

## Table of Contents

- [Introduction](#introduction)
- [Features](#features)
- [Technologies Used](#technologies-used)
- [Installation](#installation)
- [Usage](#usage)
- [Endpoints](#endpoints)
- [Testing](#testing)
- [License](#license)

## Introduction

This Employee Management System is designed to handle the CRUD operations for employees, departments, and projects within an organization. The project utilizes Spring Boot to create a RESTful web service and JPA for persistence.

## Features

- Create, read, update, and delete employees
- Create, read, update, and delete departments
- Create, read, update, and delete projects
- Cache support for improved performance
- Validation for entity fields
- Command-line runners for initializing data
- Unit tests for service layers

## Technologies Used

- Java
- Spring Boot
- Spring Data JPA
- Hibernate
- PostgreSQL
- Spring Cache
- JUnit 5
- Mockito

## Installation

### Prerequisites

- Java 17 or later
- Maven 3.3.0 or later

### Steps

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/employee_management_system.git
   ```

2. Navigate to the project directory:
   ```bash
   cd employee_management_system
   ```

3. Build the project:
   ```bash
   mvn clean install
   ```

4. Run the application:
   ```bash
   mvn spring-boot:run
   ```

The application will start and be accessible at `http://localhost:8080`.

## Usage

The application provides RESTful endpoints for managing employees, departments, and projects. You can use tools like `curl` or Postman to interact with these endpoints.

### Endpoints

#### Employees

- GET `/api/employees` - Get all employees
- POST `/api/employees` - Add a new employee
- DELETE `/api/employees/{employeeId}` - Delete an employee by ID
- PUT `/api/employees/{employeeId}` - Update an employee by ID

#### Departments

- GET `/api/departments` - Get all departments
- POST `/api/departments` - Add a new department
- DELETE `/api/departments/{departmentId}` - Delete a department by ID
- PUT `/api/departments/{departmentId}` - Update a department by ID

#### Projects

- GET `/api/projects` - Get all projects
- POST `/api/projects` - Add a new project
- DELETE `/api/projects/{projectId}` - Delete a project by ID
- PUT `/api/projects/{projectId}` - Update a project by ID

## Testing

Unit tests are provided for the service layer using JUnit and Mockito. To run the tests, use the following command:

```bash
mvn test
```

### Example Test Classes

- `EmployeeServiceTest`
- `DepartmentServiceTest`
- `ProjectServiceTest`

## License

