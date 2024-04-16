package com.jyujyu.review.service;

import com.jyujyu.review.model.ReviewEntity;
import com.jyujyu.review.model.dto.ReviewDto;
import com.jyujyu.review.repository.RestaurantRepository;
import com.jyujyu.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;

@Transactional
@RequiredArgsConstructor
@Service
public class ReviewService {

    private final ReviewRepository reviewRepository;
    private final RestaurantRepository restaurantRepository;


    public void createReview(Long restaurantId, String content, Double score) {
        restaurantRepository.findById(restaurantId).orElseThrow();

        ReviewEntity review = ReviewEntity.builder()
                .restaurantId(restaurantId)
                .content(content)
                .score(score)
                .createAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .build();

        reviewRepository.save(review);
    }

    public void deleteReview(Long reviewId) {
        ReviewEntity review = reviewRepository.findById(reviewId).orElseThrow();

        reviewRepository.delete(review);
    }

    public ReviewDto getRestaurantReview(Long restaurantId, Pageable page) {
        Double avgScore = reviewRepository.getAvgByRestaurantId(restaurantId);
        Slice<ReviewEntity> reviews = reviewRepository.findSliceByRestaurantId(restaurantId, page);

        return ReviewDto.builder()
                .avgScore(avgScore)
                .reviews(reviews.getContent())
                .page(
                        ReviewDto.ReviewDtoPage.builder()
                                .offset(page.getPageNumber() * page.getPageSize())
                                .build()
                )
                .build();
    }
}
