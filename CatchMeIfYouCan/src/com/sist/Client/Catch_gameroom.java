package com.sist.Client;

import javax.swing.*;
import com.sist.Vo.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

class char_if {
	JLabel id, rank, score, icon;
}

public class Catch_gameroom extends JPanel implements ActionListener, MouseListener {
	static int k;
	boolean flag = false;
	Image back;
	// 버튼 및 컴포넌트를 담는 패널들
	JPanel draw, timer, color_Panel;
	// 라벨 선언
	JLabel room_grade, chat, qus;
	// 캐릭터 정보를 담는 클래스 배열
	CharVO[] player = new CharVO[8];
	// 캐릭터 정보를 출력해줄 라벨을 담는 클래스 배열
	CharLabelVO[] char_group = new CharLabelVO[8];
	
	JLabel timerLabel = new JLabel("0");
	// 채팅창을 위한 택스트필드 선언
	JTextArea ta;
	JTextField tf;
	// 팔레트 버튼을 위한 버튼
	JButton[] color = new JButton[6];
	
	ImageIcon out_img;
	JButton out_btn;
	JButton timer_btn = new JButton("타이머시작");
	JButton qus_btn = new JButton("문제 끄기");
	TimeThread t = new TimeThread();

	static boolean bThread;

	Catch_gameroom() {

		// 출제자에게 보이는 문제 
		qus = new JLabel(new ImageIcon("image\\question.png"));
		qus.setBounds(265, 70, 197, 31);
		add(qus);

		setLayout(null);
		// 배경이미지 출력
		back = Toolkit.getDefaultToolkit().getImage("image\\gamm.png");
		
		// 나가기 버튼
		out_img = new ImageIcon("image\\roomexit.png");
		out_btn = new JButton("", out_img);
		out_btn.setBounds(1060, 600, 115, 51);
		out_btn.setBorderPainted(false); // 테두리 출력 없애기
		add(out_btn);
		
		// 
		qus_btn.setBounds(50, 600, 115, 51);
		qus_btn.setBackground(Color.YELLOW);
		add(qus_btn);
		
		// 채팅창 선언
		ta = new JTextArea();
		JScrollPane js3 = new JScrollPane(ta); // 스크롤을 위해 감싸주는 컴포넌트
		tf = new JTextField();
		
		// 플레이어들의 정보를 담을 클래스 선언하기
		for (int i = 0; i < 8; i++) {
			player[i] = new CharVO();
		}
		
		// 캐치마인드 그리는 부분
		draw = new JPanel();
		draw.setBackground(Color.BLACK);
		
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
		add(draw);
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
		draw.setBounds(265, 110, 725, 370);
		timerLabel.setBounds(265, 580, 300, 150);
		js3.setBounds(700, 500, 290, 100);
		add(js3);
		tf.setBounds(700, 620, 290, 30);

		setLayout(null);
		setVisible(true);

		qus_btn.addMouseListener(this);
		timer_btn.addMouseListener(this);
		tf.addActionListener(this);
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

	@Override
	public void actionPerformed(ActionEvent e) { // 채팅을치면 채팅창에 입력된게 올라가는고
		if (e.getSource() == tf) {
			String s = tf.getText();
			ta.append(s + "\n");
			tf.setText("");
		}
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		if (e.getSource() == timer_btn) {
			t = new TimeThread();
			bThread = true;
			t.start();
			qus.setVisible(false);
		}
		if (e.getSource() == qus_btn) {
			// t.interrupt();
			bThread = false;
			qus.setVisible(true);
		}
	} 

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub

	}

	class TimeThread extends Thread {
		public void run() {
			k = 150;
			if (flag == false) {
				flag = true;
				while (bThread) {
					if (k < 0)
						interrupt();
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

}