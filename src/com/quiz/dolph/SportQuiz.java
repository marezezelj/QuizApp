package com.quiz.dolph;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class SportQuiz implements SpecializedTopicQuiz {

	private DynamicQuiz dq;
	private int points=0;
	private ArrayList<Questions> listaPitanja;
	private Scanner scanner=new Scanner(System.in);
	
	@Override
	public void startQuiz() {
		this.dq=new DynamicQuiz();
		listaPitanja=dq.getQuestions();
		
		Random rand=new Random();
		
		while(this.listaPitanja.size()>0) {
			System.out.println("Points: " + this.points);
			Questions q = takeRandomQuestion(rand);
			System.out.println("\n"+ q);
			String ans=scanner.nextLine();
						
			if(ans.equals("0")) {
				System.out.println("Are you sure? Y/N");
				String choice=scanner.nextLine();
				if(choice.toLowerCase().equals("y")) {
					System.out.println("Game over! Total points won: " + this.points);
					break;
				} else if(choice.toLowerCase().equals("n")) {
					continue;
				}
			} else if(ans.equals(q.getAns())) {
				this.points+=10;
				removeQuestion(q);
				continue;
			} else if(ans != "1" || ans!="2" || ans !="3" || ans!="4" || ans!="0"){
				System.out.println("Unknown command. Try again!");
			}
			
			removeQuestion(q);
		}
		
	}

	private void removeQuestion(Questions q) {
		this.listaPitanja.remove(q);
		if (this.listaPitanja.isEmpty()) {
			System.out.println("You're reach end of the game! Well done!");
			System.out.println("Points: " + this.points);
		}
	}


	private Questions takeRandomQuestion(Random rand) {
		int num=rand.nextInt(this.listaPitanja.size());
		
		return this.listaPitanja.get(num);
	}

}
