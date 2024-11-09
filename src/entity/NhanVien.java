package entity;

import java.time.LocalDate;
import java.util.Objects;

public class NhanVien {
	private String maNhanVien;
	private String hoTen;
	private boolean gioiTinh;
	private LocalDate ngaySinh;
	private String email;
	private String soDienThoai;
	private String vaiTro;
	private LocalDate ngayBatDauLam;
	private double luong;
	private TaiKhoan tk;
	
	public NhanVien(String maNhanVien, String hoTen, boolean gioiTinh, LocalDate ngaySinh, String email,
			String soDienThoai, String vaiTro, LocalDate ngayBatDauLam, double luong, TaiKhoan tk) {
		super();
		this.maNhanVien = maNhanVien;
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.vaiTro = vaiTro;
		this.ngayBatDauLam = ngayBatDauLam;
		this.luong = luong;
		this.tk = tk;
	}

	public NhanVien(String hoTen, boolean gioiTinh, LocalDate ngaySinh, String email, String soDienThoai, String vaiTro,
			LocalDate ngayBatDauLam, double luong, TaiKhoan tk) {
		super();
		this.hoTen = hoTen;
		this.gioiTinh = gioiTinh;
		this.ngaySinh = ngaySinh;
		this.email = email;
		this.soDienThoai = soDienThoai;
		this.vaiTro = vaiTro;
		this.ngayBatDauLam = ngayBatDauLam;
		this.luong = luong;
		this.tk = tk;
	}

	public NhanVien() {
		super();
	}

	public String getHoTen() {
		return hoTen;
	}

	public void setHoTen(String hoTen) {
		this.hoTen = hoTen;
	}

	public boolean isGioiTinh() {
		return gioiTinh;
	}

	public void setGioiTinh(boolean gioiTinh) {
		this.gioiTinh = gioiTinh;
	}

	public LocalDate getNgaySinh() {
		return ngaySinh;
	}

	public void setNgaySinh(LocalDate ngaySinh) {
		this.ngaySinh = ngaySinh;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getSoDienThoai() {
		return soDienThoai;
	}

	public void setSoDienThoai(String soDienThoai) {
		this.soDienThoai = soDienThoai;
	}

	public String getVaiTro() {
		return vaiTro;
	}

	public void setVaiTro(String vaiTro) {
		this.vaiTro = vaiTro;
	}

	public LocalDate getNgayBatDauLam() {
		return ngayBatDauLam;
	}

	public void setNgayBatDauLam(LocalDate ngayBatDauLam) {
		this.ngayBatDauLam = ngayBatDauLam;
	}

	public double getLuong() {
		return luong;
	}

	public void setLuong(double luong) {
		this.luong = luong;
	}

	public TaiKhoan getTk() {
		return tk;
	}

	public void setTk(TaiKhoan tk) {
		this.tk = tk;
	}

	public String getMaNhanVien() {
		return maNhanVien;
	}

	@Override
	public String toString() {
		return "NhanVien [maNhanVien=" + maNhanVien + ", hoTen=" + hoTen + ", gioiTinh=" + gioiTinh + ", ngaySinh="
				+ ngaySinh + ", email=" + email + ", soDienThoai=" + soDienThoai + ", vaiTro=" + vaiTro
				+ ", ngayBatDauLam=" + ngayBatDauLam + ", luong=" + luong + ", tk=" + tk + "]";
	}

	@Override
	public int hashCode() {
		return Objects.hash(maNhanVien);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		NhanVien other = (NhanVien) obj;
		return Objects.equals(maNhanVien, other.maNhanVien);
	}
}
