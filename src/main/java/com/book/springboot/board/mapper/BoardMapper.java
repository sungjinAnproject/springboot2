package com.book.springboot.board.mapper;

import com.book.springboot.board.dto.BoardDto;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

//마이바티스의 매퍼 인터페이스임을 선언
@Mapper
public interface BoardMapper {

    //인터페이스이기 때문에 메서드의 이름과 반환 형식만 지정
    List<BoardDto> selectBoardList() throws Exception;

    void insertBoard(BoardDto board) throws Exception;


    void updateHitCount(int boardIdx) throws Exception;

    BoardDto selectBoardDetail(int boardIdx) throws Exception;

    void updateBoard(BoardDto board) throws Exception;

    void deleteBoard(int boardIdx) throws Exception;
}
