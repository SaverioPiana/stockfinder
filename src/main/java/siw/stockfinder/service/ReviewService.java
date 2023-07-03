package siw.stockfinder.service;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import siw.stockfinder.model.Review;
import siw.stockfinder.repository.ReviewRepository;

@Service
public class ReviewService {
    @Autowired
    private ReviewRepository reviewRepository;

    @Transactional
    public void save(Review review){
        reviewRepository.save(review);
    }

    @Transactional
    public void remove(Review review){
        reviewRepository.delete(review);
    }

    public Review findById(Long id){
        return reviewRepository.findById(id).orElse(null);
    }
}
