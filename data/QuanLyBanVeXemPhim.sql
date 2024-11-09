
USE Master
go

CREATE DATABASE QuanLyBanVe
go

USE QuanLyBanVe
go


-- Sequence cho bảng TaiKhoan
CREATE SEQUENCE TaiKhoanSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE TaiKhoan
(
    ID CHAR(6) PRIMARY KEY DEFAULT ('TK' + RIGHT('000' + CAST(NEXT VALUE FOR TaiKhoanSequence AS VARCHAR(3)), 3)),
    Username VARCHAR(50) NOT NULL UNIQUE,
    MatKhau VARCHAR(400) NOT NULL
);
go

-- Sequence cho bảng NhanVien
CREATE SEQUENCE NhanVienSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE NhanVien
(
    MaNhanVien CHAR(6) PRIMARY KEY DEFAULT ('NV' + RIGHT('000' + CAST(NEXT VALUE FOR NhanVienSequence AS VARCHAR(3)), 3)),
    HoTen NVARCHAR(100) NOT NULL,
    GioiTinh BIT NOT NULL,
    NgaySinh SMALLDATETIME NOT NULL,
    Email VARCHAR(100) NOT NULL UNIQUE,
    SoDienThoai CHAR(10) NOT NULL UNIQUE,
    VaiTro NVARCHAR(30) NOT NULL,
		CONSTRAINT CK_VaiTro CHECK (VaiTro in (N'Quản lý', N'Nhân viên')),
    NgayBatDau SMALLDATETIME NOT NULL,
    Luong MONEY NOT NULL,
    MaTaiKhoan CHAR(6),
    CONSTRAINT FK_TaiKhoan FOREIGN KEY (MaTaiKhoan) REFERENCES TaiKhoan(ID),
	TrangThai NVARCHAR(20) NOT NULL,
		CONSTRAINT CK_TrangThaiNhanVien CHECK (TrangThai in (N'Còn làm', N'Đã nghỉ làm')),
);
go

-- Sequence cho bảng KhachHang
CREATE SEQUENCE KhachHangSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE KhachHang
(
    MaKhachHang CHAR(9) PRIMARY KEY DEFAULT ('KH' + RIGHT('000000' + CAST(NEXT VALUE FOR KhachHangSequence AS VARCHAR(6)), 6)),
    TenKhachHang NVARCHAR(50) NOT NULL,
    SoDienThoai CHAR(10) NOT NULL UNIQUE,
    Email VARCHAR(50) NOT NULL
);
go

-- Sequence cho bảng SanPham
CREATE SEQUENCE SanPhamSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE SanPham
(
    MaSanPham CHAR(6) PRIMARY KEY DEFAULT ('SP' + RIGHT('000' + CAST(NEXT VALUE FOR SanPhamSequence AS VARCHAR(3)), 3)),
    TenSanPham NVARCHAR(100) NOT NULL,
    SoLuong INT NOT NULL,
	GiaMua MONEY NOT NULL,
    GiaBan MONEY NOT NULL,
	LoaiSanPham NVARCHAR(10)
		CONSTRAINT CK_LoaiSanPham CHECK (LoaiSanPham IN (N'Thức ăn', N'Nước uống')),
	Anh NVARCHAR(100) NOT NULL
);
go

-- Sequence cho bảng LoaiGhe
CREATE SEQUENCE LoaiGheSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE LoaiGhe
(
    MaLoaiGhe CHAR(6) PRIMARY KEY DEFAULT ('LG' + RIGHT('000' + CAST(NEXT VALUE FOR LoaiGheSequence AS VARCHAR(3)), 3)),
    TenLoaiGhe NVARCHAR(50) NOT NULL,
    MoTaLoaiGhe NVARCHAR(500) NULL
);
go

-- Sequence cho bảng Phong
CREATE SEQUENCE PhongSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE Phong
(
    MaPhong CHAR(6) PRIMARY KEY DEFAULT ('PH' + RIGHT('000' + CAST(NEXT VALUE FOR PhongSequence AS VARCHAR(3)), 3)),
    TenPhong NVARCHAR(50) NOT NULL UNIQUE,
    SoLuongGhe INT NOT NULL
);
go

-- Sequence cho bảng Ghe
CREATE SEQUENCE GheSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE Ghe
(
    MaGhe CHAR(7) PRIMARY KEY DEFAULT ('Ghe' + RIGHT('0000' + CAST(NEXT VALUE FOR GheSequence AS VARCHAR(4)), 4)),
    ViTri NVARCHAR(5),
    MaLoaiGhe CHAR(6),
	MaPhong CHAR(6),
    CONSTRAINT FK_LoaiGhe FOREIGN KEY (MaLoaiGhe) REFERENCES LoaiGhe(MaLoaiGhe),
	CONSTRAINT FK_MaPhong FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong)
);
go

