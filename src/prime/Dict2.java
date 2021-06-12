/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;


/**
 *
 * @author michal.ceresna
 */
public class Dict2 {
    

    private static String minus = "";
    private static String plus = "";
    private static String zmenene = "";
    private static String[] cisla1;
    private static String[] pismena1;
    private static String[] cisla2;
    private static String[] pismena2;

    public static void main(String[] args) throws IOException {
        String[] oldDictionary;
        String[] newDictionary;
        BufferedReader input = new BufferedReader(new InputStreamReader(System.in));
        String num = input.readLine();
        int pocet = Integer.parseInt(num);
        int pomoc = 0;
        while (pomoc < pocet) {
            String starySlovnik = input.readLine();
            String novySlovnik = input.readLine();
            if (starySlovnik.equals("{}")) {
                oldDictionary = new String[0];
            } else {
                oldDictionary = starySlovnik.substring(starySlovnik.indexOf("{") + 1, starySlovnik.indexOf("}")).split(",");
            }
            if (novySlovnik.equals("{}")) {
                newDictionary = new String[0];
            } else {
                newDictionary = novySlovnik.substring(novySlovnik.indexOf("{") + 1, novySlovnik.indexOf("}")).split(",");
            }
// 13. submit.... to je stastne cislo, Wrong answer. Naozaj? Posli mi este raz to zadanie. Je tam nejaky chytak. Nejaky riadok, medzera... Jeidny chytak co vidim je ten non negative Integer

//there are no restrictions on the lengths of keys or values, Ale... Nie je take velky format aby zobralo 123456789123456789123456789
//ved prave... Napisem im? Uz neviem co. Skus teda napisat. Odpoved bude isto stat za to :) Ako kazda ich odpoved :)

            Arrays.sort(oldDictionary);
            Arrays.sort(newDictionary);

            cisla1 = new String[oldDictionary.length];
            pismena1 = new String[oldDictionary.length];
            cisla2 = new String[newDictionary.length];
            pismena2 = new String[newDictionary.length];
            rozdel(oldDictionary, pismena1, cisla1);
            rozdel(newDictionary, pismena2, cisla2);
            plus();
            minus();
            zmenilo();
            if (plus.equals("") && minus.equals("") && zmenene.equals("")) {
                System.out.println("No changes");
            } else {
                if (!plus.equals("")) {
                    System.out.println(plus);
                }
                if (!minus.equals("")) {
                    System.out.println(minus);
                }
                if (!zmenene.equals("")) {
                    System.out.println(zmenene);
                }
                plus = "";
                minus = "";
                zmenene = "";

            }
            System.out.println();
            pomoc++;
        }

    }

    private static void plus() {
        int pocet = 1;
        for (String s : pismena2) {
            if (Arrays.binarySearch(pismena1, s) < 0) {
                if (pocet == 1) {
                    plus += "+" + s;
                } else {
                    plus += "," + s;
                }
                pocet++;
            }
          /*
            for (String s1 : pismena1) {
                if (s.equals(s1)) {
                    pocetPrvkov++;
                    break;
                }
            }
            if (pocetPrvkov != 1) {
                if (pocet == 1) {
                    plus += "+" + s;
                } else {
                    plus += "," + s;
                }
                pocet++;
            }
            pocetPrvkov = 0;*/
        }

    }


    private static void minus() {
        int pocet = 1;
        for (String s : pismena1) {
            if (Arrays.binarySearch(pismena2, s) < 0) {
                if (pocet == 1) {
                    minus += "-" + s;
                } else {
                    minus += "," + s;
                }
                pocet++;
            }

        }


    }


    private static void zmenilo() {
        int pocet = 1;
        for (int i = 0; i < pismena1.length; i++) {
            if (Arrays.binarySearch(pismena2, pismena1[i]) >= 0) {
                if (!cisla2[Arrays.binarySearch(pismena2, pismena1[i])].equals(cisla1[i])) {
                    if (pocet == 1) {
                        zmenene += "*" + pismena1[i];
                    } else {
                        zmenene += "," + pismena1[i];
                    }
                }
            }

        }




    }
    private static void rozdel(String[] pole, String[] poleNaVkladanieP, String[] poleCisiel) {
        int dvojb;
        for (int i = 0; i < pole.length; i++) {
            dvojb = pole[i].indexOf(":");
            poleNaVkladanieP[i] = pole[i].substring(0, dvojb);
            poleCisiel[i] = pole[i].substring(dvojb);
        }

    }

}
