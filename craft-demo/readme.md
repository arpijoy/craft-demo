## Used ThymeLeaf + HTML 5+ AJAX + Stomp +JS + BootStrap + JPA + Hibernate + MySQL in Spring Boot

### Usage

- Run the application and go on http://localhost:8090/
- Using the above url you will be able to go to Login (Only 2 Users supported: JackFrost/JackFrost, Dori/Dori)
- Use the following urls to invoke Rest controllers APIs and see the interactions
  with the database:
    * `rest`: create a new Brown Bag Session with an 
      auto-generated id and email and name as passed values.

### Build and run

#### Configurations

Open the `application.properties` file and set your own configurations for the
database connection.

#### Prerequisites

- Java 8
- Maven 3

#### From terminal

Go on the project's root folder, then type:

    $ mvn spring-boot:run

#### From Eclipse (Spring Tool Suite)

Import as *Existing Maven Project* and run it as *Spring Boot App*.
