import java.io.FileReader;
import java.util.ArrayList;
import java.util.Map;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.*;

public class JSON_Reader {

    private ArrayList credential_arrList;

    public JSON_Reader() {
    }

    public ArrayList read(String fileName) throws Exception {
        credential_arrList = new ArrayList();
        
        Object obj = new JSONParser().parse(new FileReader(fileName));

        JSONObject jso = (JSONObject) obj;

        JSONArray jsonArray = ((JSONArray) jso.get("user_credentials"));

        String[] creds;

        for(int i = 0;i < jsonArray.size(); i++){
            creds = new String[4];
            jso = (JSONObject) jsonArray.get(i);

            creds[0] = (String) jso.get("website");

            Map credmap = ((Map)jso.get("credentials"));

            creds[1] = (String) credmap.get("username");
            creds[2] = (String) credmap.get("password");
            creds[3] = (String) credmap.get("salt");
            credential_arrList.add(creds);
        }

        return credential_arrList;
    }

    public static void main(String[] args) throws Exception{
        JSON_Reader jr = new JSON_Reader();

        ArrayList ar = jr.read("creds.json");

        for(int i = 0;i < ar.size();i++){
            for(int o = 0;o < 4;o++) {
                System.out.println(((String[]) ar.get(i))[o]);
            }
        }
    }
}
