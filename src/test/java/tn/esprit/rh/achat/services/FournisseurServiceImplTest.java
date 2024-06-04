package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {

    @Mock
    private FournisseurRepository fournisseurRepository;

    @InjectMocks
    private FournisseurServiceImpl fournisseurService;

    @Test
    public void testRetrieveFournisseur() {
  
        Fournisseur fournisseur = new Fournisseur(1L, "F001", "Fournisseur 1", CategorieFournisseur.ORDINAIRE, null, null, null);
        when(fournisseurRepository.findById(1L)).thenReturn(Optional.of(fournisseur));
        Fournisseur retrievedFournisseur = fournisseurService.retrieveFournisseur(1L);
        assertNotNull(retrievedFournisseur);
        assertEquals(fournisseur, retrievedFournisseur);
    }

    @Test
    public void testRetrieveAllFournisseurs() {
       
        List<Fournisseur> fournisseursList = new ArrayList<>();
        fournisseursList.add(new Fournisseur(1L, "F001", "Fournisseur 1", CategorieFournisseur.ORDINAIRE, null, null, null));
        fournisseursList.add(new Fournisseur(2L, "F002", "Fournisseur 2", CategorieFournisseur.CONVENTIONNE, null, null, null));
        when(fournisseurRepository.findAll()).thenReturn(fournisseursList);
        List<Fournisseur> resultat = fournisseurService.retrieveAllFournisseurs();
        assertEquals(fournisseursList, resultat);
    }

    @Test
    public void testAddFournisseur() {
        
        Fournisseur fournisseur = new Fournisseur(1L, "F001", "Fournisseur 1", CategorieFournisseur.ORDINAIRE, null, null, null);
        DetailFournisseur detailFournisseur = new DetailFournisseur();
        detailFournisseur.setDateDebutCollaboration(new Date());
        fournisseur.setDetailFournisseur(detailFournisseur);
        when(fournisseurRepository.save(fournisseur)).thenReturn(fournisseur);
        Fournisseur addedFournisseur = fournisseurService.addFournisseur(fournisseur);
        assertNotNull(addedFournisseur);
        assertNotNull(addedFournisseur.getDetailFournisseur());
        assertEquals(fournisseur.getCode(), addedFournisseur.getCode());
        assertEquals(fournisseur.getLibelle(), addedFournisseur.getLibelle());
        assertEquals(fournisseur.getCategorieFournisseur(), addedFournisseur.getCategorieFournisseur());
    }

    @Test
    public void testDeleteFournisseur() {
        
        Long fournisseurId = 1L;
        doNothing().when(fournisseurRepository).deleteById(fournisseurId);
        fournisseurService.deleteFournisseur(fournisseurId);
        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
    }
}