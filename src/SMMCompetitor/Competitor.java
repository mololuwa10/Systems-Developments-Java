package SMMCompetitor;

import java.util.Arrays;
import java.util.Random;

public class Competitor{
	private String id;
	private Name name;
	private int age;
	private String country;
	private String [] scores;
	private String gender;
	private String compLevel;

//	public String getShortDetails() {
//		return null;// competitor number
//	}
//
//	public int getCompNumber() {
//		return compNumber;
//	}
//
//	public void setCompNumber(int compNumber) {
//		this.compNumber = compNumber;
//	}

	public Competitor(String id, Name Name, int Age, String Country, String[] Scores, String Gender, String CompLevel) {
		this.id = id;
		name = Name;
		age = Age;
		country = Country;
		scores = Scores;
		gender = Gender;
		compLevel = CompLevel;
	}

	public String getCompLevel() {
		return compLevel;
	}

	public void setCompLevel(String compLevel) {
		this.compLevel = compLevel;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Name getName() {
		return name;
	}

	public void setName(Name name) {
		this.name = name;
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String[] getScores() {
		return scores;
	}

	public void setScores(String[] scores) {
		this.scores = scores;
	}

	public double getOverallScore() {
		double sum = 0;
		for(double number : this.getScoreArray()) {
			sum += number;
		}
		return sum;
	}

	public double[] getScoreArray () {
		String[] numbers = this.getScores();
		double numArray [] = new double[numbers.length];

		for (int i = 0; i < numbers.length; i++) {
			numArray[i] = Double.parseDouble(numbers[i].trim());
		}

		return numArray;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getFullDetails() {
		return "Competitor Number " + this.getId() + ", Name " + this.getName().getFullName() + ", Country " + this.getCountry() + ".\n"
				+ this.getName().getFirstName() + " is a " + this.getCompLevel() + " aged " + this.getAge() + " and received these scores : " + Arrays.toString(this.getScores()) + ".\n" +
				"This gives him an overall score of " + getOverallScore();

	}

	public String getShortDetails() {
		String[] nameArray = this.getName().getFullName().split(" ");
		String initials = "";
		for(String name : nameArray) {
			initials += name.charAt(0);
		}
		return "CN " + this.getId() + " (" + initials + ") has overall score " + this.getOverallScore();
	}
}

