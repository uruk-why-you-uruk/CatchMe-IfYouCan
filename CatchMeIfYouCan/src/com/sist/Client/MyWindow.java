package com.sist.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.util.*;
// 윈도우가 위치하는 위치
import java.awt.*;

// JDK 1.2 이전에 개발된 라이브러리(java), 이후에 개발된 라이브러리(javax => xml,sql)
// 윈도우 기능을 전체 사용하고 싶다. : 상속
/*
 *      윈도우
 *      JFrame : 일반 창(윈도우)
 *      JPanel 
 *      ======= 위에는 자주 쓰이는 것
 *      JDialog : 닫기버튼만 있을때
 *      JWindow : 타이틀바가 없는 것 => 홍보할때 
 */
import javax.swing.*;
import java.net.Socket;
import java.util.Vector;

import com.sist.Server.Room;
import com.sist.Server.Server.Client;
import com.sist.common.Function;

public class MyWindow extends JFrame implements ActionListener, Runnable {
	// 윈도우 설정
	static int charno = 0;
	static String temp;
	MainView mv = new MainView();
	WaitRoom wr = new WaitRoom();
	WaitRoom_NewRoom wrn = new WaitRoom_NewRoom();
	//WaitRoom_NewRoom_Panel wrnp = new WaitRoom_NewRoom_Panel();
	Character_select cs = new Character_select();
	Catch_gameroom gr = new Catch_gameroom();
	CardLayout card = new CardLayout();
	Socket s;
	BufferedReader in;
	OutputStream out;
	// Vector<Point> vStart = new Vector<Point>();

	public MyWindow() {
		System.out.println("mywindow 실행");
		setLayout(card);
		add("MV", mv);
		add("CS", cs);
		add("WR", wr);
		add("GR", gr);

		// 소켓통신부분

		// 윈도우 크기
		setSize(1251, 750);

		// 윈도우 보여달라
		setVisible(true);
		setResizable(false);// 크기변경 제한
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// 리스너와 쓰래드 시작
		// new Thread(this).start();
		wr.tf.addActionListener(this);// actionPerformed
		mv.b1.addActionListener(this);
		cs.enter.addActionListener(this);
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
		gr.out_btn.addActionListener(this);
		wrn.okButton.addActionListener(this);
		wrn.noButton.addActionListener(this);

	}

