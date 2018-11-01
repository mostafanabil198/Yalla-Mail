/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.gui;

import AppPackage.AnimationClass;
import java.awt.Color;
import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.text.ParseException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.stage.WindowEvent;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.swing.table.DefaultTableCellRenderer;
import linkedList.cs45_cs23.LinkedList;
import mailapp.logicCode.App;
import mailapp.logicCode.ComposeGUI;
import mailapp.logicCode.Filter;
import mailapp.logicCode.FilterSubject;
import mailapp.logicCode.Folder;
import mailapp.logicCode.Helper;
import mailapp.logicCode.IndexToMail;
import mailapp.logicCode.Mail;
import mailapp.logicCode.NewFolder;
import mailapp.logicCode.Starred;
import mailapp.logicCode.SubtractDays;
import mailapp.logicCode.ViewMessages;
import mailapp.logicCode.showUserFolders;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;

/**
 *
 * @author محمد
 */
public class Inbox extends javax.swing.JFrame {

    public static String[] user;
    public static boolean foundPage = true;
    public String recentFolder = "inbox";
    int pageCounter = 1;
    int xMouse;
    String searchOn1 = "subject";
    String searchWord1;
    int yMouse;
    int sortCount = 0;
    int searchCount = 0;
    int newFolderCount = 0;
    int moveToCount = 0;
    int logoutCount = 0;
    int composeCount = 0;
    int sortOrderCount = 0;
    int filterMsgCount = 0;
    int numOfMsgsBefore = 0;
    int numOfMsgsafter = 0;
    public static int selectallcounter = 0;
    public static int addcounter = 0;
    public static int addattachmentcounter = 0;
    int userFoldersCount = 0;
    String searchOn = "subject";
    String sortOn = "subject";
    String sortOrder = "a";
    public static LinkedList recieversNames = new LinkedList();
    public static LinkedList attachments = new LinkedList();
    boolean composeAttach = false;
    AddRecframe add = new AddRecframe(this);
    AddAttachmentFrame addat = new AddAttachmentFrame(this);
    ComposeGUI setup = new ComposeGUI();
    App a = new App();
    Folder f = new Folder();
    ViewMessages vm = new ViewMessages(this);
    public Mail[] allMails;
    public static String[] currentUserFolders;

    /**
     * Creates new form Index
     */
    public Inbox() throws IOException {
        initComponents();
        f.dir = "inbox";
        if (user != null) {
            userName.setText(user[0]);
            userEmail.setText(user[2]);
            Image img = ImageIO.read(new File("server\\users\\" + user[0] + "\\pp.jpg"));
            Image pp = img.getScaledInstance(263, 263, Image.SCALE_AREA_AVERAGING);
            profilePic.setIcon(new ImageIcon(pp));
            f.user = user[0];
            Filter sort = new Filter();
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            a.setViewingOptions(f, null, sort);
            numOfMsgsBefore = a.list.size();
            allMails = (Mail[]) a.listEmails(1);
            ShowCheckBox(vm.showInTable(allMails, jTable1, "inbox"));
        }
        sortOptions.setVisible(false);
        searchOptions.setVisible(false);
        moveToOptions.setVisible(false);
        newFolderOptions.setVisible(false);
        logoutOptions.setVisible(false);
        composeOptions.setVisible(false);
        addat.setVisible(false);
        add.setVisible(false);
        userFoldersPanel.setVisible(false);
        filterMessagesPanel.setVisible(false);
        sortingOrderPanel.setVisible(false);
        here1.setVisible(false);
        here2.setVisible(false);
        here3.setVisible(false);
        here4.setVisible(false);
        here5.setVisible(false);
        newMessageAnimation.setVisible(false);
        sendMessageAnimation.setVisible(false);
    }

    public void ShowCheckBox(int size) {
        if (size >= 1) {
            check1.setVisible(true);
            check1.setSelected(false);
        } else {
            check1.setVisible(false);
            check1.setSelected(false);
        }
        if (size >= 2) {
            check2.setVisible(true);
            check2.setSelected(false);
        } else {
            check2.setVisible(false);
            check2.setSelected(false);
        }
        if (size >= 3) {
            check3.setVisible(true);
            check3.setSelected(false);
        } else {
            check3.setVisible(false);
            check3.setSelected(false);
        }
        if (size >= 4) {
            check4.setVisible(true);
            check4.setSelected(false);
        } else {
            check4.setVisible(false);
            check4.setSelected(false);
        }
        if (size >= 5) {
            check5.setVisible(true);
            check5.setSelected(false);
        } else {
            check5.setVisible(false);
            check5.setSelected(false);
        }
        if (size >= 6) {
            check6.setVisible(true);
            check6.setSelected(false);
        } else {
            check6.setVisible(false);
            check6.setSelected(false);
        }
        if (size >= 7) {
            check7.setVisible(true);
            check7.setSelected(false);
        } else {
            check7.setVisible(false);
            check7.setSelected(false);
        }
        if (size >= 8) {
            check8.setVisible(true);
            check8.setSelected(false);
        } else {
            check8.setVisible(false);
            check8.setSelected(false);
        }
        if (size >= 9) {
            check9.setVisible(true);
            check9.setSelected(false);
        } else {
            check9.setVisible(false);
            check9.setSelected(false);
        }
        if (size >= 10) {
            check10.setVisible(true);
            check10.setSelected(false);
        } else {
            check10.setVisible(false);
            check10.setSelected(false);
        }
    }

    public LinkedList NomOfChecked() {
        LinkedList indexs = new LinkedList();
        if (check1.isSelected() && check1.isVisible()) {
            indexs.add(0);
        }
        if (check2.isSelected() && check2.isVisible()) {
            indexs.add(1);
        }
        if (check3.isSelected() && check3.isVisible()) {
            indexs.add(2);
        }
        if (check4.isSelected() && check4.isVisible()) {
            indexs.add(3);
        }
        if (check5.isSelected() && check5.isVisible()) {
            indexs.add(4);
        }
        if (check6.isSelected() && check6.isVisible()) {
            indexs.add(5);
        }
        if (check7.isSelected() && check6.isVisible()) {
            indexs.add(6);
        }
        if (check8.isSelected() && check8.isVisible()) {
            indexs.add(7);
        }
        if (check9.isSelected() && check9.isVisible()) {
            indexs.add(8);
        }
        if (check10.isSelected() && check10.isVisible()) {
            indexs.add(9);
        }
        return indexs;
    }

    public void MarkedFalse(int size) {
        if (size >= 1) {
            check1.setSelected(false);
        } else {
            check1.setSelected(false);
        }
        if (size >= 2) {
            check2.setSelected(false);
        } else {
            check2.setSelected(false);
        }
        if (size >= 3) {
            check3.setSelected(false);
        } else {
            check3.setSelected(false);
        }
        if (size >= 4) {
            check4.setSelected(false);
        } else {
            check4.setSelected(false);
        }
        if (size >= 5) {
            check5.setSelected(false);
        } else {
            check5.setSelected(false);
        }
        if (size >= 6) {
            check6.setSelected(false);
        } else {
            check6.setSelected(false);
        }
        if (size >= 7) {
            check7.setSelected(false);
        } else {
            check7.setSelected(false);
        }
        if (size >= 8) {
            check8.setSelected(false);
        } else {
            check8.setSelected(false);
        }
        if (size >= 9) {
            check9.setSelected(false);
        } else {
            check9.setSelected(false);
        }
        if (size >= 10) {
            check10.setSelected(false);
        } else {
            check10.setSelected(false);
        }
    }

    public void MarkedTrue(int size) {
        if (size >= 1) {
            check1.setSelected(true);
        } else {
            check1.setSelected(false);
        }
        if (size >= 2) {
            check2.setSelected(true);
        } else {
            check2.setSelected(false);
        }
        if (size >= 3) {
            check3.setSelected(true);
        } else {
            check3.setSelected(false);
        }
        if (size >= 4) {
            check4.setSelected(true);
        } else {
            check4.setSelected(false);
        }
        if (size >= 5) {
            check5.setSelected(true);
        } else {
            check5.setSelected(false);
        }
        if (size >= 6) {
            check6.setSelected(true);
        } else {
            check6.setSelected(false);
        }
        if (size >= 7) {
            check7.setSelected(true);
        } else {
            check7.setSelected(false);
        }
        if (size >= 8) {
            check8.setSelected(true);
        } else {
            check8.setSelected(false);
        }
        if (size >= 9) {
            check9.setSelected(true);
        } else {
            check9.setSelected(false);
        }
        if (size >= 10) {
            check10.setSelected(true);
        } else {
            check10.setSelected(false);
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLayeredPane1 = new javax.swing.JLayeredPane();
        sendMessageAnimation = new javax.swing.JPanel();
        pip = new javax.swing.JLabel();
        man = new javax.swing.JLabel();
        jLabel19 = new javax.swing.JLabel();
        newMessageAnimation = new javax.swing.JPanel();
        jLabel20 = new javax.swing.JLabel();
        jLabel18 = new javax.swing.JLabel();
        msg = new javax.swing.JLabel();
        jLabel17 = new javax.swing.JLabel();
        filterMessagesPanel = new javax.swing.JPanel();
        subjectToFilter = new javax.swing.JTextField();
        FolderToFilter = new javax.swing.JTextField();
        jLabel16 = new javax.swing.JLabel();
        jLabel15 = new javax.swing.JLabel();
        sortingOrderPanel = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        jLabel10 = new javax.swing.JLabel();
        userFoldersPanel = new javax.swing.JPanel();
        indexDeleteUserFolder = new javax.swing.JTextField();
        txt7 = new javax.swing.JLabel();
        folder5 = new javax.swing.JLabel();
        txt8 = new javax.swing.JLabel();
        txt6 = new javax.swing.JLabel();
        txt5 = new javax.swing.JLabel();
        folder6 = new javax.swing.JLabel();
        folder7 = new javax.swing.JLabel();
        folder8 = new javax.swing.JLabel();
        folder3 = new javax.swing.JLabel();
        txt3 = new javax.swing.JLabel();
        folder4 = new javax.swing.JLabel();
        txt4 = new javax.swing.JLabel();
        folder2 = new javax.swing.JLabel();
        txt2 = new javax.swing.JLabel();
        txt1 = new javax.swing.JLabel();
        folder1 = new javax.swing.JLabel();
        deleteUserFolder = new javax.swing.JLabel();
        closeUserFolders = new javax.swing.JLabel();
        error = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        backgroundUserFolder = new javax.swing.JLabel();
        searchOptions = new javax.swing.JPanel();
        searchOptionSubject = new javax.swing.JLabel();
        searchOptionSender = new javax.swing.JLabel();
        searchOptionReciever = new javax.swing.JLabel();
        searchOptionDate = new javax.swing.JLabel();
        searchOptionImportance = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        backgroundSortOptions1 = new javax.swing.JLabel();
        logoutOptions = new javax.swing.JPanel();
        yesLogoutOption = new javax.swing.JLabel();
        noLogoutOption = new javax.swing.JLabel();
        backgroundLogOut = new javax.swing.JLabel();
        sortOptions = new javax.swing.JPanel();
        sortOptionSubject = new javax.swing.JLabel();
        sortOptionSender = new javax.swing.JLabel();
        sortOptionReciever = new javax.swing.JLabel();
        sortOptionDate = new javax.swing.JLabel();
        sortOptionImportance = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        backgroundSortOptions = new javax.swing.JLabel();
        moveToOptions = new javax.swing.JPanel();
        moveToBtn = new javax.swing.JLabel();
        moveToTrash = new javax.swing.JLabel();
        moveToTF = new javax.swing.JTextField();
        backgroundMoveTo = new javax.swing.JLabel();
        composeOptions = new javax.swing.JPanel();
        toCompose = new javax.swing.JTextField();
        subjectCompose1 = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTextArea1 = new javax.swing.JTextArea();
        addToCompose = new javax.swing.JLabel();
        addToCompose1 = new javax.swing.JLabel();
        jComboBox1 = new javax.swing.JComboBox();
        jLabel7 = new javax.swing.JLabel();
        backgroundCompose = new javax.swing.JLabel();
        newFolderOptions = new javax.swing.JPanel();
        moveToTF1 = new javax.swing.JTextField();
        createFolder = new javax.swing.JLabel();
        backgroundNewFolder = new javax.swing.JLabel();
        messagesPanel = new javax.swing.JPanel();
        check1 = new javax.swing.JCheckBox();
        check2 = new javax.swing.JCheckBox();
        check3 = new javax.swing.JCheckBox();
        check4 = new javax.swing.JCheckBox();
        check5 = new javax.swing.JCheckBox();
        check6 = new javax.swing.JCheckBox();
        check7 = new javax.swing.JCheckBox();
        check8 = new javax.swing.JCheckBox();
        check9 = new javax.swing.JCheckBox();
        check10 = new javax.swing.JCheckBox();
        Star10 = new javax.swing.JLabel();
        Star8 = new javax.swing.JLabel();
        Star7 = new javax.swing.JLabel();
        Star6 = new javax.swing.JLabel();
        Star9 = new javax.swing.JLabel();
        Star5 = new javax.swing.JLabel();
        Star4 = new javax.swing.JLabel();
        Star3 = new javax.swing.JLabel();
        Star2 = new javax.swing.JLabel();
        Star1 = new javax.swing.JLabel();
        msg9 = new javax.swing.JLabel();
        msg8 = new javax.swing.JLabel();
        msg7 = new javax.swing.JLabel();
        msg6 = new javax.swing.JLabel();
        msg10 = new javax.swing.JLabel();
        msg4 = new javax.swing.JLabel();
        msg5 = new javax.swing.JLabel();
        msg1 = new javax.swing.JLabel();
        msg2 = new javax.swing.JLabel();
        msg3 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        jLabel13 = new javax.swing.JLabel();
        searchError = new javax.swing.JLabel();
        searchFilter = new javax.swing.JLabel();
        searchb = new javax.swing.JLabel();
        sortbb = new javax.swing.JLabel();
        allb = new javax.swing.JLabel();
        nextb = new javax.swing.JLabel();
        choosenSearchFilter = new javax.swing.JLabel();
        left = new javax.swing.JLabel();
        contacts = new javax.swing.JLabel();
        logout = new javax.swing.JLabel();
        deleteb = new javax.swing.JLabel();
        refresh = new javax.swing.JLabel();
        star = new javax.swing.JLabel();
        newFolder = new javax.swing.JLabel();
        movetob = new javax.swing.JLabel();
        search = new javax.swing.JTextField();
        composeb = new javax.swing.JLabel();
        inboxb = new javax.swing.JLabel();
        sentmailb = new javax.swing.JLabel();
        importantb = new javax.swing.JLabel();
        draftsb = new javax.swing.JLabel();
        userFolders = new javax.swing.JLabel();
        trashb = new javax.swing.JLabel();
        exit = new javax.swing.JLabel();
        minimize = new javax.swing.JLabel();
        changePp = new javax.swing.JLabel();
        dragFrame = new javax.swing.JLabel();
        profilePic = new javax.swing.JLabel();
        pageNum = new javax.swing.JLabel();
        userEmail = new javax.swing.JLabel();
        userName = new javax.swing.JLabel();
        here1 = new javax.swing.JLabel();
        here2 = new javax.swing.JLabel();
        here3 = new javax.swing.JLabel();
        here4 = new javax.swing.JLabel();
        here = new javax.swing.JLabel();
        here5 = new javax.swing.JLabel();
        background = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);
        setUndecorated(true);
        setResizable(false);
        getContentPane().setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLayeredPane1.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sendMessageAnimation.setOpaque(false);
        sendMessageAnimation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        pip.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/pipe.png"))); // NOI18N
        sendMessageAnimation.add(pip, new org.netbeans.lib.awtextra.AbsoluteConstraints(947, 100, 95, -1));
        sendMessageAnimation.add(man, new org.netbeans.lib.awtextra.AbsoluteConstraints(-200, 120, 200, -1));

        jLabel19.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/pipeees1.png"))); // NOI18N
        sendMessageAnimation.add(jLabel19, new org.netbeans.lib.awtextra.AbsoluteConstraints(946, 100, -1, -1));

        jLayeredPane1.add(sendMessageAnimation, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 226, 1041, 587));

