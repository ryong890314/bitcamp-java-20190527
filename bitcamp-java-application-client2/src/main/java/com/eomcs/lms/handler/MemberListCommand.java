package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberListCommand implements Command {

  private MemberDao memberDao;

  public Input input;

  public MemberListCommand(Input input, MemberDao memberDao) {
    this.input = input;
    this.memberDao = memberDao;
  }

  @Override
  public void execute() {
    try {
      List<Member> members = memberDao.findAll();
      for (Object obj : members) {
        Member member = (Member) obj;
        System.out.printf("%s,%s,%s,%s,%s,%s,%s\n", member.getNo(), member.getName(),
            member.getEmail(), member.getPassword(), member.getPhoto(), member.getTel(),
            member.getRegisteredDate());
      }
    } catch (Exception e) {
      System.out.println("데이터 목록 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }

}
