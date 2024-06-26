package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import travel.model.TravelDao;

@Controller
public class TravelCheckController {
	private final String command = "/name_check_proc.tv";
	
	@Autowired
	private TravelDao travelDao;
	
	@RequestMapping(command)
	@ResponseBody
	public String doAction(@RequestParam("checkname") String checkname) {
		
		int cnt = -1;
		cnt = travelDao.searchName(checkname);
		
		if(cnt > 0) {
			return "false";
		}
		else {
			return "true";
		}
	}
}
