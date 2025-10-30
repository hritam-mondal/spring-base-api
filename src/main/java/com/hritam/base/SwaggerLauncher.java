package com.hritam.base;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

import java.awt.*;
import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

@Component
public class SwaggerLauncher implements ApplicationListener<ApplicationReadyEvent> {

	@Value("${app.open-swagger:false}")
	private boolean openSwagger;

	@Value("${server.port:8080}")
	private int port;

	@Value("${springdoc.swagger-ui.path:/swagger-ui/index.html}")
	private String swaggerPath;

	@Override
	public void onApplicationEvent(ApplicationReadyEvent event) {
		if (!openSwagger) return;
		String url = buildUrl();
		try {
			openBrowser(url);
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	private String buildUrl() {
		String path = swaggerPath.startsWith("/") ? swaggerPath : "/" + swaggerPath;
		return "http://localhost:" + port + path;
	}

	private void openBrowser(String url) throws IOException, URISyntaxException {
		if (Desktop.isDesktopSupported()) {
			Desktop.getDesktop().browse(new URI(url));
			return;
		}

		String os = System.getProperty("os.name").toLowerCase();
		Runtime rt = Runtime.getRuntime();
		if (os.contains("mac")) {
			rt.exec(new String[]{"open", url});
		} else if (os.contains("win")) {
			rt.exec(new String[]{"rundll32", "url.dll,FileProtocolHandler", url});
		} else { // assume unix / linux
			rt.exec(new String[]{"xdg-open", url});
		}
	}
}