-- Sequence cho bảng Phim
CREATE SEQUENCE PhimSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE Phim
(
    MaPhim CHAR(6) PRIMARY KEY DEFAULT ('P' + RIGHT('000' + CAST(NEXT VALUE FOR PhimSequence AS VARCHAR(3)), 3)),
    TenPhim NVARCHAR(100) NOT NULL,
    TheLoai NVARCHAR(100) NOT NULL,
    DaoDien NVARCHAR(50) NOT NULL,
    ThoiLuong INT NOT NULL,
    NgayCongChieu SMALLDATETIME NOT NULL,
	NgonNgu NVARCHAR(50) NOT NULL,
    QuocGia NVARCHAR(50) NOT NULL,
    TrangThai NVARCHAR(50) NOT NULL
		CONSTRAINT CK_TrangThai CHECK (TrangThai IN (N'Đã phát hành', N'Chưa phát hành')),
	NgayBatDau SMALLDATETIME NOT NULL,
	GiaThau MONEY NOT NULL,
	Anh NVARCHAR(100) NOT NULL,
	DoanPhimGioiThieu NVARCHAR(200) NOT NULL,
	TomTat NVARCHAR(2000) NOT NULL
);
go

-- Sequence cho bảng LichChieu
CREATE SEQUENCE LichChieuSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE LichChieu
(
    MaLichChieu CHAR(8) PRIMARY KEY DEFAULT ('LC' + RIGHT('000000' + CAST(NEXT VALUE FOR LichChieuSequence AS VARCHAR(6)), 6)),
    MaPhong CHAR(6) NOT NULL,
    MaPhim CHAR(6) NOT NULL,
    GioBatDau SMALLDATETIME NOT NULL,
    GioKetThuc SMALLDATETIME NOT NULL,
    GiaMotGhe MONEY NOT NULL,
    CONSTRAINT FK_Phong FOREIGN KEY (MaPhong) REFERENCES Phong(MaPhong),
    CONSTRAINT FK_Phim FOREIGN KEY (MaPhim) REFERENCES Phim(MaPhim)
);
go

-- Sequence cho bảng KhuyenMai
CREATE SEQUENCE KhuyenMaiSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE KhuyenMai
(
    MaKhuyenMai CHAR(6) PRIMARY KEY DEFAULT ('KM' + RIGHT('0000' + CAST(NEXT VALUE FOR KhuyenMaiSequence AS VARCHAR(4)), 4)),
	TenKhuyenMai NVARCHAR(20),
	NgayBatDau SMALLDATETIME NOT NULL,
	NgayKetThuc SMALLDATETIME NOT NULL,
	TongTienToiThieu MONEY NOT NULL,
	PhanTramKhuyenMai FLOAT NOT NULL
);
go

-- Sequence cho bảng HoaDon
CREATE SEQUENCE HoaDonSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE HoaDon
(
    MaHoaDon CHAR(10) PRIMARY KEY DEFAULT ('HD' + RIGHT('000000' + CAST(NEXT VALUE FOR HoaDonSequence AS VARCHAR(6)), 6)),
    NgayDat SMALLDATETIME NOT NULL,
    SoGhe INT NOT NULL,
    GhiChu NVARCHAR(300) NULL,
    MaKhachHang CHAR(9) NOT NULL,
    MaNhanVien CHAR(6) NOT NULL,
	MaKhuyenMai CHAR(6) NOT NULL,
    TongTien MONEY,
	VAT FLOAT,
    CONSTRAINT FK_KhachHang FOREIGN KEY (MaKhachHang) REFERENCES KhachHang(MaKhachHang),
    CONSTRAINT FK_NhanVien FOREIGN KEY (MaNhanVien) REFERENCES NhanVien(MaNhanVien),
	CONSTRAINT FK_KhuyenMai FOREIGN KEY (MaKhuyenMai) REFERENCES KhuyenMai(MaKhuyenMai),
);
go

-- Sequence cho bảng ChiTietHoaDon
CREATE SEQUENCE ChiTietHoaDonSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE ChiTietHoaDon
(
    SoLuong INT NOT NULL,
    MaHoaDon CHAR(10) NOT NULL,
    MaSanPham CHAR(6) NOT NULL,
    ThanhTien MONEY,
    PRIMARY KEY (MaHoaDon, MaSanPham),
    CONSTRAINT FK_HoaDonChiTiet FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon),
    CONSTRAINT FK_SanPham FOREIGN KEY (MaSanPham) REFERENCES SanPham(MaSanPham)
);
go

-- Sequence cho bảng Ve
CREATE SEQUENCE VeSequence
    START WITH 1
    INCREMENT BY 1;
go

CREATE TABLE Ve
(
    MaVe CHAR(8) PRIMARY KEY DEFAULT ('Ve' + RIGHT('000000' + CAST(NEXT VALUE FOR VeSequence AS VARCHAR(6)), 6)),
    NgayPhatHanh SMALLDATETIME NOT NULL,
    MaGhe CHAR(7) NOT NULL,
    MaLichChieu CHAR(8) NOT NULL,
    MaHoaDon CHAR(10)  NOT NULL,
    CONSTRAINT FK_Ghe FOREIGN KEY (MaGhe) REFERENCES Ghe(MaGhe),
    CONSTRAINT FK_LichChieu FOREIGN KEY (MaLichChieu) REFERENCES LichChieu(MaLichChieu),
    CONSTRAINT FK_HoaDon FOREIGN KEY (MaHoaDon) REFERENCES HoaDon(MaHoaDon)
);
go

