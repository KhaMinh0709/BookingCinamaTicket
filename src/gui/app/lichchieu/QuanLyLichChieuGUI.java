package gui.app.lichchieu;

import java.awt.*; 
import java.awt.event.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.*;
import com.formdev.flatlaf.FlatClientProperties;
import com.formdev.flatlaf.extras.FlatSVGIcon;
import net.miginfocom.swing.MigLayout;
import dao.LichChieuDAO;
import dao.PhimDAO;
import entity.LichChieu;
import entity.Phim;

public class QuanLyLichChieuGUI extends JPanel implements ActionListener {
    
    private static final long serialVersionUID = 1L;
	private static final JFrame QuanLyLichChieuGUI = null;
    private JTextField txtTim;
    private JButton btnTim;
    private JButton btnThem;
    private JButton btnSua;
    private DefaultTableModel tableModel;
    private JTable table;
    private JComboBox<String> cboLoaiHienThi;
    private JButton btnXoa;
    private JPanel pnlChiTiet;
    private ArrayList<LichChieu> dsLichChieu;
    private LichChieuDAO lichChieuDAO;
    private PhimDAO phimDAO;
    
    private JLabel lblTenPhim;
    private JLabel lblTheLoai;
    private JLabel lblThoiLuong;
    private JLabel lblDaoDien;
    
