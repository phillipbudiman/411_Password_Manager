import java.io.FileReader;
import java.sql.SQLOutput;
import java.util.Iterator;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSON_Reader {
    public static void main(String[] args) throws Exception{
        Object obj = new JSONParser().parse(new FileReader("creds.json"));

        JSONObject jso = (JSONObject) obj;

        String website = (String) jso.get("website");
        System.out.println(website);

        Map creds = ((Map)jso.get("credentials"));

        Iterator<Map.Entry> itr1 = creds.entrySet().iterator();
        while (itr1.hasNext()) {
            Map.Entry pair = itr1.next();
            System.out.println(pair.getKey() + " : " + pair.getValue());
        }
    }
}
