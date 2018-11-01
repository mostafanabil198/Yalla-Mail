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
public class Dnode {

    /**
     *
     * @author محمد
     */
    private Object data;
    /**
     *
     * @author محمد
     */

    private Dnode next;
    /**
     *
     * @author محمد
     */

    private Dnode prev;

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
     * @param a .
     * @param b .
     * @param c .
     */
    public Dnode(final Object a, final Dnode b, final Dnode c) {
        data = a;
        next = b;
        prev = c;
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
    public Dnode getNext() {
        return next;
    }

    /**
     *
     *
     * @param next1 .
     */
    public void setNext(final Dnode next1) {
        this.next = next1;
    }

    /**
     *
     *
     * @return .
     */
    public Dnode getPrev() {
        return prev;
    }

    /**
     *
     *
     * @param prev1 .
     */
    public void setPrev(final Dnode prev1) {
        this.prev = prev1;
    }
}
