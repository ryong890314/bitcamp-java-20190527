package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardUpdateCommand implements Command {

  private BoardDao boardDao;

  // Boardandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자!
  // => Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를
  // "의존 객체(dependency)"라 부른다.
  // => 의존 객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  // 다음과 같이 의존 객체를 넘겨 받는 생성자를 정의하는 것이다.

  public BoardUpdateCommand(BoardDao boardDao) {
    this.boardDao = boardDao;

  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {


    try {
      int no = Input.getIntValue(in, out, "번호?");
      
      Board board = boardDao.findBy(no);
      if (board == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }

      String str = Input.getStringValue(in, out, "내용? ");
      if (str.length() > 0) {
        board.setContents(str);
        boardDao.update(board);
        out.println("데이터를 변경하였습니다.");
      }else {
        out.println("데이터 변경을 취소합니다.");
      }


    } catch (Exception e) {
      out.println("데이터 변경에 실패했습니다!");
      System.out.println(e.getMessage());
    }

  }
}
