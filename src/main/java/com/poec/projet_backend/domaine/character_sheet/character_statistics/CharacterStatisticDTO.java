package com.poec.projet_backend.domaine.character_sheet.character_statistics;

import com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic.Statistic;

public record CharacterStatisticDTO(
        Long id,
        Statistic FOR,
        Statistic DEX,
        Statistic CON,
        Statistic INT,
        Statistic SAG,
        Statistic CHA
) {
    public static CharacterStatisticDTO mapFromEntity(CharacterStatistics stats) {
        return new CharacterStatisticDTO(
                stats.getId(),
                stats.getFOR(),
                stats.getDEX(),
                stats.getCON(),
                stats.getINT(),
                stats.getSAG(),
                stats.getCHA()
        );
    }
}
