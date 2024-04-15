package com.jyujyu.review.repository;

import com.jyujyu.review.model.TestEntity;

import java.util.List;

public interface TestRepositoryCustom {

    public List<TestEntity> findAllByNameByQuerydsl(String name);

}
