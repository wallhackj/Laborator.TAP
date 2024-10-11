package PCD;

import java.util.ArrayList;
import java.util.Random;

import static PCD.Lab3.mas;

public class Lab3 {
    static int[] mas = new int[100];

    public static void main(String[] args) {
        generate();

        var fir1 = new Thr1();
        var fir2 = new Thr2();
        var fir3 = new Thr3();
        var fir4 = new Thr4();

        var v1 = new Thread(fir1);
        var v2 = new Thread(fir2);
        var v3 = new Thread(fir3);
        var v4 = new Thread(fir4);

        v1.start();
        v2.start();
        v3.start();
        v4.start();

        try {
            v1.join();
            v2.join();
            v3.join();
            v4.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        student();

    }

    private static void student() {
        for (char c : "Haideu Victor".toCharArray()) {
            System.out.print(c);

            try {
                Thread.sleep(100);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private static void generate() {
        var random = new Random();
        for (int i = 0; i < 100; i++) {
            mas[i] = random.nextInt(1000);
        }
    }
}

class Thr1 implements Runnable {
    @Override
    public void run() {
        var sum = 0;
        var count = 0;
        var arr = new ArrayList<Integer>();

        for (int i = 0; i < Lab1.mas.length; i++) {
            if (Lab1.mas[i] % 2 == 0 && Lab1.mas[i] > 15 && Lab1.mas[i] < 115) {
                sum += i;
                count++;

                if (count == 2) {
                    arr.add(sum);
                    sum = 0;
                    count = 0;
                    var sumpos = arr.stream().mapToInt(x -> x).sum();

                    System.out.println("Condiție 1:" + "fir:" + i + ", Suma pozițiilor pare (de la început) este: " + sumpos);
                }
            }
        }
    }
}

class Thr2 implements Runnable {
    @Override
    public void run() {
        var sum = 0;
        var count = 0;
        var arr = new ArrayList<Integer>();

        for (int i = Lab1.mas.length - 1; i > 0; i--) {
            if (Lab1.mas[i] % 2 == 0 && Lab1.mas[i] > 6 && Lab1.mas[i] < 106) {
                sum += i;
                count++;

                if (count == 2) {
                    arr.add(sum);
                    sum = 0;
                    count = 0;
                    var sumpos = arr.stream()
                            .mapToInt(x -> x)
                            .sum();

                    System.out.println("Condiție 2:" + "fir:" + i + ", Suma pozițiilor pare (de la end) este: " + sumpos);

                }
            }
        }
    }
}

class Thr3 implements Runnable {
    @Override
    public void run() {
        for (int i = 234; i < 987; i++) {
            var num = mas[i];
            System.out.println(num);
        }
    }
}

class Thr4 implements Runnable {
    @Override
    public void run() {
        for (int i = 890; i >= 123; i--) {
            var num = mas[i];
            System.out.println(num);
        }
    }
}
