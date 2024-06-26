package travel.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import travel.model.TravelBean;
import travel.model.TravelDao;
import utility.Paging;

@Controller
public class TravelListController {
	private final String command = "/list.tv";
	private final String getPage = "travelList";
	
	@Autowired
	private TravelDao travelDao;
	
	@RequestMapping(command)
	public String doAction(@RequestParam(value = "whatColumn", required = false) String whatColumn,
													@RequestParam(value = "keyword", required = false) String keyword,
													@RequestParam(value = "pageNumber", required = false) String pageNum,
													 HttpServletRequest  request) {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("whatColumn", whatColumn);
		map.put("keyword", "%" +keyword + "%");
		
		int totalCount = travelDao.getTotalCount(map);
		String url = request.getContextPath() + this.command;
		
		Paging pageInfo = new Paging(pageNum, null, totalCount, url, whatColumn, keyword);
		
		List<TravelBean> travelLists = travelDao.getAllTravel(pageInfo, map);
		request.setAttribute("travelLists", travelLists);
		request.setAttribute("pageInfo", pageInfo);
		
		return getPage;
	}
}
