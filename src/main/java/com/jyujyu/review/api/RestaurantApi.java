package com.jyujyu.review.api;

import com.jyujyu.review.api.request.CreateAndEditRestaurantRequest;
import com.jyujyu.review.model.RestaurantEntity;
import com.jyujyu.review.service.RestaurantService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class RestaurantApi {

    private final RestaurantService restaurantService;

    @GetMapping("/restaurants")
    public String getRestaurants(@RequestBody CreateAndEditRestaurantRequest request) {
        return "this is getRestaurants";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(@PathVariable Long restaurantId) {
        return "this is getRestaurant " + restaurantId;
    }

    @PostMapping("/restaurant")
    public void createRestaurants(@RequestBody CreateAndEditRestaurantRequest request) {
        restaurantService.createRestaurant(request);
    }

    @PutMapping("/restaurant/{restaurantId}")
    public void editRestaurant(@PathVariable Long restaurantId,
                                 @RequestBody CreateAndEditRestaurantRequest request) {
        restaurantService.editRestaurant(restaurantId, request);
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public void deleteRestaurants(@PathVariable Long restaurantId) {
        restaurantService.deleteRestaurant(restaurantId);
    }
}
