package com.eomcs.lms.handler;

import java.sql.Date;
import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardAddCommand implements Command {

  private BoardDao boardDao;
  private Input input;

  // Boardandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자!
  // => Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를
  // "의존 객체(dependency)"라 부른다.
  // => 의존 객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  // 다음과 같이 의존 객체를 넘겨 받는 생성자를 정의하는 것이다.

  public BoardAddCommand(Input input, BoardDao boardDao) {
    this.input = input;
    this.boardDao = boardDao;
  }

  @Override
  public void execute() {
    Board board = new Board();

    board.setContents(input.getStringValue("내용?"));

    try {
      boardDao.insert(board);
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("데이터를 저장에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }

}
