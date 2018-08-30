package com.sist.Client;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;


public class Character_select extends JPanel{
	
	JLabel venu,result;
	int char_cnt = 15;
	JButton[] jb = new JButton[char_cnt];
	JButton enter;
	ImageIcon[] img1 = new ImageIcon[15];
	ImageIcon img2;
	ImageIcon img3;
	ImageIcon[] img4 = new ImageIcon[15];
	Image back; 
	
	
	
	Character_select()
	{
		setLayout(null);
		for(int i=0;i<15;i++)
		{
			img1[i] = new ImageIcon("image\\char_mini\\"+i+".png");
			
		}
		
		for(int i=0;i<15;i++)
		{
			img4[i] = new ImageIcon("image\\char_max\\"+i+".png");
			
		}
		
		img2 = new ImageIcon("image\\enter.png");
		img3 = new ImageIcon("image\\enter_hand.png");
		back = Toolkit.getDefaultToolkit().getImage("image\\cs_back.png");
		result = new JLabel(img1[0]);
		enter = new JButton(img2);
		enter.setBorderPainted(false); 
		enter.setFocusPainted(false); 
		enter.setContentAreaFilled(false);
		add(result);
		for(int i=0;i<char_cnt;i++)
		{
			jb[i] = new JButton(img1[i]);
			jb[i].setBorderPainted(false); 
			jb[i].setFocusPainted(false); 
			jb[i].setContentAreaFilled(false);
		}
		jb[0].setBorderPainted(false); 
		jb[0].setFocusPainted(false); 
		jb[0].setContentAreaFilled(false);
		
		JPanel firstline = new JPanel();	
		firstline.setOpaque(false);
		for(int i=0;i<5;i++)
		{
			firstline.add(jb[i]);
		}
		add(firstline);
		
		JPanel secondline = new JPanel();
		secondline.setOpaque(false);
		for(int i=5;i<10;i++)
		{
			secondline.add(jb[i]);
		}
		add(secondline);
		
		JPanel thirdline = new JPanel();
		thirdline.setOpaque(false);
		for(int i=10;i<15;i++)
		{
			thirdline.add(jb[i]);
		}
		add(thirdline);

		for(int i=0;i<15;i++)
		{
			jb[i].addMouseListener(a);
		}
		
		
		firstline.setBounds(250,250,1000,125);
		secondline.setBounds(250,390,1000,125);
		thirdline.setBounds(250,530,1000,125);
		
		
		add(enter);
		
		result.setBounds(160,150,170,125);
		enter.setBounds(320, 250, 200, 200);
		enter.setRolloverIcon(img3);
			
	}
	protected void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		g.drawImage(back,0,0,getWidth(),getHeight(),this);
	}
	MouseListener a = new MouseListener() {
		
	
	public void mouseClicked(MouseEvent e) {
		for(int i=0;i<15;i++)
		{
			if(e.getSource()==jb[i])
			{
				result.setIcon(img4[i]);
			}
		}
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		if(e.getSource()==enter)
		{
			enter.setIcon(img3);			
		}
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		if(e.getSource()==enter)
		{
			enter.setIcon(img2);			
		}
		
	}
	};
}
