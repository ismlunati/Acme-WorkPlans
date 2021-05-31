package acme.entities.customization;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Range;

import acme.entities.spamWord.SpamWord;
import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
	public class Customization extends DomainEntity {

		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------		
		
		@NotNull
		@Range(min = 0, max = 100)
		protected Double					tolerancia;
				
		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------
		
		@OneToMany
		protected List<SpamWord>			palabrasSpam;

	



	
		
}