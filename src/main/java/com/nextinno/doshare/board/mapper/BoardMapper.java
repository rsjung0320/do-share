package com.nextinno.doshare.board.mapper;

import java.util.List;

import com.nextinno.doshare.config.db.support.DoShareDb;
import com.nextinno.doshare.domain.boards.Board;
import com.nextinno.doshare.domain.comments.Comment;

/**
 * @author rsjung
 *
 */
@DoShareDb
public interface BoardMapper {
    public void addBoard(Board board);
    public List<Board> findAllBoard();
    public Board findById(int idx);
    public void updateReadCount(Board board);
    public void addComment(Comment comment);
    public List<Comment> commentFindById(int board_idx);
}
