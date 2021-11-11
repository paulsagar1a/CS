package cs.ems.CS_Event_Monitoring_System;

import cs.ems.service.EventAnalyserService;
import cs.ems.util.CommonUtil;

public class Test {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub
		EventAnalyserService eaService = new EventAnalyserService();
		String filePath = CommonUtil.LOG_FILE_PATH;
		eaService.analyseEvents(filePath);
	}

}
