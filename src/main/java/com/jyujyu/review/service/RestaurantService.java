package com.jyujyu.review.service;

import com.jyujyu.review.api.request.CreateAndEditRestaurantRequest;
import com.jyujyu.review.api.response.RestaurantDetailView;
import com.jyujyu.review.api.response.RestaurantView;
import com.jyujyu.review.model.MenuEntity;
import com.jyujyu.review.model.RestaurantEntity;
import com.jyujyu.review.repository.MenuRepository;
import com.jyujyu.review.repository.RestaurantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.ZonedDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Transactional
@RequiredArgsConstructor
@Service
public class RestaurantService {

    private final RestaurantRepository restaurantRepository;
    private final MenuRepository menuRepository;

    public void createRestaurant(CreateAndEditRestaurantRequest request) {
        RestaurantEntity restaurant = RestaurantEntity.builder()
                .name(request.getName())
                .address(request.getAddress())
                .createAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .build();

        restaurantRepository.save(restaurant);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createAt(ZonedDateTime.now())
                    .updateAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });

    }

    public void editRestaurant(Long restaurantId, CreateAndEditRestaurantRequest request) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("없는 레스토랑 입니다."));

        restaurant.changeNameAndAddress(request.getName(), request.getAddress());
        restaurantRepository.save(restaurant);

        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);
        menuRepository.deleteAll(menus);

        request.getMenus().forEach((menu) -> {
            MenuEntity menuEntity = MenuEntity.builder()
                    .restaurantId(restaurant.getId())
                    .name(menu.getName())
                    .price(menu.getPrice())
                    .createAt(ZonedDateTime.now())
                    .updateAt(ZonedDateTime.now())
                    .build();

            menuRepository.save(menuEntity);
        });
    }

    public void deleteRestaurant(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow(() -> new RuntimeException("없는 레스토랑 입니다."));

        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);

        menuRepository.deleteAll(menus);
        restaurantRepository.delete(restaurant);
    }

    public List<RestaurantView> getAllRestaurants() {
        List<RestaurantEntity> restaurants = restaurantRepository.findAll();

        return restaurants.stream().map((restaurant) -> RestaurantView.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .createAt(restaurant.getCreateAt())
                .updateAt(restaurant.getUpdateAt())
                .build()
        ).toList();
    }

    public RestaurantDetailView getRestaurantDetail(Long restaurantId) {
        RestaurantEntity restaurant = restaurantRepository.findById(restaurantId).orElseThrow();
        List<MenuEntity> menus = menuRepository.findAllByRestaurantId(restaurantId);

        return RestaurantDetailView.builder()
                .id(restaurant.getId())
                .name(restaurant.getName())
                .address(restaurant.getAddress())
                .createAt(ZonedDateTime.now())
                .updateAt(ZonedDateTime.now())
                .menus(menus.stream().map((menu) -> RestaurantDetailView.Menu.builder()
                        .id(menu.getId())
                        .name(menu.getName())
                        .price(menu.getPrice())
                        .createAt(menu.getCreateAt())
                        .updateAt(menu.getUpdateAt())
                        .build()
                        ).toList()
                )
                .build();
    }
}
