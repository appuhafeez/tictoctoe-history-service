package io.github.appuhafeez.tiktoktoe.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import io.github.appuhafeez.tiktoktoe.repo.HistoryRepository;
import io.github.appuhafeez.tiktoktoe.service.HistoryDeleteJob;

@Configuration
@EnableScheduling
@EnableBatchProcessing
public class CronJobConfig {

	@Autowired
	private JobLauncher jobLauncher;

	private Job job;

	@Autowired
	@Qualifier("stepBuilders")
	private StepBuilderFactory steps;

	@Autowired
	HistoryRepository historyRepository;

	@Value("${delete.records.before.days:30}")
	int noOfDaysBefore;

	@Scheduled(cron = "${delete.job.cron.expression:0 */1 * * * ?}")
	public void perform() throws Exception {
		JobParameters params = new JobParametersBuilder().addString("JobID", String.valueOf(System.currentTimeMillis()))
				.toJobParameters();
		jobLauncher.run(job, params);
	}

	@Bean
	public JobBuilderFactory jobBuilderFactory(@Autowired JobRepository jobRepository) {
		return new JobBuilderFactory(jobRepository);
	}

	@Bean
	public Job deleteOldHistoryJob(JobBuilderFactory jobBuilderFactory) {
		Job job = jobBuilderFactory.get("deleteOldHistoryJob").incrementer(new RunIdIncrementer())
				.start(executeJob(steps)).build();
		this.job = job;
		return job;
	}

	@Bean
	public Step executeJob(StepBuilderFactory steps) {
		return steps.get("executeJob").tasklet(new HistoryDeleteJob(historyRepository, noOfDaysBefore)).build();
	}

}
