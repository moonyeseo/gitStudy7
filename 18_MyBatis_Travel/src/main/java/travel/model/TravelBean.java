package travel.model;

import javax.validation.constraints.Min;

import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

public class TravelBean {
	private int num;
	
	@NotEmpty(message = "이름은 필수 입력 사항입니다.")
	private String name;
	
	@Range(min = 10, max = 100, message = "10 ~ 100살로 작성해야 합니다.")
	private int age;
	
	@NotEmpty(message = "관심지역은 1개 이상 선택해야 합니다.")
	private String area;
	
	@NotEmpty(message = "원하는 여행 타입을 선택해 주세요.")
	private String style;
	
	@NotEmpty(message = "원하는 가격대를 선택하세요.")
	private String price;
	
	public TravelBean() {
		
	}
	
	public TravelBean(int num, String name, int age, String area, String style, String price) {
		super();
		this.num = num;
		this.name = name;
		this.age = age;
		this.area = area;
		this.style = style;
		this.price = price;
	}
	
	public int getNum() {
		return num;
	}
	public void setNum(int num) {
		this.num = num;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	public String getArea() {
		return area;
	}
	public void setArea(String area) {
		this.area = area;
	}
	public String getStyle() {
		return style;
	}
	public void setStyle(String style) {
		this.style = style;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
}
