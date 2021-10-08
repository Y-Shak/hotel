package fr.formation.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckAdresseValid implements Check{
    String word;

    public CheckAdresseValid(String word) {
        this.word = word;
    }
//    12, avenue Valdimir komarov
//
//      4 a rue lamartine
//
//      60 rue de lyon
//
//      4 Rue George Enesco
//
//      52 av. Victor Hugo
//
//    Place Blaise Pascale
    public static final Pattern VALID_ADDRESS_REGEX =
            Pattern.compile("[0-9]{0,3}(?:(?:[,. ]){1}[-a-zA-Zàâäéèêëïîôöùûüç]+)*", Pattern.CASE_INSENSITIVE);
    @Override
    public void execute() throws Exception {
        Matcher matcher = VALID_ADDRESS_REGEX.matcher(this.word);
        if(!matcher.find()){
            throw new Exception("Adresse invalid, hi from check class");
        }
    }
}
