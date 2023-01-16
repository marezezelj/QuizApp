package com.quiz.dolph;

import java.util.Scanner;

public class DemoQuizApp {

	public static void main(String[] args) {
		
		Scanner scan=new Scanner(System.in);
		UserInterface UI=new UserInterface(scan);
		
		UI.go();
	}

}
