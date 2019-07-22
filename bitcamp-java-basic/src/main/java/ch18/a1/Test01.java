// 인터페이스 사용 전
package ch18.a1;

public class Test01 {

  public static void main(String[] args) {
    // 개개체의 기능을 사용해보자!
    // =>각 도구의 사용법이 다르기 때문에 각 도구에 맞는 메서드를 준배해야 한다.
    // => 그래서 각 도구에 맞는 use()메서드를 각각 따로 준비했다.
    
    // 1) ToolA 객체 사용하기
    use(new ToolA());
    
    // 2) ToolB 객체 사용하기    
    use(new ToolB());

  }
  
  static void use(ToolA tool) {
    // tool 레퍼런스가 가리키는 인스턴스에 대해 A 규칙에 정의된 메서드를 호출한다.
    tool.m1();
  }
  static void use(ToolB tool) {
    // tool 레퍼런스가 가리키는 인스턴스에 대해 A 규칙에 정의된 메서드를 호출한다.
    tool.m2();
  }

}







