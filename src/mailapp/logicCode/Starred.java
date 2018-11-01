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
import javax.swing.JFrame;
import linkedList.cs45_cs23.LinkedList;
import mailapp.gui.Inbox;

/**
 *
 * @author arabtech
 */
public class Starred {

    public void ShowStars(Mail[] mailArr, JFrame frame) {
        Inbox in = (Inbox) frame;
        LinkedList myMails = new LinkedList();
        for (int i = 0; i < mailArr.length; i++) {
            if (mailArr[i] != null) {
                myMails.add(mailArr[i]);
            }
        }
        int size = myMails.size();
        in.Star1.setVisible(false);
        in.Star2.setVisible(false);
        in.Star3.setVisible(false);
        in.Star4.setVisible(false);
        in.Star5.setVisible(false);
        in.Star6.setVisible(false);
        in.Star7.setVisible(false);
        in.Star8.setVisible(false);
        in.Star9.setVisible(false);
        in.Star10.setVisible(false);

        if (size >= 1) {
            if (((Mail) myMails.get(0)).starred == 0) {
                in.Star1.setVisible(false);
            } else if (((Mail) myMails.get(0)).starred == 1) {
                in.Star1.setVisible(true);
            }
        }
        if (size >= 2) {
            if (((Mail) myMails.get(1)).starred == 0) {
                in.Star2.setVisible(false);
            } else if (((Mail) myMails.get(1)).starred == 1) {
                in.Star2.setVisible(true);
            }
        }
        if (size >= 3) {
            if (((Mail) myMails.get(2)).starred == 0) {
                in.Star3.setVisible(false);
            } else if (((Mail) myMails.get(2)).starred == 1) {
                in.Star3.setVisible(true);
            }
        }
        if (size >= 4) {
            if (((Mail) myMails.get(3)).starred == 0) {
                in.Star4.setVisible(false);
            } else if (((Mail) myMails.get(3)).starred == 1) {
                in.Star4.setVisible(true);
            }
        }
        if (size >= 5) {
            if (((Mail) myMails.get(4)).starred == 0) {
                in.Star5.setVisible(false);
            } else if (((Mail) myMails.get(4)).starred == 1) {
                in.Star5.setVisible(true);
            }
        }
        if (size >= 6) {
            if (((Mail) myMails.get(5)).starred == 0) {
                in.Star6.setVisible(false);
            } else if (((Mail) myMails.get(5)).starred == 1) {
                in.Star6.setVisible(true);
            }
        }
        if (size >= 7) {
            if (((Mail) myMails.get(6)).starred == 0) {
                in.Star7.setVisible(false);
            } else if (((Mail) myMails.get(6)).starred == 1) {
                in.Star7.setVisible(true);
            }
        }
        if (size >= 8) {
            if (((Mail) myMails.get(7)).starred == 0) {
                in.Star8.setVisible(false);
            } else if (((Mail) myMails.get(7)).starred == 1) {
                in.Star8.setVisible(true);
            }
        }
        if (size >= 9) {
            if (((Mail) myMails.get(8)).starred == 0) {
                in.Star9.setVisible(false);
            } else if (((Mail) myMails.get(8)).starred == 1) {
                in.Star9.setVisible(true);
            }
        }
        if (size >= 10) {
            if (((Mail) myMails.get(9)).starred == 0) {
                in.Star10.setVisible(false);
            } else if (((Mail) myMails.get(9)).starred == 1) {
                in.Star10.setVisible(true);
            }
        }
    }

    public void clickStarred(LinkedList checked, JFrame frame) throws FileNotFoundException, IOException {
        Inbox in = (Inbox) frame;
        Helper h = new Helper();
        File fromIndex;
        if (!in.recentFolder.contains("user folders")) {
            fromIndex = new File("server\\users\\" + in.user[0] + "\\" + in.recentFolder + "\\" + in.recentFolder + ".txt");
        } else {
            String[] split = in.recentFolder.split("\\\\");
            fromIndex = new File("server\\users\\" + in.user[0] + "\\" + in.recentFolder + "\\" + split[1] + ".txt");
        }
        File tempIndex = new File("server\\users\\" + in.user[0] + "\\" + in.recentFolder + "temp.txt");
        if (in.recentFolder != "trash") {
            while (!checked.isEmpty()) {
                BufferedReader bf = new BufferedReader(new FileReader(fromIndex));
                String line;
                while ((line = bf.readLine()) != null) {
                    String[] msgDetails = line.split("~");
                    if (msgDetails[7].equals(((MoveClass) checked.get(0)).subDate)) {
                        if (msgDetails[6].equals("1")) {
                            BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));
                            String newLine = msgDetails[0] + "~" + msgDetails[1] + "~" + msgDetails[2] + "~" + msgDetails[3] + "~" + msgDetails[4] + "~" + msgDetails[5] + "~" + "0" + "~" + msgDetails[7];
                            bftemp.append(newLine);
                            bftemp.newLine();
                            bftemp.close();
                        } else {
                            BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));
                            String newLine = msgDetails[0] + "~" + msgDetails[1] + "~" + msgDetails[2] + "~" + msgDetails[3] + "~" + msgDetails[4] + "~" + msgDetails[5] + "~" + "1" + "~" + msgDetails[7];
                            bftemp.append(newLine);
                            bftemp.newLine();
                            bftemp.close();
                        }
                    } else {
                        BufferedWriter bftemp = new BufferedWriter(new FileWriter(tempIndex, true));
                        bftemp.append(line);
                        bftemp.newLine();
                        bftemp.close();
                    }
                }
                bf.close();
                fromIndex.delete();
                tempIndex.renameTo(fromIndex);

                checked.remove(0);
            }
        }
    }
}
