package com.sist.Server;

import java.util.*;
/*
 *  ����Ŭ����
 *  class A
 *  {
 *     class B
 *     {
 *        
 *     }
 *  }
 */
public class Room {
	Vector<Server.Client> userVc = new Vector<Server.Client>();
	//�濡 �� ��� ��� ����!!!!
	String roomName, roomState, roomPwd;
	int current; //�����ο�
	int maxcount; //������ �ο�
	int roomNumber; //���ȣ
	boolean gameState = false;
	
	public Room() {};

	public Room(int roomNumber, String roomName, String roomState, String roomPwd, int maxcount) {
		current=1;
		this.roomNumber=roomNumber;
		this.roomName = roomName;
		this.roomState = roomState;
		this.roomPwd = roomPwd;
		this.maxcount = maxcount;
	}

}
