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
    curl -X POST http://localhost:8080/api/employees \
    -H "Content-Type: application/json" \
    -H "X-Request-ID: 123e4567-e89b-12d3-a456-426614174000" \
    -d '{
    "firstName": "Laxman",
    "lastName": "Paudel",
    "age": 39,
    "email": "laxman.paudel@test.com",
    "phoneNumber": "9845284711",
    "department": "IT",
    "salary": 10000.00
    }'
    ```
   ```bash
    curl -X GET http://localhost:8080/api/employees \
         -H "X-Request-ID: 123e4567-e89b-12d3-a456-426614174000"
   ```
5. Swagger / OpenAPI Documentation

It also integrates Swagger UI using `springdoc-openapi-starter-webmvc-ui` version 2.7.0. All REST endpoints under `/api/employees` are automatically documented and can be tested via the Swagger UI at `http://localhost:8080/swagger-ui/index.html`.