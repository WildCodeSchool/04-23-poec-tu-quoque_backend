package com.poec.projet_backend.domaine.character_sheet.character_statistics;

import com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic.Statistic;
import com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic.StatisticDTO;

public record CharacterStatisticDTO(
        Long id,
        StatisticDTO FOR,
        StatisticDTO DEX,
        StatisticDTO CON,
        StatisticDTO INT,
        StatisticDTO SAG,
        StatisticDTO CHA
) {
    public static CharacterStatisticDTO mapFromEntity(CharacterStatistics stats) {
        return new CharacterStatisticDTO(
                stats.getId(),
                StatisticDTO.mapFromEntity(stats.getFOR()),
                StatisticDTO.mapFromEntity(stats.getDEX()),
                StatisticDTO.mapFromEntity(stats.getCON()),
                StatisticDTO.mapFromEntity(stats.getINT()),
                StatisticDTO.mapFromEntity(stats.getSAG()),
                StatisticDTO.mapFromEntity(stats.getCHA())
        );
    }
}