        newMessageAnimation.setOpaque(false);
        newMessageAnimation.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel20.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/msgs2.png"))); // NOI18N
        newMessageAnimation.add(jLabel20, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 410, 500, 180));

        jLabel18.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/2222.png"))); // NOI18N
        newMessageAnimation.add(jLabel18, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, -3, -1, -1));

        msg.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/msg1.png"))); // NOI18N
        newMessageAnimation.add(msg, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, -70, 110, 130));

        jLabel17.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/1111.png"))); // NOI18N
        newMessageAnimation.add(jLabel17, new org.netbeans.lib.awtextra.AbsoluteConstraints(380, -2, -1, -1));

        jLayeredPane1.add(newMessageAnimation, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 226, 1041, 587));

        filterMessagesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        subjectToFilter.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        subjectToFilter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        subjectToFilter.setBorder(null);
        subjectToFilter.setOpaque(false);
        subjectToFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                subjectToFilterActionPerformed(evt);
            }
        });
        filterMessagesPanel.add(subjectToFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 47, 290, 50));

        FolderToFilter.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        FolderToFilter.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        FolderToFilter.setBorder(null);
        FolderToFilter.setOpaque(false);
        FolderToFilter.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                FolderToFilterActionPerformed(evt);
            }
        });
        filterMessagesPanel.add(FolderToFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(170, 141, 290, 50));

        jLabel16.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel16.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel16MouseClicked(evt);
            }
        });
        filterMessagesPanel.add(jLabel16, new org.netbeans.lib.awtextra.AbsoluteConstraints(248, 222, 88, 53));

        jLabel15.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/filter Inbox.png"))); // NOI18N
        filterMessagesPanel.add(jLabel15, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLayeredPane1.add(filterMessagesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(760, 250, -1, -1));

        sortingOrderPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        jLabel11.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel11.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel11MouseClicked(evt);
            }
        });
        sortingOrderPanel.add(jLabel11, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 17, 160, 44));

        jLabel12.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel12.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel12MouseClicked(evt);
            }
        });
        sortingOrderPanel.add(jLabel12, new org.netbeans.lib.awtextra.AbsoluteConstraints(30, 66, 160, 45));

        jLabel10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/sorting order.png"))); // NOI18N
        sortingOrderPanel.add(jLabel10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLayeredPane1.add(sortingOrderPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 215, 217, -1));

        userFoldersPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        indexDeleteUserFolder.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 18)); // NOI18N
        indexDeleteUserFolder.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        indexDeleteUserFolder.setBorder(null);
        indexDeleteUserFolder.setOpaque(false);
        userFoldersPanel.add(indexDeleteUserFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(290, 375, 50, 30));

        txt7.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txt7.setForeground(new java.awt.Color(255, 255, 255));
        userFoldersPanel.add(txt7, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 290, 100, 20));

        folder5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folder5MouseClicked(evt);
            }
        });
        userFoldersPanel.add(folder5, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 190, 100, 90));

        txt8.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txt8.setForeground(new java.awt.Color(255, 255, 255));
        userFoldersPanel.add(txt8, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 290, 100, 20));

        txt6.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txt6.setForeground(new java.awt.Color(255, 255, 255));
        userFoldersPanel.add(txt6, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 290, 100, 20));

        txt5.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txt5.setForeground(new java.awt.Color(255, 255, 255));
        userFoldersPanel.add(txt5, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 290, 100, 20));

        folder6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folder6MouseClicked(evt);
            }
        });
        userFoldersPanel.add(folder6, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 190, 100, 90));

        folder7.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folder7MouseClicked(evt);
            }
        });
        userFoldersPanel.add(folder7, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 190, 100, 90));

        folder8.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folder8MouseClicked(evt);
            }
        });
        userFoldersPanel.add(folder8, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 190, 100, 90));

        folder3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folder3MouseClicked(evt);
            }
        });
        userFoldersPanel.add(folder3, new org.netbeans.lib.awtextra.AbsoluteConstraints(430, 20, 100, 90));

        txt3.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txt3.setForeground(new java.awt.Color(255, 255, 255));
        userFoldersPanel.add(txt3, new org.netbeans.lib.awtextra.AbsoluteConstraints(440, 120, 100, 20));

        folder4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folder4MouseClicked(evt);
            }
        });
        userFoldersPanel.add(folder4, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 20, 100, 90));

        txt4.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txt4.setForeground(new java.awt.Color(255, 255, 255));
        userFoldersPanel.add(txt4, new org.netbeans.lib.awtextra.AbsoluteConstraints(610, 120, 100, 20));

        folder2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folder2MouseClicked(evt);
            }
        });
        userFoldersPanel.add(folder2, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 20, 100, 90));

        txt2.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txt2.setForeground(new java.awt.Color(255, 255, 255));
        userFoldersPanel.add(txt2, new org.netbeans.lib.awtextra.AbsoluteConstraints(240, 120, 100, 20));

        txt1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        txt1.setForeground(new java.awt.Color(255, 255, 255));
        userFoldersPanel.add(txt1, new org.netbeans.lib.awtextra.AbsoluteConstraints(70, 120, 100, 20));

        folder1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        folder1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                folder1MouseClicked(evt);
            }
        });
        userFoldersPanel.add(folder1, new org.netbeans.lib.awtextra.AbsoluteConstraints(60, 20, 100, 90));

        deleteUserFolder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteUserFolder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deleteUserFolderMouseClicked(evt);
            }
        });
        userFoldersPanel.add(deleteUserFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(365, 360, 139, 58));

        closeUserFolders.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        closeUserFolders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                closeUserFoldersMouseClicked(evt);
            }
        });
        userFoldersPanel.add(closeUserFolders, new org.netbeans.lib.awtextra.AbsoluteConstraints(330, 448, 100, 40));

        error.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        error.setForeground(new java.awt.Color(255, 0, 0));
        error.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        userFoldersPanel.add(error, new org.netbeans.lib.awtextra.AbsoluteConstraints(230, 340, 130, 30));

        jLabel14.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel14.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel14MouseClicked(evt);
            }
        });
        userFoldersPanel.add(jLabel14, new org.netbeans.lib.awtextra.AbsoluteConstraints(528, 360, 175, 60));

        backgroundUserFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/userFolders2.png"))); // NOI18N
        userFoldersPanel.add(backgroundUserFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLayeredPane1.add(userFoldersPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(450, 200, -1, -1));

        searchOptions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        searchOptionSubject.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchOptionSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchOptionSubjectMouseClicked(evt);
            }
        });
        searchOptions.add(searchOptionSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, 42));

        searchOptionSender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchOptionSender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchOptionSenderMouseClicked(evt);
            }
        });
        searchOptions.add(searchOptionSender, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 66, 140, 44));

        searchOptionReciever.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchOptionReciever.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchOptionRecieverMouseClicked(evt);
            }
        });
        searchOptions.add(searchOptionReciever, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 115, 140, 47));

        searchOptionDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchOptionDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchOptionDateMouseClicked(evt);
            }
        });
        searchOptions.add(searchOptionDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 165, 140, 50));

        searchOptionImportance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchOptionImportance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchOptionImportanceMouseClicked(evt);
            }
        });
        searchOptions.add(searchOptionImportance, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 220, 140, 60));

        jLabel1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel1MouseEntered(evt);
            }
        });
        searchOptions.add(jLabel1, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 343, 140, 60));

        jLabel9.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel9MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                jLabel9MouseEntered(evt);
            }
        });
        searchOptions.add(jLabel9, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 280, 140, 60));

        backgroundSortOptions1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/filter2.png"))); // NOI18N
        searchOptions.add(backgroundSortOptions1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, 410));

        jLayeredPane1.add(searchOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(920, 130, 220, 412));

        logoutOptions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        yesLogoutOption.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        yesLogoutOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                yesLogoutOptionMouseClicked(evt);
            }
        });
        logoutOptions.add(yesLogoutOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(254, 150, 113, 47));

        noLogoutOption.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        noLogoutOption.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                noLogoutOptionMouseClicked(evt);
            }
        });
        logoutOptions.add(noLogoutOption, new org.netbeans.lib.awtextra.AbsoluteConstraints(69, 150, 113, 47));

        backgroundLogOut.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/LogOut.png"))); // NOI18N
        logoutOptions.add(backgroundLogOut, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLayeredPane1.add(logoutOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 340, -1, -1));

        sortOptions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        sortOptionSubject.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortOptionSubject.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortOptionSubjectMouseClicked(evt);
            }
        });
        sortOptions.add(sortOptionSubject, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 20, 140, 42));

        sortOptionSender.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortOptionSender.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortOptionSenderMouseClicked(evt);
            }
        });
        sortOptions.add(sortOptionSender, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 68, 140, 45));

        sortOptionReciever.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortOptionReciever.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortOptionRecieverMouseClicked(evt);
            }
        });
        sortOptions.add(sortOptionReciever, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 117, 140, 47));

        sortOptionDate.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortOptionDate.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortOptionDateMouseClicked(evt);
            }
        });
        sortOptions.add(sortOptionDate, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 167, 140, 50));

        sortOptionImportance.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortOptionImportance.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortOptionImportanceMouseClicked(evt);
            }
        });
        sortOptions.add(sortOptionImportance, new org.netbeans.lib.awtextra.AbsoluteConstraints(40, 222, 140, 55));

        jLabel8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel8MouseClicked(evt);
            }
        });
        sortOptions.add(jLabel8, new org.netbeans.lib.awtextra.AbsoluteConstraints(20, 285, 170, 50));

        backgroundSortOptions.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/filter1.png"))); // NOI18N
        sortOptions.add(backgroundSortOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 220, -1));

        jLayeredPane1.add(sortOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(620, 220, 220, -1));

        moveToOptions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        moveToBtn.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        moveToBtn.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moveToBtnMouseClicked(evt);
            }
        });
        moveToOptions.add(moveToBtn, new org.netbeans.lib.awtextra.AbsoluteConstraints(268, 26, 54, 37));

        moveToTrash.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        moveToTrash.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                moveToTrashMouseClicked(evt);
            }
        });
        moveToOptions.add(moveToTrash, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 110, 100, 40));

        moveToTF.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        moveToTF.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        moveToTF.setBorder(null);
        moveToTF.setOpaque(false);
        moveToTF.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                moveToTFActionPerformed(evt);
            }
        });
        moveToOptions.add(moveToTF, new org.netbeans.lib.awtextra.AbsoluteConstraints(47, 27, 209, 38));

        backgroundMoveTo.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/Move1.png"))); // NOI18N
        moveToOptions.add(backgroundMoveTo, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLayeredPane1.add(moveToOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 220, 350, 180));

        composeOptions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        toCompose.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        toCompose.setBorder(null);
        toCompose.setOpaque(false);
        composeOptions.add(toCompose, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 83, 500, 38));

        subjectCompose1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        subjectCompose1.setBorder(null);
        subjectCompose1.setOpaque(false);
        composeOptions.add(subjectCompose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(149, 144, 570, 38));

        jLabel3.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel3MouseClicked(evt);
            }
        });
        composeOptions.add(jLabel3, new org.netbeans.lib.awtextra.AbsoluteConstraints(260, 540, 50, 43));

        jLabel4.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel4MouseClicked(evt);
            }
        });
        composeOptions.add(jLabel4, new org.netbeans.lib.awtextra.AbsoluteConstraints(648, 540, 75, 43));

        jLabel2.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel2MouseClicked(evt);
            }
        });
        composeOptions.add(jLabel2, new org.netbeans.lib.awtextra.AbsoluteConstraints(120, 540, 110, 43));

        jLabel5.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel5MouseClicked(evt);
            }
        });
        composeOptions.add(jLabel5, new org.netbeans.lib.awtextra.AbsoluteConstraints(707, 10, 27, 26));

        jLabel6.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel6MouseClicked(evt);
            }
        });
        composeOptions.add(jLabel6, new org.netbeans.lib.awtextra.AbsoluteConstraints(23, 539, 88, 43));

        jScrollPane1.setBackground(new java.awt.Color(255, 255, 255));
        jScrollPane1.setBorder(null);
        jScrollPane1.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        jScrollPane1.setOpaque(false);

        jTextArea1.setColumns(20);
        jTextArea1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        jTextArea1.setRows(5);
        jTextArea1.setBorder(null);
        jTextArea1.setOpaque(false);
        jScrollPane1.setViewportView(jTextArea1);

        composeOptions.add(jScrollPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(27, 237, 692, 212));

        addToCompose.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addToCompose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToComposeMouseClicked(evt);
            }
        });
        composeOptions.add(addToCompose, new org.netbeans.lib.awtextra.AbsoluteConstraints(685, 85, 27, 35));

        addToCompose1.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        addToCompose1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                addToCompose1MouseClicked(evt);
            }
        });
        composeOptions.add(addToCompose1, new org.netbeans.lib.awtextra.AbsoluteConstraints(653, 85, 26, 35));

        jComboBox1.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jComboBox1.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "1", "2", "3", "4", "5" }));
        composeOptions.add(jComboBox1, new org.netbeans.lib.awtextra.AbsoluteConstraints(670, 189, 50, 40));

        jLabel7.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(51, 51, 51));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("Priority:");
        jLabel7.setToolTipText("");
        composeOptions.add(jLabel7, new org.netbeans.lib.awtextra.AbsoluteConstraints(580, 190, 90, 40));

        backgroundCompose.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/compose5.jpg"))); // NOI18N
        backgroundCompose.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                backgroundComposeMouseClicked(evt);
            }
        });
        composeOptions.add(backgroundCompose, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, -1));

        jLayeredPane1.add(composeOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(460, 120, 746, 607));

        newFolderOptions.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        moveToTF1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 14)); // NOI18N
        moveToTF1.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        moveToTF1.setBorder(null);
        moveToTF1.setOpaque(false);
        newFolderOptions.add(moveToTF1, new org.netbeans.lib.awtextra.AbsoluteConstraints(195, 45, 307, 53));

        createFolder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        createFolder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                createFolderMouseClicked(evt);
            }
        });
        newFolderOptions.add(createFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(377, 151, 113, 47));

        backgroundNewFolder.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/new Folder.png"))); // NOI18N
        newFolderOptions.add(backgroundNewFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 230));

        jLayeredPane1.add(newFolderOptions, new org.netbeans.lib.awtextra.AbsoluteConstraints(650, 220, 530, -1));

        messagesPanel.setOpaque(false);
        messagesPanel.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        check1.setText("jCheckBox1");
        check1.setOpaque(false);
        messagesPanel.add(check1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 15, 20, 30));

        check2.setText("jCheckBox1");
        check2.setOpaque(false);
        messagesPanel.add(check2, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 73, 20, 30));

        check3.setText("jCheckBox1");
        check3.setOpaque(false);
        messagesPanel.add(check3, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 133, 20, 30));

        check4.setText("jCheckBox1");
        check4.setOpaque(false);
        messagesPanel.add(check4, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 191, 20, 30));

        check5.setText("jCheckBox1");
        check5.setOpaque(false);
        messagesPanel.add(check5, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 249, 20, 30));

        check6.setText("jCheckBox1");
        check6.setOpaque(false);
        messagesPanel.add(check6, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 308, 20, 30));

        check7.setText("jCheckBox1");
        check7.setOpaque(false);
        messagesPanel.add(check7, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 368, 20, 30));

        check8.setText("jCheckBox1");
        check8.setOpaque(false);
        messagesPanel.add(check8, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 430, 20, 30));

        check9.setText("jCheckBox1");
        check9.setOpaque(false);
        messagesPanel.add(check9, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 490, 20, 30));

        check10.setText("jCheckBox1");
        check10.setOpaque(false);
        check10.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                check10ActionPerformed(evt);
            }
        });
        messagesPanel.add(check10, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 543, 20, 30));

        Star10.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star10, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 532, 30, 30));

        Star8.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star8, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 418, 30, 30));

        Star7.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star7, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 360, 30, 30));

        Star6.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star6, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 300, 30, 30));

        Star9.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star9, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 472, 30, 30));

        Star5.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star5, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 240, 30, 30));

        Star4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star4, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 180, 30, 30));

        Star3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star3, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 120, 30, 30));

        Star2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star2, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 60, 30, 30));

        Star1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/fetusStar.png"))); // NOI18N
        messagesPanel.add(Star1, new org.netbeans.lib.awtextra.AbsoluteConstraints(990, 10, 30, 30));

        msg9.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg9MouseClicked(evt);
            }
        });
        messagesPanel.add(msg9, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 473, 1020, 60));

        msg8.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg8MouseClicked(evt);
            }
        });
        messagesPanel.add(msg8, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 415, 1020, 58));

        msg7.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg7MouseClicked(evt);
            }
        });
        messagesPanel.add(msg7, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 357, 1020, 58));

        msg6.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg6MouseClicked(evt);
            }
        });
        messagesPanel.add(msg6, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 298, 1020, 58));

        msg10.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg10MouseClicked(evt);
            }
        });
        messagesPanel.add(msg10, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 532, 1020, 55));

        msg4.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg4MouseClicked(evt);
            }
        });
        messagesPanel.add(msg4, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 180, 1020, 58));

        msg5.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg5MouseClicked(evt);
            }
        });
        messagesPanel.add(msg5, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 238, 1020, 57));

        msg1.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg1MouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                msg1MouseEntered(evt);
            }
            public void mouseExited(java.awt.event.MouseEvent evt) {
                msg1MouseExited(evt);
            }
        });
        messagesPanel.add(msg1, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 0, 1016, 60));

        msg2.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg2MouseClicked(evt);
            }
        });
        messagesPanel.add(msg2, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 60, 1020, 60));

        msg3.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                msg3MouseClicked(evt);
            }
        });
        messagesPanel.add(msg3, new org.netbeans.lib.awtextra.AbsoluteConstraints(24, 120, 1020, 58));

        jScrollPane2.setHorizontalScrollBarPolicy(javax.swing.ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);
        jScrollPane2.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_NEVER);
        jScrollPane2.setOpaque(false);

        jTable1.setAutoCreateRowSorter(true);
        jTable1.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "", "", ""
            }
        ) {
            Class[] types = new Class [] {
                java.lang.String.class, java.lang.String.class, java.lang.String.class
            };
            boolean[] canEdit = new boolean [] {
                false, false, false
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        jTable1.setFocusable(false);
        jTable1.setOpaque(false);
        jTable1.setRowHeight(59);
        jTable1.setRowSelectionAllowed(false);
        jTable1.setShowHorizontalLines(false);
        jTable1.setShowVerticalLines(false);
        jScrollPane2.setViewportView(jTable1);

        messagesPanel.add(jScrollPane2, new org.netbeans.lib.awtextra.AbsoluteConstraints(22, -12, 1025, 598));

        jLayeredPane1.add(messagesPanel, new org.netbeans.lib.awtextra.AbsoluteConstraints(358, 226, 1041, 587));

        jLabel13.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        jLabel13.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                jLabel13MouseClicked(evt);
            }
        });
        jLayeredPane1.add(jLabel13, new org.netbeans.lib.awtextra.AbsoluteConstraints(942, 170, 163, 40));

        searchError.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 12)); // NOI18N
        searchError.setForeground(new java.awt.Color(255, 0, 0));
        searchError.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLayeredPane1.add(searchError, new org.netbeans.lib.awtextra.AbsoluteConstraints(1050, 120, 360, 30));

        searchFilter.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchFilter.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchFilterMouseClicked(evt);
            }
        });
        jLayeredPane1.add(searchFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(950, 70, 155, 50));

        searchb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        searchb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                searchbMouseClicked(evt);
            }
        });
        jLayeredPane1.add(searchb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1350, 69, 50, 50));

        sortbb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sortbb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sortbbMouseClicked(evt);
            }
            public void mouseEntered(java.awt.event.MouseEvent evt) {
                sortbbMouseEntered(evt);
            }
        });
        jLayeredPane1.add(sortbb, new org.netbeans.lib.awtextra.AbsoluteConstraints(679, 169, 77, 40));

        allb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        allb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                allbMouseClicked(evt);
            }
        });
        jLayeredPane1.add(allb, new org.netbeans.lib.awtextra.AbsoluteConstraints(383, 169, 48, 40));

        nextb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        nextb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                nextbMouseClicked(evt);
            }
        });
        jLayeredPane1.add(nextb, new org.netbeans.lib.awtextra.AbsoluteConstraints(1342, 168, 57, 38));

        choosenSearchFilter.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 14)); // NOI18N
        choosenSearchFilter.setForeground(new java.awt.Color(255, 255, 255));
        choosenSearchFilter.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        choosenSearchFilter.setText("subject");
        jLayeredPane1.add(choosenSearchFilter, new org.netbeans.lib.awtextra.AbsoluteConstraints(955, 77, 118, 30));

        left.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        left.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                leftMouseClicked(evt);
            }
        });
        jLayeredPane1.add(left, new org.netbeans.lib.awtextra.AbsoluteConstraints(1279, 169, 56, 36));

        contacts.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        contacts.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                contactsMouseClicked(evt);
            }
        });
        jLayeredPane1.add(contacts, new org.netbeans.lib.awtextra.AbsoluteConstraints(403, 55, 68, 76));

        logout.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        logout.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                logoutMouseClicked(evt);
            }
        });
        jLayeredPane1.add(logout, new org.netbeans.lib.awtextra.AbsoluteConstraints(510, 60, 70, 70));

        deleteb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        deleteb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                deletebMouseClicked(evt);
            }
        });
        jLayeredPane1.add(deleteb, new org.netbeans.lib.awtextra.AbsoluteConstraints(565, 169, 100, 40));

        refresh.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        refresh.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                refreshMouseClicked(evt);
            }
        });
        jLayeredPane1.add(refresh, new org.netbeans.lib.awtextra.AbsoluteConstraints(1190, 170, 47, 40));

        star.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        star.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                starMouseClicked(evt);
            }
        });
        jLayeredPane1.add(star, new org.netbeans.lib.awtextra.AbsoluteConstraints(860, 170, 50, 40));

        newFolder.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        newFolder.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                newFolderMouseClicked(evt);
            }
        });
        jLayeredPane1.add(newFolder, new org.netbeans.lib.awtextra.AbsoluteConstraints(790, 170, 50, 40));

        movetob.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        movetob.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                movetobMouseClicked(evt);
            }
        });
        jLayeredPane1.add(movetob, new org.netbeans.lib.awtextra.AbsoluteConstraints(468, 169, 83, 40));

        search.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 16)); // NOI18N
        search.setHorizontalAlignment(javax.swing.JTextField.CENTER);
        search.setBorder(null);
        search.setOpaque(false);
        search.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusLost(java.awt.event.FocusEvent evt) {
                searchFocusLost(evt);
            }
        });
        search.addKeyListener(new java.awt.event.KeyAdapter() {
            public void keyTyped(java.awt.event.KeyEvent evt) {
                searchKeyTyped(evt);
            }
        });
        jLayeredPane1.add(search, new org.netbeans.lib.awtextra.AbsoluteConstraints(1107, 69, 242, 49));

        composeb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        composeb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                composebMouseClicked(evt);
            }
        });
        jLayeredPane1.add(composeb, new org.netbeans.lib.awtextra.AbsoluteConstraints(25, 355, 308, 57));

        inboxb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        inboxb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                inboxbMouseClicked(evt);
            }
        });
        jLayeredPane1.add(inboxb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 440, 356, 54));

        sentmailb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        sentmailb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                sentmailbMouseClicked(evt);
            }
        });
        jLayeredPane1.add(sentmailb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 507, 356, 54));

        importantb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        importantb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                importantbMouseClicked(evt);
            }
        });
        jLayeredPane1.add(importantb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 570, 356, 54));

        draftsb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        draftsb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                draftsbMouseClicked(evt);
            }
        });
        jLayeredPane1.add(draftsb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 630, 356, 54));

        userFolders.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        userFolders.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                userFoldersMouseClicked(evt);
            }
        });
        jLayeredPane1.add(userFolders, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 757, 356, 50));

        trashb.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        trashb.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                trashbMouseClicked(evt);
            }
        });
        jLayeredPane1.add(trashb, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 692, 356, 55));

        exit.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        exit.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                exitMouseClicked(evt);
            }
        });
        jLayeredPane1.add(exit, new org.netbeans.lib.awtextra.AbsoluteConstraints(1357, 7, 36, 30));

        minimize.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        minimize.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                minimizeMouseClicked(evt);
            }
        });
        jLayeredPane1.add(minimize, new org.netbeans.lib.awtextra.AbsoluteConstraints(1296, 7, 36, 30));

        changePp.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/Change.png"))); // NOI18N
        changePp.setCursor(new java.awt.Cursor(java.awt.Cursor.HAND_CURSOR));
        changePp.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                changePpMouseClicked(evt);
            }
        });
        jLayeredPane1.add(changePp, new org.netbeans.lib.awtextra.AbsoluteConstraints(103, 293, 150, 48));

        dragFrame.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        dragFrame.addMouseMotionListener(new java.awt.event.MouseMotionAdapter() {
            public void mouseDragged(java.awt.event.MouseEvent evt) {
                dragFrameMouseDragged(evt);
            }
        });
        dragFrame.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mousePressed(java.awt.event.MouseEvent evt) {
                dragFrameMousePressed(evt);
            }
        });
        jLayeredPane1.add(dragFrame, new org.netbeans.lib.awtextra.AbsoluteConstraints(2, 0, 1418, 43));
        jLayeredPane1.add(profilePic, new org.netbeans.lib.awtextra.AbsoluteConstraints(44, 73, 263, 263));

        pageNum.setFont(new java.awt.Font("Arial Rounded MT Bold", 0, 24)); // NOI18N
        pageNum.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        pageNum.setText("1");
        jLayeredPane1.add(pageNum, new org.netbeans.lib.awtextra.AbsoluteConstraints(1240, 170, 40, 40));

        userEmail.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        userEmail.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(userEmail, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 80, 250, 40));

        userName.setFont(new java.awt.Font("Arial Rounded MT Bold", 1, 18)); // NOI18N
        userName.setForeground(new java.awt.Color(255, 255, 255));
        jLayeredPane1.add(userName, new org.netbeans.lib.awtextra.AbsoluteConstraints(600, 50, 240, 40));

        here1.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        here1.setForeground(new java.awt.Color(255, 255, 255));
        here1.setText("<");
        jLayeredPane1.add(here1, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 510, 20, 40));

        here2.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        here2.setForeground(new java.awt.Color(255, 255, 255));
        here2.setText("<");
        jLayeredPane1.add(here2, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 570, 20, 40));

        here3.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        here3.setForeground(new java.awt.Color(255, 255, 255));
        here3.setText("<");
        jLayeredPane1.add(here3, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 640, 20, 40));

        here4.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        here4.setForeground(new java.awt.Color(255, 255, 255));
        here4.setText("<");
        jLayeredPane1.add(here4, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 700, 20, 40));

        here.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        here.setForeground(new java.awt.Color(255, 255, 255));
        here.setText("<");
        jLayeredPane1.add(here, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 450, 20, 40));

        here5.setFont(new java.awt.Font("Algerian", 1, 24)); // NOI18N
        here5.setForeground(new java.awt.Color(255, 255, 255));
        here5.setText("<");
        jLayeredPane1.add(here5, new org.netbeans.lib.awtextra.AbsoluteConstraints(300, 760, 20, 40));

        background.setIcon(new javax.swing.ImageIcon(getClass().getResource("/mailapp/gui/Server13-1.jpg"))); // NOI18N
        background.setCursor(new java.awt.Cursor(java.awt.Cursor.DEFAULT_CURSOR));
        background.setRequestFocusEnabled(false);
        background.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                backgroundFocusGained(evt);
            }
        });
        jLayeredPane1.add(background, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, -1, 870));

        getContentPane().add(jLayeredPane1, new org.netbeans.lib.awtextra.AbsoluteConstraints(0, 0, 1420, 870));

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void exitMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_exitMouseClicked
        // TODO add your handling code here:
        System.exit(0);
    }//GEN-LAST:event_exitMouseClicked

    private void minimizeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_minimizeMouseClicked
        // TODO add your handling code here:
        this.setState(Inbox.ICONIFIED);
    }//GEN-LAST:event_minimizeMouseClicked

    private void dragFrameMouseDragged(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragFrameMouseDragged
        int x = evt.getXOnScreen();
        int y = evt.getYOnScreen();
        this.setLocation(x - xMouse, y - yMouse);
    }//GEN-LAST:event_dragFrameMouseDragged

    private void dragFrameMousePressed(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_dragFrameMousePressed
        xMouse = evt.getX();
        yMouse = evt.getY();
    }//GEN-LAST:event_dragFrameMousePressed

    private void sortbbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortbbMouseClicked
 if(!recentFolder.equals("imp")){
        sortCount++;
        if (sortCount % 2 == 1) {
            sortOptions.setVisible(true);
        } else {
            sortOptions.setVisible(false);
        }
 }
    }//GEN-LAST:event_sortbbMouseClicked

    private void searchFilterMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchFilterMouseClicked
 if(!recentFolder.equals("imp")){
        searchCount++;
        if (searchCount % 2 == 1) {
            searchOptions.setVisible(true);
        } else {
            searchOptions.setVisible(false);
        }
 }
    }//GEN-LAST:event_searchFilterMouseClicked

    private void movetobMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_movetobMouseClicked
 if(!recentFolder.equals("imp")){
        moveToCount++;
        if (moveToCount % 2 == 1) {
            moveToOptions.setVisible(true);
        } else {
            moveToOptions.setVisible(false);
        }
 }
    }//GEN-LAST:event_movetobMouseClicked

    private void newFolderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_newFolderMouseClicked
        newFolderCount++;
        if (newFolderCount % 2 == 1) {
            newFolderOptions.setVisible(true);
        } else {
            newFolderOptions.setVisible(false);
        }
    }//GEN-LAST:event_newFolderMouseClicked

    private void logoutMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_logoutMouseClicked
        logoutCount++;
        if (logoutCount % 2 == 1) {
            logoutOptions.setVisible(true);
        } else {
            logoutOptions.setVisible(false);
        }
    }//GEN-LAST:event_logoutMouseClicked

    private void composebMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_composebMouseClicked
        jComboBox1.setSelectedIndex(4);
        composeCount++;
        search.setText("");
        searchError.setText("");
        if (composeCount % 2 == 1) {
            composeOptions.setVisible(true);
            subjectCompose1.setText("");
            jTextArea1.setText("");
            toCompose.setText("");
            recieversNames.clear();
            attachments.clear();
            composeAttach = false;
        } else {
            composeOptions.setVisible(false);
        }
    }//GEN-LAST:event_composebMouseClicked

    private void inboxbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_inboxbMouseClicked
        here.setVisible(true);
        here1.setVisible(false);
        here2.setVisible(false);
        here3.setVisible(false);
        here4.setVisible(false);
        here5.setVisible(false);
        pageCounter = 1;
        pageNum.setText("1");
        recentFolder = "inbox";
        search.setText("");
        searchError.setText("");
        App a = new App();
        Folder f = new Folder();
        f.dir = recentFolder;
        f.user = user[0];
        ViewMessages vm = new ViewMessages(this);

        Filter sort = new Filter();
        if (sortOn.equals("subject")) {
            sort.subject = true;
        } else if (sortOn.equals("rec")) {
            sort.reciever = true;
        } else if (sortOn.equals("sender")) {
            sort.sender = true;
        } else if (sortOn.equals("date")) {
            sort.date = true;
        } else if (sortOn.equals("attach")) {
            sort.attach = true;
        } else if (sortOn.equals("priority")) {
            sort.priority = true;
        } else {
            sort.subject = true;
        }
        if (sortOrder.equals("a")) {
            sort.ascending = true;
        } else {
            sort.ascending = false;
        }
        a.setViewingOptions(f, null, sort);
        numOfMsgsafter = a.list.size();
        if (numOfMsgsafter > numOfMsgsBefore && recentFolder.equals("inbox")) {
            messagesPanel.setVisible(false);
            newMessageAnimation.setVisible(true);
            new Thread() {
                int i;
                AnimationClass ac = new AnimationClass();

                @Override
                public void run() {
                    try {
                        InputStream in;
                        in = new FileInputStream(new File("sound\\whatsapp_whistle.wav"));
                        AudioStream audios = new AudioStream(in);
                        AudioPlayer.player.start(audios);
                        ac.jLabelYDown(-70, 350, 30, 10, msg);
                        Thread.sleep(2400);
                        //AudioPlayer.player.stop(audios);
                        newMessageAnimation.setVisible(false);
                    } catch (Exception e) {

                    }
                }

            }.start();
            messagesPanel.setVisible(true);
            numOfMsgsBefore = numOfMsgsafter;
        }
        allMails = (Mail[]) a.listEmails(pageCounter);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        here.setLocation(300, 450);
    }//GEN-LAST:event_inboxbMouseClicked

    private void sentmailbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sentmailbMouseClicked
        here.setVisible(false);
        here1.setVisible(true);
        here2.setVisible(false);
        here3.setVisible(false);
        here4.setVisible(false);
        here5.setVisible(false);
        pageCounter = 1;
        pageNum.setText("1");
        search.setText("");
        searchError.setText("");
        recentFolder = "sent";
        App a = new App();
        Folder f = new Folder();
        ViewMessages vm = new ViewMessages(this);
        f.dir = recentFolder;
        f.user = user[0];
        Filter sort = new Filter();
        if (sortOn.equals("subject")) {
            sort.subject = true;
        } else if (sortOn.equals("rec")) {
            sort.reciever = true;
        } else if (sortOn.equals("sender")) {
            sort.sender = true;
        } else if (sortOn.equals("date")) {
            sort.date = true;
        } else if (sortOn.equals("attach")) {
            sort.attach = true;
        } else if (sortOn.equals("priority")) {
            sort.priority = true;
        } else {
            sort.subject = true;
        }
        if (sortOrder.equals("a")) {
            sort.ascending = true;
        } else {
            sort.ascending = false;
        }
        a.setViewingOptions(f, null, sort);
        allMails = (Mail[]) a.listEmails(pageCounter);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        here.setLocation(300, 510);
    }//GEN-LAST:event_sentmailbMouseClicked

    private void draftsbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_draftsbMouseClicked
        here.setVisible(false);
        here1.setVisible(false);
        here2.setVisible(false);
        here3.setVisible(true);
        here4.setVisible(false);
        here5.setVisible(false);
        pageCounter = 1;
        pageNum.setText("1");
        search.setText("");
        searchError.setText("");
        recentFolder = "draft";
        App a = new App();
        Folder f = new Folder();
        ViewMessages vm = new ViewMessages(this);
        f.dir = recentFolder;
        f.user = user[0];
        Filter sort = new Filter();
        if (sortOn.equals("subject")) {
            sort.subject = true;
        } else if (sortOn.equals("rec")) {
            sort.reciever = true;
        } else if (sortOn.equals("sender")) {
            sort.sender = true;
        } else if (sortOn.equals("date")) {
            sort.date = true;
        } else if (sortOn.equals("attach")) {
            sort.attach = true;
        } else if (sortOn.equals("priority")) {
            sort.priority = true;
        } else {
            sort.subject = true;
        }
        if (sortOrder.equals("a")) {
            sort.ascending = true;
        } else {
            sort.ascending = false;
        }
        a.setViewingOptions(f, null, sort);
        allMails = (Mail[]) a.listEmails(pageCounter);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        here.setLocation(300, 630);
    }//GEN-LAST:event_draftsbMouseClicked

    private void trashbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_trashbMouseClicked
        here.setVisible(false);
        here1.setVisible(false);
        here2.setVisible(false);
        here3.setVisible(false);
        here4.setVisible(true);
        here5.setVisible(false);
        SubtractDays s = new SubtractDays();
        try {
            s.deleteMail(s.removeThirtyDays(this), this);
        } catch (IOException ex) {
            Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ParseException ex) {
            Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
        }
        pageCounter = 1;
        pageNum.setText("1");
        search.setText("");
        searchError.setText("");
        recentFolder = "trash";
        App a = new App();
        Folder f = new Folder();
        ViewMessages vm = new ViewMessages(this);
        f.dir = recentFolder;
        f.user = user[0];
        Filter sort = new Filter();
        if (sortOn.equals("subject")) {
            sort.subject = true;
        } else if (sortOn.equals("rec")) {
            sort.reciever = true;
        } else if (sortOn.equals("sender")) {
            sort.sender = true;
        } else if (sortOn.equals("date")) {
            sort.date = true;
        } else if (sortOn.equals("attach")) {
            sort.attach = true;
        } else if (sortOn.equals("priority")) {
            sort.priority = true;
        } else {
            sort.subject = true;
        }
        if (sortOrder.equals("a")) {
            sort.ascending = true;
        } else {
            sort.ascending = false;
        }
        a.setViewingOptions(f, null, sort);
        allMails = (Mail[]) a.listEmails(pageCounter);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        here.setLocation(300, 690);
    }//GEN-LAST:event_trashbMouseClicked

    private void nextbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_nextbMouseClicked
        if (!recentFolder.equals("imp")) {
            App a = new App();
            Folder f = new Folder();
            f.dir = recentFolder;
            f.user = user[0];
            Filter sort = new Filter();
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            a.setViewingOptions(f, null, sort);
            if (a.checkPage(pageCounter + 1)) {
                pageCounter++;
                pageNum.setText(String.valueOf(pageCounter));
                ViewMessages vm = new ViewMessages(this);
                a.setViewingOptions(f, null, sort);
                allMails = (Mail[]) a.listEmails(pageCounter);
                ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
            }
        } else {
            App a = new App();
            Filter sort = new Filter();
            Filter filter = new Filter();
            Folder f = new Folder();
            f.dir = "inbox";
            f.user = user[0];
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            filter.priority = true;
            filter.priority1 = 1;
            a.setViewingOptions(f, filter, sort);
            if (a.checkPage(pageCounter + 1)) {
                a.setViewingOptions(f, filter, sort);
                ViewMessages vm = new ViewMessages(this);
                pageCounter++;
                pageNum.setText(String.valueOf(pageCounter));
                allMails = (Mail[]) a.listEmails(pageCounter);
                ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
            }
        }
    }//GEN-LAST:event_nextbMouseClicked

    private void leftMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_leftMouseClicked
        if (!recentFolder.equals("imp")) {
            App a = new App();
            Folder f = new Folder();
            f.dir = recentFolder;
            f.user = user[0];
            Filter sort = new Filter();
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            a.setViewingOptions(f, null, sort);
            if (a.checkPage(pageCounter - 1)) {
                pageCounter--;
                pageNum.setText(String.valueOf(pageCounter));
                ViewMessages vm = new ViewMessages(this);

                a.setViewingOptions(f, null, sort);
                allMails = (Mail[]) a.listEmails(pageCounter);
                ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
            }
        } else {
            App a = new App();
            Filter sort = new Filter();
            Filter filter = new Filter();
            Folder f = new Folder();
            f.dir = "inbox";
            f.user = user[0];
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            filter.priority = true;
            filter.priority1 = 1;
            a.setViewingOptions(f, filter, sort);
            if(a.checkPage(pageCounter-1)){
            a.setViewingOptions(f, filter, sort);
            ViewMessages vm = new ViewMessages(this);
            pageCounter--;
            pageNum.setText(String.valueOf(pageCounter));
            allMails = (Mail[]) a.listEmails(pageCounter);
            //pageCounter = 1;
            ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
            }
        }
    }//GEN-LAST:event_leftMouseClicked

    private void searchOptionSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchOptionSubjectMouseClicked
        searchOn = "subject";
        choosenSearchFilter.setText(searchOn);
        searchOptions.setVisible(false);
        searchCount++;
    }//GEN-LAST:event_searchOptionSubjectMouseClicked

    private void searchOptionSenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchOptionSenderMouseClicked
        searchOn = "sender";
        choosenSearchFilter.setText(searchOn);
        searchOptions.setVisible(false);
        searchCount++;
    }//GEN-LAST:event_searchOptionSenderMouseClicked

    private void searchOptionRecieverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchOptionRecieverMouseClicked
        searchOn = "reciever";
        choosenSearchFilter.setText(searchOn);
        searchOptions.setVisible(false);
        searchCount++;
    }//GEN-LAST:event_searchOptionRecieverMouseClicked

    private void searchOptionDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchOptionDateMouseClicked
        searchOn = "date";
        choosenSearchFilter.setText(searchOn);
        searchOptions.setVisible(false);
        searchCount++;
    }//GEN-LAST:event_searchOptionDateMouseClicked

    private void searchOptionImportanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchOptionImportanceMouseClicked
        searchOn = "importance";
        choosenSearchFilter.setText(searchOn);
        searchOptions.setVisible(false);
        searchCount++;
    }//GEN-LAST:event_searchOptionImportanceMouseClicked

    private void searchbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_searchbMouseClicked
 if(!recentFolder.equals("imp")){
        if (allMails[0] != null) {
            if (search.getText().length() != 0) {
                searchOn1 = searchOn;
                searchWord1 = search.getText();
                App a = new App();
                searchError.setText("");
                pageCounter = 1;
                pageNum.setText("1");
                Filter sort = new Filter();
                Filter filter = new Filter();
                Folder f = new Folder();
                f.dir = recentFolder;
                f.user = user[0];

                if (sortOn.equals("subject")) {
                    sort.subject = true;
                } else if (sortOn.equals("rec")) {
                    sort.reciever = true;
                } else if (sortOn.equals("sender")) {
                    sort.sender = true;
                } else if (sortOn.equals("date")) {
                    sort.date = true;
                } else if (sortOn.equals("attach")) {
                    sort.attach = true;
                } else if (sortOn.equals("priority")) {
                    sort.priority = true;
                } else {
                    sort.subject = true;
                }
                if (sortOrder.equals("a")) {
                    sort.ascending = true;
                } else {
                    sort.ascending = false;
                }
                if (searchOn.equals("subject")) {
                    filter.subject = true;
                    filter.subject1 = search.getText();
                } else if (searchOn.equals("sender")) {
                    filter.sender = true;
                    filter.sender1 = search.getText();
                } else if (searchOn.equals("reciever")) {
                    filter.reciever = true;
                    filter.reciever1 = search.getText();
                } else if (searchOn.equals("date")) {
                    filter.date = true;
                    filter.date1 = search.getText();
                } else if (searchOn.equals("attach")) {
                    filter.attach = true;
                    filter.attach1 = search.getText();
                } else if (searchOn.equals("importance")) {
                    filter.priority = true;
                    try {
                        filter.priority1 = Integer.parseInt(search.getText());
                    } catch (Exception e) {
                        searchError.setText("Enter Number Of Priority");
                    }
                }
                a.setViewingOptions(f, filter, sort);
                ViewMessages vm = new ViewMessages(this);
                allMails = (Mail[]) a.listEmails(pageCounter);
                ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
            }
        }
 }
    }//GEN-LAST:event_searchbMouseClicked

    private void jLabel4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel4MouseClicked
        ComposeGUI cG = new ComposeGUI();
        if (recieversNames.size() != 0) {
            if (attachments.size() != 0 || jTextArea1.getText().length() != 0) {
                if (subjectCompose1.getText().length() != 0) {
                    try {
                        composeCount++;
                        String username = user[0];
                        //recieversNames.add((String) toCompose.getText());
                        FilterSubject fs = new FilterSubject();
                        int names = 0;
                        while (names != recieversNames.size()) {
                            if (fs.readFromFile(subjectCompose1.getText(), (String) recieversNames.get(names))) {
                                LinkedList name = new LinkedList();
                                name.add((String) recieversNames.get(names));
                                fs.send(username, subjectCompose1.getText(), jTextArea1.getText(), jComboBox1.getSelectedIndex() + 1, name, attachments, composeAttach);
                            } else {
                                LinkedList name = new LinkedList();
                                name.add((String) recieversNames.get(names));
                                cG.send(username, subjectCompose1.getText(), jTextArea1.getText(), jComboBox1.getSelectedIndex() + 1, name, attachments, composeAttach);
                            }
                            names++;
                        }

                        subjectCompose1.setText("");
                        jTextArea1.setText("");
                        toCompose.setText("");
                        recieversNames.clear();
                        attachments.clear();
                        composeAttach = false;
                        composeOptions.setVisible(false);
                        AnimationFrame an = new AnimationFrame(user[0]);
                        an.setVisible(true);
                        messagesPanel.setVisible(false);
//                        sendMessageAnimation.setVisible(true);
//                        new Thread() {
//                            int i;
//                            AnimationClass ac = new AnimationClass();
//                            String imagee;
//
//                            @Override
//                            public void run() {
//                                try {
//                                    if (user[0].contains("mostafa")) {
//                                        imagee = "img\\characterMostafa.png";
//                                    } else if (user[0].contains("reham") || user[0].contains("roham")) {
//                                        imagee = "img\\characterRoham.png";
//                                    } else if (user[0].contains("nashar")) {
//                                        imagee = "img\\character.png";
//                                    } else if (user[0].contains("tarek")) {
//                                        imagee = "img\\characterTarek.png";
//                                    } else {
//                                        imagee = "img\\mario.png";
//                                    }
//                                    Image img = ImageIO.read(new File(imagee));
//                                    Image pp = img.getScaledInstance(207, 170, Image.SCALE_AREA_AVERAGING);
//                                    ImageIcon t = new ImageIcon(pp);
//                                    man.setIcon(t);
//                                    man.setLocation(-200, 120);
//                                    ac.jLabelXRight(-200, 1122, 35, 10, man);
//                                    Thread.sleep(2200);
//                                    InputStream in;
//                                    in = new FileInputStream(new File("sound\\doorway.wav"));
//                                    AudioStream audios = new AudioStream(in);
//                                    AudioPlayer.player.start(audios);
//                                    // man.setLocation(700, 120);
//                                    // ac.jLabelXRight(700, 1122, 50, 10, man);
//                                    Thread.sleep(2800);
//                                    sendMessageAnimation.setVisible(false);
//                                } catch (Exception e) {
//
//                                }
//                            }
//
//                        }.start();
                        messagesPanel.setVisible(true);
                    } catch (IOException ex) {
                        Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
                    }

                } else {
                    subjectCompose1.setText("Email must have a subject");
                }
            } else {
                jTextArea1.setText("Email must contain attachment or content");
            }
        } else {
            //toCompose.setForeground(Color.red);
            toCompose.setText("No users to send message for");
            // toCompose.setForeground(Color.black);
        }


    }//GEN-LAST:event_jLabel4MouseClicked

    private void showMessage(int i, java.awt.event.MouseEvent evt) {
        if (allMails[i] != null) {

            if (evt.getClickCount() == 2) {
                Mail currentMessage = new Mail();
                currentMessage = (Mail) allMails[i];
                /* System.out.println(currentMessage.sender);
                 System.out.println(currentMessage.rec);
                 System.out.println(currentMessage.priority);
                 System.out.println(currentMessage.attachBool);
                 System.out.println(currentMessage.subject);
                 System.out.println(user[0]);*/
                Folder f = allMails[i].msgFolder;
                String msgFolder1 = f.subject;
                LinkedList attatchments1 = new LinkedList();

                String[] folderContents = new File(f.subject).list();
                LinkedList attachments = new LinkedList();
                if (folderContents == null) {
                }
                for (int y = 0; y < folderContents.length; y++) {
                    if (!folderContents[y].equals("msg.txt")) {
                        attachments.add(folderContents[y]);
                        attatchments1.add(new File(f.subject + "\\" + folderContents[y]));
                    }
                }
                try {
                    BufferedReader br = new BufferedReader(new FileReader(new File(msgFolder1 + "\\msg.txt")));
                    String msg = "";
                    String line;
                    while ((line = br.readLine()) != null) {
                        msg += line + "\n";
                    }
                    br.close();
                    //jTextArea2.setText(msg);
                    ShowMessage sM = new ShowMessage(attatchments1, currentMessage, msg, user[0], this);

                    sM.setVisible(true);
                    sM.setLocationRelativeTo(null);
                    if (recentFolder == "inbox") {
                        sM.toorfrom.setText("From");
                        sM.tofromtext.setText(allMails[i].sender);
                        sM.compose.setVisible(false);
                    } else if (recentFolder == "sent") {
                        sM.toorfrom.setText("To");
                        sM.tofromtext.setText(allMails[i].rec);
                        sM.compose.setVisible(false);
                    } else if (recentFolder == "draft") {
                        sM.toorfrom.setText("To");
                        sM.tofromtext.setText(allMails[i].rec);
                        sM.compose.setVisible(true);
                    } else {
                        sM.toorfrom.setText("To / From");
                        sM.tofromtext.setText(allMails[i].rec + " / " + allMails[i].sender);
                        sM.compose.setVisible(false);
                    }
                    //sM.tofromtext.setText(allMails[i].sender);
                    sM.subjectshow.setText(allMails[i].subject);
                    sM.showmail.setText(msg);
                    ComposeGUI comp = new ComposeGUI();
                    comp.loadMessage(attatchments1, sM);
                } catch (FileNotFoundException ex) {
                    Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
                } catch (IOException ex) {
                    Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }
    private void msg1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg1MouseClicked
        showMessage(0, evt);
    }//GEN-LAST:event_msg1MouseClicked

    private void msg2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg2MouseClicked
        showMessage(1, evt);
    }//GEN-LAST:event_msg2MouseClicked

    private void msg3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg3MouseClicked
        showMessage(2, evt);
    }//GEN-LAST:event_msg3MouseClicked

    private void msg4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg4MouseClicked
        showMessage(3, evt);
    }//GEN-LAST:event_msg4MouseClicked

    private void msg5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg5MouseClicked
        showMessage(4, evt);
    }//GEN-LAST:event_msg5MouseClicked

    private void msg6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg6MouseClicked
        showMessage(5, evt);
    }//GEN-LAST:event_msg6MouseClicked

    private void msg7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg7MouseClicked
        showMessage(6, evt);
    }//GEN-LAST:event_msg7MouseClicked

    private void msg8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg8MouseClicked
        showMessage(7, evt);
    }//GEN-LAST:event_msg8MouseClicked

    private void msg9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg9MouseClicked
        showMessage(8, evt);
    }//GEN-LAST:event_msg9MouseClicked

    private void msg10MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg10MouseClicked
        showMessage(9, evt);
    }//GEN-LAST:event_msg10MouseClicked

    private void msg1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg1MouseEntered
    }//GEN-LAST:event_msg1MouseEntered

    private void msg1MouseExited(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_msg1MouseExited
        // TODO add your handling code here:
    }//GEN-LAST:event_msg1MouseExited

    private void createFolderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_createFolderMouseClicked
        NewFolder nf = new NewFolder();
        try {
            if (!nf.CreateFolder(moveToTF1.getText(), user[0])) {
                String mailDir = "server\\users\\";
                String userDir = mailDir + user[0];
                File userFolders = new File(userDir + "\\user folders");
                String[] folders = userFolders.list();
                if (folders.length < 8) {
                    moveToTF1.setText("Folder already exists!");
                } else {
                    moveToTF1.setText("Maximum 8 folders!");
                }

            } else {
                moveToTF1.setText("");
                newFolderOptions.setVisible(false);
                newFolderCount++;
            }
        } catch (IOException ex) {
            Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
        }

    }//GEN-LAST:event_createFolderMouseClicked

    private void allbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_allbMouseClicked
        if(!recentFolder.equals("imp")){
        LinkedList li = new LinkedList();

        selectallcounter++;
        li = NomOfChecked();
        if (li.size() != vm.SelectAll) {
            MarkedTrue(vm.SelectAll);
        } else {
            MarkedFalse(vm.SelectAll);
        }
        }
    }//GEN-LAST:event_allbMouseClicked

    private void jLabel2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel2MouseClicked
        if (recieversNames.size() != 0) {
            ComposeGUI cG = new ComposeGUI();
            String username = user[0];
            try {
                cG.moveDraft(username, subjectCompose1.getText(), jTextArea1.getText(), jComboBox1.getSelectedIndex() + 1, recieversNames, attachments, composeAttach);
            } catch (IOException ex) {
                Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            //in a label missing users to save draft
        }
    }//GEN-LAST:event_jLabel2MouseClicked

    private void addToComposeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addToComposeMouseClicked
        if (toCompose.getText().length() == 0) {
            toCompose.setText("Field is empty");
        } else {
            try {
                if (setup.check(toCompose.getText())) {
                    if (setup.checkExist(recieversNames, toCompose.getText())) {
                        setup.addName(recieversNames, toCompose.getText());
                        toCompose.setText("");
                    } else {
                        toCompose.setText("Email already exist");
                    }
                } else {
                    toCompose.setText("wrong email address");
                }
            } catch (IOException ex) {
                Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }//GEN-LAST:event_addToComposeMouseClicked

    private void jLabel3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel3MouseClicked
        addattachmentcounter++;
        // AddAttachmentFrame addat1=new AddAttachmentFrame(this);
        ComposeGUI comp = new ComposeGUI();

        if (addattachmentcounter % 2 == 1) {
            addat.setVisible(true);
            if (attachments.isEmpty()) {
                addat.attachmentpic.setVisible(true);
                try {
                    comp.loadAttachment(attachments, addat);
                } catch (IOException ex) {
                    Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                try {
                    comp.loadAttachment(attachments, addat);
                    addat.attachmentpic.setVisible(false);
                } catch (IOException ex) {
                    Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
                }
                addat.jTextField2.setText("");
            }
            //addat.jTextArea3.setText("");
            addat.jTextField2.setText("");
            addat.error.setText("");

        } else {
            addat.jTextField2.setText("");
            addat.setVisible(false);
        }
    }//GEN-LAST:event_jLabel3MouseClicked

    private void addToCompose1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_addToCompose1MouseClicked
        addcounter++;
        if (addcounter % 2 == 1) {
            add.setVisible(true);
            add.jTextArea2.setText(setup.showNames(recieversNames));
            add.jTextField1.setText("");
            add.error.setText("");

        } else {
            add.jTextArea2.setText("");
            add.jTextField1.setText("");
            add.setVisible(false);
        }
    }//GEN-LAST:event_addToCompose1MouseClicked

    private void jLabel6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel6MouseClicked
        attachments.clear();
        composeCount++;
        composeOptions.setVisible(false);
    }//GEN-LAST:event_jLabel6MouseClicked

    private void jLabel5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel5MouseClicked
        attachments.clear();
        composeCount++;
        composeOptions.setVisible(false);
    }//GEN-LAST:event_jLabel5MouseClicked

    private void searchFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_searchFocusLost
        search.setText("");
    }//GEN-LAST:event_searchFocusLost

    private void backgroundFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_backgroundFocusGained

    }//GEN-LAST:event_backgroundFocusGained

    private void moveToTFActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_moveToTFActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_moveToTFActionPerformed

    private void moveToBtnMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveToBtnMouseClicked
        // TODO add your handling code here:
        Folder f = new Folder();
        f.dir = moveToTF.getText();
        //f.user="reham";
        IndexToMail im = new IndexToMail();
        // im.toMail(NomOfChecked());

        App a = new App();
        //System.out.println(im.toMail(NomOfChecked()).size());
        a.moveEmails(im.toMail(NomOfChecked(), this), f);
        f = new Folder();
        ViewMessages vm = new ViewMessages(this);
        f.dir = recentFolder;
        f.user = user[0];
        Filter sort = new Filter();
        if (sortOn.equals("subject")) {
            sort.subject = true;
        } else if (sortOn.equals("rec")) {
            sort.reciever = true;
        } else if (sortOn.equals("sender")) {
            sort.sender = true;
        } else if (sortOn.equals("date")) {
            sort.date = true;
        } else if (sortOn.equals("attach")) {
            sort.attach = true;
        } else if (sortOn.equals("priority")) {
            sort.priority = true;
        } else {
            sort.subject = true;
        }
        if (sortOrder.equals("a")) {
            sort.ascending = true;
        } else {
            sort.ascending = false;
        }
        a.setViewingOptions(f, null, sort);
        allMails = (Mail[]) a.listEmails(pageCounter);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        moveToOptions.setVisible(false);
        moveToCount++;
        moveToTF.setText("");

    }//GEN-LAST:event_moveToBtnMouseClicked

    private void moveToTrashMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_moveToTrashMouseClicked
        // TODO add your handling code here:
        IndexToMail im = new IndexToMail();
        App a = new App();
        a.deleteEmails(im.toMail(NomOfChecked(), this));
        Folder f = new Folder();
        ViewMessages vm = new ViewMessages(this);
        f.dir = recentFolder;
        f.user = user[0];
        Filter sort = new Filter();
        if (sortOn.equals("subject")) {
            sort.subject = true;
        } else if (sortOn.equals("rec")) {
            sort.reciever = true;
        } else if (sortOn.equals("sender")) {
            sort.sender = true;
        } else if (sortOn.equals("date")) {
            sort.date = true;
        } else if (sortOn.equals("attach")) {
            sort.attach = true;
        } else if (sortOn.equals("priority")) {
            sort.priority = true;
        } else {
            sort.subject = true;
        }
        if (sortOrder.equals("a")) {
            sort.ascending = true;
        } else {
            sort.ascending = false;
        }
        a.setViewingOptions(f, null, sort);
        allMails = (Mail[]) a.listEmails(pageCounter);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        moveToOptions.setVisible(false);
        moveToCount++;
        moveToTF.setText("");
    }//GEN-LAST:event_moveToTrashMouseClicked

    private void deletebMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deletebMouseClicked
        // TODO add your handling code here:
        if (!recentFolder.equals("trash")&&!recentFolder.equals("imp")) {
            IndexToMail im = new IndexToMail();
            App a = new App();
            a.deleteEmails(im.toMail(NomOfChecked(), this));
            Folder f = new Folder();
            ViewMessages vm = new ViewMessages(this);
            f.dir = recentFolder;
            f.user = user[0];
            Filter sort = new Filter();
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            a.setViewingOptions(f, null, sort);
            allMails = (Mail[]) a.listEmails(pageCounter);
            ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
            moveToOptions.setVisible(false);
        }
    }//GEN-LAST:event_deletebMouseClicked

    private void refreshMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_refreshMouseClicked
