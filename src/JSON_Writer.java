// Java program for write JSON to a file

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONObject;

public class JSON_Writer
{
    public static void main(String[] args) throws FileNotFoundException
    {

        // Creates JSON Object
        JSONObject jso = new JSONObject();

        // Adds website field and value to JSON
        jso.put("website", "guguru.com");

        // Adds Linked Hash Map of size 3 to contain username, password, and password salt
        Map m = new LinkedHashMap(3);
        m.put("username", "usr");
        m.put("password", "tron82");
        m.put("salt", "bdjeklspa");

        // Adds credential values stored in m to JSON
        jso.put("credentials", m);

        // Writes data to JSON file named creds.json
        PrintWriter pw = new PrintWriter("creds.json");
        pw.write(jso.toJSONString());

        pw.flush();
        pw.close();
    }
}