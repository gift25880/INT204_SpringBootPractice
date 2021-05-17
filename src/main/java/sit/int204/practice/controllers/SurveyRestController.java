package sit.int204.practice.controllers;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sit.int204.practice.exceptions.ExceptionResponse;
import sit.int204.practice.exceptions.ExceptionResponse.ERROR_CODE;
import sit.int204.practice.exceptions.SurveyException;
import sit.int204.practice.models.Survey;

@RestController
@RequestMapping("/survey")
public class SurveyRestController {
	private static final Map<Integer, Survey> surveys = new HashMap<>();
	{
		surveys.put(1001, new Survey(1001,"Somchai",1));
		surveys.put(1002, new Survey(1002,"Somsak",2));
		surveys.put(1003, new Survey(1003,"Somrak",3));
		surveys.put(1004, new Survey(1004,"Somkiat",1));
		surveys.put(1005, new Survey(1005,"Somsri",1));
		surveys.put(1006, new Survey(1006,"Somchart",1));
		surveys.put(1007, new Survey(1007,"Somchit",1));
	}
	
	@GetMapping("")
	public List<Survey> surveyList() {
		return new ArrayList<>(surveys.values());
	}
	
	@GetMapping("/{id}")
	public Survey survey(@PathVariable int id) {
		if (surveys.get(id) == null) {
			throw new SurveyException(ExceptionResponse.ERROR_CODE.ITEM_DOES_NOT_EXIST, 
					"id: {" + id + "} does not exist !!");
		}
		return surveys.get(id);
	}
	
	@PostMapping("")
	public Survey create(@RequestBody Survey newSurvey) {
		if (surveys.get(newSurvey.getId()) != null) {
			throw new SurveyException(ERROR_CODE.ITEM_ALREADY_EXIST,
					"id: {"+ newSurvey.getId() + "} already exist !!");
		}
		surveys.put(newSurvey.getId(), newSurvey);
		return newSurvey;
	}
	
	@PutMapping("")
	public Survey update(@RequestBody Survey newSurvey) {
		surveys.put(newSurvey.getId(), newSurvey);
		return newSurvey;
	}
	
	@DeleteMapping("/{id}")
	public Survey delete(@PathVariable int id) {
		return surveys.remove(id);
	}
}