/* add data */
INSERT INTO Phong(TenPhong, SoLuongGhe)
VALUES
(N'Phòng 1',192),
(N'Phòng 2',192),
(N'Phòng 3',192),
(N'Phòng 4',192),
(N'Phòng 5',192),
(N'Phòng 6',192),
(N'Phòng 7',192);
go


INSERT INTO LoaiGhe(TenLoaiGhe, MoTaLoaiGhe)
VALUES
	(N'Ghế thường', N'Là hàng ghế 1-4 (tính từ màn chiếu).'),
	(N'Ghế VIP', N'Thường sẽ bắt đầu từ hàng ghế thứ 5 trở về sau (tính từ màn chiếu).'),
	(N'Ghế đôi Sweetbox', N'Là hàng ghế cuối của phòng chiếu.');
go

CREATE PROCEDURE tao_ghe_theo_hang (@HangGhe CHAR(1), @MaPhong CHAR(6))
AS
BEGIN
    DECLARE @ViTri NVARCHAR(5)
    DECLARE @MaLoaiGhe CHAR(6)
    DECLARE @i INT = 1

    IF @HangGhe IN ('A', 'B', 'C', 'D') -- Nếu hàng là A, B, hoặc C, tạo ghế thường
        SELECT @MaLoaiGhe = MaLoaiGhe FROM LoaiGhe WHERE TenLoaiGhe = N'Ghế thường'
    ELSE IF @HangGhe = 'M' -- Nếu hàng là M, tạo ghế loại Ghế Sweetbox
        SELECT @MaLoaiGhe = MaLoaiGhe FROM LoaiGhe WHERE TenLoaiGhe = N'Ghế đôi Sweetbox'
    ELSE
        SELECT @MaLoaiGhe = MaLoaiGhe FROM LoaiGhe WHERE TenLoaiGhe = N'Ghế VIP' -- Ngược lại, tạo ghế VIP

    WHILE (@i <= 16)
    BEGIN
        SET @ViTri = @HangGhe + RIGHT('00' + CAST(@i AS VARCHAR(2)), 2)
                
        INSERT INTO Ghe (ViTri, MaPhong, MaLoaiGhe)
        VALUES (@ViTri, @MaPhong, @MaLoaiGhe)
        
        SET @i = @i + 1
    END
END
GO

CREATE PROCEDURE tao_ghe_theo_phong (@MaPhong CHAR(6))
AS
BEGIN
    EXECUTE tao_ghe_theo_hang 'A', @MaPhong
    EXECUTE tao_ghe_theo_hang 'B', @MaPhong
    EXECUTE tao_ghe_theo_hang 'C', @MaPhong
    EXECUTE tao_ghe_theo_hang 'D', @MaPhong
    EXECUTE tao_ghe_theo_hang 'E', @MaPhong
    EXECUTE tao_ghe_theo_hang 'F', @MaPhong
    EXECUTE tao_ghe_theo_hang 'G', @MaPhong
    EXECUTE tao_ghe_theo_hang 'H', @MaPhong
    EXECUTE tao_ghe_theo_hang 'I', @MaPhong
    EXECUTE tao_ghe_theo_hang 'K', @MaPhong
    EXECUTE tao_ghe_theo_hang 'L', @MaPhong
	EXECUTE tao_ghe_theo_hang 'M', @MaPhong
END
go

EXECUTE tao_ghe_theo_phong 'PH001';
EXECUTE tao_ghe_theo_phong 'PH002';
EXECUTE tao_ghe_theo_phong 'PH003';
EXECUTE tao_ghe_theo_phong 'PH004';
EXECUTE tao_ghe_theo_phong 'PH005';
EXECUTE tao_ghe_theo_phong 'PH006';
EXECUTE tao_ghe_theo_phong 'PH007';
go

-- Thêm dữ liệu cho bảng TaiKhoan
INSERT INTO TaiKhoan (Username, MatKhau)
VALUES 
('admin', '$2a$10$TBId43wSoxr9Itgr.g8R0u2XZbuY7o98yBBCO6LqgqTSHj/HWYiqG'), 
('hehe', '$2a$10$Wk3Bw8CxJfhy0an/lZTlxeQOMj5h7x0HFmatxJNLbcXJ7PRdjI1Fm'),
('linhtran', '$2a$10$eYHgx/fnAabBsSaVmZHLmeW5gL5EbL2kKkL1jAv9QsNvh0EK.Abi6'),
('nguyenvanb', '$2a$10$X9F8yD.VH.PASqwNaGytseKlQsDoUMIl5xUfyfPbZpXIf7z.8JdQm'),
('thudao', '$2a$10$fImVuC6OI/9o/7.Zb/CEmek.P95bRiWgOz2B7De83Jx2Z8/jE.2qa'),
('minhhoang', '$2a$10$g.hCsU9Xj02KDyOB8.YJ/Ov3yNrcPjePQGcAsTPlWsA9xFiq8gG1e'),
('hanhpham', '$2a$10$rA9HzfT8f7m5lXMFmhnG3.WU9Fo/sG.Raz1uAcIjvUN7RbX9A3CSW'),
('hongnhung', '$2a$10$QXLQHGpLO8OlEFD4H2vnBe1uP/zWzISdjxYPFT8T3BvUmQbfv5mF2'),
('quangtran', '$2a$10$6J9OEXpO.ZSRs59RLO/yhuiBf0PV/d8ZsSiMIh/8Ml.e1WEVJ4wEm'),
('namnguyen', '$2a$10$wDEv8TbKv8JHmfXNPjZyjeXuW7esTYY7Q53ihLg9yRY.ZDtcuvXOG');
go

