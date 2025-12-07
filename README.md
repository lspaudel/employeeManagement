# Employee Management System

An effortless **Spring Boot** application to manage employees using **MongoDB**. It provides a RESTful API with full CRUD support and proper error handling.

- Create, read, update, and delete employees
- Input validation and error handling
- Consistent JSON responses for success and errors
- Request tracing using **MDC** (`requestId` and `userId`)
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
  ./gradlew bootRun    
```
4. Access the API at: `http://localhost:8080/api/employees`  
   The application supports request tracing via the X-Request-ID header. If you don't provide one, a unique ID will be generated automatically.  
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
