/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package binarySearch;

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
 * @author محمد
 */
public class BinarySearch {

    Stack s = new Stack();

    public DLinkedList search(DLinkedList f, int target) {
        if (f.size() != 0 && f != null) {
            DLinkedList found = new DLinkedList();
            int mid = 0;
            boolean check = true;
            int left = 0;
            int right = f.size() - 1;
            Point p = new Point(left, right);
            s.push((Point) p);
            if (target > ((Mail) f.get(right)).priority || target < ((Mail) f.get(left)).priority) {
                return new DLinkedList();
            }
            /* if (target == (int) f.get(left)) {
             return left;
             }
             if (target == (int) f.get(right)) {
             return right;
             }*/
            while (left <= right) {
                Point p1 = (Point) s.peek();
                left = (int) p1.x;
                right = (int) p1.y;
                mid = left + (right - left) / 2;
                if (((Mail) f.get(mid)).priority == target) {
                    int indexleft = mid - 1;
                    /* String indexes = "";
                     indexes += String.valueOf(mid);
                     indexes += " ";*/
                    found.add(f.get(mid));
                    if (indexleft >= 0) {
                        while (((Mail) f.get(indexleft)).priority == target) {
                            /* indexes += String.valueOf(indexleft);
                             indexes += " ";*/
                            found.add(f.get(indexleft));
                            indexleft--;
                            if (indexleft < 0) {
                                break;
                            }
                        }
                    }
                    int indexright = mid + 1;
                //String indexes = "";
                    // indexes += String.valueOf(mid);
                    // indexes += " ";
                    if (indexright < f.size()) {
                        while (((Mail) f.get(indexright)).priority == target) {
                            /*indexes += String.valueOf(indexright);
                             indexes += " ";*/
                            found.add(f.get(indexright));
                            indexright++;
                            if (indexright == f.size()) {
                                break;
                            }
                        }
                    }
                    // System.out.println(indexes);
                    return found;
                } else if (((Mail) f.get(mid)).priority < target) {
                    left = mid + 1;
                    p = new Point(left, right);
                    s.pop();
                    s.push((Point) p);
                } else if (((Mail) f.get(mid)).priority > target) {
                    right = mid - 1;
                    p = new Point(left, right);
                    s.pop();
                    s.push((Point) p);
                }
            }
        }
        return new DLinkedList();

    }

    public DLinkedList comparedate(DLinkedList f, String target) throws ParseException {
        DLinkedList found = new DLinkedList();
        int mid = 0;
        boolean check = true;
        int left = 0;
        int right = f.size() - 1;
        Point p = new Point(left, right);
        s.push((Point) p);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Date targetdate = sdf.parse(target);
        Date dateright = sdf.parse(((Mail) f.get(right)).msgDate);
        Date dateleft = sdf.parse(((Mail) f.get(left)).msgDate);

        if (targetdate.compareTo(dateright) > 0 || targetdate.compareTo(dateleft) < 0) {
            return new DLinkedList();
        }
        /*if (targetdate.compareTo(dateleft) == 0) {
         return left;
         }
         if (targetdate.compareTo(dateright) == 0) {
         return right;
         }*/
        while (left <= right) {
            Point p1 = (Point) s.peek();
            left = (int) p1.x;
            right = (int) p1.y;
            mid = left + (right - left) / 2;
            Date dateright1 = sdf.parse(((Mail) f.get(right)).msgDate);
            Date dateleft1 = sdf.parse(((Mail) f.get(left)).msgDate);
            Date datemid = sdf.parse(((Mail) f.get(mid)).msgDate);

            if (targetdate.compareTo(datemid) == 0) {
                int indexleft = mid - 1;
                /*String indexes = "";
                 indexes += String.valueOf(mid);
                 indexes += " ";*/
                found.add(f.get(mid));
                // Date datemidleft = sdf.parse(((Mail) f.get(indexleft)).msgDate);
                if (indexleft >= 0) {
                    Date datemidleft = sdf.parse(((Mail) f.get(indexleft)).msgDate);
                    while (targetdate.compareTo(datemidleft) == 0) {
                        /*indexes += String.valueOf(indexleft);
                         indexes += " ";*/
                        found.add(f.get(indexleft));
                        indexleft--;
                        if (indexleft < 0) {
                            break;
                        } else {
                            datemidleft = sdf.parse(((Mail) f.get(indexleft)).msgDate);
                        }
                    }
                }
                int indexright = mid + 1;
                //String indexes = "";
                // indexes += String.valueOf(mid);
                // indexes += " ";
                if (indexright < f.size()) {
                    Date datemidright = sdf.parse(((Mail) f.get(indexright)).msgDate);
                    while (targetdate.compareTo(datemidright) == 0) {
                        /* indexes += String.valueOf(indexright);
                         indexes += " ";*/
                        found.add(f.get(indexright));
                        indexright++;
                        if (indexright == f.size()) {
                            break;
                        } else {
                            datemidright = sdf.parse(((Mail) f.get(indexright)).msgDate);
                        }
                    }
                }
                //System.out.println(indexes);
                return found;
            } else if (targetdate.compareTo(datemid) > 0) {
                left = mid + 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            } else if (targetdate.compareTo(datemid) < 0) {
                right = mid - 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            }
        }
        return new DLinkedList();

    }

