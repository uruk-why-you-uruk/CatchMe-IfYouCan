package com.sist.Client;

import javax.swing.*;
import java.awt.*;
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WaitRoom_NewRoom extends JFrame implements ActionListener {
	WaitRoom_NewRoom_Panel wnp = new WaitRoom_NewRoom_Panel();
	CardLayout card = new CardLayout();

	public WaitRoom_NewRoom() {
		setLayout(card);
		add("WNP", wnp);
		
		setSize(600, 350);
		card.show(getContentPane(), "WNP");
		setUndecorated(true);
		setResizable(false); // 화면 못 줄이게하기
		wnp.okButton.addActionListener(this);
		wnp.noButton.addActionListener(this);
		wnp.open.addActionListener(this);
		wnp.notopen.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) { // 방만들기 창 닫기.
		if (e.getSource() == wnp.noButton) {
			this.dispose();
		}
		if (e.getSource() == wnp.notopen) {//비공개가 눌리면 변화되는 것들.
			wnp.roomPsw.setBackground(Color.GRAY);
			wnp.roomPsw.setText("");
			wnp.roomPsw.setEditable(false);
			wnp.roomPsw.requestFocus();
		}
		if (e.getSource() == wnp.open) {//공개시 변화되는것.
			wnp.roomPsw.setBackground(Color.WHITE);
			wnp.roomPsw.setEditable(true);
			
		}
	}
}