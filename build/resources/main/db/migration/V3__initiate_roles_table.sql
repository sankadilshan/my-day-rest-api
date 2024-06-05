CREATE TABLE roles (
    id SERIAL NOT NULL PRIMARY KEY,
    roles VARCHAR(50) Not Null
);

INSERT INTO roles(roles) VALUES('ADMIN');
INSERT INTO roles(roles) VALUES('USER');