    public DLinkedList searchSubject(DLinkedList f, String target) {
        DLinkedList found = new DLinkedList();
        int mid = 0;
        boolean check = true;
        int left = 0;
        int right = f.size() - 1;
        Point p = new Point(left, right);
        s.push((Point) p);
        Mail mR = (Mail) f.get(right);
        Mail mL = (Mail) f.get(left);

        String rightstring = mR.subject;
        String leftstring = mL.subject;
        if (target.compareTo(rightstring) > 0 || target.compareTo(leftstring) < 0) {
            //return -1;
            return new DLinkedList();
        }
        /* if (target == (int) f.get(left)) {
         return left;
         }
         if (target == (int) f.get(right)) {
         return right;
         }*/
        while (left <= right) {
            Point p1 = (Point) s.peek();
            left = (int) p1.x;
            right = (int) p1.y;
            mid = left + (right - left) / 2;
            String midstring = ((Mail) f.get(mid)).subject;
            if (target.compareTo(midstring) == 0) {
                int indexleft = mid - 1;
                // String indexes = "";
                /*indexes += String.valueOf(mid);
                 indexes += " ";*/
                found.add(f.get(mid));
                if (indexleft >= 0) {
                    while (target.compareTo(((Mail) f.get(indexleft)).subject) == 0) {
                        /*indexes += String.valueOf(indexleft);
                         indexes += " ";*/
                        found.add(f.get(indexleft));
                        indexleft--;
                        if (indexleft < 0) {
                            break;
                        }
                    }
                }
                int indexright = mid + 1;
                //String indexes = "";
                // indexes += String.valueOf(mid);
                // indexes += " ";
                if (indexright < f.size()) {
                    while (target.compareTo(((Mail) f.get(indexright)).subject) == 0) {
                        /*indexes += String.valueOf(indexright);
                         indexes += " ";*/
                        found.add(f.get(indexright));
                        indexright++;
                        if (indexright == f.size()) {
                            break;
                        }
                    }
                }
                //System.out.println(indexes);
                return found;
            } else if (target.compareTo(((Mail) f.get(mid)).subject) > 0) {
                left = mid + 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            } else if (target.compareTo(((Mail) f.get(mid)).subject) < 0) {
                right = mid - 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            }
        }
        return new DLinkedList();
    }

    public DLinkedList searchSender(DLinkedList f, String target) {
        DLinkedList found = new DLinkedList();
        int mid = 0;
        boolean check = true;
        int left = 0;
        int right = f.size() - 1;
        Point p = new Point(left, right);
        s.push((Point) p);
        Mail mR = (Mail) f.get(right);
        Mail mL = (Mail) f.get(left);

        String rightstring = mR.sender;
        String leftstring = mL.sender;
        if (target.compareTo(rightstring) > 0 || target.compareTo(leftstring) < 0) {
            //return -1;
            return new DLinkedList();
        }
        /* if (target == (int) f.get(left)) {
         return left;
         }
         if (target == (int) f.get(right)) {
         return right;
         }*/
        while (left <= right) {
            Point p1 = (Point) s.peek();
            left = (int) p1.x;
            right = (int) p1.y;
            mid = left + (right - left) / 2;
            String midstring = ((Mail) f.get(mid)).sender;
            if (target.compareTo(midstring) == 0) {
                int indexleft = mid - 1;
                // String indexes = "";
                /*indexes += String.valueOf(mid);
                 indexes += " ";*/
                found.add(f.get(mid));
                if (indexleft >= 0) {
                    while (target.compareTo(((Mail) f.get(indexleft)).sender) == 0) {
                        /*indexes += String.valueOf(indexleft);
                         indexes += " ";*/
                        found.add(f.get(indexleft));
                        indexleft--;
                        if (indexleft < 0) {
                            break;
                        }
                    }
                }
                int indexright = mid + 1;
                //String indexes = "";
                // indexes += String.valueOf(mid);
                // indexes += " ";
                if (indexright < f.size()) {
                    while (target.compareTo(((Mail) f.get(indexright)).sender) == 0) {
                        /*indexes += String.valueOf(indexright);
                         indexes += " ";*/
                        found.add(f.get(indexright));
                        indexright++;
                        if (indexright == f.size()) {
                            break;
                        }
                    }
                }
                //System.out.println(indexes);
                return found;
            } else if (target.compareTo(((Mail) f.get(mid)).sender) > 0) {
                left = mid + 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            } else if (target.compareTo(((Mail) f.get(mid)).sender) < 0) {
                right = mid - 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            }
        }
        return new DLinkedList();
    }

