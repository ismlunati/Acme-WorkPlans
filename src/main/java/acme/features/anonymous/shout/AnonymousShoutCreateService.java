/*
 * AnonymousShoutCreateService.java
 *
 * Copyright (C) 2012-2021 Rafael Corchuelo.
 *
 * In keeping with the traditional purpose of furthering education and research, it is
 * the policy of the copyright owner to permit non-commercial use and redistribution of
 * this software. It has been tested carefully, but it is not guaranteed for any particular
 * purposes. The copyright owner does not offer any warranties or representations, nor do
 * they accept any liabilities with respect to them.
 */

package acme.features.anonymous.shout;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import acme.entities.customization.Customization;
import acme.entities.shouts.Shout;
import acme.entities.spamWord.SpamWord;
import acme.framework.components.Errors;
import acme.framework.components.Model;
import acme.framework.components.Request;
import acme.framework.entities.Anonymous;
import acme.framework.services.AbstractCreateService;

@Service
public class AnonymousShoutCreateService implements AbstractCreateService<Anonymous, Shout> {

	// Internal state ---------------------------------------------------------

	@Autowired
	protected AnonymousShoutRepository repository;

	// AbstractCreateService<Administrator, Shout> interface --------------

	@Override
	public boolean authorise(final Request<Shout> request) {
		assert request != null;

		return true;
	}

	@Override
	public void bind(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;

		request.bind(entity, errors);
	}

	@Override
	public void unbind(final Request<Shout> request, final Shout entity, final Model model) {
		assert request != null;
		assert entity != null;
		assert model != null;

		request.unbind(entity, model, "author", "text", "info");
	}

	@Override
	public Shout instantiate(final Request<Shout> request) {
		assert request != null;

		Shout result;
		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);

		result = new Shout();
		result.setAuthor("John Doe");
		result.setText("Lorem ipsum!");
		result.setMoment(moment);
		result.setInfo("http://example.org");

		return result;
	}
	
	

	
	
	public static  boolean esSpam(final List<SpamWord> palabrasSpam, final String contenido, final Double tolerancia) {
		boolean boleano=false;
		int contador=0;
		int contadornegativo=0;
		
		
		String texto=contenido;
		
		final int a= contenido.split(" ").length;
		
		
		for(int i=0; i<palabrasSpam.size();i++) {				
		
		
		while (texto.indexOf(palabrasSpam.get(i).getPalabraSpam()) > -1) {
			if(palabrasSpam.get(i).getPalabraSpam().split(" ").length>1) {
				contadornegativo+=palabrasSpam.get(i).getPalabraSpam().split(" ").length-1;
			}
			texto= texto.replaceFirst(palabrasSpam.get(i).getPalabraSpam(), "");
			//texto = texto.substring(texto.indexOf(palabrasSpam.get(i).getPalabraSpam())+palabrasSpam.get(i).getPalabraSpam().length(),texto.length());
		     contador++; 
	
		}
		
		}
		final int npalabras= a-contadornegativo;
		
		final double porcentaje= (contador/(double)(npalabras))*100.;

		if(porcentaje >=tolerancia) {	
			boleano=true;
		}
		
		
		return boleano;
	}
	
	@Override
	public void validate(final Request<Shout> request, final Shout entity, final Errors errors) {
		assert request != null;
		assert entity != null;
		assert errors != null;		
	
		
		final List<Customization> repo= this.repository.findCustomization();
		
		String res;
		res= entity.getAuthor().concat(" ").concat(entity.getText());
			
		
		
		if(AnonymousShoutCreateService.esSpam(repo.get(0).getPalabrasSpam(), res, repo.get(0).getTolerancia())) {
			errors.state(request, false, "text", "anonymous.shout.create.error.label.text");
			
		}
	
	}

	@Override
	public void create(final Request<Shout> request, final Shout entity) {
		assert request != null;
		assert entity != null;

		Date moment;

		moment = new Date(System.currentTimeMillis() - 1);
		entity.setMoment(moment);
		this.repository.save(entity);
	}

}
