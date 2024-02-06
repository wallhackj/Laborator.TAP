import java.util.*;

public class Lab7 {

    public static void main(String[] args) {
        List<Integer> save = new ArrayList<>();
        Set<Integer> geometricProgression = new TreeSet<>(Arrays.asList(2, 6, 18, 54, 162));

        int progression = -1;

        for (Integer i : geometricProgression) {
            if (progression == -1) {
                progression = i;
            } else {
                try {
                    int ratio = i / progression;
                    if (ratio != 1) {
                        progression = -1;
                        save.add(ratio);
                    } else {
                        System.out.println("Nu este o progresie geometrica.");
                        return;
                    }
                } catch (ArithmeticException e) {
                    System.out.println("Exceptie aritmetica!");
                }
            }
        }


        if (save.stream().distinct().count() == 1) {
            System.out.println("Aceasta este o progresie geometrica.");
        } else {
            System.out.println("Aceasta nu este o progresie geometrica.");
        }
    }
}
