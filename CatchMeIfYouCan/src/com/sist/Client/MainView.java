package com.sist.Client;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

public class MainView extends JPanel{
	Image back;
	JLabel la1,la2;
	JTextField tf;
	JPasswordField pf;
	JButton b1,b2;
	//frame.setLocationRelativeTo(null);
	MainView() {
		
		back = Toolkit.getDefaultToolkit().getImage("image\\back.png");
		Dimension backsize = Toolkit.getDefaultToolkit().getScreenSize();
		la1 = new JLabel("아이디");
		la2 = new JLabel("비밀번호");
		
		tf = new JTextField("아이디");
		pf = new JPasswordField("패스워드");
		
		
		b1 = new JButton(new ImageIcon("image\\Login.png"));
		b1.setBorderPainted(false); 
		b1.setFocusPainted(false); 
		b1.setContentAreaFilled(false);
		


		b2 = new JButton("취소");
		//JPanel login_back = new JPanel();
		//login_back.setBackground(new Color(255,255,255,5));
		
		add(la1);
		add(la2);
		add(tf);
		add(pf);
		JPanel p = new JPanel();
		p.add(b1);
		p.add(b2);
		add(p);
		
		
		// 배치
		setLayout(null);// 배치를 사용하지 않고 직접 배치
		System.out.println(backsize.getWidth()+","+backsize.getHeight());
		la1.setBounds(backsize.height/2,30,80,30);
		la1.setForeground(Color.BLACK);
		la2.setBounds(20,65,80,30);
		la2.setForeground(Color.BLACK);
		tf.setBounds(105,30,100,30);
		pf.setBounds(105, 65, 100, 30);
		
		p.setBounds(20, 100, 400, 400);
		p.setOpaque(false);
		b1.setBounds(40,105,70,30);
		b2.setBounds(90,105,40,30);
		/*
		add(b1);
		add(b2);
		*/
	}
	@Override
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(back,0,0,getWidth(),getHeight(),this);
	}
	

}
