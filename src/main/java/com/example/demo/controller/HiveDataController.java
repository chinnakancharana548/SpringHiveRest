package com.example.demo.controller;


import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.transaction.Transactional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.HiveDataDto;
import com.example.demo.dto.HiveTableStatDto;
import com.example.demo.dto.ResponseWrapper;
import com.example.demo.service.HiveDataService;

@RestController
@RequestMapping("/api/v1")
public class HiveDataController {
	
	private final HiveDataService hiveDataService;
	
	@Autowired
	public HiveDataController(HiveDataService hiveDataService) {
		this.hiveDataService = hiveDataService;
	}
	
	@RequestMapping(value = "/hive/details", method = RequestMethod.GET)
	@Transactional
	public ResponseWrapper getDetails( HttpServletRequest httpRequest, HttpServletResponse httpResponse, HttpSession session) {
		
		ResponseWrapper response = new ResponseWrapper(true);
		try {
			String databaseName = httpRequest.getParameter("database");
			List<HiveDataDto> dataDtos = hiveDataService.getDetails(databaseName);
			response.setData(dataDtos);
			httpResponse.setStatus(HttpStatus.OK.value());
			response.setMessage("ok");
			response.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			httpResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
			response.setMessage("not ok");
		}
		return response;
	}
	
	@RequestMapping(value = "/hive/details/{tableId}", method = RequestMethod.GET)
	@Transactional
	public ResponseWrapper getDetailsForTable(@PathVariable String tableId,  HttpServletRequest httpRequest, HttpServletResponse httpResponse, HttpSession session) {
		
		ResponseWrapper response = new ResponseWrapper(true);
		try {
			List<HiveTableStatDto> dataDtos = hiveDataService.getTableStats(tableId);
			response.setData(dataDtos);
			httpResponse.setStatus(HttpStatus.OK.value());
			response.setMessage("ok");
			response.setSuccess(true);

		} catch (Exception e) {
			e.printStackTrace();
			response.setSuccess(false);
			httpResponse.setStatus(HttpStatus.BAD_GATEWAY.value());
			response.setMessage("not ok");
		}
		return response;
	}

}

