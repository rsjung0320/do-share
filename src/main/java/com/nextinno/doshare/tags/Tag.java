package com.nextinno.doshare.tags;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextinno.doshare.domain.boards.Board;

import lombok.Data;

/**
 * @author rsjung
 *
 */
@Entity
@Data
public class Tag {
    @Id
    @GeneratedValue
    private long idx;
    
    @Column(name = "name", length = 50, nullable = false)
    private String name;
    
    @Column(name = "tagged_count")
    private int taggedCount = 0;
    
    @ManyToMany(fetch = FetchType.LAZY, cascade={CascadeType.ALL})
    @JoinTable(name = "board_tag", joinColumns = @JoinColumn(name = "tag_idx"), inverseJoinColumns = @JoinColumn(name = "board_idx"))
    @JsonIgnore
    private List<Board> boards = new ArrayList<Board>();
}
