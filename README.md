# Employee Management System

An effortless **Spring Boot** application to manage employees using **MongoDB**. It supports basic CRUD operations.

- Create, read, update, and delete employees
- Error handling for missing employees
- RESTful API design

---

## Setup

1. Clone the repo:  
```bash
git clone https://github.com/lspaudel/employeeManagement.git
cd employeeManagement
```
2. Make sure MongoDB is running locally or update application.yaml with your MongoDB URI.

3. Run the project:
 ```bash
  ./gradlew bootRun        # for Gradle
```
4. Access the API at: `http://localhost:8080/employee`
   
   Example JSON:
   ```bash
     {
    "id": "1",
    "firstName": "Hari",
    "lastName": "Gautam",
    "age": 50
   }
   ```
