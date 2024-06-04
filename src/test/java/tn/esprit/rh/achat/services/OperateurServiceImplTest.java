package tn.esprit.rh.achat.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import tn.esprit.rh.achat.entities.Operateur;
import tn.esprit.rh.achat.repositories.OperateurRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class OperateurServiceImplTest {

    @Mock
    private OperateurRepository operateurRepository;

    @InjectMocks
    private OperateurServiceImpl operateurService;

    @Test
    public void testRetrieveOperateur() {
        Operateur operateur = new Operateur(1L, "ahmed", "fourati", "password", null);
        when(operateurRepository.findById(1L)).thenReturn(Optional.of(operateur));

        Operateur retrievedOperateur = operateurService.retrieveOperateur(1L);

        assertNotNull(retrievedOperateur);
        assertEquals(operateur, retrievedOperateur);
    }

    @Test
    public void testRetrieveAllOperateurs() {
        List<Operateur> operateursList = new ArrayList<>();
        operateursList.add(new Operateur(1L, "John", "Doe", "password", null));
        operateursList.add(new Operateur(2L, "Jane", "Smith", "password", null));
        when(operateurRepository.findAll()).thenReturn(operateursList);

        List<Operateur> resultat = operateurService.retrieveAllOperateurs();

        assertEquals(operateursList, resultat);
    }

    @Test
    public void testAddOperateur() {
        Operateur operateur = new Operateur(1L, "John", "Doe", "password", null);
        when(operateurRepository.save(operateur)).thenReturn(operateur);

        Operateur addedOperateur = operateurService.addOperateur(operateur);

        assertNotNull(addedOperateur);
        assertEquals(operateur.getNom(), addedOperateur.getNom());
        assertEquals(operateur.getPrenom(), addedOperateur.getPrenom());
        assertEquals(operateur.getPassword(), addedOperateur.getPassword());
    }

    @Test
    public void testDeleteOperateur() {
        Long operateurId = 1L;
        doNothing().when(operateurRepository).deleteById(operateurId);

        operateurService.deleteOperateur(operateurId);

        verify(operateurRepository, times(1)).deleteById(operateurId);
    }

}