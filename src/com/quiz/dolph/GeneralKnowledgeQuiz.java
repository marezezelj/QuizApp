package com.quiz.dolph;


import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class GeneralKnowledgeQuiz implements SpecializedTopicQuiz {

	private int points=0;
	private boolean isLoad=false;
	private String fileName = "C:\\Users\\marez\\eclipse-workspace\\QuizApp\\src\\com\\quiz\\dolph\\GeneralKnowledgeDB";
	private ArrayList<Questions> listaPitanja;
	private Scanner scanner=new Scanner(System.in);
	
	
	@Override
	public void startQuiz() {
		if(!(isLoad)) {
			this.listaPitanja=new ArrayList<>();
			try(Scanner scan=new Scanner(Paths.get(fileName))){
				while(scan.hasNextLine()) {
					String[] niz=scan.nextLine().split(";");
					listaPitanja.add(new Questions(niz[0],niz[1],niz[2],niz[3], niz[4],niz[5]));
				}
			} catch(Exception e) {
				System.out.println(e.getLocalizedMessage()+ "Unable to find file!");
			}
			isLoad=true;
		}
		
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
