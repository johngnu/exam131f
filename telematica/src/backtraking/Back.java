package backtraking;

import java.util.LinkedList;
import java.util.Scanner;

/* INF-143
 * Helen Carolina Castillo Mamani
 * Par: "B" 1/2023
*/

public class Back {

    static int existe = 0;
    static LinkedList<Integer> v;

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("ingresar numero:");
        int n = sc.nextInt();
        v = new LinkedList<>();
        existe = 0;
        backtracking(v, n);
        if (existe == 0) {
            System.out.println("IMPOSIBLE");
        }
    }

    public static boolean check(LinkedList<Integer> v, int x) {
        if (v.isEmpty()) {
            return true;
        }

        if (v.contains(x)) {
            return false;
        }

        int ultimovalor = v.getLast() + x;
        double y = Math.sqrt(ultimovalor);
        int aux = (int) y;
        if (aux == y) {
            return true;
        } else {
            return false;
        }
    }

    public static void backtracking(LinkedList<Integer> v, int n) {
        if (existe == 0) {
            if (v.size() == n) {
                existe = 1;
                for (int it : v) {
                    System.out.print(it + " ");
                }
                System.out.print("\n");
            } else {
                for (int i = 1; i <= n; i++) {
                    if (check(v, i)) {
                        v.addLast(i);
                        backtracking(v, n);
                        v.removeLast();
                    }
                }
            }
        }
    }

}
