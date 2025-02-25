package SO;

import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Lab1 {
    public static void main(String[] args) {
        var timer1 = new Timer();
        var timer2 = new Timer();
        var timer3 = new Timer();

        timer1.schedule(new IntervalTimer(), 0 , 3000);
        var calendar = Calendar.getInstance();
        calendar.add(Calendar.SECOND, 10);
        timer2.schedule(new SpecificTimer(), calendar.getTime());
        timer3.schedule(new PeriodicTimer(), 2000 , 5000);
    }

    static class IntervalTimer extends TimerTask {
        @Override
        public void run() {
            System.out.println("IntervalTask: Se execută la fiecare 3 secunde. [" + new Date() + "]");
        }
    }

    static class SpecificTimer extends TimerTask {
        @Override
        public void run() {
            System.out.println("IntervalTask: Se execută o singură dată la ora setată. [" + new Date() + "]");
        }
    }

    static class PeriodicTimer extends TimerTask {
        @Override
        public void run() {
            System.out.println("PeriodicTask: Se execută la fiecare 5 secunde. [" + new Date() + "]");
        }
    }
}
