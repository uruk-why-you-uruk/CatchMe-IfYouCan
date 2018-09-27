package com.sist.Vo;
import javax.swing.*;
import java.awt.*;
import javax.swing.border.*;

public class CharLabelVO extends JPanel{
	public JLabel state,rank,score,id,image;
	public ImageIcon img,img_rank;
	
	public JLabel img_la;
	public JPanel charinfo_Panel;
	public Color a = new Color(0,0,0,0);
	MatteBorder b6 = new MatteBorder(0,0,0,20,a);
	MatteBorder b7 = new MatteBorder(0,23,8,0,a);
	MatteBorder b8 = new MatteBorder(0,0,0,0,a);	
	public CharLabelVO()
	{
		setLayout(new FlowLayout());
		charinfo_Panel = new JPanel();
		state = new JLabel(" ");
		this.id = new JLabel(" ");
		ImageIcon a = new ImageIcon("image\\RANK\\"+0+".png");
		this.rank = new JLabel(" ");
		this.rank.setSize(90, 30);
		this.score = new JLabel(" "); 
		this.score.setBorder(b7);
		//this.img = new ImageIcon("image\\"+img+"\\"+rank+".png");
		this.img = new ImageIcon("image\\char_mini\\0.png");
		
		this.img_la = new JLabel(" ");
		this.img_la.setSize(90, 110);
		b6.isBorderOpaque();
		this.img_la.setBorder(b6);
		this.img_la.setBackground(Color.BLACK);
		this.img_la.setVisible(true);
		charinfo_Panel.setLayout(new GridLayout(4, 1,1,1));



		charinfo_Panel.add(state);
		charinfo_Panel.add(this.id);
		charinfo_Panel.add(this.rank);
		charinfo_Panel.add(this.score);
		
		charinfo_Panel.setVisible(true);
		//charinfo_Panel.setBackground(Color.gray);
		charinfo_Panel.setOpaque(false);
		this.add(this.img_la);
		this.add(charinfo_Panel);
		this.setVisible(false);
		this.setSize(170, 100);
		this.setBackground(Color.CYAN);
		
	}
	public void setCharVO(CharVO char_if) {
		id.setText(char_if.getId());
		this.rank.setIcon(new ImageIcon("image\\RANK\\"+char_if.getRank()+".png"));
		this.img = new ImageIcon("image\\char_mini\\"+char_if.getIcon()+".png");
		this.img_la.setIcon(img);
	}
		
	
	
}
