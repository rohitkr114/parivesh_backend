package com.backend.dbManagers;

import java.security.InvalidKeyException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import javax.crypto.BadPaddingException;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;
import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.core.env.Environment;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.backend.util.RSAUtil;
import com.google.common.base.Preconditions;

@Configuration
@EnableJpaRepositories(basePackages = {"com.backend.repository.postgres",
        "com.backend.model", "com.backend.crz.repository", "com.backend.NSWS.*"}, entityManagerFactoryRef = "postgresEntityManager", transactionManagerRef = "postgresTransactionManager")
@EnableTransactionManagement
public class PersistencePostGresConfiguration {

    @Autowired
    private Environment env;

    public PersistencePostGresConfiguration() {
        super();
    }

    @Primary
    @Bean
    public LocalContainerEntityManagerFactoryBean postgresEntityManager() {
        String[] packagesArray = "com.backend.model,com.backend.crz.repository,com.backend.NSWS.*".split(",");
        final LocalContainerEntityManagerFactoryBean em = new LocalContainerEntityManagerFactoryBean();
        em.setDataSource(postgresDataSource());
        em.setPackagesToScan(packagesArray);

        final HibernateJpaVendorAdapter vendorAdapter = new HibernateJpaVendorAdapter();
        em.setJpaVendorAdapter(vendorAdapter);
        final HashMap<String, Object> properties = new HashMap<String, Object>();
        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.ddl-auto"));
        properties.put("hibernate.dialect", env.getProperty("postgres.hibernate.dialect"));
        properties.put("org.hibernate.envers.audit_table_suffix", "_aud");

        properties.put("hibernate.listeners.envers.autoRegister", true);
        properties.put("hibernate.envers.autoRegisterListeners", true);
//		properties.put("hibernate.show_sql", true);
//		properties.put("hibernate.format_sql", true);
        em.setJpaPropertyMap(properties);

        return em;
    }

//	@Primary
//	@Bean
//	public DataSource postgresDataSource() {
//		final DriverManagerDataSource dataSource = new DriverManagerDataSource();
//		dataSource.setDriverClassName(Preconditions.checkNotNull(env.getProperty("jdbc.driverClassName")));
//		dataSource.setUrl(Preconditions.checkNotNull(env.getProperty("jdbc.url")));
//		try {
//			dataSource.setUsername(
//					Preconditions.checkNotNull(RSAUtil.decrypt(env.getProperty("jdbc.user"), RSAUtil.privateKey)));
//			dataSource.setPassword(
//					Preconditions.checkNotNull(RSAUtil.decrypt(env.getProperty("jdbc.pass"), RSAUtil.privateKey)));
//		} catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
//				| NoSuchPaddingException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		return dataSource;
//	}

    @Primary
    @Bean
    public DataSource postgresDataSource() {
        org.apache.tomcat.jdbc.pool.DataSource dataSource = new org.apache.tomcat.jdbc.pool.DataSource();
        dataSource.setDriverClassName(env.getProperty("jdbc.driverClassName"));
        dataSource.setUrl(env.getProperty("jdbc.url"));
        try {
            dataSource.setUsername(RSAUtil.decrypt(env.getProperty("jdbc.user"), RSAUtil.privateKey));
            dataSource.setPassword(RSAUtil.decrypt(env.getProperty("jdbc.pass"), RSAUtil.privateKey));
            dataSource.setInitialSize(Integer.parseInt(env.getProperty("spring.datasource.tomcat.initial-size")));
            dataSource.setMaxActive(Integer.parseInt(env.getProperty("spring.datasource.tomcat.max-active")));
            dataSource.setMaxIdle(Integer.parseInt(env.getProperty("spring.datasource.tomcat.max-idle")));
            dataSource.setMinIdle(Integer.parseInt(env.getProperty("spring.datasource.tomcat.min-idle")));
        } catch (InvalidKeyException | IllegalBlockSizeException | BadPaddingException | NoSuchAlgorithmException
                 | NoSuchPaddingException e) {
            e.printStackTrace();
        }
        return dataSource;
    }

    @Primary
    @Bean
    public PlatformTransactionManager postgresTransactionManager() {
        final JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(postgresEntityManager().getObject());
        return transactionManager;
    }
}
