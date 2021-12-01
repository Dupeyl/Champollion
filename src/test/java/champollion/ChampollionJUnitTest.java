package champollion;

import java.util.ArrayList;
import java.util.Date;
import org.junit.jupiter.api.*;
import static org.junit.jupiter.api.Assertions.*;

public class ChampollionJUnitTest {

    Enseignant untel;
    UE uml, java;

    @BeforeEach
    public void setUp() {
        untel = new Enseignant("untel", "untel@gmail.com");
        uml = new UE("UML");
        java = new UE("Programmation en java");
    }

    @Test
    public void testNouvelEnseignantSansService() {
        assertEquals(0, untel.heuresPrevues(),
                "Un nouvel enseignant doit avoir 0 heures prévues");
    }

    @Test
    public void testAjouteHeures() {
        // 10h TD pour UML
        untel.ajouteEnseignement(uml, 0, 10, 0);

        assertEquals(10, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 10 heures prévues pour l'UE 'uml'");

        // 20h TD pour UML
        untel.ajouteEnseignement(uml, 0, 20, 0);

        assertEquals(10 + 20, untel.heuresPrevuesPourUE(uml),
                "L'enseignant doit maintenant avoir 30 heures prévues pour l'UE 'uml'");
    }

    @Test
    public void testHeuresPrevues() {
        untel.ajouteEnseignement(uml, 10, 20, 30);
        assertEquals(60,untel.heuresPrevues());
    }

    @Test
    public void testEnSousService() {
        assertTrue(untel.enSousService());
        untel.ajouteEnseignement(uml, 0, 300, 0);
        assertFalse(untel.enSousService());
    }

    @Test
    public void testAjouteIntervention(Intervention inter) {
        untel.getLesInterventions().add(inter);
        assertTrue(untel.getLesInterventions().contains(inter));
    }

    @Test
    public void testResteAPlanifier() {
        boolean annulee = false;
        untel.ajouteEnseignement(uml, 10, 20, 30);
        assertEquals(10, untel.resteAPlanifier(uml, TypeIntervention.CM));
        assertEquals(20, untel.resteAPlanifier(uml, TypeIntervention.TD));
        assertEquals(30, untel.resteAPlanifier(uml, TypeIntervention.TP));
        Intervention inter= new Intervention(new Date(), 2, annulee, 8, uml, TypeIntervention.TD);
        untel.ajouteIntervention(inter);
        assertEquals(28, untel.resteAPlanifier(uml, TypeIntervention.TD));
    }
}
