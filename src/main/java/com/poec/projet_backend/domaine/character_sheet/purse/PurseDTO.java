package com.poec.projet_backend.domaine.character_sheet.purse;

public record PurseDTO(
        Long id,
        int goldPieces,
        int silverPieces,
        int copperPieces,
        boolean rolled
) {
    public static PurseDTO mapFromEntity(Purse purse ){
        return new PurseDTO(
                purse.getId(),
                purse.getGoldPieces(),
                purse.getSilverPieces(),
                purse.getCopperPieces(),
                purse.isRolled()
        );
    }
}
