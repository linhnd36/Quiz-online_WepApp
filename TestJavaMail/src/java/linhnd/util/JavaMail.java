/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.util;

import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;

/**
 *
 * @author PC
 */
public class JavaMail {
    public static void main(String[] args) {
        try {
            JavaMailUtil.sendMail("linhnguyen131999@gmail.com");
        } catch (MessagingException ex) {
            Logger.getLogger(JavaMail.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
