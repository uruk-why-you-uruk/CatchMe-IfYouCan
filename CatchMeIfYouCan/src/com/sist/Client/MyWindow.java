package com.sist.Client;

// 윈도우가 위치하는 위치
import java.awt.CardLayout;
import java.awt.Point;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
import java.net.Socket;
import java.util.Vector;

// JDK 1.2 이전에 개발된 라이브러리(java), 이후에 개발된 라이브러리(javax => xml,sql)
// 윈도우 기능을 전체 사용하고 싶다. : 상속
/*
 *      윈도우
 *      JFrame : 일반 창(윈도우)
 *      JPanel 
 *      ======= 위에는 자주 쓰이는 것
 *      JDialog : 닫기버튼만 있을때
 *      JWindow : 타이틀바가 없는 것 => 홍보할때 
 */
import javax.swing.JFrame;
import javax.swing.UIManager;

import com.sist.common.Function;

public class MyWindow extends JFrame implements ActionListener, Runnable{
   // 윈도우 설정
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
      System.out.println("mywindow 실행");
      setLayout(card);
      add("MV", mv);  
      add("CS", cs);
      add("GR", gr);
      add("WR", wr);
      
      // 소켓통신부분
      

      // 윈도우 크기
      setSize(1251, 750);

<<<<<<< HEAD
		// 윈도우 보여달라
		setVisible(true);
		setResizable(false);// 크기변경 제한
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// 리스너와 쓰래드 시작
		new Thread(this).start();
		mv.b1.addActionListener(this);		
		cs.enter.addActionListener(this); 
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
		gr.out_btn.addActionListener(this);
		
	}  
=======
      // 윈도우 보여달라
      setVisible(true);
      setResizable(false);// 크기변경 제한
      setDefaultCloseOperation(EXIT_ON_CLOSE);
      mv.b1.addActionListener(this);      
      cs.enter.addActionListener(this); 
      wr.b1.addActionListener(this);
      wr.b2.addActionListener(this);
      gr.out_btn.addActionListener(this);
   }  
>>>>>>> branch 'master' of https://github.com/uruk-why-you-uruk/CatchMe-IfYouCan.git

   public static void main(String[] args) {
      System.out.println("mywindow main 실행");
      try {
         //UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
         System.out.println("jtatto 실행");
      } catch (Exception ex) {
         System.out.println("jtatto 예외처리");
      }
      MyWindow a = new MyWindow();
      System.out.println("mywindow 켜짐");
      a.setLocationRelativeTo(null);
   }

<<<<<<< HEAD
	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mv.b1) {
			// 버튼 누르면 
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
         // 버튼 누르면 
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
      if (e.getSource() == cs.enter) { // 캐릭터 선택화면에서 엔터를 누르면 대기실로 이동한다.
         card.show(getContentPane(), "WR");
      }
      if (e.getSource() == wr.b1) { // 대기화면에서 방만들기 버튼을 누르면 방만들기 프레임이 보여진다.
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