package io.github.appuhafeez.tiktoktoe.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.CreationTimestamp;

import lombok.Data;

@Data
@Entity
@Table(name = "GAME_HISTORY")
public class GameHistoryEntity {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "ID")
	private int id;
	
	@Column(name = "USERNAME")
	private String username;
	
	@Column(name = "WIN_STATUS")
	private Boolean winStatus;
	
	@Column(name = "IS_GAME_TIE")
	private Boolean isGameTie;
	
	@Column(name = "GAME_WITH")
	private String gameWith;
	
	@CreationTimestamp
	@Column(name = "DATE_OF_GAME")
	private Date dateOfGame;
	
}
