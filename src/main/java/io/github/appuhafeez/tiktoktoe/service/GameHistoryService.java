package io.github.appuhafeez.tiktoktoe.service;

import java.util.List;

import javax.transaction.Transactional;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;

import io.github.appuhafeez.tiktoktoe.entity.GameHistoryEntity;
import io.github.appuhafeez.tiktoktoe.model.AddHistoryRequest;
import io.github.appuhafeez.tiktoktoe.repo.HistoryRepository;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class GameHistoryService {

	@Autowired
	private HistoryRepository historyRepository;

	@Transactional
	public boolean addHistory(AddHistoryRequest addHistoryRequest) {
		try {
			GameHistoryEntity gameHistoryEntityOriginal = new GameHistoryEntity();
			if(addHistoryRequest.getIsMatchTie()!=null && addHistoryRequest.getIsMatchTie()) {
				gameHistoryEntityOriginal.setIsGameTie(true);
				if(StringUtils.isNotBlank(addHistoryRequest.getPlayedWith())) {
					GameHistoryEntity gameHistoryEntity = new GameHistoryEntity();
					gameHistoryEntity.setUsername(addHistoryRequest.getPlayedWith());
					gameHistoryEntity.setGameWith(addHistoryRequest.getUsername());
					gameHistoryEntity.setIsGameTie(true);
					gameHistoryEntityOriginal.setGameWith(addHistoryRequest.getPlayedWith());
					historyRepository.saveAndFlush(gameHistoryEntity);
				}
				gameHistoryEntityOriginal.setUsername(addHistoryRequest.getUsername());
				historyRepository.saveAndFlush(gameHistoryEntityOriginal);
				return true;
			}
			if(StringUtils.isNotBlank(addHistoryRequest.getPlayedWith())) {
				GameHistoryEntity gameHistoryEntity = new GameHistoryEntity();
				gameHistoryEntity.setUsername(addHistoryRequest.getPlayedWith());
				gameHistoryEntity.setGameWith(addHistoryRequest.getUsername());
				gameHistoryEntity.setWinStatus(!addHistoryRequest.getDidUserWon());
				gameHistoryEntityOriginal.setGameWith(addHistoryRequest.getPlayedWith());
				historyRepository.saveAndFlush(gameHistoryEntity);
			}
			gameHistoryEntityOriginal.setUsername(addHistoryRequest.getUsername());
			gameHistoryEntityOriginal.setWinStatus(addHistoryRequest.getDidUserWon());
			historyRepository.saveAndFlush(gameHistoryEntityOriginal);
			return true;
		}catch (Exception e) {
			log.error("history eddition failed : {}",e);
			return false;
		}
	}


	public List<GameHistoryEntity> getGameHistoryOfUser(Authentication authentication){
		return historyRepository.findByUsernameOrderByDateOfGameDesc(authentication.getName());
	}

}
