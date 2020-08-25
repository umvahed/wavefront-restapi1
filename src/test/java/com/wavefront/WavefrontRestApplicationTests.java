package com.wavefront;

import com.wavefront.dto.ShapeDTO;
import com.wavefront.exception.ShapeException;
import org.h2.util.json.JSONObject;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.junit.rules.ExpectedException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.*;
import org.springframework.util.Assert;

import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import java.awt.*;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
class WavefrontRestApplicationTests {

	@Rule
	public ExpectedException thrown = ExpectedException.none();

	@Autowired
	private TestRestTemplate restTemplate;



	@Test
	public void whenShapesDoNotOverlapThenItShouldReturn200() {
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map= new LinkedMultiValueMap<>();
		map.add("type", "square");
		map.add("descriptor", "10,10,20,10,20,20,10,20");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
		ResponseEntity<String> response = restTemplate.exchange("/api/shapes", HttpMethod.POST, request, String.class);
		Assert.isTrue(response.getStatusCode().value()==200);
	}


	@Test
	public void whenShapesTypeIsNullThenItShouldReturn400(){
			HttpHeaders headers = new HttpHeaders();
			headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
			MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
			map.add("descriptor", "10,10,20,10,20,20,10,20");
			HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);
			ResponseEntity<String> response = restTemplate.exchange("/api/shapes", HttpMethod.POST, request, String.class);
		    Assert.isTrue(response.getStatusCode().value()==400);
	}

	@Test
	public void whenTheSameShapesUsedToCreateNewThenItShouldReturn400() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("descriptor", "10,10,20,10,20,20,10,20");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/shapes", HttpMethod.POST, request, String.class);

		// call again
		response = restTemplate.exchange("/api/shapes", HttpMethod.POST, request, String.class);
		Assert.isTrue(response.getStatusCode().value()==400);

	}

	@Test
	public void whenShapesOverlapThenItShouldReturnTrue() {
		Point l1 = new Point(0,10);
		Point r1 = new Point(10,0);

		Point l2 = new Point(5,5);
		Point r2 = new Point(15,0);

		Assert.isTrue(Util.overlap(l1, r1, l2, r2));
	}


	@Test
	public void whenOverlappingCoordinatesUsedToCreateNewThenItShouldReturn400() {

		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("descriptor", "0,10,20,10,10,0,10,20");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/shapes", HttpMethod.POST, request, String.class);

		// call again
		MultiValueMap<String, String> map1 = new LinkedMultiValueMap<>();
		map1.add("descriptor", "5,5,20,10,15,0,10,20");
		response = restTemplate.exchange("/api/shapes", HttpMethod.POST, request, String.class);
		Assert.isTrue(response.getStatusCode().value()==400);

	}
	
	@Test
	public void usingIncorrectDesciptor() {
		
		HttpHeaders headers = new HttpHeaders();
		headers.setContentType(MediaType.APPLICATION_FORM_URLENCODED);
		MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
		map.add("descriptor", "0,10,20,10,10,0,10");
		HttpEntity<MultiValueMap<String, String>> request = new HttpEntity<>(map, headers);

		ResponseEntity<String> response = restTemplate.exchange("/api/shapes", HttpMethod.POST, request, String.class);

		// call again
		MultiValueMap<String, String> map1 = new LinkedMultiValueMap<>();
		map1.add("descriptor", "5,5,20,10,15,0,10");
		response = restTemplate.exchange("/api/shapes", HttpMethod.POST, request, String.class);
		Assert.isTrue(response.getStatusCode().value()==400);


}

}