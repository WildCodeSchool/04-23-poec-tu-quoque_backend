package com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic;

public record StatisticDTO(
        Long id,
        int originalValue,
        int tempModifier,
        String abbr
        ) {
    public static StatisticDTO mapFromEntity(Statistic statistic) {
        return new StatisticDTO(
                statistic.getId(),
                statistic.getOriginalValue(),
                statistic.getTempModifier(),
                statistic.getAbbr()
        );
    }
}
