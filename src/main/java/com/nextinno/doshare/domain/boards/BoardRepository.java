package com.nextinno.doshare.domain.boards;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.nextinno.doshare.domain.comments.Comment;

/**
 * @author rsjung
 *
 */
public interface BoardRepository extends JpaRepository<Board, Long>{
}
