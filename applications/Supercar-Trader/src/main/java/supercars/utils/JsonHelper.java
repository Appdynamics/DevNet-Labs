/**
 * 
 */
package supercars.utils;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import org.apache.http.HttpResponse;

import supercars.Manufacturer;
import supercars.form.EnquireForm;
import supercars.utils.json.JSONArray;
import supercars.utils.json.JSONObject;

/**
 * @author james
 *
 */
public class JsonHelper {

	/**
	 * 
	 */
	public JsonHelper() {
		
	}

	
	public static String getJsonFromHttpResponse(HttpResponse response) throws Throwable {
		String resp = "";
        BufferedReader reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent()));
        StringBuilder out = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            out.append(line);
        }			
		
        resp = out.toString();
		reader.close();
		return resp;
	}
	
	public static Collection<Manufacturer> getManufacturerList(String json) throws Throwable {
		
		List<Manufacturer> manufacturers = new ArrayList<Manufacturer>();
		
		JSONArray jsonArray = new JSONArray(json);
		
		int arrLength = jsonArray.length();
		for (int i = 0; i < arrLength; i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			Manufacturer dest = new Manufacturer();
			dest.setEmail(obj.getString("email"));
			dest.setEngineId(obj.getLong("engineId"));
			dest.setSmallLogo(obj.getString("smallLogo"));
			dest.setLargeLogo(obj.getString("largeLogo"));
			dest.setManufacturerId(obj.getLong("manufacturerId"));
			dest.setName(obj.getString("name"));
			dest.setWeb(obj.getString("web"));
			manufacturers.add(dest);
		}
		
		return manufacturers;
		
	}
	
	public static Collection<EnquireForm> getEnquiryList(String json) throws Throwable {
		
		List<EnquireForm> enquiries = new ArrayList<EnquireForm>();
		JSONArray jsonArray = new JSONArray(json);
		
		int arrLength = jsonArray.length();
		for (int i = 0; i < arrLength; i++) {
			JSONObject obj = jsonArray.getJSONObject(i);
			EnquireForm dest = new EnquireForm();
			dest.setName(obj.getString("name"));
			dest.setEmail(obj.getString("email"));
			dest.setComment(obj.getString("comment"));
			enquiries.add(dest);
		}		
		return enquiries;
	} 
}
