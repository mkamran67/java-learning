
public class Human {

  String name;
  int age;
  double weight;

  Human() {

  }

  Human(String name, int age, double weight) {
    this.name = name;
    this.age = age;
    this.weight = weight;
  }

  void printDeets() {
    System.out.println(this.name);
    System.out.println(this.age);
    System.out.println(this.weight);
  }
}
