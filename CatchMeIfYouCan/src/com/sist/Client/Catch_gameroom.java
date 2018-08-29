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
		
		char_group[0].setBounds(50, 130, 180, 110);
		char_group[1].setBounds(50, 250, 180, 110);
		char_group[2].setBounds(50, 370, 180, 110);
		char_group[3].setBounds(50, 490, 180, 110);
	    char_group[4].setBounds(1025, 130, 180, 110);
	    char_group[5].setBounds(1025, 250, 180, 110);
	    char_group[6].setBounds(1025, 370, 180, 110);
	    char_group[7].setBounds(1025, 490, 180, 110);
	    draw.setBounds(265, 130, 725, 320);
	    timer.setBounds(265, 520, 120, 80);
	    tf.setBounds(700, 560, 300, 30);
		
		setVisible(true);
		
	}
	


}
