package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberUpdateCommand implements Command {

  private List<Member> list;

  public Input input;

  public MemberUpdateCommand(Input input, List<Member> list) {
    this.input = input;
    this.list = list;
  }

  @Override
  public void execute() {

    int no = input.getIntValue("번호?");

    Member member = null;

    for (int i = 0; i < list.size(); i++) {
      Member temp = list.get(i);
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

}
