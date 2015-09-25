package org.projectdanube.blockstore.client.jsonrpc;

import java.io.IOException;
import java.io.Reader;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Support methods for Blockstore's "not-quite-JSONRPC" interface.
 * 
 * @author peacekeeper
 */
public class NotQuiteJSONRPC {

	private static final String JSONRPC_VERSION = "2.0";

	@SuppressWarnings("unchecked")
	public static String netstringRequest(String method, Object[] params) {

		JSONArray jsonArray = new JSONArray();
		for (Object param : params) if (param != null) jsonArray.add(param);

		JSONObject jsonObject = new JSONObject();
		jsonObject.put("version", JSONRPC_VERSION);
		jsonObject.put("id", UUID.randomUUID().toString());
		jsonObject.put("method", method);
		jsonObject.put("params", jsonArray);

		String jsonString = jsonObject.toJSONString();

		return Integer.toString(jsonString.length()) + ":" + jsonString + ",";
	}

	public static String netstringResponse(Reader reader) throws IOException {

		int ch;
		StringBuilder lengthString = new StringBuilder();

		while ((ch = reader.read()) != ':') lengthString.append((char) ch);

		char[] buffer = new char[Integer.parseInt(lengthString.toString())];
		reader.read(buffer);

		return String.valueOf(buffer);
	}
}
