package com.example.querydslpractice.service;

import com.example.querydslpractice.entity.Bar;
import com.example.querydslpractice.entity.Board;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@SpringBootTest
@Transactional
@Rollback(value = false)
class BarServiceTest {
    @Autowired
    BarService barService;
    @Autowired
    BoardService boardService;

    @Test
    public void createBar() {
        Board board = Board.builder().title("testBoard").build();
        boardService.saveBoard(board);

//        Bar bar = Bar.builder().title("test01").board(board).build();
//        Bar bar2 = Bar.builder().title("test02").board(board).build();
//        Bar bar3 = Bar.builder().title("test03").board(board).build();
//        Bar bar4 = Bar.builder().title("test04").board(board).build();
//
//        barService.saveBar(bar);
//        barService.saveBar(bar2);
//        barService.saveBar(bar3);
//        barService.saveBar(bar4);

        List<Bar> bars = barService.findAllByBoard(board);
        assert bars.size() == 4;
    }
}