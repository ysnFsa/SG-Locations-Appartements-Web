package com.SGlocationappartements.demo.service;

import com.SGlocationappartements.demo.entity.Ville;
import com.SGlocationappartements.demo.repository.VilleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class VilleService {

    @Autowired
    private VilleRepository villeRepository;

    public Optional<Ville> getVilleByName(String name) {
        return villeRepository.findByName(name);
    }

    public Optional<Ville> getVilleById(Long id) {
        return villeRepository.findById(id);
    }
}
