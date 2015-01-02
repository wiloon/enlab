package com.wiloon.enlab.dictionary;

import com.wiloon.enlab.domain.ECP;
import org.mybatis.spring.support.SqlSessionDaoSupport;

import java.util.List;

/**
 * Created by wiloon on 1/1/15.
 */
public class DictionaryDaoImpl extends SqlSessionDaoSupport implements DictionaryDao {
    @Override
    public List<ECP> selectEnRagne() {
        ECP ecp = new ECP();
        ecp.setEnglish("test");
        return this.getSqlSession().selectList("selectEnRagne", ecp);
    }
}
