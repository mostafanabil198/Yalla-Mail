/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.text.SimpleDateFormat;
import java.util.Date;
import linkedList.cs45_cs23.LinkedList;

/**
 *
 * @author arabtech
 */
public class Helper {

    public void newUser(String UserName) throws IOException {
        String mailDir = "server\\users\\";
        String userDir = mailDir + UserName;
        new File(userDir).mkdirs();
        new File(userDir + "\\sent").mkdirs();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(userDir + "\\sent\\sent.txt")));
        new File(userDir + "\\inbox").mkdirs();
        bw = new BufferedWriter(new FileWriter(new File(userDir + "\\inbox\\inbox.txt")));
        new File(userDir + "\\draft").mkdirs();
        bw = new BufferedWriter(new FileWriter(new File(userDir + "\\draft\\draft.txt")));
        new File(userDir + "\\trash").mkdirs();
        bw = new BufferedWriter(new FileWriter(new File(userDir + "\\trash\\trash.txt")));
        new File(userDir + "\\user folders").mkdirs();
        new File(userDir + "\\contacts").mkdirs();
        bw = new BufferedWriter(new FileWriter(new File(userDir + "\\contacts\\contacts.txt")));
        bw = new BufferedWriter(new FileWriter(new File(userDir + "\\contacts\\view contacts.txt")));
        new File(userDir + "\\viewingOptions").mkdirs();
        File pic = new File("img\\pp.jpg");
        File pic1 = new File("server\\users\\" + UserName + "\\pp.jpg");
        bw = new BufferedWriter(new FileWriter(new File(userDir + "\\filter.txt")));
        Files.copy(pic.toPath(), pic1.toPath(), StandardCopyOption.REPLACE_EXISTING);
        bw = new BufferedWriter(new FileWriter(new File(userDir + "\\viewingOptions\\viewingOptions.txt")));
        bw.close();
    }

    public void newMessage(String subject, String from, String to, boolean attachments, String msg, String date, int priority, int starred, String subDate) throws IOException {
        String mailDir = "server\\users\\";
        String fromDir = mailDir + from + "\\";
        String toDir = mailDir + to + "\\";
        new File(fromDir + "sent\\" + subDate).mkdirs();
        new File(toDir + "inbox\\" + subDate).mkdirs();

        BufferedWriter writeTo = new BufferedWriter(new FileWriter(new File(toDir + "inbox\\" + subDate + "\\msg.txt")));
        BufferedWriter writeFrom = new BufferedWriter(new FileWriter(new File(fromDir + "sent\\" + subDate + "\\msg.txt")));
        writeFrom.write(msg);
        writeTo.write(msg);
        writeTo.close();
        writeFrom.close();
        writeFrom = new BufferedWriter(new FileWriter(new File(fromDir + "sent\\" + "\\sent.txt"), true));
        writeFrom.append(subject + "~" + from + "~" + to + "~" + attachments + "~" + priority + "~" + date + "~" + starred + "~" + subDate);
        writeFrom.newLine();
        writeFrom.close();
        writeTo = new BufferedWriter(new FileWriter(new File(toDir + "inbox\\" + "\\inbox.txt"), true));
        writeTo.write(subject + "~" + from + "~" + to + "~" + attachments + "~" + priority + "~" + date + "~" + starred + "~" + subDate);
        writeTo.newLine();
        writeTo.close();

    }

    public void copyAttachment(String subject, String from, String to, File attachments) throws IOException {
        String mailDir = "server\\users\\";
        String fromDir = mailDir + from + "\\";
        String toDir = mailDir + to + "\\";
        Files.copy(attachments.toPath(), new File(toDir + "inbox\\" + subject + "\\" + attachments.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);
        Files.copy(attachments.toPath(), new File(fromDir + "sent\\" + subject + "\\" + attachments.getName()).toPath(), StandardCopyOption.REPLACE_EXISTING);

    }

    public void moveTrash(String userName, String subject) throws IOException {
        String mailDir = "server\\users\\";
        String userDir = mailDir + userName + "\\";
        File from = new File(userDir + "sent\\" + subject);
        File to = new File(userDir + "trash\\" + subject);
        to.mkdirs();
        for (File f : from.listFiles()) {
            String toPath = to.toString() + "\\" + f.getName();
            Files.move(f.toPath(), Paths.get(toPath), StandardCopyOption.REPLACE_EXISTING);
        }
        from.delete();
    }

    public void copyFolder(File source, File destination) throws IOException {
        if (source.isDirectory()) {
            if (!destination.exists()) {
                destination.mkdirs();
            }

            String files[] = source.list();

            for (int i = 0; i < files.length; i++) {
                File srcFile = new File(source, files[i]);
                File destFile = new File(destination, files[i]);

                copyFolder(srcFile, destFile);
            }
        } else {

            Files.copy(source.toPath(), destination.toPath(), StandardCopyOption.REPLACE_EXISTING);

        }
    }

    public void deleteFolder(File f) {
        // TODO Auto-generated method stub
        File[] contents = f.listFiles();
        if (contents != null) {
            for (int i = 0; i < contents.length; i++) {
                contents[i].delete();
            }
        }
        f.delete();
    }

    public String todayDate() {
        Date d = new Date();
        SimpleDateFormat ft = new SimpleDateFormat("yyyy-MM-dd");
        return ft.format(d);
    }

    public void moveToDraft(String subject, String from, String to, boolean attachments, String msg, String date, int priority, int starred, LinkedList attachments1) throws IOException {
        Date d = new Date();
        String subDate = subject + d.getTime();
        String mailDir = "server\\users\\";
        String fromDir = mailDir + from + "\\";
        new File(fromDir + "draft\\" + subDate).mkdirs();

        BufferedWriter writeFrom = new BufferedWriter(new FileWriter(new File(fromDir + "draft\\" + subDate + "\\msg.txt")));
        writeFrom.write(msg);
        writeFrom.close();
        writeFrom = new BufferedWriter(new FileWriter(new File(fromDir + "draft\\" + "\\draft.txt"), true));
        
        writeFrom.append(subject + "~" + from + "~" + to + "~" + attachments + "~" + priority + "~" + date + "~" + Integer.valueOf(starred) + "~" + subDate);
        writeFrom.newLine();
        writeFrom.close();
        int z = 0;
        while (z < attachments1.size()) {
            copyFolder((File) attachments1.get(0), new File(fromDir + "draft\\" + subDate + "\\" + ((File) attachments1.get(z)).getName()));
            z++;
        }

    }

    public void deleteFiles(File source) {
        if (source.isDirectory()) {
            String files[] = source.list();

            for (int i = 0; i < files.length; i++) {
                File srcFile = new File(source, files[i]);

                deleteFiles(srcFile);
            }
        } else {
            source.delete();
        }

    }
}
