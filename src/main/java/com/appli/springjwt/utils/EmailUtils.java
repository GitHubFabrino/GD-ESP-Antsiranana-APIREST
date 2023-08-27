package com.appli.springjwt.utils;

public class EmailUtils {
    public static  String getEmailMessage(String nom,String prenom, String password ,String pseudo){
        return "Objet : Confirmation d'enregistrement de compte réussi" + "\n\n" + "Cher(e) " + prenom + " " + nom +", \n\n"+
                "Nous sommes ravis de vous informer que votre compte a été enregistré avec succès sur notre plateforme." +
                " Nous tenons à vous souhaiter la bienvenue dans notre communauté ! \n\n"+
                "Voici vos informations de compte :\n\n" +
                "Nom d'utilisateur : " +pseudo+ " \n" +
                "Mot de passe : " + password + "\n\n"+
                "Désormais, vous pouvez accéder à votre compte en utilisant ces informations.\n\n"+
                "Cordialement,\n" +
                "L'équipe de ESP Antsiranana"
                ;
    }
}
