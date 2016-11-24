package com.nextinno.doshare.board;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import org.hibernate.annotations.DynamicUpdate;

import com.nextinno.doshare.comment.Comment;
import com.nextinno.doshare.user.User;
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
    private Long idx;
    // 글 제목
    @Column(name = "title", nullable = false)
    private String title = "";
    // 글쓴이
    @Column(name = "email", nullable = false)
    private String email = "";
    // 글 쓴 날짜
    @Column(name = "upload_date", nullable = false)
    @Temporal(TemporalType.TIMESTAMP)
    private Date uploadDate;
    // 최종 수정
    @Column(name = "updated_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date updatedDate;
    // 조회 수
    @Column(name = "read_count")
    private int readCount = 0;
    // 이미지 path
    @Column(name = "image_path")
    private String imagePath = "";
    // 글 태그
    // @Column(name = "content", nullable = false, columnDefinition = "mediumtext")
    @Lob
    @Column(name = "content", nullable = false)
    private String content = "";
    // user의 idx
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(nullable = false)
    private User user;

    // JoinColumn 에서 name을 지정하면 상대 테이블에 해당 네임으로 foreign key가 생긴다.
    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name="board_idx",foreignKey = @ForeignKey(name = "fk_comment_board"), nullable = true)
    // new ArrayList<>(); 이거 삭제 하면 에러가 난다.
    private List<Comment> comments = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(name = "board_tag", joinColumns = @JoinColumn(name = "board_idx"), inverseJoinColumns = @JoinColumn(
            name = "tag_idx"))
    // new ArrayList<>(); 이거 삭제 하면 에러가 난다.
    private List<Tag> tags = new ArrayList<>();

    public Board(){}

    public Board(BoardDto.CreateBoard board) {
        super();
        this.title = board.getTitle();
        this.email = board.getEmail();
        Date now = new Date();
        this.uploadDate = now;
        this.updatedDate = now;
        this.content = board.getContent();
        this.user = new User();
        user.setIdx(board.getUserIdx());
    }

    /**
     * @param updatedBoard
     */
    public void update(BoardDto.UpdateBoard updatedBoard) {
        this.title = updatedBoard.getTitle();
        Date now = new Date();
        this.updatedDate = now;
        this.content = updatedBoard.getContent();
    }

}
