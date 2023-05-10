/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 *
 * @author Salim Ben Hamida
 */
public class RememberPassword {

    String email, token, created_at;

    public RememberPassword(String email) {
        this.email = email;
        this.token = usingRandomUUID();;
        this.created_at = getSysDate();;
    }

    public RememberPassword(String email, String token, String created_at) {
        this.email = email;
        this.token = token;
        this.created_at = created_at;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCreated_at() {
        return created_at;
    }

    public void setCreated_at(String created_at) {
        this.created_at = created_at;
    }

    public String usingRandomUUID() {

        UUID randomUUID = UUID.randomUUID();

        return randomUUID.toString().replaceAll("_", "");

    }

    public String getSysDate() {
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");
        Date date = new Date();
        return formatter.format(date);
    }

    @Override
    public String toString() {
        return "RememberPassword{" + "email=" + email + ", token=" + token + ", created_at=" + created_at + '}';
    }
}
