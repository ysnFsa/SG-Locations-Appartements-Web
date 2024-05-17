package com.SGlocationappartements.demo.service;

import com.SGlocationappartements.demo.entity.Quartier;
import com.SGlocationappartements.demo.repository.QuartierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class QuartierService {

    @Autowired
    private QuartierRepository quartierRepository;

    public Optional<Quartier> getQuartierByName(String name) {
        return quartierRepository.findByName(name);
    }

    public Optional<Quartier> getQuartierById(Long id) {
        return quartierRepository.findById(id);
    }
}