-- Thêm dữ liệu cho bảng Employee
INSERT INTO NhanVien (HoTen, GioiTinh, NgaySinh, Email, SoDienThoai, VaiTro, NgayBatDau, Luong, MaTaiKhoan, TrangThai)
VALUES
(N'Nguyễn Văn An', 1, '1990-02-14', 'an.nguyen@example.com', '0935123456', N'Quản lý', '2023-01-01', 2000, 'TK001', N'Còn làm'),
(N'Trần Thị Bích', 0, '1995-03-22', 'bich.tran@example.com', '0936123457', N'Nhân viên', '2023-03-15', 1500, 'TK002', N'Còn làm'),
(N'Phạm Minh Hoàng', 1, '1988-11-10', 'hoang.pham@example.com', '0937123458', N'Nhân viên', '2023-05-10', 1800, 'TK003', N'Còn làm'),
(N'Hoàng Thị Lan', 0, '1992-07-30', 'lan.hoang@example.com', '0938123459', N'Nhân viên', '2023-06-20', 1700, 'TK004', N'Đã nghỉ làm'),
(N'Võ Văn Hưng', 1, '1985-09-05', 'hung.vo@example.com', '0939123460', N'Nhân viên', '2023-08-01', 1900, 'TK005', N'Đã nghỉ làm'),
(N'Nguyễn Thị Hương', 0, '1994-12-12', 'huong.nguyen@example.com', '0940123461', N'Nhân viên', '2023-09-15', 1600, 'TK006', N'Còn làm'),
(N'Lê Thanh Tùng', 1, '1987-04-25', 'tung.le@example.com', '0941123462', N'Nhân viên', '2023-10-01', 2000, 'TK007', N'Đã nghỉ làm'),
(N'Đặng Thu Phương', 0, '1993-08-18', 'phuong.dang@example.com', '0942123463', N'Nhân viên', '2023-11-10', 1750, 'TK008', N'Còn làm'),
(N'Bùi Văn Kiên', 1, '1991-05-19', 'kien.bui@example.com', '0943123464', N'Nhân viên', '2023-12-01', 1850, 'TK009', N'Còn làm'),
(N'Hồ Thị Vân Anh', 0, '1989-10-15', 'vananh.ho@example.com', '0944123465', N'Nhân viên', '2024-01-05', 1600, 'TK010', N'Còn làm');
go

-- Thêm dữ liệu cho bảng KhachHang
INSERT INTO KhachHang (TenKhachHang, SoDienThoai, Email)
VALUES
(N'Nguyễn Văn An', '0901234567', 'an.nguyen@example.com'),
(N'Trần Thị Bích', '0912345678', 'bich.tran@example.com'),
(N'Lê Văn Cường', '0923456789', 'cuong.le@example.com'),
(N'Phạm Thị Dung', '0934567890', 'dung.pham@example.com'),
(N'Hoàng Minh Đức', '0945678901', 'duc.hoang@example.com'),
(N'Bùi Ngọc Anh', '0956789012', 'anh.bui@example.com'),
(N'Vũ Thanh Phong', '0967890123', 'phong.vu@example.com'),
(N'Đặng Huyền My', '0978901234', 'my.dang@example.com'),
(N'Tô Hải Yến', '0989012345', 'yen.to@example.com'),
(N'Lý Bảo Long', '0990123456', 'long.ly@example.com');
go

