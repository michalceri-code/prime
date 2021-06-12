/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime;

/**
 *
 * @author michal.ceresna
 */
public class Gray {
    public static void main(String[] args) {
        int[] n = {1,1,2,3,4,20,30};
        int[] k = {0,1,3,7,5,478,1000000};        
                
        for (int i=0; i< k.length; i++) {        
            gray1(n[i], k[i]);
            gray2(n[i], k[i]);
            gray3(n[i], k[i]);            
            gray4(n[i], k[i]); 
        }
    }
    
    public static void gray1(int n, int k) {
        if (k >= (1<<n)) {
            System.out.println("Chyba! Cislo "+k+" sa neda zapisat pomocou "+n+" bitov");    
            return;
        }
        
        int g = k ^ (k>>1);
        System.out.println(g);          
    }
    
    public static void gray4(int n, int k) {
        int g = 0;
        int bit = 0;
        int ni = n;
        int ki = k;
        int pow2_half;
        int prev_bits = 0;
        
        if (k >= (1<<n)) {
            System.out.println("Chyba! Cislo "+k+" sa neda zapisat pomocou "+n+" bitov");    
            return;
        }        
        
        for (int i=0; i<n; i++) {
            bit = (n-i-1);
            pow2_half = (1 << (ni - 1 ));

            if (prev_bits == 0) {
                if (ki >= pow2_half) {   
                    g = g | (1 << bit); 
                    ki = ki - pow2_half;
                    prev_bits = prev_bits ^ 1;
                }
            } else {
                if (ki < pow2_half) {   
                    g = g | (1 << bit); 
                    prev_bits = prev_bits ^ 1;
                } else {
                    ki = ki - pow2_half;
                }               
            }
            
            ni = ni - 1;                                    
        }
        
        System.out.println(g);          
    }    
    
    public static void gray2(int n, int k) {
        int g = 0;
        int bit = 0;
        int k1 = k;
        int [] base = {0,1,1,0};
        
        if (k >= (1<<n)) {
            System.out.println("Chyba! Cislo "+k+" sa neda zapisat pomocou "+n+" bitov");    
            return;
        }
        
        for (int i=0; i<n; i++) {            
            bit = base[ ( k1 % 4 ) ]; 
            k1 = k1/2;
            g = g | (bit << i);            
        }
        
        System.out.println(g);          
    }      
    
    public static void gray3(int n, int k) {
        int g = 0;
        int bit = 0;
        int k1 = k;
        int [] base = {0,1,1,0};
        
        if (k >= Math.pow(2, n)) {
            System.out.println("Chyba! Cislo "+k+" sa neda zapisat pomocou "+n+" bitov");    
            return;
        }
        
        for (int i=0; i<n; i++) {            
            bit = base[ ( k1 % 4 ) ]; 
            k1 = k1/2;
            g = g + bit*(int)Math.pow(2,i);            
        }
        
        System.out.println(g);          
    }     
}
