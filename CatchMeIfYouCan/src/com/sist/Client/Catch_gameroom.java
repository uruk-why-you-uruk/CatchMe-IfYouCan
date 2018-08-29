package com.sist.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

class char_if
{
	JLabel id,rank,score,icon;
}

public class Catch_gameroom extends JPanel implements ActionListener{
	Image back;
	JPanel draw,timer;
	JLabel room_grade,chat;
	char_if[] player = new char_if[8];
	JPanel[] char_group = new JPanel[8];
	JTextArea ta;
	JTextField tf;
	JButton[] color = new JButton[8];
	
	Catch_gameroom(){
		setLayout(null);
		back = Toolkit.getDefaultToolkit().getImage("image\\gamm.jpg");

		
		ta = new JTextArea();
	    JScrollPane js3 = new JScrollPane(ta);
	    tf = new JTextField();
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
		for(int i=0;i<8;i++)
		{
			add(char_group[i]);
			
		}	
		
		
		add(draw);
		add(tf);
		add(timer);
		
		char_group[0].setBounds(50, 110, 180, 110);
		char_group[1].setBounds(50, 230, 180, 110);
		char_group[2].setBounds(50, 350, 180, 110);
		char_group[3].setBounds(50, 470, 180, 110);
	    char_group[4].setBounds(1025, 110, 180, 110);
	    char_group[5].setBounds(1025, 230, 180, 110);
	    char_group[6].setBounds(1025, 350, 180, 110);
	    char_group[7].setBounds(1025, 470, 180, 110);
	    draw.setBounds(265, 110, 725, 370);
	    timer.setBounds(265, 600, 150, 50);
	    js3.setBounds(700,500,290,100);
	    add(js3);
	    tf.setBounds(700, 620, 290, 30);
	    
		//
	    setLayout(null);
		setVisible(true);
		
		tf.addActionListener(this);
	}
	 @Override
	   protected void paintComponent(Graphics g) {
	      g.drawImage(back, 0, 0, getWidth(), getHeight(), this);

	   }

	 @Override
	   public void actionPerformed(ActionEvent e) { // 채팅을치면 채팅창에 입력된게 올라가는고
	      if (e.getSource() == tf) {
	         String s = tf.getText();
	         ta.append(s + "\n");
	         tf.setText("");
	      }
	   }
	


}