-- Thêm dữ liệu cho bảng Phim 
INSERT INTO Phim (TenPhim, TheLoai, DaoDien, ThoiLuong, NgayCongChieu, NgonNgu, QuocGia, TrangThai, NgayBatDau, GiaThau, Anh, DoanPhimGioiThieu, TomTat)
VALUES 
(N'Venom: Kèo Cuối', N'Hành Động, Giả Tưởng', N'Kelly Marcel', 109, '2024-10-23', N'Tiếng Anh', N'Mỹ', N'Đã phát hành', '2024-10-23', 12000000, N'images/venom_keo_cuoi.jpg', 
N'https://www.youtube.com/watch?v=P0C9ccMXtqc', 
N'Sau chuyến du lịch ngắn sang quê nhà của Spider-Man: No Way Home (2021), Eddie Brock (Tom Hardy) giờ đây cùng Venom “hành hiệp trượng nghĩa” và “nhai đầu” hết đám tội phạm trong thành phố. Tuy nhiên, đi đêm lắm cũng có ngày gặp ma, chính phủ Mỹ đã phát hiện ra sự tồn tại của con quái vật ngoài hành tinh này. Anh chàng buộc phải trở thành kẻ đào tẩu, liên tục trốn chạy khỏi những cuộc truy quét liên tục. Thế nhưng, đây chưa phải là rắc rối lớn nhất… Những con quái vật gớm ghiếc bất ngờ xuất hiện tại nhiều nơi. Hành tinh của chủng tộc Symbiote đã phát hiện ra Trái Đất và chuẩn bị cho cuộc xâm lăng tổng lực.'),
(N'Cô Dâu Hào Môn', N'Tình Cảm', N'Vũ Ngọc Đãng', 114, '2024-10-16', N'Tiếng Việt', N'Việt Nam', N'Đã phát hành', '2024-10-16', 2500000, 'images/co_dau_hao_mon.jpg', 
'https://www.youtube.com/watch?v=cwumN4-rLWY&t', 
N'Uyển Ân chính thức lên xe hoa trong thế giới thượng lưu với câu chuyện hài hước và châm biếm, hé lộ những câu chuyện kén dâu rối ren trong giới thượng lưu.'),
(N'Elli và Bí Ẩn Chiếc Tàu Ma', N'Hoạt Hình, Phiêu Lưu, Gia Đình', N'Jespe Møller, Piet De Rycker', 87, '2024-10-25', N'Tiếng Đức', N'Đức', N'Đã phát hành', '2024-10-25', 1200000, 'images/elli_va_tau_ma.jpg', 
'https://www.youtube.com/watch?v=9tDZpBbg8Ow&t', 
N'Một cuộc truy đuổi của hồn ma nhỏ Elli và những người bạn trong hành trình phiêu lưu kỳ thú, khám phá bí ẩn và thử thách đầy cam go.'),
(N'Ngày Xưa Có Một Chuyện Tình', N'Tình Cảm', N'Trịnh Đình Lê Minh', 135, '2024-10-25', N'Tiếng Việt', N'Việt Nam', N'Đã phát hành', '2024-10-25', 35000000, N'images/ngay_xua_co_mot_chuyen_tinh.jpg',
N'https://www.youtube.com/watch?v=68xnG7hkEOE&t',
N'Sau thành công của Mắt Biếc, tác phẩm do nhà văn Nguyễn Nhật Ánh chấp bút tiếp tục câu chuyện về tuổi trẻ và tình yêu học trò, xoay quanh ba nhân vật Vinh - Miên - Phúc, từ 1987 - 2000. Phim sử dụng những cảnh đẹp như tranh, dưới bàn tay đạo diễn Trịnh Đình Lê Minh. Bộ phim mang đến câu chuyện đầy xúc động, thể hiện những năm tháng tình bạn và tình yêu trong một giai đoạn đầy cảm xúc.'),
(N'Mufasa: Vua Sư Tử', N'Hoạt Hình, Phiêu Lưu', N'Barry Jenkins', 109, '2024-12-20', N'Tiếng Anh', N'Mỹ', N'Chưa phát hành', '2024-12-20', 45000000, N'images/mufasa_vua_su_tu.jpg',
N'https://www.youtube.com/watch?v=XcS9JwQEUag&t', 
N'Thương hiệu kinh điển và nổi tiếng nhất nhì hãng Disney sẽ trở lại vào cuối năm nay với tác phẩm Mufasa: The Lion King. Phim kể về cuộc đời Mufasa – người cha đáng kính của Simba. Dù là tiền truyện, Mufasa: The Lion King vẫn lồng ghép nhân vật quen thuộc từ The Lion King. Khi Rafiki sẽ kể cho con gái Simba và Nala – cô bé Kiara về ông nội. Cùng lúc ấy, cặp đôi quen thuộc Timon và Pumbaa sẽ ở cạnh thêm thắt chi tiết hài hước. Nhiều năm trước câu chuyện chính, chú sư tử con Mufasa bơ vơ trơ trọi giữa đồng cỏ châu Phi. Dù không mang dòng máu hoàng gia nhưng Mufasa trở thành vua sư tử vĩ đại sau một hành trình đầy hấp dẫn và kịch tính.   Teaser phim đem đến cho công chúng trải nghiệm mãn nhãn nhờ phần kĩ xảo hoành tráng qua cảnh sắc thiên nhiên hùng vĩ, trải dài khắp các mùa với đủ địa hình đồi núi, thác nước, sông, rừng cây, đầm lầy… châu Phi. Mufasa gặp vô vàn tình huống giật gân như cuộc đối đầu con cá sấu khát máu hay cuộc di tản của đàn voi khổng lồ… cũng như hình ảnh gợi nhớ The Lion King như cuộc dạo chơi trên lưng đàn hà mã hay đủ loại động vật cùng nhau nhảy múa. Đặc biệt, Mufasa: The Lion King sẽ còn hé lộ mối quan hệ giữa hai anh em Mufasa và Taka (Kelvin Harrison Jr.). Trưởng thành và trải qua nhiều biến cố, tại sao Taka lại trở thành kẻ phản diện Scar tàn nhẫn trong The Lion King?'),
(N'Thiên Đường Quả Báo', N'Tâm Lý, Giật Gân', N'Boss Kuno', 120, '2024-10-30', N'Tiếng Thái', N'Thái Lan', N'Chưa phát hành', '2024-10-30', 15000000, 'images/thien_duong_qua_bao.jpg',
N'https://www.youtube.com/watch?v=2-oT85FKYsQ&t', 
N'Thongkam và Sek làm lụng vất vả để xây dựng cơ ngơi, nhưng bi kịch ập đến khiến Thongkam phải tranh đấu để giành lại quyền thừa kế trang trại gia đình.'),
(N'Godzilla -1.0', N'Giả Tưởng', N'Yamazaki Takashi', 125, '2024-11-01', N'Tiếng Nhật', N'Nhật Bản', N'Chưa phát hành', '2024-11-01', 20000000, 'images/godzilla_minus_one.jpg',
N'https://www.youtube.com/watch?v=ba7WqCQ_cOQ', 
N'Godzilla Minus One/ Godzilla -1.0 là niềm tự hào của Nhật Bản, vừa nhận được tượng vàng Oscar vừa được công chúng ủng hộ cuồng nhiệt tại các rạp chiếu phim.   Phim kể về chàng trai trẻ Shikijima Koichi - một phi công đầy mặc cảm tội lỗi sau cuộc chiến. Anh tìm thấy sức mạnh và lòng dũng cảm để chống lại Godzilla nhờ việc giúp đỡ mẹ con người phụ nữ trẻ Oishi Noriko. Bộ phim được nhiều nhà phê bình và đông đảo người hâm mộ trên toàn thế giới ca ngợi là một trong những bản Godzilla hay nhất mà ai cũng nên xem một lần Godzilla Minus One/ Godzilla -1.0 giành giải thưởng Hiệu ứng hình ảnh xuất sắc nhất tại Giải Oscar lần thứ 96. Phim có sự tham gia của những ngôi sao lớn như Kamiki Ryunosuke, Hamabe Minami, Yamada Yuki, Aoki Munetaka, Yoshioka Hidetaka, Ando Sakura và Sasaki Kuranosuke và là dấu mốc kỷ niệm 70 năm ra mắt loạt phim về quái vật Godzilla.'),
(N'Đôi Bạn Học Yêu', N'Hài, Lãng Mạn', N'E.Oni', 109, '2024-11-07', N'Tiếng Hàn', N'Hàn Quốc', N'Chưa phát hành', '2024-11-07', 14000000, 'images/doi_ban_hoc_yeu.jpg',
N'https://www.youtube.com/watch?v=WOpnzT2Jb0A', 
N'Đôi Bạn Học Yêu là bộ phim kể về Jae-hee (Kim Go-eun) - một cô gái tự do, phóng khoáng, không quan tâm người khác nghĩ gì về mình và Heung-soo - một chàng trai tự cô lập mình ra khỏi thế giới bởi một bí mật không thể tiết lộ. Khi Jae-hee tình cờ phát hiện ra điều thầm kín của Heung-soo, cả hai đã hình thành nên một mối quan hệ không tưởng với cuộc sống chung đầy màu sắc.'),
(N'Tee Yod: Quỷ Ăn Tạng Phần 2', N'Kinh Dị', N'Taweewat Wantha', 111, '2024-10-10', N'Tiếng Thái', N'Thái Lan', N'Đã phát hành', '2024-10-10', 12000000, 'images/tee_yod_quy_an_tang_phan_2.jpg',
N'https://www.youtube.com/watch?v=kZZkCDc38yI', 
N'Ba năm sau cái chết của Yam, Yak vẫn tiếp tục săn lùng linh hồn bí ẩn mặc áo choàng đen. Gặp một cô gái giống Yam, Yak phát hiện ra người bảo vệ linh hồn nguy hiểm và những sinh vật ma quái khác.'),
(N'Trò Chơi Nhân Tính', N'Kinh Dị, Giật Gân', N'William Aherne', 125, '2024-10-25', N'Tiếng Thái', N'Thái Lan', N'Chưa phát hành', '2024-10-25', 17000000, 'images/tro_choi_nhan_tinh.jpg',
N'https://www.youtube.com/watch?v=qlcHaI-jRIk', 
N'Lễ hội trường bỗng biến thành sân chơi "khát máu" của thế lực bí ẩn buộc nhóm học sinh phải tham gia vào những trò chơi kỳ dị và tàn bạo. Không có ngoại lệ nào dành cho kẻ yếu, thua một trò chơi mất cả mạng người. Liệu trong một thế giới tàn khốc sẽ luôn tồn tại một lối thoát hay đó chỉ là niềm tin "ảo" của những trái tim đang hoảng loạn trước cái chết?');
go

