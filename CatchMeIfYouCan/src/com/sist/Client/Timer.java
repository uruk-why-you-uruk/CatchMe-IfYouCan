package com.sist.Client;
import java.util.Scanner;
public class Timer {
	  public static void main(String[] args) throws InterruptedException
	  {
	   //Scanner scan = new Scanner(System.in);
	    //System.out.println("How much time (In Minutes) ?");
	    int timet= 140; // Convert to seconds
	    long delay = timet * 1000;

	    do
	    {
	      int minutes = timet / 60;
	      int seconds = timet % 60;
	      System.out.println(minutes+" : " + seconds);
	      Thread.sleep(1000);
	      timet = timet - 1;
	      delay = delay - 1000;

	    }
	    while (delay != 0);
	    System.out.println("Time's Up!");
	  }
	}
