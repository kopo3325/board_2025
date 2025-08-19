package kr.ac.kopo.ctc.kopo33.board.web;

import kr.ac.kopo.ctc.kopo33.board.domain.Article;
import kr.ac.kopo.ctc.kopo33.board.domain.Comment;
import kr.ac.kopo.ctc.kopo33.board.domain.User;
import kr.ac.kopo.ctc.kopo33.board.dto.CommentDto;
import kr.ac.kopo.ctc.kopo33.board.service.ArticleService;
import kr.ac.kopo.ctc.kopo33.board.service.CommentService;
import kr.ac.kopo.ctc.kopo33.board.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/articles")
public class ArticleController {

    @Autowired
    private ArticleService articleService;

    @Autowired
    private CommentService commentService;

    @Autowired
    private UserRepository userRepository;

    private boolean isAuthorized(Article article, Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + authentication.getName()));

        // Check if the current user is the author of the article
        if (article.getUser() != null && article.getUser().getUser_id().equals(currentUser.getUser_id())) {
            return true;
        }

        // Check if the current user has ADMIN role
        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

    private boolean isCommentAuthorized(Comment comment, Authentication authentication) {
        if (authentication == null) {
            return false;
        }
        User currentUser = userRepository.findByUsername(authentication.getName())
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + authentication.getName()));

        // Check if the current user is the author of the comment
        if (comment.getUser() != null && comment.getUser().getUser_id().equals(currentUser.getUser_id())) {
            return true;
        }

        // Check if the current user has ADMIN role
        return authentication.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_ADMIN"));
    }

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
        String username = authentication.getName();
        User currentUser = userRepository.findByUsername(username)
                .orElseThrow(() -> new IllegalArgumentException("User not found: " + username));
        article.setUser(currentUser); // Set the author
        articleService.create(article);
        return "redirect:/articles";
    }

    @GetMapping("/{id}")
    public String detail(@PathVariable Long id, Model model, Authentication authentication) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));
        List<Comment> comments = commentService.getCommentsByArticleId(id);
        List<CommentDto> commentDtos = comments.stream().map(comment -> {
            CommentDto dto = new CommentDto(comment);
            dto.setCanEditDelete(isCommentAuthorized(comment, authentication));
            return dto;
        }).collect(Collectors.toList());
        model.addAttribute("article", article);
        model.addAttribute("comments", commentDtos);
        
        boolean canEditDeleteArticle = isAuthorized(article, authentication);
        model.addAttribute("canEditDeleteArticle", canEditDeleteArticle);

        return "articles/detail";
    }

    @GetMapping("/{id}/edit")
    public String showEditForm(@PathVariable Long id, Model model, Authentication authentication) {
        Article article = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));

        if (!isAuthorized(article, authentication)) {
            return "redirect:/articles/" + id + "?error=unauthorized"; // Redirect if not authorized
        }

        model.addAttribute("article", article);
        return "articles/edit";
    }

    @PostMapping("/{id}/edit")
    public String updateArticle(@PathVariable Long id, @ModelAttribute Article articleDetails, Authentication authentication) {
        Article existingArticle = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));

        if (!isAuthorized(existingArticle, authentication)) {
            return "redirect:/articles/" + id + "?error=unauthorized"; // Redirect if not authorized
        }

        articleService.update(id, articleDetails);
        return "redirect:/articles/" + id;
    }

    @DeleteMapping("/{id}")
    public String deleteArticle(@PathVariable Long id, Authentication authentication) {
        Article articleToDelete = articleService.findById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid article Id:" + id));

        if (!isAuthorized(articleToDelete, authentication)) {
            return "redirect:/articles/" + id + "?error=unauthorized"; // Redirect if not authorized
        }

        articleService.deleteById(id);
        return "redirect:/articles";
    }
}