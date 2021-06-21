package Cipher;
import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.ItemSelectable; 
import java.awt.event.ItemEvent; 
import java.awt.event.ItemListener;


public class Cipher extends JFrame {

	private JPanel contentPane;
	private JTextField IpTex;
	private JTextField KeyTex;
	private JTextField AnswersT;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Cipher frame = new Cipher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			} 
		});
	}

	/**
	 * Create the frame.
	 */
	public Cipher() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 507, 519);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("\u9009\u62E9\u7B97\u6CD5\uFF1A");
		lblNewLabel.setBounds(14, 49, 91, 18);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("\u9009\u62E9\u6A21\u5F0F\uFF1A");
		lblNewLabel_1.setBounds(14, 13, 91, 18);
		contentPane.add(lblNewLabel_1);
		
		
		
		JPanel panel = new JPanel();
		panel.setBounds(87, 49, 388, 127);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JRadioButton CCipher = new JRadioButton("\u51EF\u6492\u5BC6\u7801",true);
		CCipher.setBounds(0, 9, 157, 27);
		panel.add(CCipher);
		
		JRadioButton LDcipher = new JRadioButton("\u5B57\u6BCD\u5012\u6392\u5BC6\u7801");
		LDcipher.setBounds(0, 32, 157, 27);
		panel.add(LDcipher);
		
		JRadioButton STCipher = new JRadioButton("\u5355\u8868\u7F6E\u6362\u5BC6\u7801");
		STCipher.setBounds(0, 64, 157, 27);
		panel.add(STCipher);
		
		JRadioButton VGCipher = new JRadioButton("\u7EF4\u5409\u5C3C\u4E9A\u5BC6\u7801");
		VGCipher.setBounds(0, 91, 157, 27);
		panel.add(VGCipher);
		
		JRadioButton CCiphern1 = new JRadioButton("\u8F6C\u6362\u5BC6\u7801\uFF08\u6570\u5B57\u5012\u6392\uFF09");
		CCiphern1.setBounds(186, 19, 192, 27);
		panel.add(CCiphern1);
		
		JRadioButton CCiphern2 = new JRadioButton("\u8F6C\u6362\u5BC6\u7801\uFF08\u6570\u5B57\u884C\u5217\uFF09");
		CCiphern2.setBounds(186, 51, 192, 27);
		panel.add(CCiphern2);
		
		JRadioButton CCipheralp = new JRadioButton("\u8F6C\u6362\u5BC6\u7801\uFF08\u5B57\u6BCD\u52A0\u5BC6\uFF09");
		CCipheralp.setBounds(186, 83, 192, 27);
		panel.add(CCipheralp);
		
		ButtonGroup btnGroup = new ButtonGroup();

		// 添加单选按钮到按钮
		btnGroup.add(CCipher);
		btnGroup.add(LDcipher);
		btnGroup.add(STCipher);
		btnGroup.add(VGCipher);
		btnGroup.add(CCiphern1);
		btnGroup.add(CCiphern2);
		btnGroup.add(CCipheralp);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBounds(25, 203, 450, 167);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		JLabel label = new JLabel("\u8F93\u5165");
		label.setBounds(14, 35, 72, 18);
		panel_2.add(label);
		
		IpTex = new JTextField();
		IpTex.setBounds(65, 32, 372, 24);
		panel_2.add(IpTex);
		IpTex.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("\u5BC6\u94A5\uFF1A");
		lblNewLabel_2.setBounds(14, 99, 72, 18);
		panel_2.add(lblNewLabel_2);
		
		KeyTex = new JTextField();
		KeyTex.setBounds(65, 96, 372, 24);
		panel_2.add(KeyTex);
		KeyTex.setColumns(10);
		
		AnswersT = new JTextField();
		AnswersT.setBounds(65, 133, 372, 24);
		panel_2.add(AnswersT);
		AnswersT.setColumns(10);
//		AnswersT.setEnabled(false);
		
		JLabel label_1 = new JLabel("\u89E3\u7B54");
		label_1.setBounds(14, 136, 72, 18);
		panel_2.add(label_1);
		
		JComboBox methodBox1 = new JComboBox();
		methodBox1.setModel(new DefaultComboBoxModel(new String[] {"\u52A0\u5BC6", "\u89E3\u5BC6"}));
		methodBox1.setBounds(119, 10, 91, 24);
		contentPane.add(methodBox1);
		
		JButton btnNewButton = new JButton("\u751F\u6210");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				String Methods = methodBox1.getSelectedItem().toString();//记录选择的加解密
				int mark=0;//记录所选方法
				if(CCipher.isSelected())mark=1;
				else if(LDcipher.isSelected())mark=2;
				else if(STCipher.isSelected())mark=3;
				else if(VGCipher.isSelected())mark=4;
				else if(CCiphern1.isSelected())mark=5;
				else if(CCiphern2.isSelected())mark=6;
				else if(CCipheralp.isSelected())mark=7;
				String password = IpTex.getText();
				String key =KeyTex.getText();
				String answer="";
				if(Methods=="加密") {
		        	Jiami jm1 = new Jiami();
		        	answer =jm1.Fuc(mark,password,key);
		        }else if(Methods=="解密") {
		        	Jiemi jem1 = new Jiemi(); 	
		        	answer =jem1.Fuc(mark,password,key);
		        }else JOptionPane.showMessageDialog(null,"选项错误");
				if(mark==2&&password.length()==0) {
					JOptionPane.showMessageDialog(null,"密文为空！");
				}else if(mark!=2&&(password.length()==0||key.length()==0))JOptionPane.showMessageDialog(null,"密文或密码为空！");
				else {
					AnswersT.setText(answer);
				} 
				
			}
		});
		btnNewButton.setBounds(167, 400, 113, 27);
		contentPane.add(btnNewButton);
	}
}
