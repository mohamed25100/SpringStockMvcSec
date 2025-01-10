package fr.fms.web;

import fr.fms.business.IBusinessImpl;
import fr.fms.dao.ArticleRepository;
import fr.fms.dao.CustomerRepository;
import fr.fms.dao.OrderItemRepository;
import fr.fms.dao.OrderRepository;
import fr.fms.entities.Article;
import fr.fms.entities.Customer;
import fr.fms.entities.Order;
import fr.fms.entities.OrderItem;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.Date;

@Controller
//@RequestMapping("/cart")
public class CartController {
	@Autowired
	ArticleRepository articleRepository;
	
	@Autowired
	CustomerRepository customerRepository;

	@Autowired
	OrderRepository orderRepository;
	
	@Autowired
	OrderItemRepository orderItemRepository;
	
	@Autowired
	IBusinessImpl business;

	private final Logger logger = LoggerFactory.getLogger(CartController.class);
	
	/**
	 * Méthode en GET correspondant à l'url .../vcart permettant de visualiser le panier
	 * @param model
	 * @return cart.html
	 */
	@GetMapping("/vcart")	//Convention REST : l'URL indique la ressource, et le verbe HTTP indique l'action.
	public String viewCaddy(Model model) {
		model.addAttribute("cart",business.getCart());		
		double total = business.getTotalAmount();
		model.addAttribute("total",total);
		model.addAttribute("nbArt",business.getNbCart());
		return "cart";
	}
	
	/**
	 * Méthode en GET permettant d'ajouter un article au panier, tous les paramètres ci dessous permettent de garder le contexte pour redirection
	 * @param id
	 * @param page
	 * @param keyword
	 * @param idCat
	 * @param redirectAttrs sert à injecter un paramètre dans la redirection, delors ../index pourra le récupérer via le modelAttribute
	 * @return ../index
	 */
	@GetMapping("/acart")
	public String addArticleCart(Long id, int page, String keyword , Long idCat, RedirectAttributes redirectAttrs) {	
		try {
			business.addArtToCart(business.getArticleById(id));
		}
		catch(Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
			logger.error("[CART CONTROLLER : ADD ARTICLE TO CART] : {} " , e.getMessage());
		}
		return "redirect:/index?page="+page+"&keyword="+keyword + "&idCat=" + idCat + "&cart=" + business.getNbCart(); 
	}
	
	/**
	 * Méthode en GET correspondant à l'url .../dcart consistant à supprimer un article du panier
	 * @param id de l'article à supprimer
	 * @return redirection vers la gestion de l'affichage du panier
	 */
	@GetMapping("/dcart")
	public String deleteArticleCart(Long id) {
		//ToDo : ajouter une demande de confirmation en JS sur la vue
		//ToDo : ajouter ici une gestion d'exception puisqu'accès Bdd
		business.delArtFromCart(id);
		return "redirect:/vcart"; 
	}	
	
	/**
	 * Méthode en GET correspondant à l'url .../order gérant la saisi d'un client dans le cadre de la commande en cours 
	 * @param model
	 * @return order.html
	 */
	@GetMapping("/order")		
	public String order(Model model) {		
		if(business.isEmpty())	return "redirect:/index";
		model.addAttribute("customer", new Customer());
		return "order";
	}
	
	/**
	 * Méthode en POST correspondant à ../porder postant toutes les infos valides d'un client
	 * @param customer
	 * @param bindingResult gestion de la validation des saisies
	 * @param model
	 * @return recap.html soit un récapitulatif de la commande complète
	 */
	@PostMapping("/porder")
	public String postOrder(@Valid Customer customer , BindingResult bindingResult , Model model) {	
		if(bindingResult.hasErrors())	return "order";
		
		model.addAttribute("cart",business.getCart());
		double total = business.getTotalAmount();
		model.addAttribute("total",total);		
		model.addAttribute("customer",customer);		
		business.setCustomer(customer);
		return "recap";
	}
	
	/**
	 * Méthode en GET ../confirm correspondant au bouton de validation du récapitulatif de la commande, ici la commande est validée en base avec les infos associées
	 * @return thanks.html si tout va bien, si exception redirection vers /index avec message d'erreur
	 */
	@GetMapping("/confirm")
	@Transactional
	public String confirm(RedirectAttributes redirectAttrs) {
		if(business.isEmpty())	return "redirect:/index";
		
		try {
		//ToDo : ajout d'un client en base, s'il existe déjà ?
		Customer customer = business.getCustomer();
		customerRepository.save(customer);
		
		Order order = new Order(null,new Date(),business.getTotalAmount(),customer,null);
		orderRepository.save(order);
		
		for(Article article : business.getCart()) {
			OrderItem orderItem = new OrderItem();
			orderItem.setOrder(order);
			orderItem.setArticle(article);
			orderItem.setPrice(article.getPrice());
			orderItem.setQuantity(article.getQuantity());			
			orderItemRepository.save(orderItem);
		}		 
		business.delCart();
		//ToDo renvoi vers une page de paiement suivi envoi mail de confirmation...
		}
		catch(Exception e) {
			redirectAttrs.addAttribute("error",e.getMessage());
			logger.error("[CART CONTROLLER : CONFIRM ORDER] : {} " , e.getMessage());
			//return "500";
			return "redirect:/index";
		}		
		return "thanks";
	}
}
