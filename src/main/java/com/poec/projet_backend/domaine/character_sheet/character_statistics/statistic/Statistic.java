package com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@Entity
@RequiredArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int originalValue;
    private int tempModifier;
    private String abbr;

    public Statistic(int originalValue, int tempModifier, String abbr) {
        this.originalValue = originalValue;
        this.tempModifier = tempModifier;
        this.abbr = abbr;
    }

    public Statistic(String abbr) {
        this.abbr = abbr;
    }
}
