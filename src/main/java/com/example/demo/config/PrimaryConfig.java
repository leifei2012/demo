//package com.example.demo.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManagerFactoryPrimary",  //实体管理器
//        transactionManagerRef = "transactionManagerPrimary", //事务管理器
//        basePackages = {"com.example.demo.dao"})    // 指定该数据源操作的DAO接口包
////非动态切换数据源:
//public class PrimaryConfig {
//
//    @Bean(name = "primaryDataSource")
//    @Qualifier("primaryDataSource")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.sell")
//    public DataSource druidDataSource() {
//        return new DruidDataSource();
//    }
//
//    @Autowired
//    @Qualifier("primaryDataSource")
//    private DataSource primaryDataSource;
//
//    @Primary
//    @Bean(name = "entityManagerFactoryPrimary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactoryPrimary(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(primaryDataSource)
//                .packages("com.example.demo.entity")         //设置实体类所在位置
//                .persistenceUnit("primaryPersistenceUnit")
//                .build();
//    }
//
//    @Primary
//    @Bean(name = "transactionManagerPrimary")
//    public PlatformTransactionManager transactionManagerPrimary(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactoryPrimary(builder).getObject());
//    }
//
//}
//
