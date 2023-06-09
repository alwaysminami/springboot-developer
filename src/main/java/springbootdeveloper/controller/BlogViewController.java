package springbootdeveloper.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springbootdeveloper.domain.Article;
import springbootdeveloper.dto.ArticleListViewResponse;
import springbootdeveloper.dto.ArticleViewResponse;
import springbootdeveloper.service.BlogService;

import java.util.List;

@RequiredArgsConstructor
@Controller
public class BlogViewController {
    // .articles GET 요청을 처리할 코드
    private final BlogService blogService;

    @GetMapping("/articles")
    public String getArticles(Model model) {
        List<ArticleListViewResponse> articles = blogService.findAll().stream()
                .map(ArticleListViewResponse::new)
                .toList();
        model.addAttribute("articles", articles); // 블로그 글 리스트 저장

        return "articleList"; // articleList.html 라는 뷰 조회
    }

    @GetMapping("/articles/{id}")
    public String getArticle(@PathVariable Long id, Model model) {
        // DTO(ArticleViewResponse)로부터 값을 받아와 반환하는 컨트롤러 메서드
        Article article = blogService.findByID(id);
        model.addAttribute("article", new ArticleViewResponse(article));

        return "article";
    }
}
