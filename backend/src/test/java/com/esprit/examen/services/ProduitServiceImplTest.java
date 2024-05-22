package com.esprit.examen.services;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.esprit.examen.entities.Produit;
import com.esprit.examen.entities.Reglement;
import com.esprit.examen.repositories.ProduitRepository;

import org.junit.Test;

import antlr.collections.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class ProduitServiceImplTest {

	@Autowired
	ProduitServiceImpl produitServiceImpl;

	@Autowired
	ProduitRepository produitRepository;

	@Test
	public void testRetrieveAllProduits() {
		Produit produit1=Produit.builder().codeProduit("codeZara").libelleProduit("zara").build();
		Produit produit2=Produit.builder().codeProduit("codeCelio").libelleProduit("Celio").build();
		produitServiceImpl.addProduit(produit1);
		produitServiceImpl.addProduit(produit2);
	    java.util.List<Produit> list=	produitServiceImpl.retrieveAllProduits();
	    assertNotNull(list);
	    assertEquals("codeZara", list.get(0).getCodeProduit());
	    assertEquals("zara", list.get(0).getLibelleProduit());
	    assertEquals("codeCelio", list.get(1).getCodeProduit());
	    assertEquals("Celio", list.get(1).getLibelleProduit());
	    produitRepository.deleteAll();
	}

	@Test
	public void testAddProduit() {
		Produit p1=Produit.builder()
				.dateCreation(new java.util.Date())
				.dateDerniereModification(new java.util.Date())
				.codeProduit("codeZara")
				.libelleProduit("zara")
				.build();
		Produit p2=produitServiceImpl.addProduit(p1);
		assertEquals("codeZara", p2.getCodeProduit());
		assertEquals("zara", p2.getLibelleProduit());
	    produitRepository.delete(p2);
	}

	@Test
	public void testdeleteProduit() {
		Produit p1=Produit.builder()
				.dateCreation(new java.util.Date())
				.dateDerniereModification(new java.util.Date())
				.codeProduit("codeZara")
				.libelleProduit("zara")
				.build();
		 Produit p2=produitServiceImpl.addProduit(p1);
		 produitServiceImpl.deleteProduit(p2.getIdProduit());
		 java.util.List<Produit> list=produitServiceImpl.retrieveAllProduits();
		 assertThat(list.isEmpty());

	}
	@Test
	public void testUpdateProduit() {
		Produit p1=Produit.builder()
				.dateCreation(new java.util.Date())
				.dateDerniereModification(new java.util.Date())
				.codeProduit("codeZara")
				.libelleProduit("zara")
				.build();
		 Produit p2=produitServiceImpl.addProduit(p1);
p2.setLibelleProduit("h et m");
p2.setCodeProduit("code h et m");
Produit p3= produitServiceImpl.updateProduit(p2);
assertEquals("h et m",p3.getLibelleProduit() );
assertEquals("code h et m", p3.getCodeProduit());
produitRepository.delete(p3);


	}
	@Test
	public void testretrieveProduit() {
		Produit p1=Produit.builder()
				.dateCreation(new java.util.Date())
				.dateDerniereModification(new java.util.Date())
				.codeProduit("codeZara")
				.libelleProduit("zara")
				.build();
		 Produit p2=produitServiceImpl.addProduit(p1);
		 produitServiceImpl.retrieveProduit(p2.getIdProduit());


	}
}
