-- Tạo CSDL
CREATE DATABASE QUANLYDANHMUC;
USE QUANLYDANHMUC;

-- Bảng DANHMUC
CREATE TABLE DANHMUC (
    MADM INT AUTO_INCREMENT PRIMARY KEY,
    TENDANHMUC VARCHAR(100) NOT NULL,
    NGUOIQUANLY VARCHAR(100),
    GHICHU VARCHAR(255)
);

-- Bảng TINTUC
CREATE TABLE TINTUC (
    MATT INT AUTO_INCREMENT PRIMARY KEY,
    TIEUDE VARCHAR(200) NOT NULL,
    NOIDUNGTT VARCHAR(255) NOT NULL,
    LIENKET VARCHAR(200) NOT NULL,
    MADM INT NOT NULL,
    FOREIGN KEY (MADM) REFERENCES DANHMUC(MADM) ON DELETE CASCADE
);

-- Insert dữ liệu mẫu
INSERT INTO DANHMUC (TENDANHMUC, NGUOIQUANLY, GHICHU) VALUES
('Thể thao', 'Nguyen Van A', 'Tin tức thể thao trong và ngoài nước'),
('Công nghệ', 'Tran Thi B', 'Tin tức công nghệ mới nhất'),
('Giải trí', 'Le Van C', 'Tin tức giải trí showbiz');

INSERT INTO TINTUC (TIEUDE, NOIDUNGTT, LIENKET, MADM) VALUES
('Việt Nam thắng Thái Lan', 'Đội tuyển Việt Nam đã thắng 2-1 trước Thái Lan', 'http://example.com/vn-thang-thai', 1),
('iPhone 20 ra mắt', 'Apple giới thiệu iPhone 20 với nhiều tính năng đột phá', 'http://example.com/iphone20', 2),
('Ca sĩ X ra MV mới', 'Ca sĩ X tung MV mới gây bão cộng đồng mạng', 'http://example.com/mv-ca-si-x', 3),
('Chung kết C1', 'Real Madrid đối đầu Man City tại chung kết C1', 'http://example.com/chungket-c1', 1),
('Samsung Galaxy Z100', 'Samsung ra mắt Galaxy Z100 màn hình gập 4 lần', 'http://example.com/galaxy-z100', 2),
('Phim bom tấn Y', 'Phim Y ra mắt, phá kỷ lục phòng vé toàn cầu', 'http://example.com/phim-y', 3),
('U23 Việt Nam vô địch', 'U23 Việt Nam lần đầu vô địch châu Á', 'http://example.com/u23-vn', 1),
('Windows 15 phát hành', 'Microsoft phát hành Windows 15 với AI tích hợp', 'http://example.com/win15', 2),
('Ca sĩ A lưu diễn', 'Ca sĩ A công bố tour lưu diễn châu Âu', 'http://example.com/luu-dien-a', 3),
('Olympic 2032', 'Việt Nam đăng cai Olympic 2032', 'http://example.com/olympic2032', 1);