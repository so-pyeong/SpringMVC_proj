package kopo.aisw.basic_mvc.common.dao;

import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class AbstractDAO {
    @Autowired
    private SqlSessionTemplate sqlSession;

    protected void printQueryId(String queryId) {
        System.out.println("\t QueryID \t :" + queryId);
    }

    public Object insert(String queryID, Object params) {
        printQueryId(queryID);
        return sqlSession.insert(queryID, params);
    }

    public Object update(String queryID, Object params) {
        printQueryId(queryID);
        return sqlSession.update(queryID, params);
    }

    public Object delete(String queryID, Object params) {
        printQueryId(queryID);
        return sqlSession.delete(queryID, params);
    }

    public Object selectOne(String queryID) {
        printQueryId(queryID);
        return sqlSession.selectOne(queryID);
    }

    public Object selectOne(String queryID, Object params) {
        printQueryId(queryID);
        return sqlSession.selectOne(queryID, params);
    }

    @SuppressWarnings("rawtypes")
    public List selectList(String queryID) {
        printQueryId(queryID);
        return sqlSession.selectList(queryID);
    }

    @SuppressWarnings("rawtypes")
    public List selectList(String queryID, Object params) {
        printQueryId(queryID);
        return sqlSession.selectList(queryID, params);
    }
}
