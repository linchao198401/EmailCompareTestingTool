package org.performance.web.common.util;

import java.io.ByteArrayOutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.Locale;
import java.util.Map;

import freemarker.cache.StringTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

public class FreeMarkerUtil {

	public static String filterByFreeMarker(String templateContent, Map parameters) {
		try {
			String templateName = "DEFAULT";
			StringTemplateLoader stringLoader = new StringTemplateLoader();
			stringLoader.putTemplate(templateName, templateContent);
			Configuration cfg = new Configuration();
			cfg.setLocale(Locale.US);
			cfg.setTemplateLoader(stringLoader);
			Template template = cfg.getTemplate(templateName);
			ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
			Writer out = new OutputStreamWriter(byteArrayOutputStream);
			template.process(parameters, out);
			return new String(byteArrayOutputStream.toByteArray());
		}
		catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
