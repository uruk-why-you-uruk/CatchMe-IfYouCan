package com.sist.Server;
import java.util.*;

import com.sist.Server.Server.Client;
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
   private final int PORT=7339;
   //private String name;
   private String location;
   public static int roomN=0;
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
      /* (non-Javadoc)
     * @see java.lang.Thread#run()
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
                    	 System.out.println("�α��� id:"+ss.charvo.getId());
                    	 if(id.equals(ss.charvo.getId()))
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
                    	 System.out.println("�ߺ��� �� ����");
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
                      messageAll(Function.LOGIN+"|"+charvo.getId()+"|"+charvo.getpos());//������ ��� ������� �α����� �˷��ش�~(���̺� ���)
                      
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
                      
                      // ������ ���� (ĳ���ͼ����� ���Ƿ� �Ѿ�� �κ�)
                      
                      
                      
                      /// �������� ver
                      for(Room room:roomVc)
                      {
                    	  if(!room.equals(null)) {
                    		  System.out.println("�뺤�Ͱ� ���� �ƴմϴ�!");
                    		 messageTo(Function.MAKEROOM+"|"
									+room.roomNumber+"|"
						           +room.roomName+"|"
						           +room.roomState+"|"
						           +room.current+"/"+room.maxcount+"|"
						           +room.roomPwd); 
                    	  }
                    	  
	                  }
                  } break;
                 
                	  
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
                	  //out.write((Function.MAKEROOM + "|" + rname + "|" + state.trim() + "|" + pwd.trim() + "|" + (inwon + 4) + "\n")
                	  //Room.java = public Room(int roomNumber,String roomName, String roomState, String roomPwd, int maxcount)
                	  Room room=new Room(
                			  	(roomN++),
								st.nextToken(),
								st.nextToken(), 
								st.nextToken(), 
								Integer.parseInt(st.nextToken()));
						//room.userVc.addElement(this);
                	    room.userVc.addElement(this);
						pos=room.roomName;
						roomVc.addElement(room);
						messageAll(Function.MAKEROOM+"|"
									+room.roomNumber+"|"
						           +room.roomName+"|"
						           +room.roomState+"|"
						           +room.current+"/"+room.maxcount+"|"
						           +room.roomPwd);
						// 2/6
						// ���(�����)
						System.out.println("MAKEROOM :"+room.current);
						messageTo(Function.MYROOMIN+"|"
								+charvo.getId()+"|"+charvo.getRank()+"|"+charvo.getIcon()+"|"+room.maxcount);
						
						// ��� ==> client 
						messageAll(Function.ROOMNAMEUPDATE+"|"
								+charvo.getId()+"|"+pos+"��");
                  }break;
                  /*   ��ã�´�
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
					 *   ���� , �ʴ� , ���� */

					case Function.MYROOMIN:
					{
						int rn=Integer.parseInt(st.nextToken());
						for(int i=0;i<roomVc.size();i++)
						{
							Room room=roomVc.elementAt(i);
							if(rn==room.roomNumber)
							{
								room.current++;
								pos=room.roomName;
								// �濡 �ִ� ��� ó��
								for(int j=0;j<room.userVc.size();j++)
								{
									Client user=room.userVc.elementAt(j);
									System.out.println("Server current:"+room.current);
									user.messageTo(Function.ROOMIN+
											"|"+charvo.getId()+"|"+charvo.getRank()+"|"+charvo.getIcon()+"|"+room.maxcount);
									user.messageTo(Function.ROOMCHAT
											+"|[�˸� ��]"+user.charvo.getId()+"���� �����ϼ̽��ϴ�");
								}
								// �濡 ���� ��� ó��
								//room.userVc.addElement(this);
								System.out.println("myroomin�濡 ���� ���� UserVC size(): "+room.userVc.size());
								for(int z=0;z<room.userVc.size();z++)
								{
									System.out.println((z+1)+"��°: "+room.userVc.elementAt(z).charvo.getId());
							    }
								messageTo(Function.MYROOMIN+"|"
										+charvo.getId()+"|"+charvo.getRank()+"|"+charvo.getIcon()+"|"+room.maxcount);
								for(int k=0;k<room.userVc.size();k++)
								{
									Client user=room.userVc.elementAt(k);
									if(!charvo.getId().equals(user.charvo.getId()))
									{
										
										System.out.println((k+1)+"��° ��� :"+user.charvo.getId());
									  messageTo(Function.ROOMIN+"|"
											  +user.charvo.getId()+"|"+user.charvo.getRank()+"|"+user.charvo.getIcon()+"|"+room.maxcount);
									}
								}
								room.userVc.addElement(this);
								// ���� 
								/*messageAll(Function.WAITUPDATE+"|"
										+id+"|"+pos+"|"+room.roomName+"|"
										+room.current+"|"+room.maxcount);*/
							}
						}
					}
					break;
					 /*   ��ã�´�
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
					 *   ���� , �ʴ� , ���� */
					case Function.ROOMIN:
					{
					
						int rn=Integer.parseInt(st.nextToken());
						for(int i=0;i<roomVc.size();i++)
						{
							Room room=roomVc.elementAt(i);
							if(rn==room.roomNumber)
							{
								room.current++;
								pos=room.roomName;
								// �濡 �ִ� ��� ó��
								for(int j=0;j<room.userVc.size();j++)
								{
									Client user=room.userVc.elementAt(j);
									user.messageTo(Function.ROOMIN+
											"|"+user.charvo.getId()+"|"+user.charvo.getRank()+"|"+user.charvo.getIcon()+"|"+room.maxcount);
									user.messageTo(Function.ROOMCHAT
											+"|[�˸� ��]"+user.charvo.getId()+"���� �����ϼ̽��ϴ�");
								}
								// �濡 ���� ��� ó��
								//room.userVc.addElement(this);
								System.out.println("roomin�濡 ���� ���� UserVC size(): "+room.userVc.size());
								for(int z=0;z<room.userVc.size();z++)
								{
									System.out.println((z+1)+"��°: "+room.userVc.elementAt(z).charvo.getId());
							    }
								messageTo(Function.MYROOMIN+"|"
										+charvo.getId()+"|"+charvo.getRank()+"|"+charvo.getIcon()+"|"+room.maxcount);
								for(int k=0;k<room.userVc.size();k++)
								{
									Client user=room.userVc.elementAt(k);
									if(!charvo.getId().equals(user.charvo.getId()))
									{
									  messageTo(Function.ROOMIN+"|"
											  +user.charvo.getId()+"|"+user.charvo.getRank()+"|"+user.charvo.getIcon()+"|"+room.maxcount);
									}
								}
								room.userVc.addElement(this);
								// ���� 
								messageAll(Function.WAITUPDATE+"|"
										+charvo.getId()+"|"+pos+"|"+room.roomName+"|"
										+room.current+"|"+room.maxcount);
							}
						}
					}
					break;
					/*
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
					 */
					case Function.ROOMOUT:
					{
					
						int rn=Integer.parseInt(st.nextToken());
						for(int i=0;i<roomVc.size();i++)
						{
							Room room=roomVc.elementAt(i);
							if(rn==room.roomNumber)
							{
								System.out.println("Server roomVC�� number:"+rn+","+room.roomNumber);
								room.current--;
								pos="����";
								// �濡 �ִ� ��� ó��
								for(int j=0;j<room.userVc.size();j++)
								{
									Client user=room.userVc.elementAt(j);
									user.messageTo(Function.ROOMOUT+"|"+charvo.getId());
									user.messageTo(Function.ROOMCHAT
											+"|[�˸� ��]"+charvo.getId()+"���� �����ϼ̽��ϴ�");
								}
								// �濡 �ִ� ��� ó��??
								//room.userVc.addElement(this);
								messageTo(Function.MYROOMOUT+"|");
								for(int k=0;k<room.userVc.size();k++)
								{
									Client user=room.userVc.elementAt(k);
									if(charvo.getId().equals(user.charvo.getId()))
									{
										System.out.println(room.userVc.elementAt(k).charvo.getId()+": DELETE");
									   room.userVc.removeElementAt(k);
									   for(int z=0;z<room.userVc.size();z++)
									   {
										   System.out.println((z+1)+"��°: "+room.userVc.elementAt(z).charvo.getId());
									   }
									   break;
									}
								}
								// ���� 
								messageAll(Function.WAITUPDATE+"|"
										+charvo.getId()+"|"+pos+"|"+room.roomName+"|"
										+room.current+"|"+room.maxcount);
								if(room.current<1)
								{
									roomVc.removeElementAt(i);
									break;
								}
							}
						}
					}
					break;
					
					 case Function.ROOMCHAT:
                     {
                        String rn=st.nextToken();
                        String data=st.nextToken();
						for(int i=0;i<roomVc.size();i++)
						{
							Room room=roomVc.elementAt(i);
							if(Integer.parseInt(rn)==room.roomNumber)
							{
		                        for(int k=0;k<room.userVc.size();k++)
								{
									Client user=room.userVc.get(k);
									// get(i)=elements
									user.messageTo(Function.ROOMCHAT+"|["+charvo.getId()+"]"+data);
									
								}
							}
                       }break;
                     }//swith�� ��
               
               }
            }
         } catch (Exception e) {System.out.println(e.getMessage());}
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