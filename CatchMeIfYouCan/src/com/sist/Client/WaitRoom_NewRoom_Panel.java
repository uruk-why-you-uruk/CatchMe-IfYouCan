package com.sist.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WaitRoom_NewRoom_Panel extends JPanel{
   Image back; //��׶��� ���
  /* JTextField roomName;
   JPasswordField roomPsw; //���̸�, �н����� �Է��ʵ� 
   JRadioButton open, notopen;
   String[] personnel= {"4","5","6","7","8"}; //�ο��� �޺��ڽ��� ���̱� ���� ���ڿ� �迭
   JComboBox personnel_Combo; //�ο��� �޺��ڽ�
   
   JButton okButton;//����� ��ư
   JButton noButton;//��ҹ�ư
   ImageIcon okButtonIcon, noButtonIcon;//����ư �̹���
*/   
   public WaitRoom_NewRoom_Panel() {
      back = Toolkit.getDefaultToolkit().getImage("image\\newroom.png"); //��׶���
      
      /*//���̸� ����
      roomName = new JTextField("");
      roomName.setBounds(170, 109, 320, 20);
      add(roomName);
      
      //���� ����� �޺��ڽ� ����
      visibility_Combo =new JComboBox(visibility);
      visibility_Combo.setBounds(170, 148, 80, 20);
      add(visibility_Combo);
      open=new JRadioButton("����");
      open.setOpaque(false);
      open.setSelected(true);
      notopen = new JRadioButton("�����");
      notopen.setOpaque(false);
      // �׷����� ����� �ϳ��� ���õȴ�.
      ButtonGroup bg = new ButtonGroup();
      bg.add(open);
      bg.add(notopen);
      open.setBounds(170, 148, 60, 20);
      notopen.setBounds(235, 148, 80, 20);
      add(open);
      add(notopen);
      
      //�н����� ����
      roomPsw=new JPasswordField("");
      roomPsw.setBounds(170, 179, 320, 20);
      add(roomPsw);
      
      //���ο� ����
      personnel_Combo=new JComboBox(personnel);
      personnel_Combo.setBounds(170, 214, 80, 20);
      add(personnel_Combo);
      
      //�����, ��ҹ�ư ����
      okButtonIcon=new ImageIcon("image\\newroom_ok_btn.png");
      noButtonIcon=new ImageIcon("image\\newroom_no_btn.png");
      okButton=new JButton("",okButtonIcon);
      noButton=new JButton("",noButtonIcon);      
      /////////////////////////////////////////////
      //������ư ����
      okButton.setBounds(205, 300, 85, 40);
      okButton.setOpaque(false);
      okButton.setBorderPainted(false); 
      okButton.setFocusPainted(false); 
      okButton.setContentAreaFilled(false);
      add(okButton);
      /////////////////////////////////////////
      //��ҹ�ư ����
      noButton.setBounds(305, 298, 85, 40);
      noButton.setOpaque(false);
      noButton.setBorderPainted(false); 
      noButton.setFocusPainted(false);
      noButton.setContentAreaFilled(false);   
      add(noButton);      */   
   }   
   //��׶��� ���ȭ�� : Ŭ�������� ������ Ŭ�� -> Source->Override����� ->paintComponent
   @Override
   protected void paintComponent(Graphics g) {
      g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
   }
}