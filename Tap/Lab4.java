package Tap;

import java.util.Arrays;

public class Lab4 {
    public interface ArrayInterface {
        void print();
    }

    class Array implements ArrayInterface{
        protected int[] int_arr;

        public Array() {
            System.out.println("Este nevoie sa introduceti un array pentru a lucra cu classul dat.");
        }

        public Array(int[] int_arr) {
            this.int_arr = int_arr;
        }

        @Override
        public void print() {
            System.out.println("Arrayul contine:");
            Arrays.stream(int_arr).forEach(System.out::println);
        }
    }

    class ComplexArray extends Array {
        public ComplexArray(int[] int_arr) {
            super(int_arr);
        }

        @Override
        public void print() {
            int n = int_arr.length;
            for (int i = 0; i < n - 1; i++) {
                for (int j = 0; j < n - i - 1; j++) {
                    if (int_arr[j] > int_arr[j + 1]) {
                        int temp = int_arr[j];
                        int_arr[j] = int_arr[j + 1];
                        int_arr[j + 1] = temp;
                    }
                }
            }
            System.out.println("ComplexArrayul contine:");
            Arrays.stream(int_arr).forEach(System.out::println);
        }
    }

    public static void main(String[] args) {
        int[] array = {1, 4, 3, 2};

        Lab4 l = new Lab4();
        ArrayInterface arrayInterface0 = l.new Array(array);
        ArrayInterface arrayInterface1 = l.new ComplexArray(array);

        arrayInterface0.print();
        arrayInterface1.print();
    }
}
