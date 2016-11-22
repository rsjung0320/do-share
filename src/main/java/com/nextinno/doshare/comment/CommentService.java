package com.nextinno.doshare.comment;

import com.nextinno.doshare.board.Board;
import org.springframework.stereotype.Service;

/**
 * Created by rsjung on 2016-11-22.
 */
@Service
public interface CommentService {
    Comment addComment(Board board, Comment comment);
}
