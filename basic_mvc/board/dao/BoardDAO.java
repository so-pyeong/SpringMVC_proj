package kopo.aisw.basic_mvc.board.dao;

import kopo.aisw.basic_mvc.board.vo.BoardVO;
import org.springframework.dao.DataAccessException;

import java.util.List;
import java.util.Map;

public interface BoardDAO {
    public List selectAllArticlesList() throws DataAccessException;

    public int insertArticle(Map<String, Object> map) throws DataAccessException;

    public int deleteArticle(String articleNO) throws DataAccessException;

    // 수정
    public int maxArticleNum() throws DataAccessException;

    public BoardVO selectArticle(String articleNO) throws DataAccessException;

    public int updateArticle(Map<String, Object> map) throws DataAccessException;

    public void insertFile(Map<String, Object> map) throws DataAccessException;

    public List<Map<String, Object>> selectFileList(String articleNO);

    public BoardVO selectHitCnt(String cnt) throws DataAccessException;
    public int updateHitCnt(BoardVO boardVO) throws DataAccessException;
}
