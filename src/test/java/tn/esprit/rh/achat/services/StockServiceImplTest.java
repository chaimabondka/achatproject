package tn.esprit.rh.achat.services;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplTest {

    @Mock
    private StockRepository stockRepository;

    @InjectMocks
    private StockServiceImpl stockService;

    private Stock stock;

    @BeforeEach
    public void setUp() {
        stock = new Stock();
        stock.setIdStock(1L);
        stock.setLibelleStock("Test Stock");
        stock.setQte(100);
        stock.setQteMin(10);
    }

    @Test
    public void testSaveStock() {
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        Stock savedStock = stockService.saveStock(stock);

        assertNotNull(savedStock);
        assertEquals("Test Stock", savedStock.getLibelleStock());
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    public void testUpdateStock() {
        when(stockRepository.save(any(Stock.class))).thenReturn(stock);

        Stock updatedStock = stockService.updateStock(stock);

        assertNotNull(updatedStock);
        assertEquals("Test Stock", updatedStock.getLibelleStock());
        verify(stockRepository, times(1)).save(stock);
    }

    @Test
    public void testDeleteStock() {
        doNothing().when(stockRepository).deleteById(anyLong());

        stockService.deleteStock(1L);

        verify(stockRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testGetStockById() {
        when(stockRepository.findById(anyLong())).thenReturn(Optional.of(stock));

        Stock foundStock = stockService.getStockById(1L);

        assertNotNull(foundStock);
        assertEquals("Test Stock", foundStock.getLibelleStock());
        verify(stockRepository, times(1)).findById(1L);
    }

    @Test
    public void testGetAllStocks() {
        List<Stock> stocks = Arrays.asList(stock);
        when(stockRepository.findAll()).thenReturn(stocks);

        List<Stock> foundStocks = stockService.getAllStocks();

        assertNotNull(foundStocks);
        assertEquals(1, foundStocks.size());
        verify(stockRepository, times(1)).findAll();
    }
}
