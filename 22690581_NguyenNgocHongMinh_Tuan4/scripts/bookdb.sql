CREATE DATABASE bookdb;
USE bookdb;

CREATE TABLE books (
                       id INT PRIMARY KEY AUTO_INCREMENT,
                       title VARCHAR(100),
                       author VARCHAR(100),
                       price DOUBLE,
                       quantity INT,
                       imgURL VARCHAR(100),
                       description TEXT
);

-- Dữ liệu mẫu
INSERT INTO books (title, author, price, quantity, imgURL, description) VALUES
('Java Programming', 'James Gosling', 250000, 10, 'java.jpg', 'Sách học Java cơ bản'),
('Spring Boot in Action', 'Craig Walls', 300000, 5, 'spring.jpg', 'Xây dựng ứng dụng với Spring Boot'),
('Clean Code', 'Robert C. Martin', 200000, 8, 'cleancode.jpg', 'Thực hành viết code sạch');
