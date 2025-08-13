package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.Sample;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SampleDao2Test {
    @Autowired
    SampleDao2 sampleDao2;

    @Test
    void findAll() {
        List<Sample> list = sampleDao2.findAll();
        assertEquals(13, list.size());
    }

    @Test
    void findById() {
        Sample sample = sampleDao2.findById(1L);
        assertEquals(1L, sample.getId());
    }
}


