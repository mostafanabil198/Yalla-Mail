/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import java.awt.Image;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import linkedList.cs45_cs23.LinkedList;
import mailapp.gui.AddAttachmentFrame;
import mailapp.gui.ShowMessage;
import queue.cs18_cs23_cs45_cs52.Qlinked;

/**
 *
 * @author arabtech
 */
public class ComposeGUI {

    App a = new App();

    public Boolean check(String email) throws FileNotFoundException, IOException {
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
        return userExists;
    }

    public boolean checkExist(LinkedList names, String email) {
        int i = 0;
        while (i != names.size()) {
            String x = (String) names.get(i);
            if (x.equalsIgnoreCase(email)) {
                return false;
            }
            i++;
        }
        return true;
    }

    public void addName(LinkedList names, String email) {
        names.add((String) email);

    }

    public String showNames(LinkedList names) {
        String show = "";
        int i = 0;
        while (i != names.size()) {
            show += (i + 1) + "-" + (String) names.get(i) + "\n";
            i++;
        }
        return show;
    }

    public String showAttach(LinkedList attach) {
        String show = "";
        int i = 0;
        while (i != attach.size()) {
            show += (i + 1) + "-" + (String) ((File) attach.get(i)).getPath() + "\n";
            i++;
        }
        return show;
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
        a.compose(m);
        return true;
    }

    public boolean removeName(LinkedList names, int index) {
        if (index - 1 >= names.size() || index - 1 < 0) {
            return false;
        } else {
            names.remove(index - 1);
            return true;
        }
    }

    public void addAttachment(LinkedList attach, String attachPath, boolean attachbool) {
        File attachments = new File(attachPath);
        attach.add(attachments);
        attachbool = true;
    }

    public boolean removeAttachment(LinkedList attach, int index, boolean attachbool) {
        if (index - 1 >= attach.size() || index - 1 < 0) {
            return false;
        } else {
            attach.remove(index - 1);
            if (attach.isEmpty()) {
                attachbool = false;
            }
            return true;
        }
    }

    public void moveDraft(String sender1, String subject1, String body, int priority1, LinkedList names, LinkedList attachments1, boolean attach) throws IOException {
        Helper h = new Helper();
        String x = "";
        int i = 0;
        while (i != names.size()) {
            x += (String) names.get(i);
            if (i != names.size() - 1) {
                x += "-";
            }
            i++;
        }
        String date = h.todayDate();
        h.moveToDraft(subject1, sender1, x, attach, body, date, priority1, 0,attachments1);
    }

