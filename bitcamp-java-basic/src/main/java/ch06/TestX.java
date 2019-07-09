package ch06;

public class TestX {

  public static void main(String[] args) {
    
    String name = System.getProperty("name");
    int kor = Integer.parseInt(System.getProperty("kor"));
    int eng = Integer.parseInt(System.getProperty("eng"));
    int math = Integer.parseInt(System.getProperty("math"));
    
    int sum = kor + eng + math;

    System.out.printf("이름 : %s\n", name);
    
    System.out.printf("국어 : %s\n", kor);
    System.out.printf("영어 : %s\n", eng);
    System.out.printf("수학 : %s\n", math);
    System.out.printf("합계 : %s\n", sum);
    
  }
}
