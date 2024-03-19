package com.example.querydslpractice.service;

import com.example.querydslpractice.entity.Bar;
import com.example.querydslpractice.entity.Board;
import com.example.querydslpractice.repository.BarRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BarService {
    private final BarRepository barRepository;

    public void saveBar(Bar bar){
        barRepository.save(bar);
    }

    public List<Bar> findAllByBoard(Board board){
        return barRepository.findAllByBoard(board);
    }

    public List<Bar> findAllByBoardAndTitle(Long boardId, String title){
        return barRepository.findAllByBoardAndTitle(boardId, title);
    }
}
