# DFS-Master-Server Documentation

## Table of Contents
1. [Introduction](#introduction)
2. [Architecture](#architecture)
3. [Components](#components)
4. [Installation](#installation)
5. [Usage](#usage)
6. [API](#api)
7. [Testing](#testing)
8. [Contributing](#contributing)
9. [License](#license)
10. [Contact](#contact)

## Introduction
DFS-Master-Server is a distributed file system master server that manages metadata of files and directories in the distributed file system. It is built using Python and Flask.

## Architecture
The master server is build using spring boot and uses a Postgres SQL database to store metadata od files and directories. The server exposes REST APIs to interact with the file system.

## Components
1. **DFS-Master-Server**: The main server that manages metadata of files and directories.
2. **Postgres SQL**: The database used to store metadata of files and directories.

## Installation
1. Clone the repository using:
```bash
git clone https://github.com/dfs-project-personal/dfs-master-server.git
```
2. Navigate to the project directory:
```bash
cd dfs-master-server
```
3. Ensure you have Java 21 and Maven installed on your system.
4. Set up the environment variables in a `.env` file:
```dotenv
DB_HOST=your_db_host
DB_PORT=your_db_port
DB_NAME=your_db_name
DB_USER=your_db_user
DB_PASSWORD=your_db_password
```
5. Build the project using Maven:
```bash
mvn clean install
```

## Usage
To run the application, use the following command:
```bash
mvn spring-boot:run
```

## API
The server exposes the following REST APIs:



