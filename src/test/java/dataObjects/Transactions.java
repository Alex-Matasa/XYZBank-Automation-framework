package dataObjects;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Data
public class Transactions {

    private String type;
    private String amount;
    private String time;
    private LocalDateTime dateTime;


    public void setDateAndTime() {
        this.dateTime = LocalDateTime.now();
        this.time = dateTime.format(DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a"));
    }

}
