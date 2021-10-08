package fr.formation.check;

import fr.formation.entities.ClientEntity;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckTelephoneValid implements Check{

    String word;

    public CheckTelephoneValid(String word) {
        this.word = word;
    }

    @Override
    public void execute() throws Exception {

        // validate phone format
        // this pattern allow :
        // - 1- Ten-Digit Number "2055550125"
        // - 2- Number With Whitespaces, Dots or Hyphens "202 555 0125"
        // - 3 -Number With Parentheses "(202) 555-0125"
        // - 4 - Number With International Prefix "+111 (202) 555-0125"
        String patterns
                = "^\\d{10}$"
                + "|^(\\d{3}[- .]?){2}\\d{4}$"
                + "|^((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
                + "|^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$";
//                = "^(\\+\\d{1,3}( )?)?((\\(\\d{3}\\))|\\d{3})[- .]?\\d{3}[- .]?\\d{4}$"
//                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?){2}\\d{3}$"
//                + "|^(\\+\\d{1,3}( )?)?(\\d{3}[ ]?)(\\d{2}[ ]?){2}\\d{2}$";
        Pattern pattern = Pattern.compile(patterns);
        Matcher matcherPhone = pattern.matcher(this.word);
        if(!matcherPhone.find()){
            throw new Exception("Invalid value pour telephone");
        }
    }
}
