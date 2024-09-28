package com.tenco.blog_jpa_step1.board;

import java.sql.Timestamp;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

/**
 * Board 엔티티는 블로그 게시글을 나타내는 JPA 엔티티 클래스입니다.
 */
@Data // Lombok을 이용해 getter, setter, toString 등을 자동으로 생성합니다.
@Entity // JPA에게 이 클래스가 엔티티임을 알립니다.
@Table(name = "board_tb") // 실제 데이터베이스 테이블 이름을 지정합니다.
public class Board {

    @Id // 기본 키를 나타냅니다.
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 기본 키 생성을 데이터베이스에 위임합니다.
    private Integer id; // 게시글 ID

    private String title; // 게시글 제목

    private String content; // 게시글 내용

    // created_at 컬럼과 매핑하며, 이 필드는 삽입 시 자동으로 설정됨.
    // insertable = false: 삽입 시 개발자가 값을 넣지 않고 DB에서 자동으로 설정.
    // updatable = false: 업데이트 시 수정되지 않도록 설정 (생성 시점에만 값이 설정됨).
    @Column(name = "created_at") 
    private Timestamp createdAt; // 게시글 작성 시간
}