package com.esprit.examen.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.sql.Date;
import java.time.LocalDate;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Facture;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FactureRepository;
import com.esprit.examen.repositories.ReglementRepository;


@RunWith(SpringRunner.class)
@SpringBootTest
public class ReglementServiceImplTest {
	

	
	@Autowired
	ReglementServiceImpl reglementServiceImpl;
	
	@Autowired
	ReglementRepository reglementRepository;
	
	@Autowired 
	FactureServiceImpl factureServiceImpl;
	
	@Autowired
	FactureRepository factureRepository;
	
	@Test
	public void testRetrieveAllReglements() {
		Reglement r1=Reglement.builder()
				.dateReglement(new java.util.Date())
				.montantPaye(10)
				.montantRestant(2)
				.build();
		Reglement r2=Reglement.builder()
				.dateReglement(new java.util.Date())
				.montantPaye(20)
				.montantRestant(10)
				.build();
		Reglement reglement1=reglementServiceImpl.addReglement(r1);
		Reglement reglement2=reglementServiceImpl.addReglement(r2);
		List<Reglement> listReglement=reglementServiceImpl.retrieveAllReglements();
		assertNotNull(listReglement);
		assertEquals(10, listReglement.get(0).getMontantPaye());
		assertEquals(2, listReglement.get(0).getMontantRestant());
		assertEquals(20, listReglement.get(1).getMontantPaye());
		assertEquals(10, listReglement.get(1).getMontantRestant());
        reglementRepository.delete(reglement2);	
        reglementRepository.delete(reglement1);	
	}
	
	@Test
	public void testAddReglement() {
		Reglement r1=Reglement.builder()
				.dateReglement(new java.util.Date())
				.montantPaye(20)
				.montantRestant(10)
				.build();
		Reglement reglement1=reglementServiceImpl.addReglement(r1);
		assertEquals(20, reglement1.getMontantPaye());
		assertEquals(10, reglement1.getMontantRestant());
        reglementRepository.delete(reglement1);	

		
	}
	@Test
	public void testRetrieveReglement() {
		Reglement r1=Reglement.builder()
				.dateReglement(new java.util.Date())
				.montantPaye(20)
				.montantRestant(10)
				.build();
		Reglement reglement1=reglementServiceImpl.addReglement(r1);
		Reglement reglement=reglementServiceImpl.retrieveReglement(reglement1.getIdReglement());
		assertNotNull(reglement);
		assertEquals(20, reglement.getMontantPaye());
		assertEquals(10, reglement.getMontantRestant());
		reglementRepository.delete(reglement);
	}
	@Test
	public void testRetrieveReglementByFacture() {
		Facture facture=Facture.builder()
	 			.archivee(true)
	 			.dateCreationFacture(new java.util.Date())
	 			.dateDerniereModificationFacture(new java.util.Date())
				.build();
		factureServiceImpl.addFacture(facture);
		Reglement r1=Reglement.builder()
				.dateReglement(new java.util.Date())
				.montantPaye(40)
				.montantRestant(20)
				.facture(facture)
				.build();
		Reglement r2=Reglement.builder()
				.dateReglement(new java.util.Date())
				.montantPaye(20)
				.montantRestant(10)
				.facture(facture)
				.build();
		Reglement reglement1=reglementServiceImpl.addReglement(r1);
		Reglement reglement2=reglementServiceImpl.addReglement(r2);
		List<Reglement> list= reglementServiceImpl.retrieveReglementByFacture(reglement1.getFacture().getIdFacture());
		assertNotNull(list);
		assertEquals(40, list.get(0).getMontantPaye());
		assertEquals(20, list.get(0).getMontantRestant());
		assertEquals(20, list.get(1).getMontantPaye());
		assertEquals(10, list.get(1).getMontantRestant());
		reglementRepository.deleteAll();;
		factureRepository.deleteAll();
	}
	
	

}
