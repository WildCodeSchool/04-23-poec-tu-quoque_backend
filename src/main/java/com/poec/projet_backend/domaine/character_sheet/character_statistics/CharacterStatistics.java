package com.poec.projet_backend.domaine.character_sheet.character_statistics;

import com.poec.projet_backend.domaine.character_sheet.character_statistics.statistic.Statistic;
import jakarta.persistence.*;
import lombok.*;

@Data
@Entity
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CharacterStatistics {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "strength")
    private Statistic FOR;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "dexterity")
    private Statistic DEX;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "stamina")
    private Statistic CON;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "intelligence")
    private Statistic INT;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "will")
    private Statistic SAG;
    @OneToOne (cascade = CascadeType.ALL)
    @JoinColumn(name = "charisma")
    private Statistic CHA;

    static public CharacterStatistics createBlank() {
        return CharacterStatistics.builder()
                .FOR(new Statistic("FOR"))
                .DEX(new Statistic("DEX"))
                .CON(new Statistic("CON"))
                .INT(new Statistic("INT"))
                .SAG(new Statistic("SAG"))
                .CHA(new Statistic("CHA"))
                .build();
    }

    public CharacterStatistics(
            Statistic FOR,
            Statistic DEX,
            Statistic CON,
            Statistic INT,
            Statistic SAG,
            Statistic CHA
    ) {
        this.FOR = FOR;
        this.DEX = DEX;
        this.CON = CON;
        this.INT = INT;
        this.SAG = SAG;
        this.CHA = CHA;
    }
}
