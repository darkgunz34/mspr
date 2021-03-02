package fr.mspr.service;

import fr.mspr.model.entities.Partenaire;
import fr.mspr.repositoy.PartenaireRepository;
import org.springframework.stereotype.Service;

@Service
public class PartenaireServiceImpl implements PartenaireService {

    private final PartenaireRepository partenaireRepository;

    public PartenaireServiceImpl(final PartenaireRepository partenaireRepository) {
        this.partenaireRepository = partenaireRepository;
    }

    @Override
    public void sauvegardePartenaire(final Partenaire p) {
        this.partenaireRepository.save(p);
    }
}
