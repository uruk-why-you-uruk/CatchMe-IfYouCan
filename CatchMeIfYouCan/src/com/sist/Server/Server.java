package com.sist.Server;
import java.util.*;

import com.sist.Server.Server.Client;
import com.sist.Vo.CharVO;
import com.sist.common.Function;

import java.net.*;
import java.io.*;
/*
 *    1. 연결 기계 (핸드폰)  ==>구매
 *       =====
 *        Socket : 다른 컴퓨터와 연결
 *        
 *    2. 셋팅 ==> 개통(전화번호, 전화선)연결
 *              ===  ip, port
 *             유심에 심는다
 *             ========
 *             bind(ip,port)
 *    
 *    3. 대기(전화가 올 때 까지 기다린다.)
 *            listen()
 *    =========================== 개인용(P2P)
 *    멀티
 *    ===== 
 *     1) 교환소켓
 *     2) 통신소켓 ==> 접속자마다 생성 ==> Thread
 *    
 */
public class Server implements Runnable{
   // 서버 소켓 생성
   private ServerSocket ss;
   private final int PORT=7339;
   //private String name;
   private String location;
   public static int roomN=0;
   //클라이언트의 정보를 저장
   private ArrayList<Client> waitList =new ArrayList<Client>();
   
   private Vector<Room> roomVc=new Vector<Room>(); // table1 
   //클라이언트의 IP, id....
   
   public Server() { //프로그램에서 시작과 동시 수행: 생선자, main
      //서버 ==> 구동할때 한개 컴퓨터에서 두번을 실행 할 수 없다.
      try {         
         ss=new ServerSocket(PORT);
         //생성자 메소드 안에   bind():개통, listen(): 대기가 포함돼있다.
         System.out.println("Server Start...");
      } catch (Exception e) {
         System.out.println("Server_Error: "+e.getMessage());
      }
   }
   //접속 했을 때 처리하는 기능
   public void run() {
      try {
         //클라이언트의 발신자 IP확인 ==> Socket안에 IP가 있따~
         while(true) {
            //Socket s == 접속한 클라이언트의 정보(IP, PORT)
            Socket s=ss.accept(); //접속했을 때만 accept가 실행돼서 while을 한바퀴 돈다.(접속했을때만 실행)
            Client client=new Client(s);
            //스레드와 클라이언트의 통신이 시작된다.
            client.start();
            
            /*//클라이언트의 IP를 확인해보자.
            System.out.println("Client IP: "+s.getInetAddress());
            System.out.println("Client Port: "+s.getPort());
            
            //메세지전송
            OutputStream out=s.getOutputStream();
            //서버에서 클라이언트 메모리 연결
            out.write(("Server=>전송 메세지...\n").getBytes());*/
         }         
      } catch (Exception e) {
         System.out.println("Server_run_Error: "+e.getMessage());
      }
      
   }
   public static void main(String[] args) {
      Server server=new Server();
      new Thread(server).start();
   }   
   
