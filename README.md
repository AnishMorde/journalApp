# Journal Application

A secure Spring Boot application for managing personal journal entries with MongoDB backend and weather integration.

## Features

- User authentication and authorization
- CRUD operations for journal entries 
- MongoDB database integration with transaction support
- Weather information integration
- Email notifications
- Role-based access control (User/Admin)
- Caching support
- Stateless session management

## Tech Stack

- Java 17
- Spring Boot 2.7.16
- Spring Security
- MongoDB
- Maven
- Lombok

## API Endpoints

### Public Endpoints
- `GET /public/health` - Health check
- `POST /public/create-user` - Create new user

### User Endpoints (Requires Authentication)
- `GET /user` - Get user greeting with weather
- `PUT /user` - Update user details
- `DELETE /user` - Delete user account

### Journal Endpoints (Requires Authentication)
- `GET /journal` - Get all journal entries
- `POST /journal` - Create new journal entry
- `GET /journal/id/{id}` - Get specific journal entry
- `PUT /journal/id/{id}` - Update journal entry
- `DELETE /journal/id/{id}` - Delete journal entry

### Admin Endpoints (Requires ADMIN Role)
- `GET /admin/all-users` - Get all users
- `POST /admin/create-admin` - Create admin user
- `GET /admin/cache` - Clear application cache

## Configuration

The application uses YAML configuration with multiple profiles:
- `application.yml` - Base configuration
- `application-dev.yml` - Development profile
- `application-prod.yml` - Production profile

## Getting Started

1. Clone the repository
2. Configure MongoDB connection in application properties
3. Set up email configuration (for notifications)
4. Configure weather API key
5. Run using Maven:

```sh
./mvnw spring-boot:run
```

## Security

- Basic authentication
- BCrypt password encoding
- Role-based authorization
- CSRF protection disabled
- Stateless session management

## Dependencies

- spring-boot-starter-web
- spring-boot-starter-data-mongodb
- spring-boot-starter-mail
- spring-boot-starter-security
- lombok
- opencsv
- sonar-maven-plugin
