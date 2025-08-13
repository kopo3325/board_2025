package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SampleDaoTest {
    @Autowired
    SampleDao1 sampleDao1;

    @Test
    void findAll() {
        List<Sample> sampleList = sampleDao1.findAll();
        for (Sample sample : sampleList) {
            System.out.println(sample.getTitle());
        }
    }

    @Test
    void findById() {
        Long Id = 1L;
        Sample sample = sampleDao1.findById(Id);
        System.out.println(Id + " : " + sample.getTitle());
    }
}
