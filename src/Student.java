import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Data
@Slf4j
public abstract class Student extends Person
	implements Evaluable, Reportable{
	protected String major;
	protected int enrollmentYear;
	protected List<Grade> grades;
	protected double gpa;
	
	
	public Student (String id, String name, LocalDate birthDate, String email,
					String major, int enrollmentYear) {
		super(id, name, birthDate, email);
		
		this.major = major;
		this.enrollmentYear = enrollmentYear;
		this.grades = new ArrayList<>();
		this.gpa = 0.0;
		
		log.info("Studentを作成しました: {}", name);
	}
	
	
	//成績追加
	public void addGrade(Grade grade) throws InvalidGradeException{
		if(grade == null) {
			throw new InvalidGradeException("Gradeはnullには出来ません");
		}
		if(grade.getScore() < 0 || grade.getScore() > 100) {
			throw new InvalidGradeException("点数は0-100の間で入力してください: grade.getScore()");
		}
		
		grades.add(grade);
		recalculateGPA();
		log.info("{}の成績を追加しました: {}({}点)", name, grade.getSubject(),grade.getScore());
		
	}
	
	
	//GPA再計算
	private void recalculateGPA() {
		if(grades.isEmpty()) {
			this.gpa = 0.0;
			return;
		}
		
		double total = grades.stream()
							 .mapToDouble(Grade::getGPAPoint)
							 .sum();
		this.gpa = total / grades.size();
		
		
		log.debug("{}のGPAが再計算されました: {}",name, gpa);
		
	}
	
	
	//学生タイプ取得
	public abstract String getStudentType();
	
	
	//必用単位数取得
	public abstract int getRequiredCredits();
	
	
	//卒業要件チェック
	public abstract boolean canGraduate();
	
	
	
	@Override
	public double calculateGPA() {
		return this.gpa;
	}
	
	
	@Override
	public String getGradeLevel() {
		if(gpa >= 3.8) return "最優秀";
		if(gpa >= 3.5) return "優秀";
		if(gpa >= 3.0) return "良好";
		if(gpa >= 2.5) return "普通";
		return "要努力";
	}
	
	
	@Override
	public boolean isEligibleForHonors() {
		return gpa >= 3.8 && grades.size() >= 8;
	}
	
	
	@Override
	public String generateSummary() {
		return String.format("%s（%s）- GPA: %.2f, 成績レベル: %s", 
                name, getStudentType(), gpa, getGradeLevel());
	}
	
	
	public String getDetailedInfo() {
	    StringBuilder info = new StringBuilder();
	    info.append("=== 学生詳細情報 ===");
	    info.append("学生ID: ").append(getId()).append(" ");
	    info.append("氏名: ").append(getName()).append(" ");
	    info.append("年齢: ").append(getAge()).append("歳");
	    info.append("メールアドレス: ").append(getEmail()).append(" ");
	    info.append("専攻: ").append(getMajor()).append(" ");
	    info.append("====================");
	    return info.toString();
	}
	
	
	public String getAgeCategory() {
	    if (getAge() < 20) {
	        return "10代";
	    } else if (getAge() < 25) {
	        return "20代前半";
	    } else if (getAge() < 30) {
	        return "20代後半";
	    } else {
	        return "30代以上";
	    }
	}
}
