package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.util.Input;

public class PhotoBoardAddCommand implements Command {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  // PhotoBoardandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자!
  // => Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를
  // "의존 객체(dependency)"라 부른다.
  // => 의존 객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  // 다음과 같이 의존 객체를 넘겨 받는 생성자를 정의하는 것이다.

  public PhotoBoardAddCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;
  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      PhotoBoard photoBoard = new PhotoBoard();
      photoBoard.setTitle(Input.getStringValue(in, out, "내용?"));
      photoBoard.setLessonNo(Input.getIntValue(in, out, "lessonNo?"));
      
      photoBoardDao.insert(photoBoard);
      System.out.println("저장하였습니다.");
    } catch (Exception e) {
      System.out.println("데이터를 저장에 실패했습니다.");
      System.out.println(e.getMessage());
    }
  }
}

