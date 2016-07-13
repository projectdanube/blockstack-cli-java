package com.danubetech.blockstore.client.jsonrpc;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;
import java.util.UUID;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public abstract class JSONRPCClient {

	public static final String CHARSET = "UTF-8";
	private static final JSONParser PARSER = new JSONParser();

	private String server;
	private Integer port;

	protected JSONRPCClient(String server, Integer port) {

		this.server = server;
		this.port = port;
	}

	protected JSONAware send(String method, Object[] params) throws IOException {

		// connect

		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(this.getServer(), this.getPort().intValue()));

		Reader reader = new InputStreamReader(socket.getInputStream(), CHARSET);
		Writer writer = new OutputStreamWriter(socket.getOutputStream(), CHARSET);

		// send request

		String id = UUID.randomUUID().toString();

		writer.write(NotQuiteJSONRPC.netstringRequest(id, method, params));
		writer.flush();

		// parse response

		JSONObject jsonResponse;

		try {

			Object object = PARSER.parse(NotQuiteJSONRPC.netstringResponse(reader));
			if (! (object instanceof JSONObject)) throw new IOException("Not a JSON object response: " + object.getClass().getSimpleName());

			jsonResponse = (JSONObject) object;
		} catch (ParseException ex) {

			throw new IOException("Parsing error: " + ex.getMessage(), ex);
		} finally {

			reader.close();
			writer.close();
			socket.close();
		}

		// check response

		JSONAware jsonResult;

		if (! "2.0".equals((jsonResponse).get("jsonrpc"))) throw new IOException("Invalid JSON RPC response version: " + jsonResponse.get("jsonrpc"));
		if (! id.equals((jsonResponse).get("id"))) throw new IOException("Invalid JSON RPC response ID: " + jsonResponse.get("id"));
		if (! (jsonResponse.get("result") instanceof JSONAware)) throw new IOException("Invalid JSON RPC result: " + jsonResponse.get("result"));

		jsonResult = (JSONAware) jsonResponse.get("result");

		// check for error

		if (jsonResult instanceof JSONArray && ((JSONArray) jsonResult).size() == 1 && ((JSONArray) jsonResult).get(0) instanceof JSONObject) {

			jsonResult = (JSONObject) ((JSONArray) jsonResult).get(0);
		}

		if (jsonResult instanceof JSONObject) {

			JSONObject jsonObject = (JSONObject) jsonResult;

			if (jsonObject.containsKey("fault") || jsonObject.containsKey("faultCode") || jsonObject.containsKey("faultString") || jsonObject.containsKey("error")) {

				StringBuilder errorString = new StringBuilder("RPC Problem: ");
				if (jsonObject.containsKey("fault")) errorString.append(jsonObject.get("fault") + " ");
				if (jsonObject.containsKey("faultCode")) errorString.append(jsonObject.get("faultCode") + " ");
				if (jsonObject.containsKey("faultString")) errorString.append(jsonObject.get("faultString") + " ");
				if (jsonObject.containsKey("error") && jsonObject.get("error") instanceof String) errorString.append(jsonObject.get("error") + " ");
				if (jsonObject.containsKey("error") && jsonObject.get("error") instanceof JSONObject && ((JSONObject) jsonObject.get("error")).containsKey("code")) errorString.append(((JSONObject) jsonObject.get("error")).get("code") + " ");
				if (jsonObject.containsKey("error") && jsonObject.get("error") instanceof JSONObject && ((JSONObject) jsonObject.get("error")).containsKey("message")) errorString.append(((JSONObject) jsonObject.get("error")).get("message") + " ");
				if (jsonObject.containsKey("error") && jsonObject.get("error") instanceof JSONObject && ((JSONObject) jsonObject.get("error")).containsKey("data")) errorString.append(((JSONObject) jsonObject.get("error")).get("data") + " ");

				throw new IOException("Error response: " + errorString.toString());
			}
		}

		// done

		return jsonResponse;
	}

	protected JSONObject sendAndExpectJSONObject(String method, Object[] params) throws IOException {

		JSONAware jsonAware = this.send(method, params);

		if (jsonAware instanceof JSONObject) return (JSONObject) jsonAware;
		if (jsonAware instanceof JSONArray && ((JSONArray) jsonAware).size() == 1 && ((JSONArray) jsonAware).get(0) instanceof JSONObject) return (JSONObject) ((JSONArray) jsonAware).get(0);

		throw new IOException("Not a JSON object response: " + jsonAware.getClass().getSimpleName());
	}

	protected JSONArray sendAndExpectJSONArray(String method, Object[] params) throws IOException {

		JSONAware jsonAware = this.send(method, params);

		if (jsonAware instanceof JSONArray) return (JSONArray) jsonAware;

		throw new IOException("Not a JSON array response: " + jsonAware.getClass().getSimpleName());
	}

	/*
	 * Getters and setters
	 */

	public String getServer() {

		return this.server;
	}
	public void setServer(String server) {

		this.server = server;
	}

	public Integer getPort() {

		return this.port;
	}

	public void setPort(Integer port) {

		this.port = port;
	}
}
