package travel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import travel.model.TravelBean;
import travel.model.TravelDao;

@Controller
public class TravelInsertController {
	private final String command = "/insert.tv";
	private final String getPage = "travelInsertForm";
	private final String gotoPage = "redirect:/list.tv";
	
	@Autowired
	TravelDao travelDao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public String doAction() {
		
		return getPage;
	}
	
	@RequestMapping(value = command, method = RequestMethod.POST)
	public String doAction(@ModelAttribute("travel") @Valid TravelBean travel, BindingResult result, Model model) {
		
		if(result.hasErrors()) {
			return getPage;
		}
		
		int cnt = -1;
		cnt = travelDao.insertTravel(travel);
		
		if(cnt > 0){
			return gotoPage;
		}
		else {// »ğÀÔ ½ÇÆĞ ½Ã, »ğÀÔ ÆûÀ¸·Î ÀÌµ¿
			
			return "redirect:" + command  ; 
		}
		
	}
}
