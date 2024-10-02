package com.likelion.lionlib.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@Builder
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Profile extends BaseTime {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "profile_id")
    private Long id;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id", nullable = false) // 이게 foreign key
    private Member member;

    private String bio;
    private int generation;
    private String major;
    private String imageUrl;
    private String githubLink;

    public void updateProfile(String bio, int generation, String major, String imageUrl, String githubLink) {
        this.bio = bio;
        this.generation = generation;
        this.major = major;
        this.imageUrl = imageUrl;
        this.githubLink = githubLink;
    }
}
