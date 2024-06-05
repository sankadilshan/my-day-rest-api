CREATE TABLE expense
(
    id BIGSERIAL NOT NULL PRIMARY KEY ,
    userId BIGINT Not NULL,
    amount DECIMAL,
    type VARCHAR(20) NOT NULL,
    expenseDate DATE DEFAULT CURRENT_DATE,
    createdDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    modifiedDate TIMESTAMP DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_mydayUser
        FOREIGN KEY(userId) REFERENCES mydayUser(id)
)
