# Project Management System

## Description

Система управления проектами, востребованная у небольших творческих коллективов: разработчиков, тестировщиков, аналитиков. Подходит для планирования работы и наведения порядка в задачах.

## Architecture
![Project managemetnt system](https://github.com/MaxSvt/ToDoApp/assets/51627564/1f31beac-2a51-4676-91e0-447c7e7bc29b)


## Table of Contents (Optional)

If your README is long, add a table of contents to make it easy for users to find what they need.

- [Installation](#installation)
- [Usage](#usage)
- [Credits](#credits)
- [License](#license)

## Installation

What are the steps required to install your project? Provide a step-by-step description of how to get the development environment running.

## Methods
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
    "username": "Svetlov.M",
    "password": "123"
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


## How to Contribute

If you created an application or package and would like other developers to contribute it, you can include guidelines for how to do so. The [Contributor Covenant](https://www.contributor-covenant.org/) is an industry standard, but you can always write your own if you'd prefer.

## Tests

Go the extra mile and write tests for your application. The
