import java.time.LocalDate;
import java.time.Period;

import lombok.Data;

@Data
public abstract class Person {
	protected String id;
	protected String name;
	protected LocalDate birthDate;
	protected String email;
	
	
	public Person(String id, String name, LocalDate birthDate, String email) {
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.email = email;
	}
	
	//年齢計算メソッド
	public int getAge() {
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
	
	
	//自己紹介メソッド
}
