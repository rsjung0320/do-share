/*
 * Copyright (C) 2016, Nable Communications, Inc.
 * All rights reserved.
 * 
 * This software is confidential and proprietary information
 * of Nable Communications, Inc.
 */
package com.nextinno.doshare.board;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.nextinno.doshare.domain.boards.Board;

/**
 * @author rsjung
 *
 */
@Repository
public interface BoardRepository extends JpaRepository<Board, Long>{
}
