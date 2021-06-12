/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime;

import java.util.AbstractMap.SimpleEntry;
import java.util.ArrayList;
import java.util.Map.Entry;
import java.util.Scanner;

/**
 *
 * @author michal.ceresna
 */
public class Fibers {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
       
        int m[][] = {
            {0,4,9,21},
            {4,0,8,17},
            {9,8,0,16},
            {21,17,16,0}
        };
        
       /*
        int[][] m;
       
        Scanner scanner = new Scanner(System.in);
        int n = scanner.nextInt();
        
        m = new int[n][n];
       
        for (int i=0; i<n; i++) {            
            for (int j=0; j<n; j++) {
                m[i][j]=scanner.nextInt();
            }
        }
        */
        analyze(m);
        
    }


    static int findMinKeyVertex(int countOfVertices, int keys[], Boolean setOfMST[])   
    {   
        int minimum_index = -1;   
        int minimum_value = Integer.MAX_VALUE;  
          
        for (int vertex = 0; vertex < countOfVertices; vertex++)   
            if (setOfMST[vertex] == false && keys[vertex] < minimum_value) {   
                minimum_value = keys[vertex];   
                minimum_index = vertex;   
            }   
    
        return minimum_index;   
    }   
    
    static void analyze(int[][] m) {
        int start_node = 2; //lubovolny 
        int countOfVertices = m[0].length;
        
        //usporiadane dvojice mstArray[i] a keys[i]:        
        
        //hrana spajajuca vrcholy i a mstArray[i] ma minimalnu cenu, ulozenu v keys[i].
        //tato hrana je ulozena do vysledneho stromu
        int mstArray[] = new int[countOfVertices];   
        
        //minimalna cena pre vrchol i (do vrcholu i idem z vrcholu mstArray[i])
        int keys[] = new int[countOfVertices];   
        
        //priznak, ci bol vrchol spracovany
        Boolean setOfMST[] = new Boolean[countOfVertices];  

        //inicializacia: minimalna cena je nastavena na +INF
        for (int j = 0; j < countOfVertices; j++) {  
            //mstArray[j] = start_node;
            keys[j] = Integer.MAX_VALUE;   
            setOfMST[j] = false;   
        }           

        //pre pociatocny uzol: cena je 0.
        //kedze je uzol koren, nevedie don ziadna hrana, priradime specialnu hodnotu -1
        keys[start_node] = 0; 
        mstArray[start_node] = -1;   
        
        //opakujeme, kym nemame vsetky vrcholy v strome
        for (int i = 0; i < countOfVertices - 1; i++) {   
            
            //najdime hranu (index pociatocneho vrchola hrany) s najnizsou cenou, ktora este nie je v strome
            int edge = findMinKeyVertex(countOfVertices, keys, setOfMST);   
    
            //vrchol oznacme za spracovany
            setOfMST[edge] = true;   
    
            //aktualizujeme minimalnu cenu a hranu, ak hrana [edge <-> vertex] ma nizsiu cenu
            //ako je cena predchadzajucej najdenej hrany do vrchola vertex.
            for (int vertex = 0; vertex < countOfVertices; vertex++) {
                if (m[edge][vertex] != 0 && setOfMST[vertex] == false && m[edge][vertex] < keys[vertex]) {   
                    mstArray[vertex] = edge;   
                    keys[vertex] = m[edge][vertex];   
                }   
            }          
        }      

        //strom obsahuje vsetky vrcholy a ku kazdemu vrcholu okrem korena prave 1 hranu, 
        //ktorou sa do vrchola dostanem s minimalnou cenou. Sucet cien tychto hran je celkova
        //minimalna cena
        int min_cost = 0;
        for (int j = 0; j < countOfVertices; j++) {
            if (mstArray[j] >= 0) {
                min_cost += m[j][mstArray[j]];
            }        
        }
        System.out.println(min_cost);
        
    }
    
    
    
}
