/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack.cs45;

import java.util.Scanner;

/**
 *
 * @author محمد
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(final String[] args) {
        // TODO code application logic here
        /**
         *
         * @author محمد
         */
        int x = 0;
        /**
         *
         * @author محمد
         */
        final int y1 = 1;
        /**
         *
         * @author محمد
         */
        final int y2 = 2;
        /**
         *
         * @author محمد
         */
        final int y3 = 3;
        /**
         *
         * @author محمد
         */
        final int y4 = 4;
        /**
         *
         * @author محمد
         */
        final int y5 = 5;
        /**
         *
         * @author محمد
         */
        final int y6 = 6;
        /**
         *
         * @author محمد
         */
        boolean check = true;
        /**
         *
         * @author محمد
         */
        Stack st = new Stack();

        /**
         *
         * @author محمد
         */
        Scanner scan = new Scanner(System.in);
        while (check) {
            System.out.println("choose the number of action");
            System.out.println("1) Push");
            System.out.println("2) Pop");
            System.out.println("3) Peek");
            System.out.println("4) Get size");
            System.out.println("5) Chek if empty");
            System.out.println("6)Exit");
            System.out.println("enter number of action");
            x = scan.nextInt();
            if (x == y1) {
                System.out.println("enter what you want to push");
                st.push(scan.next());
            } else if (x == y2) {
                System.out.println(st.pop());
            } else if (x == y3) {

                System.out.println("top = " + st.peek());
            } else if (x == y4) {

                System.out.println("size = " + st.size());
            } else if (x == y5) {

                System.out.println(st.isEmpty());
            } else if (x == y6) {
                check = false;
            } else {
                System.out.println("Wrong input");
                continue;
            }
        }

    }

}
