package com.sist.Client;

// 윈도우가 위치하는 위치
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

// JDK 1.2 이전에 개발된 라이브러리(java), 이후에 개발된 라이브러리(javax => xml,sql)
// 윈도우 기능을 전체 사용하고 싶다. : 상속
/*
 *      윈도우
 *      JFrame : 일반 창(윈도우)
 *      JPanel 
 *      ======= 위에는 자주 쓰이는 것
 *      JDialog : 닫기버튼만 있을때
 *      JWindow : 타이틀바가 없는 것 => 홍보할때 
 * 
 *       
 */
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MyWindow extends JFrame implements ActionListener {
	// 윈도우 설정
	MainView mv = new MainView();
	WaitRoom wr = new WaitRoom();
	WaitRoom_NewRoom wrn = new WaitRoom_NewRoom();
	Character_select cs = new Character_select();
	Catch_gameroom gr = new Catch_gameroom();
	CardLayout card = new CardLayout();


	public MyWindow() {
		setLayout(card);
		add("MV", mv);
		add("CS", cs);
		add("GR", gr);
		add("WR", wr);

		// add("MF",mf);

		// 윈도우 크기
		setSize(1251, 750);

		// 윈도우 보여달라
		setVisible(true);
		setResizable(false);// 크기변경 제한
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mv.b1.addActionListener(this);		
		cs.enter.addActionListener(this);
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
	}

	public static void main(String[] args) {
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		} catch (Exception ex) {
		}
		// MyWindow a = new MyWindow();
		new MyWindow().setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mv.b1) {
			card.show(getContentPane(), "CS");
		}
		if (e.getSource() == cs.enter) {
			card.show(getContentPane(), "WR");
		}
		if (e.getSource() == wr.b1) { // 대기화면에서 방만들기 버튼을 누르면 방만들기 프레임이 보여진다.
			wrn.setLocationRelativeTo(null);
			wrn.setVisible(true);
		}
		if (e.getSource() == wr.b2) {
			card.show(getContentPane(), "GR");
		}
	}
}