INSERT INTO LichChieu (GioBatDau, MaPhim, MaPhong, GiaMotGhe, GioKetThuc)
VALUES
('2024-10-30 20:00:00', 'P001', 'PH001', 85090, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P001'), '2024-10-30 20:00:00')),
('2024-10-30 00:00:00', 'P002', 'PH002', 93490, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P002'), '2024-10-30 00:00:00')),
('2024-10-30 00:00:00', 'P003', 'PH003', 93590, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P003'), '2024-10-30 00:00:00')),
('2024-10-30 00:00:00', 'P004', 'PH004', 83690, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P004'), '2024-10-30 00:00:00')),
('2024-10-30 00:00:00', 'P005', 'PH005', 93490, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P005'), '2024-10-30 00:00:00')),
('2024-10-30 00:00:00', 'P006', 'PH006', 83590, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P006'), '2024-10-30 00:00:00')),
('2024-10-30 00:00:00', 'P007', 'PH007', 83590, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P007'), '2024-10-30 00:00:00')),
('2024-11-01 14:00:00', 'P008', 'PH001', 90000, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P008'), '2024-11-01 14:00:00')),
('2024-11-01 18:30:00', 'P009', 'PH002', 85000, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P009'), '2024-11-01 18:30:00')),
('2024-11-01 21:00:00', 'P010', 'PH003', 92000, DATEADD(MINUTE, (SELECT ThoiLuong FROM Phim WHERE MaPhim = 'P010'), '2024-11-01 21:00:00'));
go

INSERT INTO SanPham (TenSanPham, SoLuong, GiaMua, GiaBan, LoaiSanPham, Anh)
VALUES
(N'Bắp rang bơ', 100, 15000, 15000 * 2, N'Thức ăn', 'images/bap_rang_bo.jpg'),
(N'Nước ngọt Coca-Cola', 200, 10000, 10000 * 2, N'Nước uống', 'images/coca_cola.png'),
(N'Nước suối Aquafina', 150, 5000, 5000 * 2, N'Nước uống', 'images/aquafina.jpg'),
(N'Combo Bắp nước', 50, 30000, 30000 * 2, N'Thức ăn', 'images/combo_bap_nuoc.png'),
(N'Hamburger', 80, 20000, 20000 * 2, N'Thức ăn', 'images/hamburger.jpg'),
(N'Trái cây sấy khô', 60, 12000, 12000 * 2, N'Thức ăn', 'images/trai_cay_say.jpg');
go

--Thêm dữ liệu cho bảng KhuyenMai
INSERT INTO KhuyenMai ([TenKhuyenMai], [NgayBatDau], [NgayKetThuc], TongTienToiThieu, [PhanTramKhuyenMai])
VALUES
(N'Khuyến mãi 1', '2024-06-01 15:30:00', '2024-11-01 15:30:00', 50000, 0.02),
(N'Khuyến mãi 2', '2024-06-29 15:30:00', '2024-10-01 15:30:00', 200000, 0.03),
(N'Khuyến mãi 3', '2024-06-01 15:30:00', '2024-09-01 15:30:00', 600000, 0.4),
(N'Khuyến mãi 4', '2024-06-01 15:30:00', '2024-07-01 15:30:00', 30000, 0.15),
(N'Khuyến mãi 5', '2024-10-01 15:30:00', '2024-12-01 15:30:00', 300000, 0.3),
(N'Khuyến mãi 6', '2024-07-15 10:00:00', '2024-11-15 10:00:00', 250000, 0.05),
(N'Khuyến mãi 7', '2024-08-01 12:00:00', '2024-10-30 12:00:00', 100000, 0.1),
(N'Khuyến mãi 8', '2024-09-01 14:00:00', '2024-11-30 14:00:00', 39000, 0.25),
(N'Khuyến mãi 9', '2024-08-15 09:30:00', '2024-10-15 09:30:00', 99000, 0.08),
(N'Khuyến mãi 10', '2024-11-01 15:00:00', '2025-01-01 15:00:00', 59000, 0.35),
(N'Khuyến mãi 11', '2024-11-01 15:00:00', '2025-12-01 15:00:00', 1000000, 0.35)


-- Thêm dữ liệu cho bảng HoaDon
INSERT INTO HoaDon (NgayDat, SoGhe, GhiChu, MaKhachHang, MaNhanVien, TongTien, VAT, MaKhuyenMai)
VALUES
('2024-06-01 15:30:00', 2, N'No butter', 'KH000001', 'NV001', 
    (50000 + 40000) + (2 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000001')), 0.1, 'KM0001'),

('2024-06-02 18:00:00', 4, N'Extra ice', 'KH000002', 'NV002', 
    (80000 + 100000) + (4 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000002')), 0.1, 'KM0002'),

('2024-06-03 20:00:00', 3, N'No sugar', 'KH000003', 'NV003', 
    (75000 + 60000) + (3 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000003')), 0.1, 'KM0003'),

('2024-06-04 19:45:00', 5, N'VIP seats', 'KH000004', 'NV004', 
    (150000 + 75000) + (5 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000004')), 0.1, 'KM0004'),

('2024-06-05 14:30:00', 1, N'Extra cheese', 'KH000005', 'NV005', 
    (45000) + (1 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000005')), 0.1, 'KM0001'),

('2024-06-06 21:00:00', 2, N'Family package', 'KH000006', 'NV006', 
    (50000 + 40000) + (2 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000006')), 0.1, 'KM0001'),

('2024-06-07 16:15:00', 6, N'Student discount', 'KH000007', 'NV007', 
    (150000 + 120000) + (6 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000007')), 0.1, 'KM0002'),

('2024-06-08 13:45:00', 3, N'No notes', 'KH000008', 'NV008', 
    (45000 + 90000) + (3 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000008')), 0.1, 'KM0003'),

('2024-06-09 11:00:00', 4, N'Child ticket', 'KH000009', 'NV009', 
    (180000) + (4 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000009')), 0.1, 'KM0002'),

('2024-06-10 17:30:00', 2, N'Birthday discount', 'KH000010', 'NV010', 
    (50000 + 40000) + (2 * (SELECT GiaMotGhe FROM LichChieu WHERE MaLichChieu = 'LC000010')), 0.1, 'KM0004');
go

-- Thêm dữ liệu cho bảng ChiTietHoaDon
INSERT INTO ChiTietHoaDon (SoLuong, MaHoaDon, MaSanPham, ThanhTien)
VALUES
(3, 'HD000001', 'SP002', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP002') * 3),
(2, 'HD000001', 'SP004', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP004') * 2),
(2, 'HD000002', 'SP002', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP002') * 2),
(5, 'HD000002', 'SP004', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP004') * 5),
(3, 'HD000002', 'SP001', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP001') * 3),
(4, 'HD000003', 'SP001', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP001') * 4),
(1, 'HD000004', 'SP006', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP006') * 1),
(4, 'HD000005', 'SP002', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP002') * 4),
(6, 'HD000005', 'SP005', (SELECT GiaBan FROM SanPham WHERE MaSanPham = 'SP005') * 6);
go

-- Thêm dữ liệu cho bảng Ve
INSERT INTO Ve (NgayPhatHanh, MaGhe, MaLichChieu, MaHoaDon)
VALUES
('2024-06-01', 'Ghe0001', 'LC000001', 'HD000001'),
('2024-06-01', 'Ghe0002', 'LC000001', 'HD000001'),

('2024-06-02', 'Ghe0003', 'LC000002', 'HD000002'),
('2024-06-02', 'Ghe0004', 'LC000002', 'HD000002'),
('2024-06-02', 'Ghe0005', 'LC000002', 'HD000002'),
('2024-06-02', 'Ghe0006', 'LC000002', 'HD000002'),

('2024-06-03', 'Ghe0007', 'LC000003', 'HD000003'),
('2024-06-03', 'Ghe0008', 'LC000003', 'HD000003'),
('2024-06-03', 'Ghe0009', 'LC000003', 'HD000003'),

('2024-06-04', 'Ghe0010', 'LC000004', 'HD000004'),
('2024-06-04', 'Ghe0011', 'LC000004', 'HD000004'),
('2024-06-04', 'Ghe0012', 'LC000004', 'HD000004'),
('2024-06-04', 'Ghe0013', 'LC000004', 'HD000004'),
('2024-06-04', 'Ghe0014', 'LC000004', 'HD000004'),

('2024-06-05', 'Ghe0015', 'LC000005', 'HD000005'),

('2024-06-06', 'Ghe0016', 'LC000006', 'HD000006'),
('2024-06-06', 'Ghe0017', 'LC000006', 'HD000006'),

('2024-06-07', 'Ghe0018', 'LC000007', 'HD000007'),
('2024-06-07', 'Ghe0019', 'LC000007', 'HD000007'),
('2024-06-07', 'Ghe0020', 'LC000007', 'HD000007'),
('2024-06-07', 'Ghe0021', 'LC000007', 'HD000007'),
('2024-06-07', 'Ghe0022', 'LC000007', 'HD000007'),
('2024-06-07', 'Ghe0023', 'LC000007', 'HD000007'),

('2024-06-08', 'Ghe0024', 'LC000001', 'HD000008'),
('2024-06-08', 'Ghe0025', 'LC000001', 'HD000008'),
('2024-06-08', 'Ghe0026', 'LC000001', 'HD000008'),

('2024-06-09', 'Ghe0027', 'LC000002', 'HD000009'),
('2024-06-09', 'Ghe0028', 'LC000002', 'HD000009'),
('2024-06-09', 'Ghe0029', 'LC000002', 'HD000009'),
('2024-06-09', 'Ghe0030', 'LC000002', 'HD000009'),

('2024-06-10', 'Ghe0031', 'LC000003', 'HD000010'),
('2024-06-10', 'Ghe0032', 'LC000003', 'HD000010');
go