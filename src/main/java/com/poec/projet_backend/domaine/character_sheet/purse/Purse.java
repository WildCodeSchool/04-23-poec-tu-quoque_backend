package com.poec.projet_backend.domaine.character_sheet.purse;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class Purse {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private int goldPieces;
    private int silverPieces;
    private int copperPieces;
    private boolean rolled = false;

    public Purse(
            int goldPieces,
            int silverPieces,
            int copperPieces,
            boolean rolled) {
        this.goldPieces = goldPieces;
        this.silverPieces = silverPieces;
        this.copperPieces = copperPieces;
        this.rolled = rolled;
    }
}
