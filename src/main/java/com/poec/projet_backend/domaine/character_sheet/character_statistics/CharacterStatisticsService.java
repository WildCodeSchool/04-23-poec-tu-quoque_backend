package com.poec.projet_backend.domaine.character_sheet.character_statistics;

import com.poec.projet_backend.domaine.abstract_package.AbstractService;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic.StatisticService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class CharacterStatisticsService extends AbstractService<CharacterStatistics> {

    @Autowired
    private StatisticService statisticService;

    public CharacterStatisticsService(JpaRepository<CharacterStatistics, Long> repository) {
        super(repository);
    }

    @Override
    public CharacterStatistics update(Long id, CharacterStatistics entity) {
        statisticService.update(entity.getFOR().getId(), entity.getFOR());
        statisticService.update(entity.getDEX().getId(), entity.getDEX());
        statisticService.update(entity.getCON().getId(), entity.getCON());
        statisticService.update(entity.getINT().getId(), entity.getINT());
        statisticService.update(entity.getSAG().getId(), entity.getSAG());
        statisticService.update(entity.getCHA().getId(), entity.getCHA());
        return repository.save(entity);
    }
}
