package com.eomcs.lms.handler;

import com.eomcs.lms.dao.BoardDao;
import com.eomcs.lms.domain.Board;
import com.eomcs.util.Input;

public class BoardDetailCommand implements Command {

  private BoardDao boardDao;
  public Input input;

  // Boardandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자!
  // => Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를
  // "의존 객체(dependency)"라 부른다.
  // => 의존 객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  // 다음과 같이 의존 객체를 넘겨 받는 생성자를 정의하는 것이다.

  public BoardDetailCommand(Input input, BoardDao boardDao) {
    this.input = input;
    this.boardDao = boardDao;

  }

  @Override
  public void execute() {

    int no = input.getIntValue("번호? ");

    try {
      
      boardDao.findBy(no);

//      if (board == null) {
//        System.out.println("해당 게시글을 찾을 수 없습니다.");
//        return;
//
//      }
//
//      System.out.printf("내용:%s\n", board.getContents());
//      System.out.printf("작성일:%s\n", board.getCreatedDate());

    } catch (Exception e) {
      System.out.println("데이터 조회에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }
}
