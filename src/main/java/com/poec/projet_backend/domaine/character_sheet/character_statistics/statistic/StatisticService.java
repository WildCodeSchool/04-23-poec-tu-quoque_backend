package com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import org.springframework.data.jpa.repository.JpaRepository;

public class StatisticService extends AbstractService<Statistic> {
    public StatisticService(JpaRepository<Statistic, Long> repository) {
        super(repository);
    }

    @Override
    public Statistic update(Long id, Statistic entity) {
        return null;
    }
}
