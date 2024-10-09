package PCD;

public class Lab2 {
    public static void main(String[] args) {
        ThreadGroup mainGroup = new ThreadGroup("Main");
        ThreadGroup groupGO = new ThreadGroup(mainGroup, "GO");
        ThreadGroup groupGZ = new ThreadGroup(groupGO, "GZ");
        ThreadGroup groupGF = new ThreadGroup(mainGroup, "GF");

        Thread tha = new Thread(groupGZ, createTask("Tha-GZ"), "Tha");
        tha.setPriority(1);

        Thread thb = new Thread(groupGZ, createTask("Thb-GZ"), "Thb");
        thb.setPriority(3);

        Thread thc = new Thread(groupGZ, createTask("Thc-GZ"), "Thc");
        thc.setPriority(3);

        Thread thd = new Thread(groupGZ, createTask("Thd-GZ"), "Thd");
        thd.setPriority(7);

        Thread thA = new Thread(groupGO, createTask("ThA"), "ThA");
        thA.setPriority(3);

        Thread th1Main = new Thread(mainGroup, createTask("Th1"), "Th1");
        th1Main.setPriority(3);

        Thread th2Main = new Thread(mainGroup, createTask("Th2"), "Th2");
        th2Main.setPriority(5);

        Thread th1GF = new Thread(groupGF, createTask("Th1-GF"), "Th1");
        th1GF.setPriority(5);

        Thread th2GF = new Thread(groupGF, createTask("Th2-GF"), "Th2");
        th2GF.setPriority(3);

        Thread th3GF = new Thread(groupGF, createTask("Th3-GF"), "Th3");
        th3GF.setPriority(9);

        thA.start();
        th1Main.start();
        th2Main.start();

        th1GF.start();
        th2GF.start();
        th3GF.start();

        tha.start();
        thb.start();
        thc.start();
        thd.start();

        System.out.println("All threads info:");
        printThreadsGroups(mainGroup);
    }

    private static void printThreadsGroups(ThreadGroup group) {
        var threads = new Thread[group.activeCount()];
        var groups = new ThreadGroup[group.activeGroupCount()];

        group.enumerate(threads, false);
        group.enumerate(groups, false);


        for (var t : threads) {
            if (t != null) {
                System.out.printf("Thread name: %s, Group: %s, Priority: %d\n",
                        t.getName(), t.getThreadGroup().getName(), t.getPriority());
            }
        }

        for (var g : groups) {
            if (g != null) {
                printThreadsGroups(g);
            }
        }

    }

    private static Runnable createTask(String s) {
        return () -> {
            for (int i = 0; i < 5; i++) {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        };
    }

}