    public QuanLyLichChieuGUI() {
        lichChieuDAO = new LichChieuDAO();
        phimDAO = new PhimDAO();
        setLayout(new BorderLayout());
        
        JPanel pnlTitle = new JPanel();
        JLabel lblTitle = new JLabel("THÔNG TIN LỊCH CHIẾU");
        lblTitle.setFont(new Font(lblTitle.getFont().getFontName(), Font.BOLD, 24));
        pnlTitle.add(lblTitle);
        add(pnlTitle, BorderLayout.NORTH);
        
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        add(splitPane, BorderLayout.CENTER);
        
        JPanel pnlLeft = new JPanel(new BorderLayout());
        
        JPanel pnlControls = new JPanel();
        pnlControls.setLayout(new MigLayout("", "[][]push[][]", ""));
        
        txtTim = new JTextField(15);
        txtTim.putClientProperty(FlatClientProperties.PLACEHOLDER_TEXT, "Tìm theo mã lịch chiếu");
        pnlControls.add(txtTim, "w 200!");
        
        btnTim = new JButton("Tìm");
        btnTim.setIcon(new FlatSVGIcon("gui/icon/svg/search.svg", 16, 16));
        pnlControls.add(btnTim);
        
        cboLoaiHienThi = new JComboBox<>(new String[]{"Tất cả", "Sắp chiếu", "Đang chiếu", "Đã chiếu"});
        pnlControls.add(cboLoaiHienThi);
        
        btnThem = new JButton("Thêm mới");
        btnThem.setIcon(new FlatSVGIcon("gui/icon/svg/add.svg", 16, 16));
        pnlControls.add(btnThem);
        
        btnSua = new JButton("Sửa");
        btnSua.setIcon(new FlatSVGIcon("gui/icon/svg/edit.svg", 16, 16));
        pnlControls.add(btnSua);
        
        btnXoa = new JButton("Xóa");
        btnXoa.setIcon(new FlatSVGIcon("gui/icon/svg/delete.svg", 16, 16));
        pnlControls.add(btnXoa);
        
        pnlLeft.add(pnlControls, BorderLayout.NORTH);
        
        String[] header = {"Mã lịch chiếu", "Mã phòng", "Mã phim", "Giờ bắt đầu", "Giờ kết thúc", "Giá một ghế"};
        tableModel = new DefaultTableModel(header, 0);
        table = new JTable(tableModel);
        table.setRowHeight(25);
        JScrollPane scroll = new JScrollPane(table);
        pnlLeft.add(scroll, BorderLayout.CENTER);
        
        table.getTableHeader().putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.putClientProperty(FlatClientProperties.STYLE_CLASS, "table_style");
        table.getTableHeader().setDefaultRenderer(getAlignmentCellRender(table.getTableHeader().getDefaultRenderer(), true));
        table.setDefaultRenderer(Object.class, getAlignmentCellRender(table.getDefaultRenderer(Object.class), false));
        
        pnlChiTiet = new JPanel();
        pnlChiTiet.setLayout(new MigLayout("wrap 2", "[][grow]", "[]10[]10[]10[]10[]"));
        pnlChiTiet.setBorder(BorderFactory.createTitledBorder("Chi tiết phim"));
        
        pnlChiTiet.add(new JLabel("Tên Phim:"), "right");
        lblTenPhim = new JLabel("");
        pnlChiTiet.add(lblTenPhim, "grow");
        
        pnlChiTiet.add(new JLabel("Thể Loại:"), "right");
        lblTheLoai = new JLabel("");
        pnlChiTiet.add(lblTheLoai, "grow");
        
        pnlChiTiet.add(new JLabel("Thời Lượng:"), "right");
        lblThoiLuong = new JLabel("");
        pnlChiTiet.add(lblThoiLuong, "grow");
        
        pnlChiTiet.add(new JLabel("Đạo Diễn:"), "right");
        lblDaoDien = new JLabel("");
        pnlChiTiet.add(lblDaoDien, "grow");
        
        splitPane.setLeftComponent(pnlLeft);
        splitPane.setRightComponent(pnlChiTiet);
        splitPane.setDividerLocation(800);
        
        table.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = table.getSelectedRow();
                    if (selectedRow != -1) {
                        updateMovieDetails(selectedRow);
                    }
                }
            }
        });
        
        btnTim.addActionListener(this);
        cboLoaiHienThi.addActionListener(this);
        btnThem.addActionListener(this);
        btnSua.addActionListener(this);
        btnXoa.addActionListener(this);
        
        loadLichChieuData();
    }
    
    private void loadLichChieuData() {
        dsLichChieu = lichChieuDAO.getTatCaLichChieu();
        updateTableData();
    }
    
    private void updateTableData() {
        tableModel.setRowCount(0);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm");
        for (LichChieu lc : dsLichChieu) {
            tableModel.addRow(new Object[]{
                lc.getMaLichChieu(),
                lc.getMaPhong(),
                lc.getMaPhim(),
                lc.getGioBatDau().format(formatter),
                lc.getGioKetThuc().format(formatter),
                String.format("%,.0f", lc.getGiaMotGhe())
            });
        }
    }
    
    private void updateMovieDetails(int selectedRow) {
        String maPhim = (String) table.getValueAt(selectedRow, 2);
        Phim phim = phimDAO.getPhimTheoMaDuaVoDeTail(maPhim);
        if (phim != null) {
            lblTenPhim.setText(phim.getTenPhim());
            lblTheLoai.setText(phim.getTheLoai());
            lblThoiLuong.setText(phim.getThoiLuong() + " phút");
            lblDaoDien.setText(phim.getDaoDien());
        } else {
            lblTenPhim.setText("");
            lblTheLoai.setText("");
            lblThoiLuong.setText("");
            lblDaoDien.setText("");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        Object o = e.getSource();
        if (o == btnTim) {
            String maLichChieu = txtTim.getText().trim();
            if (!maLichChieu.isEmpty()) {
                dsLichChieu = lichChieuDAO.getLichChieuTheoMa(maLichChieu);
                updateTableData();
            } else {
                loadLichChieuData();
            }
        } else if (o == cboLoaiHienThi) {
            String loaiHienThi = (String) cboLoaiHienThi.getSelectedItem();
            LocalDateTime now = LocalDateTime.now();
            switch (loaiHienThi) {
                case "Sắp chiếu":
                    dsLichChieu = lichChieuDAO.getLichChieuTheoTrangThai(now, true);
                    break;
                case "Đang chiếu":
                    dsLichChieu = lichChieuDAO.getLichChieuDangChieu(now);
                    break;
                case "Đã chiếu":
                    dsLichChieu = lichChieuDAO.getLichChieuTheoTrangThai(now, false);
                    break;
                default:
                    loadLichChieuData();
                    break;
            }
            updateTableData();
        } else if (o == btnThem) {
        	ThemLichChieuDialog themLT = new ThemLichChieuDialog(QuanLyLichChieuGUI);
        	themLT.setModal(true);
        	themLT.setVisible(true);
            }
        else if (o == btnSua) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String maLichChieu = (String) table.getValueAt(selectedRow, 0);
                LichChieu lichChieu = lichChieuDAO.getLichChieuTheoMa(maLichChieu).get(0);
                SuaLichChieuDialog SuaLT = new SuaLichChieuDialog(QuanLyLichChieuGUI, lichChieu);
                SuaLT.setModal(true);
                SuaLT.setVisible(true);
                SuaLT.setVisible(true);
                if (SuaLT.isDataChanged()) {
                    loadLichChieuData();
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một lịch chiếu để sửa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        } else if (o == btnXoa) {
            int selectedRow = table.getSelectedRow();
            if (selectedRow != -1) {
                String maLichChieu = (String) table.getValueAt(selectedRow, 0);
                int confirm = JOptionPane.showConfirmDialog(this, "Bạn có chắc chắn muốn xóa lịch chiếu này?", "Xác nhận xóa", JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    boolean success = lichChieuDAO.xoaLichChieu(maLichChieu);
                    if (success) {
                        JOptionPane.showMessageDialog(this, "Xóa lịch chiếu thành công.", "Thông báo", JOptionPane.INFORMATION_MESSAGE);
                        loadLichChieuData();
                    } else {
                        JOptionPane.showMessageDialog(this, "Xóa lịch chiếu thất bại.", "Lỗi", JOptionPane.ERROR_MESSAGE);
                    }
                }
            } else {
                JOptionPane.showMessageDialog(this, "Vui lòng chọn một lịch chiếu để xóa.", "Thông báo", JOptionPane.WARNING_MESSAGE);
            }
        }
    }
    
    @SuppressWarnings("serial")
    private TableCellRenderer getAlignmentCellRender(TableCellRenderer oldRender, boolean header) {
        return new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected,
                    boolean hasFocus, int row, int column) {
                Component com = oldRender.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
                if (com instanceof JLabel) {
                    JLabel label = (JLabel) com;
                    label.setHorizontalAlignment(SwingConstants.CENTER);
                }
                return com;
            }
        };
    }
}