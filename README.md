
 This is a Spring Boot application developed using Java 8.
 
 
 [Springboot has all the support for developing from a simple REST to complex Micro service project. I used start.spring.io to generate the 
 project template with proper depencencies.] 
 
 If you are not cloning the Github repository, you need to install Java 8 and maven on your local machine. Then perform  the following steps:
 
 1. Unzip the file
 
 2. Navigate to the root of the project where pom.xml file is there
 
 3. Then run mvn clean install
 
 4. It will generate a jar file under target folder.
 
 5. Navigate to the 'target' folder and run the following command:
 
    java -jar <name of the jar file>
    
 6. Open a browser and hit the following urls:

    http://localhost:8080/api/shapes = will get all the shapes in json format

    In order to generate a new shape, please use the following request:

    http://localhost:8080/api/shapes with a POST request
    
    You can use POSTMAN tool to create a post request
    there you have to pass :
    
    key = type
    value = square
    
    key = descriptor
    value = 10,10,20,10,20,20,10,20
    
    You will get a response in json
    
    
    Database
    ========
    This application uses h2 as in-memory database which means that once the applcation is restarted the data from
    the table will be erased.
    
    You can login into the application using the following link:
    http://localhost:8080/h2/login.do?jsessionid=49d6b083eb85fa2dc14442fc9e0bf19f
    This will open up a login page and just click on the connect button. It will open a web sql editor
    
    Why h2 was selected?
    
    [H2 is an in-memory database. There is no need of any database to be installed on the local PC. 
    However, once the application is stopped, then the database is no longer accessible.]
    
    Development Tool:
    =================
    I have used Eclipse to develop the project. 
    
    Maven Build Tool
    ----------------
    
	[Maven is very flexible build tool. It is for dependency management. Most of the project dependencies are readily
    available in the maven central repository (which is a remote server).]
    
    Project Libraries
    =================
    
    1. Spring Boot web component

       This component gives an embedded tomcat server and apis for exposing REST endpoints.
       
    2. Spring Boot H2 component

       This component create an in-memory database automatically when the application starts. There is no
       need for other database servers. 
       
    3. Spring Boot unit test component
       Spring boot is uses the JUnit framework.
       