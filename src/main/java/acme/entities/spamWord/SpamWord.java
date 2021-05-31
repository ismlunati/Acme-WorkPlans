package acme.entities.spamWord;

import javax.persistence.Entity;
import javax.validation.constraints.NotBlank;

import acme.framework.entities.DomainEntity;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
	public class SpamWord extends DomainEntity {

		// Serialisation identifier -----------------------------------------------

		protected static final long	serialVersionUID	= 1L;

		// Attributes -------------------------------------------------------------	
		@NotBlank
		protected String			palabraSpam;
				
		// Derived attributes -----------------------------------------------------

		// Relationships ----------------------------------------------------------

	}