**#Excercise:**
Build a Book catalogue microservice that allows consumer to do the following: 

•	Add a book with Title, Author(s), 13-digit ISBN, and Publication Date 

•	Delete a book 

•	Update a book 

•	List books with search criteria - title, author or by ISBN 

**Solution**
- Microservice developed in Spring Boot with CRUD Operations: POST, GET, PUT and DELETE

**Prerequisites**:
   - Install Java 1.8 JDK
   - Download Eclipse for project set up
   - Install Maven 3 or higher version
   - Install Git Hub if you want to try curl - otherwise you can skip.

**Project set up (Developers)**
- Download code from Development branch and copy it to C:/
- Import the project in eclipse
- Set up Environment variable JAVA_HOME which should point to JDK installed - for example: set JAVA_HOME=C:\Program Files\Java\jdk1.8.0_291

**Run Project:**
- Open Command Prompt and navigate to C:\cba-book-catalogue
- Enter : mvnw spring-boot:run
- This will start the application and once its started, application will run on port 8080 (with default configuration). If you need to change, provide the port in application.properties.

**Test Api**
- Open the browser and enter http://localhost:8080/cba-books/swagger-ui.html
