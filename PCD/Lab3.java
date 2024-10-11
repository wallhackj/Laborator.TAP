package PCD;

import java.util.ArrayList;

public class Lab3 {
    public static void main(String[] args) {
        var fir1 = new Thr1();
        var fir2 = new Thr2();
        var fir3 = new Thr3();
        var fir4 = new Thr4();

        var t1 = new Thread(fir1);
        var t2 = new Thread(fir2);
        var t3 = new Thread(fir3);
        var t4 = new Thread(fir4);

        t1.start();
        t2.start();
        t3.start();
        t4.start();

        try {
            t1.join();
            t2.join();
            t3.join();
            t4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
class Thr1 implements Runnable {
    @Override
    public void run() {
        var sum = 0;
        var count = 0;
        var arr = new ArrayList<Integer>();


    }
}

class Thr2 implements Runnable {
    @Override
    public void run() {

    }
}

class Thr3 implements Runnable {
    @Override
    public void run() {
        for (int i = 234; i < 987; i++) {
            System.out.println("Condiția 3: Parcurs de la începutul intervalului [234, 987], i = " + i);
        }
    }
}

class Thr4 implements Runnable {
    @Override
    public void run() {
        for (int i = 890; i >= 123; i--) {
            System.out.println("Condiția 4: Parcurs de la sfârșitul intervalului [123, 890], i = " + i);
        }
    }
}
