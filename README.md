# Quarkus REST API Example

A simple RESTful API for managing Person records, built with Quarkus framework.

## Features

- RESTful CRUD operations on Person entities
- Pagination support for listing records
- UUID-based identification
- OpenAPI/Swagger documentation
- PostgreSQL database persistence

## API Endpoints

| Method | Path | Description |
|--------|------|--------------|
| POST | /people | Create a new person |
| GET | /people | List all people (paginated) |
| GET | /people/{id} | Get person by ID |
| PUT | /people/{id} | Update a person |
| DELETE | /people/{id} | Delete a person |

### Query Parameters

List endpoint supports pagination:
- `page`: Page index (default: 0)
- `size`: Page size (default: 20)

### Example Request

```json
POST /people
{
  "name": "John Doe",
  "email": "john.doe@example.com",
  "birthDay": "1990-05-15"
}
```

## Database Configuration

This application uses PostgreSQL. Configure the datasource in `application.properties`:

```properties
quarkus.datasource.db-kind=postgresql
quarkus.datasource.jdbc.url=jdbc:postgresql://localhost:5432/yourdatabase
quarkus.datasource.username=youruser
quarkus.datasource.password=yourpassword
```

Ensure the UUID extension is available:
```sql
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
```

## Running the application in dev mode

```shell
./mvnw quarkus:dev
```

> Quarkus Dev UI is available at <http://localhost:8080/q/dev/>

## Packaging and running the application

```shell
./mvnw package
java -jar target/quarkus-app/quarkus-run.jar
```

### Build uber-jar

```shell
./mvnw package -Dquarkus.package.jar.type=uber-jar
java -jar target/crud-1.0.0-SNAPSHOT-runner.jar
```

## Creating a native executable

```shell
./mvnw package -Dnative
```

Or build in a container (if GraalVM is not installed):

```shell
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

Run the native executable:
```shell
./target/crud-1.0.0-SNAPSHOT-runner
```

## OpenAPI Documentation

Access the Swagger UI at: <http://localhost:8080/q/swagger-ui>

## Project Structure

```
src/main/java/br/eti/fmarques/
├── application/
│   └── PeopleResource.java    # REST endpoints
├── entity/
│   └── Person.java            # JPA entity
└── repository/
    └── EntityPeople.java      # Panache repository
```

## Person Entity Fields

| Field | Type | Constraints |
|-------|------|--------------|
| id | UUID | Auto-generated |
| name | String | Required, 5-100 chars |
| email | String | Required, valid email format |
| birthDay | LocalDate | Must be in the past |