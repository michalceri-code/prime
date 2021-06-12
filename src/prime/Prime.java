/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

/**
 *
 * @author michal.ceresna
 */
public class Prime {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        
        
        
        
        long s = System.currentTimeMillis();

        System.out.println(getFactorial(0));        
        System.out.println(getFactorial(1));        
        System.out.println(getFactorial(2));        
        System.out.println(getFactorial(3));        
        System.out.println(getFactorial(4));
        System.out.println(getFactorial(101));
        System.out.println(getFactorial(123456));
        System.out.println(getFactorial(10000000));
        
        s = System.currentTimeMillis() - s;
        System.out.println("Time: "+s / 1000.0+" s");

    }


    private static int getFactorial(int nprimes) {
        int n = 0;
        int x = 1;
        while (n < nprimes) {
            n+=getNumPrimes2(++x);
        }
        if (n == nprimes) {
            return x;
        } 
        
        return -1;
    }
    
    private static int getFactorial2(int nprimes) {
        int n = 0;
        int x = 1;
        int max;
        int x0;
        
        while (n < nprimes) {
            
            x0 = ++x;

            while (x0 % 2 == 0) {
                n++;
                x0 /= 2;
            }

            max = (int)Math.sqrt(x0);
            for (int i=3; i <= max; i+= 2) {
                while (x0 % i == 0) {
                    n++;
                    x0/=i;
                    max = (int)Math.sqrt(x0);
                }   
            }

            if (x0>2) {
                n++;
            }           

        }
        
        if (n == nprimes) {
            return x;
        } 
        
        return -1;
    }    
    
    /*
    private static boolean isPrime(int number) {  
       if (number < 2) return false;
       //for (int i=2; i<=(number/2); i++) {
       for (int i=2; i<=Math.sqrt(number); i++) {
           steps++;
           if ( number % i == 0 ) {
               return false;
           }
       }
       return true;
    }

    static int steps = 0;
    
    private static boolean isPrime(int start, int number) {  
       if (number < start) return false;
       for (int i=start; i<=(number/start); i++) {
           //steps++;
           if ( number % i == 0 ) {
               return false;
           } else {
               return isPrime(i+1, number);
           }
       }
       return true;
    }    
    */
    
    private static int getNumPrimes2(int number) {
        int cnt = 0;
        int n = number;
        
        while (n % 2 == 0) {
            cnt++;
            n /= 2;
        }

        int n0 = n;
        int max = (int)Math.sqrt(n);
        for (int i=3; i <= max; i+= 2) {
            while (n % i == 0) {
                cnt++;
                n/=i;
                max = (int)Math.sqrt(n);
            }   
        }
        
        if (n>2) {
            cnt++;
        }           

        return cnt;
    }
    
/*
    private static int getNumPrimes(int number) {         
       if (number == 2 || number == 3 || number == 5 || number == 7 || number == 11) {
           return 1;
       }      
       
       if (isPrime(2, number)) {
           return 1;
       }        
       
       int np;    
       int j;
       //for (int i=2; i<=(number/2); i++) {           
       for (int i=2; i<=Math.sqrt(number); i++) {  

           steps++;           
           if ( number % i == 0 ) {
               System.out.println(""+i+","+number/i);
               j = number/i;
               if (i==j) {
                   np = 2*getNumPrimes(i);
               } else {
                   np = getNumPrimes(i) + getNumPrimes(j);
               }
               return np;
            }
       }        
              
       return 0;
    }    
    */
    
    /*
    private static int getNumPrimes(int number) {  
       if (number == 2 || number == 3 || number == 5 || number == 7 || number == 11) {
           return 1;
       }      
       
       Integer o = mem_primes.get(number);
       
       if (o!=null) {
           return o.intValue();
       }
       
       if (isPrime(2, number)) {
           mem_primes.put(number, 1);
           return 1;
       }        
       
       int np;    
       
       
       
       for (int i=2; i<=(number/2); i++) {           
            if ( number % i == 0 ) {
               np = getNumPrimes(i) + getNumPrimes(number/i);
               mem_primes.put(number, np);
               return np;
            }
       }        
              
       return 0;
    }   */    
    
}
