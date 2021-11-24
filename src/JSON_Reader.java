import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSON_Reader {

    // ArrayList variable to hold the credentials
    private ArrayList credential_arrList;

    // Constructor
    public JSON_Reader() {
    }

    public ArrayList read(String fileName) throws Exception {

        // Initialize ArrayList to hold exported JSON credentials
        credential_arrList = new ArrayList();

        // Initializes JSONParser and parses JSON data from fileName
        // into variable of type Object
        Object obj = new JSONParser().parse(new FileReader(fileName));

        // Converts obj to type JSONObject at variable jso
        JSONObject jso = (JSONObject) obj;

        // Obtains JSONArray located at field "credentials" of JSON
        JSONArray jsonArray = ((JSONArray) jso.get("credentials"));

        // Declare 'creds' String array to hold the different fields
        // for each JSON
        String[] creds;

        // For loop to iterate through all elements in the JSONArray
        for(int i = 0;i < jsonArray.size(); i++){

            // Initialize creds String array
            creds = new String[4];

            // Initialize jso variable to JSONObject at current index i
            jso = (JSONObject) jsonArray.get(i);

            // Gets "website" field and places it in index 0 of creds array
            creds[0] = (String) jso.get("website");

            // Create a map variable to be able to access the fields within
            // "credentials"
            Map credmap = ((Map)jso.get("loginfo"));

            // Sets remaining creds indexes as follows
            // creds[1] = Username
            // creds[2] = Password
            // creds[3] = Salt
            creds[1] = (String) credmap.get("username");
            creds[2] = (String) credmap.get("password");
            creds[3] = (String) credmap.get("salt");

            // Adds creds String arr to credential_arrList
            credential_arrList.add(creds);
        }

        // Returns the ArrayList with credentials
        return credential_arrList;
    }

    public static void main(String[] args) throws Exception{
        JSON_Reader jr = new JSON_Reader();

        ArrayList ar = jr.read("creds.json");

        for(int i = 0;i < ar.size();i++){
            for(int o = 0;o < 4;o++) {
                System.out.println(((String[]) ar.get(i))[o]);
            }
            System.out.println();
        }
    }
}
