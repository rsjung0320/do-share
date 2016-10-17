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

/**
 * @author rsjung
 *
 */
public interface CommentRepository extends JpaRepository<Comment, Long>{
    List<Comment> findByIdx(long idx);
}
