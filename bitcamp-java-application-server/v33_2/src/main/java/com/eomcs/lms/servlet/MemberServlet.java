package com.eomcs.lms.servlet;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.sql.Date;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.dao.serial.MemberSerialDao;
import com.eomcs.lms.domain.Member;

// 게시물 요청을 처리하는 담당자
public class MemberServlet implements Servlet {
  // 멤버 DAO를 교체하기 쉽도록 인터페이스의 레퍼런스로 선언한다.

  MemberDao memberDao;
  ObjectInputStream in;
  ObjectOutputStream out;

  public MemberServlet(MemberDao memberDao, ObjectInputStream in, ObjectOutputStream out)
      throws ClassNotFoundException {
    this.in = in;
    this.out = out;

    this.memberDao = memberDao;

    memberDao = new MemberSerialDao("./member.ser");
  }


  @Override
  public void service(String command) throws Exception {

    switch (command) {
      case "/member/add":
        addMember();
        break;
      case "/member/list":
        listMember();
        break;
      case "/member/delete":
        deleteMember();
        break;
      case "/member/detail":
        detailMember();
        break;
      case "/member/update":
        updateMember();
        break;
      case "quit":
        out.writeUTF("ok");
      default:
        out.writeUTF("fail");
        out.writeUTF("지원하지 않는 명령입니다.");
    }
  }



  private void updateMember() throws Exception {
    Member member = (Member) in.readObject();

    // 서버쪽에서 게시글 변경일을 설정해야 한다.
    // => 클라이언트에서 보내 온 날짜는 조작된 날짜일 수 있기 때문이다.
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    if (memberDao.update(member) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void detailMember() throws Exception {
    int no = in.readInt();

    Member member = memberDao.findBy(no);

    if (member == null) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
    out.writeObject(member);
  }

  private void deleteMember() throws Exception {
    int no = in.readInt();

    if (memberDao.delete(no) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void addMember() throws Exception {
    Member member = (Member) in.readObject();

    // 서버쪽에서 게시글 변경일을 설정해야 한다.
    // => 클라이언트에서 보내 온 날짜는 조작된 날짜일 수 있기 때문이다.
    member.setRegisteredDate(new Date(System.currentTimeMillis()));

    if (memberDao.insert(member) == 0) {
      fail("해당 번호의 회원이 없습니다.");
      return;
    }
    out.writeUTF("ok");
  }

  private void listMember() throws Exception {
    out.writeUTF("ok");
    out.reset(); // 기존에 serialize 했던 객체의 상태를 무시하고 다시 serialize 한다.
    out.writeObject(memberDao.findAll());
  }


  private void fail(String cause) throws Exception {
    out.writeUTF("fail");
    out.writeUTF(cause);
  }

}
