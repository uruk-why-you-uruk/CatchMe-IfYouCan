package com.sist.Client;
import javax.swing.*;
import java.awt.*;
import java.util.*;
import java.awt.event.*;
import java.net.*;
import java.io.*;
public class Client2 extends JFrame implements ActionListener,Runnable{
  JButton b1,b2;
  int start=0;
  Socket s;
  BufferedReader in;
  OutputStream out;
  Vector<Point> vStart = new Vector<Point>();
  Client2() {
  b1=new JButton("접속");
  b2=new JButton("취소");
  
  JPanel p=new JPanel();
  p.add(b1);
  p.add(b2);
  add("North",p);
  setTitle("마우스로 곡선 그리기");
  setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  MyPanel t = new MyPanel();
  //setContentPane(t);
  add("Center",t);
  t.setFocusable(true);
  setSize(300, 300);
  setVisible(true);
  
  b1.addActionListener(this);
  b2.addActionListener(this);
 }

 public static void main(String[] args) {
  new Client2();
 }

 class MyPanel extends JPanel {

  

  public MyPanel() {

   addKeyListener(new KeyListener() {

    @Override

    public void keyTyped(KeyEvent e) {

     // TODO Auto-generated method stub

    }
    @Override
    public void keyReleased(KeyEvent e) {

     // TODO Auto-generated method stub
    }
    @Override
    public void keyPressed(KeyEvent e) {
     // TODO Auto-generated method stub
     switch (e.getKeyCode()) {
     case KeyEvent.VK_ENTER:
      vStart.removeAllElements();
      repaint();
      break;
     }
    }
   });

   addMouseMotionListener(new MouseMotionAdapter() {
    public void mouseDragged(MouseEvent e) { 
     vStart.add(e.getPoint());
     try
     {
    	 out.write(("200|"+e.getPoint().getX()+"|"
    			 +e.getPoint().getY()+"\n").getBytes());
     }catch(Exception ex){}
     repaint();
    }
   });

   // 마우스 이벤트 처리
   addMouseListener(new MouseAdapter() {
    // 마우스를 누르면 호출된다.
    public void mousePressed(MouseEvent e) {
     vStart.add(null);
     vStart.add(e.getPoint());
     try
     {
    	 out.write(("100|"+e.getPoint().getX()+"|"
    			 +e.getPoint().getY()+"\n").getBytes());
     }catch(Exception ex){}
     System.out.println("mousePressed:"+e.getPoint().getX()+","+e.getPoint().getY());
    }
   });
  }
  public void paintComponent(Graphics g) {
   super.paintComponent(g);
   g.setColor(Color.BLUE); // 파란색을 선택한다.
   for (int i = 1; i < vStart.size(); i++) {
    if (vStart.get(i - 1) == null)
     continue;
    else if (vStart.get(i) == null)
     continue;
    else
    {
     /*System.out.println("x="+(int) vStart.get(i - 1).getX());
     System.out.println("x="+(int) vStart.get(i - 1).getY());
     System.out.println("x="+(int) vStart.get(i).getX());
     System.out.println("x="+(int) vStart.get(i).getY());*/
     
       /*try
       {
    	   out.write(((int) vStart.get(i - 1).getX()+"|"
    			   +(int) vStart.get(i - 1).getY()+"|"
    			   +(int) vStart.get(i).getX()+"|"
    			   +(int) vStart.get(i).getY()+"\n").getBytes());
       }catch(Exception ex){
    	   System.out.println(ex.getMessage());
       }*/
     
     g.drawLine((int) vStart.get(i - 1).getX(), (int) vStart.get(i - 1).getY(),
       (int) vStart.get(i).getX(), (int) vStart.get(i).getY());
    }
   }
  }

 }

@Override
public void actionPerformed(ActionEvent e) {
	// TODO Auto-generated method stub
	if(e.getSource()==b1)
	{
		try
		{
			s=new Socket("211.238.142.66",3955);
			in=new BufferedReader(new InputStreamReader(s.getInputStream()));
			out=s.getOutputStream();
			b1.setEnabled(false);
		}catch(Exception ex){}
		new Thread(this).start();
	}
	if(e.getSource()==b2)
	{
		System.out.println("b2 Client");
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
			StringTokenizer st=new StringTokenizer(msg, "|");
			int no=Integer.parseInt(st.nextToken());
			switch(no)
			{
				case 100:
				{
					double x=Double.parseDouble(st.nextToken());
					double y=Double.parseDouble(st.nextToken());
				    Point point=new Point((int)x, (int)y);
				    vStart.add(null);
				    vStart.add(point);
				}
				break;
				case 200:
				{
					double x=Double.parseDouble(st.nextToken());
					double y=Double.parseDouble(st.nextToken());
					Point point=new Point((int)x, (int)y);
					vStart.add(point);
					repaint();
				}
				break;
			}
		}
	}catch(Exception ex){}
}
}


