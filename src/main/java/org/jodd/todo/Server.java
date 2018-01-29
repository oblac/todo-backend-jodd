package org.jodd.todo;

import jodd.io.FileUtil;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import javax.servlet.ServletException;
import java.io.File;
import java.io.IOException;

/**
 * Simple Tomcat embedded server.
 */
public class Server {

	public static void main(String[] args) throws LifecycleException, ServletException, IOException {
		printOutEnvirnoment();

		System.setProperty("org.apache.catalina.startup.EXIT_ON_INIT_FAILURE", "true");

		final Tomcat tomcat = new Tomcat();

		String port = System.getenv("PORT");
		if (port == null || port.isEmpty()) {
			port = "8080";
		}

		tomcat.setPort(Integer.valueOf(port));
		tomcat.setBaseDir(FileUtil.createTempDirectory("joy", "tomcat").getAbsolutePath());

		final String docBase = ".";

		Context context = tomcat.addWebapp("", new File(docBase).getAbsolutePath());

		context.addApplicationListener(TodoAppContextListener.class.getName());

		tomcat.start();
		tomcat.getServer().await();
	}

	private static void printOutEnvirnoment() {
		System.getenv().forEach((key, value) -> System.out.println(key + "=" + value));
	}
}
