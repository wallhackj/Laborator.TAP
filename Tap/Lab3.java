package Tap;

import java.util.regex.Pattern;

public class Lab3 {
    class Carte {
        private String titlu;

        public Carte(String titlu) {
            this.titlu = titlu;
        }

        public void metoda1() {
            System.out.println("Metoda 1 din Clasa Carte a fost apelată");
        }

        public void metoda2(int x) {
            System.out.println("Metoda 2 din Clasa Carte cu un parametru a fost apelată" + x);
        }

        public boolean search(String pattern) {
            return Pattern.compile(pattern).matcher(titlu).find();
        }

        public String getTitlu() {
            return titlu;
        }
    }

    class Biblioteca extends Carte {
        public Biblioteca(String titlu) {
            super(titlu);
        }



        @Override
        public boolean search(String pattern) {
            return Pattern.compile(pattern, Pattern.CASE_INSENSITIVE).matcher(getTitlu()).find();
        }

        @Override
        public void metoda1() {
            System.out.println("Metoda 1 din Clasa Bibliotecă a fost apelată");
        }

        @Override
        public void metoda2(int x) {
            System.out.println("Metoda 2 din Clasa Bibliotecă cu un parametru a fost apelată" + x);
        }
    }

    public static void main(String[] args) {
        Lab3 l = new Lab3();

        Carte carte1 = l.new Carte("Harry Potter and the Philosopher's Stone");

        Carte biblioteca = l.new Biblioteca("Harry Potter and the Philosopher's Stone");

        boolean existaCuvant = carte1.search("stone");
        System.out.println(existaCuvant ? "Exista cuvântul 'Stone'" : "Nu există asemănări");

        boolean existaCuvant1 = biblioteca.search("stone");
        System.out.println(existaCuvant1 ? "Exista cuvântul 'Stone'" : "Nu există asemănări");
    }
}
