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
	JPanel draw,timer,color_Panel;  
	JLabel room_grade,chat;
	CharVO[] player = new CharVO[8];  
	CharLabelVO[] char_group = new CharLabelVO[8];
	//JLabel[] char_group = new JLabel[8];
	JTextArea ta;  
	JTextField tf;
	JButton[] color = new JButton[6];
	ImageIcon out_img;
	JButton out_btn;
	  
	Catch_gameroom(){
		setLayout(null);
		back = Toolkit.getDefaultToolkit().getImage("image\\gamm.jpg");

		out_img=new ImageIcon("image\\newroombtn.png");
	    out_btn=new JButton("",out_img);

	    out_btn.setBounds(1030, 600, 130, 50);
	    add(out_btn);
		
		
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
		color_Panel = new JPanel();
		color_Panel.setLayout(new FlowLayout());
		for(int i=0;i<color.length;i++)
		{
			ImageIcon img2 = new ImageIcon("image\\color\\"+(i+1)+".png");
			color[i] = new JButton(img2);
			color[i].setBorderPainted(false); 
			color[i].setFocusPainted(false); 
			color[i].setContentAreaFilled(false);
			color_Panel.add(color[i]);
			color_Panel.setOpaque(false);
		}
		
		for(int i=0;i<char_group.length;i++)
		{
			//ImageIcon img2 = new ImageIcon("image\\nickname.png");
			char_group[i] = new CharLabelVO();
			char_group[i].setOpaque(false);
			
			
		}
		for(int i=0;i<char_group.length;i++)
		{
			add(char_group[i]);
			
		}	
		
		add(color_Panel);
		add(draw);
		add(tf);
		add(timer);
		int y1= 112;
		//int y= 110;
		char_group[0].setBounds(45, y1, 180, 110); 
		char_group[1].setBounds(45, y1+120, 180, 110);
		char_group[2].setBounds(45, y1+240, 180, 110);
		char_group[3].setBounds(45, y1+360, 180, 110);
	    char_group[4].setBounds(1022, y1, 180, 110);
	    char_group[5].setBounds(1022, y1+120, 180, 110);
	    char_group[6].setBounds(1022, y1+240, 180, 110);
	    char_group[7].setBounds(1022, y1+360, 180, 110);
	    
	    color_Panel.setBounds(235, 490, 450, 60);
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
