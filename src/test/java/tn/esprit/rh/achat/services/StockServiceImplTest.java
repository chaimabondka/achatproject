package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
        stock = new Stock(1L, "Test Stock", 100, 10);
    }

    @Test
    public void testRetrieveAllStocks() {
        List<Stock> stocks = Arrays.asList(stock);
        when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> result = stockService.retrieveAllStocks();
        assertNotNull(result);
        assertEquals(1, result.size());
        assertEquals(stock.getLibelleStock(), result.get(0).getLibelleStock());
    }

    @Test
    public void testAddStock() {
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);
        Stock createdStock = stockService.addStock(stock);
        assertNotNull(createdStock);
        assertEquals(stock.getLibelleStock(), createdStock.getLibelleStock());
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    public void testDeleteStock() {
        doNothing().when(stockRepository).deleteById(anyLong());
        stockService.deleteStock(1L);
        verify(stockRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateStock() {
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);
        Stock updatedStock = stockService.updateStock(stock);
        assertNotNull(updatedStock);
        assertEquals(stock.getLibelleStock(), updatedStock.getLibelleStock());
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    public void testRetrieveStock() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));
        Stock foundStock = stockService.retrieveStock(1L);
        assertNotNull(foundStock);
        assertEquals(stock.getLibelleStock(), foundStock.getLibelleStock());
        verify(stockRepository, times(1)).findById(1L);
    }

    @Test
    public void testRetrieveStatusStock() {
        List<Stock> stocksEnRouge = Arrays.asList(stock);
        when(stockRepository.retrieveStatusStock()).thenReturn(stocksEnRouge);

        String status = stockService.retrieveStatusStock();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
        String now = sdf.format(new Date());
        String expectedMessage = System.lineSeparator() + now + System.lineSeparator()
                + ": le stock Test Stock a une quantité de 100 inférieur à la quantité minimale a ne pas dépasser de 10"
                + System.lineSeparator();

        assertTrue(status.contains(expectedMessage));
    }
}
