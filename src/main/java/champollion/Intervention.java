/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package champollion;

import java.util.Date;

/**
 *
 * @author Utilisateur
 */
public class Intervention {
    
    private Date debut;
    private int duree;
    private boolean annulee;
    private int heureDebut;
    private UE ue;
    private TypeIntervention type;

    public Intervention(Date debut, int duree, boolean annulee, int heureDebut, UE ue,TypeIntervention type) {
        this.debut = debut;
        this.duree = duree;
        this.annulee = annulee;
        this.heureDebut = heureDebut;
        this.ue = ue;
        this.type=type;
    }

    public Date getDebut() {
        return debut;
    }

    public int getDuree() {
        return duree;
    }

    public boolean isAnnulee() {
        return annulee;
    }

    public int getHeureDebut() {
        return heureDebut;
    }

    public UE getUe() {
        return ue;
    }

    public TypeIntervention getIntervention() {
        return type;
    }
}
