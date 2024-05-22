package com.esprit.examen.services;


import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Fournisseur;
import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.FournisseurRepository;
import com.esprit.examen.repositories.ProduitRepository;

import org.junit.Test;

import antlr.collections.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FournisseurServiceImplTest {

	@Autowired
	FournisseurServiceImpl fournisseurServiceImpl;
	
	@Autowired
	FournisseurRepository fournisseurRepository;
	
	@Test
	public void testretrieveAllFournisseurs() {
		Fournisseur f1=Fournisseur.builder().code("abc").libelle("sami").build();
		Fournisseur f2=Fournisseur.builder().code("efg").libelle("mongi").build();
		fournisseurServiceImpl.addFournisseur(f1);
		fournisseurServiceImpl.addFournisseur(f2);
	    java.util.List<Fournisseur> list=	fournisseurServiceImpl.retrieveAllFournisseurs();
	    assertNotNull(list);
	    assertEquals("abc", list.get(0).getCode());
	    assertEquals("sami", list.get(0).getLibelle());
	    assertEquals("efg", list.get(1).getCode());
	    assertEquals("mongi", list.get(1).getLibelle());
	    fournisseurRepository.deleteAll();
	}
	
	@Test
	public void testaddFournisseur() {
		Fournisseur f1=Fournisseur.builder()
				.code("codeZara")
				.libelle("zara")
				.build();
		Fournisseur f2=fournisseurServiceImpl.addFournisseur(f1);
		assertEquals("codeZara", f2.getCode());
		assertEquals("zara", f2.getLibelle());
		fournisseurRepository.delete(f2);	
	}
	
	@Test
	public void testdeleteFournisseur() {
		Fournisseur f1=Fournisseur.builder()
				.code("codeZara")
				.libelle("zara")
				.build();
		Fournisseur f2=fournisseurServiceImpl.addFournisseur(f1);
		fournisseurServiceImpl.deleteFournisseur(f2.getIdFournisseur());
		 java.util.List<Fournisseur> list=fournisseurServiceImpl.retrieveAllFournisseurs();
		 assertThat(list.isEmpty());
	
	}
	
	
}