    public DLinkedList searchrec(DLinkedList f, String target) {
        DLinkedList found = new DLinkedList();
        int mid = 0;
        boolean check = true;
        int left = 0;
        int right = f.size() - 1;
        Point p = new Point(left, right);
        s.push((Point) p);
        Mail mR = (Mail) f.get(right);
        Mail mL = (Mail) f.get(left);

        String rightstring = mR.rec;
        String leftstring = mL.rec;
        if (target.compareTo(rightstring) > 0 || target.compareTo(leftstring) < 0) {
            //return -1;
            return new DLinkedList();
        }
        /* if (target == (int) f.get(left)) {
         return left;
         }
         if (target == (int) f.get(right)) {
         return right;
         }*/
        while (left <= right) {
            Point p1 = (Point) s.peek();
            left = (int) p1.x;
            right = (int) p1.y;
            mid = left + (right - left) / 2;
            String midstring = ((Mail) f.get(mid)).rec;
            if (target.compareTo(midstring) == 0) {
                int indexleft = mid - 1;
                // String indexes = "";
                /*indexes += String.valueOf(mid);
                 indexes += " ";*/
                found.add(f.get(mid));
                if (indexleft >= 0) {
                    while (target.compareTo(((Mail) f.get(indexleft)).rec) == 0) {
                        /*indexes += String.valueOf(indexleft);
                         indexes += " ";*/
                        found.add(f.get(indexleft));
                        indexleft--;
                        if (indexleft < 0) {
                            break;
                        }
                    }
                }
                int indexright = mid + 1;
                //String indexes = "";
                // indexes += String.valueOf(mid);
                // indexes += " ";
                if (indexright < f.size()) {
                    while (target.compareTo(((Mail) f.get(indexright)).rec) == 0) {
                        /*indexes += String.valueOf(indexright);
                         indexes += " ";*/
                        found.add(f.get(indexright));
                        indexright++;
                        if (indexright == f.size()) {
                            break;
                        }
                    }
                }
                //System.out.println(indexes);
                return found;
            } else if (target.compareTo(((Mail) f.get(mid)).rec) > 0) {
                left = mid + 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            } else if (target.compareTo(((Mail) f.get(mid)).rec) < 0) {
                right = mid - 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            }
        }
        return new DLinkedList();
    }

    public DLinkedList searchattach(DLinkedList f, String target) {
        DLinkedList found = new DLinkedList();
        int mid = 0;
        boolean check = true;
        int left = 0;
        int right = f.size() - 1;
        Point p = new Point(left, right);
        s.push((Point) p);
        Mail mR = (Mail) f.get(right);
        Mail mL = (Mail) f.get(left);

        String rightstring = mR.attachbools;
        String leftstring = mL.attachbools;
        if (target.compareTo(rightstring) > 0 || target.compareTo(leftstring) < 0) {
            //return -1;
            return new DLinkedList();
        }
        /* if (target == (int) f.get(left)) {
         return left;
         }
         if (target == (int) f.get(right)) {
         return right;
         }*/
        while (left <= right) {
            Point p1 = (Point) s.peek();
            left = (int) p1.x;
            right = (int) p1.y;
            mid = left + (right - left) / 2;
            String midstring = ((Mail) f.get(mid)).attachbools;
            if (target.compareTo(midstring) == 0) {
                int indexleft = mid - 1;
                // String indexes = "";
                /*indexes += String.valueOf(mid);
                 indexes += " ";*/
                found.add(f.get(mid));
                if (indexleft >= 0) {
                    while (target.compareTo(((Mail) f.get(indexleft)).attachbools) == 0) {
                        /*indexes += String.valueOf(indexleft);
                         indexes += " ";*/
                        found.add(f.get(indexleft));
                        indexleft--;
                        if (indexleft < 0) {
                            break;
                        }
                    }
                }
                int indexright = mid + 1;
                //String indexes = "";
                // indexes += String.valueOf(mid);
                // indexes += " ";
                if (indexright < f.size()) {
                    while (target.compareTo(((Mail) f.get(indexright)).attachbools) == 0) {
                        /*indexes += String.valueOf(indexright);
                         indexes += " ";*/
                        found.add(f.get(indexright));
                        indexright++;
                        if (indexright == f.size()) {
                            break;
                        }
                    }
                }
                //System.out.println(indexes);
                return found;
            } else if (target.compareTo(((Mail) f.get(mid)).attachbools) > 0) {
                left = mid + 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            } else if (target.compareTo(((Mail) f.get(mid)).attachbools) < 0) {
                right = mid - 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            }
        }
        return new DLinkedList();
    }

