package gui.app;

import java.awt.Component;
import java.awt.Font;
import java.time.LocalTime;

import javax.swing.GroupLayout;
import javax.swing.JFrame;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.WindowConstants;

import com.formdev.flatlaf.FlatLaf;
import com.formdev.flatlaf.extras.FlatAnimatedLafChange;
import com.formdev.flatlaf.fonts.roboto.FlatRobotoFont;
import com.formdev.flatlaf.themes.FlatMacDarkLaf;
import com.formdev.flatlaf.themes.FlatMacLightLaf;

import entity.NhanVien;
import gui.app.form.LoginForm;
import gui.app.form.MainForm;

public class Main extends JFrame {
	private static final long serialVersionUID = 1L;
	private static Main app;
//	private final LoginForm loginForm;
	private MainForm mainForm;

	private Main() {
//		initComponents();
//		loginForm = new LoginForm();
//		setContentPane(loginForm);
//		setExtendedState(JFrame.MAXIMIZED_BOTH);
//		app = this;
		
	    initComponents();

	    // Tạo Employee giả cho mục đích vào thẳng hệ thống
	    NhanVien defaultEmployee = new NhanVien();
	    defaultEmployee.setVaiTro("Quản lý");  // Bạn có thể đặt vai trò hoặc thông tin cần thiết khác

	    // Khởi tạo MainForm trực tiếp và đặt làm ContentPane
	    mainForm = new MainForm(defaultEmployee);
	    setContentPane(mainForm);

	    setExtendedState(JFrame.MAXIMIZED_BOTH);
	    app = this;
		
	}

	public static Main getInstance() {
		return app;
	}

	public MainForm getMainForm() {
		return mainForm;
	}

//	public LoginForm getLoginForm() {
//		return loginForm;
//	}

	public void createMainForm(NhanVien employee) {
		mainForm = new MainForm(employee);
	}

	public static void showMainForm(Component component) {
		component.applyComponentOrientation(app.getComponentOrientation());
		app.mainForm.showForm(component);
	}

	public static void setSelectedMenu(int index, int subIndex) {
		app.mainForm.setSelectedMenu(index, subIndex);
	}

//	public static void logout() {
//		app.loginForm.resetLogin();
//		FlatAnimatedLafChange.showSnapshot();
//		app.setContentPane(app.loginForm);
//		app.loginForm.applyComponentOrientation(app.getComponentOrientation());
//		SwingUtilities.updateComponentTreeUI(app.loginForm);
//		FlatAnimatedLafChange.hideSnapshotWithAnimation();
//	}

	private void initComponents() {
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setUndecorated(false);
		setResizable(false);

		GroupLayout layout = new GroupLayout(getContentPane());
		getContentPane().setLayout(layout);

		layout.setHorizontalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 719, Short.MAX_VALUE));
		layout.setVerticalGroup(
				layout.createParallelGroup(GroupLayout.Alignment.LEADING).addGap(0, 521, Short.MAX_VALUE));

		pack();
	}

	public static void main(String args[]) {
		FlatRobotoFont.install();
		FlatLaf.registerCustomDefaultsSource("gui.theme");
		UIManager.put("defaultFont", new Font(FlatRobotoFont.FAMILY, Font.PLAIN, 16));
		FlatMacLightLaf.setup();
		SwingUtilities.invokeLater(() -> new Main().setVisible(true));
	}

}
