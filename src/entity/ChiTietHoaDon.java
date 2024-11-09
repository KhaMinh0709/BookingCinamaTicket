package entity;

public class ChiTietHoaDon {
	
	private int soLuong;
	private double tongTienSP;
	private HoaDon hd;
	private SanPham sp;
	
	
	public ChiTietHoaDon(int soLuong, HoaDon hd, SanPham sp) {
		super();
		this.soLuong = soLuong;
		this.hd = hd;
		this.sp = sp;
	}


	public ChiTietHoaDon() {
		super();
	}


	public int getSoLuong() {
		return soLuong;
	}


	public void setSoLuong(int soLuong) {
		this.soLuong = soLuong;
	}


	public double getTongTienSP() {
		return tongTienSP;
	}


	public void setTongTienSP() {
		this.tongTienSP = sp.getGiaBan() * soLuong;
	}


	public HoaDon getHd() {
		return hd;
	}


	public void setHd(HoaDon hd) {
		this.hd = hd;
	}


	public SanPham getSp() {
		return sp;
	}


	public void setSp(SanPham sp) {
		this.sp = sp;
	}


	@Override
	public String toString() {
		return "ChiTietHoaDon [soLuong=" + soLuong + ", tongTienSP=" + tongTienSP + ", hd=" + hd + ", sp=" + sp + "]";
	}
	
	
}
