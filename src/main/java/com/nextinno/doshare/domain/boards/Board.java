package com.nextinno.doshare.domain.boards;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

import com.nextinno.doshare.domain.users.User;

/**
 * @author rsjung
 *
 */
@SuppressWarnings("serial")
@Entity
public class Board implements Serializable{
    
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
    @Column(name = "content", nullable = false, columnDefinition = "mediumtext")
    private String content = "";
    // user의 idx
    @ManyToOne
    private User user;
    
    
}

