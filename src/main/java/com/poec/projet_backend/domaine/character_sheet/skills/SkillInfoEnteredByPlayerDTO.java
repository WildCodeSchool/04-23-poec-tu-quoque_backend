package com.poec.projet_backend.domaine.character_sheet.skills;

public record SkillInfoEnteredByPlayerDTO(
        Long id,
        int skillId,
        int rankSkill,
        String complement
) {
    public static SkillInfoEnteredByPlayerDTO mapFromEntity(SkillInfoEnteredByPlayer skill) {
        return new SkillInfoEnteredByPlayerDTO(
                skill.getId(),
                skill.getSkillId(),
                skill.getRankSkill(),
                skill.getComplement()
        );
    }

    public static SkillInfoEnteredByPlayer mapFromDtoToEntity(SkillInfoEnteredByPlayerDTO skillDTO) {
        return SkillInfoEnteredByPlayer.builder()
                .rankSkill(skillDTO.rankSkill())
                .complement(skillDTO.complement())
                .skillId(skillDTO.skillId())
                .id(skillDTO.id())
                .build();
    }
}
