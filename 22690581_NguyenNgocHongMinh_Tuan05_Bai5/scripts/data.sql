USE companies

CREATE TABLE departments (
department_id INT PRIMARY KEY,
name VARCHAR(255)
)
CREATE TABLE employees (
id INT PRIMARY KEY,
name VARCHAR(255),
department_id INT,
salary FLOAT,
FOREIGN KEY (department_id) REFERENCES departments(department_id)
)
-- Tạo dữ liệu mẫu cho bảng departments
    INSERT INTO departments (department_id, name) VALUES
(1, 'Sales'),
(2, 'Marketing'),
(3, 'Engineering'),
(4, 'HR'),
(5, 'Finance');

-- Tạo dữ liệu mẫu cho bảng employees
INSERT INTO employees (id, name, department_id, salary) VALUES
(1, 'Alice', 1, 50000),
(2, 'Bob', 2, 60000),
(3, 'Charlie', 3, 70000),
(4, 'David', 4, 55000),
(5, 'Eva', 5, 75000),
(6, 'Frank', 3, 80000),
(7, 'Grace', 1, 48000),
(8, 'Hannah', 2, 62000),
(9, 'Irene', 5, 78000),
(10, 'Jack', 4, 53000);
