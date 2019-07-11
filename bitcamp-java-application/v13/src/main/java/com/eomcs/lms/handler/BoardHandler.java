package com.eomcs.lms.handler;

import java.sql.Date;
import java.util.Scanner;
import com.eomcs.lms.domain.Board;
import com.eomcs.lms.util.Input;

public class BoardHandler {
  private Board[] boards = new Board[100];
  private int boardSize = 0;
  public static Scanner keyScan;

  public void listBoard() {

    for (int i = 0; i < boardSize; i++) {
      Board board = boards[i];
      System.out.printf("%s,%s,%s,%s\n", board.no, board.contents, board.createdDate,
          board.viewCount);

    }
  }

  public void addBoard() {
    Board board = new Board();

    board.no = Input.getIntValue("번호?");
    board.contents = Input.getStringValue("내용?");
    board.createdDate = new Date(System.currentTimeMillis());
    board.viewCount = Input.getIntValue("조회수?");

    boards[boardSize++] = board;

    System.out.println("저장했습니다.");
  }

}