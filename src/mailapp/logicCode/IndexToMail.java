/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import javax.swing.JFrame;
import linkedList.cs45_cs23.LinkedList;
import mailapp.gui.Inbox;

/**
 *
 * @author arabtech
 */
public class IndexToMail {
    public LinkedList toMail(LinkedList ind, JFrame frame){
        Inbox in = (Inbox) frame;
        LinkedList retIndex= new LinkedList();
            retIndex =    in.NomOfChecked();
       Mail[] retMails= in.allMails;
       LinkedList markedMails=new LinkedList();
      while(retIndex.size()!=0){  
          MoveClass mc=new MoveClass();
          mc.subject=retMails[(int)retIndex.get(0)].subject;
          mc.user= in.user[0];
          mc.dir=in.recentFolder;
          mc.subDate = retMails[(int)retIndex.get(0)].subDate;
          markedMails.add(mc);
          retIndex.remove(0);
      }
       return markedMails;
    }
    
}
