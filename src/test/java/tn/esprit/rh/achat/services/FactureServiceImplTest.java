package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Facture;
import tn.esprit.rh.achat.repositories.FactureRepository;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FactureServiceImplTest {

    @Mock
    private FactureRepository factureRepository;

    @InjectMocks
    private FactureServiceImpl factureService;

    @Test
    public void testRetrieveAllFactures() {
        Facture facture = new Facture();
        facture.setIdFacture(1L);

        when(factureRepository.findAll()).thenReturn(Collections.singletonList(facture));

        List<Facture> result = factureService.retrieveAllFactures();

        assertEquals(1, result.size());
    }

    @Test
    public void testAddFacture() {
        Facture facture = new Facture();

        when(factureRepository.save(facture)).thenReturn(facture);

        Facture result = factureService.addFacture(facture);

        assertEquals(facture, result);
    }

    @Test
    public void testCancelFacture() {
        Long factureId = 1L;
        Facture facture = new Facture();

        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        factureService.cancelFacture(factureId);

        // Assert or verify the behavior as needed
    }

    @Test
    public void testRetrieveFacture() {
        Long factureId = 1L;
        Facture facture = new Facture();

        when(factureRepository.findById(factureId)).thenReturn(Optional.of(facture));

        Facture result = factureService.retrieveFacture(factureId);

        assertEquals(facture, result);
    }

    // Add more test cases for other methods if needed
}