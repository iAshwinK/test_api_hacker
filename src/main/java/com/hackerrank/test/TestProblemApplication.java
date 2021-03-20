package com.hackerrank.test;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.hackerrank.test.vo.Dataum;
import com.hackerrank.test.vo.FoodResponse;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class TestProblemApplication {

	public static void main(String args[])  {

		String city = "Houston";
		int maxCost = 20;
		TestProblemApplication main = new TestProblemApplication();
		System.out.println(main.getNumberOfOutlets(city, maxCost));
	}

	//https://jsonmock.hackerrank.com/api/food_outlets?city=Houston&page=2
	private List<String> getNumberOfOutlets(String city, int maxCost)  {
		List<String> outletNames = new ArrayList<>();
		String first_url = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city + "&page=" + 1;

		FoodResponse foodResponse = getResponse(first_url);
		outletNames.addAll(getOutletByMaxCost(foodResponse.getData(), maxCost));

		int totalPages = foodResponse.getTotal_pages();
		int currentPage = foodResponse.getPage();
		while (currentPage < totalPages) {
			currentPage++;
			String nextPageUrl = "https://jsonmock.hackerrank.com/api/food_outlets?city=" + city + "&page=" + currentPage;

			FoodResponse nextPageResponse = getResponse(nextPageUrl);
			outletNames.addAll(getOutletByMaxCost(nextPageResponse.getData(),maxCost));
		}


		return outletNames;
	}

	private List<String> getOutletByMaxCost(List<Dataum> getData, int maxCost) {
		return getData
				.stream()
				.filter(item -> (item.getEstimated_cost() <= maxCost))
				.map(Dataum::getName)
				.collect(Collectors.toList());
	}


	private FoodResponse getResponse(String url) {
		HttpClient client = HttpClient.newHttpClient();
		HttpRequest request = HttpRequest
				.newBuilder()
				.uri(URI.create(url))
				.build();

		FoodResponse foodResponse = new FoodResponse();
		try {
			HttpResponse response = client.send(request, HttpResponse.BodyHandlers.ofString());
			ObjectMapper mapper = new ObjectMapper();
			foodResponse = mapper.readValue(response.body().toString(), FoodResponse.class);
		} catch (IOException | InterruptedException e) {
			e.printStackTrace();
		}

		return foodResponse;
	}

}

