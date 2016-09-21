package com.nextinno.doshare.board.mapper;

import java.util.List;

import com.nextinno.doshare.config.db.support.DoShareDb;
import com.nextinno.doshare.domain.boards.Board;

/**
 * @author rsjung
 *
 */
@DoShareDb
public interface BoardMapper {
    public void addBoard(Board board);
    public List<Board> findAllBoard();
}
