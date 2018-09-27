package com.sist.Client;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.*;
// �����찡 ��ġ�ϴ� ��ġ
import java.awt.*;

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
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;

import java.net.Socket;
import java.util.Vector;

import com.sist.Server.Room;
import com.sist.Server.Server.Client;
import com.sist.Vo.CharLabelVO;
import com.sist.Vo.CharVO;
import com.sist.common.Function;

public class MyWindow extends JFrame implements ActionListener, Runnable,MouseListener {
	// ������ ����
	static int charno = 0,roomno=0;
	static String temp;
	MainView mv = new MainView();
	WaitRoom wr = new WaitRoom();
	WaitRoom_NewRoom wrn = new WaitRoom_NewRoom();
	//WaitRoom_NewRoom_Panel wrnp = new WaitRoom_NewRoom_Panel();
	Character_select cs = new Character_select();
	Catch_gameroom gr = new Catch_gameroom();
	////////////////////////////////////////////////������ �߰�
	PwdDialog dialog=new PwdDialog(this, "������� ��й�ȣ �Է�");
	String pwdStr, pwdStrCheck;
	////////////////////////////////////////////////////////
	
	CardLayout card = new CardLayout();
	Socket s;
	BufferedReader in;
	OutputStream out;
	// Vector<Point> vStart = new Vector<Point>();

	public MyWindow() {
		System.out.println("mywindow ����");
		setLayout(card);
		add("MV", mv);
		add("CS", cs);
		add("WR", wr);
		add("GR", gr);

		// ������źκ�

		// ������ ũ��
		setSize(1251, 750);

		// ������ �����޶�
		setVisible(true);
		setResizable(false);// ũ�⺯�� ����
		setDefaultCloseOperation(EXIT_ON_CLOSE);

		// �����ʿ� ������ ����
		// new Thread(this).start();
		wr.tf.addActionListener(this);// actionPerformed
		mv.b1.addActionListener(this);
		cs.enter.addActionListener(this);
		wr.b1.addActionListener(this);
		wr.b2.addActionListener(this);
		gr.out_btn.addActionListener(this);
		wrn.okButton.addActionListener(this);
		wrn.noButton.addActionListener(this);
		wr.table1.addMouseListener(this);
		dialog.okButton.addActionListener(this); //������ �߰�
	}

