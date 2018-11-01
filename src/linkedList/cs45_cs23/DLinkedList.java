/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedList.cs45_cs23;

import linkedList.ILinkedList;

/**
 *
 * @author محمد
 */
public class DLinkedList implements ILinkedList {

    /**
     *
     * @author محمد
     */
    private Dnode head;
    /**
     *
     * @author محمد
     */

    private Dnode tail;
    /**
     *
     * @author محمد
     */

    private int size;

    /**
     *
     * @author محمد
     */
    public DLinkedList() {
        tail = null;
        head = null;
        size = 0;
    }

    @Override
    public void add(final int index, final Object element) {

        Dnode sadd = new Dnode(null, null, null);
        Dnode s1 = head;
        Dnode s2 = head;
        if (index < 0 || index > size) {
            throw new UnsupportedOperationException();
        }
        if (index == 0 && size == 0) {
            sadd.setData(element);
            head = sadd;
            tail = sadd;
            size++;
        } else if (size == 1 && index == 0) {
            sadd.setData(element);
            head = sadd;
            tail.setPrev(head);
            head.setNext(tail);
            size++;
        } else if (size == 1 && index == 1) {
            sadd.setData(element);
            size++;
            tail = sadd;
            tail.setPrev(head);
            head.setNext(tail);
        } else if (size > 1 && index == 0) {
            sadd.setData(element);
            head = sadd;
            s1.setPrev(head);
            head.setNext(s1);
            size++;

        } else if (size > 1 && index != size) {
            for (int i = 0; i < size - 1; i++) {
                // s2 = s2.getNext();
                if (i == index - 1) {
                    s2 = s1;
                    s2 = s2.getNext();
                    sadd.setData(element);
                    s2.setPrev(sadd);
                    s1.setNext(sadd);
                    sadd.setNext(s2);
                    sadd.setPrev(s1);
                    size++;
                    break;
                } else {
                    s1 = s1.getNext();
                }
            }
        } else if (size > 1 && index == size) {
            for (int i = 0; i < size - 1; i++) {
                s1 = s1.getNext();
                //s2=s2.getNext();
                if (i == index - 2) {
                    // s2=s2.getNext();
                    sadd.setData(element);
                    size++;
                    tail.setNext(sadd);
                    sadd.setPrev(tail);
                    tail = sadd;
                    break;
                }
            }
        }

    }

    @Override
    public void add(final Object element) {
        Dnode s1 = new Dnode(null, null, null);
        s1.setData(element);
        if (size == 0) {
            head = s1;
            tail = head;

        } else {
            tail.setNext(s1);
            s1.setPrev(tail);
            tail = s1;

            tail.setNext(null);
            if (size == 1) {
                head.setNext(tail);
            }
        }
        size++;
    }

    @Override
    public Object get(final int index) {
        Dnode s1 = head;
        if (index >= size || size == 0 || index < 0) {
            throw new UnsupportedOperationException();
        }
        if (index == 0) {
            return head.getData();
        } else {
            for (int i = 0; i < index; i++) {
                s1 = s1.getNext();
            }
        }
        return s1.getData();
    }

    @Override
    public void set(final int index, final Object element) {
        Dnode s1 = head;
        if (index >= size || size == 0 || index < 0) {
            throw new UnsupportedOperationException();
        }
        if (index == 0) {
            head.setData(element);
        } else {
            for (int i = 0; i < index; i++) {
                s1 = s1.getNext();
            }
            s1.setData(element);
        }
    }

    @Override
    public void clear() {
        size = 0;
        head = null;
        tail = head;

    }

    @Override
    public boolean isEmpty() {
        return (size == 0);
    }

    @Override
    public void remove(final int index) {
        Dnode s1 = head;
        if (index >= size || size == 0 || index < 0) {
            throw new UnsupportedOperationException();
        }
        if (size == 1 && index == 0) {
            size--;
            head = null;
            tail = null;
        } else if (size == 2 && index == 0) {
            size--;
            tail.setPrev(null);
            head = tail;
        } else if (size == 2 && index == 1) {
            size--;
            head.setNext(null);
            tail = head;

        } else if (size > 2 && index == 0) {
            s1 = s1.getNext();
            s1.setPrev(null);
            head = s1;
            size--;

        } else if (size > 2 && index != size - 1) {

            Dnode s3;
            for (int i = 0; i < size - 1; i++) {

                if (i == index - 1) {

                    s3 = s1;
                    s1 = s1.getNext();
                    s1 = s1.getNext();
                    s1.setPrev(s3);
                    s3.setNext(s1);
                    size--;
                    break;
                } else {
                    s1 = s1.getNext();
                }
            }
        } else if (size > 2 && index == size - 1) {
            Dnode s3;
            for (int i = 0; i < size - 1; i++) {

                if (i == index - 2) {
                    s3 = s1;
                    s1 = s1.getNext();
                    s1.setNext(null);
                    s1.setPrev(s3);
                    s3.setNext(s1);
                    tail = s1;
                    size--;
                    break;
                } else {
                    s1 = s1.getNext();
                }
            }
        }
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public ILinkedList sublist(final int fromIndex, final int toIndex) {

        if (fromIndex < 0 || fromIndex > size - 1 || toIndex
                < 0 || toIndex > size - 1) {
            throw new UnsupportedOperationException();
        }

        Dnode s1 = new Dnode(null, null, null);
        s1 = head;
        LinkedList link = new LinkedList();
        for (int i = 0; i < size - 1; i++) {
            if (i >= fromIndex && i <= toIndex) {
                link.add(s1.getData());
            }
            s1 = s1.getNext();
        }
        return link;
    }

    @Override
    public boolean contains(final Object o) {
        if (size == 0) {
            throw new UnsupportedOperationException();
        }
        Dnode s1 = head;
        if (o.equals(s1.getData())) {
            return true;
        } else {
            for (int i = 0; i < size - 1; i++) {
                s1 = s1.getNext();
                if (o.equals(s1.getData())) {
                    return true;
                }
            }
        }
        return false;

    }

}
