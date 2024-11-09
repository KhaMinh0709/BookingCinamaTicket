package gui.app.lichchieu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import entity.LichChieu;
import dao.LichChieuDAO;

public class ThemLichChieuDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtMaLichChieu;
    private JTextField txtMaPhong;
    private JTextField txtMaPhim;
    private JTextField txtGioBatDau;
    private JTextField txtGioKetThuc;
    private JTextField txtGiaMotGhe;
    private JButton btnThem;
    private JButton btnHuy;
    private LichChieuDAO lichChieuDAO;
    private boolean dataChanged;

    public ThemLichChieuDialog(JFrame parent) {
        super(parent, "Thêm Lịch Chiếu", true);
        this.lichChieuDAO = new LichChieuDAO();
        this.dataChanged = false;

        setSize(400, 350);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel pnlContent = new JPanel(new GridLayout(6, 2, 5, 5));
        pnlContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pnlContent.add(new JLabel("Mã Lịch Chiếu:"));
        txtMaLichChieu = new JTextField();
        pnlContent.add(txtMaLichChieu);

        pnlContent.add(new JLabel("Mã Phòng:"));
        txtMaPhong = new JTextField();
        pnlContent.add(txtMaPhong);

        pnlContent.add(new JLabel("Mã Phim:"));
        txtMaPhim = new JTextField();
        pnlContent.add(txtMaPhim);

        pnlContent.add(new JLabel("Giờ Bắt Đầu (dd-MM-yyyy HH:mm):"));
        txtGioBatDau = new JTextField();
        pnlContent.add(txtGioBatDau);

        pnlContent.add(new JLabel("Giờ Kết Thúc (dd-MM-yyyy HH:mm):"));
        txtGioKetThuc = new JTextField();
        pnlContent.add(txtGioKetThuc);

        pnlContent.add(new JLabel("Giá Một Ghế:"));
        txtGiaMotGhe = new JTextField();
        pnlContent.add(txtGiaMotGhe);

        add(pnlContent, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnThem = new JButton("Thêm");
        btnHuy = new JButton("Hủy");
        pnlButtons.add(btnThem);
        pnlButtons.add(btnHuy);

        add(pnlButtons, BorderLayout.SOUTH);

        btnThem.addActionListener(this);
        btnHuy.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnThem) {
            if (validateInput()) {
                LichChieu lichChieu = createLichChieu();
                if (lichChieuDAO.themLichChieu(lichChieu)) {
                    JOptionPane.showMessageDialog(this, "Thêm lịch chiếu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dataChanged = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Thêm lịch chiếu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
                }
            }
        } else if (e.getSource() == btnHuy) {
            dispose();
        }
    }

    private boolean validateInput() {
        // Add validation logic here
        return true;
    }

    private LichChieu createLichChieu() {
        String maLichChieu = txtMaLichChieu.getText();
        String maPhong = txtMaPhong.getText();
        String maPhim = txtMaPhim.getText();
        LocalDateTime gioBatDau = LocalDateTime.parse(txtGioBatDau.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        LocalDateTime gioKetThuc = LocalDateTime.parse(txtGioKetThuc.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm"));
        double giaMotGhe = Double.parseDouble(txtGiaMotGhe.getText());

        return new LichChieu(maLichChieu, maPhong, maPhim, gioBatDau, gioKetThuc, giaMotGhe);
    }

    public boolean isDataChanged() {
        return dataChanged;
    }
}