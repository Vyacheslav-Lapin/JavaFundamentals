SET MODE POSTGRESQL;

CREATE TABLE Person (
  id         INT PRIMARY KEY AUTO_INCREMENT,
  first_name VARCHAR(255) NOT NULL,
  last_name  VARCHAR(255),
  permission BOOLEAN         DEFAULT FALSE,
  dob        DATE,
  email      VARCHAR(255) NOT NULL,
  password   VARCHAR(255) NOT NULL,
  address    VARCHAR(255),
  telephone  VARCHAR(15)
);

INSERT INTO Person (first_name, last_name, permission, dob, email, password, address, telephone)
VALUES ('Jose', 'Eglesias', TRUE, '1980-06-15', 'Jose_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');

INSERT INTO Person (first_name, last_name, permission, dob, email, password, address, telephone)
VALUES ('John', 'Eglesias', TRUE, '1980-06-15', 'John_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');

INSERT INTO Person (first_name, last_name, dob, email, password, address, telephone)
VALUES ('Pit', 'Eglesias', '1980-06-15', 'Pit_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');

INSERT INTO Person (first_name, last_name, permission, dob, email, password, address, telephone)
VALUES ('Aisha', 'Eglesias', TRUE, '1980-06-15', 'Aisha_Eglesias@mail.es', 'qwerty', 'Franco squere, 5/1, 10',
        '+38007654321');

CREATE TABLE Gun (
  id      INT PRIMARY KEY AUTO_INCREMENT,
  name    VARCHAR(255) NOT NULL,
  caliber DOUBLE       NOT NULL
);

INSERT INTO Gun (name, caliber) VALUES ('Kolt', 11.52);
INSERT INTO Gun (name, caliber) VALUES ('Beretta', 6.35);
INSERT INTO Gun (name, caliber) VALUES ('Glock', 9.0);
INSERT INTO Gun (name, caliber) VALUES ('AKM-47', 7.62);
INSERT INTO Gun (name, caliber) VALUES ('AK-74', 5.45);

CREATE TABLE Instance (
  id       INT PRIMARY KEY AUTO_INCREMENT,
  model_id INT NOT NULL,
  FOREIGN KEY (model_id) REFERENCES Gun (id)
);

INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (1);
INSERT INTO Instance (model_id) VALUES (2);
INSERT INTO Instance (model_id) VALUES (2);
INSERT INTO Instance (model_id) VALUES (2);
INSERT INTO Instance (model_id) VALUES (2);
INSERT INTO Instance (model_id) VALUES (3);
INSERT INTO Instance (model_id) VALUES (3);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (4);
INSERT INTO Instance (model_id) VALUES (5);
INSERT INTO Instance (model_id) VALUES (5);
INSERT INTO Instance (model_id) VALUES (5);
INSERT INTO Instance (model_id) VALUES (5);
INSERT INTO Instance (model_id) VALUES (5);
