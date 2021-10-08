package fr.formation.check;

public class CheckEtoilesHotelValid implements Check{
    int etoiles;

    public CheckEtoilesHotelValid(int etoiles) {
        this.etoiles = etoiles;
    }

    @Override
    public void execute() throws Exception {
        if(this.etoiles <1 || this.etoiles >5){
            throw new Exception("valeur du nombre des etoiles invalid ");
        }
    }
}
