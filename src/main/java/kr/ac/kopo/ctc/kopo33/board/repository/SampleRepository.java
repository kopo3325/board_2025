package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.Sample;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SampleRepository extends JpaRepository<Sample, Long> {
}
