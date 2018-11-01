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
public class PDNode {

    /**
     *
     * @author محمد
     */
    private int coeff;
    /**
     *
     * @author محمد
     */

    private int exp;

    /**
     *
     * @param a .
     * @param d .
     */
    public PDNode(final int a, final int d) {
        coeff = a;
        exp = d;
    }

    /**
     *
     * @return tag.
     */
    public int getCoeff() {
        return coeff;
    }

    /**
     *
     *
     * @param coefff .
     */
    public void setCoeff(final int coefff) {
        this.coeff = coefff;
    }

    /**
     *
     * @return tag.
     */
    public int getExp() {
        return exp;
    }

    /**
     *
     *
     * @param exp1 .
     */
    public void setExp(final int exp1) {
        this.exp = exp1;
    }
}
