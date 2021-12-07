package sample;// Java program for write JSON to a file

import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.SecretKey;

public class JSON_Writer
{
    // Declares private JSONArray variable called 'json_arr
    private JSONArray json_arr;

    // Constructor
    public JSON_Writer() {
        // Initializes json_arr variable
        json_arr = new JSONArray();
    }

    public JSON_Writer(String fileName) {
        JSON_Reader jr = new JSON_Reader();
        json_arr = jr.getJSONArray("creds.json");
    }

    // addEntry in the case of not having a JSONObject made already, but info already set up
    public void addEntry(String website, String username, String password){

        // Create holding JSONObject variable
        JSONObject jso = new JSONObject();

        // Add website field and value from parameter
        jso.put("website", website);

        jso.put("username", username);
        jso.put("password", password);

        // Adds JSONObject jso to json_arr
        json_arr.add(jso);
    }
    //Initialise a master login for user
    //create an ecryption object with master password
    //store master login and password in separate JSON file
    public void initANDstoreUser(String master_username){

        JSONObject jso = new JSONObject();

        jso.put("m_username",master_username);
        //question: should we store the password given that the login file may not be encrypteed.

        // Sets fileName to {name}.json
        String fileName = "master_creds.json";

        // Sets up PrintWriter object with fileName designation from fileName variable
        try {
            PrintWriter pw = new PrintWriter(fileName);

            // Writes JSON to specified file of name in fileName
            pw.write(jso.toJSONString());

            // Flushes the PrintWriter object stream
            pw.flush();

            // Closes PrintWriter object
            pw.close();
        } catch (FileNotFoundException fo) {
            System.out.println("File not found!");
            return;
        }
    }

    // addEntry for when a JSONObject is already set up
    // **added for flexibility**
    public void addEntry(JSONObject jso) {
        json_arr.add(jso);
    }

    // remove JSONObject from json_arr
    public void remove(String website) {
        for(int i = 0;i < json_arr.size();i++) {
            if ( ( (String) ( ((JSONObject)json_arr.get(i)).get("website") )  ).equalsIgnoreCase(website)) {
                json_arr.remove(json_arr.get(i));
            }
        }
    }

    public boolean editEntry(String website, int choice, String new_value) {
        for(int i = 0;i < json_arr.size();i++){
            if ( ( (String) ( ((JSONObject)json_arr.get(i)).get("website") )  ).equalsIgnoreCase(website)){
                if (choice == 0) {
                    ((JSONObject)json_arr.get(i)).put("website", new_value);
                } else if(choice == 1) {
                    ((JSONObject)json_arr.get(i)).put("username", new_value);
                } else if (choice == 2) {
                    ((JSONObject)json_arr.get(i)).put("password", new_value);
                } else {
                    return false;
                }
                return true;
            }
        }
        return false;
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
//        // Option 1: Call addEntry() with strings in parameters (NO pre-built JSON file in parameter)
//        JSON_Writer jw = new JSON_Writer();
//
//        jw.addEntry("google.com", "admin", "password123");
//        jw.addEntry("amazon.com", "appori", "pasawado");
//
//        // Option 2: Call addEntry() with pre-built JSON file in parameter
//        // Creating JSONObject
//        JSONObject jso = new JSONObject();
//
//        // Adding field 'website' and setting value
//        jso.put("website", "safe.com");
//
//        // Creating LinkedHashMap to put login credentials under one field
//        jso.put("username", "safer");
//        jso.put("password", "safest");
//
//        // Calls addEntry() and pushes the JSON to JSON_Writer's storage
//        jw.addEntry(jso);
//
//        jw.editEntry("safe.com", 1, "best_user");
//        jw.editEntry("safe.com", 2, "best_password");
//        jw.editEntry("safe.com", 0, "unsafe.com");
//
//        jw.remove("amazon.com");
//
//        // Exports JSONArray in JSON_Writer to a file of name 'creds.json'
//        // (.json is added in export(), don't add it to the name parameter)
//        jw.export("creds");

        JSON_Writer jw = new JSON_Writer("creds.json");

        jw.addEntry("abc", "def", "ghj");
        jw.addEntry("bcd", "jng", "tot");

        jw.export("creds");
    }
}
