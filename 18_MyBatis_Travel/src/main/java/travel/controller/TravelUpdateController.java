package travel.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import travel.model.TravelBean;
import travel.model.TravelDao;

@Controller
public class TravelUpdateController {
	private final String command = "/update.tv";
	private final String getPage = "travelUpdateForm";
	private final String gotoPage = "redirect:/list.tv";
	
	@Autowired
	private TravelDao travelDao;
	
	@RequestMapping(value = command, method = RequestMethod.GET)
	public ModelAndView doAction(@RequestParam("num") int num , @RequestParam("pageNumber") int pageNumber) {
		
		TravelBean travel = travelDao.getTravel(num);
		
		ModelAndView mav = new ModelAndView();
		mav.addObject("travel", travel);
		mav.addObject("pageNumber", pageNumber);
		mav.setViewName(getPage);
		
		return mav;
	}

	@RequestMapping(value = command, method = RequestMethod.POST)
	public String doAction(@ModelAttribute("travel") @Valid TravelBean travel, BindingResult result , @RequestParam("pageNumber") int pageNumber, Model model) {
		
		if(result.hasErrors()) {
			model.addAttribute("pageNumber", pageNumber); // ���� �����ؼ�  ���� form���� �Ѿ �� ���� ������ ��ȣ ���� �ٽ� �Ѱ��ֱ�
			return getPage ; // �Ķ���ͷ� pageNumber�� �ѱ� �� ���� -> getPage�� +�� �����ϸ� �� �ڿ� .jsp�� �ٱ� ������ error!
		}
		
		int cnt = -1;
		cnt = travelDao.updateTravel(travel);
		
		if(cnt > 0){
			return gotoPage + "?pageNumber=" + pageNumber;
		}
		else {// ���� ���� ��, ���� ������ �̵�
			model.addAttribute("num", travel.getNum());
			model.addAttribute("pageNumber", pageNumber);
			
			return "redirect:" + command  ; 
		}
	}
	
}
