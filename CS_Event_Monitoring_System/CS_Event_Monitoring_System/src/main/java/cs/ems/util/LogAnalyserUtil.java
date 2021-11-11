package cs.ems.util;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicLongArray;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;

import cs.ems.bean.Event;
import cs.ems.bean.Log;
import cs.ems.repository.EventRepository;

@Component
public class LogAnalyserUtil extends Thread {
	private static Logger LOGGER = LoggerFactory.getLogger(LogAnalyserUtil.class);
	private static ObjectMapper mapper = new ObjectMapper();
	private static ConcurrentHashMap<String, AtomicLongArray> map = new ConcurrentHashMap<>();
	private String eventData;
	@Autowired
	private EventRepository eventRepository;
	
	public LogAnalyserUtil(String eventData) {
		this.eventData = eventData;
	}
	
	public void run() {
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
		Log log;
		try {
			log = mapper.readValue(eventData, Log.class);
	        LOGGER.info("Worker Thread "+Thread.currentThread().getName()+" executed :: "+log.toString());
	        String id = log.getId();
	        map.computeIfAbsent(id, i -> new AtomicLongArray(2));
	        
	        if(CommonUtil.STARTED.equals(log.getState())) {
	        	map.get(id).set(0, log.getTimestamp());
	        } else if(CommonUtil.FINISHED.equals(log.getState())) {
	        	map.get(id).set(1, log.getTimestamp());
	        }
	        
	        if(0 != map.get(id).get(0) && 0 != map.get(id).get(1)) {
	        	int duration = (int)(map.get(id).get(1) - map.get(id).get(0));
	        	LOGGER.info("Time Diffeerence of log "+id+" is "+duration);
	        	
	        	//create and save the event object
	        	String type = log.getType();
	        	String host = log.getHost();
	        	boolean alert = (duration > 4);
	        	
	        	Event event = new Event(id, duration, type, host, alert);
	        	eventRepository.save(event);
	        	LOGGER.info("Event saved successfully. "+event.toString());
	        }
	        
		} catch (JsonProcessingException e) {
			LOGGER.error("ERROR: "+e.getMessage());
		}
	}
}
