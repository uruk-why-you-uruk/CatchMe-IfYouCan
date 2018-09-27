package com.sist.Client;
 
import javax.swing.*;
import com.sist.Vo.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionAdapter;
import java.io.OutputStream;
import java.util.Vector;

class char_if {
   JLabel id, rank, score, icon;
}

public class Catch_gameroom extends JPanel{
   static int k;
   boolean flag = false;
   Vector<Point> vStart = new Vector<Point>();
   OutputStream out;
   Image back;
   // 버튼 및 컴포넌트를 담는 패널들
   JPanel timer, color_Panel;
   // 라벨 선언
   JLabel room_grade, chat, qus,qus_text;
   // 캐릭터 정보를 담는 클래스 배열
   CharVO[] player = new CharVO[8];
   // 캐릭터 정보를 출력해줄 라벨을 담는 클래스 배열
   CharLabelVO[] char_group = new CharLabelVO[8];
   
   JLabel timerLabel = new JLabel("0");
   // 채팅창을 위한 택스트필드 선언
   JTextArea ta;
   JTextField tf;
   JScrollBar bar;
   // 팔레트 버튼을 위한 버튼
   JButton[] color = new JButton[6];
   Color[] c = {Color.BLACK, Color.GREEN, Color.RED, Color.YELLOW, Color.BLUE, Color.WHITE};
   

   ImageIcon out_img, giveup, eraser;
   JButton out_btn,giveup_btn, eraser_btn;

   JButton timer_btn = new JButton("타이머시작");
   JButton qus_btn = new JButton("문제 끄기");
   TimeThread t = new TimeThread();
   Color col=Color.BLACK;
   
   static boolean bThread;
   MyPanel draw = new MyPanel();
   Catch_gameroom() {

      // 출제자에게 보이는 문제 
	  draw.setFocusable(true);
	  
      qus = new JLabel(new ImageIcon("image\\question.png"));
      qus_text = new JLabel("goal");
      qus_text.setBounds(330, 70, 130, 31);
      qus.setBounds(265, 70, 197, 31);
      
      add(qus_text);
      add(qus);
      

      setLayout(null);
      // 배경이미지 출력
      back = Toolkit.getDefaultToolkit().getImage("image\\gamm.png");
      
      // 나가기 버튼
      out_img = new ImageIcon("image\\roomexit.png");
      out_btn = new JButton("", out_img);
      out_btn.setBounds(1060, 600, 115, 51);
      out_btn.setBorderPainted(false); // 테두리 출력 없애기
      out_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      add(out_btn);
      

      // 포기 버튼
      giveup = new ImageIcon("image\\giveup_btn.png");
      giveup_btn = new JButton(giveup);
      giveup_btn.setBounds(790, 45, 67, 66);
      giveup_btn.setBorderPainted(false);
      giveup_btn.setContentAreaFilled(false);
      giveup_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      add(giveup_btn);
      
      // 전체지우기 버튼
      eraser = new ImageIcon("image\\eraser_btn.png");
      eraser_btn = new JButton(eraser);
      eraser_btn.setBounds(930, 50, 64, 70);
      eraser_btn.setBorderPainted(false);
      eraser_btn.setContentAreaFilled(false);
      eraser_btn.setCursor(new Cursor(Cursor.HAND_CURSOR));
      add(eraser_btn);
      
      

      qus_btn.setBounds(50, 600, 115, 51);
      qus_btn.setBackground(Color.YELLOW);
      add(qus_btn);
      

      // 채팅창 선언


      ta = new JTextArea();
      JScrollPane js3 = new JScrollPane(ta); // 스크롤을 위해 감싸주는 컴포넌트
      bar=js3.getVerticalScrollBar();
      tf = new JTextField();
      
      // 플레이어들의 정보를 담을 클래스 선언하기
      for (int i = 0; i < 8; i++) {
         player[i] = new CharVO();
      }
      
      // 캐치마인드 그리는 부분
      
      draw.setBackground(Color.GRAY);
      
      // 방장 표시하는 라벨
      room_grade = new JLabel();
      
      // 채팅창 표시하는  부분
      chat = new JLabel();
      
      // 팔레트 표현하는 부분
      color_Panel = new JPanel();
      color_Panel.setOpaque(false); // 패널의 뒷배경 제거 
      color_Panel.setLayout(new FlowLayout()); // 버튼을 옆으로 표시하기 위한 레이아웃 선언
      
      for (int i = 0; i < color.length; i++) {
         // 버튼의 이미지 가져오기
         ImageIcon img2 = new ImageIcon("image\\color\\" + (i + 1) + ".png");
         Image img3 = getImageSizeChange(img2, 25, 28);
         ImageIcon ii = new ImageIcon(img3);
         //////////////////////////////////////////
         color[i] = new JButton(ii);
         color[i].setPreferredSize(new Dimension(25, 28)); // 이미지 크기 조절  
         // 이미지 
         color[i].setBorderPainted(false);
         color[i].setFocusPainted(false);
         color[i].setContentAreaFilled(false);
        // color[i].addActionListener(this);
         color[i].setCursor(new Cursor(Cursor.HAND_CURSOR));
         color_Panel.add(color[i]);
      }
      
      for (int i = 0; i < char_group.length; i++) {
         // ImageIcon img2 = new ImageIcon("image\\nickname.png");
         char_group[i] = new CharLabelVO();
         char_group[i].setOpaque(false);

      }
      for (int i = 0; i < char_group.length; i++) {
         add(char_group[i]);

      }

      add(color_Panel);
      add("Center",draw);
      add(tf);
      add(timerLabel);
      add(timer_btn);
      timerLabel.setText(String.valueOf(String.format("%02d:%02d", 2, 30)));
      timer_btn.setBounds(580, 580, 150, 150);
      timerLabel.setFont(new Font("Gothic", Font.ITALIC, 50));
      timerLabel.setForeground(Color.WHITE);
      int y1 = 112;
      // int y= 110;
      char_group[0].setBounds(45, y1, 180, 110);
      char_group[1].setBounds(45, y1 + 120, 180, 110);
      char_group[2].setBounds(45, y1 + 240, 180, 110);
      char_group[3].setBounds(45, y1 + 360, 180, 110);
      char_group[4].setBounds(1022, y1, 180, 110);
      char_group[5].setBounds(1022, y1 + 120, 180, 110);
      char_group[6].setBounds(1022, y1 + 240, 180, 110);
      char_group[7].setBounds(1022, y1 + 360, 180, 110);

      color_Panel.setBounds(265, 510, 220, 60);
      draw.setFocusable(true);
      draw.setBounds(265, 110, 725, 370);
      timerLabel.setBounds(265, 580, 300, 150);
      js3.setBounds(700, 500, 290, 100);
      add(js3);
      tf.setBounds(700, 620, 290, 30);

      setLayout(null);
      setVisible(true);

     
   }

