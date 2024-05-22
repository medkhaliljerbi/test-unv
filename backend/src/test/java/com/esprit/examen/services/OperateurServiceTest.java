
package com.esprit.examen.services;



import static org.assertj.core.api.Assertions.assertThat;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertSame;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Operateur;
import com.esprit.examen.repositories.OperateurRepository;


import org.junit.Test;


@RunWith(SpringRunner.class)
@SpringBootTest
public class OperateurServiceTest {
	
	@Autowired
	IOperateurService operateurService;
	
	@Autowired
	OperateurRepository operateurRepository;


	@Test
	public void testretrieveAllOperateurs() {
		Operateur operateur1=Operateur.builder().nom("Adam").prenom("Nouili").build();
		Operateur operateur2=Operateur.builder().nom("Jack").prenom("Russell").build();
		operateurService.addOperateur(operateur1);
		operateurService.addOperateur(operateur2);
	    java.util.List<Operateur> list= operateurService.retrieveAllOperateurs();
	    assertNotNull(list);
	    
	    assertEquals("Adam", list.get(0).getNom());
	    assertEquals("Nouili", list.get(0).getPrenom());
	    
	    assertEquals("Jack", list.get(1).getNom());
	    assertEquals("Russell", list.get(1).getPrenom());
	    
	    operateurRepository.deleteAll();
	}

	@Test
	public void testaddOperateur() {
		Operateur O1=Operateur.builder()
				.nom("Adam")
				.prenom("Nouili")
				.password("Hulk")
				.build();
		Operateur O2=operateurService.addOperateur(O1);
		assertEquals("Adam", O2.getNom());
		assertEquals("Nouili", O2.getPrenom());
		operateurRepository.deleteAll();	
	}
	
	@Test
	public void testdeleteOperateurt() {
		Operateur o1=Operateur.builder()
				.nom("Adam")
				.prenom("Nouili")
				.password("Hulk")
				.build();
		Operateur o2=operateurService.addOperateur(o1);
		operateurService.deleteOperateur(o2.getIdOperateur());
		 java.util.List<Operateur> list=operateurService.retrieveAllOperateurs();
		 assertThat(list.isEmpty());
		 operateurRepository.deleteAll();
	
	}

	@Test
	public void testupdateOperateur() {
		Operateur O1=Operateur.builder()
				.nom("Adam")
				.prenom("Nouili")
				.password("Hulk")
				.build();
		Operateur O2=operateurService.addOperateur(O1);
		 O2.setPrenom("Olivier");
		 O2.setNom("Gerou");
		 Operateur O3= operateurService.updateOperateur(O2);
		 assertEquals("Olivier",O3.getPrenom() );
		 assertEquals("Gerou", O3.getNom());
		 operateurRepository.deleteAll();
				
	}

	@Test
	public void testretrieveOperateur() {
		Operateur O1=Operateur.builder()
				.nom("Adam")
				.prenom("Nouili")
				.password("Hulk")
				.build();
		 Operateur O2=operateurService.addOperateur(O1);
		 operateurService.retrieveOperateur(O2.getIdOperateur());
		 assertSame(O1.getNom(),O2.getNom());
		 assertTrue(O1.getNom().length()==4);
		 operateurRepository.deleteAll();
	}
	
}





