/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp;

import mailapp.logicCode.Mail;
import mailapp.logicCode.Contact;
import mailapp.logicCode.App;
import mailapp.logicCode.Folder;
import mailapp.logicCode.MoveClass;
import java.io.File;
import java.io.IOException;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;
import java.util.logging.SimpleFormatter;
import linkedList.cs45_cs23.LinkedList;
import mailapp.gui.Home;
import mailapp.logicCode.ComposeGUI;
import queue.cs18_cs23_cs45_cs52.Qlinked;

/**
 *
 * @author محمد
 */
public class MailApp {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws ParseException, IOException, InterruptedException {
        Thread.sleep(4500);
        Home mailApp = new Home();
        mailApp.setVisible(true);
        mailApp.setLocationRelativeTo(null);
    }
}
