package com.quiz.dolph;

import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.Scanner;

import com.google.gson.Gson;

public class DynamicQuiz {
	
	ArrayList<Questions> listaPitanja=new ArrayList<>();
	
	public ArrayList<Questions> getQuestions(){

		try {
			URL url = new URL("https://opentdb.com/api.php?amount=10&category=21&difficulty=medium&type=multiple");

			HttpURLConnection conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int responseCode = conn.getResponseCode();

			if (responseCode != 200) {
				throw new RuntimeException("HttpResponseCode: " + responseCode);
			} else {
				StringBuilder sb = new StringBuilder();
				Scanner scan = new Scanner(url.openStream());

				while (scan.hasNext()) {
					sb.append(scan.nextLine());
				}

				scan.close();

				String json = sb.toString();
				
				Gson gson = new Gson();
				Map map = gson.fromJson(json, Map.class);

				List<Map> lista = (List<Map>) map.get("results");
				

				while (lista.size() > 0) {
					Random rand = new Random();
					int num = rand.nextInt(lista.size());
					String question =(String) lista.get(num).get("question");
					String tacanOdgovor = (String) lista.get(num).get("correct_answer");
					ArrayList<String> ponudjeniOdgovori = (ArrayList<String>) lista.get(num).get("incorrect_answers");

					ponudjeniOdgovori.add(tacanOdgovor);
					Collections.shuffle(ponudjeniOdgovori);
					
					String index = String.valueOf(ponudjeniOdgovori.indexOf(tacanOdgovor) + 1);
					
					listaPitanja.add(new Questions(question, ponudjeniOdgovori.get(0), ponudjeniOdgovori.get(1), ponudjeniOdgovori.get(2), ponudjeniOdgovori.get(3), index ));
					lista.remove(num);
				}
				
				
			}
			

		} catch (Exception e) {
			System.out.println(e.getLocalizedMessage());
		}
		
		return listaPitanja;
		
	}
}
