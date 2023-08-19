package com.appli.springjwt.dto;

public class ModifiermotdepasseDto {

    String username;
    String oldpassword;
    String  newpassword;


    public ModifiermotdepasseDto() {
    }

    public ModifiermotdepasseDto(String username, String oldpassword, String newpassword) {
        this.username = username;
        this.oldpassword = oldpassword;
        this.newpassword = newpassword;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getOldpassword() {
        return oldpassword;
    }

    public void setOldpassword(String oldpassword) {
        this.oldpassword = oldpassword;
    }

    public String getNewpassword() {
        return newpassword;
    }

    public void setNewpassword(String newpassword) {
        this.newpassword = newpassword;
    }

    @Override
    public String toString() {
        return "Modifiermotdepasse{" +
                "username='" + username + '\'' +
                ", oldpassword='" + oldpassword + '\'' +
                ", newpassword='" + newpassword + '\'' +
                '}';
    }
}
