package kr.ac.kopo.ctc.kopo33.board.repository;

import kr.ac.kopo.ctc.kopo33.board.domain.Comment;
import kr.ac.kopo.ctc.kopo33.board.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CommentRepository  extends JpaRepository<Comment, Long> {
    List<Comment> findByArticleId(Long articleId);
}