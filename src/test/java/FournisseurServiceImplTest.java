
package tn.esprit.rh.achat.services;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.junit.jupiter.api.extension.ExtendWith;
import org.junit.Test;
import org.junit.jupiter.api.BeforeEach;

import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import tn.esprit.rh.achat.entities.CategorieFournisseur;
import tn.esprit.rh.achat.entities.DetailFournisseur;
import tn.esprit.rh.achat.entities.Fournisseur;
import tn.esprit.rh.achat.repositories.DetailFournisseurRepository;
import tn.esprit.rh.achat.repositories.FournisseurRepository;

@ExtendWith(MockitoExtension.class)
public class FournisseurServiceImplTest {
	

	  @Mock
	    private FournisseurRepository fournisseurRepository;
	    private DetailFournisseurRepository detailFournisseurRepository;

	  @InjectMocks
	    private FournisseurServiceImpl fournisseurService;
	  
	  @BeforeEach
	    public void setUp() {
	        MockitoAnnotations.openMocks(this);
	    }
	  
	  @Test
	    public void testRetrieveFournisseur() {
	        Fournisseur fournisseur = new Fournisseur(1L, "F001", "Fournisseur 1", CategorieFournisseur.ORDINAIRE, null, null, null);
	        when(fournisseurRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(fournisseur));
	        
	        Fournisseur retrievedFournisseur = fournisseurService.retrieveFournisseur(2L);
	        
	        assertNotNull(retrievedFournisseur);
	        assertEquals(fournisseur.getIdFournisseur(), retrievedFournisseur.getIdFournisseur());
	        assertEquals(fournisseur.getCode(), retrievedFournisseur.getCode());
	        assertEquals(fournisseur.getLibelle(), retrievedFournisseur.getLibelle());
	        assertEquals(fournisseur.getCategorieFournisseur(), retrievedFournisseur.getCategorieFournisseur());
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

	        when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur);

	        Fournisseur addedFournisseur = fournisseurService.addFournisseur(fournisseur);

	        assertNotNull(addedFournisseur);
	        assertNotNull(addedFournisseur.getDetailFournisseur());
	        assertEquals(fournisseur.getCode(), addedFournisseur.getCode());
	        assertEquals(fournisseur.getLibelle(), addedFournisseur.getLibelle());
	        assertEquals(fournisseur.getCategorieFournisseur(), addedFournisseur.getCategorieFournisseur());
	    }

	    @Test
	    
	    public void testSaveDetailFournisseur() {
	        Fournisseur fournisseur = new Fournisseur(1L, "F001", "Fournisseur 1", CategorieFournisseur.ORDINAIRE, null, null, null);
	        DetailFournisseur detailFournisseur = new DetailFournisseur();
	        fournisseur.setDetailFournisseur(detailFournisseur);

	        when(detailFournisseurRepository.save(Mockito.any(DetailFournisseur.class))).thenReturn(detailFournisseur);

	        DetailFournisseur savedDetailFournisseur = fournisseurService.saveDetailFournisseur(fournisseur);

	        assertNotNull(savedDetailFournisseur);
	        assertEquals(detailFournisseur, savedDetailFournisseur);
	    }
	    
	    @Test
	    
	    public void testUpdateFournisseur() {
	        Fournisseur fournisseur = new Fournisseur(1L, "F001", "Fournisseur 1", CategorieFournisseur.ORDINAIRE, null, null, null);
	        DetailFournisseur detailFournisseur = new DetailFournisseur();
	        fournisseur.setDetailFournisseur(detailFournisseur);

	        when(detailFournisseurRepository.save(Mockito.any(DetailFournisseur.class))).thenReturn(detailFournisseur);
	        when(fournisseurRepository.save(Mockito.any(Fournisseur.class))).thenReturn(fournisseur);

	        Fournisseur updatedFournisseur = fournisseurService.updateFournisseur(fournisseur);

	        assertNotNull(updatedFournisseur);
	        assertEquals(fournisseur.getDetailFournisseur(), updatedFournisseur.getDetailFournisseur());
	    }

	    @Test
	    
	    public void testDeleteFournisseur() {
	        Long fournisseurId = 1L;
	        
	        doNothing().when(fournisseurRepository).deleteById(fournisseurId);
	        
	        fournisseurService.deleteFournisseur(fournisseurId);
	        
	        verify(fournisseurRepository, times(1)).deleteById(fournisseurId);
	    }
	
}
