/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package macronutrients;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;
import java.util.TreeMap;

/**
 *
 * @author michal.ceresna
 */
public class Main {
    
    private static final int pUnit = 4;
    private static final int cUnit = 4;
    private static final int fUnit = 9;
    
    public static TreeMap<String, Integer> ingredients;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws Exception {
        BufferedReader br = new BufferedReader( new FileReader( new File( "input.txt" ) ) );        
        Scanner scanner = new Scanner(br);
        
        int nCases = scanner.nextInt();
        
        for (int i=0; i< nCases; i++) {        
            int nIngredients = scanner.nextInt();  
            int cMin, cMax;
            
            ingredients = new TreeMap();
            for (int j=0; j< nIngredients; j++) {
                String name = scanner.next();
                int value = scanner.nextInt() * pUnit + scanner.nextInt() * cUnit + scanner.nextInt() * fUnit;
                ingredients.put(name, value);
            }
            
            cMin = scanner.nextInt();
            cMax = scanner.nextInt();      
            
            processCase( nIngredients, cMin, cMax );
            if (solution_list.isEmpty()) {
                System.out.println("no solution found");
            } else {
                solution_list.forEach((s) -> {
                    System.out.println(s.replace("=", " ="));
                }); 
            }
            System.out.println();
        }
        
        scanner.close();
    }
    
    private static HashMap< String, Integer > solutions;          
    private static ArrayList<String> solution_list;                
    
    public static void processCase(int nIngredients, int cMin, int cMax) {

        solutions = new HashMap();
        solution_list = new ArrayList();
        
        solutions.put("", 0);
        
        for (String ingr_name : ingredients.keySet()) {
            HashMap<String, Integer> solutions_new = new HashMap();
            for (String s : solutions.keySet()) {
                int ingr_value = ingredients.get(ingr_name);
                int value = solutions.get(s) + ingr_value;
                int pocet = 1;
                while ( value <= cMax ) {
                    String s1 = ingr_name + " " + pocet + "x";
                    if (!s.isEmpty()) {
                        s1 = s + ", "+s1;
                    }
                    solutions_new.put(s1, value);
                    pocet++;
                    value+=ingr_value;
                } 
                
            }              
            solutions.putAll(solutions_new);
        }
        
        for (String s : solutions.keySet()) {
            int value = solutions.get(s);
            if (value >= cMin) {
                solution_list.add(s + "= "+value+" kcal");
            }
        }        
        Collections.sort(solution_list);                
    }
    
}
