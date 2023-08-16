package com.book.springboot.board.service;


import com.book.springboot.board.dto.BoardDto;
import com.book.springboot.board.mapper.BoardMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


//서비스를 나타네는 것
@Service
public class BoardServiceImpl implements BoardService {

    @Autowired
    //DAO빈을 선언
    private BoardMapper boardMapper;

    @Override
    public List<BoardDto> selectBoardList() throws Exception{
        //BoardMapper 클래스의 selectBoardList를 호출
        return boardMapper.selectBoardList();
    }

    @Override
    public void insertBoard(BoardDto board) throws Exception{
        boardMapper.insertBoard(board);
    }

    @Override
    public BoardDto selectBoardDetail(int boardIdx) throws Exception {

//        선택된 게시글의 조회수를 증가
        boardMapper.updateHitCount(boardIdx);

//        선택된 게시글 내용을 조회
        BoardDto board = boardMapper.selectBoardDetail(boardIdx);

        return board;
    }

    @Override
    public void updateBoard(BoardDto board) throws Exception {
        boardMapper.updateBoard(board);
    }

    @Override
    public void deleteBoard(int boardIdx) throws Exception {
        boardMapper.deleteBoard(boardIdx);
    }


}
