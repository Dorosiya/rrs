package com.jyujyu.review.repository;

import com.jyujyu.review.model.ReviewEntity;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Slice;

public interface ReviewRepositoryCustom {

    Double getAvgByRestaurantId(Long restaurantId);

    Slice<ReviewEntity> findSliceByRestaurantId(Long restaurantId, Pageable page);
}
