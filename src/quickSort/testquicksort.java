/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickSort;

import java.text.ParseException;
import linkedList.cs45_cs23.DLinkedList;

/**
 *
 * @author HP
 */
public class testquicksort {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException {
        int[] need = {5, 4, 10, 3, 1, 54, 8, 7, 2, 20, 22, 6};
        Quicksort x = new Quicksort();
        x.quicksort(need);
        for (int i = 0; i < need.length; i++) {
            System.out.print(need[i] + " ");
        }
        System.out.println("\n");
        /*DLinkedList y=new DLinkedList();
         y.add(5);
         y.add(4);
         y.add(10);
         y.add(3);
         y.add(1);
         y.add(54);
         y.add(8);
         y.add(7);
         y.add(2);
         y.add(20);
         y.add(22);
         y.add(6);*/
        //DLinkedList d = new DLinkedList();
        DLinkedList d = new DLinkedList();
        d.add("aaa");
        d.add("aaa");
        d.add("aaa");
        d.add("aaa");
        d.add("aaa");
        d.add("aaa");
        d.add("aaa");
        d.add("aaa");
        //d.set(3, "aaa");
        Quicksortdl hop = new Quicksortdl();
     //   hop.quicksort(d, true, false, false);
        for (int i = 0; i < d.size(); i++) {
            System.out.println("d.add(\"" + d.get(i) + "\");");
        }
    }

}
