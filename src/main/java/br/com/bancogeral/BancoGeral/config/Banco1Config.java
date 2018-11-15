package br.com.bancogeral.BancoGeral.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;

@Configuration
@PropertySource({ "classpath:application.properties" })
@EnableJpaRepositories(
        basePackages = "br.com.bancogeral.BancoGeral.banco1.repository",
        entityManagerFactoryRef = "banco1EntityManager",
        transactionManagerRef = "banco1TransactionManager"
)
public class Banco1Config {

    @Autowired
    private Environment env;

    @Bean
    @Primary
    public LocalContainerEntityManagerFactoryBean banco1EntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(banco1DataSource());
        em.setPackagesToScan(
                new String[] { "br.com.bancogeral.BancoGeral.domain" });

        HibernateJpaVendorAdapter vendorAdapter
                = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("registrogeral.jpa.hibernate.ddl-auto",
                env.getProperty("registrogeral.jpa.hibernate.ddl-auto"));
        properties.put("registrogeral.jpa.properties.hibernate.dialect",
                env.getProperty("registrogeral.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Primary
    @Bean
    public DataSource banco1DataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("registrogeral.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("registrogeral.datasource.url"));
        dataSource.setUsername(env.getProperty("registrogeral.datasource.username"));
        dataSource.setPassword(env.getProperty("registrogeral.datasource.password"));

        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager banco1TransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                banco1EntityManager().getObject());
        return transactionManager;
    }
}

