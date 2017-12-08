package controllers;

import models.Dictionary;
import util.InputStreamOperator;
import views.Displayable;
import views.View;

import java.io.InputStream;

public class Controller_2 extends InputStreamOperator {

    private final Displayable enterPhraseView;
    private static final String ORIGINAL_PHRASE = "Original phrase: ";
    private static final String TRANSLATED_PHRASE = "Translated phrase: ";

    private Dictionary dictionary = new Dictionary() {{
        update("hello", "привет");
        update("world", "мир");
    }};

    @Override
    protected void onStarted() {
        enterPhraseView.display();
    }

    @Override
    protected void onMessage(String message) {
        new View(
                ORIGINAL_PHRASE + message,
                TRANSLATED_PHRASE + dictionary.translatePhrase(message),
                ""
        ).display();
        stop();
    }

    public Controller_2(
            InputStream input,
            Displayable enterPhraseView
    ) {
        super(input);
        this.enterPhraseView = enterPhraseView;
    }
}
