public class Lab2 {
  class A {
    public A() {
        System.out.println("Constructorul ClasaDeBaza a fost apelata.");
    }

    public A(int x) {
        this();
        System.out.println("Constructorul ClasaDeBaza cu un parametru a fost apelata.");
    }

    public A(int x, int y) {
        this(x);
        System.out.println("Constructorul ClasaDeBaza cu doi parametri a fost apelata.");
    }

    public void metoda1() {
        System.out.println("Metoda 1 din ClasaDeBaza a fost apelata.");
    }

    public void metoda2() {
        System.out.println("Metoda 2 din ClasaDeBaza a fost apelata.");
    }
}

 class B extends A {
    public B() {
        super();
        System.out.println("Constructorul ClasaDerivata a fost apelata.");
    }

    public B(int x) {
        super(x);
        System.out.println("Constructorul ClasaDerivata cu un parametru a fost apelata.");
    }

    public void metoda3() {
        System.out.println("Metoda 1 din ClasaDerivata a fost apelata.");
    }

    public void metoda4(int x) {
        System.out.println("Metoda 2 din ClasaDerivata cu un parametru a fost apelata.");
    }

    public void metoda5() {
        System.out.println("Metoda 3 din ClasaDerivata a fost apelata.");
    }
}


    public static void main(String[] args) {
      Lab2 l = new Lab2();
        A obj1 = l.new A();
        B obj2 = l.new B();
        B obj3 = l.new B(10);
        A obj4 = l.new B();
        A obj5 = l.new A(10,20);

        obj1.metoda1();
        obj1.metoda2();

        obj2.metoda1();
        obj2.metoda2();
        obj2.metoda3();
        obj2.metoda4(10);
        obj2.metoda5();

        obj3.metoda1();
        obj3.metoda2();
        obj3.metoda3();
        obj3.metoda4(10);
        obj3.metoda5();

        obj4.metoda1();
        obj4.metoda2();

        obj5.metoda1();
        obj5.metoda2();
    }
}
