package com.example.querydslpractice.service;

import com.example.querydslpractice.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BoardServiceTest {
    @Autowired
    BoardService boardService;

    @Test
    public void createBoard() {
        boardService.saveBoard(Board.builder().title("test").build());
    }
}