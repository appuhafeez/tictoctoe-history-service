package io.github.appuhafeez.tiktoktoe.repo;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import io.github.appuhafeez.tiktoktoe.entity.GameHistoryEntity;

public interface HistoryRepository extends JpaRepository<GameHistoryEntity, Integer>{

	public List<GameHistoryEntity> findByUsernameOrderByDateOfGameDesc(String username);
	
	public void deleteByDateOfGameBefore(Date beforeDate);
	
}
