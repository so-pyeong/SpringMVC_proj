package kopo.aisw.basic_mvc.board.controller;

import kopo.aisw.basic_mvc.board.vo.BoardVO;
import kopo.aisw.basic_mvc.common.CommandMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface BoardController {
    public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView addArticle(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView removeArticle(@RequestParam("articleNO") String articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception;

    public ModelAndView detailArticle(@RequestParam("articleNO") String articleNO, HttpServletRequest request) throws Exception;

    public ModelAndView modifyArticle(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
