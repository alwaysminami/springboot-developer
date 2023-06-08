package springbootdeveloper.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import springbootdeveloper.domain.Article;

public interface BlogRepository extends JpaRepository<Article, Long> {
// JpaRepository 클래스를 상속받을 때 엔티티 Article과 엔티티의 퍼스널키(PK) 타입 Long을 인수로 주었음
}