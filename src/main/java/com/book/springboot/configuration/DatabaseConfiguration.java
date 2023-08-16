package com.book.springboot.configuration;
import javax.sql.DataSource;


import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;


@Configuration
//application.properties를 사용할 수 있도록 설정 파일의 위치를 지정해 줍니다.
//@PropertySource 을 추가해 다른설정 파일도 사용할 수 있다.
@PropertySource("classpath:/application.properties")
public class DatabaseConfiguration {

    //마이바티스 연결
    @Autowired
    private ApplicationContext applicationContext;

    // hikari 설정
    @Bean
    @ConfigurationProperties(prefix="spring.datasource.hikari")
    public HikariConfig hikariConfig(){
        return new HikariConfig();
    }
    // hikari 설정
    @Bean
    public DataSource dataSource() throws Exception{
        DataSource dataSource = new HikariDataSource(hikariConfig());
        System.out.println("이곳 =>"+dataSource.toString());
        return dataSource;
    }

    //마이바티스 연결
    @Bean
    public SqlSessionFactory sqlSessionFactory(DataSource dataSource) throws Exception{
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
//        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/**/sql-*.xml"));
        sqlSessionFactoryBean.setMapperLocations(applicationContext.getResources("classpath:/mapper/sql-*.xml"));

        //마이바티스 관련 내용
        sqlSessionFactoryBean.setConfiguration(mybatisConfig());
        return sqlSessionFactoryBean.getObject();
    }
    @Bean
    public SqlSessionTemplate sqlSessionTemplate(SqlSessionFactory sqlSessionFactory){
        return new SqlSessionTemplate(sqlSessionFactory);
    }

    //마이바티스 빈 등록
    @Bean
    //application.properties의 설정 중 마이바티스에 관련된 설정을 가져옴
    @ConfigurationProperties(prefix="mybatis.configuration")
    public org.apache.ibatis.session.Configuration mybatisConfig(){
        //가져온 마이바티스 설정을 자바 클래스로 만들어서 반환
        return new org.apache.ibatis.session.Configuration();
    }

}
