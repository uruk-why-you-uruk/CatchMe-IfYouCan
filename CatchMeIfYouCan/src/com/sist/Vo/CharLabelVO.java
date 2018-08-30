package com.sist.Vo;
import javax.swing.*;
import java.awt.*;

public class CharLabelVO extends JPanel{
	JLabel state,rank,score,id,image;
	ImageIcon img,img_rank;
	
	JLabel img_la;
	JPanel charinfo_Panel;
	public CharLabelVO()
	{
		charinfo_Panel = new JPanel();
		state = new JLabel();
		this.id = new JLabel("À±¿øÅ¹");
		//ImageIcon a = new ImageIcon("image\\rank\\"+rank+".png");
		this.rank = new JLabel();
		this.rank.setSize(90, 30);
		this.score = new JLabel("0"); 
		//this.img = new ImageIcon("image\\"+img+"\\"+rank+".png");
		this.img_la = new JLabel(/*this.img*/);
		this.img_la.setSize(90, 100);
		charinfo_Panel.setLayout(new GridLayout(1, 4));
		charinfo_Panel.add(state);
		charinfo_Panel.add(this.rank);
		charinfo_Panel.add(state);
		charinfo_Panel.add(state);
		charinfo_Panel.setVisible(true);
		charinfo_Panel.setOpaque(false);
		this.add(this.img_la);
		this.add(charinfo_Panel);
		this.setVisible(true);
		this.setSize(170, 100);
		this.setOpaque(false);
	}
	public CharLabelVO(CharVO char_if) {
		charinfo_Panel = new JPanel();
		state = new JLabel();
		this.id = new JLabel(char_if.getId());
		this.rank = new JLabel(new ImageIcon("image\\rank\\"+rank+".png"));
		this.score = new JLabel("0");
		this.img = new ImageIcon("image\\"+img+"\\"+rank+".png");
		this.img_la = new JLabel(this.img);
		charinfo_Panel.setLayout(new GridLayout(1, 4));
		charinfo_Panel.add(state);
		charinfo_Panel.add(this.rank);
		charinfo_Panel.add(state);
		charinfo_Panel.add(state);
		charinfo_Panel.setVisible(true);
		charinfo_Panel.setOpaque(false);
		this.add(this.img_la);
		this.add(charinfo_Panel);
		this.setVisible(true);
		this.setSize(170, 100);
		this.setOpaque(false);
		
		
		
	}
		
	
	
}
