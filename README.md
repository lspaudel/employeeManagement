# Employee Management System

An effortless **Spring Boot** application to manage employees using **MongoDB**. It provides a RESTful API with full CRUD support and proper error handling.

- Create, read, update, and delete employees
- Input validation and error handling
- Consistent JSON responses for success and errors
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
4. Access the API at: `http://localhost:8080/employees`
   
   Example JSON:
   ```bash
    { 
   "firstName": "Laxman",
   "lastName": "Paudel",
   "age": 39,
   "email": "laxman.paudel@test.com",
   "phoneNumber": "9845284711",
   "department": "IT",
   "salary": 10000.00
   }
   ```
