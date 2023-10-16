# Project Management System

## Description

A project management system that is in demand among small creative teams: developers, testers, analysts. Suitable for planning work and putting tasks in order.

## Architecture
![Project managemetnt system](https://github.com/MaxSvt/ToDoApp/assets/51627564/1f31beac-2a51-4676-91e0-447c7e7bc29b)

## Technology used in this Project:

1) Java : all the logic has been written in java.
2) MySQL: PostgreSQL database has been used as database.
3) Spring Framework.
4) Spring Boot.
5) SpringSecurity: SpringSecurity has been used for authentication.
6) JWT token for Authorization.
7) Spring Data JPA.
8) Hibernate: Hibernate ORM is used.

## Software And Tools Required:

- Java JDK 17
- Intellij Idea
- MySQL

## Installation & Run

- Before running the API server, you should update the database config inside the application.properties file.
- Update the port number, username and password as per your local database config.
```
server.port=8089
spring.datasource.url=jdbc:postgresql://localhost:5432/todoapp
spring.datasource.username=postgres
spring.datasource.password=root
```

## Sample API requests
```
POST Registration /registration
```
Body

```json
{
    "email": "string",
    "firstname": "string",
    "lastname": "string",
    "password": "string"
}
```
-------------------------------------

```
POST Auth /auth
```
Body

```json
{
    "username": "string",
    "password": "string"
}
```
Response
```json
{
    "token": "HEADER.PAYLOAD.VERIFY SIGNATURE"
}
```
-------------------------------------

```
POST Create project /api/v1/projects
```
Body

```json
{
    "title": "string",
    "code": "string",
    "description": "string"
}
```
-------------------------------------

```
POST Create task in project /api/v1/projects/{id}/tasks
```
Body

```json
{
    "title": "string",
    "description": "string",
    "performer": "string"
}
```
-------------------------------------

```
POST Create comment /api/v1/tasks/1/comments
```
Body

```json
{
    "description": "string"
}
```
-------------------------------------

```
POST Add Employee Position /api/v1/positions
```
Body

```json
{
    "name": "string"
}
```
-------------------------------------

```
POST Add participant to Project /api/v1/projects/{id}/participants
```
Body

```json
{
    "displayName": "string",
    "position": "string"
}
```
-------------------------------------

## Tests

Go the extra mile and write tests for your application. The
