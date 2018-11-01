/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickSort;

import java.awt.Point;
import stack.cs45.Stack;

/**
 *
 * @author HP
 */
public class Quicksort {

    public void quicksort(int[] array) {
        int start = 0, end = array.length-1;
        Stack order = new Stack();
        Point range1 = new Point(start, end);
        order.push((Point) range1);
        while (!order.isEmpty()) {
            Point range = (Point) order.pop();
            int st = (int) range.x;
            int en = (int) range.y;
            int pivot = partition(array, st, en);
            if (pivot + 1 < en) {
                Point x1 = new Point(pivot + 1, en);
                order.push((Point) x1);
            }
            if (st < pivot - 1) {
                Point x2 = new Point(st, pivot - 1);
                order.push((Point) x2);
            }

        }

    }

    public int partition(int[] array, int st, int en) {
        int pivot = array[en];
        int i = st - 1;
        for (int j = st; j <= en - 1; j++) {
            if (array[j] <= pivot) {
                i++;
                int temp = array[j];
                array[j] = array[i];
                array[i] = temp;
            }
        }
        int temp = array[en];
        array[en] = array[i + 1];
        array[i + 1] = temp;
        return i + 1;
    }
}
