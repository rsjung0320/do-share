package com.nextinno.doshare.config.db;

import javax.sql.DataSource;

import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.nextinno.doshare.config.DoShareConfig;
import com.nextinno.doshare.config.db.support.DoShareDb;

/**
 * 메인 데이터베이스(dodb) 관련 설정 클래스.
 * 
 * @author rsjung
 *
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = DoShareConfig.DEFAULT_BASE_PACKAGE, annotationClass = DoShareDb.class,
        sqlSessionFactoryRef = "doShareDbSqlSessionFactoryBean")
public class DoShareDbConfig {
    @Bean
    public DataSource doShareDbDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource =
                new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName("com.mysql.jdbc.Driver");
        // to-do 설정 값에서 가져오도록 한다. github에 올라가면 안되는 정보이기 때문에
        dataSource.setUrl("jdbc:mysql://192.168.13.165:3306/dodb?useUnicode=true&characterEncoding=utf8");
        dataSource.setUsername("rsjung");
        dataSource.setPassword("rsjung");

        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager doShareDbTransactionManager(
            @Qualifier("doShareDbDataSource") DataSource doShareDbDataSource) {
        return new DataSourceTransactionManager(doShareDbDataSource);
    }

    @Bean
    public SqlSessionFactoryBean doShareDbSqlSessionFactoryBean(
            @Qualifier("doShareDbDataSource") DataSource doShareDbDataSource) throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(doShareDbDataSource);
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                .getResources(DoShareConfig.MAPPER_LOCATIONS_PATH));
        return sessionFactory;
    }
}
