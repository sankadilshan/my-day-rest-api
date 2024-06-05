CREATE TABLE mydayUser
(
    id BIGSERIAL NOT NULL PRIMARY KEY,
    email VARCHAR(50) NOT NULL,
    firstName VARCHAR(30) NOT NULL,
    lastName VARCHAR(30),
    createdDate DATE DEFAULT CURRENT_DATE,
    modifiedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP
)
