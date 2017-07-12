package de.tub.ise.anwsys.clients;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.json.JSONArray;
import org.json.JSONObject;

import com.mashape.unirest.http.HttpResponse;
import com.mashape.unirest.http.JsonNode;
import com.mashape.unirest.http.Unirest;
import com.mashape.unirest.http.exceptions.UnirestException;

public class TestClient {

	public static void main(String[] args) throws IOException, UnirestException {

		HttpResponse<String> response = Unirest.get("http://localhost:7878/meters").asString();
		JSONObject jsonObject = new JSONObject(response.getBody());
		JSONArray meterIdsJSONArray = ((JSONArray) jsonObject.get("meters"));
		Iterator<Object> iterator = meterIdsJSONArray.iterator();

		Set<Measurement> measurements = new HashSet<>();

		List<String> meterIds = new ArrayList<>();
		while (iterator.hasNext()) {
			Object next = iterator.next();
			meterIds.add((String) next);
			addMetricNames((String) next, measurements);
		}

		Map<String, List<MeasuredData>> measuredDataByMeterId = new HashMap<>();
		for (int i = 0; i < 10; i++) {
			for (String meterId : meterIds) {
				MeasuredData metricData = getMetricData(meterId, measurements);
				List<MeasuredData> list = measuredDataByMeterId.get(meterId);
				if (list == null) {
					list = new ArrayList<>();
					measuredDataByMeterId.put(meterId, list);
				}
				list.add(metricData);
			}
		}

		System.out.println(measurements.size());
		for (String key : measuredDataByMeterId.keySet()) {
			System.out.println("For meter id : " + key);
			for (MeasuredData data : measuredDataByMeterId.get(key)) {
				StringBuffer buf = new StringBuffer();
				for (String dataKey : data.getMeasuredValueById().keySet()) {
					Double doubles = data.getMeasuredValueById().get(dataKey);
					buf.append(dataKey + " : " + doubles + " ");
				}

				System.out.println("######## " + " TIMESTAMP : " + data.getTimestamp() + " --- " + buf);

			}
		}
	}

	private static void addMetricNames(String meterId, Set<Measurement> measurements)
			throws IOException, UnirestException {
		String URL = "http://localhost:7878/meters/" + meterId;

		HttpResponse<JsonNode> asJson = Unirest.get(URL).asJson();
		JSONArray array = asJson.getBody().getArray();
		Iterator<Object> iterator = array.iterator();
		while (iterator.hasNext()) {
			JSONObject node = (JSONObject) iterator.next();
			String metricIdValue = (String) node.get("metricId");
			String metricTextValue = (String) node.get("metricText");
			Measurement measurement = new Measurement(metricIdValue, metricTextValue);
			measurements.add(measurement);
		}

	}

	private static MeasuredData getMetricData(String meterId, Set<Measurement> measurements)
			throws IOException, UnirestException {
		MeasuredData result = null;
		String URL = "http://localhost:7878/meters/" + meterId + "/data";

		HttpResponse<JsonNode> asJson = Unirest.get(URL).asJson();
		JSONArray array = asJson.getBody().getArray();
		Iterator<Object> iterator = array.iterator();
		while (iterator.hasNext()) {
			JSONObject node = (JSONObject) iterator.next();
			Integer timestamp = ((Integer) node.get("unixTimestamp"));
			JSONArray measurementsJSONArray = (JSONArray) node.get("measurements");
			Iterator<Object> innerIterator = measurementsJSONArray.iterator();
			Map<String, Double> map = new HashMap<String, Double>();

			while (innerIterator.hasNext()) {
				JSONObject innerNode = (JSONObject) innerIterator.next();
				String metricId = ((String) innerNode.get("metricId"));
				double metricValue = ((Double) innerNode.get("value"));
				map.put(metricId, metricValue);
			}
			result = new MeasuredData(timestamp, map);
			break;
		}

		return result;

	}
}
