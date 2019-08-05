package com.eomcs.lms.handler;

import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberUpdateCommand implements Command {

  private MemberDao memberDao;

  public Input input;

  public MemberUpdateCommand(Input input, MemberDao memberDao) {
    this.input = input;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    int no = input.getIntValue("번호? ");

    try {
      Member member = memberDao.findBy(no);
      if (member == null) {
        System.out.println("해당 학생을 찾을 수 없습니다.");
        return;
      }

      member.setName(input.getStringValue("이름(" + member.getName() + ")?"));
      member.setEmail(input.getStringValue("이메일(" + member.getEmail() + ")?"));
      member.setPassword(input.getStringValue("암호(" + member.getPassword() + ")?"));
      member.setPhoto(input.getStringValue("사진(" + member.getPhoto() + ")?"));
      member.setTel(input.getStringValue("전화(" + member.getTel() + ")?"));

      memberDao.update(member);
      System.out.println("회원을 변경했습니다.");

    } catch (Exception e) {
      System.out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
