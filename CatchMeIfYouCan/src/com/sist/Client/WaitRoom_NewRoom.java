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
	Image back; // 백그라운드 배경
	JTextField roomName;
	JPasswordField roomPsw; // 방이름, 패스워드 입력필드
	JRadioButton open, notopen;
	String[] personnel = { "4", "5", "6", "7", "8" }; // 인원수 콤보박스에 붙이기 위한 문자열 배열
	JComboBox personnel_Combo; // 인원수 콤보박스
	
	ImageIcon okButtonIcon, noButtonIcon;// 각버튼 이미지
	JButton okButton;// 만들기 버튼
	JButton noButton;// 취소버튼

	
	public WaitRoom_NewRoom() {
		setContentPane(new JLabel(new ImageIcon("image\\newroom.png")));
        setLayout(null);

		// 방이름 설정
		roomName = new JTextField("");
		roomName.setBounds(170, 109, 320, 20);
		add(roomName);

		open = new JRadioButton("공개");
		open.setOpaque(false);
		open.setSelected(true);
		notopen = new JRadioButton("비공개");
		notopen.setOpaque(false);
		

		// 패스워드 설정
		roomPsw = new JPasswordField("");
		roomPsw.setBounds(170, 179, 320, 20);
		add(roomPsw);

		// 방인원 설정
		personnel_Combo = new JComboBox(personnel);
		personnel_Combo.setBounds(170, 214, 80, 20);
		add(personnel_Combo);

		// 만들기, 취소버튼 생성
		okButtonIcon = new ImageIcon("image\\newroom_ok_btn.png");
		noButtonIcon = new ImageIcon("image\\newroom_no_btn.png");
		okButton = new JButton("", okButtonIcon);
		noButton = new JButton("", noButtonIcon);
		/////////////////////////////////////////////
		// 방만들기버튼 설정
		okButton.setBounds(205, 300, 85, 40);
		/*okButton.setOpaque(false);
		okButton.setBorderPainted(false);
		okButton.setFocusPainted(false);
		okButton.setContentAreaFilled(false);*/
		okButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 모양
		add(okButton);
		/////////////////////////////////////////
		// 취소버튼 설정
		noButton.setBounds(305, 298, 85, 40);
		/*noButton.setOpaque(false);
		noButton.setBorderPainted(false);
		noButton.setFocusPainted(false);
		noButton.setContentAreaFilled(false);*/
		noButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // 마우스 커서 모양
		add(noButton);
		
		
		/*JPanel p1=new JPanel();
		p1.add(okButton);
		p1.setBounds(205, 300, 85, 40);
		add(p1);
		JPanel p2=new JPanel();
		p2.add(noButton);
		p2.setBounds(305, 298, 85, 40);				
		add(p2);*/
		
		// 그룹으로 묶어야 하나만 선택된다.
		ButtonGroup bg = new ButtonGroup();
		bg.add(open);
		bg.add(notopen);
		open.setBounds(170, 148, 60, 20);
		notopen.setBounds(235, 148, 80, 20);
		add(open);
		add(notopen);
		
		setSize(600, 350);
		setUndecorated(true);
		setResizable(false); // 화면 못 줄이게하기
		open.addActionListener(this);
		notopen.addActionListener(this);
	}
	

	class myPanel extends JPanel {
		public void paint(Graphics g) {
			g.drawImage(img, 0, 0, null);
		}
	}

	public void actionPerformed(ActionEvent e) { // 방만들기 창 닫기.
		if (e.getSource() == notopen) {// 비공개가 눌리면 변화되는 것들.
			System.out.println("비공개");
			roomPsw.setBackground(Color.WHITE);
			roomPsw.setEditable(true);
		}
		if (e.getSource() == open) {// 공개시 변화되는것.
			System.out.println("공개");
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