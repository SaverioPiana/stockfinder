package siw.stockfinder.controller;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import siw.stockfinder.model.Review;
import siw.stockfinder.model.Stock;
import siw.stockfinder.service.ReviewService;
import siw.stockfinder.service.StockService;
import siw.stockfinder.service.UserService;

import java.time.LocalDateTime;

@Controller
public class ReviewController {
    @Autowired
    UserService userService;
    @Autowired
    StockService stockService;
    @Autowired
    ReviewService reviewService;
    @GetMapping("/registered/formNewReview/{stock_id}")
    public String formNewReview(@PathVariable("stock_id") Long stock_id, Model model){
        model.addAttribute("review", new Review());
        model.addAttribute("stock_id", stock_id);
        return "registered/formNewReview";
    }
    @PostMapping("/registered/review/{stock_id}")
    public String newReview(@PathVariable("stock_id") Long stock_id,@Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model){
        if(!userService.canReview(userService.getCurrentUser(), stockService.findById(stock_id))){
            model.addAttribute("error", "You can't review this stock");
            return "/errors/genericError";
        }
        if(!bindingResult.hasErrors()){
            Stock stock = stockService.findById(stock_id);
            if(stock == null) {
                model.addAttribute("error", "Stock not found");
                return "errors/genericError";
            }
            review.setReviewedStock(stock);
            review.setAuthor(userService.getCurrentUser());
            review.setCreationDateTime(LocalDateTime.now());
            this.reviewService.save(review);
            model.addAttribute("review", review);
            return "redirect:/registered/stock/" + stock_id;
        }else{
            return "registered/formNewReview";
        }
    }
    @GetMapping("/registered/removeOwnReview/{review_id}")
    public String removeOwnReview(@PathVariable("review_id") Long id, Model model){
        Review review = reviewService.findById(id);
        if(review == null || !userService.getCurrentUser().equals(review.getAuthor())){
            model.addAttribute("error", "You can't remove this review");
            return "/errors/genericError";
        }
        Stock stock = review.getReviewedStock();
        reviewService.remove(review);
        return "redirect:/registered/stock/"+stock.getId();
    }
    //########### ADMIN #################
    @GetMapping("/admin/formNewReview/{stock_id}")
    public String formNewReviewA(@PathVariable("stock_id") Long stock_id, Model model){
        model.addAttribute("review", new Review());
        model.addAttribute("stock_id", stock_id);
        return "admin/formNewReview";
    }
    @PostMapping("/admin/review/{stock_id}")
    public String newReviewA(@PathVariable("stock_id") Long stock_id,@Valid @ModelAttribute("review") Review review, BindingResult bindingResult, Model model){
        if(!userService.canReview(userService.getCurrentUser(), stockService.findById(stock_id))){
            model.addAttribute("error", "You can't review this stock");
            return "/errors/genericError";
        }
        if(!bindingResult.hasErrors()){
            Stock stock = stockService.findById(stock_id);
            if(stock == null) {
                model.addAttribute("error", "Stock not found");
                return "errors/genericError";
            }
            review.setReviewedStock(stock);
            review.setAuthor(userService.getCurrentUser());
            review.setCreationDateTime(LocalDateTime.now());
            this.reviewService.save(review);
            model.addAttribute("review", review);
            return "redirect:/admin/stock/" + stock_id;
        }else{
            return "admin/formNewReview";
        }
    }
    @GetMapping("/admin/removeReview/{review_id}")
    public String removeReview(@PathVariable("review_id") Long id, Model model){
        Review review = reviewService.findById(id);
        if(review == null) {
            model.addAttribute("error", "Review not found");
            return "errors/genericError";
        }
        Stock stock = review.getReviewedStock();
        reviewService.remove(review);
        return "redirect:/admin/stock/"+stock.getId();
    }
}
