/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package linhnd.dtos;

import java.io.Serializable;

/**
 *
 * @author PC
 */
public class AccountCrearteError implements Serializable{
    private String emailIsExited;
    private String confirmNoMatched;
    private String passwordLeghtError;

    public AccountCrearteError() {
    }

    public String getEmailIsExited() {
        return emailIsExited;
    }

    public String getConfirmNoMatched() {
        return confirmNoMatched;
    }

    public String getPasswordLeghtError() {
        return passwordLeghtError;
    }

    public void setEmailIsExited(String emailIsExited) {
        this.emailIsExited = emailIsExited;
    }

    public void setConfirmNoMatched(String confirmNoMatched) {
        this.confirmNoMatched = confirmNoMatched;
    }

    public void setPasswordLeghtError(String passwordLeghtError) {
        this.passwordLeghtError = passwordLeghtError;
    }
    
    
    
}
