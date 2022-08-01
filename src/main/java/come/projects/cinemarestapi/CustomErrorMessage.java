package come.projects.cinemarestapi;

public class CustomErrorMessage {
    private final String error;

    public CustomErrorMessage(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
