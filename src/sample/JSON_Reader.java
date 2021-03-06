package sample;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.lang.reflect.Array;
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

    public String readMaster(String fileName) {
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader(fileName));
        } catch (ParseException | IOException e) {
            System.out.println(e);
            return "null";
        }

        JSONObject jso = (JSONObject) obj;

        return (String) jso.get("m_username");
    }

    public ArrayList read(String fileName) throws Exception {

        // Initialize ArrayList to hold exported JSON credentials
        credential_arrList = new ArrayList();

        // Initializes JSONParser and parses JSON data from fileName
        // into variable of type Object if no ParseException or IOException
        // is thrown. Otherwise, sets obj to null.
        Object obj;
//        try {
//            obj = new JSONParser().parse(new FileReader(fileName));
//        } catch (ParseException | IOException e) {
//            System.out.println("wsz" + e);
//            obj = null;
//        }

        obj = new JSONParser().parse(new FileReader(fileName));

        // Converts obj to type JSONObject at variable jso if obj is not null
        if (obj != null) {
            JSONObject jso = (JSONObject) obj;

            // Obtains JSONArray located at field "credentials" of JSON
            JSONArray jsonArray = ((JSONArray) jso.get("credentials"));

            // Declare 'creds' String array to hold the different fields
            // for each JSON
            String[] creds;

            // For loop to iterate through all elements in the JSONArray
            for (int i = 0; i < jsonArray.size(); i++) {

                // Initialize creds String array
                creds = new String[3];

                // Initialize jso variable to JSONObject at current index i
                jso = (JSONObject) jsonArray.get(i);

                // Gets "website" field and places it in index 0 of creds array
                creds[0] = (String) jso.get("website");

                // Sets remaining creds indexes as follows
                // creds[1] = Username
                // creds[2] = Password
                creds[1] = (String) jso.get("username");
                creds[2] = (String) jso.get("password");

                // Adds creds String arr to credential_arrList
                credential_arrList.add(creds);
            }

            // Returns the ArrayList with credentials
            return credential_arrList;
        } else {
            // Else, function returns null
            return (ArrayList) obj;
        }
    }

    public JSONArray getJSONArray(String fileName) {
        // Initialize ArrayList to hold exported JSON credentials
        credential_arrList = new ArrayList();

        // Initializes JSONParser and parses JSON data from fileName
        // into variable of type Object if no ParseException or IOException
        // is thrown. Otherwise, sets obj to null.
        Object obj;
        try {
            obj = new JSONParser().parse(new FileReader(fileName));
        } catch (ParseException | IOException e) {
            System.out.println(e);
            obj = null;
        }

        // Converts obj to type JSONObject at variable jso if obj is not null
        if (obj != null) {
            JSONObject jso = (JSONObject) obj;

            // Obtains JSONArray located at field "credentials" of JSON
            JSONArray jsonArray = ((JSONArray) jso.get("credentials"));
            return jsonArray;
        }
        return null;
    }

    public static void main(String[] args) throws Exception{
        // Creates JSON_Reader object
        JSON_Reader jr = new JSON_Reader();

        // Declares ArrayList variable of name ar and sets value to output of read() function
        // Note: parameter needs to be path of file relative to root directory. If the file is
        // in a different folder, then it needs to have that folder name appended before the
        // file name.
        //
        // Example: 'src/creds.json' if the file is in folder src, but if not, just
        // 'creds.json' if it's in the root path of the repository
        ArrayList ar = jr.read("creds.json");

        String mastr = jr.readMaster("master_creds.json");

        System.out.println(mastr);
//
//        // For loop that iterates based on the number of items in the arrayList
//        for(int i = 0;i < ar.size();i++){
//            // For loop that iterates 3 times--one for each index in the array for each ArrayList index
//            for(int o = 0;o < 3;o++) {
//
//                // Casts output of ar.get() to String[] from Object[] such that we can make the code more
//                // compact and use the [o] operator to access the array indices.
//                System.out.println(((String[]) ar.get(i))[o]);
//            }
//            // Newline
//            System.out.println();
//        }
    }
}
