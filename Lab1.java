import java.util.*;

public class Lab1 {
    private String text;

    private Lab1(String text) {
        this.text = text;
    }

    private int search(){
        int count = 0;
        Set<Character> vocale = new HashSet<>(Arrays.asList('a', 'e', 'i', 'o', 'u'));
        String[] str = text.toLowerCase().split(" ");

        for (String s : str){
            char first = s.charAt(0);
            char last = s.charAt(s.length() - 1);

            if (vocale.contains(first) && vocale.contains(last)){
                count++;
            }
        }
        return count;
    }
    public static void main(String[] args) {
        Lab1 l = new Lab1("Eraodata can povesti");
        int nr = l.search();
        System.out.println("Nr. de cuvinte este : " + nr);

    }
}
