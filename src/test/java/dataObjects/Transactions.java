package dataObjects;

import lombok.Data;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Data
public class Transactions {

    private String type;
    private String amount;
    private String time;
    private List<String> depositHistory;
    private List<String> withdrawHistory;

    public void setDateAndTime() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MMM d, yyyy h:mm:ss a");
        LocalDateTime timestamp = LocalDateTime.now();
        this.time = timestamp.format(formatter);
    }

    @Override
    public String toString() {
        return "Transactions{" +
                "type='" + type + '\'' +
                ", amount='" + amount + '\'' +
                ", time='" + time + '\'' +
                '}';
    }
}
