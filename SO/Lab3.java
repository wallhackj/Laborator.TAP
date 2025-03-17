package SO;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.function.Supplier;

public class Lab3 {
    private static final int NUMAR_FILOZOFI = 18;
    private static final Filosof[] filosofi = new Filosof[NUMAR_FILOZOFI];
    private static final Betisoare betisoare = Betisoare.getInstance(NUMAR_FILOZOFI);
    private static final Lock consoleLock = new ReentrantLock();

    public static void main(String[] args) {
        ExecutorService executor = Executors.newFixedThreadPool(NUMAR_FILOZOFI);

        // Inițializarea și pornirea filozofilor
        for (int i = 0; i < NUMAR_FILOZOFI; i++) {
            filosofi[i] = new Filosof(i, betisoare);
            executor.execute(filosofi[i]);
        }

         try {
             Thread.sleep(10000);
         } catch (InterruptedException e) {
             Thread.currentThread().interrupt();
         }
         executor.shutdownNow();

        executor.shutdown();
    }

    static class Betisoare {
        private static Betisoare instance;
        private final Semaphore[] betisoare;

        private Betisoare(int n) {
            if (n <= 0) {
                throw new IllegalArgumentException("Numărul de betisoare trebuie să fie pozitiv.");
            }
            betisoare = new Semaphore[n];
            for (int i = 0; i < n; i++) {
                betisoare[i] = new Semaphore(1); // Fiecare betisoare poate fi luată de un singur filozof
            }
        }


        public static synchronized Betisoare getInstance(int n) {
            if (instance == null) {
                instance = new Betisoare(n);
            }
            return instance;
        }

        /**
         * Ia betisoarele necesare pentru a mânca.
         * @param id ID-ul filozofului
         * @throws InterruptedException dacă este întrerupt
         */
        public void iaBetisoare(int id) throws InterruptedException {
            if (id % 2 == 0) {
                betisoare[id].acquire();
                betisoare[(id + 1) % NUMAR_FILOZOFI].acquire();
            } else {
                betisoare[(id + 1) % NUMAR_FILOZOFI].acquire();
                betisoare[id].acquire();
            }
        }

        /**
         * Lasă betisoarele după ce a mâncat.
         * @param id ID-ul filozofului
         */
        public void lasaBetisoare(int id) {
            betisoare[id].release();
            betisoare[(id + 1) % NUMAR_FILOZOFI].release();
        }
    }

    enum Actiune {
        GANDESTE("gândește"),
        MANANCA("mănâncă");

        private final String descriere;

        Actiune(String descriere) {
            this.descriere = descriere;
        }

        public String getDescriere() {
            return descriere;
        }
    }

    static class Filosof implements Runnable {
        private final int id;
        private final Betisoare betisoare;
        private volatile boolean continua = true;

        /**
         * Constructor pentru Filozof.
         * @param id ID-ul filozofului
         * @param betisoare Betisoarele disponibile
         */
        public Filosof(int id, Betisoare betisoare) {
            this.id = id;
            this.betisoare = betisoare;
        }

        public void opreste() {
            continua = false;
        }

        @Override
        public void run() {
            try {
                while (continua) {
                    actiune(Actiune.GANDESTE);
                    betisoare.iaBetisoare(id);
                    actiune(Actiune.MANANCA);
                    betisoare.lasaBetisoare(id);
                }
            } catch (InterruptedException e) {
                printMessage("Filozoful " + id + " a fost întrerupt.");
                Thread.currentThread().interrupt();
            }
        }

        /**
         * Execută o acțiune și afișează mesajul corespunzător.
         * @param actiune Acțiunea de efectuat
         * @throws InterruptedException dacă este întrerupt
         */
        private void actiune(Actiune actiune) throws InterruptedException {
            printMessage(() -> "Filozoful " + id + " " + actiune.getDescriere() + "...");
            Thread.sleep((long) (Math.random() * 1000));
        }

        /**
         * Afișează un mesaj în consolă, sincronizând accesul.
         * @param mesajSupplier Supplier pentru mesaj
         */
        private void printMessage(Supplier<String> mesajSupplier) {
            consoleLock.lock();
            try {
                System.out.println(mesajSupplier.get());
            } finally {
                consoleLock.unlock();
            }
        }

        /**
         * Afișează un mesaj în consolă, sincronizând accesul.
         * @param mesaj Mesajul de afișat
         */
        private void printMessage(String mesaj) {
            consoleLock.lock();
            try {
                System.out.println(mesaj);
            } finally {
                consoleLock.unlock();
            }
        }
    }
}
