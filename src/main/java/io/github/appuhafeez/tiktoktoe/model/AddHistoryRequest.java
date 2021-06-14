package io.github.appuhafeez.tiktoktoe.model;

import javax.validation.constraints.NotNull;

import lombok.Data;

@Data
public class AddHistoryRequest {

	@NotNull(message = "username cannot be null")
	private String username;
	@NotNull(message = "playedWith cannot be null")
	private String playedWith;
	private Boolean didUserWon;
	private Boolean isMatchTie;
}
