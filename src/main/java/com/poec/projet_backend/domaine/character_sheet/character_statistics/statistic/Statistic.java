package com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class Statistic {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int originalValue;
    private int tempValue;
    private String abbr;

    public Statistic(int originalValue, int tempValue, String abbr) {
        this.originalValue = originalValue;
        this.tempValue = tempValue;
        this.abbr = abbr;
    }

    public Statistic(String abbr) {
        this.abbr = abbr;
    }
}
