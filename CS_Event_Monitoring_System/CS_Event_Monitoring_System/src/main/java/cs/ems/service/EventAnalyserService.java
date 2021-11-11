package cs.ems.service;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.LineIterator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import cs.ems.util.LogAnalyserUtil;


@Service
public class EventAnalyserService {
	private static Logger LOGGER = LoggerFactory.getLogger(EventAnalyserService.class);
	private static ExecutorService pool = null;
	
	@SuppressWarnings("deprecation")
	public void analyseEvents(String path) {
		if(null == path || !path.toString().toLowerCase().endsWith(".txt")) {
			LOGGER.debug("The log file either not available or invalid. "+path);
			return;
		}
		if(null == pool) {
			pool =  Executors.newFixedThreadPool(5);
		}
		LineIterator it = null;
		File file  =  new File(path);
		try {
			it = FileUtils.lineIterator(file, "UTF-8");
		    while (it.hasNext()) {
		        String line = it.nextLine();
		        
		        Thread task = new LogAnalyserUtil(line);
		        pool.submit(task);
		    }
		    pool.shutdown();
		} catch (IOException e) {
			LOGGER.error("ERROR: "+e.getMessage());
		} finally {
		    if(null != it) LineIterator.closeQuietly(it);
		}
	}
}