   public Image getImageSizeChange(ImageIcon icon, int width, int height) {
      Image img = icon.getImage();
      Image change = img.getScaledInstance(width, height, Image.SCALE_SMOOTH);
      return change;
   }

   @Override
   protected void paintComponent(Graphics g) {
      g.drawImage(back, 0, 0, getWidth(), getHeight(), this);

   }

 

   
   class TimeThread extends Thread {
      public void run() {
         k = 150;
         if (flag == false) {
            flag = true;
            while (bThread) {
               if (k < 0)
                  break;
               try {
                  int minutes = k / 60;
                  int seconds = k % 60;
                  timerLabel.setText(String.valueOf(String.format("%02d:%02d", minutes, seconds)));
                  System.out.printf("%d\n", k);
                  Thread.sleep(1000);
                  // timerLabel.repaint();
               } catch (InterruptedException e1) {
                  e1.printStackTrace();
               }

               k--;
            }
            flag = false;
         }
      }
   }

   class MyPanel extends JPanel {

      public MyPanel() {

         /*addKeyListener(new KeyListener() {

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
               try {
                  out.write(("200|" + e.getPoint().getX() + "|" + e.getPoint().getY() + "\n").getBytes());
               } catch (Exception ex) {
               }
               repaint();
            }
         });

         // 마우스 이벤트 처리
         addMouseListener(new MouseAdapter() {
            // 마우스를 누르면 호출된다.
            public void mousePressed(MouseEvent e) {
               vStart.add(null);
               vStart.add(e.getPoint());
               try {
                  out.write(("100|" + e.getPoint().getX() + "|" + e.getPoint().getY() + "\n").getBytes());
               } catch (Exception ex) {
               }
               System.out.println("mousePressed:" + e.getPoint().getX() + "," + e.getPoint().getY());
            }
         });*/
      }

      public void paintComponent(Graphics g) {
         super.paintComponent(g);
         g.setColor(col); // 파란색을 선택한다.
         for (int i = 1; i < vStart.size(); i++) {
            if (vStart.get(i - 1) == null)
               continue;
            else if (vStart.get(i) == null)
               continue;
            else {
               
                System.out.println("x="+(int) vStart.get(i - 1).getX());
                System.out.println("x="+(int) vStart.get(i - 1).getY());
                System.out.println("x="+(int) vStart.get(i).getX());
                System.out.println("x="+(int) vStart.get(i).getY());
                

               /*
                * try { out.write(((int) vStart.get(i - 1).getX()+"|" +(int) vStart.get(i -
                * 1).getY()+"|" +(int) vStart.get(i).getX()+"|" +(int)
                * vStart.get(i).getY()+"\n").getBytes()); }catch(Exception ex){
                * System.out.println(ex.getMessage()); }
                */

               g.drawLine((int) vStart.get(i - 1).getX(), (int) vStart.get(i - 1).getY(),
                     (int) vStart.get(i).getX(), (int) vStart.get(i).getY());
            }
         }
      }

   }
}