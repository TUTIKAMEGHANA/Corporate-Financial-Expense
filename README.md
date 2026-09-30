# Corporate Financial Expense Analytics & Budget Reconciliation
## Review-1 working source package

Technology stack:
- Java 17
- Spring Boot 3.3.5
- Spring Cloud 2023.0.3
- Spring Cloud Gateway
- Netflix Eureka
- JWT (JJWT)
- Maven
- REST APIs
- In-memory storage for the review/demo build
- HTML/CSS/JavaScript frontend

Services:
1. eureka-server : 8761
2. auth-service : 8081
3. category-service : 8082
4. expense-service : 8083
5. report-service : 8084
6. api-gateway : 8080

Architecture:
Browser -> API Gateway -> Auth / Expense / Category / Report
                         |
                      Eureka

The report service calls Expense Service and Category Service using service discovery.
The gateway validates JWT before forwarding protected API calls.

## Prerequisites
- JDK 17+
- Maven 3.9+
- Internet access the first time Maven downloads dependencies.

## Run
Open six terminals from this folder and run:
mvn -f eureka-server/pom.xml spring-boot:run
mvn -f auth-service/pom.xml spring-boot:run
mvn -f category-service/pom.xml spring-boot:run
mvn -f expense-service/pom.xml spring-boot:run
mvn -f report-service/pom.xml spring-boot:run
mvn -f api-gateway/pom.xml spring-boot:run

Then open:
http://localhost:8080

Demo login:
admin / admin123

The frontend is inside api-gateway/src/main/resources/static/index.html.

## API examples

Login:
POST http://localhost:8080/auth/login
Content-Type: application/json
{"username":"admin","password":"admin123"}

Add expense:
POST http://localhost:8080/api/expenses
Authorization: Bearer <token>
Content-Type: application/json
{"description":"Business Travel","category":"Travel","amount":12000}

Categories:
GET http://localhost:8080/api/categories

Report:
GET http://localhost:8080/api/reports/summary

## Important for the review
This is a working educational/demo implementation. Storage is in memory so it is easy to run for a review. A production version should use a persistent database, externalized secrets, stronger validation, HTTPS, refresh tokens, centralized configuration, monitoring and automated CI/CD.