    public void loadAttachment(LinkedList files, JFrame frame) throws IOException {
        AddAttachmentFrame add = (AddAttachmentFrame) frame;
        int count = 0;
        while (count != files.size()) {
            File f = (File) files.get(count);
            if (count == 0) {
                add.attach1.setVisible(true);
                add.na1.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach1.setIcon(new ImageIcon(newimg));
                    add.na1.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach1.setIcon(new ImageIcon(newimg));
                    add.na1.setText(f.getName());

                }
            }
            if (count == 1) {
                add.attach2.setVisible(true);
                add.na2.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach2.setIcon(new ImageIcon(newimg));
                    add.na2.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach2.setIcon(new ImageIcon(newimg));
                    add.na2.setText(f.getName());

                }
            }
            if (count == 2) {
                add.attach3.setVisible(true);
                add.na3.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach3.setIcon(new ImageIcon(newimg));
                    add.na3.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach3.setIcon(new ImageIcon(newimg));
                    add.na3.setText(f.getName());

                }
            }
            if (count == 3) {
                add.attach4.setVisible(true);
                add.na4.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach4.setIcon(new ImageIcon(newimg));
                    add.na4.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach4.setIcon(new ImageIcon(newimg));
                    add.na4.setText(f.getName());

                }
            }
            if (count == 4) {
                add.attach5.setVisible(true);
                add.na5.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach5.setIcon(new ImageIcon(newimg));
                    add.na5.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach5.setIcon(new ImageIcon(newimg));
                    add.na5.setText(f.getName());

                }
            }
            if (count == 5) {
                add.attach6.setVisible(true);
                add.na6.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach6.setIcon(new ImageIcon(newimg));
                    add.na6.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach6.setIcon(new ImageIcon(newimg));
                    add.na6.setText(f.getName());

                }
            }
            count++;
        }
        while (count < 6) {
            if (count == 0) {
                add.attach1.setVisible(false);
                add.na1.setVisible(false);
            }
            if (count == 1) {
                add.attach2.setVisible(false);
                add.na2.setVisible(false);
            }
            if (count == 2) {
                add.attach3.setVisible(false);
                add.na3.setVisible(false);
            }
            if (count == 3) {
                add.attach4.setVisible(false);
                add.na4.setVisible(false);
            }
            if (count == 4) {
                add.attach5.setVisible(false);
                add.na5.setVisible(false);
            }
            if (count == 5) {
                add.attach6.setVisible(false);
                add.na6.setVisible(false);
            }
            count++;
        }
    }
    public void loadMessage(LinkedList files, JFrame frame) throws IOException {
        ShowMessage add = (ShowMessage) frame;
        int count = 0;
        while (count != files.size()) {
            File f = (File) files.get(count);
            if (count == 0) {
                add.attach1.setVisible(true);
                add.na1.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    //System.out.println(f.toString());
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach1.setIcon(new ImageIcon(newimg));
                    add.na1.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach1.setIcon(new ImageIcon(newimg));
                    add.na1.setText(f.getName());

                }
            }
            if (count == 1) {
                add.attach2.setVisible(true);
                add.na2.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach2.setIcon(new ImageIcon(newimg));
                    add.na2.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach2.setIcon(new ImageIcon(newimg));
                    add.na2.setText(f.getName());

                }
            }
            if (count == 2) {
                add.attach3.setVisible(true);
                add.na3.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach3.setIcon(new ImageIcon(newimg));
                    add.na3.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach3.setIcon(new ImageIcon(newimg));
                    add.na3.setText(f.getName());

                }
            }
            if (count == 3) {
                add.attach4.setVisible(true);
                add.na4.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach4.setIcon(new ImageIcon(newimg));
                    add.na4.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach4.setIcon(new ImageIcon(newimg));
                    add.na4.setText(f.getName());

                }
            }
            if (count == 4) {
                add.attach5.setVisible(true);
                add.na5.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach5.setIcon(new ImageIcon(newimg));
                    add.na5.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach5.setIcon(new ImageIcon(newimg));
                    add.na5.setText(f.getName());

                }
            }
            if (count == 5) {
                add.attach6.setVisible(true);
                add.na6.setVisible(true);
                if (f.getName().contains("jpg") || f.getName().contains("png")) {
                    Image img = ImageIO.read(f);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach6.setIcon(new ImageIcon(newimg));
                    add.na6.setText(f.getName());
                } else {
                    File f2 = new File("img\\error.png");
                    Image img = ImageIO.read(f2);
                    Image newimg = img.getScaledInstance(110,70, Image.SCALE_AREA_AVERAGING);
                    add.attach6.setIcon(new ImageIcon(newimg));
                    add.na6.setText(f.getName());

                }
            }
            count++;
        }
        while (count < 6) {
            if (count == 0) {
                add.attach1.setVisible(false);
                add.na1.setVisible(false);
            }
            if (count == 1) {
                add.attach2.setVisible(false);
                add.na2.setVisible(false);
            }
            if (count == 2) {
                add.attach3.setVisible(false);
                add.na3.setVisible(false);
            }
            if (count == 3) {
                add.attach4.setVisible(false);
                add.na4.setVisible(false);
            }
            if (count == 4) {
                add.attach5.setVisible(false);
                add.na5.setVisible(false);
            }
            if (count == 5) {
                add.attach6.setVisible(false);
                add.na6.setVisible(false);
            }
            count++;
        }
    }
}
