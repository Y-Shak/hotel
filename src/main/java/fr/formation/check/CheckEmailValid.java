package fr.formation.check;

import fr.formation.entities.ClientEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckEmailValid implements Check{

    String word ;

    public CheckEmailValid(String word) {
        this.word = word;
    }

    public static final Pattern VALID_EMAIL_ADDRESS_REGEX =
            Pattern.compile("^[A-Z0-9._%+-]+@[A-Z0-9.-]+\\.[A-Z]{2,6}$", Pattern.CASE_INSENSITIVE);
    @Override
    public void execute() throws Exception {
        // validate email format
        Matcher matcher = VALID_EMAIL_ADDRESS_REGEX.matcher(this.word);
        if(!matcher.find()){
            throw new Exception("Email invalid, hi from check class");
        }
    }
}
