/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack.cs45;

import stack.IStack;
import linkedList.cs45_cs23.LinkedList;
//import eg.edu.alexu.csd.datastructure.linkedList.cs45_cs23.Snode;

/**
 *
 * @author محمد
 */
public class Stack implements IStack {

    //  private Node top;
    // private int size;
    /*  public Stack()
     {
     top = null;
     size = 0;
     }*/
    /**
     *
     * @author محمد
     */
    LinkedList d = new LinkedList();
    //int size = 0;

    /**
     * .
     * @return
     */
    @Override
    public Object pop() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        Object s = d.get(0);
        d.remove(0);
        //size--;
        return s;

    }

    /**
     * .
     * @return
     */
    @Override
    public Object peek() {
        if (isEmpty()) {
            throw new UnsupportedOperationException();
        }
        return d.get(0);
    }

    /**
     * .
     * @param element
     */
    @Override
    public void push(final Object element) {
        // Node s = new Node(element, top);
        d.add(0, element);
        // top = s;
        // size ++;
    }

    /**
     * .
     * @return
     */
    @Override
    public boolean isEmpty() {
        if (d.size() == 0) {
            return true;
        }
        return false;
    }

    /**
     * .
     * @return
     */
    @Override
    public int size() {
        return d.size();
    }

}
