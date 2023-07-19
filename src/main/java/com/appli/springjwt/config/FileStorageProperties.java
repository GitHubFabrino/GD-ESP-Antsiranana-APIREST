package com.appli.springjwt.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties(prefix = "file")
public class FileStorageProperties {
    private String emplacementStockageFichiers;

    public String getEmplacementStockageFichiers() {
        return emplacementStockageFichiers;
    }

    public void setEmplacementStockageFichiers(String emplacementStockageFichiers) {
        this.emplacementStockageFichiers = emplacementStockageFichiers;
    }

}