/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue.cs18_cs23_cs45_cs52;

import queue.ILinkedBased;
import queue.IQueue;
import linkedList.cs45_cs23.LinkedList;

/**
 *
 * @author محمد
 */
public class Qlinked implements IQueue, ILinkedBased {

    /**
     *
     * @author محمد
     */
    LinkedList link = new LinkedList();

    /**
     * .
     * @param item
     */
    @Override
    public void enqueue(final Object item) {
        link.add(item);
    }

    /**
     * .
     * @return
     */
    @Override
    public Object dequeue() {
        if (isEmpty()) {
            throw new UnsupportedOperationException("dq");
        }
        Object ob = link.get(0);
        link.remove(0);
        return ob;
    }

    /**
     * .
     * @return
     */
    @Override
    public boolean isEmpty() {
        return link.size() == 0;

    }

    /**
     * .
     * @return
     */
    @Override
    public int size() {
        return link.size();
    }
//
}
