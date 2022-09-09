package edu.deakin.sit218.coachwebapp.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Entity
@Table(name = "client") // just to be explicit
public class Client {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "qaid")
	protected int id;

	@NotNull(message = "is required")
	@Size(min = 5, max=50, message = "is required")

	@Column(name = "question")
	protected String question;

	@NotNull(message = "is required")
	@Size(min = 4, message = "is required")
	@Column(name = "answer")
	protected String answer;

	public Client() {

	}

	public Client(String question, String answer) {
		setQuestion(question);
		setAnswer(answer);
	}

	public String getQuestion() {
		return question;
	}

	public void setQuestion(String question) {
		this.question = question;
	}

	public String getAnswer() {
		return answer;
	}

	public void setAnswer(String answer) {
		this.answer = answer;
	}

	@Override
	public String toString() {
		return "qa = [question: " + getQuestion() + ", answer: " + getAnswer() + "]";
	}

}
