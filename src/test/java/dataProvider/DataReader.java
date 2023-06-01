package dataProvider;
/*
import com.fasterxml.jackson.databind.ObjectMapper;
import net.bytebuddy.jar.asm.TypeReference;
import org.apache.commons.io.FileUtils;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

public class DataReader {

    public List<HashMap<String, String>> getJsonDataToMap() throws IOException {

        // Read Json To String
        String jsonContent = FileUtils.readFileToString(new File("/Users/ilyasepitech/Desktop/JavaUIAutomationProject/src/test/java/dataProvider/PurchaseOrder.json"));

        //String to Hashmap through Jackson Databind
        ObjectMapper mapper = new ObjectMapper();

        List<HashMap <String,String>> data;
        data = mapper.readValue(jsonContent,new TypeReference<List<HashMap <String,String>>> (){});
        return data;


    }
}
*/