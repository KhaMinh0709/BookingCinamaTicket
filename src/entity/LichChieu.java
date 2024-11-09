package entity;

import java.time.LocalDateTime;

public class LichChieu {
    private String maLichChieu;
    private String maPhong;
    private String maPhim;
    private LocalDateTime gioBatDau;
    private LocalDateTime gioKetThuc;
    private double giaMotGhe;

    public LichChieu(String maLichChieu, String maPhong, String maPhim, LocalDateTime gioBatDau, LocalDateTime gioKetThuc, double giaMotGhe) {
        this.maLichChieu = maLichChieu;
        this.maPhong = maPhong;
        this.maPhim = maPhim;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
        this.giaMotGhe = giaMotGhe;
    }

    // Getters and setters
    public String getMaLichChieu() { return maLichChieu; }
    public void setMaLichChieu(String maLichChieu) { this.maLichChieu = maLichChieu; }
    public String getMaPhong() { return maPhong; }
    public void setMaPhong(String maPhong) { this.maPhong = maPhong; }
    public String getMaPhim() { return maPhim; }
    public void setMaPhim(String maPhim) { this.maPhim = maPhim; }
    public LocalDateTime getGioBatDau() { return gioBatDau; }
    public void setGioBatDau(LocalDateTime gioBatDau) { this.gioBatDau = gioBatDau; }
    public LocalDateTime getGioKetThuc() { return gioKetThuc; }
    public void setGioKetThuc(LocalDateTime gioKetThuc) { this.gioKetThuc = gioKetThuc; }
    public double getGiaMotGhe() { return giaMotGhe; }
    public void setGiaMotGhe(double giaMotGhe) { this.giaMotGhe = giaMotGhe; }
}