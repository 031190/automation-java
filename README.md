# automation-java
This an API automation testing framework

Run tests command example:
- for cucumber: 

mvn clean test -P local -Denv=qa -Dcucumber.filter.tags="@PET"
- for testng:
mvn clean test -P local -Denv=qa -Dgroups=smoke

Preconditions:

- java 17

- maven 3.9

- restAssured 5.5

- testNG 7.10

- Cucumber 7.20
- allure report