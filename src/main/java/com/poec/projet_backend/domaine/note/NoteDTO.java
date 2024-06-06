package com.poec.projet_backend.domaine.note;

public record NoteDTO(
        Long id,
        String name,
        String text,
        Long tableId,
        Long playerCharacterId,
        Long userId
) {
    public static NoteDTO mapFromEntity(Note note) {
        return  new NoteDTO(
        note.getId(),
        note.getName(),
        note.getText(),
        note.getGame_table() != null ? note.getGame_table().getId() : null,
        note.getPlayer_character() != null ? note.getPlayer_character().getId() : null,
        note.getUser() != null ? note.getUser().getId() : null
        );
    }
}
