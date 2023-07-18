package nel;

import java.util.Scanner;

public class VSets {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        long n = sc.nextLong();
        long lng = 0;
        long ant = 0;
        for (long i = 1; i <= n; i++) {
            long k = sc.nextInt();
            if ((k - ant) > lng) {
                lng = k - ant;
            }
            ant = k;
        }
        System.out.println(lng);
    }
}
