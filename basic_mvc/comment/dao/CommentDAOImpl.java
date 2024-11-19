package kopo.aisw.basic_mvc.comment.dao;

import kopo.aisw.basic_mvc.comment.vo.CommentVO;
import org.apache.ibatis.session.SqlSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("commentDAO")
public class CommentDAOImpl implements CommentDAO {
    @Autowired
    SqlSession sqlSession;

    @Override
    public List selectAllComment(String parentsNO) throws DataAccessException {
        List<CommentVO> commentsList = null;
        commentsList = sqlSession.selectList("mapper.comment.selectCommentList", parentsNO);
        return commentsList;
    }

    @Override
    public int insertComment(CommentVO commentVO) throws DataAccessException {
        return sqlSession.insert("mapper.comment.insertComment", commentVO);
    }

    @Override
    public int deleteComment(String commentNO) throws DataAccessException {
        return sqlSession.delete("mapper.comment.deleteComment", commentNO);
    }

    @Override
    public int updateComment(CommentVO commentVO) throws DataAccessException {
        return sqlSession.update("mapper.comment.updateComment", commentVO);
    }

    @Override
    public int maxCommentNO() throws DataAccessException {
        return sqlSession.selectOne("mapper.comment.getMaxCommentNO");
    }

}
