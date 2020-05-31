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
public class AccountCrearteError implements Serializable {

    private String emailIsExited, passwordLeghtError, confirmNoMatched;

    public AccountCrearteError() {
    }

    public AccountCrearteError(String emailIsExited, String passwordLeghtError, String confirmNoMatched) {
        this.emailIsExited = emailIsExited;
        this.passwordLeghtError = passwordLeghtError;
        this.confirmNoMatched = confirmNoMatched;
    }

    public String getEmailIsExited() {
        return emailIsExited;
    }

    public String getPasswordLeghtError() {
        return passwordLeghtError;
    }

    public String getConfirmNoMatched() {
        return confirmNoMatched;
    }

    public void setEmailIsExited(String emailIsExited) {
        this.emailIsExited = emailIsExited;
    }

    public void setPasswordLeghtError(String passwordLeghtError) {
        this.passwordLeghtError = passwordLeghtError;
    }

    public void setConfirmNoMatched(String confirmNoMatched) {
        this.confirmNoMatched = confirmNoMatched;
    }

}
