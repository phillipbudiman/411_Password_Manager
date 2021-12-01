// Java program for write JSON to a file

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

public class JSON_Writer
{
    // Declares private JSONArray variable called 'json_arr
    private JSONArray json_arr;

    // Constructor
    public JSON_Writer() {
        // Initializes json_arr variable
        json_arr = new JSONArray();
    }

    // addEntry in the case of not having a JSONObject made already, but info already set up
    public void addEntry(String website, String username, String password){

        // Create holding JSONObject variable
        JSONObject jso = new JSONObject();

        // Add website field and value from parameter
        jso.put("website", website);

        // Create a LinkedHashMap such that we can add the login credentials in one field.
        Map m = new LinkedHashMap(3);
        m.put("username", username);
        m.put("password", password);

        // Adds login credentials stored in m to JSONObject jso
        jso.put("loginfo", m);

        // Adds JSONObject jso to json_arr
        json_arr.add(jso);
    }

    // addEntry for when a JSONObject is already set up
    // **added for flexibility**
    public void addEntry(JSONObject jso) {
        json_arr.add(jso);
    }

    // remove JSONObject from json_arr
    public void remove(JSONObject jso) {
        json_arr.remove(jso);
    }

    // Exports the JSONArray in a single JSONObject under the field "credentials"
    // to a file of name specified in parameter
    public void export(String name) throws FileNotFoundException {

        // Creates JSONObject
        JSONObject jso = new JSONObject();

        // Sets fileName to {name}.json
        String fileName = name + ".json";

        // Adds JSONArray json_arr to "credentials" field in the JSONObject jso
        jso.put("credentials", json_arr);

        // Sets up PrintWriter object with fileName designation from fileName variable
        PrintWriter pw = new PrintWriter(fileName);

        // Writes JSON to specified file of name in fileName
        pw.write(jso.toJSONString());

        // Flushes the PrintWriter object stream
        pw.flush();

        // Closes PrintWriter object
        pw.close();
    }

    public static void main(String[] args) throws FileNotFoundException
    {
        // Option 1: Call addEntry() with strings in parameters (NO pre-built JSON file in parameter)
        JSON_Writer jw = new JSON_Writer();

        jw.addEntry("google.com", "admin", "password123");
        jw.addEntry("amazon.com", "appori", "pasawado");

        // Option 2: Call addEntry() with pre-built JSON file in parameter
        // Creating JSONObject
        JSONObject jso = new JSONObject();

        // Adding field 'website' and setting value
        jso.put("website", "safe.com");

        // Creating LinkedHashMap to put login credentials under one field
        Map m = new LinkedHashMap(2);
        m.put("username", "safer");
        m.put("password", "safest");

        // Adds credential values stored in m to JSONObject jso
        jso.put("loginfo", m);

        // Calls addEntry() and pushes the JSON to JSON_Writer's storage
        jw.addEntry(jso);

        // Exports JSONArray in JSON_Writer to a file of name 'creds.json'
        // (.json is added in export(), don't add it to the name parameter)
        jw.export("creds");
    }
}