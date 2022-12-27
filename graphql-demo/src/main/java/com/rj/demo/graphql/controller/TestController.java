package com.rj.demo.graphql.controller;

import com.rj.demo.graphql.model.ApiResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@Slf4j
@RestController
@RequestMapping("/test")
public class TestController {
	@GetMapping("/book")
	public Object test() {
		return ApiResponse.success("ok!");
	}

}
