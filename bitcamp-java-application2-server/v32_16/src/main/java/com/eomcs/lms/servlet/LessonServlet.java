package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.Servlet;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.dao.serial.LessonSerialDao;
import com.eomcs.lms.domain.Lesson;

// 게시물 요청을 처리하는 담당자
public class LessonServlet implements Servlet {

  // 수업 DAO를 교체하기 쉽도록 인터페이스의 레퍼런스로 선언한다.
  LessonDao lessonDao;
  ObjectInputStream in;
  ObjectOutputStream out;

  public LessonServlet(LessonDao lessonDao, ObjectInputStream in, ObjectOutputStream out)
      throws ClassNotFoundException {
    this.in = in;
    this.out = out;
    this.lessonDao = lessonDao;

    lessonDao = new LessonSerialDao("./lesson.ser");
  }

  @Override
  public void service(String command) throws Exception {

    switch (command) {
      case "/lesson/add":
        addLesson();
        break;
      case "/lesson/list":
        listLesson();
        break;
      case "/lesson/delete":
        deleteLesson();
        break;
      case "/lesson/detail":
        detailLesson();
        break;
      case "/lesson/update":
        updateLesson();
        break;
      case "quit":
        out.writeUTF("ok");
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }



  private void updateLesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();

    // 서버쪽에서 게시글 변경일을 설정해야 한다.
    // => 클라이언트에서 보내 온 날짜는 조작된 날짜일 수 있기 때문이다.
    lesson.setStartDate(new Date(System.currentTimeMillis()));
    lesson.setEndDate(new Date(System.currentTimeMillis()));

    if (lessonDao.update(lesson) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void detailLesson() throws Exception {
    int no = in.readInt();

    Lesson lesson = lessonDao.findBy(no);
    if (lesson == null) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(lesson);
  }

  private void deleteLesson() throws Exception {
    int no = in.readInt();

    if (lessonDao.delete(no) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void addLesson() throws Exception {
    Lesson lesson = (Lesson) in.readObject();

    // 서버쪽에서 게시글 변경일을 설정해야 한다.
    // => 클라이언트에서 보내 온 날짜는 조작된 날짜일 수 있기 때문이다.
    lesson.setStartDate(new Date(System.currentTimeMillis()));
    lesson.setEndDate(new Date(System.currentTimeMillis()));

    if (lessonDao.insert(lesson) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void listLesson() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(lessonDao.findAll());
  }

  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
