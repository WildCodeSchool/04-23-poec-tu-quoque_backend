package com.poec.projet_backend.domaine.character_sheet.skills;

public record SkillInfoEnteredByPlayerDTO(
        int skillId,
        int rank,
        String complement,
        Long id
) {
    public static SkillInfoEnteredByPlayerDTO mapFromEntity(SkillInfoEnteredByPlayer skill) {
        return new SkillInfoEnteredByPlayerDTO(
                skill.getSkillId(),
                skill.getRankSkill(),
                skill.getComplement(),
                skill.getId()
        );
    }

    public static SkillInfoEnteredByPlayer mapFromDtoToEntity(SkillInfoEnteredByPlayerDTO skillDTO) {
        System.err.println(skillDTO.id() + " -> " + skillDTO.rank());
        return SkillInfoEnteredByPlayer.builder()
                .rankSkill(skillDTO.rank())
                .complement(skillDTO.complement())
                .skillId(skillDTO.skillId())
                .id(skillDTO.id())
                .build();
    }
}
