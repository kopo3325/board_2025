package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SampleRepositoryTest {

    @Autowired
    SampleRepository sampleRepository;

    @Test
    void findAllByTitle() {
        Map<String, Object> filter = new HashMap<>();
        filter.put("title", "t1");

        PageRequest pageable = PageRequest.of(0,10);
        Page<Sample> page = sampleRepository.findAll(SampleSpecs.search(filter), pageable);

        for (Sample s : page) {
            System.out.println(s.getTitle());
        }
    }

    @Test
    void findByTitleLike() {
        List<Sample> list = sampleRepository.findByTitleLike("%t1%");

        for (Sample s : list) {
            System.out.println(s.getTitle());
        }

    }
}
