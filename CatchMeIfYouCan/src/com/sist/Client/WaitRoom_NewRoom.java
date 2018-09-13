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
		setResizable(false); // ȭ�� �� ���̰��ϱ�
		wnp.okButton.addActionListener(this);
		wnp.noButton.addActionListener(this);
		wnp.open.addActionListener(this);
		wnp.notopen.addActionListener(this);
	}

	public void actionPerformed(ActionEvent e) { // �游��� â �ݱ�.
		if (e.getSource() == wnp.noButton) {
			this.dispose();
		}
		if (e.getSource() == wnp.notopen) {//������� ������ ��ȭ�Ǵ� �͵�.
			wnp.roomPsw.setBackground(Color.GRAY);
			wnp.roomPsw.setText("");
			wnp.roomPsw.setEditable(false);
			wnp.roomPsw.requestFocus();
		}
		if (e.getSource() == wnp.open) {//������ ��ȭ�Ǵ°�.
			wnp.roomPsw.setBackground(Color.WHITE);
			wnp.roomPsw.setEditable(true);
			
		}
	}
}