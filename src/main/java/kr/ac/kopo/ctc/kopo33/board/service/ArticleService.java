package kr.ac.kopo.ctc.kopo33.board.service;

import kr.ac.kopo.ctc.kopo33.board.domain.Article;
import kr.ac.kopo.ctc.kopo33.board.repository.ArticleRepository;
import kr.ac.kopo.ctc.kopo33.board.repository.ArticleSpecs;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository articleRepository;

    public Page<Article> findAll(Pageable pageable, String searchType, String keyword) {
        Specification<Article> spec = ArticleSpecs.search(searchType, keyword);
        return articleRepository.findAll(spec, pageable);
    }

    public Optional<Article> findById(Long id) {
        return articleRepository.findById(id);
    }

    public Article create(Article article) {
        return articleRepository.save(article);
    }

    @Transactional
    public Article update(Long id, Article articleDetails) {
        Article article = articleRepository.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        article.setTitle(articleDetails.getTitle());
        article.setContent(articleDetails.getContent());
        return articleRepository.save(article);
    }

    public void deleteById(Long id) {
        articleRepository.deleteById(id);
    }
}
