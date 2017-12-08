package views;

import java.util.Collection;

public class CollectionView extends View {
    private final Collection collection;

    public CollectionView(String header, Collection collection) {
        super(header, "", "");
        this.collection = collection;
    }

    @Override
    void displayBody() {
        System.out.println(collection);
    }
}
