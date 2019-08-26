package com.eomcs.lms.dao.mariadb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import com.eomcs.lms.dao.MemberDao;
import com.eomcs.lms.domain.Member;
import com.eomcs.util.DataSource;

public class MemberDaoImpl implements MemberDao {

  SqlSessionFactory sqlSessionFactory;
  DataSource dataSource;
  
  public MemberDaoImpl(SqlSessionFactory sqlSessionFactory, DataSource conFactory) {
    this.sqlSessionFactory = sqlSessionFactory; 
    this.dataSource = conFactory;
  }
  
  @Override
  public int insert(Member member) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    
    try {
      int count = sqlSession.insert("MemberDao.insert", member);
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
  public List<Member> findAll() throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession()) {
      return sqlSession.selectList("MemberDao.findAll");
    }
  }

  @Override
  public Member findBy(int no) throws Exception {
    SqlSession sqlSession = sqlSessionFactory.openSession();
    try {
      Member member = sqlSession.selectOne("MemberDao.findBy", no);

      return member;
    } catch (Exception e) {
      sqlSession.rollback();
      throw e;

    } finally {
      sqlSession.close();
    }
  }
  
  @Override
  public List<Member> findByKeyword(String keyword) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select member_id,name,email,tel,cdt"
                + " from lms_member"
                + " where name like ?"
                + " or email like ?"
                + " or tel like ?"
                + " order by name asc")) {
      
      stmt.setString(1, "%" + keyword + "%");
      stmt.setString(2, "%" + keyword + "%");
      stmt.setString(3, "%" + keyword + "%");
      
      try (ResultSet rs = stmt.executeQuery()) {
        ArrayList<Member> list = new ArrayList<>();
        while (rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setTel(rs.getString("tel"));
          member.setRegisteredDate(rs.getDate("cdt"));
          
          list.add(member);
        }
        return list;
      }
    }
  }

  @Override
  public int update(Member member) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      return sqlSession.update("MemberDao.update", member);
    }
  }

  @Override
  public int delete(int no) throws Exception {
    try (SqlSession sqlSession = sqlSessionFactory.openSession(true)) {
      
      return sqlSession.delete("MemberDao.delete", no);
    }
  }
  
  @Override
  public Member findByEmailPassword(String email, String password) throws Exception {
    try (Connection con = dataSource.getConnection();
        PreparedStatement stmt = con.prepareStatement(
            "select * from lms_member where email=? and pwd=password(?)")) {
      
      stmt.setString(1, email);
      stmt.setString(2, password);
      
      try (ResultSet rs = stmt.executeQuery()) {
        if (rs.next()) {
          Member member = new Member();
          member.setNo(rs.getInt("member_id"));
          member.setName(rs.getString("name"));
          member.setEmail(rs.getString("email"));
          member.setRegisteredDate(rs.getDate("cdt"));
          member.setTel(rs.getString("tel"));
          member.setPhoto(rs.getString("photo"));
          
          return member;
          
        } else {
          return null;
        }
      }
    }
  }

}
