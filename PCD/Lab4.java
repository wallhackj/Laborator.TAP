package PCD;

import java.util.*;

import static java.lang.Thread.currentThread;
import static java.lang.Thread.sleep;

public class Lab4 {
    public static void main(String[] args) {
        final int X = 2;
        final int Y = 5;
        final int Z = 3;
        final int D = 12;

        Depo depo = new Depo(D);
        Productorul[] productorul = new Productorul[X];
        for (int i = 0; i < X; i++) {
            productorul[i] = new Productorul(depo, i + 1, Z * Y / X);
            productorul[i].run();
        }

        Consumer[] consumer = new Consumer[Y];
        for (int i = 0; i < Y; i++) {
            consumer[i] = new Consumer(depo, i + 1, Z);
            consumer[i].run();
        }

    }

    static class Depo {
        private final int capacity;
        private final Queue<Integer> items = new LinkedList<>();

        public Depo(int capacity) {
            this.capacity = capacity;
        }

        public synchronized Optional<Integer> get() throws InterruptedException {
            while (items.isEmpty()) {
                System.out.println("Depozit gol");
                wait();
            }

            System.out.println("Obj scos");
            notifyAll();
            return Optional.ofNullable(items.poll());
        }

        public synchronized void put(int item) throws InterruptedException {
            while (items.size() == capacity) {
                System.out.println("Depozitul este plin. Producătorul așteaptă...");
                wait();
            }
            items.add(item);
            System.out.println("Obiectul " + item + " a fost produs.");
            notifyAll();
        }
    }

    static class Productorul implements Runnable {
        @Override
        public void run() {
            Random random = new Random();
            try {
                for (int i = 0; i < number; i++) {
                    var item = random.nextInt(100);
                    depo.put(item);
                    System.out.println("Producătorul #" + id + " a produs obiectul: " + item);
                    sleep(random.nextInt(100));
                }
            } catch (InterruptedException e) {
                currentThread().interrupt();
            }
        }

        private final Depo depo;
        private final int id;
        private final int number;

        public Productorul(Depo dep, int id, int number) {
            this.depo = dep;
            this.id = id;
            this.number = number;
        }
    }

    static class Consumer implements Runnable {
        private final Depo dep;
        private final int id;
        private final int number;

        public Consumer(Depo dep, int id, int number) {
            this.dep = dep;
            this.id = id;
            this.number = number;
        }

        @Override
        public void run() {
            try {
                for (int i = 0; i < number; i++) {
                    var item = dep.get();
                    System.out.println("Consumer #" + id + " a produs obiectul: " + item);
                    sleep(new Random().nextInt(100));
                }
            } catch (Exception e) {
                currentThread().interrupt();
            }
        }
    }

}
