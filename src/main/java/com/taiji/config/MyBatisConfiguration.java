package com.taiji.config;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.jdbc.DataSourceBuilder;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;


/**
 * Created by Sleeb on 2017/5/2.
 */
@Configuration
@ConfigurationProperties(prefix = "mybatis")
@MapperScan(basePackages="com.taiji.dao.manage")
public class MyBatisConfiguration {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private String typeAliasesPackage;
    private String mapperLocations;

    public String getTypeAliasesPackage() {
        return typeAliasesPackage;
    }

    public void setTypeAliasesPackage(String typeAliasesPackage) {
        this.typeAliasesPackage = typeAliasesPackage;
    }

    public String getMapperLocations() {
        return mapperLocations;
    }

    public void setMapperLocations(String mapperLocations) {
        this.mapperLocations = mapperLocations;
    }


    @Bean(name = "dataSource",destroyMethod = "close")
    @ConfigurationProperties(prefix = "pooled.c3p0")
    public DataSource getDataSource(){
        return DataSourceBuilder.create().type(com.mchange.v2.c3p0.ComboPooledDataSource.class).build();
    }

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(){
        try {
            SqlSessionFactoryBean sessionFactory=new SqlSessionFactoryBean();
            sessionFactory.setDataSource(getDataSource());
            sessionFactory.setTypeAliasesPackage(getTypeAliasesPackage());
            sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver()
                            .getResources(getMapperLocations()));

            return  sessionFactory.getObject();
        }catch (Exception ex){
            logger.error(ex.getMessage(),ex);
            return  null;
        }
    }

    @Bean(name = "sqlSessionTemplate")
    public SqlSessionTemplate sqlSessionTemplate(){
        SqlSessionTemplate sessionTemplate=new SqlSessionTemplate(sqlSessionFactory());

        return sessionTemplate;
    }

}
