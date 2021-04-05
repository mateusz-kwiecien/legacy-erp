# legacy-erp [![mateusz-kwiecien](https://circleci.com/gh/mateusz-kwiecien/legacy-erp.svg?style=shield)](https://app.circleci.com/pipelines/github/mateusz-kwiecien/legacy-erp?branch=master)

**Legacy ERP** is a simple Java project, just for learning and practicing purposes. There has been
used mostly legacy technologies like Spring MVC

## Technologies

* Java 11
* Gradle
* Spring Boot, MVC, JPA
* Hibernate
* H2 database
* Thymeleaf
* JUnit 5

## Code coverage

In this project I have been used the JaCoCo plugin, with following parameters :
* Instructions cover ration : 90%
* Lines cover ration : 90%
* Methods cover ration : 90%
* Classes cover ration : 90%

## Roadmap

#### TODO :

* Add GitHub Actions for code coverage badge
* Update department creating view (assign manager by list, not id input)
* Add department info to employee view  
* Add more data in Employee model
* Add pagination to lists (e.g. employees list)
* Add sorting option for lists
* Improve app view style
* Add manual for local run with other db
* Release first version on Heroku

#### DONE :
* ~~Add validation handling for creating employee~~
* ~~Add validation handling for updating employee~~
* ~~Add Data Population Service~~
* ~~Add Departments and Managers~~
* ~~Add department assignment option for employee update operation~~
* ~~Add update and delete department controllers~~
* ~~Create department view, with number of employees~~
* ~~Fix deleting employees assigned to department~~
* ~~Add and configure JaCoCo plugin~~