package travel.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.RowBounds;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.stereotype.Component;

import utility.Paging;

@Component("myTravelDao")
public class TravelDao {
	private String namespace = "travel.TravelBean";
	
	@Autowired
	SqlSessionTemplate sqlSessionTemplate;
	
// select
	public List<TravelBean> getAllTravel(Paging pageInfo, Map<String, String> map){
		RowBounds rowBounds = new RowBounds(pageInfo.getOffset(), pageInfo.getLimit());
		
		List<TravelBean> lists = new ArrayList<TravelBean>();
		lists = sqlSessionTemplate.selectList(namespace + ".getAllTravel", map, rowBounds);
		
		return lists;
	}
	
// insert
	public int  insertTravel(TravelBean travel) {
		int cnt = -1;
		
		try {
			cnt = sqlSessionTemplate.insert(namespace + ".insertTravel", travel);
		}catch(DataAccessException e) {
			System.out.println("error : " + e.getMessage());
		}
		
		return cnt;
	}
	
// delete
	public void deleteTravel(int num) {
		sqlSessionTemplate.delete(namespace + ".deleteTravel", num);
	}
	
// select by num
	public TravelBean getTravel(int num) {
		TravelBean travel = new TravelBean();
		travel = sqlSessionTemplate.selectOne(namespace + ".getTravel", num);
		
		return travel;
	}
	
// update
	public int updateTravel(TravelBean travel) {
		int cnt = -1;
		
		try {
			cnt = sqlSessionTemplate.update(namespace + ".updateTravel", travel);
		}catch(DataAccessException e) { // 형식에 맞지 않는 데이터 입력 시, 예외처리
			System.out.println("에러 발생");
			System.out.println(e.getMessage());
			System.out.println("cnt : " + cnt);
		}
		return cnt;
	}
	
// select count
	public int getTotalCount(Map<String, String> map) {
		int totalCount = -1;
		
		totalCount = sqlSessionTemplate.selectOne(namespace + ".getTotalCount", map); 
		
		return totalCount;
	}
	
// search name
	public int searchName(String name) {
		int cnt = -1;
		
		cnt = sqlSessionTemplate.selectOne(namespace + ".searchName", name);
		
		return cnt;
	}
}