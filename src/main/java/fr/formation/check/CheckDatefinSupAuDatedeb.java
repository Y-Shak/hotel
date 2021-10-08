package fr.formation.check;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class CheckDatefinSupAuDatedeb implements Check{
    private LocalDate datedeb;
    private LocalDate datefin;

    public CheckDatefinSupAuDatedeb(LocalDate datedeb, LocalDate datefin) {
        this.datedeb = datedeb;
        this.datefin = datefin;
    }

    @Override
    public void execute() throws Exception {
        if(datefin.isBefore(datedeb)){
            throw new Exception("Invalid dates , date fin inférieur à date de debut ");
        }
    }
}
