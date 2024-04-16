package com.jyujyu.review.api.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;

import java.time.ZonedDateTime;
import java.util.List;

@Getter
@Builder
@AllArgsConstructor
public class RestaurantDetailView {

    private final Long id;
    private final String name;
    private final String address;
    private final ZonedDateTime createAt;
    private final ZonedDateTime updateAt;
    private final List<Menu> menus;

    @Getter
    @Builder
    @AllArgsConstructor
    public static class Menu {
        private final Long id;
        private final String name;
        private final Integer price;
        private final ZonedDateTime createAt;
        private final ZonedDateTime updateAt;
    }
}
