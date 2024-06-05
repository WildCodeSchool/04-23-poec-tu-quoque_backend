package com.poec.projet_backend.domaine.character_sheet.character_statistics;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

public class CharacterStatisticsService extends AbstractService<CharacterStatistics> {
    public CharacterStatisticsService(JpaRepository<CharacterStatistics, Long> repository) {
        super(repository);
    }

    @Override
    public CharacterStatistics update(Long id, CharacterStatistics entity) {
        return null;
    }
}