    public DLinkedList searchContactName(DLinkedList f, String target) {
        DLinkedList found = new DLinkedList();
        int mid = 0;
        boolean check = true;
        int left = 0;
        int right = f.size() - 1;
        Point p = new Point(left, right);
        s.push((Point) p);
        UserContacts mR = (UserContacts) f.get(right);
        UserContacts mL = (UserContacts) f.get(left);

        String rightstring = mR.userName;
        String leftstring = mL.userName;
        if (target.compareTo(rightstring) > 0 || target.compareTo(leftstring) < 0) {
            //return -1;
            return new DLinkedList();
        }
        /* if (target == (int) f.get(left)) {
         return left;
         }
         if (target == (int) f.get(right)) {
         return right;
         }*/
        while (left <= right) {
            Point p1 = (Point) s.peek();
            left = (int) p1.x;
            right = (int) p1.y;
            mid = left + (right - left) / 2;
            String midstring = ((UserContacts) f.get(mid)).userName;
            if (target.compareTo(midstring) == 0) {
                int indexleft = mid - 1;
                // String indexes = "";
                /*indexes += String.valueOf(mid);
                 indexes += " ";*/
                found.add(f.get(mid));
                if (indexleft >= 0) {
                    while (target.compareTo(((UserContacts) f.get(indexleft)).userName) == 0) {
                        /*indexes += String.valueOf(indexleft);
                         indexes += " ";*/
                        found.add(f.get(indexleft));
                        indexleft--;
                        if (indexleft < 0) {
                            break;
                        }
                    }
                }
                int indexright = mid + 1;
                //String indexes = "";
                // indexes += String.valueOf(mid);
                // indexes += " ";
                if (indexright < f.size()) {
                    while (target.compareTo(((UserContacts) f.get(indexright)).userName) == 0) {
                        /*indexes += String.valueOf(indexright);
                         indexes += " ";*/
                        found.add(f.get(indexright));
                        indexright++;
                        if (indexright == f.size()) {
                            break;
                        }
                    }
                }
                //System.out.println(indexes);
                return found;
            } else if (target.compareTo(((UserContacts) f.get(mid)).userName) > 0) {
                left = mid + 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            } else if (target.compareTo(((UserContacts) f.get(mid)).userName) < 0) {
                right = mid - 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            }
        }
        return new DLinkedList();
    }

    public DLinkedList searchContactPhone(DLinkedList f, String target) {
        DLinkedList found = new DLinkedList();
        int mid = 0;
        boolean check = true;
        int left = 0;
        int right = f.size() - 1;
        Point p = new Point(left, right);
        s.push((Point) p);
        UserContacts mR = (UserContacts) f.get(right);
        UserContacts mL = (UserContacts) f.get(left);

        String rightstring = mR.phoneNumber;
        String leftstring = mL.phoneNumber;
        if (target.compareTo(rightstring) > 0 || target.compareTo(leftstring) < 0) {
            //return -1;
            return new DLinkedList();
        }
        /* if (target == (int) f.get(left)) {
         return left;
         }
         if (target == (int) f.get(right)) {
         return right;
         }*/
        while (left <= right) {
            Point p1 = (Point) s.peek();
            left = (int) p1.x;
            right = (int) p1.y;
            mid = left + (right - left) / 2;
            String midstring = ((UserContacts) f.get(mid)).phoneNumber;
            if (target.compareTo(midstring) == 0) {
                int indexleft = mid - 1;
                // String indexes = "";
                /*indexes += String.valueOf(mid);
                 indexes += " ";*/
                found.add(f.get(mid));
                if (indexleft >= 0) {
                    while (target.compareTo(((UserContacts) f.get(indexleft)).phoneNumber) == 0) {
                        /*indexes += String.valueOf(indexleft);
                         indexes += " ";*/
                        found.add(f.get(indexleft));
                        indexleft--;
                        if (indexleft < 0) {
                            break;
                        }
                    }
                }
                int indexright = mid + 1;
                //String indexes = "";
                // indexes += String.valueOf(mid);
                // indexes += " ";
                if (indexright < f.size()) {
                    while (target.compareTo(((UserContacts) f.get(indexright)).phoneNumber) == 0) {
                        /*indexes += String.valueOf(indexright);
                         indexes += " ";*/
                        found.add(f.get(indexright));
                        indexright++;
                        if (indexright == f.size()) {
                            break;
                        }
                    }
                }
                //System.out.println(indexes);
                return found;
            } else if (target.compareTo(((UserContacts) f.get(mid)).phoneNumber) > 0) {
                left = mid + 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            } else if (target.compareTo(((UserContacts) f.get(mid)).phoneNumber) < 0) {
                right = mid - 1;
                p = new Point(left, right);
                s.pop();
                s.push((Point) p);
            }
        }
        return new DLinkedList();
    }

}
