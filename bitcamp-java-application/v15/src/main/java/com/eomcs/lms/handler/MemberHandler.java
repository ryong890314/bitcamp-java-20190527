package com.eomcs.lms.handler;

import com.eomcs.lms.domain.Member;
import com.eomcs.lms.util.Input;

public class MemberHandler {
  private Member[] members = new Member[100];
  private int memberSize = 0;

  public Input input;

  public MemberHandler(Input input) {
    this.input = input;
  }

  public void listMember() {
    for (int i = 0; i < memberSize; i++) {
      Member member = members[i];
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

    members[memberSize++] = member;

    System.out.println("저장했습니다.");

  }

}
