package com.nextinno.doshare.board;

import com.nextinno.doshare.board.Board;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author rsjung
 *
 */
public interface BoardRepository extends JpaRepository<Board, Long>{
}
