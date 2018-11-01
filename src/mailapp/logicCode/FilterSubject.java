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
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
import linkedList.cs45_cs23.LinkedList;
import mailapp.gui.Inbox;
import mailapp.interfaces.IMail;
import queue.cs18_cs23_cs45_cs52.Qlinked;

/**
 *
 * @author arabtech
 */
public class FilterSubject {

    String userFolder;

    public void writeToFilter(String user, String userFolder1, String filterName) throws IOException {
        BufferedWriter write = new BufferedWriter(new FileWriter(new File("server\\users\\" + user + "\\filter.txt"), true));
        write.append(filterName + "~" + userFolder1);
        write.newLine();
        write.close();
        File f = new File("server\\users\\" + user + "\\user folders\\" + userFolder1);
        f.mkdirs();
    }

    public boolean readFromFile(String subject, String reciever) throws IOException {
        File usersDir = new File("server\\users");
        String un = "";
        FileReader fi = null;
        fi = new FileReader(new File(usersDir.toString() + "\\users.txt"));
        BufferedReader bfRead = new BufferedReader(fi);
        String usersReader;
        while ((usersReader = bfRead.readLine()) != null) {
            String[] user = usersReader.split("~");
            if (user[2].equals(reciever)) {
                un = user[0];
            }
        }
        bfRead.close();
        File userFilter = new File("server\\users\\" + un);
        FileReader f = null;
        try {
            f = new FileReader(new File(userFilter.toString() + "\\filter.txt"));
        } catch (FileNotFoundException ex) {
            Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
        }
        BufferedReader bf = new BufferedReader(f);
        String line;
        while ((line = bf.readLine()) != null) {
            String[] ff = line.split("~");
            if (ff[0].equals(subject)) {
                userFolder = ff[1];
                bf.close();
                return true;
            }
        }
        bf.close();
        return false;
    }

    public Boolean send(String sender1, String subject1, String body, int priority1, LinkedList names, LinkedList attachments1, boolean attach) {
        Mail m = new Mail();
        m.sender = sender1;
        m.msg = body;
        m.priority = priority1;
        m.subject = subject1;
        m.attachments = attachments1;
        m.attachBool = attach;
        Helper h = new Helper();
        m.msgDate = h.todayDate();
        Qlinked temp = new Qlinked();
        int i = 0;
        while (i != names.size()) {
            temp.enqueue((String) names.get(i));
            // names.remove(i);
            i++;
        }
        m.receiver = temp;
        compose(m);
        return true;
    }

    public boolean compose(IMail email) {
        String recieverName = "";
        File usersDir = new File("server\\users");
        boolean userExists = false;
        Mail newMail = (Mail) email;
        Date d = new Date();
        newMail.subDate = newMail.subject + d.getTime();
        while (!newMail.receiver.isEmpty()) {

            String receiver = (String) newMail.receiver.dequeue();
            FileReader f = null;
            try {
                f = new FileReader(new File(usersDir.toString() + "\\users.txt"));
                BufferedReader bfRead = new BufferedReader(f);
                String usersReader;
                while ((usersReader = bfRead.readLine()) != null) {
                    String[] user = usersReader.split("~");
                    if (user[2].equalsIgnoreCase(receiver)) {
                        recieverName = user[0];
                        userExists = true;
                        break;
                    }
                }
                if (!userExists) {
                    return false;
                } else {

                    boolean hasAttachments = false;
                    if (newMail.attachments != null && !newMail.attachments.isEmpty()) {
                        hasAttachments = true;
                    }
                    newMessage(newMail.subject, newMail.sender, recieverName, hasAttachments, newMail.msg, newMail.msgDate, newMail.priority, newMail.starred, newMail.subDate);
                    for (int i = 0; i < newMail.attachments.size(); i++) {
                        copyAttachment(newMail.subDate, newMail.sender, recieverName, (File) newMail.attachments.get(i));

                    }
                }

            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(App.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        return true;
    }

    public void newMessage(String subject, String from, String to, boolean attachments, String msg, String date, int priority, int starred, String subDate) throws IOException {
        String mailDir = "server\\users\\";
        String fromDir = mailDir + from + "\\";
        String toDir = mailDir + to + "\\";
        new File(fromDir + "sent\\" + subDate).mkdirs();
        new File(toDir + "user folders\\" + userFolder + "\\" + subDate).mkdirs();

        BufferedWriter writeTo = new BufferedWriter(new FileWriter(new File(toDir + "user folders\\" + userFolder + "\\" + subDate + "\\msg.txt")));
        BufferedWriter writeFrom = new BufferedWriter(new FileWriter(new File(fromDir + "sent\\" + subDate + "\\msg.txt")));
        writeFrom.write(msg);
        writeTo.write(msg);
        writeTo.close();
        writeFrom.close();
        writeFrom = new BufferedWriter(new FileWriter(new File(fromDir + "sent\\" + "\\sent.txt"), true));
        writeFrom.append(subject + "~" + from + "~" + to + "~" + attachments + "~" + priority + "~" + date + "~" + starred + "~" + subDate);
        writeFrom.newLine();
        writeFrom.close();
        writeTo = new BufferedWriter(new FileWriter(new File(toDir + "user folders\\" + userFolder + "\\" + userFolder + ".txt"), true));
        writeTo.write(subject + "~" + from + "~" + to + "~" + attachments + "~" + priority + "~" + date + "~" + starred + "~" + subDate);
        writeTo.newLine();
        writeTo.close();

    }

    public void copyAttachment(String subject, String from, String to, File attachments) throws IOException {
        String mailDir = "server\\users\\";
        String fromDir = mailDir + from + "\\";
        String toDir = mailDir + to + "\\";
        Files.copy(attachments.toPath(), new File(toDir + "user folders\\" + userFolder + "\\" + subject + "\\" + attachments.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(attachments.toPath(), new File(fromDir + "sent\\" + subject + "\\" + attachments.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

}
