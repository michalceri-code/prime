/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package prime;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author michal.ceresna
 */
public class Matrix {

    static String[] matrixName = {"A", "B", "C", "D", "E", "F"};
    static int[] matrixRows = {10, 10, 5, 10, 20, 5, 10};
    static int[] matrixCols = {10, 20, 10, 5, 5, 5};

    static HashMap<String, Integer> rows = new HashMap();
    static HashMap<String, Integer> cols = new HashMap();
    static HashMap<String, Integer> mult = new HashMap();

    // static String[] expr = {"A", "(AA)", "B", "(A(BC))", "(A((BC)D))", "((AA)(AA))", "((((DE)C)A)(F(((CF)C)B)))"};
    static String[] expr = {"A", "(AA)", "B", "(BB)", "(AB)(AB)", "((AB)C)", "(AB)", "(((DE)C)A)(F(((CF)C)B))", "(EF)", "(((AF)C)B)"};

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

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

         for (int i=0; i<expr.length; i++) {
        //int i = 4;
        work(i);
          }

    }

    static void work(int idx) {
        String ex = expr[idx];

        if (ex.indexOf('(') == -1) {
            //single            
            System.out.println(0);
        } else {
            int vysledok = evaluate2(ex, 0);
            if (vysledok == -1) {
                System.out.println("error");
            } else {
                System.out.println(vysledok);
            }
        }

    }

    /*
    static int multiplications = 0;
    static int rows = 0;
    static int colums = 0;
    static int poradie = 1;
    static int offset = 0;
     */
    static class Result {

        int mul = 0;
        int rows = 0;
        int cols = 0;
        int end = 0;
    }

    static ArrayList<String> vyrazy = new ArrayList();
    static ArrayList<Integer> levely = new ArrayList();

    static int offset = 0;

    static int level = 0;

    /*
    private static int updateHashMap(String expr) {
        int to1;
        String expr1;
        if (expr.charAt(1) == '(') {            
            to1 = findCorresponingBracket(expr) + 1;
            expr1 = expr.substring(1, to1);
        } else {
            to1 = 2;
            expr1 = expr.substring(1, to1);
        }
        
        System.out.println("vyraz1 = " + expr1);

        int rows1 = rows.get(expr1);
        int cols1 = cols.get(expr1);
        Integer mult1 = mult.get(expr1);


        if (expr.length() > to1) {
            int to2;
            if (expr.charAt(to1) == '(') {
                to2 = to1 + findCorresponingBracket(expr.substring(to1)) + 1;
            } else {
                to2 = to1 + 1;
            }

            String expr2 = expr.substring(to1, to2);
            System.out.println("vyraz2 = " + expr2);

            int rows2 = rows.get(expr2);
            int cols2 = cols.get(expr2);
            Integer mult2 = mult.get(expr2);

            if (cols1 != rows2) {
                return -1;
            }

            rows.put(expr, rows1);
            cols.put(expr, cols2);

            int mult_new = rows1 * cols1 * cols2;
            if (mult1 != null) {
                mult_new += mult1;
            }
            if (mult2 != null) {
                mult_new += mult2;
            }

            System.out.println("mult = " + mult_new);
            System.out.println("rows = " + rows1);
            System.out.println("cols = " + cols2);

            mult.put(expr, mult_new);
        }

        return 0;
    }
     */

 /*
    private static int updateHashMap(String expr) {
        int to1;
        String expr1;
        if (expr.charAt(1) == '(') {            
            to1 = findCorresponingBracket(expr) + 1;
            expr1 = expr.substring(1, to1);
        } else {
            to1 = 2;
            expr1 = expr.substring(1, to1);
        }
        
        System.out.println("vyraz1 = " + expr1);

        int rows1 = rows.get(expr1);
        int cols1 = cols.get(expr1);
        Integer mult1 = mult.get(expr1);


        if (expr.length() > to1) {
            int to2;
            if (expr.charAt(to1) == '(') {
                to2 = to1 + findCorresponingBracket(expr.substring(to1)) + 1;
            } else {
                to2 = to1 + 1;
            }

            String expr2 = expr.substring(to1, to2);
            System.out.println("vyraz2 = " + expr2);

            int rows2 = rows.get(expr2);
            int cols2 = cols.get(expr2);
            Integer mult2 = mult.get(expr2);

            if (cols1 != rows2) {
                return -1;
            }

            rows.put(expr, rows1);
            cols.put(expr, cols2);

            int mult_new = rows1 * cols1 * cols2;
            if (mult1 != null) {
                mult_new += mult1;
            }
            if (mult2 != null) {
                mult_new += mult2;
            }

            System.out.println("mult = " + mult_new);
            System.out.println("rows = " + rows1);
            System.out.println("cols = " + cols2);

            mult.put(expr, mult_new);
        }

        return 0;
    }
     */
    static int findCorresponingBracket(String ex, int start_index) {
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

    static int evaluate2(String ex, int level) {

        int pos = 0;
        int pos_end = 0;
        int ret;
        String expr1;
        String expr2 = null;

        if (ex.charAt(0) == '(') {
            pos_end = findCorresponingBracket(ex, 0);
            expr1 = ex.substring(pos, pos_end + 1);
            if (evaluate2(expr1.substring(1), level + 1) == -1) {
                return -1;
            }
        } else {
            //zakladna matica
            expr1 = "" + ex.charAt(0);
        }

        pos = pos_end + 1;
        if (pos < ex.length()) {
            if (ex.charAt(pos) == '(') {
                pos_end = findCorresponingBracket(ex, pos);
                expr2 = ex.substring(pos, pos_end + 1);
                if (evaluate2(expr2.substring(1), level + 1) == -1) {
                    return -1;
                }
            } else {
                //zakladna matica
                expr2 = "" + ex.charAt(pos);
            }
        }

        //System.out.println("Vyraz1 = " + expr1);
        //System.out.println("Vyraz2 = " + expr2);

        String new_expr = ex;
        if (level > 0) {
            new_expr = "(" + new_expr;
        }

        int mult_new;

        if (mult.containsKey(new_expr)) {
            //System.out.println("Pouzitie vyrazu: " + new_expr);
            mult_new = mult.get(new_expr);

        } else {
            int rows1 = rows.get(expr1);
            int cols1 = cols.get(expr1);
            Integer mult1 = mult.get(expr1);

            //System.out.println("Novy vyraz: " + new_expr);

            if (expr2 == null) {
                if (level > 0) {
                    rows.put(new_expr, rows1);
                    cols.put(new_expr, cols1);
                    mult.put(new_expr, mult1);
                }

                mult_new = mult1;

            } else {
                int rows2 = rows.get(expr2);
                int cols2 = cols.get(expr2);
                Integer mult2 = mult.get(expr2);

                if (cols1 != rows2) {
                    return -1;
                }



                mult_new = mult1 + mult2 + rows1 * cols1 * cols2;

                //System.out.println("mult = " + mult_new);
                //System.out.println("rows = " + rows1);
                //System.out.println("cols = " + cols2);

                if (level > 0) {
                    rows.put(new_expr, rows1);
                    cols.put(new_expr, cols2);
                    mult.put(new_expr, mult_new);
                }

            }
        }

        return mult_new;
    }

    static int evaluate3(String ex) {

        int pos = 0;
        int pos_new;

        while ((pos_new = findCorresponingBracket(ex, pos)) != -1) {
            String sub_expr = ex.substring(pos, pos_new + 1);

            evaluate2(sub_expr.substring(1), 0);

            System.out.println(sub_expr);
            pos = pos_new + 1;
        }

        return 0;
    }

    /*
    static int evaluate1(String ex) {

        Stack stack_start = new Stack();

        for (int i = 0; i < ex.length(); i++) {
            if (ex.charAt(i) == '(') {
                level++;
                stack_start.push(i);
            }
            if (ex.charAt(i) == ')') {
                Integer start_ofs = (Integer) stack_start.pop();
                String expr = ex.substring(start_ofs, i + 1);
                System.out.println("" + level + ": " + expr);

                updateHashMap(expr);

                level--;                
            }
        }

        //updateHashMap(ex);

        Integer m = mult.get(ex);

        if (m == null) {
            return -1;
        }

        return m;
    }
     */
    static Result evaluate(String ex) {
        String op1, op2 = "";
        Result r = new Result();

        System.out.println(ex);

        if ((ex.charAt(1) != '(' && ex.charAt(2) != '(')) {
            //simple;
            System.out.println(ex.charAt(1) + "*" + ex.charAt(2));
            r.end = ex.indexOf(')') + 1;

            int rows1 = -1;
            int rows2 = -1;
            int cols1 = -1;
            int cols2 = -1;

            rows1 = rows.get("" + ex.charAt(1));
            cols1 = cols.get("" + ex.charAt(1));

            rows2 = rows.get("" + ex.charAt(2));
            cols2 = cols.get("" + ex.charAt(2));

            if (cols1 != rows2) {
                r.mul = -1;
                return r;
            }

            r.cols = cols2;
            r.rows = rows1;
            r.mul = rows1 * (cols1 * cols2);

            return r;
        }

        Result r1 = null, r2 = null;

        if (ex.charAt(1) == '(') {

            r1 = evaluate(ex.substring(1));
            op1 = ex.substring(1, 1 + r1.end);
            r.end = 1 + r1.end;

            if (ex.charAt(r.end) == '(') {

                r2 = evaluate(ex.substring(r.end));
                op2 = ex.substring(r.end, r.end + r2.end);
                r.end = r.end + r2.end;

            } else {
                op2 = "" + ex.charAt(r.end);
                r.end = r.end + 2;
            }

        } else {
            op1 = "" + ex.charAt(1);

            if (ex.charAt(2) == '(') {

                r2 = evaluate(ex.substring(2));
                op2 = ex.substring(2, 2 + r2.end);
                r.end = 2 + r2.end;
            }

        }

        System.out.println(op1 + "*" + op2);

        if (r1 != null && r2 != null) {
            if (r1.cols != r2.rows) {
                r.mul = -1;
                return r;
            }

            r.cols = r2.cols;
            r.rows = r1.rows;
            r.mul = r1.mul + r2.mul + r1.rows * (r1.cols * r2.cols);
        } else if (r1 == null) {
            int rows1 = -1;
            int cols1 = -1;

            rows1 = rows.get(op1);
            cols1 = cols.get(op1);

            if (rows1 != r2.rows) {
                r.mul = -1;
                return r;
            }

            r.cols = r2.cols;
            r.rows = rows1;
            r.mul = r2.mul + rows1 * (cols1 * r2.cols);

        } else if (r2 == null) {
            int rows2 = -1;
            int cols2 = -1;

            rows2 = rows.get(op2);
            cols2 = cols.get(op2);

            if (r1.cols != rows2) {
                r.mul = -1;
                return r;
            }

            r.cols = cols2;
            r.rows = r1.rows;
            r.mul = r1.mul + r1.rows * (r1.cols * cols2);
        }

        return r;
    }

}
