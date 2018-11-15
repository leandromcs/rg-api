package br.com.bancogeral.BancoGeral.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
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
        basePackages = "br.com.bancogeral.BancoGeral.banco2.repository",
        entityManagerFactoryRef = "banco2EntityManager",
        transactionManagerRef = "banco2TransactionManager"
)
public class Banco2Config {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean banco2EntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(banco2DataSource());
        em.setPackagesToScan(
                new String[] { "br.com.bancogeral.BancoGeral.domain" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("registrogeral2.jpa.hibernate.ddl-auto",
                env.getProperty("registrogeral2.jpa.hibernate.ddl-auto"));
        properties.put("registrogeral2.jpa.properties.hibernate.dialect",
                env.getProperty("registrogeral2.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource banco2DataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("registrogeral2.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("registrogeral2.datasource.url"));
        dataSource.setUsername(env.getProperty("registrogeral2.datasource.username"));
        dataSource.setPassword(env.getProperty("registrogeral2.datasource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager banco2TransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                banco2EntityManager().getObject());
        return transactionManager;
    }
}
