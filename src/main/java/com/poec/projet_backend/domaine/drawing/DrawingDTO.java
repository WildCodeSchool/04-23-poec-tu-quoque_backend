package com.poec.projet_backend.domaine.drawing;

public record DrawingDTO(
        Long id,
        String name,
        String content
) {
    public static DrawingDTO mapFromEntity(Drawing drawing) {
        return new DrawingDTO(
                drawing.getId(),
                drawing.getName(),
                drawing.getContent()
        );
    }
}
