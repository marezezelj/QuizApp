package com.quiz.dolph;

import java.io.IOException;
import java.util.Scanner;

public class UserInterface {
	private Scanner scan;

	public UserInterface(Scanner scan) {
		this.scan = scan;
	}

	public void go() {
		System.out.println("Welcome to Quiz App by Dolph Games!");

		System.out.println("Main Menu: ");
		System.out.println("Select option: ");
		System.out.println("\n1.Start Game\n2.About Game\n3.Quit");

		while (true) {
			String ans = scan.nextLine();
			if (ans.equals("1")) {
				clearConsole();
				System.out.println("Choose topic: \n1.General Knowledge\n2.Science\n3.Sport(Dynamic Quiz)");
				String ans1 = scan.nextLine();
				pickupUserChoice(ans1);
			} else if (ans.equals("2")) {
				System.out.println("Made by Dolph Games.");
			} else if (ans.equals("3")) {
				break;
			} else {
				System.out.println("Unknown command! Try again.");
				continue;
			}
		}

	}

	private void pickupUserChoice(String ans1) {
		try {
			QuizFactory quizFactory = new QuizFactory();
			SpecializedTopicQuiz quiz = quizFactory.getQuiz(ans1);
			quiz.startQuiz();
		} catch (Exception e) {
			System.out.println("ERROR: " + e.getLocalizedMessage());
		}

	}

	public static void clearConsole() {
		try {
			if (System.getProperty("os.name").contains("Windows")) {
				new ProcessBuilder("cmd", "/c", "cls").inheritIO().start().waitFor();
			} else {
				System.out.print("\033\143");
			}
		} catch (IOException | InterruptedException ex) {
		}
	}
}
