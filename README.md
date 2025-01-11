# User Management API

This API is an example implementation of the **Notification Pattern** for error handling. The API provides endpoints to manage users, including user creation, data validation, and error handling.

The API is built using **Spring Boot** and demonstrates how to handle validation errors and return structured error messages.

## Features

- **User Creation**: Create a new user with name and email validation.
- **Data Validation**: Ensure required fields (name and email) are provided during user creation.
- **Error Handling**: Uses the **Notification Pattern** to return structured error messages in case of validation failure.

## Technologies Used

- **Spring Boot**: Framework to build the REST API.
- **JUnit 5**: Testing framework for unit tests.
- **Mockito**: Library for creating mocks in unit tests.
- **Spring Boot Test**: Framework for integration testing.
- **Maven**: Dependency management and build tool.

## How to Run the Project

### Prerequisites

- **Java 11 or higher**.
- **Maven** to manage dependencies and run the project.

### Step-by-Step Instructions

1. **Clone the repository**:

    ```bash
    git clone https://github.com/MarioCesarJr/notification-pattern-example
    cd user-management-api
    ```

2. **Install project dependencies**:

    If you don't have Maven installed globally, you can use Maven Wrapper to install the dependencies:

    ```bash
    ./mvnw clean install
    ```

3. **Run the Spring Boot application**:

    To run the application locally, execute:

    ```bash
    ./mvnw spring-boot:run
    ```

    This will start the application on `http://localhost:8080`.

4. **Test the API**:

    The API has the following endpoint:

    - **POST /users**: Creates a new user.

    **Example Request to Create a User**:

    ```bash
    curl -X POST http://localhost:8080/users \
    -H "Content-Type: application/json" \
    -d '{
        "name": "Mazaropi da Silva",
        "email": "mazaropi.silva@example.com"
    }'
    ```

    **Successful Response** (HTTP 201 Created):

    ```json
    {
        "name": "Mazaropi da Silva",
        "email": "mazaropi.silva@example.com"
    }
    ```

    **Error Response** (HTTP 400 Bad Request) - When name or email is missing:

    ```json
    {
        "status": 400,
        "errors": [
            "Name is required",
            "Email is required"
        ]
    }
    ```

## Testing

The project includes unit and integration tests to ensure the application works as expected.

### Unit Tests

Unit tests verify the validation logic in the `UserService`.

To run the unit tests, use:

```bash
./mvnw test