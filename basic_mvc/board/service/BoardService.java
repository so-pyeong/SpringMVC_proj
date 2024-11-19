package kopo.aisw.basic_mvc.board.service;

import kopo.aisw.basic_mvc.board.vo.BoardVO;
import org.springframework.dao.DataAccessException;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.Map;

public interface BoardService {
    public List listArticles() throws DataAccessException;

    public int addArticle(Map<String, Object> map, HttpServletRequest request) throws Exception;

    public int removeArticle(String articleNO) throws DataAccessException;

    public BoardVO getArticle(String articleNO) throws DataAccessException;

    public int updateArticle(Map<String, Object> map, HttpServletRequest request) throws Exception;

    public List<Map<String, Object>> selectFileList(String articleNO);

}
