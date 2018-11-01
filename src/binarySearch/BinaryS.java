/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarySearch;

import java.awt.Point;
import stack.cs45.Stack;

/**
 *
 * @author محمد
 */
public class BinaryS {

    Stack s = new Stack();

    public int search(int[] arr, int target) {
        int mid = 0;
        boolean check = true;
        int left = 0;
        int right = arr.length - 1;
        Point p = new Point(left, right);
        s.push((Point) p);
        if (target > arr[right] || target < arr[left]) {
            return -1;
        }
        if (target == arr[left]) {
            return left;
        }
        if (target == arr[right]) {
            return right;
        }
        while (left <= right) {
            Point p1 = (Point) s.peek();
            left = (int) p1.x;
            right = (int) p1.y;
            mid = left + (right - left) / 2;
            if (arr[mid] == target) {
                int indexleft = mid - 1;
                String indexes = "";
                indexes += String.valueOf(mid);
                indexes += " ";
                while (arr[indexleft] == target) {
                    indexes += String.valueOf(indexleft);
                    indexes += " ";
                    indexleft--;
                }
                int indexright = mid + 1;
                //String indexes = "";
               // indexes += String.valueOf(mid);
               // indexes += " ";
                while (arr[indexright] == target) {
                    indexes += String.valueOf(indexright);
                    indexes += " ";
                    indexright++;
                }
                System.out.println(indexes);
                return mid;
            } else if (arr[mid] < target) {
                left = mid + 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            } else if (arr[mid] > target) {
                right = mid - 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            }
        }
        return -1;
    }

}
