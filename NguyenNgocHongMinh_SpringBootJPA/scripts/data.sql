CREATE DATABASE company;
USE company;
CREATE TABLE departments (
                             dept_id VARCHAR(10) PRIMARY KEY,
                             dept_name VARCHAR(100)
);
CREATE TABLE employees (
                           emp_id VARCHAR(10) PRIMARY KEY,
                           emp_name VARCHAR(100),
                           email VARCHAR(100),
                           age INT,
                           salary DOUBLE,
                           status INT,
                           dept_id VARCHAR(10),
                           FOREIGN KEY (dept_id) REFERENCES departments(dept_id)
);
INSERT INTO departments VALUES ('D001', 'IT'), ('D002', 'HR');

INSERT INTO employees VALUES
                          ('E001', 'Nguyen Van A', 'a@gmail.com', 30, 2000, 1, 'D001'),
                          ('E002', 'Tran Thi B', 'b@gmail.com', 28, 1500, 1, 'D001'),
                          ('E003', 'Le Van C', 'c@gmail.com', 40, 2500, 0, 'D002'),
                          ('E004', 'Nguyen Van Dat', 'nguyenvandat@gmail.com', 25, 1600, 1, 'D001');