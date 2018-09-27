package com.sist.common; 
//LOGIN|aaaaaaaa
//out.write(("100|id|pwd")) 
//out.write(("LOGIN|id|pwd"))
public class Function { 
	//public static final 데이터형 변수명 = 상수; // 설명
	public static final int LOGIN=100;// 로그인 안된 사람들  
	public static final int DUPLICATE=110; //중복처리
	//public static final int SEMILOG=120; //캐릭터선택창에 있는 사람들 
    //캐릭터창 (각각의 화면)
    public static final int CHARACTERROOM=200;//캐릭터선택창 (각각)
    public static final int CHARACTERCHOICE=210;//선택된 캐릭터 엔터!!!선택한 캐릭터의 번호가 전송
    //로그인, 캐선 완료!!암튼완료!
    public static final int MYLOG=130;// 모든 과정을 끝내고 로그인하는 사람들
    //방관련 메세지
    public static final int MAKEROOM=300;// 방만들기
    public static final int ROOMIN=310;//방들어가기
    public static final int ROOMOUT=320;//방나가기(남아있는 사람)
    public static final int MYROOMIN=330;//방들어가는 사람 처리
    public static final int MYROOMOUT=340;//방나가는 사람 처리
    public static final int WAITUPDATE=350;//대기실 수정 
    public static final int WAITCHAT=360;//대기실 채팅
    public static final int ROOMNAMEUPDATE=370;//방이름 변경
    public static final int ROOMUPDATE=380;

    //GAME 메세지
    public static final int GAMEREADY=400;//게임 준비 인원 3명 미만
    public static final int GAMESTART=410;//게임시작 인원이 3명이상이면 자동실행
    public static final int GAMEMUNJE=420;//문제비출제자
    public static final int GAMEMYMUNJE=430;//문제출제자
    public static final int GAMEDAP=440;//답 제출, 서버의 답과 채팅에서 친 답 비교
    public static final int GAMEYESNO=450;//정답과 오답 전송

    public static final int GAMEGIVEUP=460; //게임포기
    public static final int GAMECLEANALL=470;//스케치화면 초기화
    public static final int GAMECOLOR=480;//색선택
    public static final int ROOMCHAT=490;//게임방 채팅
    public static final int GAMEDOMANG=500;//게임 도망(대기실로나감)
    public static final int GAMEANDOMANG=510; //게임 도망(남아있는사람)
    public static final int GAMETIMEOVER=520; //시간이 끝날때까지 맞춘 사람이 없는 거 처리
    public static final int GAMEROUNDEND=530;//게임 각 라운드 종료오
    public static final int GAMEEND=540;//(모든 라운드 끝)
    
    public static final int MOUSEMOVE=600;//(모든 라운드 끝)
    public static final int MOUSEPRESS=610;//(모든 라운드 끝)
    // 채팅 관련
    public static final int CHAT=700;//채팅하기
    public static final int LOGOUT=900;//클라이언트종료??

    
}
