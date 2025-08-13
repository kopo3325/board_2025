package kr.ac.kopo.ctc.kopo33.board.web;

import kr.ac.kopo.ctc.kopo33.board.domain.Article;
import kr.ac.kopo.ctc.kopo33.board.service.ArticleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @GetMapping
    public String list(Model model,
                       @RequestParam(defaultValue = "0") int page,
                       @RequestParam(required = false) String searchType,
                       @RequestParam(required = false) String keyword) {
        Pageable pageable = PageRequest.of(page, 10, Sort.by(Sort.Direction.DESC, "id"));
        Page<Article> articlePage = articleService.findAll(pageable, searchType, keyword);
        model.addAttribute("articlePage", articlePage);
        model.addAttribute("searchType", searchType);
        model.addAttribute("keyword", keyword);
        return "articles/list";
    }

    @GetMapping("/write")
    public String writeForm() {
        return "articles/write";
    }

    @PostMapping("/write")
    public String writeSubmit(@ModelAttribute Article article, Authentication authentication) {
        articleService.create(article);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        model.addAttribute("article", article);
        return "articles/detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        model.addAttribute("article", article);
        return "articles/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateArticle(@PathVariable Long id, @ModelAttribute Article articleDetails) {
        articleService.update(id, articleDetails);
        return "redirect:/articles/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id) {
        articleService.deleteById(id);
        return "redirect:/articles";
    }
}
