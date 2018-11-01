/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import binarySearch.BinarySearch;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import linkedList.cs45_cs23.DLinkedList;
import linkedList.cs45_cs23.LinkedList;
import mailapp.interfaces.IFilter;
import mailapp.interfaces.IFolder;
import mailapp.interfaces.IMail;
import mailapp.interfaces.ISort;
import static mailapp.logicCode.ViewMessages.SelectAll;
import quickSort.Quicksortdl;
import searchContains.SearchContains;
import stack.cs45.Stack;

/**
 *
 * @author محمد
 */
public class UserContactsFunctions {

    DLinkedList list;

    public boolean CheckUserName(String currentUser, String userName) throws FileNotFoundException, IOException {
        boolean contactExists = true;
        FileReader f = null;
        File usersDir = new File("server\\users\\" + currentUser + "\\contacts");
        f = new FileReader(new File(usersDir.toString() + "\\contacts.txt"));
        BufferedReader bfRead = new BufferedReader(f);
        String contactReader;
        while ((contactReader = bfRead.readLine()) != null) {
            String[] user = contactReader.split("~");
            if (user[0].equalsIgnoreCase(userName)) {
                contactExists = false;
                break;
            }
        }
        bfRead.close();
        return contactExists;
    }

    public void addNewContact(String currentUser, String userName, String PhoneNum, LinkedList emails) throws FileNotFoundException, IOException {
        File usersDir = new File("server\\users\\" + currentUser + "\\contacts");
        //File newContact = new File(usersDir + "\\" + userName);
        //newContact.mkdirs();
        File f = new File(usersDir.toString() + "\\contacts.txt");
        BufferedWriter bfW = new BufferedWriter(new FileWriter(f, true));
        String emailsStr = "";
        while (!emails.isEmpty()) {
            emailsStr += "~" + (String) emails.get(0);
            emails.remove(0);
        }
        bfW.append(userName + "~" + PhoneNum + emailsStr);
        bfW.newLine();
        bfW.close();
    }

    public Boolean checkEmail(String email) throws FileNotFoundException, IOException {
        boolean userExists = false;
        FileReader f = null;
        File usersDir = new File("server\\users");
        f = new FileReader(new File(usersDir.toString() + "\\users.txt"));
        BufferedReader bfRead = new BufferedReader(f);
        String usersReader;
        while ((usersReader = bfRead.readLine()) != null) {
            String[] user = usersReader.split("~");
            if (user[2].equalsIgnoreCase(email)) {
                userExists = true;
                break;
            }
        }
        bfRead.close();
        return userExists;
    }

