/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.Scanner;
import java.util.TreeMap;
import static macronutrients.Main.ingredients;
import static macronutrients.Main.processCase;

/**
 *
 * @author michal.ceresna
 */
public class Kod {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws FileNotFoundException {
        String m = "tepoedvrcothoanangnosirixaaakutymjz";
        //String m = "ttyohhieneesiaabss";
        int c = 5;
        //int c = 3;
        
        
        int r = m.length() / c;

        for (int j=0; j<c; j++) {
            for (int i=0; i<r; i++) {
                if (i % 2 == 0) {
                    System.out.print(m.charAt(i*c+j));                    
                } else {
                    System.out.print(m.charAt(i*c+ (c-j)-1));                    
                }
            }
        }
        
        System.out.println();
        
    }
    
}
