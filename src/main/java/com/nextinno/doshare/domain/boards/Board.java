package com.nextinno.doshare.domain.boards;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.ForeignKey;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.Lob;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.nextinno.doshare.domain.comments.Comment;
import com.nextinno.doshare.domain.users.User;
import com.nextinno.doshare.tags.Tag;

/**
 * @author rsjung
 *
 */
@Entity
@DynamicUpdate
@Data
@Getter
@Setter
public class Board {
    // index
    @Id
    @GeneratedValue
    private long idx = 0;
    // 글 제목
    @Column(name = "title", nullable = false)
    private String title = "";
    // 글쓴이
    @Column(name = "email", nullable = false)
    private String email = "";
    // 글 쓴 날짜
    @Column(name = "upload_date", nullable = false)
    private String uploadDate = "";
    // 최종 수정
    @Column(name = "updated_date")
    private String updatedDate = "";
    // 조회 수
    @Column(name = "read_count")
    private int readCount = 0;
    // 이미지 path
    @Column(name = "image_path")
    private String imagePath = "";
    // 글 태그
//    @Column(name = "content", nullable = false, columnDefinition = "mediumtext")
    @Lob
    @Column(name = "content", nullable = false)
    private String content = "";
    // user의 idx
    @ManyToOne(fetch=FetchType.LAZY)
    @JoinColumn(nullable = false)
    @JsonIgnore
    private User user;
    
    @OneToMany(fetch=FetchType.LAZY, cascade = CascadeType.ALL)
    // JoinColumn 에서 name을 지정하면 상대 테이블에 해당 네임으로 foreign key가 생긴다.
    @JoinColumn(foreignKey = @ForeignKey(name="fk_comment_board"), nullable = true)
//    @JsonIgnore
    private List<Comment> comments;
    
    //
    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "board_tag", joinColumns = @JoinColumn(name = "board_idx"), inverseJoinColumns = @JoinColumn(name = "tag_idx"))
//    @JsonIgnore
    private List<Tag> tags = new ArrayList<Tag>();
    
    public Board(){}
    
    public Board(BoardVo boardVo){
        super();
        this.title = boardVo.getTitle();
        this.email = boardVo.getEmail();
        this.uploadDate = boardVo.getUploadDate();
        this.updatedDate = boardVo.getUpdatedDate();
        this.readCount = boardVo.getReadCount();
        this.imagePath = boardVo.getImagePath();
        this.content = boardVo.getContent();
        this.user = new User();
        user.setIdx(boardVo.getUserIdx());
    }

    /**
     * @param updatedBoard
     */
    public void update(BoardVo updatedBoard) {
        this.title = updatedBoard.getTitle();
        this.updatedDate = updatedBoard.getUpdatedDate();
        this.content = updatedBoard.getContent();
    }
    
}

