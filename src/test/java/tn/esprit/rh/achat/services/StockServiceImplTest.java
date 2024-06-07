package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    @Test
    public void testRetrieveStock() {
        Stock stock = new Stock(1L, "Test Stock", 100, 10, null);
        when(stockRepository.findById(1L)).thenReturn(Optional.of(stock));

        Stock retrievedStock = stockService.retrieveStock(1L);

        assertNotNull(retrievedStock);
        assertEquals(stock, retrievedStock);
    }

    @Test
    public void testRetrieveAllStocks() {
        List<Stock> stocksList = new ArrayList<>();
        stocksList.add(new Stock(1L, "Stock 1", 100, 10, null));
        stocksList.add(new Stock(2L, "Stock 2", 200, 20, null));
        when(stockRepository.findAll()).thenReturn(stocksList);

        List<Stock> result = stockService.retrieveAllStocks();

        assertEquals(stocksList, result);
    }

    @Test
    public void testAddStock() {
        Stock stock = new Stock(1L, "New Stock", 150, 15, null);
        when(stockRepository.save(stock)).thenReturn(stock);

        Stock addedStock = stockService.addStock(stock);

        assertNotNull(addedStock);
        assertEquals(stock.getLibelleStock(), addedStock.getLibelleStock());
        assertEquals(stock.getQte(), addedStock.getQte());
        assertEquals(stock.getQteMin(), addedStock.getQteMin());
    }

    @Test
    public void testDeleteStock() {
        Long stockId = 1L;
        doNothing().when(stockRepository).deleteById(stockId);

        stockService.deleteStock(stockId);

        verify(stockRepository, times(1)).deleteById(stockId);
    }
}
