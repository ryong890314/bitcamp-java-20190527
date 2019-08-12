package com.eomcs.lms;

import java.util.Map;
import com.eomcs.lms.context.ServletContextListener;
import com.eomcs.lms.dao.csv.BoardCsvDao;
import com.eomcs.lms.dao.csv.LessonCsvDao;
import com.eomcs.lms.dao.csv.MemberCsvDao;
import com.eomcs.lms.servlet.BoardServlet;
import com.eomcs.lms.servlet.LessonServlet;
import com.eomcs.lms.servlet.MemberServlet;

// 서버가 시작되거나 종료될 때 보고를 받고 작업을 수행하는 역할
// => ServletContextListener 규칙을 준비해야만 서버의 시작과 종료 알림을 받을 수 있다.

public class AppInitListener implements ServletContextListener {

  BoardCsvDao boardDao;
  MemberCsvDao memberDao;
  LessonCsvDao lessonDao;

  @Override
  public void contextInitialized(Map<String, Object> context) {
    System.out.println("서버를 시작했으니 객체를 준비해야겠다");
    try {

      // boardDao = new BoardSerialDao("./board.ser");
      boardDao = new BoardCsvDao("./board.csv");
      context.put("boardDao", boardDao);

    } catch (Exception e) {
      System.out.println("데이터 로딩중 오류 발생!");
    }

    try {

      // memberDao = new MemberSerialDao("./member.ser");
      memberDao = new MemberCsvDao("./member.csv");
      context.put("memberDao", memberDao);

    } catch (Exception e) {
      System.out.println("데이터 로딩중 오류 발생!");
    }

    try {

      // lessonDao = new LessonSerialDao("./lesson.ser");
      lessonDao = new LessonCsvDao("./lesson.csv");
      context.put("lessonDao", lessonDao);

    } catch (Exception e) {
      System.out.println("데이터 로딩중 오류 발생!");
    }
    
    context.put("/board/", new BoardServlet(boardDao));
    context.put("/member/", new MemberServlet(memberDao));
    context.put("/lesson/", new LessonServlet(lessonDao));

  }

  @Override
  public void contextDestroyed(Map<String, Object> context) {
    // TODO Auto-generated method stub
    System.out.println("서버를 종료했으니 객체를 저장해야겠다");
    boardDao.saveData();
    lessonDao.saveData();
    memberDao.saveData();

  }

}
