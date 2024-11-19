package kopo.aisw.basic_mvc.board.dao;

import kopo.aisw.basic_mvc.board.vo.BoardVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository("boardDAO")
public class BoardDAOImpl implements BoardDAO {
    @Autowired
    private SqlSession sqlSession;

    @Override
    public List selectAllArticlesList() throws DataAccessException {
        List<BoardVO> articlesList = null;
        articlesList = sqlSession.selectList("mapper.board.selectAllArticlesList");
        return articlesList;
    }

    @Override
    public int insertArticle(Map<String, Object> map) throws DataAccessException {
        int result = sqlSession.insert("mapper.board.insertArticle", map);
        return result;
    }

    // 파일 업로드
    @Override
    public void insertFile(Map<String, Object> map) throws DataAccessException {
        sqlSession.insert("mapper.board.insertFile", map);
    }

    @Override
    public int deleteArticle(String articleNO) throws DataAccessException {
        int result = sqlSession.delete("mapper.board.deleteArticle", articleNO);
        return result;
    }

    // 수정 : Max ArticleNO 구하기
    @Override
    public int maxArticleNum() throws DataAccessException {
        return sqlSession.selectOne("mapper.board.getMaxArticleNO");
    }

    @Override
    public BoardVO selectArticle(String articleNO) throws DataAccessException {
        BoardVO boardVO = sqlSession.selectOne("mapper.board.articleView", articleNO);
        return boardVO;
    }


    @Override
    public int updateArticle(Map<String, Object> map) throws DataAccessException {
        return sqlSession.update("mapper.board.updateArticle", map);
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<Map<String, Object>> selectFileList(String articleNO) {
        return sqlSession.selectList("mapper.board.selectFileList", articleNO);
    }

    @Override
    public BoardVO selectHitCnt(String cnt) throws DataAccessException {
        return sqlSession.selectOne("mapper.board.selectHitCnt", cnt);
    }

    @Override
    public int updateHitCnt(BoardVO boardVO) throws DataAccessException {
        return sqlSession.update("mapper.board.updateHitCnt", boardVO);
    }
}
