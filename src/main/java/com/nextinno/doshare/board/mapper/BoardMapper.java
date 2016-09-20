package com.nextinno.doshare.board.mapper;

import com.nextinno.doshare.config.db.support.DoShareDb;
import com.nextinno.doshare.domain.boards.Board;

/**
 * @author rsjung
 *
 */
@DoShareDb
public interface BoardMapper {
    public void addBoard(Board board);
}
