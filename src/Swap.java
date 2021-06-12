/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author michal.ceresna
 */
public class Swap {

    static int[] a = {1,2,3,4};
    static int ans = 0;
    static int cnt = 0;
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        ans = Integer.MAX_VALUE;
        cnt = 0;
        dfs( 0);
   
        System.out.println("Swaps: "+cnt);

    }

    public static void writeA() {
        for (int i=0; i<a.length; i++) {
            System.out.print(a[i] + " ");
        }
        System.out.println();
    }
    
    public static boolean swap(int i) {
        int b;
        if (a[i] > a[i+1]) {
            b = a[i+1];
            a[i+1]=a[i];
            a[i]=b;
            
            writeA();
            
            return true;
        }
        return false;
    }
    
    private static void sw(int i) {
        int t = a[i];
        a[i] = a[i-1];
        a[i-1] = t;        
    }
    
    public static int dfs(int l) {
        int flag = 0;
        for (int i = 1; i < a.length; i++) {
            if (a[i] < a[i-1]) {
                sw(i);  
                dfs(l+1);
                sw(i);
                flag = 1;
            }             
        }
        
        if (flag == 0) {
            if (ans > l) {
                ans = l;
                cnt = 0;
            } 
            if (ans == l) {
                cnt++;
            }
        }
        
        return 0;
    }
    
}
