package com.nextinno.doshare.board;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by rsjung on 2016-11-21.
 */
@Service
@Transactional
public class BoardServiceImpl implements BoardService{

    @Autowired
    private BoardRepository boardRepository;

    @Override
    public Board getBoard(Long idx) {
        Board board = boardRepository.findOne(idx);

        if (board == null) {
            throw new BoardNotFoundException(idx);
        }

        // readCount를 1증가 시킨다.
        board.setReadCount(board.getReadCount() + 1);

        return boardRepository.save(board);
    }


}
