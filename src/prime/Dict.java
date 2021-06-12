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
public class Dict {
    
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        long t0 = System.currentTimeMillis();
        
        for (int x = 0; x<10000; x++) {
        
        String oldd = "{a:1,b:2,c:2,d:4,e:5}";
        String newd = "{a:1,b:2,c:3,ee:4,f:11}";
        
        oldd = oldd.replace("{", "").replace("}","");
        newd = newd.replace("{", "").replace("}","");
        
        String[] oldw = oldd.split(",");
        String[] neww = newd.split(",");

        String[] names_old = new String[oldw.length];
        String[] values_old = new String[oldw.length];

        String[] names_new = new String[neww.length];
        String[] values_new = new String[neww.length];
        
        
        for (int i=0; i<oldw.length; i++) {
            String[] nv = oldw[i].split(":");
            names_old[i] = nv[0];
            values_old[i] = nv[1];
        }
        
        for (int i=0; i<neww.length; i++) {
            String[] nv = neww[i].split(":");
            names_new[i] = nv[0];
            values_new[i] = nv[1];
        }     
        
        String countPlus = "";
        boolean found;
        for (int i=0; i<names_new.length; i++) {
            found = false;
            for (int j=0; j<names_old.length; j++) {                
                if (names_old[j].equals(names_new[i])) {
                    found = true;
                    break;
                }
            }
            if (!found) {
                if (countPlus.length() > 0) {
                    countPlus = countPlus + ", ";                    
                }
                countPlus += names_new[i];
            }
        }     
        
        if (countPlus.length()>0) {
            System.out.println(countPlus);
        } else {
            
        }
             
    }
System.out.println(""+(System.currentTimeMillis() - t0)/1000.0);        
    }
    
}
