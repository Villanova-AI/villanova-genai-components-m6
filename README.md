# Themis

**Themis** is a recommender system consisting of a Spring Boot module implementing the recommendation server, and of a Vaadin module implementing a bot interface allowing the user to interact with the recommendation server.

## Prerequisites

This project requires Java 25 and PostgreSQL 17.6+.

## Postgres setup

Themis server needs a connection to a PostgreSQL database. To set it up, log in as `postgres` user:

```shell
sudo -u postgres psql
```

Then run the following commands:
```sql
CREATE USER themis WITH PASSWORD 'themis';
CREATE DATABASE themis;
GRANT ALL PRIVILEGES ON DATABASE themis TO themis;
\c themis
GRANT ALL ON SCHEMA public TO themis;
ALTER SCHEMA public OWNER TO themis;
ALTER DEFAULT PRIVILEGES IN SCHEMA public GRANT ALL ON TABLES TO themis;
\q
```

## Running the server

Run the recommendation server with the following command:

```shell
./mvnw -pl themis-server clean spring-boot:run
```

The server will be open at `http://localhost:8080`.

## Running the bot

After the recommendation server has been started, run the bot with the following command:

```shell
./mvnw -pl themis-chat clean spring-boot:run
```

The bot interface will be available at `http://localhost:8081`.
