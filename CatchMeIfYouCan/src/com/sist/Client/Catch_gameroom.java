package com.sist.Client;
import javax.swing.*;
import com.sist.Vo.*;
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
	CharVO[] player = new CharVO[8];
	CharLabelVO[] char_group = new CharLabelVO[8];
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
			player[i] = new CharVO();
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
			//ImageIcon img2 = new ImageIcon("image\\nickname.png");
			char_group[i] = new CharLabelVO();
			char_group[i].setOpaque(false);
			
			
		}
		for(int i=0;i<8;i++)
		{
			add(char_group[i]);
			
		}	
		
		
		add(draw);
		add(tf);
		add(timer);
		
		char_group[0].setBounds(30, 130, 180, 110); 
		char_group[1].setBounds(30, 250, 180, 110);
		char_group[2].setBounds(30, 370, 180, 110);
		char_group[3].setBounds(30, 490, 180, 110);
	    char_group[4].setBounds(1005, 130, 180, 110);
	    char_group[5].setBounds(1005, 250, 180, 110);
	    char_group[6].setBounds(1005, 370, 180, 110);
	    char_group[7].setBounds(1005, 490, 180, 110);
	    
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
