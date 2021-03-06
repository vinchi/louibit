package kr.nexparan.louibit.model;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.CreationTimestamp;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.sql.Timestamp;

//ORM -> JAVA(다른언어) Object -> 테이블로 매핑해주는 기술
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Entity
public class Faq {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) //프로젝트에서 연결된 DB의 넘버링 전략을 따라간다.
    private Long id;

    @Column(nullable = false, length = 100)
    @NotNull
    @Size(min=2, max=40, message="Titles should be between 2 and 40 characters in length.")
    private String title;

    @Lob //대용량 데이터
    private String content;

    @ColumnDefault("0")
    private int count; //조회수

    @ManyToOne(fetch = FetchType.EAGER) //Board = Many, User = One
    @JoinColumn(name = "userId")
    private User user; //누가 썼는지 DB는 오브젝트를 저장 할 수 없다. FK, 자바는 오브젝트를 저장할 수 있다.

    @CreationTimestamp //시간이 자동 입력
    private Timestamp createDate;

}
