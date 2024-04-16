package com.jyujyu.review.model;

import jakarta.persistence.*;
import lombok.*;

import java.time.ZonedDateTime;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "restaurant")
@Entity
public class RestaurantEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String address;
    private ZonedDateTime createAt;
    private ZonedDateTime updateAt;

    public void changeNameAndAddress(String name, String address) {
        this.name = name;
        this.address = address;
        this.updateAt = ZonedDateTime.now();
    }
}
