package com.example.querydslpractice.repository;

import com.example.querydslpractice.entity.Bar;
import com.example.querydslpractice.entity.Board;
import com.example.querydslpractice.entity.QBar;
import com.querydsl.core.BooleanBuilder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.thymeleaf.util.StringUtils;

import java.util.List;

import static com.example.querydslpractice.entity.QBar.bar;

@Repository
@RequiredArgsConstructor
public class BarRepository {
    private final JPAQueryFactory queryFactory;
    private final EntityManager entityManager;

    public void save(Bar bar) {
        entityManager.persist(bar);
    }

    public List<Bar> findAllByBoard(Board board) {
        return queryFactory.selectFrom(bar)
                .where(bar.board.eq(board))
                .fetch();
    }

    public List<Bar> findAllByBoardAndTitle(Board board, String title){
        BooleanBuilder booleanBuilder = new BooleanBuilder();
        booleanBuilder.and(bar.title.eq(title));
        booleanBuilder.and(bar.board.eq(board));

        return queryFactory.selectFrom(bar)
                .where(booleanBuilder)
                .fetch();
    }
}
