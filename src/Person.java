import java.time.LocalDate;
import java.time.Period;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
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
		log.info("Personを作成しました: {}", name);
	}
	
	public Person() {
		// TODO 自動生成されたコンストラクター・スタブ
	}

	//年齢計算メソッド
	public int getAge() {
		return Period.between(birthDate, LocalDate.now()).getYears();
	}
	
	
	//自己紹介メソッド
	public abstract String introduce();
	
	
	//詳細情報表示
	public final void showDetails() {
		log.info("{}の詳細を表示します", name);
		
		System.out.println("=== 詳細情報 ===");
        System.out.println("ID: " + id);
        System.out.println("名前: " + name);
        System.out.println("年齢: " + getAge() + "歳");
        System.out.println("Email: " + email);
        System.out.println(introduce());
	}
}
