package com.sist.Client;

import javax.swing.*;
import javax.swing.border.Border;
import javax.swing.border.LineBorder;

import java.awt.*;   //   Image
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
public class MainView extends JPanel implements MouseListener{
   Image back;
   JLabel la1,la2;
   JTextField tf;
   JPasswordField pf;
   JButton b1,b2;
   MainView() {
      back=Toolkit.getDefaultToolkit().getImage("image\\back.png");
      //   배치
      la1=new JLabel("아이디");
      la2=new JLabel("비밀번호");
      
      tf=new JTextField();
      pf=new JPasswordField();
      
      b1=new JButton("로그인");
      b2=new JButton("취소");
      
      
      
      //   배치
      setLayout(null); //   배치를 사용하지 않고 직접 배치
      la1.setForeground(Color.black);
      la1.setBounds(625,350,80,30);
      add(la1);
      
      la2.setForeground(Color.black);
      la2.setBounds(625,395,80,30);
      add(la2);
      
      tf.setBounds(558,350,180,30);
      tf.setBackground(Color.WHITE);
      add(tf);
      
      pf.setBounds(558,395,180,30);
      add(pf);
      
      JPanel p=new JPanel();
      p.setOpaque(false);
      p.add(b1);
      p.add(b2);
      p.setBounds(550,460,185,35);
      add(p);
      
      tf.addMouseListener(this);
   }
   @Override
   protected void paintComponent(Graphics g) {
      g.drawImage(back, 0,0,getWidth(),getHeight(),this);
   }
   @Override
   public void mouseClicked(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void mousePressed(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void mouseReleased(MouseEvent e) {
      // TODO Auto-generated method stub
      
   }
   @Override
   public void mouseEntered(MouseEvent e) {
      // TODO Auto-generated method stub
      if(e.getSource()==tf)
      {
         Border thickBorder = new LineBorder(new Color(255,255,200), 2);
         tf.setBorder(thickBorder);
      }
   }
   @Override
   public void mouseExited(MouseEvent e) {
      // TODO Auto-generated method stub
      if(e.getSource()==tf)
      {
         tf.setBorder(null);
      }
   }
   
   
}