package fr.formation.check;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckNamesValid implements Check {
    String word;
    public CheckNamesValid(String word) {
        this.word = word;
    }

    @Override
    public void execute() throws Exception {
        String expression = "^[a-zA-Z\\s]+";
        Pattern pattern = Pattern.compile(expression);
        Matcher matcher = pattern.matcher(this.word);
        if(!matcher.find()){
            throw new Exception("nom invalid ");
        }
    }
}
