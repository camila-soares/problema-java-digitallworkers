package com.digitallworkers.config;

import java.io.File;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.engine.fill.JRSwapFileVirtualizer;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.util.JRSaver;
import net.sf.jasperreports.engine.util.JRSwapFile;

@Configuration
public class ReportConfig {

	@Bean
	JasperReport report() throws JRException {
		JasperReport jr = null;
		File f = new File("receiptReport.jasper");
		if (f.exists()) {
			jr = (JasperReport) JRLoader.loadObject(f);
		} else {
			jr = JasperCompileManager.compileReport("src/main/resources/receipt.jrxml");
			JRSaver.saveObject(jr, "receiptReport.jasper");
		}
		return jr;
	}
	
	@Bean
	JRFileVirtualizer fileVirtualizer() {
		return new JRFileVirtualizer(100, "C:\\");
	}
	
	@Bean
	JRSwapFileVirtualizer swapFileVirtualizer() {
		JRSwapFile sf = new JRSwapFile("C:\\", 1024, 100);
		return new JRSwapFileVirtualizer(20, sf, true);
	}


}
