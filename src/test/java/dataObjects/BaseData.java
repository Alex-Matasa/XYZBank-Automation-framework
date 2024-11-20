package dataObjects;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public class BaseData {

    protected void fromJsonToObject(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.readerForUpdating(this).readValue(new File(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    protected void fromObjectToJson(String filePath) {
        ObjectMapper objectMapper = new ObjectMapper();

        try {
            objectMapper.writeValue(new File(filePath), this);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
