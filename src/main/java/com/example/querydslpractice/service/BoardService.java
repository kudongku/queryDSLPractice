package com.example.querydslpractice.service;

import com.example.querydslpractice.entity.Board;
import com.example.querydslpractice.repository.BoardRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class BoardService {
    private final BoardRepository boardQueryRepository;

    public void saveBoard(Board board) {
        boardQueryRepository.save(board);
    }
}
