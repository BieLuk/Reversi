package sample.App;

import sample.Exception.WrongArgumentException;

public class ApiResponse {
    private String time;
    public String getCurrentDateTime() throws WrongArgumentException {
        return time; }
}
