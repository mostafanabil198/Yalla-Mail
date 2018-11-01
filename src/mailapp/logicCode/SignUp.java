/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package mailapp.logicCode;

/**
 *
 * @author arabtech
 */
public class SignUp {

    public Contact info(String username, String email1, String password1, String question, String answer) {
        Contact user = new Contact();
        user.name = username;
        user.email = email1;
        user.password = password1;
        user.ques = question;
        user.ans = answer;
        return user;
    }

    public boolean checksign(Contact c) {
        App x = new App();
        if (x.signup(c)) {
            return true;
        }
        return false;
    }
    /*if(checksign(info())){

     }
     else(){
     sout("error");
     }
     */
}
