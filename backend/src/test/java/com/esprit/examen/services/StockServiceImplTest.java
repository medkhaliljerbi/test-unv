package com.esprit.examen.services;

import static org.junit.Assert.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import com.esprit.examen.entities.Stock;

import lombok.extern.slf4j.Slf4j;

@RunWith(SpringRunner.class)
@SpringBootTest
@Slf4j
public class StockServiceImplTest {
	
	/*@MockBean
	StockRepository stockRepository;*/
	
	
	
	@Autowired
	IStockService stockService;
	
	
	@Test
	public void testAddStock() {
	//	List<Stock> stocks = stockService.retrieveAllStocks();
	//	int expected=stocks.size();
		Stock s = Stock.builder().libelleStock("stock test")
				                 .qte(10)
				                 .qteMin(100).build();
		Stock savedStock= stockService.addStock(s);
	//	assertEquals(expected+1, stockService.retrieveAllStocks().size());
		assertNotNull(savedStock.getLibelleStock());
		stockService.deleteStock(savedStock.getIdStock());
		
	} 
	
	@Test
	public void testAddStockOptimized() {
		Stock s = Stock.builder().libelleStock("stock test")
                .qte(10)
                .qteMin(100).build();
		Stock savedStock= stockService.addStock(s);
		assertNotNull(savedStock.getIdStock());
		assertSame(10, savedStock.getQte());
		assertTrue(savedStock.getQteMin()>0);
		stockService.deleteStock(savedStock.getIdStock());
		
	} 
	
	@Test
	public void testDeleteStock() {
		Stock s = Stock.builder().libelleStock("stock test")
                .qte(30)
                .qteMin(60).build();
		Stock savedStock= stockService.addStock(s);
		stockService.deleteStock(savedStock.getIdStock());
		assertNull(stockService.retrieveStock(savedStock.getIdStock()));
	}
	@Test
	public void testretrieveAllStocks(){
		/*List<Stock> stocks= Stream.of(new Stock("stock test 1",30,60),new Stock("stock test 2",40,70),new Stock("stock test 3",50,80))
				                                            .collect(Collectors.toList());
	    */
		Stock s = Stock.builder().libelleStock("stock test")
                .qte(30)
                .qteMin(60).build();
		Stock savedStock= stockService.addStock(s);
		List<Stock> stockss=stockService.retrieveAllStocks();
		assertNotNull(stockss);
		assertEquals("stock test", stockss.get(0).getLibelleStock());
		stockService.deleteStock(savedStock.getIdStock());
	    }
	@Test
	public void testupdateStock() {
		Stock stockOrigin = Stock.builder().libelleStock("stock test origin")
                .qte(30)
                .qteMin(60).build();
		Stock stock=stockService.addStock(stockOrigin);
		Stock stockUpdated = Stock.builder().idStock(stock.getIdStock())
				                            .libelleStock("stock test updated")
                                            .qte(30)
                                            .qteMin(60).build();
		assertEquals("stock test origin",stock.getLibelleStock());
		Stock updatedStock=stockService.updateStock(stockUpdated);
        assertEquals("stock test updated",updatedStock.getLibelleStock());
        stockService.deleteStock(updatedStock.getIdStock());
	}
	
	@Test
	public void testretrieveStock() {
		Stock s = Stock.builder().libelleStock("stock test")
                .qte(30)
                .qteMin(60).build();
		Stock stock=stockService.addStock(s);
		Stock stockRetreived=stockService.retrieveStock(stock.getIdStock());
		assertEquals("stock test",stockRetreived.getLibelleStock());
		assertEquals(30, stockRetreived.getQte());
		assertEquals(60,stockRetreived.getQteMin());
        stockService.deleteStock(stockRetreived.getIdStock());
	}
	
	@Test
	public void testretrieveStatusStock() {
		
	}
}
