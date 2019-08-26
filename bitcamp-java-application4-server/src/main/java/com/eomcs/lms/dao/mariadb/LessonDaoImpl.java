package com.eomcs.lms.dao.mariadb;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;

public class LessonDaoImpl implements LessonDao {

  SqlSessionFactory sqlSessionFactory;
  
  public LessonDaoImpl(SqlSessionFactory sqlSessionFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
    
  }
  
  @Override
  public int insert(Lesson lesson) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      int count = sqlSession.insert("LessonDao.insert", lesson);
      sqlSession.commit();
      return count;
    } catch (Exception e) {
      sqlSession.rollback();
      throw e;
    } finally {
      sqlSession.close();
    }
  }

  @Override
  public List<Lesson> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("LessonDao.findAll");
    }
  }

  @Override
  public Lesson findBy(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Lesson lesson = sqlSession.selectOne("LessonDao.findBy", no);

      return lesson;
    } catch (Exception e) {
      sqlSession.rollback();
      throw e;
    } finally {
      sqlSession.close();
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.update("LessonDao.update", lesson);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      
      return sqlSession.delete("LessonDao.delete", no);
    }
  }
}
