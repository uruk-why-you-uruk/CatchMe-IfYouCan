package com.sist.Client;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class WaitRoom_NewRoom extends JFrame implements ActionListener {
	BufferedImage img = null;
	Image back; // ��׶��� ���
	JTextField roomName;
	JPasswordField roomPsw; // ���̸�, �н����� �Է��ʵ�
	JRadioButton open, notopen;
	String[] personnel = { "4", "5", "6", "7", "8" }; // �ο��� �޺��ڽ��� ���̱� ���� ���ڿ� �迭
	JComboBox personnel_Combo; // �ο��� �޺��ڽ�
	
	ImageIcon okButtonIcon, noButtonIcon;// ����ư �̹���
	JButton okButton;// ����� ��ư
	JButton noButton;// ��ҹ�ư

	
	public WaitRoom_NewRoom() {
		setContentPane(new JLabel(new ImageIcon("image\\newroom.png")));
        setLayout(null);

		// ���̸� ����
		roomName = new JTextField("");
		roomName.setBounds(170, 109, 320, 20);
		add(roomName);

		open = new JRadioButton("����");
		open.setOpaque(false);
		open.setSelected(true);
		notopen = new JRadioButton("�����");
		notopen.setOpaque(false);
		

		// �н����� ����
		roomPsw = new JPasswordField("");
		roomPsw.setBounds(170, 179, 320, 20);
		add(roomPsw);

		// ���ο� ����
		personnel_Combo = new JComboBox(personnel);
		personnel_Combo.setBounds(170, 214, 80, 20);
		add(personnel_Combo);

		// �����, ��ҹ�ư ����
		okButtonIcon = new ImageIcon("image\\newroom_ok_btn.png");
		noButtonIcon = new ImageIcon("image\\newroom_no_btn.png");
		okButton = new JButton("", okButtonIcon);
		noButton = new JButton("", noButtonIcon);
		/////////////////////////////////////////////
		// �游����ư ����
		okButton.setBounds(205, 300, 85, 40);
		/*okButton.setOpaque(false);
		okButton.setBorderPainted(false);
		okButton.setFocusPainted(false);
		okButton.setContentAreaFilled(false);*/
		okButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ�� ���
		add(okButton);
		/////////////////////////////////////////
		// ��ҹ�ư ����
		noButton.setBounds(305, 298, 85, 40);
		/*noButton.setOpaque(false);
		noButton.setBorderPainted(false);
		noButton.setFocusPainted(false);
		noButton.setContentAreaFilled(false);*/
		noButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ���콺 Ŀ�� ���
		add(noButton);
		
		
		/*JPanel p1=new JPanel();
		p1.add(okButton);
		p1.setBounds(205, 300, 85, 40);
		add(p1);
		JPanel p2=new JPanel();
		p2.add(noButton);
		p2.setBounds(305, 298, 85, 40);				
		add(p2);*/
		
		// �׷����� ����� �ϳ��� ���õȴ�.
		ButtonGroup bg = new ButtonGroup();
		bg.add(open);
		bg.add(notopen);
		open.setBounds(170, 148, 60, 20);
		notopen.setBounds(235, 148, 80, 20);
		add(open);
		add(notopen);
		
		setSize(600, 350);
		setUndecorated(true);
		setResizable(false); // ȭ�� �� ���̰��ϱ�
		open.addActionListener(this);
		notopen.addActionListener(this);
	}
	

	class myPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	public void actionPerformed(ActionEvent e) { // �游��� â �ݱ�.
		if (e.getSource() == notopen) {// ������� ������ ��ȭ�Ǵ� �͵�.
			System.out.println("�����");
			roomPsw.setBackground(Color.WHITE);
			roomPsw.setEditable(true);
		}
		if (e.getSource() == open) {// ������ ��ȭ�Ǵ°�.
			System.out.println("����");
			roomPsw.setBackground(Color.GRAY);
			roomPsw.setText("");
			roomPsw.setEditable(false);
			roomPsw.requestFocus();
		}
	}

	public static void main(String[] args) {
		new WaitRoom_NewRoom();
	}
	
}