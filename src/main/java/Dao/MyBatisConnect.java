package Dao;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;

import java.io.IOException;
import java.io.InputStream;

public class MyBatisConnect {

    //mybatis初始化

    SqlSession sqlSession;
    String resource = "mybatis-config.xml";

    public MyBatisConnect() throws IOException {
        resource = "mybatis-config.xml";
        // mybatis-config.xml

        // 读取配置文件
        InputStream is =  Resources.getResourceAsStream(this.resource);

        // 构建SqlSessionFactory
        SqlSessionFactory sqlSessionFactory = new SqlSessionFactoryBuilder().build(is);

        // 获取sqlSession
        sqlSession = sqlSessionFactory.openSession();
    }

    public SqlSession getSqlSession() {
        return sqlSession;
    }

    public void closeSqlSession()
    {
        this.sqlSession.close();
    }
}
