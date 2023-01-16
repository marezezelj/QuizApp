package com.quiz.dolph;

import java.util.Random;

public class Questions {

	private String question;
	private String first;
	private String second;
	private String third;
	private String four;
	private String ans;

	public Questions(String question, String first, String second, String third, String four, String ans) {
		this.question = question;
		this.first = first;
		this.second = second;
		this.third = third;
		this.four = four;
		this.ans = ans;

	}

	public String toString() {
		return this.question + "\n1." + this.first + "\n2." + this.second + "\n3." + this.third + "\n4." + this.four;
	}

	public String getAns() {
		return this.ans;
	}

	public String getAnsAsText() {
		switch (this.ans) {
		case "1":
			return this.first;
		case "2":
			return this.second;
		case "3":
			return this.third;
		case "4":
			return this.four;
		}

		return null;
	}

	public String getHalf() {
		Random rand = new Random();
		int num = rand.nextInt(3);

		switch (num) {
		case 0:
			if (!(this.ans.equals("1"))) {
				return this.question + "\n1." + this.first + "\n" + this.ans + "." + this.getAnsAsText();
			} else {
				return this.question + "\n2." + this.second + "\n" + this.ans + "." + this.getAnsAsText();
			}

		case 1:
			if (!(this.ans.equals("3"))) {
				return this.question + "\n" + this.ans + "." + this.getAnsAsText() + "\n3." + this.third;
			} else {
				return this.question + "\n" + this.ans + "." + this.getAnsAsText() + "\n4." + this.four;
			}
		case 2:
			if (!(this.ans.equals("2"))) {
				return this.question + "\n" + this.ans + "." + this.getAnsAsText() + "\n2." + this.second;
			} else {
				return this.question + "\n3." + this.third + "\n" + this.ans + "." + this.getAnsAsText();
			}

		}

		return null;
	}

}
