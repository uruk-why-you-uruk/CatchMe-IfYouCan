package com.sist.Client;

// �����찡 ��ġ�ϴ� ��ġ
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.net.Socket;
import java.util.Vector;

// JDK 1.2 ������ ���ߵ� ���̺귯��(java), ���Ŀ� ���ߵ� ���̺귯��(javax => xml,sql)
// ������ ����� ��ü ����ϰ� �ʹ�. : ���
/*
 *      ������
 *      JFrame : �Ϲ� â(������)
 *      JPanel 
 *      ======= ������ ���� ���̴� ��
 *      JDialog : �ݱ��ư�� ������
 *      JWindow : Ÿ��Ʋ�ٰ� ���� �� => ȫ���Ҷ� 
 */
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.sist.common.Function;

public class MyWindow extends JFrame implements ActionListener, Runnable{
   // ������ ����
   MainView mv = new MainView();
   WaitRoom wr = new WaitRoom();
   WaitRoom_NewRoom wrn = new WaitRoom_NewRoom();
   Character_select cs = new Character_select();
   Catch_gameroom gr = new Catch_gameroom();
   CardLayout card = new CardLayout();
   Socket s;
   BufferedReader in;
   OutputStream out;
   Vector<Point> vStart = new Vector<Point>();

   public MyWindow() {
      System.out.println("mywindow ����");
      setLayout(card);
      add("MV", mv);  
      add("CS", cs);
      add("GR", gr);
      add("WR", wr);
      
      // ������źκ�
      

      // ������ ũ��
      setSize(1251, 750);

<<<<<<< HEAD
		// ������ �����޶�
		setVisible(true);
		setResizable(false);// ũ�⺯�� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// �����ʿ� ������ ����
		new Thread(this).start();
		mv.b1.addActionListener(this);		
		cs.enter.addActionListener(this); 
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
		gr.out_btn.addActionListener(this);
		
	}  
=======
      // ������ �����޶�
      setVisible(true);
      setResizable(false);// ũ�⺯�� ����
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      mv.b1.addActionListener(this);      
      cs.enter.addActionListener(this); 
      wr.b1.addActionListener(this);
      wr.b2.addActionListener(this);
      gr.out_btn.addActionListener(this);
   }  
>>>>>>> branch 'master' of https://github.com/uruk-why-you-uruk/CatchMe-IfYouCan.git

   public static void main(String[] args) {
      System.out.println("mywindow main ����");
      try {
         //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
         System.out.println("jtatto ����");
      } catch (Exception ex) {
         System.out.println("jtatto ����ó��");
      }
      MyWindow a = new MyWindow();
      System.out.println("mywindow ����");
      a.setLocationRelativeTo(null);
   }

<<<<<<< HEAD
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mv.b1) {
			// ��ư ������ 
			 try
	         {
	            s=new Socket("211.238.142.66", 7339);
	            in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	               // byte ==> 2byte
	            out=s.getOutputStream();
	            System.out.println((Function.LOGIN+"|"+mv.tf.getText()));
	            out.write((Function.LOGIN+"|"+mv.tf.getText()+"\n").getBytes());
	         }catch(Exception ex) {}
			 
=======
   @Override
   public void actionPerformed(ActionEvent e) {
      if (e.getSource() == mv.b1) {
         // ��ư ������ 
          try
            {
               s=new Socket("211.238.142.66", 7339);
               in=new BufferedReader(new InputStreamReader(s.getInputStream()));
                  // byte ==> 2byte
               out=s.getOutputStream();
            }catch(Exception ex) {}
            new Thread(this).start();
>>>>>>> branch 'master' of https://github.com/uruk-why-you-uruk/CatchMe-IfYouCan.git

         card.show(getContentPane(), "CS");
      }
      if (e.getSource() == cs.enter) { // ĳ���� ����ȭ�鿡�� ���͸� ������ ���Ƿ� �̵��Ѵ�.
         card.show(getContentPane(), "WR");
      }
      if (e.getSource() == wr.b1) { // ���ȭ�鿡�� �游��� ��ư�� ������ �游��� �������� ��������.
         wrn.setLocationRelativeTo(null);
         wrn.setVisible(true);
      }
      if (e.getSource() == wr.b2) {
         card.show(getContentPane(), "GR");
      }
      if(e.getSource() == gr.out_btn) {
         card.show(getContentPane(), "WR");
      }
   }

   @Override
   public void run() {
      // TODO Auto-generated method stub
      try
      {
       while(true)
         {
            String msg=in.readLine();
            System.out.println(msg);
            StringTokenizer st=new StringTokenizer(msg, "|");
            int no=Integer.parseInt(st.nextToken());
            switch(no)
            {
            
            }
         }
      }catch(Exception ex) {}
   }

}