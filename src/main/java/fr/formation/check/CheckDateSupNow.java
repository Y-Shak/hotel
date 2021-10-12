package fr.formation.check;

import java.time.LocalDate;

public class CheckDateSupNow implements Check{
    LocalDate date ;

    public CheckDateSupNow(LocalDate date) {
        this.date = date;
    }

    @Override
    public void execute() throws Exception {
        if(date.isBefore(LocalDate.now())){
            throw new Exception("Invalid dates , date resa doit etre sup à aujourd'hui");
        }
    }
}
