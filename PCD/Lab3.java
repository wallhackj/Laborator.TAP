package PCD;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import static PCD.Lab1.mas;
import static java.lang.Thread.sleep;

public class Lab3 {
    static int[] mass = new int[1000];

    public static void main(String[] args) {
        Lab1.generate();
        generate();

        System.out.println(Arrays.toString(mas));
        System.out.println(Arrays.toString(mass));

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

        fir2.printWithDelay("Nume: Haideu");
        fir4.printWithDelay("Grupa: RM-221");
        fir1.printWithDelay("Prenume: Victor");
        fir3.printWithDelay("Disciplina: Programare Concurentă");
    }

    private static void generate() {
        var random = new Random();
        for (int i = 0; i < 1000; i++) {
            mass[i] = random.nextInt(1000);
        }
    }

    private static class Thr1 extends Disp implements Runnable {
        @Override
        public void run() {
            var sum = 0;
            var count = 0;
            var arr = new ArrayList<Integer>();

            for (int i = 0; i < mas.length; i++) {
                if (mas[i] % 2 == 0 && mas[i] > 15 && mas[i] < 115) {
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

    private static class Thr2 extends Disp implements Runnable {
        @Override
        public void run() {
            var sum = 0;
            var count = 0;
            var arr = new ArrayList<Integer>();

            for (int i = mas.length - 1; i > 0; i--) {
                if (mas[i] % 2 == 0 && mas[i] > 6 && mas[i] < 106) {
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

    private static class Thr3 extends Disp implements Runnable {
        @Override
        public void run() {
            for (int i = 234; i < 987; i++) {
                var num = Lab3.mass[i];
                System.out.println("Condiție 3:" + "fir:" + i + ", " + num);
            }
        }
    }

    private static class Thr4 extends Disp implements Runnable {
        @Override
        public void run() {
            for (int i = 890; i >= 123; i--) {
                var num = Lab3.mass[i];
                System.out.println("Condiție 4:" + "fir:" + i + ", " + num);
            }
        }
    }

    abstract static class Disp implements Runnable {
        public void printWithDelay(String msg){
            for (char c : msg.toCharArray()) {
                System.out.print(c);

                try {
                    sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            System.out.println();
        }
    }
}

