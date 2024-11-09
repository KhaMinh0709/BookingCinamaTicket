package gui.app.doAn;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.border.*;

public class QuanLyDoAnGUI extends JPanel {
    private JTextField searchField;
    private JPanel productsPanel;
    
    public QuanLyDoAnGUI() {
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
        addProductCard("bắp rang bơ", 99, 121.0, "images/bap_rang_bo.jpg");
        addProductCard("bắp rang bơ lớn", 1000, 150.9, "images/bap_rang_bo.jpg");
        addProductCard("bắp thường", 1, 99.9, "images/bap_rang_bo.jpg");
        addProductCard("combo bắp lớn", 22, 200.9, "images/bap_rang_bo.jpg");
        addProductCard("combo bắp nhỏ", 55, 180.0, "images/bap_rang_bo.jpg");
        addProductCard("combo bắp thường", 32, 110.0, "images/bap_rang_bo.jpg");
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