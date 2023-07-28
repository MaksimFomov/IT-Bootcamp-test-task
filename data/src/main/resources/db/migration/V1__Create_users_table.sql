CREATE TABLE users (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       first_name VARCHAR(20) NOT NULL,
                       last_name VARCHAR(40) NOT NULL,
                       middle_name VARCHAR(40),
                       email VARCHAR(50) UNIQUE NOT NULL,
                       role VARCHAR(20) NOT NULL
);