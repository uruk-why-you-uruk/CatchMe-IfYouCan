package com.sist.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.table.*;

public class WaitRoom extends JPanel implements ActionListener {
   Image back; //배경화면
   JLabel la1, la2; //la1 = 방개설 테이블을 붙이는 라벨,  la2 = 접속자 테이블을 붙이는라벨
   JTable table1, table2; //table1 = 방개설 테이블 뷰,  table2 = 접속자 테이블 뷰
   DefaultTableModel model1, model2; //테이블들의 row col설정 
   JTextArea ta; //채팅창 뷰
   JTextField tf; //채팅치는 곳
   JButton b1,b2; //b1 = 방만들기 버튼,   b2= 다음페이지버튼(게임룸이동) ※ 잠시 쓰는것.
   ImageIcon mb; //방만들기버튼 이미지

   WaitRoom() {
      setLayout(null); //사용자 지정 레이아웃으로 
      back = Toolkit.getDefaultToolkit().getImage("image\\waitingroom.png");//뒷배경화면

      la1 = new JLabel("");
      la2 = new JLabel("");
      ta = new JTextArea();
      JScrollPane js3 = new JScrollPane(ta);
      tf = new JTextField();
      
      b2 = new JButton("다음페이지");      
      //방만들기 버튼
      mb=new ImageIcon("image\\newroombtn.png");
      b1 = new JButton("",mb);      

      // table
      String[] col1 = {"No", "방이름", "공개/비공개", "방인원" };
      String[][] row1 = new String[0][4];
      model1 = new DefaultTableModel(row1, col1);
      table1 = new JTable(model1);
      table1.getColumn("No").setPreferredWidth(60);
      table1.getColumn("방이름").setPreferredWidth(440); 
      table1.getColumn("공개/비공개").setPreferredWidth(120);
      table1.getColumn("방인원").setPreferredWidth(80);
      table1.getTableHeader().setReorderingAllowed(false); // 이동 불가 
      table1.getTableHeader().setResizingAllowed(false); //크기변경불가
      JScrollPane js1 = new JScrollPane(table1);
      
      
      String[] col2 = { "닉네임", "방위치" };
      String[][] row2 = new String[0][2];
      model2 = new DefaultTableModel(row2, col2);
      table2 = new JTable(model2);
      table2.getTableHeader().setReorderingAllowed(false); // 이동 불가 
      table2.getTableHeader().setResizingAllowed(false); //크기변경불가
      JScrollPane js2 = new JScrollPane(table2);

      // 배치
      la1.setBounds(30, 70, 100, 30);
      js1.setBounds(30, 95, 700, 300);
      js1.setOpaque(false);
      js1.getViewport().setOpaque(false);
      //add(la1);
      add(js1);

      la2.setBounds(820, 70, 130, 30);
      js2.setBounds(820, 95, 340, 350);
      js2.setOpaque(false);
      js2.getViewport().setOpaque(false);
      //add(la2);
      add(js2);
      
      
      js3.setBounds(30, 450, 700, 225); // 채팅창
      js3.setOpaque(true);
      add(js3);
      tf.setBounds(30, 685, 700, 20); // 채팅치는곳
      tf.setOpaque(true);
      add(tf);
      
      b1.setBounds(930, 645, 120, 50); //방만들기 버튼
      b2.setBounds(930, 600, 120, 30);
      
      b1.setOpaque(false);
      //이미지 뒤에 버튼 라인 안보이게 하는것
      b1.setBorderPainted(false); 
      b1.setFocusPainted(false); 
      b1.setContentAreaFilled(false);
      
      add(b1);
      add(b2);

      setSize(1251, 750);
      setVisible(true);

      tf.addActionListener(this);
   }

   public static void main(String[] args) {
      WaitRoom wr = new WaitRoom();
      try {
         UIManager.setLookAndFeel("com.jtattoo.plaf.mint.MintLookAndFeel");
      } catch (Exception ex) {
      } // 예외처리
   }

   @Override
   public void actionPerformed(ActionEvent e) { // 채팅을치면 채팅창에 입력된게 올라가는고
      if (e.getSource() == tf) {
         String s = tf.getText();
         ta.append(s + "\n");
         tf.setText("");
      }
   }

   //백그라운드 배경화면 : 클래스파일 오른쪽 클릭 -> Source->Override어찌고 ->paintComponent
   @Override
   protected void paintComponent(Graphics g) {
      g.drawImage(back, 0, 0, getWidth(), getHeight(), this);

   }
}