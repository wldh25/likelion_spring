package com.likelion.lionlib.dto;

import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.likelion.lionlib.domain.Member;
import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class MemberResponse {
    private String email;
    private String name;
    private String bio;
    private int generation;
    private String major;
    private String imageUrl;
    private String githubLink;

    public static MemberResponse fromEntity(Member member) {
        return MemberResponse.builder()
                .email(member.getEmail())
                .name(member.getName())
                .bio(member.getProfile().getBio())
                .generation(member.getProfile().getGeneration())
                .major(member.getProfile().getMajor())
                .imageUrl(member.getProfile().getImageUrl())
                .githubLink(member.getProfile().getGithubLink())
                .build();
    }
}