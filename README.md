# Wages Counter

People who work different number of hours per week often pay different tax and insurance contributions. It often happens
 that a few months after the beginning of the financial year, an employee may not know what percentage he paid and 
 whether he can expect that part of the tax paid will be refunded at the end of the year. 
 Wages Counter App helps you keep accurate hours and automatically calculates taxes and ACC deductions for each work week. 
 The App also allows you to generate an annual report to find out how the amount of taxes paid differs from the correct amount 
 based on the total annual income.
 
Stored data can be updated and deleted by user

**Current working version of the application deployed on AWS and available to review [at this link](http://wagescounter-env.eba-8ma2t9ue.ap-southeast-2.elasticbeanstalk.com/)**

## Description

The application stores working hours worked by each employee per week.
 
The following parameters are calculated automatically for each employee:
* Total working hours
* Gross earnings
* Tax paid
* ACC Levy
* Total Earnings

## What's inside

Project is based on the Spring Boot framework and includes the following technologies:
* Maven
* Spring Core
* Spring Data JPA
* Tomcat
* MySQL
* JSP
* JUnit

Current configuration uses H2 database, if you want to use MySQL instead you need to change corresponding settings in application.properties file

## Steps to run the application on a local machine using H2 in-memory database:
### 1. Use following URL to create a new project from version control in Intellij Idea:
```
https://github.com/Dyegor/WagesCounter.git
```
### 2. Right click on project in Idea and then Maven -> Generate Sources and Update Folders

### 3. Update database credential and other configuration in src/main/java/resources/application.properties 
```
spring.datasource.url=jdbc:h2:mem:wages_db
spring.datasource.driverClassName=org.h2.Driver
spring.datasource.username=
spring.datasource.password=
spring.jpa.database-platform=org.hibernate.dialect.H2Dialect

spring.h2.console.settings.trace=false
spring.h2.console.settings.web-allow-others=true

spring.h2.console.enabled=true

spring.jpa.hibernate.ddl-auto=create-drop

spring.mvc.view.prefix=/WEB-INF/jsp/
spring.mvc.view.suffix=.jsp
```
MySQL and web deployment configurations also available in a file

### 4. Right click on Application.java file -> Run as Java Application

### 5. Once application started index page will be available at:
```http://localhost:8080/index.jsp```

### 6. In order to create executable jar change following in pom.xml:
```<packaging>war</packaging>``` 
to
```<packaging>jar</packaging>```

and then run from terminal:
```mvn clean package```

# Screenshots:
Adding hours form:

![1](https://user-images.githubusercontent.com/18030933/89379895-bf953580-d74a-11ea-8d14-7c3ebb8d2175.jpg)

Caclulated weekly payslip:

![2](https://user-images.githubusercontent.com/18030933/89379985-e8b5c600-d74a-11ea-896f-b1808e42d517.jpg)

Saved timesheet:

![3](https://user-images.githubusercontent.com/18030933/89379995-ed7a7a00-d74a-11ea-8015-f56ecaa80d07.jpg)

AnnualReport:

![4](https://user-images.githubusercontent.com/18030933/89504650-78777500-d81c-11ea-8e86-4f01f3d02fd5.jpg)


