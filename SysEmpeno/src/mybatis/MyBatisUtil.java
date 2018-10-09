package mybatis;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.io.IOException;
import java.io.Reader;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
/**
 *
 * @author Enrique Ceballos Mtz
 */




public class MyBatisUtil {
    public final static String RESOURCE = "mybatis/mybatis.config.xml";
    public final static String ENVIRONMENT = "development";
    public static SqlSession getSession() throws IOException {
        SqlSession session = null;
        Reader reader = Resources.getResourceAsReader(RESOURCE);
        SqlSessionFactory sqlMapper = new SqlSessionFactoryBuilder().build(reader, ENVIRONMENT);
        session = sqlMapper.openSession();
        return session; 
    }
}

