package com.sist.Client;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;

public class WaitRoom_NewRoom_Panel extends JPanel{
   Image back; //백그라운드 배경
  /* JTextField roomName;
   JPasswordField roomPsw; //방이름, 패스워드 입력필드 
   JRadioButton open, notopen;
   String[] personnel= {"4","5","6","7","8"}; //인원수 콤보박스에 붙이기 위한 문자열 배열
   JComboBox personnel_Combo; //인원수 콤보박스
   
   JButton okButton;//만들기 버튼
   JButton noButton;//취소버튼
   ImageIcon okButtonIcon, noButtonIcon;//각버튼 이미지
*/   
   public WaitRoom_NewRoom_Panel() {
      back = Toolkit.getDefaultToolkit().getImage("image\\newroom.png"); //백그라운드
      
      /*//방이름 설정
      roomName = new JTextField("");
      roomName.setBounds(170, 109, 320, 20);
      add(roomName);
      
      //공개 비공개 콤보박스 설정
      visibility_Combo =new JComboBox(visibility);
      visibility_Combo.setBounds(170, 148, 80, 20);
      add(visibility_Combo);
      open=new JRadioButton("공개");
      open.setOpaque(false);
      open.setSelected(true);
      notopen = new JRadioButton("비공개");
      notopen.setOpaque(false);
      // 그룹으로 묶어야 하나만 선택된다.
      ButtonGroup bg = new ButtonGroup();
      bg.add(open);
      bg.add(notopen);
      open.setBounds(170, 148, 60, 20);
      notopen.setBounds(235, 148, 80, 20);
      add(open);
      add(notopen);
      
      //패스워드 설정
      roomPsw=new JPasswordField("");
      roomPsw.setBounds(170, 179, 320, 20);
      add(roomPsw);
      
      //방인원 설정
      personnel_Combo=new JComboBox(personnel);
      personnel_Combo.setBounds(170, 214, 80, 20);
      add(personnel_Combo);
      
      //만들기, 취소버튼 생성
      okButtonIcon=new ImageIcon("image\\newroom_ok_btn.png");
      noButtonIcon=new ImageIcon("image\\newroom_no_btn.png");
      okButton=new JButton("",okButtonIcon);
      noButton=new JButton("",noButtonIcon);      
      /////////////////////////////////////////////
      //만들기버튼 설정
      okButton.setBounds(205, 300, 85, 40);
      okButton.setOpaque(false);
      okButton.setBorderPainted(false); 
      okButton.setFocusPainted(false); 
      okButton.setContentAreaFilled(false);
      add(okButton);
      /////////////////////////////////////////
      //취소버튼 설정
      noButton.setBounds(305, 298, 85, 40);
      noButton.setOpaque(false);
      noButton.setBorderPainted(false); 
      noButton.setFocusPainted(false);
      noButton.setContentAreaFilled(false);   
      add(noButton);      */   
   }   
   //백그라운드 배경화면 : 클래스파일 오른쪽 클릭 -> Source->Override어찌고 ->paintComponent
   @Override
   protected void paintComponent(Graphics g) {
      g.drawImage(back, 0, 0, getWidth(), getHeight(), this);
   }
}