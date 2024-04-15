package com.jyujyu.review.api;

import com.jyujyu.review.api.request.CreateAndEditRestaurantRequest;
import org.springframework.web.bind.annotation.*;

@RestController
public class RestaurantApi {

    @GetMapping("/restaurants")
    public String getRestaurants(@RequestBody CreateAndEditRestaurantRequest request) {
        return "this is getRestaurants";
    }

    @GetMapping("/restaurant/{restaurantId}")
    public String getRestaurant(@PathVariable Long restaurantId) {
        return "this is getRestaurant " + restaurantId;
    }

    @PostMapping("/restaurant")
    public String createRestaurants(@RequestBody CreateAndEditRestaurantRequest request) {
        return "this is createRestaurant, name = " + request.getName() + " address = " + request.getAddress()
                + ", menu[0].name = " + request.getMenus().get(0).getName() + ", menu[0].price = " + request.getMenus().get(0).getPrice();
    }

    @PutMapping("/restaurant/{restaurantId}")
    public String editRestaurant(@PathVariable Long restaurantId,
                                 @RequestBody CreateAndEditRestaurantRequest request) {
        return "this is editRestaurant, name = " + request.getName() + " address = " + request.getAddress()
                + ", menu[0].name = " + request.getMenus().get(0).getName() + ", menu[0].price = " + request.getMenus().get(0).getPrice();
    }

    @DeleteMapping("/restaurant/{restaurantId}")
    public String deleteRestaurants(@PathVariable Long restaurantId) {
        return "this is deleteRestaurants";
    }
}
