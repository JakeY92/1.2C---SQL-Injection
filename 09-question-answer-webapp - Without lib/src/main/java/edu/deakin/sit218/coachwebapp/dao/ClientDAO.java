package edu.deakin.sit218.coachwebapp.dao;

import edu.deakin.sit218.coachwebapp.entity.Client;

public interface ClientDAO {
	
	public void insertClient (Client client);

	public boolean existsClient(Client client);

	public Client retrieveClient(Client client);

	public void updateClient(Client client);

}
