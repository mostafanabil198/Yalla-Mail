/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author arabtech
 */
public class ForgetPassword {

    public String[] Check(String email) {
        File usersDir = new File("server\\users");
        FileReader f = null;
        try {
            f = new FileReader(new File(usersDir.toString() + "\\users.txt"));
            BufferedReader bfRead = new BufferedReader(f);
            String usersReader;
            while ((usersReader = bfRead.readLine()) != null) {
                String[] user = usersReader.split("~");
                if (user[2].equals(email)) {
                    String[] retArray = new String[3];
                    retArray[0] = user[3];
                    retArray[1] = user[4];
                    retArray[2] = user[1];
                    return retArray;
                }
            }
        } catch (FileNotFoundException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(App.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    public String question(String[] array) {
        return array[0];
    }

    public Boolean answer(String answer, String[] array) {
        if (answer.equals(array[1])) {
            return true;
        } else {
            return false;
        }
    }

    public String showPassword(String[] array) {
        return array[2];
    }
}
