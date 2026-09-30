@echo off
echo Start these commands in separate terminals:
echo mvn -f eureka-server/pom.xml spring-boot:run
echo mvn -f auth-service/pom.xml spring-boot:run
echo mvn -f category-service/pom.xml spring-boot:run
echo mvn -f expense-service/pom.xml spring-boot:run
echo mvn -f report-service/pom.xml spring-boot:run
echo mvn -f api-gateway/pom.xml spring-boot:run
echo.
echo Then open http://localhost:8080
pause
