package org.jodd.todo;

import jodd.io.FileUtil;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

public class Server {

	public static void main(String[] args) throws LifecycleException, ServletException, IOException {
		System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");

		final Tomcat tomcat = new Tomcat();

		tomcat.setPort(8080);
		tomcat.setBaseDir(FileUtil.createTempDirectory("joy", "tomcat").getAbsolutePath());

		final String docBase = ".";

		Context context = tomcat.addWebapp("", new File(docBase).getAbsolutePath());

		context.addApplicationListener(TodoAppContextListener.class.getName());

		tomcat.start();
		tomcat.getServer().await();
	}
}