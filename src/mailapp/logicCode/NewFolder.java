/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 *
 * @author arabtech
 */
public class NewFolder {

    public boolean CreateFolder(String folderName, String user) throws IOException {

        String mailDir = "server\\users\\";
        String userDir = mailDir + user;
        File userFolders = new File(userDir + "\\user folders");
        String[] folders = userFolders.list();
        if (folders != null) {

            for (int i = 0; i < folders.length; i++) {
                if (folders[i].equalsIgnoreCase(folderName)) {
                    return false;
                }
            }
        }
        if(folders.length<8){
        new File(userDir + "\\user folders\\" + folderName).mkdirs();
        BufferedWriter bw = new BufferedWriter(new FileWriter(new File(userDir + "\\user folders\\" + folderName + "\\" + folderName + ".txt")));
        bw.close();
        return true;

    }
        return false;
    }
}
