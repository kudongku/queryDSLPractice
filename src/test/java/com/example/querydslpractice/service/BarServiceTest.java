package com.example.querydslpractice.service;

import com.example.querydslpractice.dto.BarResponseDto;
import com.example.querydslpractice.entity.Bar;
import com.example.querydslpractice.entity.Board;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("Bar를 잘 저장하고, findAllByBoard 메소드가 잘 동작하는지")
    public void createBar() {
        Board board = Board.builder().title("testBoard").build();
        boardService.saveBoard(board);

        Bar bar = Bar.builder().title("test01").board(board).build();
        Bar bar2 = Bar.builder().title("test02").board(board).build();
        Bar bar3 = Bar.builder().title("test03").board(board).build();
        Bar bar4 = Bar.builder().title("test04").board(board).build();

        barService.saveBar(bar);
        barService.saveBar(bar2);
        barService.saveBar(bar3);
        barService.saveBar(bar4);

        List<Bar> bars = barService.findAllByBoard(board);
        assert bars.size() == 4;
    }

    @Test
    @DisplayName("BooleanExpression을 사용해서 null값일때 조건절 변화")
    public void findBar() {
        Board board = Board.builder().title("testBoard").build();
        boardService.saveBoard(board);

        Bar bar = Bar.builder().title("true").board(board).build();
        Bar bar2 = Bar.builder().title("true").board(board).build();
        Bar bar3 = Bar.builder().title("false").board(board).build();
        Bar bar4 = Bar.builder().title("false").board(board).build();

        barService.saveBar(bar);
        barService.saveBar(bar2);
        barService.saveBar(bar3);
        barService.saveBar(bar4);

        List<BarResponseDto> bars = barService.findAllByBoardAndTitle(board.getId(), "true");
        assert bars.size() == 2;
    }

    @Test
    @DisplayName("Exist 대체하기")
    public void exist() {
        Board board = Board.builder().title("testBoard").build();
        boardService.saveBoard(board);

        for(int i=0; i<50; i++){
            barService.saveBar(Bar.builder().title("existTest"+i+1).board(board).build());
        }

        assert barService.isExistByBoard(board.getId());
    }
}