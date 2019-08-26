package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.LessonDao;
import com.eomcs.lms.domain.Lesson;
import com.eomcs.util.DataSource;

public class LessonDaoImpl implements LessonDao {

  SqlSessionFactory sqlSessionFactory;
  DataSource dataSource;
  
  public LessonDaoImpl(SqlSessionFactory sqlSessionFactory, DataSource conFactory) {
    this.sqlSessionFactory = sqlSessionFactory;
    this.dataSource = conFactory;
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
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<Lesson> list = new ArrayList<>();
      
      while (rs.next()) {
        Lesson lesson = new Lesson();
        lesson.setNo(rs.getInt("lesson_id"));
        lesson.setTitle(rs.getString("titl"));
        lesson.setStartDate(rs.getDate("sdt"));
        lesson.setEndDate(rs.getDate("edt"));
        lesson.setTotalHours(rs.getInt("tot_hr"));
        
        list.add(lesson);
      }
      return list;
    }
  }

  @Override
  public Lesson findBy(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select *"
                + " from lms_lesson"
                + " where lesson_id=?")) {
      
      stmt.setInt(1, no);
      
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Lesson lesson = new Lesson();
          lesson.setNo(rs.getInt("lesson_id"));
          lesson.setTitle(rs.getString("titl"));
          lesson.setContents(rs.getString("conts"));
          lesson.setStartDate(rs.getDate("sdt"));
          lesson.setEndDate(rs.getDate("edt"));
          lesson.setTotalHours(rs.getInt("tot_hr"));
          lesson.setDayHours(rs.getInt("day_hr"));
          
          return lesson;
        } else {
          return null;
        }
      }
    }
  }

  @Override
  public int update(Lesson lesson) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "update lms_lesson set"
                + " titl=?, conts=?, sdt=?, edt=?, tot_hr=?, day_hr=?"
                + " where lesson_id=?")) {

      stmt.setString(1, lesson.getTitle());
      stmt.setString(2, lesson.getContents());
      stmt.setDate(3, lesson.getStartDate());
      stmt.setDate(4, lesson.getEndDate());
      stmt.setInt(5, lesson.getTotalHours());
      stmt.setInt(6, lesson.getDayHours());
      stmt.setInt(7, lesson.getNo());
      
      return stmt.executeUpdate();
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "delete from lms_lesson where lesson_id=?")) {
      
      stmt.setInt(1, no);
      
      return stmt.executeUpdate();
    }
  }
}