    public void deleteContacts(String currentUser, LinkedList indexes, UserContacts[] contacts) throws FileNotFoundException, IOException {
        while (!indexes.isEmpty()) {
            UserContacts contactToDelete = contacts[(int) indexes.get(0)];
            indexes.remove(0);
            BufferedReader bf = new BufferedReader(new FileReader(new File("server\\users\\" + currentUser + "\\contacts\\contacts.txt")));
            BufferedWriter bfw = new BufferedWriter(new FileWriter(new File("server\\users\\" + currentUser + "\\contacts\\contacts1.txt"), true));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split("~");
                if (!split[0].equalsIgnoreCase(contactToDelete.userName)) {
                    bfw.append(line);
                    bfw.newLine();
                }
            }
            bf.close();
            bfw.close();
            (new File("server\\users\\" + currentUser + "\\contacts\\contacts.txt")).delete();
            (new File("server\\users\\" + currentUser + "\\contacts\\contacts1.txt")).renameTo(new File("server\\users\\" + currentUser + "\\contacts\\contacts.txt"));
        }
    }

    public void editContact(String currentUser, UserContacts newData, String oldContactName) throws FileNotFoundException, IOException {
        BufferedReader bf = new BufferedReader(new FileReader(new File("server\\users\\" + currentUser + "\\contacts\\contacts.txt")));
        BufferedWriter bfw = new BufferedWriter(new FileWriter(new File("server\\users\\" + currentUser + "\\contacts\\contacts1.txt"), true));
        String line;
        while ((line = bf.readLine()) != null) {
            String[] split = line.split("~");
            if (!split[0].equalsIgnoreCase(oldContactName)) {
                bfw.append(line);
                bfw.newLine();
            } else {
                String emailsStr = "";
                while (!newData.emails.isEmpty()) {
                    emailsStr += "~" + (String) newData.emails.get(0);
                    newData.emails.remove(0);
                }
                bfw.append(newData.userName + "~" + newData.phoneNumber + emailsStr);
                bfw.newLine();
            }
        }
        bf.close();
        bfw.close();
        (new File("server\\users\\" + currentUser + "\\contacts\\contacts.txt")).delete();
        (new File("server\\users\\" + currentUser + "\\contacts\\contacts1.txt")).renameTo(new File("server\\users\\" + currentUser + "\\contacts\\contacts.txt"));
    }

    public void addEmails(LinkedList names, String email) {
        names.add((String) email);
    }

    public String showNames(LinkedList names) {
        String show = "";
        int i = 0;
        while (i != names.size()) {
            show += (i + 1) + "- " + (String) names.get(i) + "\n";
            i++;
        }
        return show;
    }

    public boolean removeName(LinkedList names, int index) {
        if (index - 1 >= names.size() || index - 1 < 0) {
            return false;
        } else {
            names.remove(index - 1);
            return true;
        }
    }

    public void setViewingOptions(String currentUser, IFilter filter, ISort sort, boolean page) {
        DLinkedList allContacts = new DLinkedList();
        File contactsIndex;
        if (!page) {
            contactsIndex = new File("server\\users\\" + currentUser
                    + "\\contacts\\contacts.txt");
        } else {
            contactsIndex = new File("server\\users\\" + currentUser
                    + "\\contacts\\view contacts.txt");
        }
        try {
            BufferedReader bf = new BufferedReader(new FileReader(contactsIndex));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split("~");
                UserContacts uc = new UserContacts();
                uc.userName = split[0];
                uc.phoneNumber = split[1];
                LinkedList list2 = new LinkedList();
                for (int i = 2; i < split.length; i++) {
                    list2.add((String) split[i]);
                }
                uc.emails = list2;
                allContacts.add(uc);
            }
            bf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        Filter f = (Filter) filter;
        Filter s = (Filter) sort;
        BinarySearch bS = new BinarySearch();
        SearchContains search = new SearchContains();
        Quicksortdl qS = new Quicksortdl();
        if (filter != null) {

            if (f.contactName) {
                try {
                    qS.quicksortContacts(allContacts, true, false);
                    allContacts = bS.searchContactName(allContacts, f.contactName1);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.contactPhone) {
                try {
                    qS.quicksortContacts(allContacts, false, true);
                    allContacts = bS.searchContactPhone(allContacts, f.contactPhone1);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (sort != null) {
            if (s.contactName) {
                try {
                    qS.quicksortContacts(allContacts, true, false);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (s.contactPhone) {
                try {
                    qS.quicksortContacts(allContacts, false, true);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (!s.ascending) {
                Stack st = new Stack();
                while (!allContacts.isEmpty()) {
                    st.push(allContacts.get(0));
                    allContacts.remove(0);
                }
                while (!st.isEmpty()) {
                    allContacts.add(st.pop());
                }
            }
        }
        //mailsIndex.delete();
        list = allContacts;
        File viewingOptionsIndex = new File("server\\users\\" + currentUser + "\\contacts\\" + "view contacts.txt");
        viewingOptionsIndex.delete();
        try {
            BufferedWriter bfW = new BufferedWriter(new FileWriter(viewingOptionsIndex, true));
            for (int i = 0; i < allContacts.size(); i++) {
                UserContacts addContacts = (UserContacts) allContacts.get(i);
                String emailsStr = "";
                int z = 0;
                while (z != addContacts.emails.size()) {
                    emailsStr += "~" + (String) addContacts.emails.get(z);
                    z++;
                }
                bfW.append(addContacts.userName + "~" + addContacts.phoneNumber + emailsStr);
                bfW.newLine();
            }
            bfW.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public void setViewingOptionsTyped(String currentUser, IFilter filter, ISort sort, boolean page) {
        DLinkedList allContacts = new DLinkedList();
        File contactsIndex;
        if (!page) {
            contactsIndex = new File("server\\users\\" + currentUser
                    + "\\contacts\\contacts.txt");
        } else {
            contactsIndex = new File("server\\users\\" + currentUser
                    + "\\contacts\\view contacts.txt");
        }
        try {
            BufferedReader bf = new BufferedReader(new FileReader(contactsIndex));
            String line;
            while ((line = bf.readLine()) != null) {
                String[] split = line.split("~");
                UserContacts uc = new UserContacts();
                uc.userName = split[0];
                uc.phoneNumber = split[1];
                LinkedList list2 = new LinkedList();
                for (int i = 2; i < split.length; i++) {
                    list2.add((String) split[i]);
                }
                uc.emails = list2;
                allContacts.add(uc);
            }
            bf.close();
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        Filter f = (Filter) filter;
        Filter s = (Filter) sort;
        
        BinarySearch bS = new BinarySearch();
        Quicksortdl qS = new Quicksortdl();
        if (filter != null) {

            if (f.contactName) {
                try {
                    qS.quicksortContacts(allContacts, true, false);
                    //allContacts = bS.searchContactName(allContacts, f.contactName1);
                    SearchContains se = new SearchContains();
                    allContacts = se.searchContactName(allContacts, f.contactName1);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (f.contactPhone) {
                try {
                    qS.quicksortContacts(allContacts, false, true);
                    //allContacts = bS.searchContactPhone(allContacts, f.contactPhone1);
                    SearchContains se = new SearchContains();
                    allContacts = se.searchContactPhone(allContacts, f.contactPhone1);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        if (sort != null) {
            if (s.contactName) {
                try {
                    qS.quicksortContacts(allContacts, true, false);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else if (s.contactPhone) {
                try {
                    qS.quicksortContacts(allContacts, false, true);
                } catch (ParseException ex) {
                    Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
                }
            }

            if (!s.ascending) {
                Stack st = new Stack();
                while (!allContacts.isEmpty()) {
                    st.push(allContacts.get(0));
                    allContacts.remove(0);
                }
                while (!st.isEmpty()) {
                    allContacts.add(st.pop());
                }
            }
        }
        //mailsIndex.delete();
        list = allContacts;
        File viewingOptionsIndex = new File("server\\users\\" + currentUser + "\\contacts\\" + "view contacts.txt");
        viewingOptionsIndex.delete();
        try {
            BufferedWriter bfW = new BufferedWriter(new FileWriter(viewingOptionsIndex, true));
            for (int i = 0; i < allContacts.size(); i++) {
                UserContacts addContacts = (UserContacts) allContacts.get(i);
                String emailsStr = "";
                int z = 0;
                while (z != addContacts.emails.size()) {
                    emailsStr += "~" + (String) addContacts.emails.get(z);
                    z++;
                }
                bfW.append(addContacts.userName + "~" + addContacts.phoneNumber + emailsStr);
                bfW.newLine();
            }
            bfW.close();
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public UserContacts[] listContacts(int page) {
        int start = (page - 1) * 5;
        int end = start + 5;
        // Mail m = new Mail();
        //UserContacts[] contactArr = new UserContacts[5];
        UserContacts[] contactArr = new UserContacts[5];
        int count = 0;
        for (int i = start; i < end; i++) {
            if (i < list.size()) {
                contactArr[count] = (UserContacts) list.get(i);
            } else {
                contactArr[count] = null;
            }
            count++;
        }
        return contactArr;
    }

    public int showContactsInTable(UserContacts[] contacts, JTable table) {
        DefaultTableModel model = (DefaultTableModel) table.getModel();
        int numberofrows = 0;
        boolean check = false;
        //Inbox in = new Inbox();
       /* in.check1.setVisible(false);
         in.check2.setVisible(false);
         in.check3.setVisible(false);
         in.check4.setVisible(false);
         in.check5.setVisible(false);
         in.check6.setVisible(false);
         in.check7.setVisible(false);
         in.check8.setVisible(false);
         in.check9.setVisible(false);
         in.check10.setVisible(false);*/
        for (int i = 0; i < 5; i++) {
            model.setValueAt("", i, 0);
            model.setValueAt("", i, 1);
            model.setValueAt("", i, 2);
        }
        for (int i = 0; i < contacts.length && contacts[i] != null; i++) {
            model.setValueAt(contacts[i].userName, i, 0);
            model.setValueAt(contacts[i].phoneNumber, i, 1);
            model.setValueAt((String) ((LinkedList) (contacts[i].emails)).get(0), i, 2);
            numberofrows = i;
            check = true;

        }
        if (check) {
            SelectAll = numberofrows + 1;
            return numberofrows + 1;
        } else {
            SelectAll = 0;
            return 0;
        }
    }

    public boolean checkPage(int page) {
        int start = (page - 1) * 5;
        if (start >= list.size() || page <= 0) {
            return false;
        } else {
            return true;
        }
    }

    public boolean checkExist(LinkedList emails, String email) {
        int i = 0;
        while (i != emails.size()) {
            String x = (String) emails.get(i);
            if (x.equalsIgnoreCase(email)) {
                return false;
            }
            i++;
        }
        return true;
    }

}
