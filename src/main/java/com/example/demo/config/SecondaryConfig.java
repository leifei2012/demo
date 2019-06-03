//package com.example.demo.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
//import org.springframework.orm.jpa.JpaTransactionManager;
//import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
//import org.springframework.transaction.PlatformTransactionManager;
//import org.springframework.transaction.annotation.EnableTransactionManagement;
//
//import javax.sql.DataSource;
//
//@Configuration
//@EnableTransactionManagement
//@EnableJpaRepositories(
//        entityManagerFactoryRef = "entityManagerFactorySecondary",  //实体管理器
//        transactionManagerRef = "transactionManagerSecondary", //事务管理器
//        basePackages = {"com.example.demo.SecondaryDao"})    // 指定该数据源操作的DAO接口包
//public class SecondaryConfig {
////非动态切换数据源:
//    //这么写导致druid无法监控到此数据源:
////    @Bean(name = "secondaryDataSource")
////    @Qualifier("secondaryDataSource")
////    @ConfigurationProperties(prefix = "spring.datasource.test")
////    public DataSource secondaryDataSource() {
////        return DataSourceBuilder.create().build();
////    }
//
//    @ConfigurationProperties(prefix = "spring.datasource.slave")
//    @Bean
//    @Qualifier("DruidDataSourceslave")
//    public DataSource druidDataSourceslave() {
//        return new DruidDataSource();
//      }
//
//    @Autowired
//    @Qualifier("DruidDataSourceslave")
//    private DataSource secondaryDataSource;
//
//
//    @Bean(name = "entityManagerFactorySecondary")
//    public LocalContainerEntityManagerFactoryBean entityManagerFactorySecondary(EntityManagerFactoryBuilder builder) {
//        return builder
//                .dataSource(secondaryDataSource)
//                .packages("com.example.demo.secentity")         //设置实体类所在位置
//                .persistenceUnit("SecondaryPersistenceUnit")
//                .build();
//    }
//
//    @Bean(name = "transactionManagerSecondary")
//    public PlatformTransactionManager transactionManagerSecondary(EntityManagerFactoryBuilder builder) {
//        return new JpaTransactionManager(entityManagerFactorySecondary(builder).getObject());
//    }
//}
//
