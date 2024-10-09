package PCD;

import java.util.Random;

public class Lab1 {
    static int[] mas = new int[100];

    public static void main(String[] args) {
        generate();

        var fir1 = new Th1();
        var fir2 = new Th2();

        var v1 = new Thread(fir1);
        var v2 = new Thread(fir2);

        v1.start();
        v2.start();

        try {
            v1.join();
            v2.join();
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
            mas[i] = random.nextInt(100);
        }
    }
}

class Th1 implements Runnable {
    @Override
    public void run() {
        var sum = 0;
        for (int i = 0; i < Lab1.mas.length; i += 2) {
            var sec = i + 1;
            sum += i + sec;
            System.out.println("Condiție 1:" + "i :" + i + " , i + 1:" + sec + ", Suma pozițiilor pare (de la început) este: " + sum);
        }
    }
}

class Th2 implements Runnable {
    @Override
    public void run() {
        var sum = 0;
        for (int i = Lab1.mas.length - 2; i >= 0; i -= 2) {
            var sec = i - 1;
            sum += i + sec;
            System.out.println("Condiție 2:" + "i :" + i + ", i + 1:" + sec + ", Suma pozițiilor pare (de la sfârșit) este: " + sum);
        }
    }
}
