/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import javax.swing.JFrame;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import mailapp.gui.Inbox;


/**
 *
 * @author arabtech
 */
public class ViewMessages {
    public static int SelectAll = 0;
    Inbox in ;
    
    public ViewMessages(JFrame frame){
    in=(Inbox)frame;
}

    public int showInTable(Mail[] msg, JTable table, String msgFolder) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int numberofrows = 0;
        boolean check = false;

        for (int i = 0; i < 10; i++) {
            model.setValueAt("", i, 0);
            model.setValueAt("", i, 1);
            model.setValueAt("", i, 2);
        }
        if (msgFolder.equals("inbox")) {
            for (int i = 0; i < msg.length && msg[i] != null; i++) {
                model.setValueAt(msg[i].sender, i, 0);
                model.setValueAt(msg[i].subject, i, 1);
                model.setValueAt(msg[i].msgDate, i, 2);
                numberofrows = i;
                check=true;

            }
        } else if (msgFolder.equals("sent") || msgFolder.equals("draft")) {
            for (int i = 0; i < msg.length && msg[i] != null; i++) {
                model.setValueAt("to: " + msg[i].rec, i, 0);
                model.setValueAt(msg[i].subject, i, 1);
                model.setValueAt(msg[i].msgDate, i, 2);
                numberofrows = i;
                check = true;
            }
        } else  {
            for (int i = 0; i < msg.length && msg[i] != null; i++) {
                model.setValueAt("to: " + msg[i].rec + "from:" + msg[i].sender, i, 0);
                model.setValueAt(msg[i].subject, i, 1);
                model.setValueAt(msg[i].msgDate, i, 2);
                numberofrows = i;
                check= true;
            }
        }
        Starred s = new Starred();
        s.ShowStars(msg, in);
        if (check) {
            SelectAll = numberofrows+1;
            return numberofrows+1;
        } else {
            SelectAll = 0;
            return 0;
        }
    }

}
