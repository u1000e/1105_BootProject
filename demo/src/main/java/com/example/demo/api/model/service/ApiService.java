package com.example.demo.api.model.service;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.example.demo.api.model.dao.CommentMapper;
import com.example.demo.api.model.dto.Comment;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
public class ApiService {

	private final CommentMapper mapper;

	public String requestBusan(int pageNo) {
		final String SERVICE_KEY = "?serviceKey=uo3R%2Fu2NMjMnbqvHCys6TD6gatTNgcS7ydTHET4vVi7Uk7YlRkiEiYpGjDzoutC7gWXHy6wuX72EtOakSZeBBA%3D%3D";
		StringBuilder sb = new StringBuilder();
		sb.append("https://apis.data.go.kr/6260000/FoodService/getFoodKr");
		sb.append(SERVICE_KEY);
		sb.append("&pageNo=" + pageNo);
		sb.append("&numOfRows=6");
		sb.append("&resultType=json");

		URI uri = null;
		try {
			uri = new URI(sb.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String apiResponse = new RestTemplate().getForObject(uri, String.class);
		return apiResponse;
	}

	public String requestBusanDetail(int num) {
		final String SERVICE_KEY = "?serviceKey=uo3R%2Fu2NMjMnbqvHCys6TD6gatTNgcS7ydTHET4vVi7Uk7YlRkiEiYpGjDzoutC7gWXHy6wuX72EtOakSZeBBA%3D%3D";
		StringBuilder sb = new StringBuilder();
		sb.append("https://apis.data.go.kr/6260000/FoodService/getFoodKr");
		sb.append(SERVICE_KEY);
		sb.append("&pageNo=1");
		sb.append("&numOfRows=1");
		sb.append("&resultType=json");
		sb.append("&UC_SEQ=" + num);

		URI uri = null;
		try {
			uri = new URI(sb.toString());
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		String apiResponse = new RestTemplate().getForObject(uri, String.class);
		return apiResponse;
	}

	public void saveComment(Comment comment) {
		mapper.saveComment(comment);
	}

	public List<Comment> selectAll(Long seq) {
		return mapper.selectComment(seq);
	}

}
