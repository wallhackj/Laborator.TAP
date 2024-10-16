package PCD;

import java.util.ArrayList;
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

    public static void generate() {
        var random = new Random();
        for (int i = 0; i < 100; i++) {
            mas[i] = random.nextInt(100);
        }
    }

    private static class Th1 implements Runnable {
        @Override
        public void run() {
            var sum = 0;
            var count = 0;
            var arr = new ArrayList<Integer>();

            for (int i = 0; i < Lab1.mas.length; i++) {
                if (Lab1.mas[i] % 2 == 0) {
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

    private static class Th2 implements Runnable {
        @Override
        public void run() {
            var sum = 0;
            var count = 0;
            var arr = new ArrayList<Integer>();

            for (int i = Lab1.mas.length - 1; i > 0; i--) {
                if (Lab1.mas[i] % 2 == 0) {
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
}

