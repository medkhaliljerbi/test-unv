package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.SecteurActivite;
import com.esprit.examen.repositories.SecteurActiviteRepository;
import org.springframework.boot.test.mock.mockito.MockBean;


@RunWith(SpringRunner.class)
@SpringBootTest
public class SecteurActiviteServiceImplTest {
	
	@MockBean
    SecteurActiviteRepository activiteRepository;
	
	@Autowired
	SecteurActiviteRepository activiteRepository2;
	
	@Autowired
	SecteurActiviteServiceImpl activiteServiceImpl;
	
	@Test
	public void testRetrieveAllSecteurActivite(){
		List<SecteurActivite> activites=Stream.of(SecteurActivite.builder()
				                                                 .libelleSecteurActivite("Finance")
				                                                 .codeSecteurActivite("code")
				                                                 .build(),
				                                  SecteurActivite.builder()
				                                                 .libelleSecteurActivite("Informatique")
				                                                 .codeSecteurActivite("code")
				                                                 .build(),
				                                  SecteurActivite.builder()
	                                                 .libelleSecteurActivite("Economie")
	                                                 .codeSecteurActivite("code")
	                                                 .build())
				                                  .collect(Collectors.toList());
		when(activiteRepository.findAll()).thenReturn(activites);
		List<SecteurActivite> activites2= activiteServiceImpl.retrieveAllSecteurActivite();
		assertNotNull(activites2);
		assertEquals("Finance", activites2.get(0).getLibelleSecteurActivite());
		assertEquals("Informatique", activites2.get(1).getLibelleSecteurActivite());
		assertEquals("Economie", activites2.get(2).getLibelleSecteurActivite());
	}
	
	@Test
	public void testAddSecteurActivite() {
		SecteurActivite activite= SecteurActivite.builder()
		               .libelleSecteurActivite("Informatique")
		               .codeSecteurActivite("code")
		               .build();
		when(activiteRepository.save(activite)).thenReturn(activite);
		SecteurActivite activite2= activiteServiceImpl.addSecteurActivite(activite);
		assertNotNull(activite2);
		assertEquals("Informatique", activite2.getLibelleSecteurActivite());
        assertEquals("code", activite2.getCodeSecteurActivite());		
	}
	

	@Test
	public void testUpdateSecteurActivite() {
		SecteurActivite activite= SecteurActivite.builder()
		               .idSecteurActivite(1l)
		               .libelleSecteurActivite("informatique")
		               .codeSecteurActivite("code")
		               .build();
		activiteRepository2.save(activite);
		activite.setLibelleSecteurActivite("economie");
		SecteurActivite activite2= activiteServiceImpl.updateSecteurActivite(activite);
		assertEquals("economie", activite2.getLibelleSecteurActivite());
	}
	
	@Test
	public void testRetrieveSecteurActivite() {
		Optional<SecteurActivite> activite= Optional.ofNullable(SecteurActivite.builder()
	               .idSecteurActivite(1l)
	               .libelleSecteurActivite("informatique")
	               .codeSecteurActivite("code")
	               .build());
		when(activiteRepository.findById(any())).thenReturn(activite);
		SecteurActivite activite2=activiteServiceImpl.retrieveSecteurActivite(1L);
		assertEquals("informatique", activite2.getLibelleSecteurActivite());
		assertEquals("code", activite2.getCodeSecteurActivite());
	}
}
