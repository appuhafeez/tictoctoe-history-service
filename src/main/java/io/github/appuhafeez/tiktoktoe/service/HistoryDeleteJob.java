package io.github.appuhafeez.tiktoktoe.service;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

import io.github.appuhafeez.tiktoktoe.repo.HistoryRepository;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HistoryDeleteJob  implements Tasklet{
	
	private HistoryRepository historyRepository;
	
	private int noOfDays;
	
	public HistoryDeleteJob(HistoryRepository historyRepository, int noOfDays) {
		this.historyRepository = historyRepository;
		this.noOfDays =  noOfDays;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		log.info("History delete job started");
		Calendar calendar = new GregorianCalendar();
		
		log.info("Todays date : {}",calendar.getTime());
		
		calendar.add(Calendar.DAY_OF_MONTH, -noOfDays);
		
		Date date = calendar.getTime();
		
		log.info("deleting recoreds before :: {}",date);
		
		historyRepository.deleteByDateOfGameBefore(date);
		
		log.info("History delete job started");
		return RepeatStatus.FINISHED;
	}

}
