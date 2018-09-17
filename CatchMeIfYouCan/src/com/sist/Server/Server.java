package com.sist.Server;
import java.util.*; 

import com.sist.Vo.CharVO;
import com.sist.common.Function;

import java.net.*;
import java.io.*;
/*
 *    1. ���� ��� (�ڵ���)  ==>����
 *       =====
 *        Socket : �ٸ� ��ǻ�Ϳ� ����
 *        
 *    2. ���� ==> ����(��ȭ��ȣ, ��ȭ��)����
 *              ===  ip, port
 *             ���ɿ� �ɴ´�
 *             ========
 *             bind(ip,port)
 *    
 *    3. ���(��ȭ�� �� �� ���� ��ٸ���.)
 *            listen()
 *    =========================== ���ο�(P2P)
 *    ��Ƽ
 *    ===== 
 *     1) ��ȯ����
 *     2) ��ż��� ==> �����ڸ��� ���� ==> Thread
 *    
 */
public class Server implements Runnable{
   // ���� ���� ����
   private ServerSocket ss;
   private final int PORT=7334;
   //private String name;
   private String location;
   
   
   //Ŭ���̾�Ʈ�� ������ ����
   private ArrayList<Client> waitList =new ArrayList<Client>();
   
   private Vector<Room> roomVc=new Vector<Room>(); // table1 
   //Ŭ���̾�Ʈ�� IP, id....
   
   public Server() { //���α׷����� ���۰� ���� ����: ������, main
      //���� ==> �����Ҷ� �Ѱ� ��ǻ�Ϳ��� �ι��� ���� �� �� ����.
      try {         
         ss=new ServerSocket(PORT);
         //������ �޼ҵ� �ȿ�   bind():����, listen(): ��Ⱑ ���Ե��ִ�.
         System.out.println("Server Start...");
      } catch (Exception e) {
         System.out.println("Server_Error: "+e.getMessage());
      }
   }
   //���� ���� �� ó���ϴ� ���
   public void run() {
      try {
         //Ŭ���̾�Ʈ�� �߽��� IPȮ�� ==> Socket�ȿ� IP�� �ֵ�~
         while(true) {
            //Socket s == ������ Ŭ���̾�Ʈ�� ����(IP, PORT)
            Socket s=ss.accept(); //�������� ���� accept�� ����ż� while�� �ѹ��� ����.(������������ ����)
            Client client=new Client(s);
            //������� Ŭ���̾�Ʈ�� ����� ���۵ȴ�.
            client.start();
            
            /*//Ŭ���̾�Ʈ�� IP�� Ȯ���غ���.
            System.out.println("Client IP: "+s.getInetAddress());
            System.out.println("Client Port: "+s.getPort());
            
            //�޼�������
            OutputStream out=s.getOutputStream();
            //�������� Ŭ���̾�Ʈ �޸� ����
            out.write(("Server=>���� �޼���...\n").getBytes());*/
         }         
      } catch (Exception e) {
         System.out.println("Server_run_Error: "+e.getMessage());
      }
      
   }
   public static void main(String[] args) {
      Server server=new Server();
      new Thread(server).start();
   }   
   
