/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package quickSort;

import java.awt.Point;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import linkedList.cs45_cs23.DLinkedList;
import mailapp.logicCode.Mail;
import mailapp.logicCode.UserContacts;
import stack.cs45.Stack;

/**
 *
 * @author HP
 */
public class Quicksortdl {

    public void quicksort(DLinkedList array, boolean stri, boolean datt, boolean num, boolean send, boolean reciev, boolean subj,boolean attatchments,boolean stared) throws ParseException {
        if (array.size() > 0) {
            int start = 0, end = array.size() - 1;
            Stack order = new Stack();
            Point range1 = new Point(start, end);
            order.push((Point) range1);
            while (!order.isEmpty()) {
                Point range = (Point) order.pop();
                int st = (int) range.x;
                int en = (int) range.y;
                int pivot = partition(array, st, en, stri, datt, num, send, reciev, subj,attatchments,stared);
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
    }

    public int partition(DLinkedList array, int st, int en, boolean stri, boolean datt, boolean num, boolean send, boolean reciev, boolean subj,boolean attatchments,boolean starred) throws ParseException {
        //int pivot = (int) array.get(en);
        int i = st - 1;
        if (stri && send) {
            Mail pivot1 = (Mail) array.get(en);
            String pivot = pivot1.sender;
            // i = st - 1;
            for (int j = st; j <= en - 1; j++) {
                //int x1 = (int) array.get(j);
                Mail x11 = (Mail) array.get(j);
                String x1 = x11.sender;
                //if (x1 <= pivot)
                if (x1.compareTo(pivot) <= 0) {
                    i++;
                    Mail x31 = (Mail) array.get(i);
                    //String x3 = (String) array.get(i);
                    array.set(j, (Mail) x31);
                    array.set(i, (Mail) x11);
                }
            }
            Mail x41 = (Mail) array.get(i + 1);
            //String x4 = (String) array.get(i + 1);
            array.set(en, (Mail) x41);
            array.set(i + 1, (Mail) pivot1);
            //return i + 1;
        } else if (stri && reciev) {
            Mail pivot1 = (Mail) array.get(en);
            String pivot = pivot1.rec;
            // i = st - 1;
            for (int j = st; j <= en - 1; j++) {
                //int x1 = (int) array.get(j);
                Mail x11 = (Mail) array.get(j);
                String x1 = x11.rec;
                //if (x1 <= pivot)
                if (x1.compareTo(pivot) <= 0) {
                    i++;
                    Mail x31 = (Mail) array.get(i);
                    //String x3 = (String) array.get(i);
                    array.set(j, (Mail) x31);
                    array.set(i, (Mail) x11);
                }
            }
            Mail x41 = (Mail) array.get(i + 1);
            //String x4 = (String) array.get(i + 1);
            array.set(en, (Mail) x41);
            array.set(i + 1, (Mail) pivot1);
            //return i + 1;
        }else if (stri && attatchments) {
            Mail pivot1 = (Mail) array.get(en);
            String pivot = pivot1.attachbools;
            // i = st - 1;
            for (int j = st; j <= en - 1; j++) {
                //int x1 = (int) array.get(j);
                Mail x11 = (Mail) array.get(j);
                String x1 = x11.attachbools;
                //if (x1 <= pivot)
                if (x1.compareTo(pivot) <= 0) {
                    i++;
                    Mail x31 = (Mail) array.get(i);
                    //String x3 = (String) array.get(i);
                    array.set(j, (Mail) x31);
                    array.set(i, (Mail) x11);
                }
            }
            Mail x41 = (Mail) array.get(i + 1);
            //String x4 = (String) array.get(i + 1);
            array.set(en, (Mail) x41);
            array.set(i + 1, (Mail) pivot1);
            //return i + 1;
        } else if (stri && subj) {
            Mail pivot1 = (Mail) array.get(en);
            String pivot = pivot1.subject;
            // i = st - 1;
            for (int j = st; j <= en - 1; j++) {
                //int x1 = (int) array.get(j);
                Mail x11 = (Mail) array.get(j);
                String x1 = x11.subject;
                //if (x1 <= pivot)
                if (x1.compareTo(pivot) <= 0) {
                    i++;
                    Mail x31 = (Mail) array.get(i);
                    //String x3 = (String) array.get(i);
                    array.set(j, (Mail) x31);
                    array.set(i, (Mail) x11);
                }
            }
            Mail x41 = (Mail) array.get(i + 1);
            //String x4 = (String) array.get(i + 1);
            array.set(en, (Mail) x41);
            array.set(i + 1, (Mail) pivot1);
            //return i + 1;
        } else if (datt) {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
            Mail pivot2 = (Mail) array.get(en);
            String pivot = pivot2.msgDate;
            Date pivot1 = sdf.parse(pivot);
            //i = st - 1;
            for (int j = st; j <= en - 1; j++) {
                //int x1 = (int) array.get(j);
                Mail x111 = (Mail) array.get(j);
                String x1 = x111.msgDate;
                Date x11 = sdf.parse(x1);
                //if (x1 <= pivot)
                if (x11.compareTo(pivot1) <= 0) {
                    i++;
                    Mail x31 = (Mail) array.get(i);
                    // String x3 = (String) array.get(i);
                    //Date x31=sdf.parse(x3);
                    array.set(j, (Mail) x31);
                    array.set(i, (Mail) x111);
                }
            }
            Mail x41 = (Mail) array.get(i + 1);
            //String x4 = (String) array.get(i + 1);
            array.set(en, (Mail) x41);
            array.set(i + 1, (Mail) pivot2);
            //return i + 1; 
        } else if (num) {
            Mail pivot1 = (Mail) array.get(en);
            int pivot = pivot1.priority;
            // i = st - 1;
            for (int j = st; j <= en - 1; j++) {
                //int x1 = (int) array.get(j);
                Mail x11 = (Mail) array.get(j);
                int x1 = x11.priority;
                //if (x1 <= pivot)
                if (x1 <= pivot) {
                    i++;
                    Mail x31 = (Mail) array.get(i);
                    //int x3 =x31.priority ;
                    array.set(j, (Mail) x31);
                    array.set(i, (Mail) x11);
                }
            }
            Mail x41 = (Mail) array.get(i + 1);
            //int x4 = (int) array.get(i + 1);
            array.set(en, (Mail) x41);
            array.set(i + 1, (Mail) pivot1);
        }else if (starred) {
            Mail pivot1 = (Mail) array.get(en);
            int pivot = pivot1.starred;
            // i = st - 1;
            for (int j = st; j <= en - 1; j++) {
                //int x1 = (int) array.get(j);
                Mail x11 = (Mail) array.get(j);
                int x1 = x11.starred;
                //if (x1 <= pivot)
                if (x1 <= pivot) {
                    i++;
                    Mail x31 = (Mail) array.get(i);
                    //int x3 =x31.priority ;
                    array.set(j, (Mail) x31);
                    array.set(i, (Mail) x11);
                }
            }
            Mail x41 = (Mail) array.get(i + 1);
            //int x4 = (int) array.get(i + 1);
            array.set(en, (Mail) x41);
            array.set(i + 1, (Mail) pivot1);
        }
        //return i + 1;
        return i + 1;
    }
    public void quicksortContacts(DLinkedList array, boolean userName, boolean phoneNumber) throws ParseException {
        if (array.size() > 0) {
            int start = 0, end = array.size() - 1;
            Stack order = new Stack();
            Point range1 = new Point(start, end);
            order.push((Point) range1);
            while (!order.isEmpty()) {
                Point range = (Point) order.pop();
                int st = (int) range.x;
                int en = (int) range.y;
                int pivot = partitionContacts(array, st, en, userName, phoneNumber);
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
    }
    public int partitionContacts(DLinkedList array, int st, int en, boolean userName1, boolean phoneNumber1) throws ParseException {
        int i = st - 1;
        if (userName1) {
            UserContacts pivot1 = (UserContacts) array.get(en);
            String pivot = pivot1.userName;
            for (int j = st; j <= en - 1; j++) {
                UserContacts x11 = (UserContacts) array.get(j);
                String x1 = x11.userName;
                if (x1.compareTo(pivot) <= 0) {
                    i++;
                    UserContacts x31 = (UserContacts) array.get(i);
                    array.set(j, (UserContacts) x31);
                    array.set(i, (UserContacts) x11);
                }
            }
            UserContacts x41 = (UserContacts) array.get(i + 1);
            array.set(en, (UserContacts) x41);
            array.set(i + 1, (UserContacts) pivot1);
        } else if (phoneNumber1) {
            UserContacts pivot1 = (UserContacts) array.get(en);
            String pivot = pivot1.phoneNumber;
            for (int j = st; j <= en - 1; j++) {
                UserContacts x11 = (UserContacts) array.get(j);
                String x1 = x11.phoneNumber;
                if (x1.compareTo(pivot) <= 0) {
                    i++;
                    UserContacts x31 = (UserContacts) array.get(i);
                    array.set(j, (UserContacts) x31);
                    array.set(i, (UserContacts) x11);
                }
            }
            UserContacts x41 = (UserContacts) array.get(i + 1);
            array.set(en, (UserContacts) x41);
            array.set(i + 1, (UserContacts) pivot1);
        }
        return i + 1;
    }
}
