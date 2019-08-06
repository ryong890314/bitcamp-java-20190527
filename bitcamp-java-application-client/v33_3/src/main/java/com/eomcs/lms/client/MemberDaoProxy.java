package com.eomcs.lms.client;

import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;

// Stateful 통신 방식을 stateless 통신 방식으로 변경한다.
// => 매번 요청할 때마다 서버와 연결한다.
// => 서버의 응답을 받으면 연결을 끊는다.

public class MemberDaoProxy implements MemberDao {

  String host;
  int port;

  public MemberDaoProxy(String host, int port) {
    this.host = host;
    this.port = port;
  }

  @Override
  public int insert(Member member) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/add");
      out.writeObject(member);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }

  @SuppressWarnings("unchecked")
  @Override
  public List<Member> findAll() throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/list");
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return (List<Member>) in.readObject();
    }
  }

  @Override
  public Member findBy(int no) throws Exception {
    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {

      out.writeUTF("/member/detail");
      out.writeInt(no);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return (Member) in.readObject();
    }
  }

  @Override
  public int update(Member member) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/update");
      out.writeObject(member);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }

  @Override
  public int delete(int no) throws Exception {

    try (Socket socket = new Socket(host, port);
        ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream())) {
      out.writeUTF("/member/delete");
      out.writeInt(no);
      out.flush();

      if (!in.readUTF().equals("ok"))
        throw new Exception(in.readUTF());

      return 1;
    }
  }

  public static void main(String[] args) throws Exception {
    MemberDaoProxy daoProxy = new MemberDaoProxy("localhost", 8888);

    // // 입력 테스트
    // Member member = new Member();
    // member.setNo(100);
    // member.setContents("okokok");
    //
    // daoProxy.insert(member);
    // System.out.println("입력성공");

    // // 조회 테스트
    // Member member = daoProxy.findBy(100);
    // System.out.println(member);

    // // 목록 조회 테스트
    // List<Member> members = daoProxy.findAll();
    // for (Member member : members) {
    // System.out.println(member);
    // }

    // // 변경 테스트
    // Member member = new Member();
    // member.setNo(100);
    // member.setContents("변경완료");
    //
    // daoProxy.update(member);
    //
    // Member member2 = daoProxy.findBy(100);
    // System.out.println(member2);
    // 삭제 테스트
//    daoProxy.delete(100);
//    System.out.println("삭제 완료!");

  }
}
