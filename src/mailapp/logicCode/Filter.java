/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import mailapp.interfaces.IFilter;
import mailapp.interfaces.ISort;

/**
 *
 * @author arabtech
 */
public class Filter implements IFilter, ISort {

    public boolean subject;
    public boolean sender;
    public boolean reciever;
    public boolean date;
    public boolean priority;
    public boolean ascending;
    public boolean attach = false;
    public String subject1;
    public String sender1;
    public String reciever1;
    public String date1;
    public String attach1;
    public int priority1;
    public boolean contactName;
    public String contactName1;
    public boolean contactPhone;
    public String contactPhone1;
    public String msg1;
    public boolean msg;

    public Filter() {
        ascending = true;
    }
}
