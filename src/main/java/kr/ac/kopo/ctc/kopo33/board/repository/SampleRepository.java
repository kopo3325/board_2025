package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.Sample;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

import java.util.List;
import java.util.Optional;

public interface SampleRepository extends JpaRepository<Sample, Long>, JpaSpecificationExecutor<Sample> {
    Optional<Sample> findOnByTitle(String title);
    Page<Sample> findAllByTitle(String type, Pageable pageable);
    List<Sample> findAllByTitle(String type);
    List<Sample> findByTitleLike(String type);
}
