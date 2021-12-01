package champollion;

import java.util.ArrayList;

public class Enseignant extends Personne {
    
    private final ArrayList<ServicePrevu> lesEnseignements = new ArrayList<ServicePrevu>();
    private final ArrayList<Intervention> lesInterventions = new ArrayList<Intervention>();

    public Enseignant(String nom, String email) {
        super(nom, email);
    }

    public ArrayList<ServicePrevu> getLesEnseignements(){
        return lesEnseignements;
    }
    
    public ArrayList<Intervention> getLesInterventions(){
        return lesInterventions;
    }
    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant en "heures équivalent TD" Pour le calcul : 1 heure
     * de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure de TP vaut 0,75h
     * "équivalent TD"
     *
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevues() {
        double heures=0;
        for(ServicePrevu s : lesEnseignements){
            heures = heures +(s.getVolumeCM()*1.5+ s.getVolumeTD()+s.getVolumeTP()*0.75);
        }
        int arrondis = (int) Math.round(heures);
        return arrondis;
    }
    
    //Retourne true si l'enseignant effectue moins de 192h
    public boolean enSousService(){
        boolean res=false;
        int heures=heuresPrevues();
        if(heures<192){
            res=true;
        }
        return res;
    }

    /**
     * Calcule le nombre total d'heures prévues pour cet enseignant dans l'UE spécifiée en "heures équivalent TD" Pour
     * le calcul : 1 heure de cours magistral vaut 1,5 h "équivalent TD" 1 heure de TD vaut 1h "équivalent TD" 1 heure
     * de TP vaut 0,75h "équivalent TD"
     *
     * @param ue l'UE concernée
     * @return le nombre total d'heures "équivalent TD" prévues pour cet enseignant, arrondi à l'entier le plus proche
     *
     */
    public int heuresPrevuesPourUE(UE ue) {
        ServicePrevu s=null;
        for(ServicePrevu sp: lesEnseignements){
            if(sp.getUe()==ue){
                s=sp;
            }
        }
        double heures=0;
        heures = heures + (s.getVolumeCM()*1.5+ s.getVolumeTD()+ s.getVolumeTP()*0.75);
        int arrondis = (int) Math.round(heures);
        return arrondis;
    }

    /**
     * Ajoute un enseignement au service prévu pour cet enseignant
     *
     * @param ue l'UE concernée
     * @param volumeCM le volume d'heures de cours magitral
     * @param volumeTD le volume d'heures de TD
     * @param volumeTP le volume d'heures de TP
     */
    public void ajouteEnseignement(UE ue, int volumeCM, int volumeTD, int volumeTP) {
        ServicePrevu s=null;
        boolean ueExistant=false;
        for(ServicePrevu sp: lesEnseignements){
                if(sp.getUe()==ue){
                    s=sp;
                    s.setVolumeCM(volumeCM + s.getVolumeCM());
                    s.setVolumeTD(volumeTD + s.getVolumeTD());
                    s.setVolumeTP(volumeTP+ s.getVolumeTP());
                    ueExistant=true;
                }
        }
        if(ueExistant==false){
            s=new ServicePrevu(volumeCM, volumeTD, volumeTP,ue);
            lesEnseignements.add(s);
        }
    }
    
    public void ajouteIntervention(Intervention inter){
        lesInterventions.add(inter);
    }
    
    public int resteAPlanifier (UE ue, TypeIntervention type){
        int restePlanif=0;
        int heuresPlanif=0;
        int heures=0;
        for(Intervention i: lesInterventions){
            if(i.getIntervention()==type && i.getUe()==ue){
                    heures= heures + (i.getDuree());
            }
        }
        for(ServicePrevu s: lesEnseignements){
            if(s.getUe()==ue){
                if(type==TypeIntervention.CM)
                    heuresPlanif= heuresPlanif +(s.getVolumeCM());
                else if(type==TypeIntervention.TD)
                    heuresPlanif= heuresPlanif +(s.getVolumeTD());
                else if(type==TypeIntervention.TP)
                    heuresPlanif= heuresPlanif +(s.getVolumeTP());
            }
        }
        restePlanif= heures-heuresPlanif;
        return restePlanif;
    }
}
