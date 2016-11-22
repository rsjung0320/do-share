package com.nextinno.doshare.comment;

import com.nextinno.doshare.board.Board;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by rsjung on 2016-11-22.
 */
@Component
@Transactional
public class CommentServiceImpl implements CommentService {

    @Autowired
    private CommentRepository commentRepository;

    @Override
    public Comment addComment(Board board, Comment comment) {
        comment.setBoard(board);
        Date now = new Date();
        comment.setUploadDate(now);
        return commentRepository.save(comment);
    }
}
