package com.eomcs.lms.context;

import java.util.Map;

// 서버가 시작하거나 종료할때,
// 리스너에게 보고하기 위해 호출하는 메서드의 규칙
public interface ServletContextListener {

  // 서버가 시작될때 호출되는 메서드
  void contextInitialized(Map<String,Object> context);
  
  // 서버가 종료될때 호출되는 메서드
  void contextDestroyed(Map<String,Object> context);
  

}
