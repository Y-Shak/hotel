package fr.formation.check;

import java.time.LocalDateTime;

public class CheckDatefinSupAuDatedeb implements Check{
    private LocalDateTime datedeb;
    private LocalDateTime datefin;

    public CheckDatefinSupAuDatedeb(LocalDateTime datedeb, LocalDateTime datefin) {
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
