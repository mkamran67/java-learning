
public class Car {

  String make = "toyota";
  String model = "prius";
  private String password = "";

  public void printDeets() {
    System.out.println(make + "\t" + model);
  }

  public void setPassword(String password) {

    this.password = password;

  }

  public String getPassword() {

    return this.password;

  }
}
