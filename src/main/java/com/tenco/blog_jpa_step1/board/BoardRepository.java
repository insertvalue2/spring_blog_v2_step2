package com.tenco.blog_jpa_step1.board;

import org.springframework.stereotype.Repository;

import jakarta.persistence.EntityManager;
import jakarta.persistence.Query;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Repository // 이 클래스를 Spring에서 관리하는 '레포지토리 빈'으로 등록, 데이터베이스와의 상호작용을 담당
public class BoardRepository {
    
    // EntityManager: JPA에서 데이터베이스와 상호작용을 관리하는 핵심 클래스.
    // 이를 통해 SQL 쿼리 실행, 데이터 삽입, 삭제, 업데이트 작업을 처리
    private final EntityManager em;

    @Transactional // 이 메서드가 데이터베이스와의 트랜잭션 내에서 실행되도록 보장하는 어노테이션.
    // 트랜잭션은 작업이 전부 성공적으로 완료되거나, 실패하면 롤백되도록 함
    public void save(String title, String content, String username) {
        
        // createNativeQuery: 네이티브 SQL 쿼리를 실행할 때 사용하는 메서드. 
        // 여기서는 직접 SQL 쿼리를 작성하여 테이블에 데이터를 삽입 
        Query query = em
                .createNativeQuery("insert into board_tb(title, content, username, created_at) values(?,?,?,now())");
        
        // 쿼리 파라미터 설정: 쿼리 내의 '?' 부분에 실제 값을 바인딩.
        // 첫 번째 파라미터는 title, 두 번째는 content, 세 번째는 username 값
        query.setParameter(1, title);
        query.setParameter(2, content);
        query.setParameter(3, username);

        // executeUpdate: insert, update, delete 같은 데이터베이스 수정 쿼리를 실행.
        // 실행된 후 영향을 받은 행(row)의 개수를 반환
        query.executeUpdate();
    }
}

