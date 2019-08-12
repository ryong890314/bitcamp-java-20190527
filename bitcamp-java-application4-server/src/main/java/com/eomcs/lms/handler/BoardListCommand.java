package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardListCommand implements Command {

  private BoardDao boardDao;

  // Boardandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자!
  // => Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를
  // "의존 객체(dependency)"라 부른다.
  // => 의존 객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  // 다음과 같이 의존 객체를 넘겨 받는 생성자를 정의하는 것이다.

  public BoardListCommand(Input input, BoardDao boardDao) {
    this.boardDao = boardDao;

  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      List<Board> boards = boardDao.findAll();
      for (Board board : boards) {
        out.printf("%s, %s, %s, %s\n", board.getNo(), board.getContents(),
            board.getCreatedDate(), board.getViewCount());
      }

    } catch (Exception e) {
      out.println("데이터 목록 조회에 실패했습니다!");
      out.println(e.getMessage());

    }
  }

}
