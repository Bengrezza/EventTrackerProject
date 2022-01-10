# UnTapped - The World's Finest Beer Tracker

## REST API Weekend Project

### Skill Distillery - January 10, 2022

Built by:

-   Benjamin Allegrezza

This was a weekend project to exceed the minimum viable product for our Event Tracker [assignment](https://github.com/SkillDistillery/SD31/tree/master/rest/EventTracker).

### Overview:

This program is designed to be a REST API that creates, reads, updates and deletes user and beer data from a database built using MySQL Workbench and connecting with using Spring Data JPA.


### Routes

| Return Type      | Route                                         | Functionality                                  |
| ---------------- | --------------------------------------------- | ---------------------------------------------- |
| `List<Beer>`     | `GET api/beers`                                   | Get all beers                              |
| `Beer`           | `GET api/beers/{id}`                              | Get one beer by id                                 |
| `Beer`           | `POST api/beers`                                   | Create beer                               |
| `Beer`           | `PUT api/beers/{id}`                              | Update a beer by id                                 |
| `Void`           | `DELETE api/beers/{id}`                           | Delete a beer by id                                 |
| `List<Beer>`     | `GET api/beers/alcoholic`                         | Get all alcoholic beers                              |
| `List<Beer>`     | `GET api/beers/{min}/{max}`                       | Get all beers by min and max alcohol mg's                       |
| `List<Beer>`     | `GET api/beers/name/{keyword}`                    | Get all beers by keyword search of name                            |
| `List<Beer>`     | `GET api/beers/date/{date}`                       | Get all beer by date (yyyy-MM-dd)                                |
| `List<User>`     | `GET api/users`                                   | Get all users                              |
| `User`           | `POST api/users`                                  | Create user                               |
| `User`           | `PUT api/users/{id}`                              | Update a user by id                                 |
| `Beer`           | `POST api/users/{id}/beers`                           | Create beer for user by id                                 |
| `Void`           | `DELETE api/users/{userId}/beer/{beerId}`         | Delete a beer by user id and beer id                            |

### Technologies used:

Java, Java Persistence API, REST API, Spring Boot, Gradle, MySQL Workbench, Postman, JSON, Apache, Tomcat, AWS, Atom, Git, and GitHub.

### Topics implemented:

-   Spring REST annotations.

-   Spring Data JPA to perform all CRUD operations.

-   Send and receive JSON.

-   Tomcat 8 on AWS EC2 Instance.

-   Object-Relational Mapping (ORM).

-   Relational Database: SQL Database creation using MySQL Workbench.

-   Object-Oriented Programming in Java: Abstraction, Polymorphism, Inheritance, and Encapsulation.

-   Test Driven Development using JUNIT Juniper.

### Lessons learned:

This project helped me grasp the capabilities of REST API and allowed me to learn how to:

-   Create a JPA Project

    -   Create a Java entity class POJO(Plain Old Java Object) that models your database table.
    -   Map your POJO using JPA.

-   Configure a Spring Boot application that published a REST API.
    -   Use Spring REST annotations.
    -   Use Spring Data JPA to perform all CRUD operations.
    -   Send and receive JSON.
