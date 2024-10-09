package PCD;

public class Lab2 {
    public static void main(String[] args) {
        ThreadGroup mainGroup = new ThreadGroup("Main");

        ThreadGroup groupGO = new ThreadGroup("GO");
        ThreadGroup groupGZ = new ThreadGroup("GZ");
        ThreadGroup groupGF = new ThreadGroup("GF");

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

        Thread tha = new Thread(groupGZ, createTask("Tha-GZ"), "Tha");
        tha.setPriority(1);

        Thread thb = new Thread(groupGZ, createTask("Thb-GZ"), "Thb");
        thb.setPriority(3);

        Thread thc = new Thread(groupGZ, createTask("Thc-GZ"), "Thc");
        thc.setPriority(3);

        Thread thd = new Thread(groupGZ, createTask("Thd-GZ"), "Thd");
        thd.setPriority(7);
    }

    private static Runnable createTask(String s) {
        return null;
    }

}
