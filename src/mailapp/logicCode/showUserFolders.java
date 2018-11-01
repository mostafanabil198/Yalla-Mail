/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import mailapp.gui.Inbox;

/**
 *
 * @author arabtech
 */
public class showUserFolders {

    Inbox in;

    public void show(JFrame frame) throws IOException {
        in = (Inbox) frame;
        File pic = new File("img\\folder.png");
        File userFolder = new File("server\\users\\" + in.user[0] + "\\user folders");
        String[] folders = userFolder.list();
        int i = 0;
        for (i = 0; i < folders.length && folders != null; i++) {
            if (i == 0) {
                Image img = ImageIO.read(pic);
                Image newImg = img.getScaledInstance(100, 90, Image.SCALE_AREA_AVERAGING);
                in.folder1.setVisible(true);
                in.folder1.setIcon(new ImageIcon(newImg));
                in.txt1.setVisible(true);
                in.txt1.setText((i+1) + "-"  + folders[i]);
            }
            if (i == 1) {
                Image img = ImageIO.read(pic);
                Image newImg = img.getScaledInstance(100, 90, Image.SCALE_AREA_AVERAGING);
                in.folder2.setVisible(true);
                in.folder2.setIcon(new ImageIcon(newImg));
                in.txt2.setVisible(true);
                in.txt2.setText((i+1) + "-"  + folders[i]);
            }
            if (i == 2) {
                Image img = ImageIO.read(pic);
                Image newImg = img.getScaledInstance(100, 90, Image.SCALE_AREA_AVERAGING);
                in.folder3.setVisible(true);
                in.folder3.setIcon(new ImageIcon(newImg));
                in.txt3.setVisible(true);
                in.txt3.setText((i+1) + "-"  + folders[i]);
            }
            if (i == 3) {
                Image img = ImageIO.read(pic);
                Image newImg = img.getScaledInstance(100, 90, Image.SCALE_AREA_AVERAGING);
                in.folder4.setVisible(true);
                in.folder4.setIcon(new ImageIcon(newImg));
                in.txt4.setVisible(true);
                in.txt4.setText((i+1) + "-"  + folders[i]);
            }
            if (i == 4) {
                Image img = ImageIO.read(pic);
                Image newImg = img.getScaledInstance(100, 90, Image.SCALE_AREA_AVERAGING);
                in.folder5.setVisible(true);
                in.folder5.setIcon(new ImageIcon(newImg));
                in.txt5.setVisible(true);
                in.txt5.setText((i+1) + "-"  + folders[i]);
            }
            if (i == 5) {
                Image img = ImageIO.read(pic);
                Image newImg = img.getScaledInstance(100, 90, Image.SCALE_AREA_AVERAGING);
                in.folder6.setVisible(true);
                in.folder6.setIcon(new ImageIcon(newImg));
                in.txt6.setVisible(true);
                in.txt6.setText((i+1) + "-"  + folders[i]);
            }
            if (i == 6) {
                Image img = ImageIO.read(pic);
                Image newImg = img.getScaledInstance(100, 90, Image.SCALE_AREA_AVERAGING);
                in.folder7.setVisible(true);
                in.folder7.setIcon(new ImageIcon(newImg));
                in.txt7.setVisible(true);
                in.txt7.setText((i+1) + "-"  + folders[i]);
            }
            if (i == 7) {
                Image img = ImageIO.read(pic);
                Image newImg = img.getScaledInstance(100, 90, Image.SCALE_AREA_AVERAGING);
                in.folder8.setVisible(true);
                in.folder8.setIcon(new ImageIcon(newImg));
                in.txt8.setText((i+1) + "-"  + folders[i]);
                in.txt8.setVisible(true);
            }
        }
        for (int y = i; i < 8; i++) {
            if (y == 0) {
                in.folder1.setVisible(false);
                in.txt1.setVisible(false);
            }
            if (y == 1) {
                in.folder2.setVisible(false);
                in.txt2.setVisible(false);
            }
            if (y == 2) {
                in.folder3.setVisible(false);
                in.txt3.setVisible(false);
            }
            if (y == 3) {
                in.folder4.setVisible(false);
                in.txt4.setVisible(false);
            }
            if (y == 4) {
                in.folder5.setVisible(false);
                in.txt5.setVisible(false);
            }
            if (y == 5) {
                in.folder6.setVisible(false);
                in.txt6.setVisible(false);
            }
            if (y == 6) {
                in.folder7.setVisible(false);
                in.txt7.setVisible(false);
            }
            if (y == 7) {
                in.folder8.setVisible(false);
                in.txt8.setVisible(false);
            }

        }
        in.currentUserFolders = folders;
    }
    
}