	public static void main(String[] args) {
		System.out.println("mywindow main ����");
		try {
			// UIManager.setLookAndFeel("com.jtattoo.plaf.smart.SmartLookAndFeel");
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
			// ��ư ������
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
		if (e.getSource() == cs.enter) { // ĳ���� ����ȭ�鿡�� ���͸� ������ ���Ƿ� �̵��Ѵ�.
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
			// ä�� ��û
			try {
				// �Է°� �б�
				String msg = wr.tf.getText();
				if (msg.length() < 1)
					return;
				out.write((Function.WAITCHAT + "|" + msg + "\n").getBytes());
				// ó�� ==> ����
				wr.tf.setText("");
				wr.tf.requestFocus();// focus
			} catch (Exception ex) {
			}
		}
		if (e.getSource() == wr.b1) { // ���ȭ�鿡�� �游��� ��ư�� ������ �游��� �������� ��������.
			System.out.println("�游��� ��ư Ŭ��");
			//wrn.roomName.setText("�������� �Է����ּ���"); //�ȴ�.
			wrn.roomName.setText("");
			wrn.roomPsw.setText("");
			wrn.open.setSelected(true);
			wrn.roomPsw.setBackground(Color.GRAY);
			wrn.roomPsw.setEditable(true);
			wrn.setLocationRelativeTo(null);
			wrn.setVisible(true);
		}
		// ���� �游���.
		if (e.getSource() == wrn.okButton) { 
			System.out.println("�����!!!");
			// �Էµ� ������ �б�
			String rname = wrn.roomName.getText();
			if (rname.trim().length() < 1) // ������ ������ ���¿��� �Է��� �ȵ� ����
			{
				JOptionPane.showMessageDialog(this, "���̸��� �Է��ϼ���");
				wrn.roomName.requestFocus();
				return;
			}

			// �� �̸� �ߺ� ã��, �ٽø�����ϱ�
			String temp = "";
			for (int i = 0; i < wr.model1.getRowCount(); i++) {
				temp = wr.model1.getValueAt(i, 1).toString();
				if (temp.equals(rname)) {
					JOptionPane.showMessageDialog(this, "�̹� �����ϴ� ���Դϴ�.\n�ٽ� �Է��ϼ���");
					wrn.roomName.setText("");
					wrn.roomName.requestFocus();
					return;
				}
			}

			String state = "";
			String pwd = "null";
			if (wrn.open.isSelected()) // ������ư�� ���õƴ�.
			{
				state = "����";
				pwd = "null";
			} else {
				state = "�����";
				pwd = new String(wrn.roomPsw.getPassword());
				// �Ǵ� pwd=String.valueOf(mr.pf.getPassword());
			}
			int inwon = wrn.personnel_Combo.getSelectedIndex();
			try {
				//Room.java = public Room(String roomName, String roomState, String roomPwd, int maxcount)
				if(state.equals("�����")) {
					out.write((Function.MAKEROOM + "|" + rname + "|" + state.trim() + "|" + pwd + "|" + (inwon + 4) + "\n")
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
			try {
				out.write((Function.ROOMOUT+"|"+roomno+"\n").getBytes());
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			card.show(getContentPane(), "WR");
		}
		if (e.getSource() == gr.tf) {
			// ä�� ��û
			try {
				String msg = gr.tf.getText().trim();
				// �Է°� �б�
				if (msg.length() < 1)
					return;
				out.write((Function.ROOMCHAT+ "|" +roomno+"|"+ msg + "\n").getBytes());
				// ó�� ==> ����
				gr.tf.setText("");
				gr.tf.requestFocus();// focus
			} catch (Exception ex) {
			}
		}
		////////////////////////////////////////////////////������ �߰�
		if(e.getSource() ==dialog.okButton) { //�� ��� ���̾�α� Ȯ�ι�ư
	         pwdStrCheck=dialog.tf.getText();
	         if(pwdStrCheck.equals(pwdStr)) {
	            try {
	               // ���� ���� ��ȣ�� �����´�.
	               out.write((Function.MYROOMIN + "|" + wr.table1.getValueAt(wr.table1.getSelectedRow(), 0) + "\n")
	                     .getBytes());
	               card.show(getContentPane(), "GR");
	            } catch (IOException e1) {
	               // TODO Auto-generated catch block
	               e1.printStackTrace();
	            }
	         }else {
	            dialog.tf.setText("");
	            dialog.setVisible(false);
	            JOptionPane.showMessageDialog(null, "Ʋ�Ƚ��ϴ�!.");
	         }
	         dialog.tf.setText("");
	         dialog.setVisible(false);
	      }
		///////////////////////////////////////////////////////
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
					System.out.println("�α��� ��� �Ѹ���~");
					String[] data = { st.nextToken(), // ID
							st.nextToken()// POS
					};
					wr.model2.addRow(data);
				}
					break;
				case Function.DUPLICATE: // ���̵� �ߺ�
					mv.tf.setText("���̵� �ߺ�");
					mv.tf.requestFocus();
					break;

				case Function.CHARACTERROOM:
					temp = st.nextToken();
					System.out.println("window CR :" + temp);
					card.show(getContentPane(), "CS");
					break;

				case Function.MYLOG: {
					System.out.println("WR �����ֱ�");
					card.show(getContentPane(), "WR");
				}break;
				
				case Function.WAITCHAT: {
					wr.bar.setValue(wr.bar.getMaximum());
					wr.ta.append(st.nextToken() + "\n");
				}break;
				
				case Function.CHARACTERCHOICE: {
					System.out.println("CHARACTERCHOICE �۵�");
					wr.nickName.setText(st.nextToken());
					ImageIcon temp = new ImageIcon("image\\char_mini\\" + Integer.parseInt(st.nextToken()) + ".png");
					wr.mc.setIcon(temp);
					temp = new ImageIcon("image\\RANK\\" + Integer.parseInt(st.nextToken()) + ".png");
					wr.rank.setIcon(temp);
				}break;
				
				case Function.MAKEROOM: {
					System.out.println("MAKEROOM�۵�~");
					/*int temp = Integer.parseInt(st.nextToken());
					String roomNumber= Integer.toString(temp+1);*/
					String roomNumber= st.nextToken();
					String rname=st.nextToken();
					String state=st.nextToken();					
					String inwon=st.nextToken();
					String pwd=st.nextToken();
					String[] data = {roomNumber,rname,state,inwon,pwd};
					wr.model1.addRow(data);
					}break;	
					
				case Function.ROOMNAMEUPDATE: { //����� ������ ������Ʈ
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
					CharVO temp = new CharVO();
					temp.setId(st.nextToken());
					temp.setRank(Integer.parseInt(st.nextToken()));
					temp.setIcon(st.nextToken());
					
					//+user.charvo.getId()+"|"+user.charvo.getRank()+"|"+user.charvo.getIcon()+room.current);
					int i=0;
					int num_temp = Integer.parseInt(st.nextToken());
					while(i<num_temp)						
					{
						if(gr.char_group[i].isVisible()==false)
						{
							gr.char_group[i].setCharVO(temp);
							gr.char_group[i].setVisible(true);
							break;
						}
						i++;
					}
					if(i>=num_temp)
					{
						System.out.println("�� ���� �ʰ�");
					}
				}break;
				
				case Function.MYROOMIN: {
					CharVO temp = new CharVO();
					temp.setId(st.nextToken());
					temp.setRank(Integer.parseInt(st.nextToken()));
					temp.setIcon(st.nextToken());
					
					//+user.charvo.getId()+"|"+user.charvo.getRank()+"|"+user.charvo.getIcon()+room.current);
					int num_temp = Integer.parseInt(st.nextToken());
					int i=0;
					while(i<num_temp)						
					{
						if(gr.char_group[i].isVisible()==false)
						{							
							gr.char_group[i].setCharVO(temp);
							gr.char_group[i].setVisible(true);
							card.show(getContentPane(), "GR");
							break;
						}
						i++;
					}
					if(i>=num_temp)
					{
						System.out.println("�� ���� �ʰ�");
					}
				}break;
				
				case Function.MYROOMOUT:
			    {
			    	/*CharVO temp = new CharVO();
			    	temp.setId(" ");
			    	temp.set*/
			    	for(int i=0;i<8;i++)
			    	{
			    		gr.char_group[i].setVisible(false);
			    	}
			    	gr.ta.setText("");
			    	card.show(getContentPane(), "WR");
			    }
			    break;
			    case Function.ROOMOUT:
			    {
			    	String id=st.nextToken();
			    	for(int i=0;i<8;i++)
			    	{
			    		String temp=gr.char_group[i].id.getText();
			    		System.out.println("****************RoomOut: "+id+","+temp);
			    		if(id.equals(temp))
			    		{
			    			gr.char_group[i].id.setText(" ");
			    			gr.char_group[i].setVisible(false);
			    			break;
			    		}
			    	}
			    	/*for(int i=0;i<cr.model.getRowCount();i++)
			    	{
			    		String temp=cr.model.getValueAt(i, 0).toString();
			    		if(name.equals(temp))
			    		{
			    			cr.model.removeRow(i);
			    			break;
			    		}
			    	}*/
			    }
			    break;
			    case Function.WAITUPDATE:
			    {
			    	/*String id=st.nextToken();
			    	String pos=st.nextToken();
			    	String rn=st.nextToken();
			    	String rc=st.nextToken();
			    	String rm=st.nextToken();
			    	
			    	String temp="";
			    	for(int i=0;i<wr.model1.getRowCount();i++)
			    	{
			    		temp=wr.model1.getValueAt(i, 0).toString();
			    		if(temp.equals(rn))
			    		{
			    			if(Integer.parseInt(rc)<1)
			    			{
			    				wr.model1.removeRow(i);
			    			}
			    			else
			    			{
			    				wr.model1.setValueAt(rc+"/"+rm, i, 2);
			    			}
			    			break;
			    		}
			    	}
			    	for(int i=0;i<wr.model2.getRowCount();i++)
			    	{
			    		temp=wr.model2.getValueAt(i, 0).toString();
			    		if(id.equals(temp))
			    		{
			    			wr.model2.setValueAt(pos, i, 3);
			    			break;
			    		}
			    	}*/
			    }
				
				
				
					
				}//switch����
				
			}//while�� ��
			
		} catch (Exception ex) {
			System.out.println("Clinet:"+ex.getMessage());
			ex.printStackTrace();
		}
	}
	////////////////////////////////////////////////������ �߰�~
	@Override
	   public void mouseClicked(MouseEvent e) {
	      if (e.getSource() == wr.table1) {
	         if (e.getClickCount() == 2) {
	        	 System.out.println(wr.table1.getValueAt(wr.table1.getSelectedRow(), 4).toString());
	            // Room.java = public Room(int roomNumber,String roomName, String roomState,
	            // String roomPwd, int maxcount)
	            try {
	               if (wr.table1.getValueAt(wr.table1.getSelectedRow(), 2).toString().equals("�����")) {
	               pwdStr =wr.table1.getValueAt(wr.table1.getSelectedRow(), 4).toString();
	               System.out.println("pwdStr(����Ŭ���� ���й�ȣ) : "+pwdStr);               
	               dialog.setResizable(false);
	               dialog.setLocationRelativeTo(null);
	               dialog.setVisible(true);
	            } else {
	               try {
	                  // ���� ���� ��ȣ�� �����´�.
	                  out.write((Function.MYROOMIN + "|" + wr.table1.getValueAt(wr.table1.getSelectedRow(), 0) + "\n")
	                        .getBytes());
	                  card.show(getContentPane(), "GR");
	               } catch (IOException e1) {
	                  // TODO Auto-generated catch block
	                  e1.printStackTrace();
	               }
	            }
	            } catch (Exception e2) {
	               System.out.println("����Ŭ���� if�κ�~ ::::::::"+e2.getMessage());
	               e2.printStackTrace();
	            }
	         }
	      }
	   }
	///////////////////////////////////////////////////////////////

	/*@Override
	public void mouseClicked(MouseEvent e) {
		if(e.getSource()==wr.table1)
		{
			if(e.getClickCount()==2)
			{
				if(wr.table1.getValueAt(wr.table1.getSelectedRow(), 2).toString().equals("�����"))
				{
					
				}
				//Room.java = public Room(int roomNumber,String roomName, String roomState, String roomPwd, int maxcount)
				//System.out.println(wr.table1.getValueAt(wr.table1.getSelectedRow(), 0))
          	    try {
          	    	roomno = Integer.parseInt((String)wr.table1.getValueAt(wr.table1.getSelectedRow(), 0));
          	    	System.out.println("����Ŭ�� �� ���ȣ :"+roomno);
          	    	//                                       ���� ���� ��ȣ�� �����´�. 
					out.write((Function.MYROOMIN+"|"+roomno+"\n").getBytes());
					card.show(getContentPane(), "GR");
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
	}*/

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

}