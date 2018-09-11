package com.sist.Server;
import java.util.*;
import com.sist.common.*;
import java.io.*;
import java.net.*;
public class Server implements Runnable{
   ServerSocket ss;
   Vector<Client> waitVc=new Vector<Client>(); 
   public Server()
   {
      try
      {
         ss=new ServerSocket(7339);
         System.out.println("서버 가동...");
      }catch(Exception ex){
         System.out.println(ex.getMessage());
      }
   }
   public void run()
   {
	   //접속자 정보 저장!
      try
      {
         while(true)
         {
            Socket s=ss.accept();
            Client client=new Client(s);
            waitVc.addElement(client);
            client.start();
            System.out.println("client접속완료");
         }
      }catch(Exception ex){}
   }
   public static void main(String[] args) {
      // TODO Auto-generated method stub
        Server server=new Server();
        new Thread(server).start();
   }
   
    class Client extends Thread
    {
    	//클라이언트의 통신
       Socket s;
       OutputStream out;  //클라이언트 송신
       BufferedReader in; //클라이언트 수신
       public Client(Socket s)
       {
          try
          {
             this.s=s;
             out=s.getOutputStream(); // 
             in=new BufferedReader(
                   new InputStreamReader(
                         s.getInputStream()));
          }catch(Exception ex){}
       }
       public void run()
       {
    	   //통신
          try
          {
             while(true)
             {           
                String msg=in.readLine();
                StringTokenizer st=new StringTokenizer(msg, "|");
                   int no=Integer.parseInt(st.nextToken());

                   switch(no)
                      {
                        case Function.LOGIN:
                        {
                         
                        }break;
                        
                      }

                for(int i=0;i<waitVc.size();i++)
                {
                   Client user=waitVc.elementAt(i);
                   if(user!=this)
                   {
                     user.out.write((msg+"\n").getBytes());
                   }
                }
             }
          }catch(Exception ex){}
       }
    }
}