   //��� �غ� ==> ���� Ŭ����
   public class Client extends Thread
   {
      //�α��ν� �����ϴ� ������ id, name
      CharVO charvo ;
      String pos;
      String id;
      Socket s; //����
      BufferedReader in;
      OutputStream out;
      public Client(Socket s)
      {
         try {
            this.s=s;
            in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            //�����尡 ����ϴ� Ŭ���̾�Ʈ�� �޼����� ���� ����
            out=s.getOutputStream();
            //�����尡 ����ϴ� Ŭ���̾�Ʈ�� �޼��� ����
         } catch (Exception e) {
            System.out.println("Server.Client.run()����: "+e.getMessage());
         }
         charvo = new CharVO();
      }
      //���
      /*
       *  <======���� ========>
       *     (2)�����͸� �޾Ƽ� ó��!
       *     
       *      
       *  <======Ŭ���̾�Ʈ =====>
       *     (1)��û�� ���� �ʿ��� �����͸� ����
       *        ex) �α���  : id, pwd
       *     (3)������� �޾Ƽ� ȭ���� ���!
       */
      public void run()
      {
         try {
            // 100|id|name �̷������� �޴´�
            while(true) {
               String msg=in.readLine();//Ŭ���̾�Ʈ���� ������ �޼���  ó���� ����� ������
               System.out.println("Client=>��û�� : "+msg);
               
               
               // 100|id|name �̷������� �޴´�
               // ��ȣ ==> ���(����)�� ��û��ȣ
               StringTokenizer st=new StringTokenizer(msg, "|");
               int no=Integer.parseInt(st.nextToken());
               switch(no)
               {
                  case Function.LOGIN:
                  {
                     //name = st.nextToken();
                     boolean idcheck=false;
                     id=st.nextToken();
                    // name=st.nextToken();
                     pos="����";
                     for(Client ss:waitList)
                     {
                    	 System.out.println("�α��� id:"+ss.getId());
                    	 if(id.equals(ss.getId()))
                    	 {
                    		 System.out.println(charvo.getId()+": ����");
 							out.write((Function.DUPLICATE+"|\n").getBytes());
 							idcheck = true;
                    	 }
                     }
                     if(idcheck == false)
                     {
                    	 charvo.setId(id);
                    	 charvo.setpos(pos);
                    	 //(*1*)���� ���� ������ ����鿡�� �ڽ��� �����ߴٴ� ���� �˸���.
                    	 out.write((Function.CHARACTERROOM+"|"+charvo.getId()+"\n").getBytes());                    	 
                     }
                     
                     
                     //������ �� ����!
                     /*
                      *  
                      *  �α���
                      *  �氳��
                      *  �����
                      *  �泪����
                      *  ==> ���� 4���� ���ϸ� �������α׷��� �ƹ��͵� �ƴϴ�~
                      */
                  }
                  break;
                	  
                  case Function.CHARACTERCHOICE:
                  {
                	  System.out.println("CHARACTERCHOICE �۵�");
                	  charvo.setId(st.nextToken());
                	  charvo.setIcon(st.nextToken());
              		  charvo.setRank(Integer.parseInt(st.nextToken()));
              		  
                	// (*1*)���� ���� ������ ����鿡�� �ڽ��� �����ߴٴ� ���� �˸���.
                      messageAll(Function.LOGIN+"|"+charvo.getId()+"|"+pos);//������ ��� ������� �α����� �˷��ش�~(���̺� ���)
                      
                      //(*2*)���Ŀ� �ڽ��� ���� ��Ų��.
                      waitList.add(this);
                      //(*3*)������ �ٲ��ֱ�
                      messageTo(Function.CHARACTERCHOICE+"|"+charvo.getId()+"|"+charvo.getIcon()+"|"+charvo.getRank());
                      
                      // (*4*) �α��� ==> ���Ƿ� ȭ���� �����Ų��.
                      System.out.println("server : ȭ�� �ٲٱ�");
                      messageTo(Function.MYLOG+"|");
                      System.out.println("server : ���Ӹ�� �Ѹ���");
                      messageAll(Function.WAITCHAT+"|"+charvo.getId()+"���� �����ϼ̽��ϴ�");
                      
                      // (*5*) �ڽſ��Ը� ������ ������� ������ �Ѹ���.
                      System.out.println("server : ������ ���Ӹ�� �Ѹ���");
                      for(Client client:waitList)
                      {
                         messageTo(Function.LOGIN+"|"
                                    +client.id+"|"
                                 +client.pos);
                      }
                  }
                  break;
                	  
                  //ä�� ��û ó��
                  case Function.WAITCHAT:
                     {
                        String data=st.nextToken();
                        messageAll(Function.WAITCHAT+"|["+id+"]"+data);
                     }
                     break;
                  
                   //�游���
                  case Function.MAKEROOM:
                  {
                	  //Room.java = public Room(String roomName, String roomState, String roomPwd, int maxcount)
                	  Room room=new Room(
								st.nextToken(),
								st.nextToken(), 
								st.nextToken(), 
								Integer.parseInt(st.nextToken()));
						room.userVc.addElement(this);
						pos=room.roomName;
						roomVc.addElement(room);
						messageAll(Function.MAKEROOM+"|"
						           +room.roomName+"|"
						           +room.roomState+"|"
						           +room.current+"/"+room.maxcount);
						/*// 2/6
						// ���(�����)
						messageTo(Function.MYROOMIN+"|"
								+id+"|"+name+"|"
								+sex+"|"+avata+"|"+room.roomName);
						*/
						// ��� ==> client
						messageAll(Function.ROOMNAMEUPDATE+"|"
								+id+"|"+pos);
                  }break;

					/*case Function.MYROOMIN:
					{
						
						 *   ��ã�´�
						 *   �����ο� ����
						 *   ��ġ ����
						 *   ==========
						 *   �濡 �ִ� ��� 
						 *     => �濡 ���� ����� ���� ����
						 *     => ����޼��� 
						 *   �濡 ���� ��� ó��
						 *     => ������ ����
						 *     => �濡 �ִ� ����� ��� ������ �޴´� 
						 *   ���� ó��
						 *     => 1) �ο� (table1)
						 *        2) ��ġ (table2)
						 *        
						 *   ���� , �ʴ� , ���� 
						 
						String rn=st.nextToken();
						for(int i=0;i<roomVc.size();i++)
						{
							Room room=roomVc.elementAt(i);
							if(rn.equals(room.roomName))
							{
								room.current++;
								pos=room.roomName;
								// �濡 �ִ� ��� ó��
								for(int j=0;j<room.userVc.size();j++)
								{
									Client user=room.userVc.elementAt(j);
									user.messageTo(Function.ROOMIN+"|"
										+id+"|"+name+"|"+sex+"|"+avata);
									user.messageTo(Function.ROOMCHAT
											+"|[�˸� ��]"+name+"���� �����ϼ̽��ϴ�");
								}
								// �濡 ���� ��� ó��
								room.userVc.addElement(this);
								messageTo(Function.MYROOMIN+"|"
										+id+"|"+name+"|"
										+sex+"|"+avata+"|"+room.roomName);
								for(int k=0;k<room.userVc.size();k++)
								{
									Client user=room.userVc.elementAt(k);
									if(!id.equals(user.id))
									{
									  messageTo(Function.ROOMIN+"|"
										+user.id+"|"+user.name+"|"
										+user.sex+"|"+user.avata);
									}
								}
								// ���� 
								messageAll(Function.WAITUPDATE+"|"
										+id+"|"+pos+"|"+room.roomName+"|"
										+room.current+"|"+room.maxcount);
							}
						}
					}
					break;
					case Function.ROOMOUT:
					{
						
						 *   ��ã�´�
						 *   �����ο� ����
						 *   ��ġ ����
						 *   ==========
						 *   �濡 �ִ� ��� 
						 *     => �濡 ���� ����� ���� ����
						 *     => ����޼��� 
						 *   �濡 ���� ��� ó��
						 *     => ������ ����
						 *     => �濡 �ִ� ����� ��� ������ �޴´� 
						 *   ���� ó��
						 *     => 1) �ο� (table1)
						 *        2) ��ġ (table2)
						 *        
						 *   ���� , �ʴ� , ���� 
						 
						String rn=st.nextToken();
						for(int i=0;i<roomVc.size();i++)
						{
							Room room=roomVc.elementAt(i);
							if(rn.equals(room.roomName))
							{
								room.current--;
								pos="����";
								// �濡 �ִ� ��� ó��
								for(int j=0;j<room.userVc.size();j++)
								{
									Client user=room.userVc.elementAt(j);
									user.messageTo(Function.ROOMOUT+"|"+id+"|"+name);
									user.messageTo(Function.ROOMCHAT
											+"|[�˸� ��]"+name+"���� �����ϼ̽��ϴ�");
								}
								// �濡 ���� ��� ó��
								//room.userVc.addElement(this);
								messageTo(Function.MYROOMOUT+"|");
								for(int k=0;k<room.userVc.size();k++)
								{
									Client user=room.userVc.elementAt(k);
									if(id.equals(user.id))
									{
									   room.userVc.removeElementAt(k);
									   break;
									}
								}
								// ���� 
								messageAll(Function.WAITUPDATE+"|"
										+id+"|"+pos+"|"+room.roomName+"|"
										+room.current+"|"+room.maxcount);
								if(room.current<1)
								{
									roomVc.removeElementAt(i);
									break;
								}
							}
						}
					}
					break;*/
                  
                  
                     
               }//swith�� ��
               
            }
         } catch (Exception e) {}
            // TODO: handle exception
      }
      
      /*
       *  ���� => Ŭ���̾�Ʈ ���� �޼���
       */
      
      // ��ü���� �޼���
      public void messageAll(String msg)
      {
         try {
            for(Client client:waitList) {
               client.messageTo(msg);
            }
         } catch (Exception e) {}
      }
      // ���������� �����ϴ� �޼���
      public void messageTo(String msg)
      {
         try {
            out.write((msg+"\n").getBytes());
         } catch (Exception e) {
            // TODO: handle exception
         }
      }
      
   }
}