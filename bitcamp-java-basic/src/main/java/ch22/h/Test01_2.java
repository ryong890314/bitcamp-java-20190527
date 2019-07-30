// 파일 입출력과 예외처리 - try-with-resources
package ch22.h;

import java.io.FileOutputStream;

public class Test01_2 {
  public static void main(String[] args) throws Exception {
    FileOutputStream out = null;

    try {
      out = new FileOutputStream("temp3/data.bin");
      out.write(0x55);

    } catch (Exception e) {
      System.out.println("파일 입출력 예외 발생!");
      e.printStackTrace();
    } finally {
      try {
        out.close();
      } catch (Exception e2) {
      }
    }
    System.out.println("출력 완료!");
  }
}


