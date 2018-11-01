/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedList.cs45_cs23;

/**
 *
 * @author محمد
 */
public class Snode {

    /**
     *
     * @author محمد
     */
    private Object data;
    /**
     *
     * @author محمد
     */

    private Snode next;

    /**
     *
     *
     * @param oj .
     * @param n .
     */
    public Snode(final Object oj, final Snode n) {
        data = oj;
        next = n;
    }

    /**
     *
     *
     * @return .
     */
    public Object getData() {
        return data;
    }

    /**
     *
     *
     * @param data1 .
     */
    public void setData(final Object data1) {
        this.data = data1;
    }

    /**
     *
     *
     * @return .
     */
    public Snode getNext() {
        return next;
    }

    /**
     *
     *
     * @param next1 .
     */
    public void setNext(final Snode next1) {
        this.next = next1;
    }
}
