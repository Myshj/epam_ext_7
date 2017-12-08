package controllers;

import models.CollectionGenerator;
import util.InputStreamOperator;
import views.Displayable;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Controller_1 extends InputStreamOperator {
    private enum  State{
        WAITING_FOR_SIZE,
        WAITING_FOR_MIN_BOUND,
        WAITING_FOR_MAX_BOUND
    }

    private final Displayable enterSizeOfCollectionView;
    private final Displayable enterMinBoundView;
    private final Displayable enterMaxBoundView;
    private final Displayable listView;
    private final Displayable setView;
    private final Displayable inputErrorView;

    private final Set<Double> setToGenerate;
    private final List<Double> listToGenerate;

    private State state = State.WAITING_FOR_SIZE;

    private int size;
    private double minBound;
    private double maxBound;

    public Controller_1(
            InputStream input,
            Displayable enterSizeOfCollectionView,
            Displayable enterMinBoundView,
            Displayable enterMaxBoundView,
            Displayable inputErrorView,
            Displayable setView,
            Displayable listView,
            Set<Double> setToGenerate,
            List<Double> listToGenerate
    ) {
        super(input);
        this.enterSizeOfCollectionView = enterSizeOfCollectionView;
        this.enterMinBoundView = enterMinBoundView;
        this.enterMaxBoundView = enterMaxBoundView;
        this.listView = listView;
        this.setView = setView;
        this.inputErrorView = inputErrorView;
        this.setToGenerate = setToGenerate;
        this.listToGenerate = listToGenerate;
    }

    @Override
    protected void onStarted() {
        enterSizeOfCollectionView.display();
    }

    @Override
    protected void onMessage(String message) {
        switch (state){
            case WAITING_FOR_SIZE:
                onSizeEntered(message);
                break;
            case WAITING_FOR_MIN_BOUND:
                onMinBoundEntered(message);
                break;
            case WAITING_FOR_MAX_BOUND:
                onMaxBoundEntered(message);
                break;
        }
    }

    private void onMaxBoundEntered(String message) {
        try{
            maxBound = Double.parseDouble(message);
            if (maxBound <= minBound){
                throw new NumberFormatException();
            }
            CollectionGenerator generator = new CollectionGenerator(minBound, maxBound, size);
            generator.fill(listToGenerate);
            generator.fill(setToGenerate);
            listView.display();
            setView.display();
            stop();
        } catch (NumberFormatException ex){
            displayInputErrorView();
        }
    }

    private void onMinBoundEntered(String message) {
        try {
            minBound = Double.parseDouble(message);
            state = State.WAITING_FOR_MAX_BOUND;
            enterMaxBoundView.display();
        } catch (NumberFormatException ex){
            displayInputErrorView();
        }
    }

    private void onSizeEntered(String message) {
        try {
            size = Integer.parseInt(message);
            if (size <= 0){
                throw new NumberFormatException();
            }
            state = State.WAITING_FOR_MIN_BOUND;
            enterMinBoundView.display();
        } catch (NumberFormatException ex){
            displayInputErrorView();
        }
    }

    private void displayInputErrorView(){
        inputErrorView.display();
    }
}
