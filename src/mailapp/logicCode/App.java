/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import mailapp.interfaces.IFilter;
import mailapp.interfaces.IContact;
import mailapp.interfaces.IFolder;
import mailapp.interfaces.ISort;
import mailapp.interfaces.IMail;
import mailapp.interfaces.IApp;
import binarySearch.BinarySearch;
import linkedList.ILinkedList;
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
import java.util.logging.Level;
import java.util.logging.Logger;
import linkedList.cs45_cs23.DLinkedList;
import mailapp.gui.Inbox;
import quickSort.Quicksort;
import quickSort.Quicksortdl;
import searchContains.SearchContains;
import stack.cs45.Stack;

/**
 *
 * @author arabtech
 */
public class App implements IApp {

    public DLinkedList list = new DLinkedList();

    @Override
    public boolean signin(String email, String password) {
        File usersDir = new File("server\\users");
        FileReader f = null;
        try {
            f = new FileReader(new File(usersDir.toString() + "\\users.txt"));
            BufferedReader bfRead = new BufferedReader(f);
            String usersReader;
            while ((usersReader = bfRead.readLine()) != null) {
                String[] user = usersReader.split("~");
                if (user[2].equals(email)) {
                    if (user[1].equals(password)) {
                        Inbox in = new Inbox();
                        in.user = user;
                        return true;
                    } else {
                        return false;
                    }
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean signup(IContact contact) {
        Contact newUser = (Contact) contact;
        String userName = newUser.name;
        String userPass = newUser.password;
        String userMail = newUser.email;
        String userQues = newUser.ques;
        String userAns = newUser.ans;
        Helper help = new Helper();
        String[] user1 = {userName, userPass, userMail, userQues, userAns};
        File usersDir = new File("server\\users");
        FileReader f = null;
        try {
            f = new FileReader(new File(usersDir.toString() + "\\users.txt"));
            BufferedReader bfRead = new BufferedReader(f);
            String usersReader;
            while ((usersReader = bfRead.readLine()) != null) {
                String[] user = usersReader.split("~");
                if (user[0].equals(userName) || user[2].equals(userMail)) {
                    return false;
                }
            }
            BufferedWriter write = new BufferedWriter(new FileWriter(new File(usersDir.toString() + "\\users.txt"), true));
            write.append(userName + "~" + userPass + "~" + userMail + "~" + userQues + "~" + userAns);
            write.newLine();
            write.close();
            help.newUser(userName);

        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        Inbox in;
        try {
            in = new Inbox();
            in.user = user1;
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return true;

    }

    @Override
    public void setViewingOptions(IFolder folder, IFilter filter, ISort sort) {
        DLinkedList allMails = new DLinkedList();
        Folder fold1 = (Folder) folder;
        if (!fold1.dir.contains("user folders")) {
            File mailsIndex = new File("server\\users\\" + ((Folder) folder).user
                    + "\\" + ((Folder) folder).dir + "\\" + ((Folder) folder).dir + ".txt");
            try {
                BufferedReader bf = new BufferedReader(new FileReader(mailsIndex));
                String line;
                while ((line = bf.readLine()) != null) {
                    String[] split = line.split("~");
                    Mail m = new Mail();
                    m.subject = split[0];
                    m.sender = split[1];
                    m.rec = split[2];
                    m.attachBool = Boolean.valueOf(split[3]);
                    m.attachbools = split[3];
                    m.priority = Integer.parseInt(split[4]);
                    m.msgDate = split[5];
                    m.starred = Integer.valueOf(split[6]);
                    Folder fold = new Folder();
                    fold.subject = "server\\users\\" + ((Folder) folder).user
                            + "\\" + ((Folder) folder).dir + "\\" + split[7];
                    m.msgFolder = fold;
                    m.subDate = split[7];
                    allMails.add(m);
                }
                bf.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String s1 = fold1.dir;
            String[] newDir = s1.split("\\\\");
            File mailsIndex = new File("server\\users\\" + ((Folder) folder).user
                    + "\\" + "user folders\\" + newDir[1] + "\\" + newDir[1] + ".txt");
            try {
                BufferedReader bf = new BufferedReader(new FileReader(mailsIndex));
                String line;
                while ((line = bf.readLine()) != null) {
                    String[] split = line.split("~");
                    Mail m = new Mail();
                    m.subject = split[0];
                    m.sender = split[1];
                    m.rec = split[2];
                    m.attachBool = Boolean.valueOf(split[3]);
                    m.attachbools = split[3];
                    m.priority = Integer.parseInt(split[4]);
                    m.msgDate = split[5];
                    m.starred = Integer.valueOf(split[6]);
                    Folder fold = new Folder();
                    fold.subject = "server\\users\\" + ((Folder) folder).user
                            + "\\" + ((Folder) folder).dir + "\\" + split[7];
                    m.msgFolder = fold;
                    m.subDate = split[7];
                    allMails.add(m);
                }
                bf.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        Filter f = (Filter) filter;
        Filter s = (Filter) sort;
        BinarySearch bS = new BinarySearch();
        Quicksortdl qS = new Quicksortdl();
        if (filter != null || sort != null) {
            if (filter != null) {

                if (f.subject) {
                    try {
                        qS.quicksort(allMails, true, false, false, false, false, true, false, false);
                        allMails = bS.searchSubject(allMails, f.subject1);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (f.sender) {
                    try {
                        qS.quicksort(allMails, true, false, false, true, false, false, false, false);
                        allMails = bS.searchSender(allMails, f.sender1);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (f.reciever) {
                    try {
                        qS.quicksort(allMails, true, false, false, false, true, false, false, false);
                        allMails = bS.searchrec(allMails, f.reciever1);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (f.priority) {
                    try {
                        qS.quicksort(allMails, false, false, true, false, false, false, false, false);
                        allMails = bS.search(allMails, f.priority1);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (f.date) {
                    try {
                        qS.quicksort(allMails, false, true, false, false, false, false, false, false);
                        allMails = bS.comparedate(allMails, f.date1);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (f.attach) {
                    try {
                        qS.quicksort(allMails, true, false, false, false, false, false, true, false);
                        allMails = bS.searchattach(allMails, f.attach1);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
            if (sort != null) {

                if (s.subject) {
                    try {
                        qS.quicksort(allMails, true, false, false, false, false, true, false, false);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (s.sender) {
                    try {
                        qS.quicksort(allMails, true, false, false, true, false, false, false, false);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (s.reciever) {
                    try {
                        qS.quicksort(allMails, true, false, false, false, true, false, false, false);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (s.priority) {
                    try {
                        qS.quicksort(allMails, false, false, true, false, false, false, false, false);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (s.attach) {
                    try {
                        qS.quicksort(allMails, true, false, false, false, false, false, true, false);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }
                } else if (s.date) {
                    try {
                        qS.quicksort(allMails, false, true, false, false, false, false, false, false);
                    } catch (ParseException ex) {
                        Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                    }

                }
            }

            if (!s.ascending) {
                Stack st = new Stack();
                while (!allMails.isEmpty()) {
                    st.push(allMails.get(0));
                    allMails.remove(0);
                }
                while (!st.isEmpty()) {
                    allMails.add(st.pop());
                }
            }
        }
        //mailsIndex.delete();
        list = allMails;
        File viewingOptionsIndex = new File("server\\users\\" + ((Folder) folder).user
                + "\\" + "viewingOptions\\" + "viewingOptions.txt");

        viewingOptionsIndex.delete();

        try {
            BufferedWriter bfW = new BufferedWriter(new FileWriter(viewingOptionsIndex, true));
            for (int i = 0; i < allMails.size(); i++) {
                Mail addMail = (Mail) allMails.get(i);
                bfW.append(addMail.subject + "~");
                bfW.append(addMail.sender + "~");
                bfW.append(addMail.rec + "~");
                bfW.append(addMail.attachBool + "~");
                bfW.append(addMail.priority + "~");
                bfW.append(addMail.msgDate + "~");
                bfW.append(String.valueOf(addMail.starred));
                bfW.append("~" + addMail.subDate);
                bfW.newLine();

            }
            bfW.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    public void setViewingOptionsTyped(IFolder folder, IFilter filter, ISort sort) throws IOException {
        DLinkedList allMails = new DLinkedList();
        Folder fold1 = (Folder) folder;
        if (!fold1.dir.contains("user folders")) {
            File mailsIndex = new File("server\\users\\" + ((Folder) folder).user
                    + "\\" + ((Folder) folder).dir + "\\" + ((Folder) folder).dir + ".txt");
            try {
                BufferedReader bf = new BufferedReader(new FileReader(mailsIndex));
                String line;
                while ((line = bf.readLine()) != null) {
                    String[] split = line.split("~");
                    Mail m = new Mail();
                    m.subject = split[0];
                    m.sender = split[1];
                    m.rec = split[2];
                    m.attachBool = Boolean.valueOf(split[3]);
                    m.attachbools = split[3];
                    m.priority = Integer.parseInt(split[4]);
                    m.msgDate = split[5];
                    Folder fold = new Folder();
                    fold.subject = "server\\users\\" + ((Folder) folder).user
                            + "\\" + ((Folder) folder).dir + "\\" + split[7];
                    m.msgFolder = fold;
                    m.subDate = split[7];
                    allMails.add(m);
                }
                bf.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(App.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            String s1 = fold1.dir;
            String[] newDir = s1.split("\\\\");
            File mailsIndex = new File("server\\users\\" + ((Folder) folder).user
                    + "\\" + "user folders\\" + newDir[1] + "\\" + newDir[1] + ".txt");
            try {
                BufferedReader bf = new BufferedReader(new FileReader(mailsIndex));
                String line;
                while ((line = bf.readLine()) != null) {
                    String[] split = line.split("~");
                    Mail m = new Mail();
                    m.subject = split[0];
                    m.sender = split[1];
                    m.rec = split[2];
                    m.attachBool = Boolean.valueOf(split[3]);
                    m.attachbools = split[3];
                    m.priority = Integer.parseInt(split[4]);
                    m.msgDate = split[5];
                    m.starred = Integer.valueOf(split[6]);
                    Folder fold = new Folder();
                    fold.subject = "server\\users\\" + ((Folder) folder).user
                            + "\\" + ((Folder) folder).dir + "\\" + split[7];
                    m.msgFolder = fold;
                    m.subDate = split[7];
                    allMails.add(m);
                }
                bf.close();

            } catch (FileNotFoundException ex) {
                Logger.getLogger(App.class
                        .getName()).log(Level.SEVERE, null, ex);
            } catch (IOException ex) {
                Logger.getLogger(App.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (filter != null && sort != null) {

            Filter f = (Filter) filter;
            Filter s = (Filter) sort;
            BinarySearch bS = new BinarySearch();
            Quicksortdl qS = new Quicksortdl();
            if (f.subject) {
                try {
                    qS.quicksort(allMails, true, false, false, false, false, true, false, false);
                    //allMails = bS.searchSubject(allMails, f.subject1);
                    SearchContains se = new SearchContains();
                    allMails = se.searchSubject(allMails, f.subject1);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.attach) {
                try {
                    qS.quicksort(allMails, true, false, false, false, false, false, true, false);
                    //allMails = bS.searchSubject(allMails, f.subject1);
                    SearchContains se = new SearchContains();
                    allMails = se.searchattach(allMails, f.attach1);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.sender) {
                try {
                    qS.quicksort(allMails, true, false, false, true, false, false, false, false);
                    //allMails = bS.searchSender(allMails, f.sender1);
                    SearchContains se = new SearchContains();
                    allMails = se.searchSender(allMails, f.sender1);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.reciever) {
                try {
                    qS.quicksort(allMails, true, false, false, false, true, false, false, false);
                    //allMails = bS.searchrec(allMails, f.reciever1);
                    SearchContains se = new SearchContains();
                    allMails = se.searchRec(allMails, f.reciever1);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.msg) {
                try {
                    qS.quicksort(allMails, true, false, false, false, true, false, false, false);
                    //allMails = bS.searchrec(allMails, f.reciever1);
                    SearchContains se = new SearchContains();
                    allMails = se.searchMsgs(allMails, f.msg1);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.priority) {
                try {
                    qS.quicksort(allMails, false, false, true, false, false, false, false, false);
                    allMails = bS.search(allMails, f.priority1);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.date) {
                try {
                    qS.quicksort(allMails, false, true, false, false, false, false, false, false);
                    //allMails = bS.comparedate(allMails, f.date1);
                    SearchContains se = new SearchContains();
                    allMails = se.searchDate(allMails, f.date1);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
            if (s.subject) {
                try {
                    qS.quicksort(allMails, true, false, false, false, false, true, false, false);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (s.sender) {
                try {
                    qS.quicksort(allMails, true, false, false, true, false, false, false, false);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (s.reciever) {
                try {
                    qS.quicksort(allMails, true, false, false, false, true, false, false, false);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (s.attach) {
                try {
                    qS.quicksort(allMails, true, false, false, false, false, false, true, false);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (s.priority) {
                try {
                    qS.quicksort(allMails, false, false, true, false, false, false, false, false);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else if (s.date) {
                try {
                    qS.quicksort(allMails, false, true, false, false, false, false, false, false);

                } catch (ParseException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }

            }

            if (!s.ascending) {
                Stack st = new Stack();
                while (!allMails.isEmpty()) {
                    st.push(allMails.get(0));
                    allMails.remove(0);
                }
                while (!st.isEmpty()) {
                    allMails.add(st.pop());
                }
            }
        }
        //mailsIndex.delete();
        list = allMails;
        File viewingOptionsIndex = new File("server\\users\\" + ((Folder) folder).user
                + "\\" + "viewingOptions\\" + "viewingOptions.txt");
        viewingOptionsIndex.delete();
        try {
            BufferedWriter bfW = new BufferedWriter(new FileWriter(viewingOptionsIndex, true));
            for (int i = 0; i < allMails.size(); i++) {
                Mail addMail = (Mail) allMails.get(i);
                bfW.append(addMail.subject + "~");
                bfW.append(addMail.sender + "~");
                bfW.append(addMail.rec + "~");
                bfW.append(addMail.attachBool + "~");
                bfW.append(addMail.priority + "~");
                bfW.append(addMail.msgDate + "~");
                bfW.append(String.valueOf(addMail.starred));
                bfW.append("~" + addMail.subDate);
                bfW.newLine();

            }
            bfW.close();

        } catch (IOException ex) {
            Logger.getLogger(App.class
                    .getName()).log(Level.SEVERE, null, ex);
        }

    }

    @Override
    public IMail[] listEmails(int page) {
        int start = (page - 1) * 10;
        int end = start + 10;
        // Mail m = new Mail();
        Mail[] mailarr = new Mail[10];
        int count = 0;
        for (int i = start; i < end; i++) {
            if (i < list.size()) {
                mailarr[count] = (Mail) list.get(i);
            } else {
                mailarr[count] = null;
            }
            count++;
        }
        return mailarr;
    }

    public boolean checkPage(int page) {
        int start = (page - 1) * 10;
        if (start >= list.size() || page <= 0) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public void deleteEmails(ILinkedList mails) {
        for (int i = 0; i < mails.size(); i++) {
            MoveClass from = (MoveClass) mails.get(i);
            Helper help = new Helper();
            if (!from.dir.contains("user folders")) {
                File fromFile = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + from.subDate);
                File toFile = new File("server\\users" + "\\" + from.user + "\\" + "trash\\" + from.subDate);
                try {
                    help.copyFolder(fromFile, toFile);

                } catch (IOException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                help.deleteFolder(fromFile);
                File fromIndex = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + from.dir + ".txt");
                File toIndex = new File("server\\users\\" + from.user + "\\trash\\trash.txt");
                File tempIndex = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + "temp.txt");
                try {
                    BufferedReader bf = new BufferedReader(new FileReader(fromIndex));
                    BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));

                    String line;
                    while ((line = bf.readLine()) != null) {
                        String[] msgDetails = line.split("~");
                        if (msgDetails[7].equals(from.subDate)) {
                            BufferedWriter bfW = new BufferedWriter(new FileWriter(toIndex, true));
                            bfW.append(line);
                            bfW.newLine();
                            bfW.close();
                        } else {
                            bftemp.append(line);
                            bftemp.newLine();
                        }
                    }
                    bftemp.close();
                    bf.close();
                    fromIndex.delete();
                    tempIndex.renameTo(fromIndex);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String s1 = from.dir;
                String[] newDir = s1.split("\\\\");
                File fromFile = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + from.subDate);
                File toFile = new File("server\\users" + "\\" + from.user + "\\" + "trash\\" + from.subDate);
                try {
                    help.copyFolder(fromFile, toFile);

                } catch (IOException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
                help.deleteFolder(fromFile);
                File fromIndex = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + newDir[1] + ".txt");
                File toIndex = new File("server\\users\\" + from.user + "\\trash\\trash.txt");
                File tempIndex = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + "temp.txt");
                try {
                    BufferedReader bf = new BufferedReader(new FileReader(fromIndex));
                    BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));

                    String line;
                    while ((line = bf.readLine()) != null) {
                        String[] msgDetails = line.split("~");
                        if (msgDetails[7].equals(from.subDate)) {
                            BufferedWriter bfW = new BufferedWriter(new FileWriter(toIndex, true));
                            bfW.append(line);
                            bfW.newLine();
                            bfW.close();
                        } else {
                            bftemp.append(line);
                            bftemp.newLine();
                        }
                    }
                    bftemp.close();
                    bf.close();
                    fromIndex.delete();
                    tempIndex.renameTo(fromIndex);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
    public void moveEmails(ILinkedList mails, IFolder des) {
        for (int i = 0; i < mails.size(); i++) {
            MoveClass from = (MoveClass) mails.get(i);
            Helper help = new Helper();
            Folder to = (Folder) des;
            File fromFile = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + from.subDate);
            File toFile = new File("server\\users" + "\\" + from.user + "\\user folders\\" + to.dir + "\\" + from.subDate);
            File tempIndex = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + "temp.txt");
            try {
                help.copyFolder(fromFile, toFile);

            } catch (IOException ex) {
                Logger.getLogger(App.class
                        .getName()).log(Level.SEVERE, null, ex);
            }
            help.deleteFolder(fromFile);
            if (!from.dir.contains("user folders")) {
                File fromIndex = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + from.dir + ".txt");

                File toIndex = new File("server\\users\\" + from.user + "\\user folders\\" + to.dir + "\\" + to.dir + ".txt");
                try {
                    BufferedReader bf = new BufferedReader(new FileReader(fromIndex));
                    BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));

                    String line;
                    while ((line = bf.readLine()) != null) {
                        String[] msgDetails = line.split("~");
                        if (msgDetails[7].equals(from.subDate)) {
                            BufferedWriter bfW = new BufferedWriter(new FileWriter(toIndex, true));
                            bfW.append(line);
                            bfW.newLine();
                            bfW.close();
                        } else {
                            bftemp.append(line);
                            bftemp.newLine();
                        }
                    }
                    bftemp.close();
                    bf.close();
                    fromIndex.delete();
                    tempIndex.renameTo(fromIndex);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                String[] newDir = from.dir.split("\\\\");
                File fromIndex = new File("server\\users\\" + from.user + "\\" + from.dir + "\\" + newDir[1] + ".txt");
                File toIndex = new File("server\\users\\" + from.user + "\\user folders\\" + to.dir + "\\" + to.dir + ".txt");
                try {
                    BufferedReader bf = new BufferedReader(new FileReader(fromIndex));
                    BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));

                    String line;
                    while ((line = bf.readLine()) != null) {
                        String[] msgDetails = line.split("~");
                        if (msgDetails[7].equals(from.subDate)) {
                            BufferedWriter bfW = new BufferedWriter(new FileWriter(toIndex, true));
                            bfW.append(line);
                            bfW.newLine();
                            bfW.close();
                        } else {
                            bftemp.append(line);
                            bftemp.newLine();
                        }
                    }
                    bftemp.close();

                    bf.close();
                    fromIndex.delete();
                    tempIndex.renameTo(fromIndex);

                } catch (FileNotFoundException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(App.class
                            .getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    @Override
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
                    Helper help = new Helper();
                    boolean hasAttachments = false;
                    if (newMail.attachments != null && !newMail.attachments.isEmpty()) {
                        hasAttachments = true;
                    }
                    help.newMessage(newMail.subject, newMail.sender, recieverName, hasAttachments, newMail.msg, newMail.msgDate, newMail.priority, newMail.starred, newMail.subDate);
                    for (int i = 0; i < newMail.attachments.size(); i++) {
                        help.copyAttachment(newMail.subDate, newMail.sender, recieverName, (File) newMail.attachments.get(i));

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

}
