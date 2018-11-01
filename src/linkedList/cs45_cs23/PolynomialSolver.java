/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linkedList.cs45_cs23;

import linkedList.IPolynomialSolver;

/**
 *
 * @author محمد
 */
public class PolynomialSolver implements IPolynomialSolver {

    /**
     *
     * @author محمد
     */
    DLinkedList a1 = new DLinkedList();
    /**
     *
     * @author محمد
     */

    DLinkedList b1 = new DLinkedList();
    /**
     *
     * @author محمد
     */

    DLinkedList c1 = new DLinkedList();
    /**
     *
     * @author محمد
     */

    DLinkedList r = new DLinkedList();
    /**
     *
     * @author محمد
     */

    boolean found = false;

    @Override
    public void setPolynomial(final char poly, final int[][] terms) {
        int cof = 0, exp = 0;
        if (poly == 'A') {
            clearPolynomial('A');
            for (int i = 0; i < terms.length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        cof = terms[i][j];
                    } else if (j == 1) {
                        exp = terms[i][j];
                    }
                }
                PDNode temp = new PDNode(0, 0);
                PDNode temp2 = new PDNode(0, 0);
                temp.setCoeff(cof);
                temp.setExp(exp);
                int p = 0;
                if (a1.size() == 0) {

                    a1.add((PDNode) temp);
                    PDNode n = new PDNode(0, 0);
                    n = (PDNode) a1.get(p);
                } else {
                    found = false;
                    for (int m = 0; m < a1.size(); m++) {
                        temp2 = (PDNode) a1.get(m);
                        if (temp2.getExp() == temp.getExp()) {
                            temp2.setCoeff(temp2.getCoeff()
                                    + temp.getCoeff());

                            a1.set(m, temp2);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {

                        a1.add(temp);
                    }

                }
            }
        } else if (poly == 'B') {
            clearPolynomial('B');
            for (int i = 0; i < terms.length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        cof = terms[i][j];
                    } else if (j == 1) {
                        exp = terms[i][j];
                    }
                }
                PDNode temp = new PDNode(0, 0);
                PDNode temp2 = new PDNode(0, 0);
                temp.setCoeff(cof);
                temp.setExp(exp);
                if (b1.size() == 0) {
                    b1.add(temp);
                } else {
                    found = false;
                    for (int m = 0; m < b1.size(); m++) {
                        temp2 = (PDNode) b1.get(m);
                        if (temp2.getExp() == temp.getExp()) {
                            temp2.setCoeff(temp2.getCoeff()
                                    + temp.getCoeff());
                            b1.set(m, temp2);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        b1.add(temp);
                    }

                }

            }
        } else if (poly == 'C') {
            clearPolynomial('C');
            for (int i = 0; i < terms.length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        cof = terms[i][j];
                    } else if (j == 1) {
                        exp = terms[i][j];
                    }
                }
                PDNode temp = new PDNode(0, 0);
                PDNode temp2 = new PDNode(0, 0);
                temp.setCoeff(cof);
                temp.setExp(exp);
                if (c1.size() == 0) {
                    c1.add(temp);
                } else {
                    found = false;
                    for (int m = 0; m < c1.size(); m++) {
                        temp2 = (PDNode) c1.get(m);
                        if (temp2.getExp() == temp.getExp()) {
                            temp2.setCoeff(temp2.getCoeff()
                                    + temp.getCoeff());
                            c1.set(m, temp2);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        c1.add(temp);
                    }

                }

            }
        } else if (poly == 'R') {
            clearPolynomial('R');
            for (int i = 0; i < terms.length; i++) {
                for (int j = 0; j < 2; j++) {
                    if (j == 0) {
                        cof = terms[i][j];
                    } else if (j == 1) {
                        exp = terms[i][j];
                    }
                }
                PDNode temp = new PDNode(0, 0);
                PDNode temp2 = new PDNode(0, 0);
                temp.setCoeff(cof);
                temp.setExp(exp);
                if (r.size() == 0) {
                    r.add(temp);
                } else {
                    found = false;
                    for (int m = 0; m < r.size(); m++) {
                        temp2 = (PDNode) r.get(m);
                        if (temp2.getExp() == temp.getExp()) {
                            temp2.setCoeff(temp2.getCoeff()
                                    + temp.getCoeff());
                            r.set(m, temp2);
                            found = true;
                            break;
                        }
                    }
                    if (!found) {
                        r.add(temp);
                    }
                }
            }
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public String print(final char poly) {
        String equ = "";
        if (poly == 'A' && a1.size() > 0) {
            sort(a1);
            equ = printpol(a1);
            return equ;
        } else if (poly == 'B' && b1.size() > 0) {
            sort(b1);
            equ = printpol(b1);
            return equ;
        } else if (poly == 'C' && c1.size() > 0) {
            sort(c1);
            equ = printpol(c1);
            return equ;
        } else if (poly == 'R' && r.size() > 0) {
            sort(r);
            equ = printpol(r);
            return equ;
        } else {
            return null;
        }
    }

    @Override
    public void clearPolynomial(final char poly) {
        if (poly == 'A') {
            a1.clear();
        } else if (poly == 'B') {
            b1.clear();
        } else if (poly == 'C') {
            c1.clear();
        } else if (poly == 'R') {
            r.clear();
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public float evaluatePolynomial(final char poly, final float value) {
        float ans1 = 0;
        if (poly == 'A') {
            if (a1.size() == 0) {
                throw new UnsupportedOperationException();
            }
            ans1 = evaluatepol(a1, value);
        } else if (poly == 'B') {
            if (b1.size() == 0) {
                throw new UnsupportedOperationException();
            }
            ans1 = evaluatepol(b1, value);
        } else if (poly == 'C') {
            if (c1.size() == 0) {
                throw new UnsupportedOperationException();
            }
            ans1 = evaluatepol(c1, value);
        } else if (poly == 'R') {
            if (r.size() == 0) {
                throw new UnsupportedOperationException();
            }
            ans1 = evaluatepol(r, value);
        } else {
            throw new UnsupportedOperationException();
        }
        return ans1;
    }

    @Override
    public int[][] add(final char poly1, final char poly2) {
        int[][] array;
        if ((poly1 == 'A' && poly2 == 'B')
                || (poly1 == 'B' && poly2 == 'A')) {
            if (a1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            addlists(r, a1, b1);
            array = makearray(r);
            return array;
        } else if ((poly1 == 'A' && poly2 == 'C')
                || (poly1 == 'C' && poly2 == 'A')) {
            if (a1.isEmpty() || c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            addlists(r, a1, c1);
            array = makearray(r);
            return array;
        } else if ((poly1 == 'B' && poly2 == 'C')
                || (poly1 == 'C' && poly2 == 'B')) {
            if (c1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            addlists(r, b1, c1);
            array = makearray(r);
            return array;

        } else if (poly1 == 'A' && poly2 == 'A') {
            if (a1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            addlists(r, a1, a1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'B' && poly2 == 'B') {
            if (b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            addlists(r, b1, b1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'C' && poly2 == 'C') {
            if (c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            addlists(r, c1, c1);
            array = makearray(r);
            return array;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int[][] subtract(final char poly1, final char poly2) {
        int[][] array;
        if (poly1 == 'A' && poly2 == 'B') {
            if (a1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, a1, b1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;
        } else if (poly1 == 'A' && poly2 == 'C') {
            if (a1.isEmpty() || c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, a1, c1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;
        } else if (poly1 == 'B' && poly2 == 'C') {
            if (c1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, b1, c1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;
        } else if (poly1 == 'B' && poly2 == 'A') {
            if (a1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, b1, a1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;
        } else if (poly1 == 'C' && poly2 == 'A') {
            if (a1.isEmpty() || c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, c1, a1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;
        } else if (poly1 == 'C' && poly2 == 'B') {
            if (c1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, c1, b1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;
        } else if (poly1 == 'A' && poly2 == 'A') {
            if (a1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, a1, a1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;

        } else if (poly1 == 'B' && poly2 == 'B') {
            if (b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, b1, b1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;
        } else if (poly1 == 'C' && poly2 == 'C') {
            if (c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            subblists(r, c1, c1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;

        } else {
            throw new UnsupportedOperationException();
        }
    }

    @Override
    public int[][] multiply(final char poly1, final char poly2) {
        int[][] array;
        if (poly1 == 'A' && poly2 == 'B') {
            if (a1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            multiplylists(r, a1, b1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'A' && poly2 == 'C') {
            if (a1.isEmpty() || c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            multiplylists(r, a1, c1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'B' && poly2 == 'C') {
            if (c1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            multiplylists(r, b1, c1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'A' && poly2 == 'A') {
            if (a1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            multiplylists(r, a1, a1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'B' && poly2 == 'B') {
            if (b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            multiplylists(r, b1, b1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'C' && poly2 == 'C') {
            if (c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            multiplylists(r, c1, c1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'B' && poly2 == 'A') {
            if (a1.isEmpty() || b1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            multiplylists(r, a1, b1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'C' && poly2 == 'A') {
            if (a1.isEmpty() || c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            multiplylists(r, a1, c1);
            array = makearray(r);
            return array;
        } else if (poly1 == 'C' && poly2 == 'B') {
            if (b1.isEmpty() || c1.isEmpty()) {
                throw new UnsupportedOperationException();
            }
            clearPolynomial('R');
            //r.clear();
            multiplylists(r, b1, c1);
            if (r.size() == 0) {
                array = new int[1][2];
                array[0][0] = 0;
                array[0][1] = 0;
            } else {
                array = makearray(r);
            }
            return array;
        } else {
            throw new UnsupportedOperationException();
        }
    }

    /**
     *
     * @param x .
     */
    public void sort(final DLinkedList x) {

        for (int i = 0; i < x.size() - 1; i++) {
            for (int j = 0; j < x.size() - 1 - i; j++) {
                PDNode s1 = new PDNode(0, 0);
                s1 = (PDNode) x.get(j);
                PDNode s2 = new PDNode(0, 0);
                s2 = (PDNode) x.get(j + 1);
                if (s1.getExp() < s2.getExp()) {
                    PDNode temp = new PDNode(0, 0);
                    temp = s2;
                    x.set(j + 1, s1);
                    x.set(j, temp);
                }
            }
        }
    }

    /**
     *
     * @param x .
     * @return .
     */
    public String printpol(final DLinkedList x) {
        PDNode s1 = new PDNode(0, 0);
        String pol = "";
        for (int i = 0; i < x.size(); i++) {
            s1 = (PDNode) x.get(i);
            if (s1.getCoeff() == 0) {
                continue;
            } else if (s1.getCoeff() > 0) {
                if (pol.length() == 0) {
                    if (s1.getExp() == 0) {
                        pol += (s1.getCoeff());
                    } else if (s1.getExp() == 1) {
                        if (s1.getCoeff() == 1) {
                            pol += ("x");
                        } else {
                            pol += (s1.getCoeff() + "x");
                        }
                    } else {
                        if (s1.getCoeff() == 1) {
                            pol += ("x^" + s1.getExp());
                        } else {
                            pol += (s1.getCoeff() + "x^" + s1.getExp());
                        }
                    }
                } else if (pol.length() != 0) {
                    if (s1.getExp() == 0) {
                        pol += ("+" + s1.getCoeff());
                    } else {
                        if (s1.getExp() == 1) {
                            if (s1.getCoeff() == 1) {
                                pol += ("+" + "x");
                            } else {
                                pol += ("+" + s1.getCoeff() + "x");
                            }
                        } else {
                            if (s1.getCoeff() == 1) {
                                pol += ("+" + "x^" + s1.getExp());
                            } else {
                                pol += ("+" + s1.getCoeff()
                                        + "x^" + s1.getExp());
                            }
                        }
                    }
                }
            } else if (s1.getCoeff() < 0) {
                if (s1.getExp() == 0) {
                    pol += ("-" + (s1.getCoeff() * -1));
                } else {
                    if (s1.getExp() == 1) {
                        if (s1.getCoeff() == -1) {
                            pol += ("-" + "x");
                        } else {
                            pol += ("-" + (s1.getCoeff() * -1) + "x");
                        }
                    } else {
                        if (s1.getCoeff() == -1) {
                            pol += ("-" + "x^" + s1.getExp());
                        } else {
                            pol += ("-" + (s1.getCoeff() * -1)
                                    + "x^" + s1.getExp());
                        }
                    }
                }
            }
        }
        return pol;
    }

    /**
     *
     * @param value .
     * @param x .
     * @return .
     */
    public float evaluatepol(final DLinkedList x, final float value) {
        float ans = 0;
        PDNode s1 = new PDNode(0, 0);
        for (int i = 0; i < x.size(); i++) {
            s1 = (PDNode) x.get(i);
            if (s1.getExp() < 0 && value == 0) {
                throw new UnsupportedOperationException();
            }
            ans += s1.getCoeff() * Math.pow(value, s1.getExp());
        }
        return ans;
    }

    /**
     *
     * @param x .
     */
    public void minimize(final DLinkedList x) {
        sort(x);
        for (int i = 0; i < x.size(); i++) {
            PDNode s1 = new PDNode(0, 0);
            s1 = (PDNode) x.get(i);
            for (int j = i + 1; j < x.size(); j++) {
                PDNode s2 = new PDNode(0, 0);
                s2 = (PDNode) x.get(j);
                if (s1.getExp() == s2.getExp()) {
                    PDNode s3 = new PDNode(0, 0);
                    s3.setCoeff(s1.getCoeff() + s2.getCoeff());
                    s3.setExp(s1.getExp());
                    x.remove(j);
                    j--;
                    s1 = s3;
                    x.set(i, s1);
                }
            }
        }
        for (int i = 0; i < x.size(); i++) {
            PDNode s1 = new PDNode(0, 0);
            s1 = (PDNode) x.get(i);
            if (s1.getCoeff() == 0) {
                x.remove(i);
                i--;
            }
        }
        if (x.isEmpty()) {
            PDNode s4 = new PDNode(0, 0);
            x.add(s4);
        }
    }

    /**
     *
     * @param z .
     * @param x .
     * @param v .
     */
    public void addlists(final DLinkedList v,
            final DLinkedList x, final DLinkedList z) {
        for (int i = 0; i < x.size(); i++) {
            PDNode s1 = new PDNode(0, 0);
            s1 = (PDNode) x.get(i);
            v.add(s1);
        }
        for (int i = 0; i < z.size(); i++) {
            PDNode s1 = new PDNode(0, 0);
            s1 = (PDNode) z.get(i);
            v.add(s1);
        }
        minimize(v);
        sort(v);
    }

    /**
     *
     * @param z .
     * @param x .
     * @param v .
     */
    public void subblists(final DLinkedList v,
            final DLinkedList x, final DLinkedList z) {
        for (int i = 0; i < x.size(); i++) {
            PDNode s1 = new PDNode(0, 0);
            s1 = (PDNode) x.get(i);
            v.add(s1);
        }
        for (int i = 0; i < z.size(); i++) {
            PDNode s2 = new PDNode(0, 0);
            s2 = (PDNode) z.get(i);
            PDNode s3 = new PDNode(0, 0);
            s3.setCoeff(s2.getCoeff() * -1);
            s3.setExp(s2.getExp());
            v.add(s3);
        }
        minimize(v);
        sort(v);
    }

    /**
     *
     * @param x .
     * @return .
     */
    public int[][] makearray(final DLinkedList x) {
        int[][] array = new int[x.size()][2];
        for (int i = 0; i < x.size(); i++) {
            PDNode s1 = new PDNode(0, 0);
            s1 = (PDNode) x.get(i);
            array[i][0] = s1.getCoeff();
            array[i][1] = s1.getExp();
        }
        return array;
    }

    /**
     *
     * @param z .
     * @param x .
     * @param y .
     */

    public void multiplylists(final DLinkedList z,
            final DLinkedList x, final DLinkedList y) {
        for (int i = 0; i < x.size(); i++) {
            PDNode s1 = new PDNode(0, 0);
            s1 = (PDNode) x.get(i);
            for (int j = 0; j < y.size(); j++) {
                PDNode s2 = new PDNode(0, 0);
                s2 = (PDNode) y.get(j);
                PDNode s3 = new PDNode(0, 0);
                s3.setCoeff(s1.getCoeff() * s2.getCoeff());
                s3.setExp(s1.getExp() + s2.getExp());
                z.add(s3);
            }
        }
        minimize(z);
        sort(z);
    }
}
