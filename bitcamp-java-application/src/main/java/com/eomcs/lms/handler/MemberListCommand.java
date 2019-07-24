package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.Input;

public class MemberListCommand implements Command{

  private List<Member> list;

  public Input input;

  public MemberListCommand(Input input, List<Member> list) {
    this.input = input;
    this.list = list;
  }
@Override
  public void execute() {

    Member[] members = list.toArray(new Member[] {});

    for (Object obj : members) {
      Member member = (Member) obj;
      System.out.printf("%s,%s,%s,%s,%s,%s,%s\n", member.getNo(), member.getName(),
          member.getEmail(), member.getPassword(), member.getPhoto(), member.getTel(),
          member.getRegisteredDate());
    }
  }

}
