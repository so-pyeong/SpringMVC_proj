package kopo.aisw.basic_mvc.board.controller;

import kopo.aisw.basic_mvc.board.service.BoardService;
import kopo.aisw.basic_mvc.board.vo.BoardVO;
import kopo.aisw.basic_mvc.comment.service.CommentService;
import kopo.aisw.basic_mvc.common.CommandMap;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.List;
import java.util.Map;

@Controller("boardController")
public class BoardControllerImpl implements BoardController {
    @Autowired
    private BoardService boardService;

    // 수정(ver3)
    @Autowired
    CommentService commentService;

    @Override
    @RequestMapping(value = "/board/listArticles.do", method = RequestMethod.GET)
    public ModelAndView listArticles(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        List articlesList = boardService.listArticles();
        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("articlesList", articlesList);
        return modelAndView;
    }

    @RequestMapping(value = "/board/boardForm.do", method = RequestMethod.GET)
    private ModelAndView boardForm(HttpServletRequest request, HttpServletResponse response) throws Exception {
        String viewName = getViewName(request);
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName(viewName);
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/board/addArticle.do", method = RequestMethod.POST)
    public ModelAndView addArticle(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        boardService.addArticle(commandMap.getMap(), request);
        ModelAndView modelAndView = new ModelAndView("redirect:/board/listArticles.do");
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/board/removeArticle.do", method = RequestMethod.GET)
    public ModelAndView removeArticle(@RequestParam("articleNO") String articleNO, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        boardService.removeArticle(articleNO);
        ModelAndView modelAndView = new ModelAndView("redirect:/board/listArticles.do");
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/board/viewArticle.do", method = RequestMethod.GET)
    public ModelAndView detailArticle(@RequestParam("articleNO") String articleNO, HttpServletRequest request) throws Exception {
        request.setCharacterEncoding("UTF-8");
        String viewName = getViewName(request);
        BoardVO articleView = boardService.getArticle(articleNO);
        List commentList = commentService.commentList(articleNO);
        List<Map<String, Object>> fileList = boardService.selectFileList(articleNO);

        ModelAndView modelAndView = new ModelAndView(viewName);
        modelAndView.addObject("articleView", articleView);
        modelAndView.addObject("commentList", commentList);
        modelAndView.addObject("fileList", fileList);
        modelAndView.addObject("parentNO", articleNO);
        return modelAndView;
    }

    @Override
    @RequestMapping(value = "/board/modifyArticle.do", method = RequestMethod.POST)
    public ModelAndView modifyArticle(CommandMap commandMap, HttpServletRequest request, HttpServletResponse response) throws Exception {
        request.setCharacterEncoding("UTF-8");
        boardService.updateArticle(commandMap.getMap(), request);
        ModelAndView modelAndView = new ModelAndView("redirect:/board/listArticles.do");
        return modelAndView;
    }

    private String getViewName(HttpServletRequest request) throws Exception {
        String contextPath = request.getContextPath();
        String uri = (String) request.getAttribute("javax.servlet.include.request_uri");

        if (uri == null || uri.trim().equals("")) {
            uri = request.getRequestURI();
        }

        int begin = 0;
        if (!((contextPath == null) || ("".equals(contextPath)))) {
            begin = contextPath.length();
        }

        int end;
        if (uri.indexOf(";") != -1) {
            end = uri.indexOf(";");
        } else if (uri.indexOf("?") != -1) {
            end = uri.indexOf("?");
        } else {
            end = uri.length();
        }

        String viewName = uri.substring(begin, end);
        if (viewName.indexOf(".") != -1) {
            viewName = viewName.substring(0, viewName.lastIndexOf("."));
        }
        if (viewName.lastIndexOf("/") != -1) {
            viewName = viewName.substring(viewName.lastIndexOf("/", 1), viewName.length());
        }

        return viewName;
    }
}
