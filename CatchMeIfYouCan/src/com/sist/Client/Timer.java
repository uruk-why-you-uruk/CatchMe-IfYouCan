package com.sist.Client;

import javax.swing.*;

import com.sist.Client.Client2.MyPanel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Timer extends JFrame implements ActionListener{
	Image back;
	JPanel draw;
	ImageIcon img;
	Timer() { 
		// 선언
//		back=Toolkit.getDefaultToolkit().getImage("image\\time.gif");
		back=Toolkit.getDefaultToolkit().getImage("image\\time.gif");
		    
		draw = new JPanel();
		// 배치
		  
		draw.setVisible(true);
		setSize(500, 500);
		setVisible(true);
	}
	public static void main(String[] args) throws InterruptedException
	  {
		  Timer a = new Timer();
		  int timet= 140;
		  long delay = timet * 1000;

		  do
		  {
	      int minutes = timet / 60;
	      int seconds = timet % 60;
	      System.out.println(minutes+" : " + seconds);
	      Thread.sleep(1000);
	      timet = timet - 1;
	      delay = delay - 1000;
	    }
	    while (delay != 0);
	    System.out.println("Time's Up!");
	  }
	  
	public void paint(Graphics g)
	 {
	  g.drawImage(back, 0, 0, this);
	 }

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}	
	  
}

