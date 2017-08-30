package com.imediawork.jackson;

import java.io.File;
import java.io.IOException;
import java.io.StringWriter;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.file.Files;
import java.text.DateFormat;
import java.util.Date;
import java.util.Locale;

import org.apache.commons.codec.binary.Base64;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.imediawork.jackson.domain.BrokerCatalog;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController
{

	private static final Logger logger = LoggerFactory.getLogger(HomeController.class);

	/**
	 * Simply selects the home view to render by returning its name.
	 * 
	 * @throws IOException
	 */
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String testLocal(Locale locale, Model model) throws IOException
	{
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("result", testJsonFromLocalResource());
		return "home";
	}

	@RequestMapping(value = "/url", method = RequestMethod.GET)
	public String testUrl(Locale locale, Model model) throws IOException
	{
		logger.info("Welcome home! The client locale is {}.", locale);
		Date date = new Date();
		DateFormat dateFormat = DateFormat.getDateTimeInstance(DateFormat.LONG, DateFormat.LONG, locale);
		String formattedDate = dateFormat.format(date);
		model.addAttribute("serverTime", formattedDate);
		model.addAttribute("result", testJsonFromURL("http://172.10.2.218:8888/?command=Get-BrokerCatalog", "portal\\administrator", "P@ssw0rd"));
		return "home";
	}

	private String testJsonFromLocalResource() throws IOException
	{
		String result = "";

		File folder = new ClassPathResource("json").getFile();
		File[] fileList = folder.listFiles();
		for (File file : fileList) {
			logger.info(file.getPath());
			// parse json
			byte[] jsonData = Files.readAllBytes(file.toPath());
			ObjectMapper objectMapper = new ObjectMapper();
			BrokerCatalog[] catalogArray = objectMapper.readValue(jsonData, BrokerCatalog[].class);
			logger.info("parsed " + catalogArray.length);

			// generate json
			objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
			for (BrokerCatalog catalog : catalogArray) {
				StringWriter jsonString = new StringWriter();
				objectMapper.writeValue(jsonString, catalog);
				logger.info("BrokerCatalog JSON is\n" + jsonString);
				result += jsonString.toString();
			}
		}

		return result;
	}

	private String testJsonFromURL(String urlPath, String username, String password) throws IOException
	{
		String result = "";

		URL url = new URL(urlPath);
		HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
		String encoding = Base64.encodeBase64String((username + ":" + password).getBytes());
		urlConnection.setRequestProperty("Authorization", "Basic  " + encoding);
		urlConnection.connect();
		logger.info("username = " + username + ", password = " + password);
		logger.info("connect status = " + urlConnection.getResponseCode());

		ObjectMapper objectMapper = new ObjectMapper();
		BrokerCatalog[] catalogArray = objectMapper.readValue(urlConnection.getInputStream(), BrokerCatalog[].class);
		logger.info("parsed " + catalogArray.length);

		// generate json
		objectMapper.configure(SerializationFeature.INDENT_OUTPUT, true);
		for (BrokerCatalog catalog : catalogArray) {
			StringWriter jsonString = new StringWriter();
			objectMapper.writeValue(jsonString, catalog);
			logger.info("BrokerCatalog JSON is\n" + jsonString);
			result += jsonString.toString();
		}

		return result;
	}
}