	public static void main(String[] args) {
		System.out.println("mywindow main 실행");
		try {
			// UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
			System.out.println("jtatto 실행");
		} catch (Exception ex) {
			System.out.println("jtatto 예외처리");
		}
		MyWindow a = new MyWindow();
		System.out.println("mywindow 켜짐");
		a.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == mv.b1) {
			// 버튼 누르면
			try {
				s = new Socket("211.238.142.66", 7339); 
				
				in = new BufferedReader(new InputStreamReader(s.getInputStream()));
				// byte ==> 2byte
				out = s.getOutputStream();
				// System.out.println((Function.LOGIN+"|"+mv.tf.getText()));
				out.write((Function.LOGIN + "|" + mv.tf.getText() + "\n").getBytes());
			} catch (Exception ex) {
			}
			new Thread(this).start();
			card.show(getContentPane(), "CS");
		}
		if (e.getSource() == cs.enter) { // 캐릭터 선택화면에서 엔터를 누르면 대기실로 이동한다.

			try {
				out.write(
						(Function.CHARACTERCHOICE + "|" + temp + "|" + charno + "|" + (int) (Math.random() * 5) + "\n")
								.getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}

		}
		if (e.getSource() == wr.tf) {
			// 채팅 요청
			try {
				// 입력값 읽기
				String msg = wr.tf.getText();
				if (msg.length() < 1)
					return;
				out.write((Function.WAITCHAT + "|" + msg + "\n").getBytes());
				// 처리 ==> 서버
				wr.tf.setText("");
				wr.tf.requestFocus();// focus
			} catch (Exception ex) {
			}
		}
		if (e.getSource() == wr.b1) { // 대기화면에서 방만들기 버튼을 누르면 방만들기 프레임이 보여진다.
			System.out.println("방만들기 버튼 클릭");
			//wrn.roomName.setText("방제목을 입력해주세요"); //된다.
			wrn.roomName.setText("");
			wrn.roomPsw.setText("");
			wrn.open.setSelected(true);
			wrn.roomPsw.setBackground(Color.GRAY);
			wrn.roomPsw.setEditable(true);
			wrn.setLocationRelativeTo(null);
			wrn.setVisible(true);
		}
		// 실제 방만들기.
		if (e.getSource() == wrn.okButton) { 
			System.out.println("방생성!!!");
			// 입력된 방정보 읽기
			String rname = wrn.roomName.getText();
			if (rname.trim().length() < 1) // 공백을 제거한 상태에서 입력이 안된 상태
			{
				JOptionPane.showMessageDialog(this, "방이름을 입력하세요");
				wrn.roomName.requestFocus();
				return;
			}

			// 방 이름 중복 찾고, 다시만들라하기
			String temp = "";
			for (int i = 0; i < wr.model1.getRowCount(); i++) {
				temp = wr.model1.getValueAt(i, 1).toString();
				if (temp.equals(rname)) {
					JOptionPane.showMessageDialog(this, "이미 존재하는 방입니다.\n다시 입력하세요");
					wrn.roomName.setText("");
					wrn.roomName.requestFocus();
					return;
				}
			}

			String state = "";
			String pwd = "";
			if (wrn.open.isSelected()) // 공개버튼이 선택됐다.
			{
				state = "공개";
				pwd = " ";
			} else {
				state = "비공개";
				pwd = new String(wrn.roomPsw.getPassword());
				// 또는 pwd=String.valueOf(mr.pf.getPassword());
			}
			int inwon = wrn.personnel_Combo.getSelectedIndex();
			try {
				//Room.java = public Room(String roomName, String roomState, String roomPwd, int maxcount)
				if(state.equals("비공개")) {
					out.write((Function.MAKEROOM + "|" + rname + "|" + state.trim() + "|" + pwd.trim() + "|" + (inwon + 4) + "\n")
						.getBytes());
				}else {
					out.write((Function.MAKEROOM + "|" + rname + "|" + state.trim() + "|" + pwd +"|"+ (inwon + 4)+"\n")
							.getBytes());
				}
			} catch (Exception e2) {
				// TODO: handle exception
			}
			wrn.setVisible(false);
		}
		
		if (e.getSource() == wrn.noButton)
		{
			wrn.setVisible(false);
		}
		
		if (e.getSource() == wr.b2) {
			card.show(getContentPane(), "GR");
		}
		
		if (e.getSource() == gr.out_btn) {
			card.show(getContentPane(), "WR");
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			while (true) {
				String msg = in.readLine().trim();
				System.out.println("Mywindow:" + msg);
				StringTokenizer st = new StringTokenizer(msg, "|");
				int no = Integer.parseInt(st.nextToken());
				switch (no) {
				case Function.LOGIN:// login.jsp
				{
					System.out.println("로그인 목록 뿌린다~");
					String[] data = { st.nextToken(), // ID
							st.nextToken()// POS
					};
					wr.model2.addRow(data);
				}
					break;
				case Function.DUPLICATE: // 아이디 중복
					mv.tf.setText("아이디 중복");
					mv.tf.requestFocus();
					break;

				case Function.CHARACTERROOM:
					temp = st.nextToken();
					System.out.println("window CR :" + temp);
					card.show(getContentPane(), "CS");
					break;

				case Function.MYLOG: {
					System.out.println("WR 보여주기");
					card.show(getContentPane(), "WR");
				}break;
				
				case Function.WAITCHAT: {
					wr.bar.setValue(wr.bar.getMaximum());
					wr.ta.append(st.nextToken() + "\n");
				}break;
				
				case Function.CHARACTERCHOICE: {
					System.out.println("CHARACTERCHOICE 작동");
					wr.nickName.setText(st.nextToken());
					ImageIcon temp = new ImageIcon("image\\char_mini\\" + Integer.parseInt(st.nextToken()) + ".png");
					wr.mc.setIcon(temp);
					temp = new ImageIcon("image\\RANK\\" + Integer.parseInt(st.nextToken()) + ".png");
					wr.rank.setIcon(temp);
				}break;
				
				case Function.MAKEROOM: {
					System.out.println("MAKEROOM작동~");
					String roomNumber=st.nextToken();
					String rname=st.nextToken();
					String state=st.nextToken();					
					String inwon=st.nextToken();
					//String pwd=st.nextToken();
					String[] data = {roomNumber,rname,state,inwon};
					wr.model1.addRow(data);
					}break;	
					
				case Function.ROOMNAMEUPDATE: { //사용자 방위지 업데이트
					String id=st.nextToken();
			    	String pos=st.nextToken();
			    	String temp="";
			    	for(int i=0;i<wr.model2.getRowCount();i++)
			    	{
			    		temp=wr.model2.getValueAt(i, 0).toString();
			    		if(id.equals(temp))
			    		{
			    			wr.model2.setValueAt(pos, i, 1);
			    			break;
			    		}
			    	}
					}break;	
				
				case Function.ROOMUPDATE:{
					String[] data = {};
				}break;
				
				case Function.ROOMIN: {
					
				}
					
				}//switch문끝
				
			}//while문 끝
			
		} catch (Exception ex) {
			System.out.println("Clinet:"+ex.getMessage());
			ex.printStackTrace();
		}
	}

}