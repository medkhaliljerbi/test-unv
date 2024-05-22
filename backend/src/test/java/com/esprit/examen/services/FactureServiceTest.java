package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;

import static org.mockito.Mockito.when;

import java.util.ArrayList;

import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Test;
import org.junit.jupiter.api.Assertions;
import org.junit.runner.RunWith;

import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Fournisseur;

import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.FournisseurRepository;

import org.springframework.boot.test.mock.mockito.MockBean;


@RunWith(SpringRunner.class)
@SpringBootTest
public class FactureServiceTest {
	
	@MockBean
	FactureRepository factureRepo;
	
	@Autowired
	FactureRepository factureRepository;
	
	@Autowired
	IFactureService factureService;
	
	@Autowired
    private FournisseurRepository fournisseurRepository;
	
	@Test
	public void testretrieveAllFactures(){
		List<Facture> fact1=Stream.of(Facture.builder()
				.montantRemise(20)
				.montantFacture(26000)
				.build(),Facture.builder()
				.montantRemise(30)
				.montantFacture(60000)
				.build(),Facture.builder()
				.montantRemise(25)
				.montantFacture(3000)
				.build())
				.collect(Collectors.toList());
		when(factureRepo.findAll()).thenReturn(fact1);
		
		List<Facture> fact2= factureService.retrieveAllFactures();
		assertNotNull(fact2);
		assertEquals(20, fact2.get(0).getMontantRemise());
		assertEquals(30, fact2.get(1).getMontantRemise());
		assertEquals(25, fact2.get(2).getMontantRemise());
		
		factureRepo.deleteAll();
	}
	
	@Test
	public void testaddFacture() {
		Facture facture= Facture.builder()
		                .montantRemise(15)
						.montantFacture(5000)
		                .build();
		when(factureRepository.save(facture)).thenReturn(facture);
		Facture facture2= factureService.addFacture(facture);
		assertNotNull(facture2);
		assertEquals(15, facture2.getMontantRemise());
        assertEquals(5000, facture2.getMontantFacture());		
	}
	
	
    @Test
    public void testCancelFacture() {
        Long factureId = 1L;
        Facture facture = new Facture();
        facture.setIdFacture(factureId);
 
        // mock the factureRepository findById method to return the facture
        Mockito.when(factureRepo.findById(factureId)).thenReturn(Optional.of(facture));
 
        // call the service method
        factureService.cancelFacture(factureId);
 
        // verify that the facture is archived
        assertTrue(facture.getArchivee());
 
        // verify that the factureRepository save method was called with the archived facture
        Mockito.verify(factureRepository).save(facture);
    }

    @Test
	public void testretrieveFacture() {
		Optional<Facture> facture= Optional.ofNullable(Facture.builder()
	               .idFacture((long) 3L)
	               .montantRemise(10)
	               .montantFacture(3800)
	               .build());
		when(factureRepo.findById(any())).thenReturn(facture);
		Facture facture2=factureService.retrieveFacture((long) 3L);
		assertEquals(10, facture2.getMontantRemise());
		assertEquals(3800, facture2.getMontantFacture());
	}
	

	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}
