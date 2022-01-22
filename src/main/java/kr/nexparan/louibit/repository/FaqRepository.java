package kr.nexparan.louibit.repository;

import kr.nexparan.louibit.model.Faq;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FaqRepository extends JpaRepository<Faq, Long> {
    List<Faq> findByTitle(String title);
    List<Faq> findByTitleOrContent(String title, String content);
    Page<Faq> findByTitleContainingOrContentContaining(String title, String content, Pageable pageable);
}
