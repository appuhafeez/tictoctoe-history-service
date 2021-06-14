package io.github.appuhafeez.tiktoktoe.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import io.github.appuhafeez.tiktoktoe.entity.GameHistoryEntity;
import io.github.appuhafeez.tiktoktoe.model.AddHistoryRequest;
import io.github.appuhafeez.tiktoktoe.service.GameHistoryService;

//@CrossOrigin(origins = "${allowed.origins}",allowedHeaders = "*")
@RestController
@RequestMapping("/history")
public class HistoryController {

	@Autowired
	private GameHistoryService gameHistoryService;
	
	@PostMapping("/add")
	public boolean addTransactionHistory(@RequestBody AddHistoryRequest addHistoryRequest) {
		return gameHistoryService.addHistory(addHistoryRequest);
	}
	
	@PostMapping("/get")
	public List<GameHistoryEntity> getUserHistory(@AuthenticationPrincipal Authentication authentication) {
		return gameHistoryService.getGameHistoryOfUser(authentication);
	}
	
}
