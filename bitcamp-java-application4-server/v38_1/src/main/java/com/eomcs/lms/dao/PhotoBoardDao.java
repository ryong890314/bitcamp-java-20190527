package com.eomcs.lms.dao;

import java.util.List;
import com.eomcs.lms.domain.PhotoBoard;

// DAO 사용 규칙을 정의한다.
public interface PhotoBoardDao {

  int insert(PhotoBoard Photoboard) throws Exception;
  List<PhotoBoard> findAll() throws Exception;
  PhotoBoard findBy(int no) throws Exception;
  int update(PhotoBoard board) throws Exception;
  int delete(int no) throws Exception;

}
