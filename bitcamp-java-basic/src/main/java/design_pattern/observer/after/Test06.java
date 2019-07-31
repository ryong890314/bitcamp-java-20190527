package design_pattern.observer.after;

public class Test06 {

  public static void main(String[] args) {
    Car car = new Car();
    
    car.addObserver(new SafeBeltCarObserver());
    car.addObserver(new EngineOilCarObserver());
    car.addObserver(new BreakOilCarObserver());
    car.addObserver(new LightOffCarObserver());

    car.addObserver(new SunRoofCloseCarObserver());
    car.start();
    car.run();
    car.stop();
    System.out.println("------------------------------");
  }

}








