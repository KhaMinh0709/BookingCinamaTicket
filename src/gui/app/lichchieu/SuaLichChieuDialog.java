package gui.app.lichchieu;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import entity.LichChieu;
import dao.LichChieuDAO;

public class SuaLichChieuDialog extends JDialog implements ActionListener {

    private static final long serialVersionUID = 1L;
    private JTextField txtMaPhong;
    private JTextField txtMaPhim;
    private JTextField txtGioBatDau;
    private JTextField txtGioKetThuc;
    private JTextField txtGiaMotGhe;
    private JButton btnSua;
    private JButton btnHuy;
    private LichChieuDAO lichChieuDAO;
    private LichChieu lichChieu;
    private boolean dataChanged;

    public SuaLichChieuDialog(JFrame parent, LichChieu lichChieu) {
        super(parent, "Sửa Lịch Chiếu", true);
        this.lichChieu = lichChieu;
        this.lichChieuDAO = new LichChieuDAO();
        this.dataChanged = false;

        setSize(400, 300);
        setLocationRelativeTo(parent);
        setLayout(new BorderLayout());

        JPanel pnlContent = new JPanel(new GridLayout(5, 2, 5, 5));
        pnlContent.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        pnlContent.add(new JLabel("Mã Phòng:"));
        txtMaPhong = new JTextField(lichChieu.getMaPhong());
        pnlContent.add(txtMaPhong);

        pnlContent.add(new JLabel("Mã Phim:"));
        txtMaPhim = new JTextField(lichChieu.getMaPhim());
        pnlContent.add(txtMaPhim);

        pnlContent.add(new JLabel("Giờ Bắt Đầu (dd-MM-yyyy HH:mm):"));
        txtGioBatDau = new JTextField(lichChieu.getGioBatDau().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        pnlContent.add(txtGioBatDau);

        pnlContent.add(new JLabel("Giờ Kết Thúc (dd-MM-yyyy HH:mm):"));
        txtGioKetThuc = new JTextField(lichChieu.getGioKetThuc().format(DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        pnlContent.add(txtGioKetThuc);

        pnlContent.add(new JLabel("Giá Một Ghế:"));
        txtGiaMotGhe = new JTextField(String.valueOf(lichChieu.getGiaMotGhe()));
        pnlContent.add(txtGiaMotGhe);

        add(pnlContent, BorderLayout.CENTER);

        JPanel pnlButtons = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        btnSua = new JButton("Sửa");
        btnHuy = new JButton("Hủy");
        pnlButtons.add(btnSua);
        pnlButtons.add(btnHuy);

        add(pnlButtons, BorderLayout.SOUTH);

        btnSua.addActionListener(this);
        btnHuy.addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnSua) {
            if (validateInput()) {
                updateLichChieu();
                if (lichChieuDAO.capNhatLichChieu(lichChieu)) {
                    JOptionPane.showMessageDialog(this, "Sửa lịch chiếu thành công!", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                    dataChanged = true;
                    dispose();
                } else {
                    JOptionPane.showMessageDialog(this, "Sửa lịch chiếu thất bại!", "Lỗi", JOptionPane.ERROR_MESSAGE);
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

    private void updateLichChieu() {
        lichChieu.setMaPhong(txtMaPhong.getText());
        lichChieu.setMaPhim(txtMaPhim.getText());
        lichChieu.setGioBatDau(LocalDateTime.parse(txtGioBatDau.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        lichChieu.setGioKetThuc(LocalDateTime.parse(txtGioKetThuc.getText(), DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm")));
        lichChieu.setGiaMotGhe(Double.parseDouble(txtGiaMotGhe.getText()));
    }

    public boolean isDataChanged() {
        return dataChanged;
    }
    
}