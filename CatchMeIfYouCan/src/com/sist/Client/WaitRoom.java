package com.sist.Client;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.imageio.ImageIO;
import java.io.File;
import javax.swing.table.*;

public class WaitRoom extends JPanel implements ActionListener {
   Image back; //���ȭ��
   JLabel la1, la2; //la1 = �氳�� ���̺��� ���̴� ��,  la2 = ������ ���̺��� ���̴¶�
   JTable table1, table2; //table1 = �氳�� ���̺� ��,  table2 = ������ ���̺� ��
   DefaultTableModel model1, model2; //���̺���� row col���� 
   JTextArea ta; //ä��â ��
   JTextField tf; //ä��ġ�� ��
   JButton b1,b2; //b1 = �游��� ��ư,   b2= ������������ư(���ӷ��̵�) �� ��� ���°�.
   ImageIcon mb; //�游����ư �̹���

   WaitRoom() {
      setLayout(null); //����� ���� ���̾ƿ����� 
      back = Toolkit.getDefaultToolkit().getImage("image\\waitingroom.png");//�޹��ȭ��

      la1 = new JLabel("");
      la2 = new JLabel("");
      ta = new JTextArea();
      JScrollPane js3 = new JScrollPane(ta);
      tf = new JTextField();
      
      b2 = new JButton("����������");      
      //�游��� ��ư
      mb=new ImageIcon("image\\newroombtn.png");
      b1 = new JButton("",mb);      

      // table
      String[] col1 = {"No", "���̸�", "����/�����", "���ο�" };
      String[][] row1 = new String[0][4];
      model1 = new DefaultTableModel(row1, col1);
      table1 = new JTable(model1);
      table1.getColumn("No").setPreferredWidth(60);
      table1.getColumn("���̸�").setPreferredWidth(440); 
      table1.getColumn("����/�����").setPreferredWidth(120);
      table1.getColumn("���ο�").setPreferredWidth(80);
      table1.getTableHeader().setReorderingAllowed(false); // �̵� �Ұ� 
      table1.getTableHeader().setResizingAllowed(false); //ũ�⺯��Ұ�
      JScrollPane js1 = new JScrollPane(table1);
      
      
      String[] col2 = { "�г���", "����ġ" };
      String[][] row2 = new String[0][2];
      model2 = new DefaultTableModel(row2, col2);
      table2 = new JTable(model2);
      table2.getTableHeader().setReorderingAllowed(false); // �̵� �Ұ� 
      table2.getTableHeader().setResizingAllowed(false); //ũ�⺯��Ұ�
      JScrollPane js2 = new JScrollPane(table2);

      // ��ġ
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
      
      
      js3.setBounds(30, 450, 700, 225); // ä��â
      js3.setOpaque(true);
      add(js3);
      tf.setBounds(30, 685, 700, 20); // ä��ġ�°�
      tf.setOpaque(true);
      add(tf);
      
      b1.setBounds(930, 645, 120, 50); //�游��� ��ư
      b2.setBounds(930, 600, 120, 30);
      
      b1.setOpaque(false);
      //�̹��� �ڿ� ��ư ���� �Ⱥ��̰� �ϴ°�
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
      } // ����ó��
   }

   @Override
   public void actionPerformed(ActionEvent e) { // ä����ġ�� ä��â�� �ԷµȰ� �ö󰡴°�
      if (e.getSource() == tf) {
         String s = tf.getText();
         ta.append(s + "\n");
         tf.setText("");
      }
   }

   //��׶��� ���ȭ�� : Ŭ�������� ������ Ŭ�� -> Source->Override����� ->paintComponent
   @Override
   protected void paintComponent(Graphics g) {
      g.drawImage(back, 0, 0, getWidth(), getHeight(), this);

   }
}