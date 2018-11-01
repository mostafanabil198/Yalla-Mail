/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package stack.cs45;

import stack.IExpressionEvaluator;

/**
 *
 * @author محمد
 */
public class ExpressionEvaluator implements IExpressionEvaluator {

    /**
     *
     * @author محمد
     */
    String s1 = "";
    /**
     *
     * @author محمد
     */
    Stack x = new Stack();
    /**
     *
     * @author محمد
     */
    int a, b, c, d;
    /**
     *
     * @author محمد
     */
    boolean check = false;
    /**
     *
     * @author محمد
     */
    int c1 = 0, c2 = 0;
    /**
     *
     * @author محمد
     */
    StringBuilder sr = new StringBuilder("");

    /**
     * .
     * @param expression
     * @return
     */
    @Override
    public String infixToPostfix(final String expression) {
        if (expression == null) {
            throw new UnsupportedOperationException();
        }
        if (expression == "") {
            throw new UnsupportedOperationException();
        }
        for (int i = 0; i < expression.length(); i++) {
            char cc = expression.charAt(i);
            if (Character.isWhitespace(cc)) {
                continue;
            }
            if (Character.isLetterOrDigit(cc)) {
                //s1 += c ;
                sr.append(cc);
                c1++;
                while ((i + 1) < expression.length()) {
                    if (Character.isLetterOrDigit(expression.charAt(i + 1))) {
                        // s1 += expression.charAt(i+1);
                        sr.append(expression.charAt(i + 1));

                        i++;
                    } else {
                        break;
                    }
                }

                //s1 += " ";
                sr.append(" ");
                check = true;
            } else if (cc == '+' || cc == '-' || cc == '*' || cc == '/' || cc
                    == '(') {
                if (cc == '+' || cc == '-' || cc == '*' || cc == '/') {
                    c2++;
                }
                if (!check && cc != '(') {
                    throw new UnsupportedOperationException();
                }
                if (x.size() == 0 && cc != '(' && !check) {
                    throw new UnsupportedOperationException();
                }
                check = false;
                if (cc == '(') {
                    a = 0;
                }
                if (cc == '+' || cc == '-') {
                    a = 1;
                }
                if (cc == '*' || cc == '/') {
                    a = 2;
                }
                if (x.size() == 0) {
                    x.push(cc);
                    b = a;
                } else {
                    if (b < a || a == 0) {
                        x.push(cc);
                        b = a;
                    } else {
                        while ((b == a || b > a)) {
                            // s1 += x.pop();
                            sr.append(x.pop());
                            // s1 += " ";
                            sr.append(" ");
                            if (x.size() == 0) {
                                break;
                            }
                            if ((char) x.peek() == '(') {
                                b = 0;
                            }
                            if ((char) x.peek() == '+' || (char) x.peek()
                                    == '-') {
                                b = 1;
                            }
                            if ((char) x.peek() == '*' || (char) x.peek()
                                    == '/') {
                                b = 2;
                            }
                        }
                        x.push(cc);
                        b = a;
                    }
                }
            } else if (cc == ')') {
                while ((char) x.peek() != '(') {
                    // s1 += x.pop();
                    sr.append(x.pop());
                    // s1 += " ";
                    sr.append(" ");
                }
                x.pop();
                if (x.size() != 0) {
                    if ((char) x.peek() == '(') {
                        b = 0;
                    }
                    if ((char) x.peek() == '+' || (char) x.peek()
                            == '-') {
                        b = 1;
                    }
                    if ((char) x.peek() == '*' || (char) x.peek()
                            == '/') {
                        b = 2;
                    }
                }
            } else {
                throw new UnsupportedOperationException();
            }
        }
        if (c2 != (c1 - 1)) {
            throw new UnsupportedOperationException();
        }
        if (x.size() != 0) {
            while (x.size() > 0) {
                // s1 += x.pop();
                sr.append(x.pop());
                if (x.size() != 0) {
                    //s1 += " ";
                    sr.append(" ");
                }

            }
        }

        return sr.toString();
    }

    /**
     * .
     * @param expression
     * @return
     */
    @Override
    public int evaluate(final String expression) {
        int c3 = 0, c4 = 0;
        if (expression == null) {
            throw new UnsupportedOperationException();
        }
        if (expression == "") {
            throw new UnsupportedOperationException();
        }
        Stack st = new Stack();
        String ss = "";
        int counter = 0;
        for (int i = 0; i < expression.length(); i++) {
            char cs = expression.charAt(i);
            if (Character.isLetter(cs)) {
                throw new UnsupportedOperationException();
            }
            if (Character.isDigit(cs)) {
                c3++;
                ss += cs;
                while ((i + 1) < expression.length()) {
                    if (Character.isLetterOrDigit(expression.charAt(
                            i + 1))) {
                        ss += expression.charAt(i + 1);
                        i++;
                    } else {
                        break;
                    }
                }
                st.push(Float.parseFloat(ss));
                ss = "";
            } else if (cs == '/' || cs == '-' || cs == '*' || cs == '+') {
                c4++;
                if (cs == '+') {
                    float x4 = (float) st.pop();
                    float y4 = (float) st.pop();
                    // z = x+y;
                    st.push(x4 + y4);
                }
                if (cs == '*') {
                    float xx = (float) st.pop();
                    float yy = (float) st.pop();
                    st.push(xx * yy);
                }
                if (cs == '-') {
                    float x1 = (float) st.pop();
                    float y1 = (float) st.pop();
                    st.push(y1 - x1);
                }
                if (cs == '/') {
                    float x2 = (float) st.pop();
                    float y2 = (float) st.pop();
                    st.push(y2 / x2);
                }
            } else if (cs != ' ') {
                throw new UnsupportedOperationException();
            }

        }
        if (c4 != c3 - 1) {
            throw new UnsupportedOperationException();
        }
        float e = (float) st.pop();
        int ans = Math.round(e);
        return ans;
    }

}
