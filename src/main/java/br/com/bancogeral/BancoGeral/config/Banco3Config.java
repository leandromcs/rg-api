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
        basePackages = "br.com.bancogeral.BancoGeral.banco3.repository",
        entityManagerFactoryRef = "banco3EntityManager",
        transactionManagerRef = "banco3TransactionManager"
)
public class Banco3Config {

    @Autowired
    private Environment env;

    @Bean
    public LocalContainerEntityManagerFactoryBean banco3EntityManager() {
        LocalContainerEntityManagerFactoryBean em
                = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(banco3DataSource());
        em.setPackagesToScan(
                new String[] { "br.com.bancogeral.BancoGeral.domain" });

        HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        HashMap<String, Object> properties = new HashMap<>();
        properties.put("registrogeral3.jpa.hibernate.ddl-auto",
                env.getProperty("registrogeral3.jpa.hibernate.ddl-auto"));
        properties.put("registrogeral3.jpa.properties.hibernate.dialect",
                env.getProperty("registrogeral3.jpa.properties.hibernate.dialect"));
        em.setJpaPropertyMap(properties);

        return em;
    }

    @Bean
    public DataSource banco3DataSource() {

        DriverManagerDataSource dataSource
                = new DriverManagerDataSource();
        dataSource.setDriverClassName(
                env.getProperty("registrogeral3.datasource.driverClassName"));
        dataSource.setUrl(env.getProperty("registrogeral3.datasource.url"));
        dataSource.setUsername(env.getProperty("registrogeral3.datasource.username"));
        dataSource.setPassword(env.getProperty("registrogeral3.datasource.password"));

        return dataSource;
    }

    @Bean
    public PlatformTransactionManager banco3TransactionManager() {

        JpaTransactionManager transactionManager
                = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(
                banco3EntityManager().getObject());
        return transactionManager;
    }
}
