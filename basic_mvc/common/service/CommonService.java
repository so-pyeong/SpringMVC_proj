package kopo.aisw.basic_mvc.common.service;

import org.springframework.dao.DataAccessException;

import java.util.Map;

public interface CommonService {

    public Map<String,Object> selectFileInfo(Map<String,Object> map) throws Exception;

    public void deleteFile(Map<String, Object> map) throws DataAccessException;
}
