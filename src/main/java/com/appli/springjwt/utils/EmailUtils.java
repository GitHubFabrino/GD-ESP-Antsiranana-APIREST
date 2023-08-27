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

    public static  String getEmailMessageEtudiant(
            String nom,String prenom, String password ,String pseudo,
            String nomAU , String mention ,String parcoursAcronyme, String parcours ,String niveau ){
        return "Objet : Enregistrement réussi - Bienvenue sur notre plateforme !\n\n"+

                "Cher(e) "+ prenom+" ,\n"+

                "Félicitations ! Votre inscription sur notre plateforme a été un succès. Voici les détails de votre compte :\n\n"+

        "Nom : "+nom+"\n\n"+
        "Prénom : "+prenom+"\n\n"+
        "Année universitaire : "+nomAU+"\n\n"+
        "Mention : "+mention+"\n\n"+
        "Parcours : "+parcoursAcronyme+"\n\n"+
        "Parcours Acronyme : "+parcours+"\n\n"+
        "Niveau : "+niveau+"\n\n"+

        "Ces informations seront nécessaires pour accéder à votre compte " +
                "et profiter de tous les avantages de notre plateforme.\n"+

        "Nous vous encourageons maintenant à effectuer les étapes d'inscription " +
                "administrative et pédagogique en utilisant les informations de connexion suivantes :\n\n"+

        "Pseudo : "+pseudo+"\n\n"+
        "Mot de passe : "+password+"\n\n"+

        "L'inscription administrative et pédagogique est une étape importante pour commencer votre parcours " +
                "académique en toute simplicité. N'hésitez pas à suivre les instructions fournies sur notre " +
                "plateforme pour accomplir ces étapes.\n\n"+

                "Cordialement,\n"+
                "ESP Antsiranana "
        ;
    }
}
