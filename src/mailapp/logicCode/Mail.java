/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import mailapp.interfaces.IMail;
import java.util.Date;
import linkedList.cs45_cs23.LinkedList;
import queue.cs18_cs23_cs45_cs52.Qlinked;

/**
 *
 * @author arabtech
 */
public class Mail implements IMail {

    public String sender;
    public Qlinked receiver;
    public String rec;
    public String msg;
    public LinkedList attachments;
    public boolean attachBool;
    public String attachbools;
    public String subject;
    public String msgDate;
    public int priority;
    public Folder msgFolder;
    public int starred;
    public String subDate;

    public Mail() {
        priority = 5;
        starred = 0;
    }

}
