package entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class Ve {

	private String maVe;
	private LocalDateTime ngayPhatHanh;
	private Ghe ghe;
	private LichChieu lc;
	private HoaDon hd;
	
	
	public Ve(String maVe, Ghe ghe, LichChieu lc, HoaDon hd) {
		super();
		this.maVe = maVe;
		this.ngayPhatHanh = LocalDateTime.now();
		this.ghe = ghe;
		this.lc = lc;
		this.hd = hd;
	}


	public Ve() {
		super();
	}

	

	public Ghe getGhe() {
		return ghe;
	}


	public void setGhe(Ghe ghe) {
		this.ghe = ghe;
	}


	public LichChieu getLc() {
		return lc;
	}


	public void setLc(LichChieu lc) {
		this.lc = lc;
	}


	public HoaDon getHd() {
		return hd;
	}


	public void setHd(HoaDon hd) {
		this.hd = hd;
	}


	public String getMaVe() {
		return maVe;
	}


	public LocalDateTime getNgayPhatHanh() {
		return ngayPhatHanh;
	}


	@Override
	public int hashCode() {
		return Objects.hash(maVe);
	}


	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Ve other = (Ve) obj;
		return Objects.equals(maVe, other.maVe);
	}


	@Override
	public String toString() {
		return "Ve [maVe=" + maVe + ", ngayPhatHanh=" + ngayPhatHanh + ", ghe=" + ghe + ", lc=" + lc + ", hd=" + hd
				+ "]";
	}
	
	
	
	
}
