package pl.soapworkshop.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import pl.soapworkshop.entity.Product;
import pl.soapworkshop.entity.ShoppingCart;
import pl.soapworkshop.repository.CategoryRepository;
import pl.soapworkshop.repository.ProductRepository;
import pl.soapworkshop.service.ShoppingCartService;

@Controller
@Scope("request")
public class ShoppingCartController {

    private ShoppingCart shoppingCart;
    private ShoppingCartService shoppingCartService;
    private ProductRepository productRepository;
    private CategoryRepository categoryRepository;

    @Autowired
    public ShoppingCartController(ShoppingCart shoppingCart, ProductRepository productRepository, CategoryRepository categoryRepository, ShoppingCartService shoppingCartService) {
        this.shoppingCart = shoppingCart;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
        this.shoppingCartService = shoppingCartService;
    }

    //Navbar resources
    @ModelAttribute
    public void addCategories(Model model) {
        model.addAttribute("categories", this.categoryRepository.findAll());
    }

    @RequestMapping(value = "/store/productDetails/{id}", method = RequestMethod.POST)
    public String addProductToCart(@PathVariable Integer id, @ModelAttribute Product product) {
        shoppingCartService.addToCart(product);
      return "/index";
    }

    @RequestMapping(value = "/store/shoppingCart", method = RequestMethod.GET)
    public String showCart(Model model){
        model.addAttribute("cart", shoppingCartService.getCart());
        model.addAttribute("cartTotal", shoppingCartService.getCartTotal());
        return "store/shoppingCart";
    }

    @RequestMapping(value = "/store/shoppingCart", method = RequestMethod.POST)
    public String editCart(@RequestParam int editedQuantity, Model model, @ModelAttribute Product product){
        shoppingCartService.editCart(product, editedQuantity);
        model.addAttribute("cart", shoppingCartService.getCart());
        model.addAttribute("cartTotal", shoppingCartService.getCartTotal());
        return "store/shoppingCart";
    }

    @RequestMapping(value = "/store/deleteItemFromCart/{id}", method = RequestMethod.GET)
    public String deleteProductFromCart(@PathVariable Integer id, Model model){
        shoppingCartService.deleteProductFromCart(id);
        model.addAttribute("cart", shoppingCartService.getCart());
        model.addAttribute("cartTotal", shoppingCartService.getCartTotal());
        return "store/shoppingCart";
    }

}
