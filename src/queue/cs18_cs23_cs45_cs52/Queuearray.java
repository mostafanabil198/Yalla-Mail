/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package queue.cs18_cs23_cs45_cs52;

import queue.IArrayBased;
import queue.IQueue;

/**
 *
 * @author HP
 */
public class Queuearray implements IQueue, IArrayBased {

    /**
     *
     * @author HP
     */
    int front, rear, count, limit;
    /**
     *
     * @author HP
     */
    Object[] array;

    /**
     *
     * @param n gjh.
     */
    public Queuearray(final int n) {
        front = 0;
        rear = 0;
        count = 0;
        limit = n;
        array = new Object[n];

    }

    @Override
    public void enqueue(final Object item) {
        if (count == limit) {
            throw new UnsupportedOperationException("full queue");
        }
        array[rear] = item;
        if (rear == limit - 1) {
            rear = 0;
        } else {
            rear++;
        }
        count++;
    }

    @Override
    public Object dequeue() {
        if (count == 0) {
            throw new UnsupportedOperationException("empty");
        }
        Object x = array[front];
        array[front] = null;
        if (front == limit - 1) {
            front = 0;
        } else {
            front++;
        }
        count--;
        return x;
    }

    @Override
    public boolean isEmpty() {
        return (count == 0);
    }

    @Override
    public int size() {
        return count;
    }

}
