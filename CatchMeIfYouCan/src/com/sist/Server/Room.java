package com.sist.Server;

import java.util.*;
/*
 *  내부클래스
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
	//방에 들어간 사람 명단 저장!!!!
	String roomName, roomState, roomPwd;
	int current; //현재인원
	int maxcount; //설정된 인원
	int roomNumber; //방번호
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
