package cs.ems.unittest;

import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import cs.ems.CS_Event_Monitoring_System.CsEventMonitoringSystemApplication;
import cs.ems.bean.Event;
import cs.ems.repository.EventRepository;
import cs.ems.service.EventAnalyserService;
import cs.ems.util.CommonUtil;

import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes=CsEventMonitoringSystemApplication.class)
public class EMSUnitTest {
	private static Logger LOGGER = LoggerFactory.getLogger(EMSUnitTest.class);
	
	@Autowired
	private EventAnalyserService eaService;
	@Autowired
	private EventRepository eventRepository;
	
	@Before(value = "")
	public void testBefore() {
		LOGGER.debug("** Event Monitoring System Unit Testing started ***");
	}
	
	@After(value = "")
	public void testAfter() {
		LOGGER.debug("** Event Monitoring System Unit Testing stopped ***");
	}
	
	@Test
	public void testAnalyseEvents() {
		LOGGER.info("unit test on Service Layer.");
		String filePath = CommonUtil.LOG_FILE_PATH;
		eaService.analyseEvents(filePath);
	}
	
	@Test
	public void testEventSave() {
		LOGGER.info("unit test on Repository Layer.");
		Event event = new Event("myunittestid", 5, "APPLICATION_LOG", "12345", true);
		Event status = eventRepository.save(event);
		LOGGER.debug("SAVE STATUS= "+status.toString());
	}
}
