/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.SGlocationappartements.demo.service;

/**
 *
 * @author yassin
 */


import com.SGlocationappartements.demo.entity.Appartement;
import com.SGlocationappartements.demo.entity.Photos;
import com.SGlocationappartements.demo.repository.AppartementRepository;
import com.SGlocationappartements.demo.repository.PhotosRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AppartementService {

    @Autowired
    private AppartementRepository appartementRepository;

    @Autowired
    private PhotosRepository photosRepository;

    public List<Appartement> getAllAppartements() {
        return appartementRepository.findAll();
    }

    public Optional<Appartement> getAppartementById(Long id) {
        return appartementRepository.findById(id);
    }

    public Appartement addAppartement(Appartement appartement) {
        return appartementRepository.save(appartement);
    }

    public boolean deleteAppartement(Long id) {
        if (appartementRepository.existsById(id)) {
            appartementRepository.deleteById(id);
            return true;
        }
        return false;
    }

    public boolean updateAppartement(Appartement appartement) {
        if (appartementRepository.existsById(appartement.getId())) {
            appartementRepository.save(appartement);
            return true;
        }
        return false;
    }

    public Photos addPhoto(Long appartementId, String path) {
        Appartement appartement = appartementRepository.findById(appartementId).orElseThrow();
        Photos photo = new Photos();
        photo.setPath(path);
        photo.setAppartement(appartement);
        return photosRepository.save(photo);
    }

    public List<Photos> getPhotosByAppartementId(Long appartementId) {
        return photosRepository.findAll();
    }
}
