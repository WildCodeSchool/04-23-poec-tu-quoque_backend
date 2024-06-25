package com.poec.projet_backend.domaine.character_sheet.skills;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.stereotype.Service;

@Service
public class SkillInfoEnteredByPlayerService extends AbstractService<SkillInfoEnteredByPlayer> {
    public SkillInfoEnteredByPlayerService(SkillInfoEnteredByPlayerRepository repository) {
        super(repository);
    }

    @Override
    public SkillInfoEnteredByPlayer update(Long id, SkillInfoEnteredByPlayer entity) {
        return repository.save(entity);
    }
}
