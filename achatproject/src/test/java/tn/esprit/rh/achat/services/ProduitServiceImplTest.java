package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.rh.achat.entities.Produit;
import tn.esprit.rh.achat.repositories.ProduitRepository;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.*;

public class ProduitServiceImplTest {

    @Mock
    private ProduitRepository produitRepository;

    @InjectMocks
    private ProduitServiceImpl produitService;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testRetrieveProduit() {
        // Arrange
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setLibelleProduit("Test Product");
        when(produitRepository.findById(1L)).thenReturn(Optional.of(produit));

        // Act
        Produit foundProduit = produitService.retrieveProduit(1L);

        // Assert
        assertThat(foundProduit.getLibelleProduit()).isEqualTo("Test Product");
        verify(produitRepository, times(1)).findById(1L);
    }

    @Test
    public void testAddProduit() {
        // Arrange
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setLibelleProduit("Test Product");
        when(produitRepository.save(produit)).thenReturn(produit);

        // Act
        Produit savedProduit = produitService.addProduit(produit);

        // Assert
        assertThat(savedProduit.getLibelleProduit()).isEqualTo("Test Product");
        verify(produitRepository, times(1)).save(produit);
    }

    @Test
    public void testDeleteProduit() {
        // Act
        produitService.deleteProduit(1L);

        // Assert
        verify(produitRepository, times(1)).deleteById(1L);
    }

    @Test
    public void testUpdateProduit() {
        // Arrange
        Produit produit = new Produit();
        produit.setIdProduit(1L);
        produit.setLibelleProduit("Updated Product");
        when(produitRepository.save(produit)).thenReturn(produit);

        // Act
        Produit updatedProduit = produitService.updateProduit(produit);

        // Assert
        assertThat(updatedProduit.getLibelleProduit()).isEqualTo("Updated Product");
        verify(produitRepository, times(1)).save(produit);
    }
}
