package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.PaginationInfo;
import kr.ac.kopo.ctc.kopo33.board.service.designpattern.PaginationInfoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

public class PaginationInfoServiceTest {

    @Autowired
    PaginationInfoService paginationInfoService;

    void check(PaginationInfo paginationInfo, int firstPage, int prevPage, int startPage, int currentPage, int endPage, int nextPage, int lastPage, int totalPageCount) {
        assertEquals(paginationInfo.getFirstPage(), firstPage);
        assertEquals(paginationInfo.getPrevPage(), prevPage);
        assertEquals(paginationInfo.getStartPage(), startPage);
        assertEquals(paginationInfo.getCurrentPage(), currentPage);
        assertEquals(paginationInfo.getEndPage(), endPage);
        assertEquals(paginationInfo.getNextPage(), nextPage);
        assertEquals(paginationInfo.getLastPage(), lastPage);
        assertEquals(paginationInfo.getTotalPageCount(), totalPageCount);
    }

    @Test
    void getPaginationInfo01() {
        PaginationInfo paginationInfo = paginationInfoService.getPaginationInfo(1, 5, 1, 1);
        check(paginationInfo, -1, -1, 1, 1, -1, -1, -1, 1);
    }
}
