package com.nextinno.doshare.board;

/**
 * Created by rsjung on 2016-11-24.
 */
public class BoardNotFoundException extends RuntimeException {
    private Long id;

    public BoardNotFoundException(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
