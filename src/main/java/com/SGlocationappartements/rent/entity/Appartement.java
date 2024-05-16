/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.rent.entity;

/**
 *
 * @author yassin
 */


import jakarta.persistence.*;
import java.util.List;

@Entity
public class Appartement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String type;
    private double surface;
    private int chambres;
    private boolean disponibilite;
    private boolean meublee;
    private double prix;
    private int villeId;
    private int quartierId;
    private String description;

    @OneToMany(mappedBy = "appartement", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Photos> photos;

    // Getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public double getSurface() {
        return surface;
    }

    public void setSurface(double surface) {
        this.surface = surface;
    }

    public int getChambres() {
        return chambres;
    }

    public void setChambres(int chambres) {
        this.chambres = chambres;
    }

    public boolean isDisponibilite() {
        return disponibilite;
    }

    public void setDisponibilite(boolean disponibilite) {
        this.disponibilite = disponibilite;
    }

    public boolean isMeublee() {
        return meublee;
    }

    public void setMeublee(boolean meublee) {
        this.meublee = meublee;
    }

    public double getPrix() {
        return prix;
    }

    public void setPrix(double prix) {
        this.prix = prix;
    }

    public int getVilleId() {
        return villeId;
    }

    public void setVilleId(int villeId) {
        this.villeId = villeId;
    }

    public int getQuartierId() {
        return quartierId;
    }

    public void setQuartierId(int quartierId) {
        this.quartierId = quartierId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Photos> getPhotos() {
        return photos;
    }

    public void setPhotos(List<Photos> photos) {
        this.photos = photos;
    }
}
