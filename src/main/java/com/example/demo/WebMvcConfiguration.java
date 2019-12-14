package com.example.demo;


//import com.saintsoftware.filter.CorsFilter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.AutowireCapableBeanFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@Configuration
public class WebMvcConfiguration extends WebMvcConfigurerAdapter {

	@Autowired
	AutowireCapableBeanFactory beanFactory;	
	private DriverManagerDataSource getDriverManagerDataSource(String driver, String url, String username, String password) {
		DriverManagerDataSource dataSource = new DriverManagerDataSource();
		dataSource.setDriverClassName(driver);
		dataSource.setUrl(url);
		dataSource.setUsername(username);
		dataSource.setPassword(password);
		return dataSource;
	}
	@Value("${ds_hive_metastore.url}")
	private String hiveUrl;
	@Value("${ds_hive_metastore.driver-class-name}")
	private  String hiveDriver;
	@Value("${ds_hive_metastore.username}")
	private String hiveUsername;
	@Value("${ds_hive_metastore.password}")
	private String hivePassword;

	public DriverManagerDataSource getHiveDataSource() {
		return getDriverManagerDataSource(hiveDriver, hiveUrl, hiveUsername, hivePassword);

	}

	@Bean(name = "jdbcHive")
	@Autowired
	public JdbcTemplate hiveJdbcTemplate() {
		return new JdbcTemplate(getHiveDataSource());
	}
	
	@Value("${ds_hive_url}")
	private String hiveUrl1;
	@Value("${ds_hive_driver-class-name}")
	private  String hiveDriver1;
	@Value("${ds_hive_username}")
	private String hiveUsername1;
	@Value("${ds_hive_password}")
	private String hivePassword1;

	public DriverManagerDataSource getHiveDataSource1() {
		return getDriverManagerDataSource(hiveDriver1, hiveUrl1, hiveUsername1, hivePassword1);

	}

	@Bean(name = "jdbcHive1")
	@Autowired
	public JdbcTemplate hiveJdbcTemplate1() {
		return new JdbcTemplate(getHiveDataSource1());
	}
}