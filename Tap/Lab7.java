package Tap;

import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

public class Lab7 {

    public static void main(String[] args) {
       Set<Integer> geometricProgression = new LinkedHashSet<>(Arrays.asList(2, 4, 8, 16, 32));
        AtomicReference<Integer> previous = new AtomicReference<>(1);
//
//
//        int[] arr = geometricProgression.stream().mapToInt(current -> {
//            var result = current / previous.get();
//            previous.set(current);
//            return result;
//        }).toArray();
//
//        int[] array = Arrays.stream(arr).skip(1).toArray();
//
//        boolean result = Arrays.stream(array).allMatch(n -> n == array[0]);



        boolean result = geometricProgression.stream()
                .map(current -> {
                    int ratio = current / previous.get();
                    previous.set(current);
                    return ratio;
                })
                .skip(1)
                .allMatch(n -> n.equals(geometricProgression.iterator().next()));
       System.out.println("Result: " + result);
    }
}
