package com.digitallworkers.controllers;

import java.io.FileInputStream;
import java.net.URI;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.sql.DataSource;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.digitallworkers.domain.Receipt;
import com.digitallworkers.services.ReceiptService;

import javassist.tools.rmi.ObjectNotFoundException;
import net.sf.jasperreports.engine.JRParameter;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.fill.JRFileVirtualizer;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;

@RestController
@RequestMapping("/receipt")
public class ReceiptController {
	
	protected Logger logger = Logger.getLogger(ReceiptController.class.getName());
	public static int count = 0;
	
	@Autowired
	JRFileVirtualizer fv;
	
	@Autowired
	DataSource datasource;
	
	@Autowired
	JasperReport jasperReport;

	@Autowired
	private ReceiptService service;


	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<Void> insert(@Valid @RequestBody Receipt obj){
		obj = service.criarComprovante(obj);
		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).build();
	}

	@GetMapping("/listAll")
	public ResponseEntity<List<Receipt>> findAll() throws ObjectNotFoundException {
		List<Receipt> list = service.findAll();
		return ResponseEntity.ok().body(list);

	}
	
	@GetMapping("/download/{id}")
	public ResponseEntity<InputStreamResource> getReport(@PathVariable("id") Integer id){
		logger.info("getReport(" + id + ")");
		Map<String, Object> m = new HashMap<>();
		m.put(JRParameter.REPORT_VIRTUALIZER, fv);
		m.put("id", id);
		String name = ++count + "receiptReport.pdf";
		return generateReport(name, m );
	}

	private ResponseEntity<InputStreamResource> generateReport(String name, Map<String, Object> params) {
		FileInputStream st = null;
		Connection cc = null;
		try {
			cc = datasource.getConnection();
			JasperPrint p = JasperFillManager.fillReport(jasperReport, params, cc);
			JRPdfExporter exporter = new JRPdfExporter();
			SimpleOutputStreamExporterOutput c = new SimpleOutputStreamExporterOutput(name);
			exporter.setExporterInput(new SimpleExporterInput(p));
			exporter.setExporterOutput(c);
			exporter.exportReport();
			
			st = new FileInputStream(name);
			HttpHeaders responseHeaders = new HttpHeaders();
			responseHeaders.setContentType(MediaType.valueOf("application/pdf"));
			responseHeaders.setContentDispositionFormData("attachment", name);
			responseHeaders.setContentLength(st.available());
		    return new ResponseEntity<InputStreamResource>(new InputStreamResource(st), responseHeaders, HttpStatus.OK);
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			fv.cleanup();
			if (cc != null)
				try {
					cc.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return null;
	}
	
}