   //통신 준비 ==> 내부 클래스
   public class Client extends Thread
   {
      //로그인시 전송하는 데이터 id, name
      CharVO charvo ;
      String pos;
      String id;
      Socket s; //연결
      BufferedReader in;
      OutputStream out;
      public Client(Socket s)
      {
         try {
            this.s=s;
            in=new BufferedReader(new InputStreamReader(s.getInputStream()));
            //쓰레드가 담당하는 클라이언트의 메세지를 받을 변수
            out=s.getOutputStream();
            //쓰레드가 담당하는 클라이언트에 메세지 전송
         } catch (Exception e) {
            System.out.println("Server.Client.run()에러: "+e.getMessage());
         }
         charvo = new CharVO();
      }
      //통신
      /*
       *  <======서버 ========>
       *     (2)데이터를 받아서 처리!
       *     
       *      
       *  <======클라이언트 =====>
       *     (1)요청을 위해 필요한 데이터를 전송
       *        ex) 로그인  : id, pwd
       *     (3)결과값을 받아서 화면을 출력!
       */
      /* (non-Javadoc)
     * @see java.lang.Thread#run()
     */
    public void run()
      {
         try {
            // 100|id|name 이런식으로 받는다
            while(true) {
               String msg=in.readLine();//클라이언트에서 전송한 메세지  처리후 결과값 보내기
               System.out.println("Client=>요청값 : "+msg);
               
               
               // 100|id|name 이런식으로 받는다
               // 번호 ==> 기능(행위)의 요청번호
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
                     pos="대기실";
                     for(Client ss:waitList)
                     {
                    	 System.out.println("로그인 id:"+ss.charvo.getId());
                    	 if(id.equals(ss.charvo.getId()))
                    	 {
                    		 System.out.println(charvo.getId()+": 존재");
 							out.write((Function.DUPLICATE+"|\n").getBytes());
 							idcheck = true;
                    	 }
                     }
                     if(idcheck == false)
                     {
                    	 charvo.setId(id);
                    	 charvo.setpos(pos);
                    	 //(*1*)제일 먼저 접속한 사람들에게 자신이 접속했다는 것을 알린다.
                    	 System.out.println("중복된 값 없음");
                    	 out.write((Function.CHARACTERROOM+"|"+charvo.getId()+"\n").getBytes());                    	 
                     }
                     
                     
                     //개설된 방 전송!
                     /*
                      *  
                      *  로그인
                      *  방개설
                      *  방들어가기
                      *  방나가기
                      *  ==> 위에 4개만 잘하면 게임프로그램은 아무것도 아니다~
                      */
                  }
                  break;
                	  
                  case Function.CHARACTERCHOICE:
                  {
                	  System.out.println("CHARACTERCHOICE 작동");
                	  charvo.setId(st.nextToken());
                	  charvo.setIcon(st.nextToken());
              		  charvo.setRank(Integer.parseInt(st.nextToken()));
              		  
                	// (*1*)제일 먼저 접속한 사람들에게 자신이 접속했다는 것을 알린다.
                      messageAll(Function.LOGIN+"|"+charvo.getId()+"|"+charvo.getpos());//접속한 모든 사람에게 로그인을 알려준다~(테이블에 출력)
                      
                      //(*2*)이후에 자신을 접속 시킨다.
                      waitList.add(this);
                      //(*3*)아이콘 바꿔주기
                      messageTo(Function.CHARACTERCHOICE+"|"+charvo.getId()+"|"+charvo.getIcon()+"|"+charvo.getRank());
                      
                      // (*4*) 로그인 ==> 대기실로 화면을 변경시킨다.
                      System.out.println("server : 화면 바꾸기");
                      messageTo(Function.MYLOG+"|");
                      
                      System.out.println("server : 접속명단 뿌리기");
                      messageAll(Function.WAITCHAT+"|"+charvo.getId()+"님이 접속하셨습니다");
                      
                      // (*5*) 자신에게만 접속한 사람들의 정보를 뿌린다.
                      System.out.println("server : 나에게 접속명단 뿌리기");
                      for(Client client:waitList)
                      {
                         messageTo(Function.LOGIN+"|"
                                    +client.id+"|"
                                 +client.pos);
                      }
                      
                      // 방정보 전송 (캐릭터선택후 대기실로 넘어가는 부분)
                      
                      
                      
                      /// 정일이형 ver
                      for(Room room:roomVc)
                      {
                    	  if(!room.equals(null)) {
                    		  System.out.println("룸벡터가 널이 아닙니다!");
                    		 messageTo(Function.MAKEROOM+"|"
									+room.roomNumber+"|"
						           +room.roomName+"|"
						           +room.roomState+"|"
						           +room.current+"/"+room.maxcount+"|"
						           +room.roomPwd); 
                    	  }
                    	  
	                  }
                  } break;
                 
                	  
                  //채팅 요청 처리
                  case Function.WAITCHAT:
                     {
                        String data=st.nextToken();
                        messageAll(Function.WAITCHAT+"|["+id+"]"+data);
                     }
                     break;
                  
                   //방만들기
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
						// 명령(방들어가기)
						System.out.println("MAKEROOM :"+room.current);
						messageTo(Function.MYROOMIN+"|"
								+charvo.getId()+"|"+charvo.getRank()+"|"+charvo.getIcon()+"|"+room.maxcount);
						
						// 출력 ==> client 
						messageAll(Function.ROOMNAMEUPDATE+"|"
								+charvo.getId()+"|"+pos+"방");
                  }break;
                  /*   방찾는다
					 *   현재인원 증가
					 *   위치 변경
					 *   ==========
					 *   방에 있는 사람 
					 *     => 방에 들어가는 사람의 정보 전송
					 *     => 입장메세지 
					 *   방에 들어가는 사람 처리
					 *     => 방으로 변경
					 *     => 방에 있는 사람의 모든 정보를 받는다 
					 *   대기실 처리
					 *     => 1) 인원 (table1)
					 *        2) 위치 (table2)
					 *        
					 *   강퇴 , 초대 , 게임 */

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
								// 방에 있는 사람 처리
								for(int j=0;j<room.userVc.size();j++)
								{
									Client user=room.userVc.elementAt(j);
									System.out.println("Server current:"+room.current);
									user.messageTo(Function.ROOMIN+
											"|"+charvo.getId()+"|"+charvo.getRank()+"|"+charvo.getIcon()+"|"+room.maxcount);
									user.messageTo(Function.ROOMCHAT
											+"|[알림 ☞]"+user.charvo.getId()+"님이 입장하셨습니다");
								}
								// 방에 들어가는 사람 처리
								//room.userVc.addElement(this);
								System.out.println("myroomin방에 들어가고 나서 UserVC size(): "+room.userVc.size());
								for(int z=0;z<room.userVc.size();z++)
								{
									System.out.println((z+1)+"번째: "+room.userVc.elementAt(z).charvo.getId());
							    }
								messageTo(Function.MYROOMIN+"|"
										+charvo.getId()+"|"+charvo.getRank()+"|"+charvo.getIcon()+"|"+room.maxcount);
								for(int k=0;k<room.userVc.size();k++)
								{
									Client user=room.userVc.elementAt(k);
									if(!charvo.getId().equals(user.charvo.getId()))
									{
										
										System.out.println((k+1)+"번째 사람 :"+user.charvo.getId());
									  messageTo(Function.ROOMIN+"|"
											  +user.charvo.getId()+"|"+user.charvo.getRank()+"|"+user.charvo.getIcon()+"|"+room.maxcount);
									}
								}
								room.userVc.addElement(this);
								// 대기실 
								/*messageAll(Function.WAITUPDATE+"|"
										+id+"|"+pos+"|"+room.roomName+"|"
										+room.current+"|"+room.maxcount);*/
							}
						}
					}
					break;
					 /*   방찾는다
					 *   현재인원 증가
					 *   위치 변경
					 *   ==========
					 *   방에 있는 사람 
					 *     => 방에 들어가는 사람의 정보 전송
					 *     => 입장메세지 
					 *   방에 들어가는 사람 처리
					 *     => 방으로 변경
					 *     => 방에 있는 사람의 모든 정보를 받는다 
					 *   대기실 처리
					 *     => 1) 인원 (table1)
					 *        2) 위치 (table2)
					 *        
					 *   강퇴 , 초대 , 게임 */
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
								// 방에 있는 사람 처리
								for(int j=0;j<room.userVc.size();j++)
								{
									Client user=room.userVc.elementAt(j);
									user.messageTo(Function.ROOMIN+
											"|"+user.charvo.getId()+"|"+user.charvo.getRank()+"|"+user.charvo.getIcon()+"|"+room.maxcount);
									user.messageTo(Function.ROOMCHAT
											+"|[알림 ☞]"+user.charvo.getId()+"님이 입장하셨습니다");
								}
								// 방에 들어가는 사람 처리
								//room.userVc.addElement(this);
								System.out.println("roomin방에 들어가고 나서 UserVC size(): "+room.userVc.size());
								for(int z=0;z<room.userVc.size();z++)
								{
									System.out.println((z+1)+"번째: "+room.userVc.elementAt(z).charvo.getId());
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
								// 대기실 
								messageAll(Function.WAITUPDATE+"|"
										+charvo.getId()+"|"+pos+"|"+room.roomName+"|"
										+room.current+"|"+room.maxcount);
							}
						}
					}
					break;
					/*
					 *   방찾는다
					 *   현재인원 증가
					 *   위치 변경
					 *   ==========
					 *   방에 있는 사람 
					 *     => 방에 들어가는 사람의 정보 전송
					 *     => 입장메세지 
					 *   방에 들어가는 사람 처리
					 *     => 방으로 변경
					 *     => 방에 있는 사람의 모든 정보를 받는다 
					 *   대기실 처리
					 *     => 1) 인원 (table1)
					 *        2) 위치 (table2)
					 *        
					 *   강퇴 , 초대 , 게임 
					 */
					case Function.ROOMOUT:
					{
					
						int rn=Integer.parseInt(st.nextToken());
						for(int i=0;i<roomVc.size();i++)
						{
							Room room=roomVc.elementAt(i);
							if(rn==room.roomNumber)
							{
								System.out.println("Server roomVC의 number:"+rn+","+room.roomNumber);
								room.current--;
								pos="대기실";
								// 방에 있는 사람 처리
								for(int j=0;j<room.userVc.size();j++)
								{
									Client user=room.userVc.elementAt(j);
									user.messageTo(Function.ROOMOUT+"|"+charvo.getId());
									user.messageTo(Function.ROOMCHAT
											+"|[알림 ☞]"+charvo.getId()+"님이 퇴장하셨습니다");
								}
								// 방에 있는 사람 처리??
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
										   System.out.println((z+1)+"번째: "+room.userVc.elementAt(z).charvo.getId());
									   }
									   break;
									}
								}
								// 대기실 
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
                     }//swith문 끝
               
               }
            }
         } catch (Exception e) {System.out.println(e.getMessage());}
            // TODO: handle exception
      }
      
      /*
       *  서버 => 클라이언트 전송 메세지
       */
      
      // 전체전송 메세지
      public void messageAll(String msg)
      {
         try {
            for(Client client:waitList) {
               client.messageTo(msg);
            }
         } catch (Exception e) {}
      }
      // 개인적으로 전송하는 메세지
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