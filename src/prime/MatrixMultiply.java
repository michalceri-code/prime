/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime;

import java.util.HashMap;

public class MatrixMultiply {

    static HashMap<String, Integer> rows = new HashMap();
    static HashMap<String, Integer> cols = new HashMap();
    static HashMap<String, Integer> mult = new HashMap();

    static String[] expr = {"A", "(AA)", "B", "(BB)", "((AB)(AB))", "((AB)C)", "(AB)", "(((DE)C)A)(F(((CF)C)B))", "(EF)", "(((AF)C)B)"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        /* testovacie data */
        rows.put("A", 10);
        rows.put("B", 10);
        rows.put("C", 5);
        rows.put("D", 20);
        rows.put("E", 5);
        rows.put("F", 10);

        cols.put("A", 10);
        cols.put("B", 20);
        cols.put("C", 10);
        cols.put("D", 5);
        cols.put("E", 5);
        cols.put("F", 5);

        mult.put("A", 0);
        mult.put("B", 0);
        mult.put("C", 0);
        mult.put("D", 0);
        mult.put("E", 0);
        mult.put("F", 0);            


        //String test = "((AB)(CD))";
        //int start = 5;
        //int pos = findCorrespondingBracket(test, start);
        //System.out.println(test.substring(start, pos + 1));
                
        //long time = System.currentTimeMillis();

        for (int i=0; i<expr.length; i++) {
            work(i);            
        }
        
        //System.out.println("Time: " + (System.currentTimeMillis() - time)/1000.0 + "s" );

    }

    static void work(int idx) {
        String ex = expr[idx];

        if (ex.indexOf('(') == -1) {
            //len 1 matica        
            System.out.println(0);
        } else {
            int vysledok = evaluate(ex, 0);
            if (vysledok == -1) {
                System.out.println("error");
            } else {
                System.out.println(vysledok);
            }
        }

    }

    /**
     * Najde spravnu ukoncovaciu zatvorku
     * @param ex vyraz
     * @param start_index zaciatocna pozicia, od ktorej hladame
     * @return pozicia prislusnej ukoncovacej zatvorky
     */
    static int findCorrespondingBracket(String ex, int start_index) {
        int level = 0;
        int init = ex.indexOf('(', start_index);
        if (init == -1) {
            return -1;
        }
        for (int i = init; i < ex.length(); i++) {
            if (ex.charAt(i) == '(') {
                level++;
            }
            if (ex.charAt(i) == ')') {
                level--;
            }
            if (level == 0) {
                return i;
            }
        }
        return -1;
    }

    static int evaluate(String ex, int level) {

        int pos = 0;
        int pos_end = 0;
        String expr1;
        String expr2 = null;

        //prva cast vyrazu
        if (ex.charAt(0) == '(') {
            pos_end = findCorrespondingBracket(ex, 0);
            expr1 = ex.substring(pos, pos_end + 1);
            if (evaluate(expr1.substring(1), level + 1) == -1) {
                return -1;
            }
        } else {
            //zakladna matica, koniec rekurzie
            expr1 = "" + ex.charAt(0);
        }

        //druha cast vyrazu
        pos = pos_end + 1;
        if (pos < ex.length()) {
            if (ex.charAt(pos) == '(') {
                pos_end = findCorrespondingBracket(ex, pos);
                expr2 = ex.substring(pos, pos_end + 1);
                if (evaluate(expr2.substring(1), level + 1) == -1) {
                    return -1;
                }
            } else {
                //zakladna matica, koniec rekurzie
                expr2 = "" + ex.charAt(pos);
            }
        }

        //System.out.println("Vyraz1 = " + expr1);
        //System.out.println("Vyraz2 = " + expr2);

        String new_expr = ex;
        if (level > 0) {
            new_expr = "(" + new_expr;
        }
        
        if (level == 0 && expr2 != null) {
            //specialny pripad alebo BUG v zadani / sample outpute?
            //ak je vyraz neozatvorkovany => parser nasiel 2 samostatne vyrazy,
            //ktore nie su obalene "()" na leveli 0, potom
            //vysledky vyrazy sa uz medzi sebou nevynasobia, len sa scita
            //pocet nasobeni vo vyraze1 a vo vyraze2
            
            //to je testovaci pripad (((DE)C)A)(F(((CF)C)B)), kde vysledok
            //podla vzoru je 6000. Keby sa este vyrazy (((DE)C)A) a (F(((CF)C)B))
            //medzi sebou nasobili, pocet operacii by bol 10000.
            
            int mult1 = mult.get(expr1);
            int mult2 = mult.get(expr2);
            return mult1 + mult2;
        }

        int mult_new;

        if (mult.containsKey(new_expr)) {
            //System.out.println("Pouzitie vyrazu: " + new_expr);
            
            //zrychlenie - pouzijeme vyraz, ktory sme uz raz pocitali
            mult_new = mult.get(new_expr);

        } else {
            //int rows1 = rows.get(expr1);
            //int cols1 = cols.get(expr1);
            int mult1 = mult.get(expr1);

            //System.out.println("Novy vyraz: " + new_expr);

            if (expr2 == null) {
                //single vyraz - pocet nasobeni sa nemeni
                //mozem sa sem vobec dostat, kedze tento stav by mi mal
                //riesit mult.containsKey(new_expr)?
                
                int rows1 = rows.get(expr1);
                int cols1 = cols.get(expr1);
                
                if (level > 0) { 
                    rows.put(new_expr, rows1);
                    cols.put(new_expr, cols1);
                    mult.put(new_expr, mult1);
                }
                
                mult_new = mult1;

            } else {
                int rows2 = rows.get(expr2);
                int cols1 = cols.get(expr1);
                
                if (cols1 != rows2) {
                    return -1;
                }

                int cols2 = cols.get(expr2);
                int mult2 = mult.get(expr2);
                int rows1 = rows.get(expr1);
                                
                //pocet nasobeni z vyrazu1 + pocet nasobeni z vyrazu2 + pocet nasobeni medzi vyrazom1 a vyrazom2
                mult_new = mult1 + mult2 + rows1 * cols1 * cols2;

                //System.out.println("mult = " + mult_new);
                //System.out.println("rows = " + rows1);
                //System.out.println("cols = " + cols2);

                if (level > 0) {
                    //zapisme si vysledok vyrazu do mapy, pre level 0 uz zbytocne, kedze tam uz neposuvame vyssie
                    rows.put(new_expr, rows1);
                    cols.put(new_expr, cols2);
                    mult.put(new_expr, mult_new);
                }

            }
        }

        return mult_new;
    }


}
