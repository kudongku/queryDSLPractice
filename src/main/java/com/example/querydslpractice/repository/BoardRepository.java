package com.example.querydslpractice.repository;

import com.example.querydslpractice.entity.Board;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class BoardRepository {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public void save(Board board) {
        entityManager.persist(board);
    }
}
