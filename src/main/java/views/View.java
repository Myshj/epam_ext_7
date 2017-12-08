package views;

public class View implements Displayable {

    private String header;
    private String body;
    private String footer;

    public View(
            String header,
            String body,
            String footer
    ) {
        this.header = header;
        this.body = body;
        this.footer = footer;
    }

    void displayHeader() {
        System.out.println(header);
    }

    void displayBody() {
        System.out.println(body);
    }

    void displayFooter() {
        System.out.println(footer);
    }

    @Override
    public void display() {
        displayHeader();
        displayBody();
        displayFooter();
    }
}
