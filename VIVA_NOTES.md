# Viva / Sir Questions — short answers

Q: What is the project?
A: A Corporate Financial Expense Analytics and Budget Reconciliation System. It records expenses, manages categories, generates reports and compares spending with the approved budget.

Q: Why microservices?
A: Expense, Category and Report are separate business capabilities. They can be developed, deployed and scaled independently.

Q: What is Eureka?
A: Eureka is the service registry. Services register themselves and clients can discover service instances without hard-coding their host/port.

Q: What is API Gateway?
A: It is the single entry point for the client. It routes requests to the correct microservice and is a suitable place for cross-cutting concerns such as authentication.

Q: What is JWT?
A: JSON Web Token is a signed token issued after login. The client sends it as Authorization: Bearer <token>; the gateway validates it before forwarding protected requests.

Q: What is load balancing?
A: If multiple instances of a service are registered, the client-side load balancer can distribute requests among available instances.

Q: How do services communicate?
A: The Report Service calls the Expense Service using a service-discovery name (EXPENSE-SERVICE). This avoids hard-coded service addresses.

Q: Where is the database?
A: This review build uses in-memory storage so it can run easily. The production design can replace the repositories with MySQL/PostgreSQL.

Q: What did you use?
A: Java 17, Spring Boot, Spring Cloud Gateway, Eureka, JWT/JJWT, Maven, REST APIs, HTML/CSS/JavaScript, and Docker as the deployment model.

Q: How do you test?
A: Unit tests cover calculations/validation and integration tests should verify JWT, gateway routing and inter-service communication.

Q: What is DTI?
A: Digital Transformation and Innovation. The project applies modular digital services to improve visibility, automation and reconciliation of corporate expenses.
