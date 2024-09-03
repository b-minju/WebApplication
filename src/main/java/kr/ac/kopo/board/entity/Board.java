package kr.ac.kopo.board.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@ToString(exclude = "writer")
public class Board extends BaseEntity{
    @Id // 기본 키 설정
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 1씩 자동 증가 auto-increment
    private Long bno;
    private String title;
    private String content;

    @ManyToOne(fetch = FetchType.LAZY)
    private Member writer; // foreign key 설정 - 참조 무결성
}
