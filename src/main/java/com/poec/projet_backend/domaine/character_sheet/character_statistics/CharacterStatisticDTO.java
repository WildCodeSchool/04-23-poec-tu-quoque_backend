package com.poec.projet_backend.domaine.character_sheet.character_statistics;

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

    public static CharacterStatistics mapFromDtoToEntity(CharacterStatisticDTO statsDTO, Long statsId) {
        return new CharacterStatistics(
                statsId,
                StatisticDTO.mapFromDtoToEntity(statsDTO.FOR()),
                StatisticDTO.mapFromDtoToEntity(statsDTO.DEX()),
                StatisticDTO.mapFromDtoToEntity(statsDTO.CON()),
                StatisticDTO.mapFromDtoToEntity(statsDTO.INT()),
                StatisticDTO.mapFromDtoToEntity(statsDTO.SAG()),
                StatisticDTO.mapFromDtoToEntity(statsDTO.CHA())
        );
    }
}
