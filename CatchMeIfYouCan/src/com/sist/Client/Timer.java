package com.sist.Client;
import java.awt.*;
import javax.swing.*;

 

public class Timer extends JPanel{
    JLabel timerLabel = null;
    static int k;
    public Timer()
    {
        this.setLayout(new FlowLayout());
        timerLabel = new JLabel("0");
        timerLabel.setFont(new Font("Gothic",Font.ITALIC,80));
        
        k = 150;
        while(k!=-1)
        {
        	int minutes = k / 60;
            int seconds = k % 60;
            timerLabel.setText(String.valueOf(String.format("%02d:%02d",minutes, seconds)));
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            k--;
        }
        this.setVisible(true);
    }


}

