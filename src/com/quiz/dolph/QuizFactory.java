package com.quiz.dolph;

public class QuizFactory {

	public SpecializedTopicQuiz getQuiz(String choice) {
		switch(choice) {
		case "1":
			return new ScienceQuiz("src/com/quiz/dolph/GeneralKnowledgeDB");
		case "2":
			return new ScienceQuiz("src/ScienceDB");
		case "3":
			return new ScienceQuiz();
		default:
			throw new IllegalArgumentException("Unknown command: "+ choice);
		}
	}
}
