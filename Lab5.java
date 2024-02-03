import java.util.ArrayList;
import java.util.List;

public class Lab5 {

    class KevinEleven extends RuntimeException {
        public KevinEleven() {
            super("Nr. contine cifra 11.");
        }
    }
    public static void main(String[] args) {
        Lab5 l = new Lab5();

        //Test Case:
        // NullPointerException: int[] array = {};
        //ArrayIndexOutOfBoundsException: int[] array = {1,2,3,4,5,6,7,8,9};
        // KevinEleven: int[] array = {1,2,3,4,5,6,7,8,9,10,11};
        int[] array = {1,2,3,4,5,6,7,8,9,10};

        List<Integer> arr = new ArrayList<>();

        try {
            for (int i = 0; i < array.length; i += 2){
                if (array[i] == 11 || array[i + 1] == 11){
                    throw l.new KevinEleven();
                }
                arr.add(array[i] + array[i + 1]);
            }

            while (arr.size() > 1) {
                List<Integer> temp = new ArrayList<>();
                for (int i = 0; i < arr.size() - 1; i += 2) {
                    int sum = arr.get(i) + arr.get(i + 1);
                    temp.add(sum);
                }
                if (arr.size() % 2 != 0) {
                    temp.add(arr.get(arr.size() - 1));
                }
                arr = temp;
            }


        }catch (NullPointerException e){
            System.out.println("Arrayul nu trebuie sa fie gol!");
        }catch (ArrayIndexOutOfBoundsException e){
            System.out.println("Indexul matricei în afara limitelor!");
        }catch (KevinEleven e){
            System.out.println("A aparut o erroare !");
        }

        System.out.println("Nr. final este : " + arr);
    }
}
