**#Excercise:**
Build a Book catalogue microservice that allows consumer to do the following: 

•	Add a book with Title, Author(s), 13-digit ISBN, and Publication Date 

•	Delete a book 

•	Update a book 

•	List books with search criteria - title, author or by ISBN 

**Solution**
- Microservice developed in Spring Boot with CRUD Operations: POST, GET, PUT and DELETE
- Junit Test cases added for Controller to test all the operations.
- Swagger API documentation added

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
- You can test API in Swagger or Post Man (install only if required)

**To Create Book** : 
Provide below JSON Request Object sample and click on Try it out. You can see sample success response. 
On the same request, you can remove "title" and check - you will be surprised - Request is rejected now - as title is required for a book :) (http://localhost:8080/cba-books/createBook)
 {
  "authors": [
    "William", "Gerald"
  ],
  "isbnNumber": "1234512345123",
  "publishedDate": "20/01/2018",
  "title": "Think Positive - Success is yours!"
}

**To Update a book**:
Lets update published date on the book that we have created & click Try. Yes - now book is updated!! (http://localhost:8080/cba-books/updateBook)
 {
  "authors": [
    "William", "Gerald"
  ],
  "isbnNumber": "1234512345123",
  "publishedDate": "25/01/2018",
  "title": "Think Positive - Success is yours!"
}

**Get Books by Title or Author or ISBN and see the details** 
- Enter value as "Think" and hit Try - your book details will be retrieved. (http://localhost:8080/cba-books/getBooks/Think)
 
**Delete a book by its Title**
- Enter value as "Think" and hit Try - your book is deleted now from Catalogue (http://localhost:8080/cba-books/deleteBook/Think)

