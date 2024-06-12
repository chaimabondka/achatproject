package tn.esprit.rh.achat.stock.service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Stock;
import tn.esprit.rh.achat.repositories.StockRepository;
import tn.esprit.rh.achat.services.StockServiceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class StockServiceImplMock {
    @Mock
    StockRepository stockRepository;
    @InjectMocks
    StockServiceImpl stockService;

    Stock stock = new Stock(1L, "s1", 20, 5, null);
    List<Stock> stockList = new ArrayList<Stock>(){
        {
            add(new Stock(2L, "s2", 30, 4, null));
            add(new Stock(3L, "s3", 50, 10, null));
        }
    };
    @Test
    public void testRetrieveStock() {
        Mockito.when(stockRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(stock));
        Stock s1 = stockService.retrieveStock(2L);
        Assertions.assertNotNull(s1);
    }

    @Test
    public void testAddStock() {
// Arrange
        Stock stock = Mockito.mock(Stock.class); // Create a mock Stock object

        Mockito.when(stockRepository.save(Mockito.any(Stock.class))).thenReturn(stock);

        // Act
        Stock result = stockService.addStock(stock);

        // Assert
        Assertions.assertNotNull(result);

        // Capture the argument passed to the repository's save method
        ArgumentCaptor<Stock> stockCaptor = ArgumentCaptor.forClass(Stock.class);
        Mockito.verify(stockRepository).save(stockCaptor.capture());

        // Further assertions on the captured argument can be done here if needed
        Stock capturedStock = stockCaptor.getValue();
        Assertions.assertNotNull(capturedStock);
    }

    @Test
    public void testDeleteStock() {
        // Arrange
        Long stockId = 1L;

        // Act
        stockService.deleteStock(stockId);

        // Assert
        Mockito.verify(stockRepository).deleteById(stockId);
    }

    //add delete stock test
}