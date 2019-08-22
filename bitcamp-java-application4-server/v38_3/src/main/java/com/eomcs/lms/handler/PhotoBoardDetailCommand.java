package com.eomcs.lms.handler;

import java.io.BufferedReader;
import java.io.PrintStream;
import java.util.List;
import com.eomcs.lms.dao.PhotoBoardDao;
import com.eomcs.lms.dao.PhotoFileDao;
import com.eomcs.lms.domain.PhotoBoard;
import com.eomcs.lms.domain.PhotoFile;
import com.eomcs.util.Input;

public class PhotoBoardDetailCommand implements Command {

  private PhotoBoardDao photoBoardDao;
  private PhotoFileDao photoFileDao;

  // PhotoBoardandler가 사용하는 Input 객체를 반드시 설정하도록 강제해보자!
  // => Input 객체처럼 어떤 작업을 실행하기 위해 반드시 있어야 하는 객체를
  // "의존 객체(dependency)"라 부른다.
  // => 의존 객체를 강제로 설정하게 만드는 방법? 생성자를 정의하는 것이다.
  // 다음과 같이 의존 객체를 넘겨 받는 생성자를 정의하는 것이다.

  public PhotoBoardDetailCommand(PhotoBoardDao photoBoardDao, PhotoFileDao photoFileDao) {
    this.photoBoardDao = photoBoardDao;
    this.photoFileDao = photoFileDao;

  }

  @Override
  public void execute(BufferedReader in, PrintStream out) {
    try {
      // 클라이언트에게 번호를 요구하여 받는다.
      int no = Input.getIntValue(in, out, "번호? ");
      
      PhotoBoard photoBoard = photoBoardDao.findBy(no);
      
      if (photoBoard == null) {
        out.println("해당 번호의 데이터가 없습니다!");
        return;
      }
      
      out.printf("내용: %s\n", photoBoard.getTitle());
      out.printf("작성일: %s\n", photoBoard.getCreatedDate());
      out.printf("조회수: %d\n", photoBoard.getViewCount());
      out.printf("수업: %d\n", photoBoard.getLessonNo());
      out.println("사진파일:");
      
      List<PhotoFile> files = photoFileDao.findAll(no);
      for (PhotoFile file : files) {
        out.printf("> %s\n",  file.getFilePath());
      }
      
    } catch (Exception e) {
      out.println("데이터 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }
}
