package com.quiz.dolph;

import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.nio.file.Paths;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class ScienceQuiz implements SpecializedTopicQuiz {
	private int points = 0;
	private int max;
	private int limit;
	private boolean isLoad = false;
	private boolean flag=true;
	private String fileName;
	private ArrayList<Questions> listaPitanja;
	private Scanner scanner = new Scanner(System.in);

	public ScienceQuiz() {
		DynamicQuiz dq = new DynamicQuiz();
		this.listaPitanja = dq.getQuestions();
	}

	public ScienceQuiz(String filePath) {
		this.fileName = filePath;
		if (!(isLoad)) {
			this.listaPitanja = new ArrayList<>();
			try (Scanner scan = new Scanner(Paths.get(fileName))) {
				while (scan.hasNextLine()) {
					String[] niz = scan.nextLine().split(";");
					listaPitanja.add(new Questions(niz[0], niz[1], niz[2], niz[3], niz[4], niz[5]));
				}
			} catch (Exception e) {
				System.out.println(e.getLocalizedMessage() + "Unable to find file!");
			}
			isLoad = true;
		}
	}

	@Override
	public void startQuiz() {

		System.out.println("Choose difficulty: 1.Easy (2 questions) 2.Medium (5 questions) 3.Hard");
		String diff = scanner.nextLine();
		switch (diff) {
		case "1":
			limit = 2;
			break;
		case "2":
			limit = 5;
			break;
		case "3":
			limit = this.listaPitanja.size();
		}

		Random rand = new Random();
		max = limit * 10;

		while (this.listaPitanja.size() > 0 && limit > 0 && flag) {
			System.out.println("Points: " + this.points + "/" + max);
			limit--;
			Questions q = takeRandomQuestion(rand);
			System.out.println("\n" + q);
			String ans = scanner.nextLine();

			processAnswer(q, ans);

			/*if (ans.equals("0")) {
				System.out.println("Are you sure? Y/N");
				String choice = scanner.nextLine();
				if (choice.toLowerCase().equals("y")) {
					System.out.println("Game over! Total points won: " + this.points + "/" + max);
					break;
				} else if (choice.toLowerCase().equals("n")) {
					continue;
				}
			} else if (ans.equals(q.getAns())) {
				this.points += 10;
				removeQuestion(q, limit);
				continue;
			} else if (ans.equals("5")) {
				System.out.println(q.getHalf());
				ans = scanner.nextLine();
				if (ans.equals(q.getAns())) {
					this.points += 10;
					removeQuestion(q, limit);
					continue;
				}
			} else if (ans != "1" || ans != "2" || ans != "3" || ans != "4" || ans != "0") {
				System.out.println("Wrong answer!");
			}*/

			removeQuestion(q, limit);
		}

	}

	private void processAnswer(Questions q, String ans) {
		if (ans.equals("0")) {
			System.out.println("Are you sure? Y/N");
			String choice = scanner.nextLine();
			if (choice.toLowerCase().equals("y")) {
				System.out.println("Game over! Total points won: " + this.points + "/" + max);
				flag=false;
				return;
			} else if (choice.toLowerCase().equals("n")) {
				System.out.println(q);
				ans=scanner.nextLine();
				processAnswer(q,ans);
			}
		} else if (ans.equals(q.getAns())) {
			this.points += 10;
			return;
		} else if (ans.equals("5")) {
			System.out.println("\n>>50/50: ");
			System.out.println(q.getHalf());
			String ans1 = scanner.nextLine();
			processAnswer(q, ans1);
		} else {
			System.out.println("Wrong answer!");
		}
	}

	private void removeQuestion(Questions q, int lim) {
		this.listaPitanja.remove(q);
		if (this.listaPitanja.isEmpty() || lim == 0) {
			System.out.println("You're reach end of the game! Well done!");
			System.out.println("Points: " + this.points);
			try {
				FileOutputStream fileStream=new FileOutputStream("src/Leaderboard", true);
				OutputStreamWriter osw = new OutputStreamWriter(fileStream);
				String timeStamp = new SimpleDateFormat("dd.MM.yyy HH.mm.ss").format(new java.util.Date());
				String data = timeStamp + "- Points: " + this.points + "/" + this.max +";";
				
				byte[] array= data.getBytes();
				
				fileStream.write(array);
				
				fileStream.close();
			} catch (Exception e) {
				System.out.println("ERROR: " + e.getLocalizedMessage());
			}
			
			
		}
	}

	private Questions takeRandomQuestion(Random rand) {
		int num = rand.nextInt(this.listaPitanja.size());

		return this.listaPitanja.get(num);
	}
}
