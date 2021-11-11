package cs.ems.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cs.ems.service.EventAnalyserService;
import cs.ems.util.CommonUtil;

@RestController
@RequestMapping("/")
public class EventController {
	@Autowired
	private EventAnalyserService eventAnalyserService;
	
	@GetMapping("/log")
	public String analyseEvents() {
		eventAnalyserService.analyseEvents(CommonUtil.LOG_FILE_PATH);
		return "Log Event Analysis is successful. Please follow the path for more details: http://localhost:8091/h2-console";
	}
}
