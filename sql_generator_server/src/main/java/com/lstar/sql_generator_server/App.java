package com.lstar.sql_generator_server;

import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.context.WebServerApplicationContext;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.boot.web.server.WebServer;
import org.springframework.context.ApplicationListener;
import org.springframework.core.env.Environment;
import org.springframework.scheduling.annotation.EnableScheduling;

import java.net.InetAddress;

@SpringBootApplication
@MapperScan("com.lstar.sql_generator_server.mapper")
@EnableScheduling
@Slf4j
public class App implements ApplicationListener<WebServerInitializedEvent>
{

	public static void main(String[] args)
	{
		SpringApplication.run(App.class, args);
	}

	@SneakyThrows
	@Override
	public void onApplicationEvent(WebServerInitializedEvent event)
	{
		WebServer webServer = event.getWebServer();
		WebServerApplicationContext context = event.getApplicationContext();
		Environment env = context.getEnvironment();
		String ip = InetAddress.getLocalHost().getHostAddress();
		int port = webServer.getPort();
		String contextPath = env.getProperty("server.servlet.context-path");
		if (contextPath == null)
			contextPath = "";

		log.info("\nLocal: http://localhost:{}{}\nRemote: http://{}:{}{}\nDoc: http://localhost:{}{}/doc.html",
				port, contextPath, ip, port, contextPath, port, contextPath);
	}
}
