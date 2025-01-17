package dataObjects;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Transactions {

    private String type;
    private String amount;
    private String time;

    public void setDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a");
        LocalDateTime timestamp = LocalDateTime.now();
        this.time = timestamp.format(formatter);
    }

}
