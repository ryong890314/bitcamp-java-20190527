package com.eomcs.lms.handler;

import java.util.List;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.Input;

public class LessonListCommand implements Command {

  private LessonDao lessonDao;

  public LessonListCommand(Input input, LessonDao lessonDao) {
    this.lessonDao = lessonDao;
  }

  @Override
  public void execute() {
    try {
      List<Lesson> lessons = lessonDao.findAll();
      for (Object obj : lessons) {
        Lesson lesson = (Lesson) obj;
        System.out.printf("%s, %s, %s, %s ~ %s, %s, %s\n", lesson.getNo(), lesson.getTitle(),
            lesson.getContents(), lesson.getStartDate(), lesson.getEndDate(),
            lesson.getTotalHours(), lesson.getDayHours());
      }
    } catch (Exception e) {
      System.out.println("데이터 목록 조회에 실패했습니다!");
      System.out.println(e.getMessage());
    }
  }
}

