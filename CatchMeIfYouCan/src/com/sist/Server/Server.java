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
    	Socket s;
    	OutputStream out;
    	
    	BufferedReader in;
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
