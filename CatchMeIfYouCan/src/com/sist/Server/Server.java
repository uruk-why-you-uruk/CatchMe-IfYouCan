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
   private final int PORT=7339;
   //private String name;
   private String location;
   
   
   //Ŭ���̾�Ʈ�� ������ ����
   private ArrayList<Client> waitList =new ArrayList<Client>();
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
                     //location = "ĳ���ͼ���";
                     String id=st.nextToken();
                    // name=st.nextToken();
                     charvo.setpos("����");
                     for(Client ss:waitList)
                     {
                    	 if(id.equals(ss.getId()))
                    	 {
                    		 System.out.println(charvo.getId()+": ����");
 							out.write((Function.DUPLICATE+"|\n").getBytes());
                    	 }
                     }
                     
                     charvo.setId(id);
                     // (*1*)���� ���� ������ ����鿡�� �ڽ��� �����ߴٴ� ���� �˸���.
                     messageAll(Function.LOGIN+"|"+id+"|"/*+name+"|"*/+charvo.getpos());//������ ��� ������� �α����� �˷��ش�~(���̺� ���)
                     
                     //(*2*)���Ŀ� �ڽ��� ���� ��Ų��.         
                     waitList.add(this);
                     
                     // (*3*) �α��� ==> ���Ƿ� ȭ���� �����Ų��.
                     messageTo(Function.MYLOG+"|"+(id+"���� �����ϼ̽��ϴ�"));
                     
                     // (*4*) �ڽſ��Ը� ������ ������� ������ �Ѹ���.
                     for(Client client:waitList)
                     {
                        messageTo(Function.LOGIN+"|"
                                   +charvo.getId()+"|"
                                +/*client.name+"|"+*/charvo.getpos());
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
                  //ä�� ��û ó��
                  case Function.WAITCHAT:
                     {
                        String data=st.nextToken();
                        messageAll(Function.WAITCHAT+data);
                     }
                     break;
               }
               
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