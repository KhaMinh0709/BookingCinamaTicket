package entity;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;

public class HoaDon {

	private String maHoaDon;
	private LocalDateTime ngayDat;
	private int soGhe;
	private String ghiChu;
	private KhachHang kh;
	private NhanVien nv;
	private double tongTien;
	
	public HoaDon(String maHoaDon, int soGhe, String ghiChu, KhachHang kh, NhanVien nv) {
		super();
		this.maHoaDon = maHoaDon;
		this.ngayDat = LocalDateTime.now();
		this.soGhe = soGhe;
		this.ghiChu = ghiChu;
		this.kh = kh;
		this.nv = nv;
	}

	public HoaDon() {
		super();
	}

	public LocalDateTime getNgayDat() {
		return ngayDat;
	}

	public void setNgayDat(LocalDateTime ngayDat) {
		this.ngayDat = ngayDat;
	}

	public int getSoGhe() {
		return soGhe;
	}

	public void setSoGhe(int soGhe) {
		this.soGhe = soGhe;
	}

	public String getGhiChu() {
		return ghiChu;
	}

	public void setGhiChu(String ghiChu) {
		this.ghiChu = ghiChu;
	}

	public KhachHang getKh() {
		return kh;
	}

	public void setKh(KhachHang kh) {
		this.kh = kh;
	}

	public NhanVien getNv() {
		return nv;
	}

	public void setNv(NhanVien nv) {
		this.nv = nv;
	}

	public String getMaHoaDon() {
		return maHoaDon;
	}

	@Override
	public int hashCode() {
		return Objects.hash(maHoaDon);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		HoaDon other = (HoaDon) obj;
		return Objects.equals(maHoaDon, other.maHoaDon);
	}

	@Override
	public String toString() {
		return "HoaDon [maHoaDon=" + maHoaDon + ", ngayDat=" + ngayDat + ", soGhe=" + soGhe + ", ghiChu=" + ghiChu
				+ ", kh=" + kh + ", nv=" + nv + "]";
	}
	
	public void setTotal(List<ChiTietHoaDon> dschiTietHD, Ve ve) {
		double tongTienCTHD = 0;
		for (ChiTietHoaDon chiTietHD : dschiTietHD) {
			tongTienCTHD += chiTietHD.getTongTienSP();
		}
		this.tongTien = tongTienCTHD + this.soGhe * ve.getLc().getGiaMotGhe();
	}

	public double getTongTien() {
		return tongTien;
	}
	
}
