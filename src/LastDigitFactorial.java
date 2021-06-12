/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author michal.ceresna
 */
public class LastDigitFactorial {

    private static int[] d_const = {1,1,2,6,4,2,2,4,2,8}; 
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        int n = 4;
        int result = 0;
        
        result = d(n);
        
        System.out.println(result - (result/10)*10);
        
    }
    
    public static int d(int n) {
        int result = 0;
        
        if (n <= d_const.length) {
            return d_const[n];
        }
        
        if ((n / 10) % 2 == 0) {
            result = 6 * d((int)Math.floor(n/5)) * d_const[ n - (n/10)*10 ];
        } else {
            result = 4 * d((int)Math.floor(n/5)) * d_const[ n - (n/10)*10 ];            
        }

        return result;
        
    }
    
}
