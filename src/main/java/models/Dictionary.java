package models;

import java.util.HashMap;
import java.util.Map;

public class Dictionary {
    private Map<String, String> engToRus = new HashMap<>();

    public void update(String eng, String rus) {
        engToRus.put(
                prepare(eng),
                prepare(rus)
        );
    }

    private String prepare(String string){
        return string.trim().toLowerCase();
    }


    public String translateEng(String eng){
        return engToRus.getOrDefault(prepare(eng), eng);
    }

    public String translatePhrase(String phrase){
        String[] words = phrase.split("\\s+");
        StringBuilder sb = new StringBuilder();
        for (String word : words) {
            sb.append(translateEng(word)).append(" ");
        }
        return sb.toString();
    }
}
