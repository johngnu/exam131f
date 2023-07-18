package nel;

public class UglyN {

    public static boolean isUglyNumber(int num) {
        boolean x = true;
        while (num != 1) {
            if (num % 5 == 0) {
                num /= 5;
            } else if (num % 3 == 0) {
                num /= 3;
            } // To check if number is divisible by 2 or not
            else if (num % 2 == 0) {
                num /= 2;
            } else {
                x = false;
                break;
            }
        }
        return x;
    }

    public static int nthUglyNumber(int n) {
        int i = 1;
        int count = 1;
        while (n > count) {
            i++;
            if (isUglyNumber(i)) {
                count++;
            }
        }
        return i;
    }

    public static void main(String[] args) {
        int number = 1500;
        int no = nthUglyNumber(number);
        System.out.println("The 1500`th ugly number is " + no);
    }
}
