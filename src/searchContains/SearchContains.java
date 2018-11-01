/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package searchContains;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import linkedList.cs45_cs23.DLinkedList;
import mailapp.logicCode.Mail;
import mailapp.logicCode.UserContacts;

/**
 *
 * @author arabtech
 */
public class SearchContains {

    public DLinkedList searchContactName(DLinkedList list, String target) {
        DLinkedList found = new DLinkedList();
        for (int i = 0; i < list.size(); i++) {
            UserContacts contact = (UserContacts) list.get(i);
            if (contact.userName.contains(target)) {
                found.add(contact);
            }
        }
        return found;
    }

    public DLinkedList searchContactPhone(DLinkedList list, String target) {
        DLinkedList found = new DLinkedList();
        for (int i = 0; i < list.size(); i++) {
            UserContacts contact = (UserContacts) list.get(i);
            if (contact.phoneNumber.contains(target)) {
                found.add(contact);
            }
        }
        return found;
    }

    public DLinkedList searchSubject(DLinkedList list, String target) {
        DLinkedList found = new DLinkedList();
        for (int i = 0; i < list.size(); i++) {
            Mail mail = (Mail) list.get(i);
            if (mail.subject.contains(target)) {
                found.add(mail);
            }
        }
        return found;
    }

    public DLinkedList searchSender(DLinkedList list, String target) {
        DLinkedList found = new DLinkedList();
        for (int i = 0; i < list.size(); i++) {
            Mail mail = (Mail) list.get(i);
            if (mail.sender.contains(target)) {
                found.add(mail);
            }
        }
        return found;
    }

    public DLinkedList searchRec(DLinkedList list, String target) {
        DLinkedList found = new DLinkedList();
        for (int i = 0; i < list.size(); i++) {
            Mail mail = (Mail) list.get(i);
            if (mail.rec.contains(target)) {
                found.add(mail);
            }
        }
        return found;
    }

    public DLinkedList searchDate(DLinkedList list, String target) {
        DLinkedList found = new DLinkedList();
        for (int i = 0; i < list.size(); i++) {
            Mail mail = (Mail) list.get(i);
            if (mail.msgDate.contains(target)) {
                found.add(mail);
            }
        }
        return found;
    }

    public DLinkedList searchattach(DLinkedList list, String target) {
        DLinkedList found = new DLinkedList();
        for (int i = 0; i < list.size(); i++) {
            Mail mail = (Mail) list.get(i);
            if (mail.attachbools.contains(target)) {
                found.add(mail);
            }
        }
        return found;
    }

    public DLinkedList searchMsgs(DLinkedList list, String target) throws FileNotFoundException, IOException {
        DLinkedList found = new DLinkedList();
        for (int i = 0; i < list.size(); i++) {
            Mail mail = (Mail) list.get(i);
            File f = new File(mail.msgFolder.subject + "\\msg.txt");
            BufferedReader bf = new BufferedReader(new FileReader(f));
            String msg = "";
            String line;
            while ((line = bf.readLine()) != null) {
                msg += line + "\n";
            }
            bf.close();
            if (msg.contains(target)) {
                found.add(mail);
            }
        }
        return found;
    }

}
