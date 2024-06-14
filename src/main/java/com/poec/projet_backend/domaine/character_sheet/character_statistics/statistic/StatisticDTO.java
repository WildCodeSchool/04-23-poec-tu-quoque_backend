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

    public static Statistic mapFromDtoToEntity(StatisticDTO statisticDTO) {
        return new Statistic(
                statisticDTO.id(),
                statisticDTO.originalValue(),
                statisticDTO.tempModifier(),
                statisticDTO.abbr()
        );
    }
}

