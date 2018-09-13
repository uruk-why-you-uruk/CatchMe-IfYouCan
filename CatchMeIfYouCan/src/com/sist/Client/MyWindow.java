package com.sist.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
// �����찡 ��ġ�ϴ� ��ġ
import java.awt.*;

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
import javax.swing.*;
import java.net.Socket;
import java.util.Vector;

import com.sist.common.Function;

public class MyWindow extends JFrame implements ActionListener, Runnable{
	// ������ ����
	static int charno=0;
	MainView mv = new MainView();
	WaitRoom wr = new WaitRoom();
	WaitRoom_NewRoom wrn = new WaitRoom_NewRoom();
	WaitRoom_NewRoom_Panel wrnp = new WaitRoom_NewRoom_Panel();
	Character_select cs = new Character_select();
	Catch_gameroom gr = new Catch_gameroom();
	CardLayout card = new CardLayout();
	Socket s;
	BufferedReader in;
	OutputStream out;
	//Vector<Point> vStart = new Vector<Point>();

	public MyWindow() {
		System.out.println("mywindow ����");
		setLayout(card);
		add("MV", mv);  
		add("CS", cs);
		add("WR", wr);
		add("GR", gr);
		
		// ������źκ�
		

		// ������ ũ��
		setSize(1251, 750);

		// ������ �����޶�
		setVisible(true);
		setResizable(false);// ũ�⺯�� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		
		// �����ʿ� ������ ����
		//new Thread(this).start();
		wr.tf.addActionListener(this);//actionPerformed
		mv.b1.addActionListener(this);		
		cs.enter.addActionListener(this); 
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
		gr.out_btn.addActionListener(this);
		
	}  

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

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mv.b1) {
			// ��ư ������ 
			 try
	         {
	            s=new Socket("211.238.142.62", 7339);
	            in=new BufferedReader(new InputStreamReader(s.getInputStream()));
	               // byte ==> 2byte
	            out=s.getOutputStream();
	            //System.out.println((Function.LOGIN+"|"+mv.tf.getText()));
	            out.write((Function.LOGIN+"|"+mv.tf.getText()+"\n").getBytes());
	         }catch(Exception ex) {}
			 new Thread(this).start();
			 card.show(getContentPane(), "CS");
		}
		if (e.getSource() == cs.enter) { // ĳ���� ����ȭ�鿡�� ���͸� ������ ���Ƿ� �̵��Ѵ�.
			
			
			card.show(getContentPane(), "WR");
		}
		if(e.getSource()==wr.tf)
		{
			// ä�� ��û
			try
			{
				// �Է°� �б� 
				String msg=wr.tf.getText();
				if(msg.length()<1)
					return;
				out.write((Function.WAITCHAT+"|"+msg+"\n").getBytes());
				// ó�� ==> ���� 
				wr.tf.setText("");
				wr.tf.requestFocus();// focus
			}catch(Exception ex){}
		}
		if (e.getSource() == wr.b1) { // ���ȭ�鿡�� �游��� ��ư�� ������ �游��� �������� ��������.
			wrn.wnp.roomName.setText("");
	         wrn.wnp.roomPsw.setText("");
	         wrn.wnp.open.setSelected(true);
	         wrn.wnp.roomPsw.setBackground(Color.GRAY);
	         wrn.wnp.roomPsw.setEditable(true);
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
			System.out.println("mywindow run() �۵���");
            String msg=in.readLine();
            System.out.println("Mywindow:"+msg);
            StringTokenizer st=new StringTokenizer(msg, "|");
            int no=Integer.parseInt(st.nextToken());
            switch(no)
            {
            case Function.LOGIN:// login.jsp
			  {
				 String[] data={
					st.nextToken(),//ID
					st.nextToken()//POS
				 };
				 wr.model2.addRow(data);
     }break;
            case Function.DUPLICATE: // ���̵� ��
            	mv.tf.setText("���̵� �ߺ�");
            	mv.tf.requestFocus();
            	break;
            
            /*case Function.CHARACTERROOM:
            	card.show(getContentPane(), "CS");
            	break;*/
            case Function.MYLOG:
			  {
				  card.show(getContentPane(), "CS");
			  }
			  break;
            case Function.WAITCHAT:
			  {
				  wr.bar.setValue(wr.bar.getMaximum());
				  wr.ta.append(st.nextToken()+"\n");
			  }
			  break;
            case Function.CHARACTERCHOICE:
            {
            	wr.nickName.setText(st.nextToken());
            	ImageIcon temp = new ImageIcon("image\\char_mini"+Integer.parseInt(st.nextToken())+".png");
            	wr.mc.setIcon(temp);
            	
            }
            }
            
            
         }
      }catch(Exception ex) {}
   }

}