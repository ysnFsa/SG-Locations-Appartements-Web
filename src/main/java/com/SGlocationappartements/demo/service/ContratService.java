package com.SGlocationappartements.demo.service;

import com.SGlocationappartements.demo.entity.Contrat;
import com.SGlocationappartements.demo.repository.ContratRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ContratService {

    @Autowired
    private ContratRepository contratRepository;

    public List<Contrat> getAllContrats() {
        return contratRepository.findAll();
    }

    public Optional<Contrat> getContratById(Long id) {
        return contratRepository.findById(id);
    }

    public Contrat addContrat(Contrat contrat) {
        return contratRepository.save(contrat);
    }

    public boolean updateContrat(Contrat contrat) {
        if (contratRepository.existsById(contrat.getId())) {
            contratRepository.save(contrat);
            return true;
        }
        return false;
    }

    public boolean deleteContrat(Long id) {
        if (contratRepository.existsById(id)) {
            contratRepository.deleteById(id);
            return true;
        }
        return false;
    }
}
