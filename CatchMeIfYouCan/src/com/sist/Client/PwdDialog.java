package com.sist.Client;

import javax.swing.JDialog;
import javax.swing.JFrame;

import java.awt.*;

import javax.swing.*;
/*
 * 주석 추가
 */
public class PwdDialog extends JDialog{    
   JTextField tf = new JTextField(10);
   JButton okButton = new JButton("OK");
   public static boolean pwdCheck=false;    

   public PwdDialog(JFrame frame, String title) {
      super(frame,title, true);
      setLayout(new FlowLayout());
      add(tf);
      add(okButton);
      setSize(200, 100);
   }

}