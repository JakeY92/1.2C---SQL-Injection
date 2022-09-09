package edu.deakin.sit218.coachwebapp.controller;


import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import edu.deakin.sit218.coachwebapp.dao.ClientDAO;
import edu.deakin.sit218.coachwebapp.dao.ClientDAOImpl;
import edu.deakin.sit218.coachwebapp.entity.Client;

@Controller
@RequestMapping("/question-answer")
public class QaController {

	@RequestMapping("/processForm")
	public String questionanswer(
			@Valid @ModelAttribute("client") Client client, 
			BindingResult validationErrors, Model model) {
		//Input validation
		if (validationErrors.hasErrors())
			return "client-form";

		//Retrieve Client object from database
		
		//Check whether the client doesn't exist
		ClientDAO dao = new ClientDAOImpl(); 
		if (!dao.existsClient(client)) 
			dao.insertClient(client); //If not, save it
		//This client object is identical to a row in the database
		client = dao.retrieveClient(client);


		//Logic when there is no error
		//Decide on the type of workout.
		if (client.getQuestion().equals("How many legs do dogs have")) {
			model.addAttribute("message", "You have submitted the question: How many legs do dogs have?");
		} 
		else if (client.getAnswer().equals("four")) {
			model.addAttribute("message", "The answer to your question, " + client.getQuestion()+ 
					" is four");
		}
		else {
			model.addAttribute("message", "Your question: " + client.getQuestion()+ 
					" has been submitted with the following answer: "+ client.getAnswer());				
		}
	
		//Sync Client object with database
		dao.updateClient(client);
		
		//Return the View 
		return "question-answer";
	}
	
}
