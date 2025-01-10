package fr.fms.business;

import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CategoryRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Category;
import fr.fms.entities.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@Service
public class IBusinessImpl implements IBusiness {
    @Autowired
    ArticleRepository articleRepository;

    @Autowired
    CategoryRepository categoryRepository;

    private HashMap<Long, Article> cart;
    private Customer customer;

    public IBusinessImpl() {
        cart = new HashMap<>();
        customer = null;
    }

    @Override
    public void addArtToCart(Article article) {
        Article a = cart.get(article.getId());
        if(a != null) {
            a.setQuantity(a.getQuantity()+1);
        }
        else cart.put(article.getId(), article);
    }

    @Override
    public void delArtFromCart(Long id) {
        cart.remove(id);
    }

    @Override
    public void delCart() {
        cart.clear();
    }

    @Override
    public List<Article> getCart() {
        return new ArrayList<>(cart.values());
    }


    public double getTotalAmount() {
        double total = 0;
        for(Article article : cart.values()) {
            total += article.getPrice()*article.getQuantity();
        }
        return total;
    }

    public boolean isEmpty() {
        return cart.isEmpty();
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    @Override
    public int getNbCart() {
        return cart.size();
    }

    @Override
    public List<Article> getArticles() throws Exception {
        return articleRepository.findAll();
    }

    @Override
    public Article getArticleById(Long id) throws Exception {
        Optional<Article> optional = articleRepository.findById(id);
        return optional.isPresent() ? optional.get() : null;
    }

    @Override
    public void saveArticle(Article article) throws Exception {
        articleRepository.save(article);
    }

    @Override
    public Page<Article> getArticlesByCategoriePage(Long idCat, int page) throws Exception {
        return articleRepository.findByCategoryId(idCat, PageRequest.of(page, 5));
    }

    @Override
    public Page<Article> getArticlesPages(String kw, int page) throws Exception {
        return articleRepository.findByDescriptionContains(kw , PageRequest.of(page, 5));
    }

    @Override
    public void deleteArticle(Long id) throws Exception {
        articleRepository.deleteById(id);
    }

    public List<Category> getCatogries() throws Exception {
        return categoryRepository.findAll();
    }

    public Category getCategoryById(Long id) throws Exception {
        return categoryRepository.getById(id);
    }

    public void order() {
        try {
            Thread.sleep(500);
        } catch (Exception e) {
            e.printStackTrace();
            Thread.currentThread().interrupt();
        }
    }
}
