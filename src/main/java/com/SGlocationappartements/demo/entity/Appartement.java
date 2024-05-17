package com.SGlocationappartements.demo.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "appartement")
public class Appartement {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type", nullable = false)
    private String type;

    @Column(name = "description")
    private String description;

    @Column(name = "surface", nullable = false)
    private float surface;

    @Column(name = "chambres", nullable = false)
    private int chambres;

    @Column(name = "disponibilite", nullable = false)
    private boolean disponibilite;

    @Column(name = "meublee", nullable = false)
    private boolean meublee;

    @Column(name = "prix", nullable = false)
    private float prix;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "ville_id")
    private Ville ville;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "quartier_id")
    private Quartier quartier;

    @Column(name = "photo")
    private String photo;

    // Getters and Setters
    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public float getSurface() {
        return surface;
    }

    public void setSurface(float surface) {
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

    public float getPrix() {
        return prix;
    }

    public void setPrix(float prix) {
        this.prix = prix;
    }

    public Ville getVille() {
        return ville;
    }

    public void setVille(Ville ville) {
        this.ville = ville;
    }

    public Quartier getQuartier() {
        return quartier;
    }

    public void setQuartier(Quartier quartier) {
        this.quartier = quartier;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
