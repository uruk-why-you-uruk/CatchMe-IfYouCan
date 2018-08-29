package com.sist.Client;
import javax.swing.*;
import java.awt.*;

class char_if
{
	JLabel id,rank,score,icon;
}

public class Catch_gameroom extends JPanel{
	
	JPanel draw,timer;
	JLabel room_grade,chat;
	char_if[] player = new char_if[8];
	JPanel[] char_group = new JPanel[8];
	JTextField tf;
	JButton[] color = new JButton[8];
	
	Catch_gameroom()
	{
		//초기값
		
		for(int i=0;i<8;i++)
		{
			player[i] = new char_if();
		}
		draw = new JPanel();
		draw.setBackground(Color.BLACK);
		timer = new JPanel();
		timer.setBackground(Color.yellow);
		room_grade = new JLabel();
		chat = new JLabel();
		tf = new JTextField();
		for(int i=0;i<8;i++)
		{
			color[i] = new JButton();
		}
		for(int i=0;i<8;i++)
		{
			char_group[i] = new JPanel();
			char_group[i].setBackground(Color.blue);
		}
		
		
		
		
		
		//배치
		setLayout(null);
		for(int i=0;i<8;i++)
		{
			add(char_group[i]);
			
		}	
		add(draw);
		add(tf);
		add(timer);
		
		char_group[0].setBounds(10, 15, 150, 150);
		char_group[4].setBounds(1000, 15, 150, 150);
		draw.setBounds(170, 15, 800, 300);
		timer.setBounds(180, 550, 100, 50);
		tf.setBounds(300, 600, 150, 30);
		
		setVisible(true);
		
	}
	


}
