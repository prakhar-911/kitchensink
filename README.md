# kitchensink-springboot
1. Create a new project as per the spring boot architecture.
2. Check all the business need including the controllers, services, repositories, models
3. Create new Controllers as per the Spring boot using RestController
4. Write new services and implement as per the existing business logic.
5. Create new Repositories and Models as per the need.

## Note
Only Back-end has been created into the new project and API testing is done by the Postman.

## Some Structrual Changes
1. New KitchensinkApplication.java has been created to start the back-end application as standalone.
2. application.properties file has been added to provide database connection details, For information MongoDB database is being utilized and MongoDB Compass is being used to visualize the data into database.
3. Dependecies related to MongoDB has been added and MongoDB setup is done locally.
4. All REST APIs inluding GET,POST,DELETE is clubbed into one RestController only to reduce the clutter.
5. Custom Exceptions has been added to give user more specific error messages.
6. Native queries are avoided and JPA queries are being utilized for more clean coding.
7. Test cases has been written using Junit and Mockito framework.

## Build
mvn clean install

## Run the application
Run the new KitchensinkApplication.java class as a Java Application into the IDE

OR mvn spring-boot:run can be utilized (Make sure to set all the required environment paths JAVA_HOME, MAVEN_HOME) and provide the run plugin into the pom.xml

