/*
 * Copyright (C) 2016, Nable Communications, Inc.
 * All rights reserved.
 * 
 * This software is confidential and proprietary information
 * of Nable Communications, Inc.
 */
package com.nextinno.doshare.domain.comments;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextinno.doshare.domain.boards.Board;

/**
 * @author rsjung
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByIdx(long idx);
    List<Comment> findByBoardIdx(long board_idx);
    List<Comment> findByBoard(Board board);
}
