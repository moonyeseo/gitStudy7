package travel.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import travel.model.TravelDao;

@Controller
public class TravelDeleteController {
	private final String command = "delete.tv";
	private final String gotoPage = "redirect:/list.tv";
	
	@Autowired
	private TravelDao travelDao;
	
	@RequestMapping(command)
	public String doAction(@RequestParam("num") int num, @RequestParam("pageNumber") int pageNumber) {
		travelDao.deleteTravel(num);
		
		return gotoPage + "?pageNumber=" + pageNumber; // list.tv를 요청할 때 pageNumber를 파라미터로 넘김 -> 삭제 처리 후 현재 페이지로 다시 넘어오기 위해서 
	}
}
