package com.eomcs.lms;

import java.sql.Date;
import java.util.Scanner;

public class MemberHandler {
  static Member[] members = new Member[100];
  static int memberSize = 0;
  static Scanner keyScan;

  static void listMember() {
    for (int i = 0; i < memberSize; i++) {
      Member member = members[i];
      System.out.printf("%s,%s,%s,%s,%s,%s,%s\n", member.no, member.name, member.email,
          member.password, member.photo, member.tel, member.registeredDate);
    }
  }

  static void addMember() {

    Member member = new Member();

    member.no = Input.getIntValue("번호?");
    member.name = Input.getStringValue("이름?");
    member.email = Input.getStringValue("이메일?");
    member.password = Input.getStringValue("암호?");
    member.photo = Input.getStringValue("사진?");
    member.tel = Input.getStringValue("전화?");
    member.registeredDate = Input.getDateValue("가입일?");

    members[memberSize++] = member;

    System.out.println("저장했습니다.");

  }

}
