/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import static java.util.Date.from;
import javax.swing.JFrame;
import linkedList.cs45_cs23.LinkedList;
import mailapp.gui.Inbox;

/**
 *
 * @author arabtech
 */
public class SubtractDays {

    public double subtractDays(String mailDate) throws ParseException {
        Helper h = new Helper();
        String todayDate = h.todayDate();
        Date d1 = new SimpleDateFormat("yyyy-MM-dd").parse(mailDate);
        Date d2 = new SimpleDateFormat("yyyy-MM-dd").parse(todayDate);
       double difference = (double) Math.abs(d2.getTime() - d1.getTime());
       double dif = difference / (24 * 60 * 60 * 1000);
        return dif;
    }

    public LinkedList removeThirtyDays(JFrame frame) throws FileNotFoundException, IOException, ParseException {
        Inbox nashar = (Inbox) frame;
        FileReader f = null;
        //File trashIndex = new File("server\\users\\" + nashar.user[0] + "\\" + "trash" + "\\" + "trash.txt");
        f = new FileReader(new File("server\\users\\" + nashar.user[0] + "\\" + "trash" + "\\" + "trash.txt"));
        BufferedReader bfRead = new BufferedReader(f);
        String emailReader;
        LinkedList trashMails = new LinkedList();
        while ((emailReader = bfRead.readLine()) != null) {
            String[] email = emailReader.split("~");
           double difference = subtractDays(email[5]);
            if (difference >= 30) {
                trashMails.add(email[7]);
            }
        }
        bfRead.close();
        return trashMails;
    }

    public void deleteMail(LinkedList mailsList, JFrame frame) throws IOException {
        Inbox nashar = (Inbox) frame;
        Helper h = new Helper();
        File fromIndex = new File("server\\users\\" + nashar.user[0] + "\\trash\\" + "trash.txt");
        File tempIndex = new File("server\\users\\" + nashar.user[0] + "\\trash\\" + "temp.txt");
        while (!mailsList.isEmpty()) {
            File msg = new File("server\\users\\" + nashar.user[0] + "\\trash\\" + (String) mailsList.get(0));
            h.deleteFolder(msg);
            BufferedReader bf = new BufferedReader(new FileReader(fromIndex));
            BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));
             bftemp.close();
            String line;
            while ((line = bf.readLine()) != null) {
                String[] msgDetails = line.split("~");
                if (!msgDetails[7].equals(mailsList.get(0))) {
                    BufferedWriter bftemp1 = new BufferedWriter(new FileWriter(tempIndex, true));
                    bftemp1.append(line);
                   bftemp1.newLine();
                    bftemp1.close();
                }
            }
            bf.close();
            fromIndex.delete();
            tempIndex.renameTo(fromIndex);

            mailsList.remove(0);
        }
    }
    public void deleteMailDraft(LinkedList mailsList, JFrame frame) throws IOException {
        Inbox nashar = (Inbox) frame;
        Helper h = new Helper();
        File fromIndex = new File("server\\users\\" + nashar.user[0] + "\\draft\\" + "draft.txt");
        File tempIndex = new File("server\\users\\" + nashar.user[0] + "\\draft\\" + "temp.txt");
        while (!mailsList.isEmpty()) {
            //System.out.println((String) mailsList.get(0));
            File msg = new File("server\\users\\" + nashar.user[0] + "\\draft\\" + (String) mailsList.get(0));
            h.deleteFolder(msg);
            BufferedReader bf = new BufferedReader(new FileReader(fromIndex));
            BufferedWriter bftemp1 = new BufferedWriter(new FileWriter(tempIndex, true));
            bftemp1.close();
            String line;
            while ((line = bf.readLine()) != null) {
                String[] msgDetails = line.split("~");
                if (!msgDetails[7].equals(mailsList.get(0))) {
                    BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));
                    bftemp.append(line);
                   bftemp.newLine();
                    bftemp.close();
                }
            }
            bf.close();
            fromIndex.delete();
            tempIndex.renameTo(fromIndex);

            mailsList.remove(0);
        }
    }

}