if(recentFolder.equals("trash")){
 SubtractDays s = new SubtractDays();
    try {
        s.deleteMail(s.removeThirtyDays(this), this);
    } catch (IOException ex) {
        Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
    } catch (ParseException ex) {
        Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
    }
}
        if (!recentFolder.equals("imp")) {
            App a = new App();
            Folder f = new Folder();
            ViewMessages vm = new ViewMessages(this);
            f.dir = recentFolder;
            f.user = user[0];
            Filter sort = new Filter();
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            a.setViewingOptions(f, null, sort);
            numOfMsgsafter = a.list.size();
            if (numOfMsgsafter > numOfMsgsBefore && recentFolder.equals("inbox")) {
                messagesPanel.setVisible(false);
                newMessageAnimation.setVisible(true);
                new Thread() {
                    int i;
                    AnimationClass ac = new AnimationClass();

                    @Override
                    public void run() {
                        try {
                            InputStream in;
                            in = new FileInputStream(new File("sound\\whatsapp_whistle.wav"));
                            AudioStream audios = new AudioStream(in);
                            AudioPlayer.player.start(audios);
                            ac.jLabelYDown(-70, 350, 30, 10, msg);
                            Thread.sleep(2400);
                            //AudioPlayer.player.stop(audios);
                            newMessageAnimation.setVisible(false);
                        } catch (Exception e) {

                        }
                    }

                }.start();
                messagesPanel.setVisible(true);
                numOfMsgsBefore = numOfMsgsafter;
            }
            allMails = (Mail[]) a.listEmails(pageCounter);
            ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        } else {
            App a = new App();
            Filter sort = new Filter();
            Filter filter = new Filter();
            Folder f = new Folder();
            f.dir = "inbox";
            f.user = user[0];
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            filter.priority = true;
            filter.priority1 = 1;
            a.setViewingOptions(f, filter, sort);
            ViewMessages vm = new ViewMessages(this);
            allMails = (Mail[]) a.listEmails(1);
            pageCounter = 1;
            ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        }
    }//GEN-LAST:event_refreshMouseClicked

    private void noLogoutOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_noLogoutOptionMouseClicked
        // TODO add your handling code here:
        logoutOptions.setVisible(false);
        logoutCount++;
    }//GEN-LAST:event_noLogoutOptionMouseClicked

    private void yesLogoutOptionMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_yesLogoutOptionMouseClicked
        // TODO add your handling code here:
        this.dispose();
        Home h;
        try {
            h = new Home();
            h.setVisible(true);
            h.setLocationRelativeTo(null);
        } catch (IOException ex) {
            Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
        }
    }//GEN-LAST:event_yesLogoutOptionMouseClicked

    private void check10ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_check10ActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_check10ActionPerformed

    private void starMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_starMouseClicked
        // TODO add your handling code here:
        if (!recentFolder.equals("imp")) {
            Starred s = new Starred();
            IndexToMail im = new IndexToMail();
            try {
                s.clickStarred(im.toMail(NomOfChecked(), this), this);
            } catch (IOException ex) {
                Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
            }
            App a = new App();
            Folder f = new Folder();
            ViewMessages vm = new ViewMessages(this);
            f.dir = recentFolder;
            f.user = user[0];
            Filter sort = new Filter();
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            a.setViewingOptions(f, null, sort);
            allMails = (Mail[]) a.listEmails(pageCounter);
            ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
        }
    }//GEN-LAST:event_starMouseClicked

    private void sortOptionSenderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortOptionSenderMouseClicked
        sortCount++;
        sortOptions.setVisible(false);
        sortOn = "sender";
        pageCounter = 1;
        pageNum.setText("1");
        Filter f = new Filter();
        Filter s = new Filter();
        App a = new App();
        Folder fold = new Folder();
        fold.user = user[0];
        fold.dir = recentFolder;
        s.sender = true;
        if (sortOrder.equals("a")) {
            s.ascending = true;
        } else {
            s.ascending = false;
        }
        a.setViewingOptions(fold, null, s);
        ViewMessages vm = new ViewMessages(this);
        allMails = (Mail[]) a.listEmails(1);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
    }//GEN-LAST:event_sortOptionSenderMouseClicked

    private void contactsMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_contactsMouseClicked
        ContactsGui cg = new ContactsGui(user[0], this);
        cg.setVisible(true);
        cg.setLocationRelativeTo(null);
        cg.currentUser = user[0];
    }//GEN-LAST:event_contactsMouseClicked

    private void searchKeyTyped(java.awt.event.KeyEvent evt) {//GEN-FIRST:event_searchKeyTyped
 if(!recentFolder.equals("imp")){
        char typed = evt.getKeyChar();
        App a = new App();
        searchError.setText("");
        pageCounter = 1;
        pageNum.setText("1");
        Filter sort = new Filter();
        Filter filter = new Filter();
        Folder f = new Folder();
        f.dir = recentFolder;
        f.user = user[0];
        if (sortOn.equals("subject")) {
            sort.subject = true;
        } else if (sortOn.equals("rec")) {
            sort.reciever = true;
        } else if (sortOn.equals("sender")) {
            sort.sender = true;
        } else if (sortOn.equals("date")) {
            sort.date = true;
        } else if (sortOn.equals("attach")) {
            sort.attach = true;
        } else if (sortOn.equals("priority")) {
            sort.priority = true;
        } else {
            sort.subject = true;
        }
        if (sortOrder.equals("a")) {
            sort.ascending = true;
        } else {
            sort.ascending = false;
        }
        if (searchOn.equals("subject")) {
            filter.subject = true;
            if ((int) evt.getKeyChar() == 8) {
                filter.subject1 = search.getText();
            } else {
                filter.subject1 = search.getText() + typed;
            }
        } else if (searchOn.equals("sender")) {
            filter.sender = true;
            if ((int) evt.getKeyChar() == 8) {
                filter.sender1 = search.getText();
            } else {
                filter.sender1 = search.getText() + typed;
            }

        } else if (searchOn.equals("reciever")) {
            filter.reciever = true;
            if ((int) evt.getKeyChar() == 8) {
                filter.reciever1 = search.getText();
            } else {
                filter.reciever1 = search.getText() + typed;
            }
        } else if (searchOn.equals("msg")) {
            filter.msg = true;
            if ((int) evt.getKeyChar() == 8) {
                filter.msg1 = search.getText();
            } else {
                filter.msg1 = search.getText() + typed;
            }
        } else if (searchOn.equals("attach")) {
            filter.attach = true;
            if ((int) evt.getKeyChar() == 8) {
                filter.attach1 = search.getText();
            } else {
                filter.attach1 = search.getText() + typed;
            }
        } else if (searchOn.equals("date")) {
            filter.date = true;

            if ((int) evt.getKeyChar() == 8) {
                filter.date1 = search.getText();
            } else {
                filter.date1 = search.getText() + typed;
            }

        } else if (searchOn.equals("importance")) {
            filter.priority = true;
            try {
                filter.priority1 = Integer.parseInt(search.getText() + typed);
            } catch (Exception e) {
                searchError.setText("Enter Number Of Priority");
            }
        }
        try {
            a.setViewingOptionsTyped(f, filter, sort);
        } catch (IOException ex) {
            Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
        }
        ViewMessages vm = new ViewMessages(this);
        allMails = (Mail[]) a.listEmails(pageCounter);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
 }
    }//GEN-LAST:event_searchKeyTyped

    private void changePpMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_changePpMouseClicked
        try {
            JFileChooser fc = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("Image Files", "jpg");
            fc.setAcceptAllFileFilterUsed(false);
            fc.setFileFilter(filter);
            int result = fc.showDialog(null, "Select Image");
            File selected = fc.getSelectedFile();
            if (selected != null && result == JFileChooser.APPROVE_OPTION) {
                File old = new File("server\\users\\" + user[0] + "\\pp.jpg");
                Files.copy(selected.toPath(), old.toPath(), StandardCopyOption.REPLACE_EXISTING);
                Image img = ImageIO.read(old);
                Image pp = img.getScaledInstance(263, 263, Image.SCALE_AREA_AVERAGING);
                profilePic.setIcon(new ImageIcon(pp));
            }
        } catch (IOException ex) {

        }
    }//GEN-LAST:event_changePpMouseClicked

    private void userFoldersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_userFoldersMouseClicked
        userFoldersCount++;
        if (userFoldersCount % 2 == 1) {

            try {
                showUserFolders suf = new showUserFolders();
                suf.show(this);
                userFoldersPanel.setVisible(true);
                indexDeleteUserFolder.setText("");
                error.setText("");
            } catch (IOException ex) {
                Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else {
            userFoldersPanel.setVisible(false);
        }

    }//GEN-LAST:event_userFoldersMouseClicked

    private void deleteUserFolderMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_deleteUserFolderMouseClicked
        try {
            if (Integer.parseInt(indexDeleteUserFolder.getText()) > 0 && Integer.parseInt(indexDeleteUserFolder.getText()) <= currentUserFolders.length) {
                try {
                    Helper help = new Helper();
                    System.out.println("before delete file");
                    help.deleteFiles(new File("server\\users\\" + user[0] + "\\user folders\\" + currentUserFolders[Integer.parseInt(indexDeleteUserFolder.getText()) - 1]));
                    System.out.println("before delete folder");
                    help.deleteFolder(new File("server\\users\\" + user[0] + "\\user folders\\" + currentUserFolders[Integer.parseInt(indexDeleteUserFolder.getText()) - 1]));

                    showUserFolders s = new showUserFolders();
                    System.out.println("before show");
                    s.show(this);
                    error.setText("");
                } catch (IOException ex) {
                    Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else {
                error.setText("Wrong index");
            }
        } catch (Exception e) {
            error.setText("Wrong index");
        }
    }//GEN-LAST:event_deleteUserFolderMouseClicked

    private void closeUserFoldersMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_closeUserFoldersMouseClicked
        userFoldersCount++;
        userFoldersPanel.setVisible(false);
    }//GEN-LAST:event_closeUserFoldersMouseClicked

    private void showMsgsFolder(int i, java.awt.event.MouseEvent evt) {
        if (evt.getClickCount() == 2 && currentUserFolders.length > i) {
            here.setVisible(false);
            here1.setVisible(false);
            here2.setVisible(false);
            here3.setVisible(false);
            here4.setVisible(false);
            here5.setVisible(true);
            userFoldersCount++;
            pageCounter = 1;
            pageNum.setText("1");
            Folder f = new Folder();
            recentFolder = "user folders\\" + currentUserFolders[i];
            f.dir = "user folders\\" + currentUserFolders[i];
            f.user = user[0];
            App a = new App();
            Filter sort = new Filter();
            if (sortOn.equals("subject")) {
                sort.subject = true;
            } else if (sortOn.equals("rec")) {
                sort.reciever = true;
            } else if (sortOn.equals("sender")) {
                sort.sender = true;
            } else if (sortOn.equals("date")) {
                sort.date = true;
            } else if (sortOn.equals("attach")) {
                sort.attach = true;
            } else if (sortOn.equals("priority")) {
                sort.priority = true;
            } else {
                sort.subject = true;
            }
            if (sortOrder.equals("a")) {
                sort.ascending = true;
            } else {
                sort.ascending = false;
            }
            a.setViewingOptions(f, null, sort);
            allMails = (Mail[]) a.listEmails(pageCounter);
            ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
            userFoldersPanel.setVisible(false);
        }
    }
    private void folder1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folder1MouseClicked
        showMsgsFolder(0, evt);
    }//GEN-LAST:event_folder1MouseClicked

    private void folder2MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folder2MouseClicked
        showMsgsFolder(1, evt);
    }//GEN-LAST:event_folder2MouseClicked

    private void folder3MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folder3MouseClicked
        showMsgsFolder(2, evt);
    }//GEN-LAST:event_folder3MouseClicked

    private void folder4MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folder4MouseClicked
        showMsgsFolder(3, evt);
    }//GEN-LAST:event_folder4MouseClicked

    private void folder5MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folder5MouseClicked
        showMsgsFolder(4, evt);
    }//GEN-LAST:event_folder5MouseClicked

    private void folder6MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folder6MouseClicked
        showMsgsFolder(5, evt);
    }//GEN-LAST:event_folder6MouseClicked

    private void folder7MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folder7MouseClicked
        showMsgsFolder(6, evt);
    }//GEN-LAST:event_folder7MouseClicked

    private void folder8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_folder8MouseClicked
        showMsgsFolder(7, evt);
    }//GEN-LAST:event_folder8MouseClicked

    private void jLabel1MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel1MouseEntered

    private void jLabel1MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel1MouseClicked
        /*        searchOn = "attach";
         choosenSearchFilter.setText(searchOn);
         searchOptions.setVisible(false);
         searchCount++;
         */
        searchOn = "msg";
        choosenSearchFilter.setText(searchOn);
        searchOptions.setVisible(false);
        searchCount++;
    }//GEN-LAST:event_jLabel1MouseClicked

    private void sortOptionSubjectMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortOptionSubjectMouseClicked
        sortCount++;
        sortOptions.setVisible(false);
        sortOn = "subject";
        pageCounter = 1;
        pageNum.setText("1");
        Filter f = new Filter();
        Filter s = new Filter();
        App a = new App();
        Folder fold = new Folder();
        fold.user = user[0];
        fold.dir = recentFolder;
        s.subject = true;
        if (sortOrder.equals("a")) {
            s.ascending = true;
        } else {
            s.ascending = false;
        }
        a.setViewingOptions(fold, null, s);
        ViewMessages vm = new ViewMessages(this);
        allMails = (Mail[]) a.listEmails(1);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
    }//GEN-LAST:event_sortOptionSubjectMouseClicked

    private void sortOptionRecieverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortOptionRecieverMouseClicked
        sortCount++;
        sortOptions.setVisible(false);
        sortOn = "rec";
        pageCounter = 1;
        pageNum.setText("1");
        Filter f = new Filter();
        Filter s = new Filter();
        App a = new App();
        Folder fold = new Folder();
        fold.user = user[0];
        fold.dir = recentFolder;
        s.reciever = true;
        if (sortOrder.equals("a")) {
            s.ascending = true;
        } else {
            s.ascending = false;
        }
        a.setViewingOptions(fold, null, s);
        ViewMessages vm = new ViewMessages(this);
        allMails = (Mail[]) a.listEmails(1);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
    }//GEN-LAST:event_sortOptionRecieverMouseClicked

    private void sortOptionDateMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortOptionDateMouseClicked
        sortCount++;
        sortOptions.setVisible(false);
        sortOn = "date";
        pageCounter = 1;
        pageNum.setText("1");
        Filter f = new Filter();
        Filter s = new Filter();
        App a = new App();
        Folder fold = new Folder();
        fold.user = user[0];
        fold.dir = recentFolder;
        s.date = true;
        if (sortOrder.equals("a")) {
            s.ascending = true;
        } else {
            s.ascending = false;
        }
        a.setViewingOptions(fold, null, s);
        ViewMessages vm = new ViewMessages(this);
        allMails = (Mail[]) a.listEmails(1);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
    }//GEN-LAST:event_sortOptionDateMouseClicked

    private void sortOptionImportanceMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortOptionImportanceMouseClicked
        sortCount++;
        sortOptions.setVisible(false);
        sortOn = "priority";
        pageCounter = 1;
        pageNum.setText("1");
        Filter f = new Filter();
        Filter s = new Filter();
        App a = new App();
        Folder fold = new Folder();
        fold.user = user[0];
        fold.dir = recentFolder;
        s.priority = true;
        if (sortOrder.equals("a")) {
            s.ascending = true;
        } else {
            s.ascending = false;
        }
        a.setViewingOptions(fold, null, s);
        ViewMessages vm = new ViewMessages(this);
        allMails = (Mail[]) a.listEmails(1);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
    }//GEN-LAST:event_sortOptionImportanceMouseClicked

    private void jLabel8MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel8MouseClicked
        sortCount++;
        sortOptions.setVisible(false);
        sortOn = "attach";
        pageCounter = 1;
        pageNum.setText("1");
        Filter f = new Filter();
        Filter s = new Filter();
        App a = new App();
        Folder fold = new Folder();
        fold.user = user[0];
        fold.dir = recentFolder;
        s.attach = true;
        if (sortOrder.equals("a")) {
            s.ascending = true;
        } else {
            s.ascending = false;
        }
        a.setViewingOptions(fold, null, s);
        ViewMessages vm = new ViewMessages(this);
        allMails = (Mail[]) a.listEmails(1);
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));
    }//GEN-LAST:event_jLabel8MouseClicked

    private void importantbMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_importantbMouseClicked
        here.setVisible(false);
        here1.setVisible(false);
        here2.setVisible(true);
        here3.setVisible(false);
        here4.setVisible(false);
        here5.setVisible(false);
        App a = new App();
        recentFolder = "imp";
        Filter sort = new Filter();
        Filter filter = new Filter();
        Folder f = new Folder();
        f.dir = "inbox";
        f.user = user[0];
        if (sortOn.equals("subject")) {
            sort.subject = true;
        } else if (sortOn.equals("rec")) {
            sort.reciever = true;
        } else if (sortOn.equals("sender")) {
            sort.sender = true;
        } else if (sortOn.equals("date")) {
            sort.date = true;
        } else if (sortOn.equals("attach")) {
            sort.attach = true;
        } else if (sortOn.equals("priority")) {
            sort.priority = true;
        } else {
            sort.subject = true;
        }
        filter.priority = true;
        filter.priority1 = 1;
        if (sortOrder.equals("a")) {
            sort.ascending = true;
        } else {
            sort.ascending = false;
        }
        a.setViewingOptions(f, filter, sort);
        ViewMessages vm = new ViewMessages(this);
        allMails = (Mail[]) a.listEmails(1);
        pageCounter = 1;
        ShowCheckBox(vm.showInTable(allMails, jTable1, recentFolder));

    }//GEN-LAST:event_importantbMouseClicked

    private void backgroundComposeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_backgroundComposeMouseClicked
        // TODO add your handling code here:
    }//GEN-LAST:event_backgroundComposeMouseClicked

    private void jLabel9MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseClicked
        searchOn = "attach";
        choosenSearchFilter.setText(searchOn);
        searchOptions.setVisible(false);
        searchCount++;
    }//GEN-LAST:event_jLabel9MouseClicked

    private void jLabel9MouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel9MouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel9MouseEntered

    private void subjectToFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_subjectToFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_subjectToFilterActionPerformed

    private void FolderToFilterActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_FolderToFilterActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_FolderToFilterActionPerformed

    private void jLabel13MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel13MouseClicked
        sortOrderCount++;
        if (sortOrderCount % 2 == 1) {
            sortingOrderPanel.setVisible(true);
        } else {
            sortingOrderPanel.setVisible(false);
        }
    }//GEN-LAST:event_jLabel13MouseClicked

    private void jLabel11MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel11MouseClicked
        sortOrder = "a";
        sortOrderCount++;
        sortingOrderPanel.setVisible(false);
    }//GEN-LAST:event_jLabel11MouseClicked

    private void jLabel12MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel12MouseClicked
        sortOrder = "d";
        sortOrderCount++;
        sortingOrderPanel.setVisible(false);
    }//GEN-LAST:event_jLabel12MouseClicked

    private void jLabel14MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel14MouseClicked
        filterMsgCount++;
        if (filterMsgCount % 2 == 1) {
            filterMessagesPanel.setVisible(true);
        } else {
            filterMessagesPanel.setVisible(false);
        }
    }//GEN-LAST:event_jLabel14MouseClicked

    private void jLabel16MouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_jLabel16MouseClicked
        File f = new File("server\\users\\" + user[0] + "\\user folders");
        String[] arr = f.list();
        boolean exists = false;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i].equalsIgnoreCase(FolderToFilter.getText())) {
                exists = true;
                break;
            }
        }
        if (!FolderToFilter.getText().isEmpty() && (arr.length < 8 || (arr.length == 8 && exists))) {
            try {
                FilterSubject fs = new FilterSubject();
                fs.writeToFilter(user[0], FolderToFilter.getText(), subjectToFilter.getText());
            } catch (IOException ex) {
                Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
            }
            filterMessagesPanel.setVisible(false);
            filterMsgCount++;
        }
    }//GEN-LAST:event_jLabel16MouseClicked

    private void sortbbMouseEntered(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_sortbbMouseEntered
        // TODO add your handling code here:
    }//GEN-LAST:event_sortbbMouseEntered

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(Inbox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(Inbox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(Inbox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(Inbox.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                try {
                    new Inbox().setVisible(true);
                } catch (IOException ex) {
                    Logger.getLogger(Inbox.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JTextField FolderToFilter;
    public static javax.swing.JLabel Star1;
    public javax.swing.JLabel Star10;
    public javax.swing.JLabel Star2;
    public javax.swing.JLabel Star3;
    public javax.swing.JLabel Star4;
    public javax.swing.JLabel Star5;
    public javax.swing.JLabel Star6;
    public javax.swing.JLabel Star7;
    public javax.swing.JLabel Star8;
    public javax.swing.JLabel Star9;
    private javax.swing.JLabel addToCompose;
    private javax.swing.JLabel addToCompose1;
    private javax.swing.JLabel allb;
    private javax.swing.JLabel background;
    private javax.swing.JLabel backgroundCompose;
    private javax.swing.JLabel backgroundLogOut;
    private javax.swing.JLabel backgroundMoveTo;
    private javax.swing.JLabel backgroundNewFolder;
    private javax.swing.JLabel backgroundSortOptions;
    private javax.swing.JLabel backgroundSortOptions1;
    private javax.swing.JLabel backgroundUserFolder;
    private javax.swing.JLabel changePp;
    private javax.swing.JCheckBox check1;
    private javax.swing.JCheckBox check10;
    private javax.swing.JCheckBox check2;
    private javax.swing.JCheckBox check3;
    private javax.swing.JCheckBox check4;
    private javax.swing.JCheckBox check5;
    private javax.swing.JCheckBox check6;
    private javax.swing.JCheckBox check7;
    private javax.swing.JCheckBox check8;
    private javax.swing.JCheckBox check9;
    private javax.swing.JLabel choosenSearchFilter;
    private javax.swing.JLabel closeUserFolders;
    public static javax.swing.JPanel composeOptions;
    private javax.swing.JLabel composeb;
    private javax.swing.JLabel contacts;
    private javax.swing.JLabel createFolder;
    private javax.swing.JLabel deleteUserFolder;
    private javax.swing.JLabel deleteb;
    private javax.swing.JLabel draftsb;
    private javax.swing.JLabel dragFrame;
    private javax.swing.JLabel error;
    private javax.swing.JLabel exit;
    private javax.swing.JPanel filterMessagesPanel;
    public static javax.swing.JLabel folder1;
    public static javax.swing.JLabel folder2;
    public static javax.swing.JLabel folder3;
    public static javax.swing.JLabel folder4;
    public static javax.swing.JLabel folder5;
    public static javax.swing.JLabel folder6;
    public static javax.swing.JLabel folder7;
    public static javax.swing.JLabel folder8;
    private javax.swing.JLabel here;
    private javax.swing.JLabel here1;
    private javax.swing.JLabel here2;
    private javax.swing.JLabel here3;
    private javax.swing.JLabel here4;
    private javax.swing.JLabel here5;
    private javax.swing.JLabel importantb;
    private javax.swing.JLabel inboxb;
    private javax.swing.JTextField indexDeleteUserFolder;
    private javax.swing.JComboBox jComboBox1;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel16;
    private javax.swing.JLabel jLabel17;
    private javax.swing.JLabel jLabel18;
    private javax.swing.JLabel jLabel19;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel20;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JLayeredPane jLayeredPane1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    public static javax.swing.JTable jTable1;
    public static javax.swing.JTextArea jTextArea1;
    private javax.swing.JLabel left;
    private javax.swing.JLabel logout;
    private javax.swing.JPanel logoutOptions;
    private javax.swing.JLabel man;
    private javax.swing.JPanel messagesPanel;
    private javax.swing.JLabel minimize;
    private javax.swing.JLabel moveToBtn;
    private javax.swing.JPanel moveToOptions;
    private javax.swing.JTextField moveToTF;
    private javax.swing.JTextField moveToTF1;
    private javax.swing.JLabel moveToTrash;
    private javax.swing.JLabel movetob;
    private javax.swing.JLabel msg;
    private javax.swing.JLabel msg1;
    private javax.swing.JLabel msg10;
    private javax.swing.JLabel msg2;
    private javax.swing.JLabel msg3;
    private javax.swing.JLabel msg4;
    private javax.swing.JLabel msg5;
    private javax.swing.JLabel msg6;
    private javax.swing.JLabel msg7;
    private javax.swing.JLabel msg8;
    private javax.swing.JLabel msg9;
    private javax.swing.JLabel newFolder;
    private javax.swing.JPanel newFolderOptions;
    private javax.swing.JPanel newMessageAnimation;
    private javax.swing.JLabel nextb;
    private javax.swing.JLabel noLogoutOption;
    private javax.swing.JLabel pageNum;
    private javax.swing.JLabel pip;
    private javax.swing.JLabel profilePic;
    private javax.swing.JLabel refresh;
    private javax.swing.JTextField search;
    private javax.swing.JLabel searchError;
    private javax.swing.JLabel searchFilter;
    private javax.swing.JLabel searchOptionDate;
    private javax.swing.JLabel searchOptionImportance;
    private javax.swing.JLabel searchOptionReciever;
    private javax.swing.JLabel searchOptionSender;
    private javax.swing.JLabel searchOptionSubject;
    private javax.swing.JPanel searchOptions;
    private javax.swing.JLabel searchb;
    private javax.swing.JPanel sendMessageAnimation;
    private javax.swing.JLabel sentmailb;
    private javax.swing.JLabel sortOptionDate;
    private javax.swing.JLabel sortOptionImportance;
    private javax.swing.JLabel sortOptionReciever;
    private javax.swing.JLabel sortOptionSender;
    private javax.swing.JLabel sortOptionSubject;
    private javax.swing.JPanel sortOptions;
    private javax.swing.JLabel sortbb;
    private javax.swing.JPanel sortingOrderPanel;
    private javax.swing.JLabel star;
    public static javax.swing.JTextField subjectCompose1;
    private javax.swing.JTextField subjectToFilter;
    public static javax.swing.JTextField toCompose;
    private javax.swing.JLabel trashb;
    public static javax.swing.JLabel txt1;
    public static javax.swing.JLabel txt2;
    public static javax.swing.JLabel txt3;
    public static javax.swing.JLabel txt4;
    public static javax.swing.JLabel txt5;
    public static javax.swing.JLabel txt6;
    public static javax.swing.JLabel txt7;
    public static javax.swing.JLabel txt8;
    private javax.swing.JLabel userEmail;
    private javax.swing.JLabel userFolders;
    private javax.swing.JPanel userFoldersPanel;
    private javax.swing.JLabel userName;
    private javax.swing.JLabel yesLogoutOption;
    // End of variables declaration//GEN-END:variables
}
