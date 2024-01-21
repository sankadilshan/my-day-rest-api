ALTER TABLE mydayuser
    ADD password VARCHAR(30) NOT NULL,
    ADD roleId INT NOT NULL,
    ADD FOREIGN KEY(roleId) REFERENCES roles(id)