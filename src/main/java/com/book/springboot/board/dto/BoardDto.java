package com.book.springboot.board.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

//롬북어노테이션
@Data
@Getter
@Setter
public class BoardDto {

    private int boardIdx;

    private String title;

    private String contents;

    private int hitCnt;

    private String creatorId;

    private String createdDatetime;

    private String updaterId;

    private String updatedDatetime;

}
