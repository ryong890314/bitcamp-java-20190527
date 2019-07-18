package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;
import com.eomcs.util.LinkedList;

public class MemberHandler {

  private LinkedList<Member> memberList = new LinkedList<>();

  public Input input;

  public MemberHandler(Input input) {
    this.input = input;
  }

  public void listMember() {

    Object[] list = memberList.toArray(new Member[] {});

    for (Object obj : list) {
      Member member = (Member) obj;
      System.out.printf("%s,%s,%s,%s,%s,%s,%s\n", member.getNo(), member.getName(),
          member.getEmail(), member.getPassword(), member.getPhoto(), member.getTel(),
          member.getRegisteredDate());
    }
  }

  public void addMember() {

    Member member = new Member();

    member.setNo(input.getIntValue("번호?"));
    member.setName(input.getStringValue("이름?"));
    member.setEmail(input.getStringValue("이메일?"));
    member.setPassword(input.getStringValue("암호?"));
    member.setPhoto(input.getStringValue("사진?"));
    member.setTel(input.getStringValue("전화?"));
    member.setRegisteredDate(input.getDateValue("가입일?"));

    memberList.add(member);;

    System.out.println("저장했습니다.");

  }

  public void detailMember() {
    int no = input.getIntValue("번호?");

    Member member = null;

    for (int i = 0; i < memberList.size(); i++) {
      Member temp = memberList.get(i);
      if (temp.getNo() == no) {
        member = temp;
        break;
      }
    }
    if (member == null) {
      System.out.println("해당 학생을 찾을 수 없습니다.");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("이메일: %s\n", member.getEmail());
    System.out.printf("암호: %s\n", member.getPassword());
    System.out.printf("사진: %s\n", member.getPhoto());
    System.out.printf("전화: %s\n", member.getTel());
    System.out.printf("가입일: %s\n", member.getRegisteredDate());

  }

  public void updateMember() {
    
    int no = input.getIntValue("번호?");

    Member member = null;

    for (int i = 0; i < memberList.size(); i++) {
      Member temp = memberList.get(i);
      if (temp.getNo() == no) {
        member = temp;
        break;
      }
    }
    if (member == null) {
      System.out.println("해당 학생을 찾을 수 없습니다.");
      return;
    }
    
    member.setName(input.getStringValue("이름(" + member.getName() + ")?"));
    member.setEmail(input.getStringValue("이메일(" + member.getEmail() + ")?"));
    member.setPassword(input.getStringValue("암호(" + member.getPassword() + ")?"));
    member.setPhoto(input.getStringValue("사진(" + member.getPhoto() + ")?"));
    member.setTel(input.getStringValue("전화(" + member.getTel() + ")?"));
    
    System.out.println("회원을 변경했습니다.");
    
  }

  public void deleteMember() {
    
    int no = input.getIntValue("번호?");
    
    for (int i = 0; i < memberList.size(); i++) {
      Member temp = memberList.get(i);
      if (temp.getNo() == no) {
        memberList.remove(i);
        System.out.println("회원을 삭제했습니다.");
        return;
      }
    }
    System.out.println("해당 회원을 찾을 수 없습니다.");
    return;
  }

}
