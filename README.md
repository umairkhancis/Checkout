## Checkout REST API 

## Run the app:
`./gradlew run`

## Libraries used:
 - [Ktor](https://github.com/ktorio/ktor) - Kotlin async web framework
 - [Netty](https://github.com/netty/netty) - Async web server
 - [Exposed](https://github.com/JetBrains/Exposed) - Kotlin SQL framework
 - [H2](https://github.com/h2database/h2database) - Embeddable database
 - [HikariCP](https://github.com/brettwooldridge/HikariCP) - High performance JDBC connection pooling
 - [Jackson](https://github.com/FasterXML/jackson) - JSON serialization/deserialization
 - [JUnit 5](https://junit.org/junit5/), [AssertJ](http://joel-costigliola.github.io/assertj/) and [Rest Assured](http://rest-assured.io/) for testing
 
 ## APIs:
Request 
```
POST /checkout  
["001", "002", "001", "004", "003"]
```
*Response*: 

`{ "price" : 360 }`

## Testing
100% test coverage by **Unit Tests** for service layer, usecase layer, repository layer and 
**Integration Tests** from the api controller layer.
