package fr.fms.web;

import fr.fms.dao.ArticleRepository;
import fr.fms.entities.Article;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@Controller
public class ArticleController {
    @Autowired
    ArticleRepository articleRepository;

    //@RequestMapping(value="/index" , methode=RequestMethod.GET)
    @GetMapping("/index")   //dans une servlet on utilisait request.getParameter("page")
    public String index(Model model, @RequestParam(name = "page", defaultValue = "0")int page,
                                        @RequestParam(name = "keyword",defaultValue = "")String kw) {//le model est fourni par spring, je peux l'utiliser comme ci
        Page<Article> articles = articleRepository.findByDescriptionContains(kw,PageRequest.of(page,5));
        //en retour, au lieu d'une liste d'articles, on a tous les articles formatés en page pointant sur la page demandée
        model.addAttribute("listArticle",articles.getContent()); //pour récupérer sous forme de liste la page pointée

        model.addAttribute("pages",new int[articles.getTotalPages()]);

        model.addAttribute("currentPage",page);

        model.addAttribute("keyword", kw);
        return "articles"; //cette méthode retourne au dispacterServlet une vue
    }

    @GetMapping("/delete")
    public String delete(Long id, int page, String keyword) {
        articleRepository.deleteById(id);

        return "redirect:/index?page="+page+"&keyword="+keyword;
    }

    @GetMapping("/article")
    public String article(Model model) {
        model.addAttribute("article", new Article());
        return "article";
    }

    @PostMapping("/save")
    public String save(@Valid Article article, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) return  "article";
        articleRepository.save(article);
        return "redirect:/index";
    }

}
