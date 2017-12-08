import controllers.Controller_1;
import views.CollectionView;
import views.PromtingView;
import views.SimpleView;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public abstract class Main_1 {
    public static void main(String[] args) {
        Set<Double> setToGenerate = new HashSet<>();
        List<Double> listToGenerate = new ArrayList<>();
        new Controller_1(
                System.in,
                new PromtingView(
                        "Enter size of collection",
                        "Size should be > 0",
                        "Type desired size and press Enter..."
                ),
                new PromtingView(
                        "Enter min bound of collection",
                        "Min bound should be valid number",
                        "Type desired min bound and press Enter..."
                ),
                new PromtingView(
                        "Enter max bound of collection.",
                        "Max bound should be valid number and be greater than min bound",
                        "Type desired max bound and press Enter..."
                ),
                new SimpleView(
                        "Wrong input! Please try again."
                ),
                new CollectionView(
                        "Generated set:",
                        setToGenerate
                ),
                new CollectionView(
                        "Generated list:",
                        listToGenerate
                ),
                setToGenerate,
                listToGenerate
        ).run();
    }
}
