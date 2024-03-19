package com.example.querydslpractice.repository;

import com.example.querydslpractice.dto.BarResponseDto;
import com.example.querydslpractice.entity.Bar;
import com.example.querydslpractice.entity.Board;
import com.querydsl.core.types.Projections;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Expressions;
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

    public List<BarResponseDto> findAllByBoardAndTitle(Long boardId, String title) {
        return queryFactory.select(Projections.fields(BarResponseDto.class,
                        bar.id,
                        Expressions.asString(title).as("title")
                ))
                .from(bar)
                .where(eqBoard(boardId), eqTitle(title))
                .fetch();
    }

    public Boolean exist(Long boardId) {
        Integer fetchOne = queryFactory
                .selectOne()
                .from(bar)
                .where(bar.board.id.eq(boardId))
                .fetchFirst();
        return fetchOne != null;
    }

    private BooleanExpression eqTitle(String title) {
        if (StringUtils.isEmpty(title)) {
            return null;
        }
        return bar.title.eq(title);
    }

    private BooleanExpression eqBoard(Long boardId) {
        if (boardId == null) {
            return null;
        }
        return bar.board.id.eq(boardId);
    }
}
