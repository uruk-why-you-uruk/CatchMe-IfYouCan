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
 * 
 *       
 */
import javax.swing.JFrame;
import javax.swing.UIManager;


public class MyWindow extends JFrame implements ActionListener{
	// ������ ����
	MainView mv = new MainView();
	Character_select cs = new Character_select();
	Catch_gameroom gr = new Catch_gameroom();
	CardLayout card = new CardLayout();
	public MyWindow() {
		setLayout(card);
		add("MV",mv);
		add("CS",cs);
		add("GR",gr);
		//add("MF",mf);
		
		
		//������ ũ��
		setSize(1251,750);
		
		//������ �����޶�
		setVisible(true);
		setResizable(false);// ũ�⺯�� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		mv.b1.addActionListener(this);
		cs.enter.addActionListener(this);
	}
	

	public static void main(String[] args) {
		
		
		try {
			UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
		}catch(Exception ex) {}
		//MyWindow a = new MyWindow();
		
		new MyWindow().setLocationRelativeTo(null);

	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==mv.b1) {
			card.show(getContentPane(), "CS");
		}
		if(e.getSource()==cs.enter) {
			card.show(getContentPane(), "GR");
		}
		
	}

}
