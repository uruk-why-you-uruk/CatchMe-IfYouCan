package com.sist.Client;

// �����찡 ��ġ�ϴ� ��ġ
import java.awt.CardLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

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
import javax.swing.JFrame;
import javax.swing.UIManager;

public class MyWindow extends JFrame implements ActionListener {
	// ������ ����
	MainView mv = new MainView();
	WaitRoom wr = new WaitRoom();
	WaitRoom_NewRoom wrn = new WaitRoom_NewRoom();
	Character_select cs = new Character_select();
	Catch_gameroom gr = new Catch_gameroom();
	CardLayout card = new CardLayout();

	public MyWindow() {
		System.out.println("mywindow ����");
		setLayout(card);
		add("MV", mv);  
		add("CS", cs);
		add("GR", gr);
		add("WR", wr);

		// add("MF",mf);

		// ������ ũ��
		setSize(1251, 750);

		// ������ �����޶�
		setVisible(true);
		setResizable(false);// ũ�⺯�� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
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
			card.show(getContentPane(), "CS");
		}
		if (e.getSource() == cs.enter) {
			card.show(getContentPane(), "WR");
		}
		if (e.getSource() == wr.b1) { // ���ȭ�鿡�� �游��� ��ư�� ������ �游��� �������� ��������.
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
}