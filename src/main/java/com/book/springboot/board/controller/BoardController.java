package com.book.springboot.board.controller;


import com.book.springboot.board.service.BoardService;
import com.book.springboot.board.dto.BoardDto;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;


//스프링에서 컨트롤러를 의미 => 컨트롤러로 동작하게 해줌
@Controller
//@Slf4j
public class BoardController {

    private Logger log = LoggerFactory.getLogger(this.getClass());
    @Autowired
    //비지니스 로직을 처리하는 서비스 빈을 연결
    private BoardService boardService;

    //  /board/openBoardLsit.do라는 주소입력하면
    @RequestMapping("/board/openBoardList.do")
    public ModelAndView openBoardList() throws Exception{
        log.debug("openBoardList");
        // templates 폴더 아래에 있는 board/boardList.html을 의미(.html 생략 가능)
        ModelAndView mv = new ModelAndView("board/boardList");

        // 게시글 목록을 조회 => 게시글 목록을 저장하기 위해 List 인터페이스 사용
        List<BoardDto> list = boardService.selectBoardList();
//        실행된 비지니스 로직의 결과 값을 뷰에 list라는 이름으로 저장 => 뷰에서 사용할 경우 list라는 이름으로 조회 가능
        mv.addObject("list",list);

        return mv;
    }

//    게시글 작성 화면을 호출하는 주소
    @RequestMapping("/board/openBoardWrite.do")
    public String openBoardWrite() throws Exception{

        return "/board/boardWrite";
    }

    //작성된 게시글을 등록하는 주소  <form>의 action 속성에 지정된 insertBoard.do를 확인할 수 있음
    @RequestMapping("/board/insertBoard.do")
    public String insertBoard(BoardDto board) throws Exception{
//        게시글 저장하는 service영여의 메서드를 호출
        boardService.insertBoard(board);
//        게시글 목록 화면의 주소를 호출해서 게시글이 등록된 후 게시글 목록화면 으로 이동하도록 함
        return "redirect:/board/openBoardList.do";
    }
    @RequestMapping("/board/openBoardDetail.do")
    public ModelAndView openBoardDetail(@RequestParam int boardIdx) throws Exception{
        ModelAndView mv = new ModelAndView("/board/boardDetail");

        BoardDto board = boardService.selectBoardDetail(boardIdx);
        mv.addObject("board",board);

        return mv;
    }

    @RequestMapping("/board/updateBoard.do")
    public String updateBoard(BoardDto board) throws Exception{
        boardService.updateBoard(board);
        return "redirect:/board/openBoardList.do";
    }

    @RequestMapping("/board/deleteBoard.do")
    public String deleteBoard(int boardIdx) throws Exception{
        boardService.deleteBoard(boardIdx);
        return "redirect:/board/openBoardList.do";
    }

}
