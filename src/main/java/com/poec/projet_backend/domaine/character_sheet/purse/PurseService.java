package com.poec.projet_backend.domaine.character_sheet.purse;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

public class PurseService extends AbstractService<Purse> {
    public PurseService(JpaRepository<Purse, Long> repository) {
        super(repository);
    }

    @Override
    public Purse update(Long id, Purse entity) {
        return null;
    }
}
