package dao;

import java.sql.*;
import java.time.LocalDateTime;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.LichChieu;

public class LichChieuDAO {
    
    public ArrayList<LichChieu> getTatCaLichChieu() {
        ArrayList<LichChieu> dsLichChieu = new ArrayList<>();
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM [dbo].[LichChieu]");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String maLichChieu = rs.getString("MaLichChieu");
                String maPhong = rs.getString("MaPhong");
                String maPhim = rs.getString("MaPhim");
                LocalDateTime gioBatDau = rs.getTimestamp("GioBatDau").toLocalDateTime();
                LocalDateTime gioKetThuc = rs.getTimestamp("GioKetThuc").toLocalDateTime();
                double giaMotGhe = rs.getDouble("GiaMotGhe");
                
                LichChieu lc = new LichChieu(maLichChieu, maPhong, maPhim, gioBatDau, gioKetThuc, giaMotGhe);
                dsLichChieu.add(lc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.getInstance().disconnect();
        }
        return dsLichChieu;
    }
    
    public ArrayList<LichChieu> getLichChieuTheoMa(String maLichChieu) {
        ArrayList<LichChieu> dsLichChieu = new ArrayList<>();
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        try {
            PreparedStatement preparedStatement = con.prepareStatement(
                "SELECT * FROM [dbo].[LichChieu] WHERE MaLichChieu LIKE ?");
            preparedStatement.setString(1, "%" + maLichChieu + "%");
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String ma = rs.getString("MaLichChieu");
                String maPhong = rs.getString("MaPhong");
                String maPhim = rs.getString("MaPhim");
                LocalDateTime gioBatDau = rs.getTimestamp("GioBatDau").toLocalDateTime();
                LocalDateTime gioKetThuc = rs.getTimestamp("GioKetThuc").toLocalDateTime();
                double giaMotGhe = rs.getDouble("GiaMotGhe");
                
                LichChieu lc = new LichChieu(ma, maPhong, maPhim, gioBatDau, gioKetThuc, giaMotGhe);
                dsLichChieu.add(lc);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            ConnectDB.getInstance().disconnect();
        }
        return dsLichChieu;
    }
    
    // Add more methods for CRUD operations as needed
    
    public boolean themLichChieu(LichChieu lichChieu) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("INSERT INTO [dbo].[LichChieu] (MaLichChieu, MaPhong, MaPhim, GioBatDau, GioKetThuc, GiaMotGhe) VALUES (?, ?, ?, ?, ?, ?)");
            stmt.setString(1, lichChieu.getMaLichChieu());
            stmt.setString(2, lichChieu.getMaPhong());
            stmt.setString(3, lichChieu.getMaPhim());
            stmt.setTimestamp(4, Timestamp.valueOf(lichChieu.getGioBatDau()));
            stmt.setTimestamp(5, Timestamp.valueOf(lichChieu.getGioKetThuc()));
            stmt.setDouble(6, lichChieu.getGiaMotGhe());
            
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectDB.getInstance().disconnect();
        }
        return n > 0;
    }
    
    public boolean capNhatLichChieu(LichChieu lichChieu) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("UPDATE [dbo].[LichChieu] SET MaPhong = ?, MaPhim = ?, GioBatDau = ?, GioKetThuc = ?, GiaMotGhe = ? WHERE MaLichChieu = ?");
            stmt.setString(1, lichChieu.getMaPhong());
            stmt.setString(2, lichChieu.getMaPhim());
            stmt.setTimestamp(3, Timestamp.valueOf(lichChieu.getGioBatDau()));
            stmt.setTimestamp(4, Timestamp.valueOf(lichChieu.getGioKetThuc()));
            stmt.setDouble(5, lichChieu.getGiaMotGhe());
            stmt.setString(6, lichChieu.getMaLichChieu());
            
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectDB.getInstance().disconnect();
        }
        return n > 0;
    }
    
    public boolean xoaLichChieu(String maLichChieu) {
        ConnectDB.getInstance().connect();
        Connection con = ConnectDB.getConnection();
        PreparedStatement stmt = null;
        int n = 0;
        try {
            stmt = con.prepareStatement("DELETE FROM [dbo].[LichChieu] WHERE MaLichChieu = ?");
            stmt.setString(1, maLichChieu);
            
            n = stmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            try {
                stmt.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
            ConnectDB.getInstance().disconnect();
        }
        return n > 0;
    }

	public ArrayList<LichChieu> getLichChieuTheoTrangThai(LocalDateTime now, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	public ArrayList<LichChieu> getLichChieuDangChieu(LocalDateTime now) {
		// TODO Auto-generated method stub
		return null;
	}
}