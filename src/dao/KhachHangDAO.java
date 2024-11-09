package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import connectDB.ConnectDB;
import entity.KhachHang;


public class KhachHangDAO {
	ArrayList<KhachHang> dsKhachHang;
	
	public ArrayList<KhachHang> getAllKhachHang() {
		dsKhachHang = new ArrayList<KhachHang>();
		ConnectDB.getInstance().connect();
		Connection con = ConnectDB.getConnection();
		String query = "Select * from KhachHang";
		try {
			Statement statement = con.createStatement();
			ResultSet rs = statement.executeQuery(query);
			while (rs.next()) {
				String maKhachHang = rs.getString(1);
				String tenKhachHang = rs.getString(2);
				String soDienThoai = rs.getString(3);
				String email = rs.getString(4);
				
				KhachHang kh1 = new KhachHang(maKhachHang, tenKhachHang, soDienThoai, email);
				
				dsKhachHang.add(kh1);
			}
			return dsKhachHang;
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return null;
		}
	}
	
	//test connect
//	public static void main(String[] args) {
//		KhachHangDAO dao = new KhachHangDAO();
//		ArrayList<KhachHang> listKH = dao.getAllKhachHang();
//		for (KhachHang kh1: listKH) {
//			System.out.println(kh1);
//		}
//	}
}
