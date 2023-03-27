# Platform Science SDET Assignment

## API Testing Framework

### Introduction

API Testing framework built in Java with Cucumber and Rest Assured.

### Approach
#### Dependencies used

- Java (OpenJDK v.19.0.2, JRE Temurin-19.0.2+7)  
- Apache Maven 3.9.0  
- JUnit 5  
- Cucumber 7  
- RestAssured 5

#### Development

The framework was developed with a Maven project, added the dependencies for JUnit, Cucumber and RestAssured.
CucumberTestRunner.java class was made as the Cucumber test runner, joining the classes under org.example.steps as step 
definition classes. Utility classes were developed for handling API calls, reading property files and setting up RestAssured
configuration. And model classes such as Room.java and Roomba.java were developed to have a simpler way to handle the json
parameters needed by the API.

Feature files for the Roomba cleaning tests are structured in steps including tables for coordinates and directions, 
parameters needed are set per step i.e.

```
Given Roomba is in position "1","2"
And Room size is "5" by "5"
When Room has dirt patches in positions
  | x | y |
  | 1 | 0 |
And Roomba follows the directions
  | N |
  | N |
  | E |
```

The result of that setup would be 

```json
{
  "roomSize" : [1, 2],
  "coords" : [5, 5],
  "patches" : [
    [1, 0]
  ],
  "instructions" : "NNE"
}
```

### Instructions

Run `mvn clean install` to download the dependencies needed for the project.

After that run this command on the root directory of the project, after the execution is completed, a link to the Cucumber report
will be displayed.

```shell
mvn clean test -Dcucumber.features=src/test/java/org/example/features
```

### Assumptions

Assumptions were made following clean code standards and API good practices such as having better error handling and 
descriptive exception messages.Assuming as well that since the Roomba is always on it should clean dirt wherever it's
hovering over, even if it's the starting point. 

### Report

The full Cucumber report will be linked after the execution is completed but here's an overview of the tests executed where some issues found were these:  
- Room size and Roomba position errors do not return body in response, requests with different object types do return
body but not real error message.
- No errors return an error description or comprehensive message.
- Roomba returned one patch cleaned when all patch positions were outside room boundaries (using directions: NNESEESWNWW).
- Roomba returned 1 patch cleaned when no patches were added (using directions: NNESEESWNWW).
- Roomba returns one patch cleaned when it passes across same coordinate (not when it goes through several already visited coordinates tho).
- (Possibly a bug since the Roomba it's always on it should clean on the position it's on.) Roomba doesn’t suck dirt when it is placed on starting position.
- On Monday responses started returning bigger amounts of dirt picked than expected.
