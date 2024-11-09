package gui.app.nuocUong;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class QuanLyNuocUong extends JPanel {
    private JTextField searchField;
    private JPanel productsPanel;
    
    public QuanLyNuocUong() {
        setLayout(new BorderLayout(10, 10));
        setBorder(new EmptyBorder(10, 10, 10, 10));
        
        // Top panel with search and add button
        JPanel topPanel = new JPanel(new BorderLayout(10, 0));
        searchField = new JTextField();
        JButton searchButton = new JButton("tìm");
        JPanel searchPanel = new JPanel(new BorderLayout());
        searchPanel.add(searchField, BorderLayout.CENTER);
        searchPanel.add(searchButton, BorderLayout.EAST);
        
        JButton addProductButton = new JButton("thêm SP");
        addProductButton.setPreferredSize(new Dimension(120, 30));
        
        topPanel.add(searchPanel, BorderLayout.CENTER);
        topPanel.add(addProductButton, BorderLayout.EAST);
        
        // Products panel with grid layout
        productsPanel = new JPanel(new GridLayout(0, 3, 10, 10));
        
        // Add sample products
        addProductCard("coca cola", 100, 30.5, "images/coca_cola.png");
        addProductCard("sting", 1000, 35.0, "images/sting.jpg");
        addProductCard("nước suối", 1, 20.0, "images/aquafina.jpg");
        addProductCard("pepsi", 22, 30.5, "images/pepsi.jpg");
        addProductCard("combo X2 coca", 55, 70.0, "images/coca_cola.png");
        addProductCard("combo X2 Sting", 32, 70.0, "images/sting.jpg");
        addProductCard("combo bắp nước", 85, 300.0, "images/combo_bap_nuoc.png");
        
        // Scroll pane for products
        JScrollPane scrollPane = new JScrollPane(productsPanel);
        scrollPane.setBorder(null);
        
        // Add components to main panel
        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);
    }
    
    private void addProductCard(String name, int quantity, double unitPrice, String imagePath) {
        JPanel card = new JPanel(new BorderLayout(5, 5));
        card.setBorder(new LineBorder(Color.LIGHT_GRAY));
        card.setBackground(Color.WHITE);
        
        // Product image (placeholder)
        ImageIcon originalIcon = new ImageIcon(imagePath);
        JLabel imageLabel = new JLabel(resizeImage(originalIcon, 100, 100));
        imageLabel.setHorizontalAlignment(JLabel.CENTER);
        
        // Product details
        JPanel detailsPanel = new JPanel(new GridLayout(4, 1, 5, 5));
        detailsPanel.setBackground(Color.WHITE);
        detailsPanel.add(new JLabel(name, JLabel.CENTER));
        detailsPanel.add(new JLabel("số lượng: " + quantity, JLabel.CENTER));
        detailsPanel.add(new JLabel("đơn giá: " + unitPrice, JLabel.CENTER));
        
        // Buttons panel
        JPanel buttonsPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 5, 0));
        buttonsPanel.setBackground(Color.WHITE);
        buttonsPanel.add(new JButton("xóa"));
        buttonsPanel.add(new JButton("cập nhật"));
        buttonsPanel.add(new JButton("thêm"));
        
        card.add(imageLabel, BorderLayout.NORTH);
        card.add(detailsPanel, BorderLayout.CENTER);
        card.add(buttonsPanel, BorderLayout.SOUTH);
        
        productsPanel.add(card);
    }
    
    private ImageIcon resizeImage(ImageIcon icon, int width, int height) {
        Image img = icon.getImage();
        Image resizedImg = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
        return new ImageIcon(resizedImg);
    }
    
}
