package dao;

import entity.Phim;
import connectDB.ConnectDB;

import java.sql.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class PhimDAO {
    private static final Logger LOGGER = Logger.getLogger(PhimDAO.class.getName());

    public Phim getPhimTheoMaDuaVoDeTail(String maPhim) {
        Phim phim = null;
        String sql = "SELECT TenPhim, TheLoai, ThoiLuong, DaoDien, Anh FROM Phim WHERE MaPhim = ?";

        try (Connection con = ConnectDB.getConnection();
             PreparedStatement stmt = con.prepareStatement(sql)) {

            stmt.setString(1, maPhim);

            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    phim = new Phim(
                        rs.getString("TenPhim"),
                        rs.getString("TheLoai"),
                        rs.getInt("ThoiLuong"),
                        rs.getString("DaoDien"),
                        rs.getString("Anh")
                    );
                }
            }
        } catch (SQLException e) {
            LOGGER.log(Level.SEVERE, "Error retrieving Phim with MaPhim: " + maPhim, e);
        }

        return phim;
    }

    // You can add more methods here for other operations like inserting, updating, or deleting movies
}