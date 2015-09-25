package org.projectdanube.blockstore.client;

import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.Reader;
import java.io.Writer;
import java.net.InetSocketAddress;
import java.net.Socket;

import org.json.simple.JSONArray;
import org.json.simple.JSONAware;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.projectdanube.blockstore.client.jsonrpc.NotQuiteJSONRPC;

public class BasicBlockstoreClient implements BlockstoreClient {

	public static final String CHARSET = "UTF-8";
	private static final JSONParser PARSER = new JSONParser();

	private String server;
	private Integer port;

	public BasicBlockstoreClient(String server, Integer port) {

		this.server = server;
		this.port = port;
	}

	/*
	 * Instance methods
	 */

	@Override
	public JSONObject deleteImmutable(String name, String hash, String privateKey) throws IOException {

		return this.sendAndExpectJSONObject("delete_immutable", new Object[] { name, hash, privateKey });
	}

	@Override
	public JSONObject deleteMutable(String name, String dataId, String privateKey) throws IOException {

		return this.sendAndExpectJSONObject("delete_mutable", new Object[] { name, dataId, privateKey });
	}

	@Override
	public JSONArray getAllNames(Integer offset, Integer count) throws IOException {

		return this.sendAndExpectJSONArray("get_all_names", new Object[] { offset, count });
	}

	@Override
	public JSONObject getConsensusAt(String blockId) throws IOException {

		return this.sendAndExpectJSONObject("get_consensus_at", new Object[] { blockId });
	}

	@Override
	public JSONObject getImmutable(String name, String hash) throws IOException {

		return this.sendAndExpectJSONObject("get_immutable", new Object[] { name, hash });
	}

	@Override
	public JSONObject getMutable(String name, String dataId) throws IOException {

		return this.sendAndExpectJSONObject("get_mutable", new Object[] { name, dataId });
	}

	@Override
	public JSONObject getNameCost(String name) throws IOException {

		return this.sendAndExpectJSONObject("get_name_cost", new Object[] { name });
	}

	@Override
	public JSONArray getNamesInNamespace(String namespaceId, Integer offset, Integer count) throws IOException {

		return this.sendAndExpectJSONArray("get_names_in_namespace", new Object[] { namespaceId, offset, count });
	}

	@Override
	public JSONObject getNamespaceCost(String namespaceId) throws IOException {

		return this.sendAndExpectJSONObject("get_namespace_cost", new Object[] { namespaceId });
	}

	@Override
	public JSONObject getNameRecord(String name) throws IOException {

		return this.sendAndExpectJSONObject("get_name_record", new Object[] { name });
	}

	@Override
	public JSONObject getinfo() throws IOException {

		return this.sendAndExpectJSONObject("getinfo", new Object[] { });
	}

	@Override
	public JSONObject lookup(String name) throws IOException {

		return this.sendAndExpectJSONObject("lookup", new Object[] { name });
	}

	@Override
	public JSONObject lookupNamespace(String namespaceId) throws IOException {

		return this.sendAndExpectJSONObject("lookup_namespace", new Object[] { namespaceId });
	}

	@Override
	public JSONObject nameImport() throws IOException {

		return this.sendAndExpectJSONObject("name_import", new Object[] { });
	}

	@Override
	public JSONObject namespacePreorder() throws IOException {

		return this.sendAndExpectJSONObject("namespacePreorder", new Object[] { });
	}

	@Override
	public JSONObject namespaceReveal() throws IOException {

		return this.sendAndExpectJSONObject("namespaceReveal", new Object[] { });
	}

	@Override
	public JSONObject namespaceReady() throws IOException {

		return this.sendAndExpectJSONObject("namespaceReady", new Object[] { });
	}

	@Override
	public JSONObject ping() throws IOException {

		return this.sendAndExpectJSONObject("ping", new Object[] { });
	}

	@Override
	public JSONObject preorder(String name, String privateKey) throws IOException {

		return this.sendAndExpectJSONObject("preorder", new Object[] { name, privateKey });
	}

	@Override
	public JSONObject putImmutable(String name) throws IOException {

		return this.sendAndExpectJSONObject("put_immutable", new Object[] { name });
	}

	@Override
	public JSONObject putMutable(String name, String dataId, String data, String privateKey, String nonce) throws IOException {

		return this.sendAndExpectJSONObject("put_mutable", new Object[] { name, dataId, data, privateKey, nonce });
	}

	@Override
	public JSONObject register(String name, String addr, String privateKey) throws IOException {

		return this.sendAndExpectJSONObject("register", new Object[] { name, addr, privateKey });
	}

	@Override
	public JSONObject renew(String name, String privateKey) throws IOException {

		return this.sendAndExpectJSONObject("renew", new Object[] { name, privateKey });
	}

	@Override
	public JSONObject revoke(String name, String privateKey) throws IOException {

		return this.sendAndExpectJSONObject("revoke", new Object[] { name, privateKey });
	}

	@Override
	public JSONObject transfer() throws IOException {

		return this.sendAndExpectJSONObject("transfer", new Object[] { });
	}

	@Override
	public JSONObject update() throws IOException {

		return this.sendAndExpectJSONObject("updated", new Object[] { });
	}

	/*
	 * Helper methods
	 */

	private JSONAware send(String method, Object[] params) throws IOException {

		// connect

		Socket socket = new Socket();
		socket.connect(new InetSocketAddress(this.getServer(), this.getPort().intValue()));

		Reader reader = new InputStreamReader(socket.getInputStream(), CHARSET);
		Writer writer = new OutputStreamWriter(socket.getOutputStream(), CHARSET);

		// send request

		writer.write(NotQuiteJSONRPC.netstringRequest(method, params));
		writer.flush();

		// parse response

		JSONAware jsonAware;

		try {

			Object object = PARSER.parse(NotQuiteJSONRPC.netstringResponse(reader));
			if (! (object instanceof JSONAware)) throw new IOException("Not a JSON response: " + object.getClass().getSimpleName());

			jsonAware = (JSONAware) object;
		} catch (ParseException ex) {

			throw new IOException("Parsing error: " + ex.getMessage(), ex);
		} finally {

			reader.close();
			writer.close();
			socket.close();
		}

		// check for error

		if (jsonAware instanceof JSONObject) {

			JSONObject jsonObject = (JSONObject) jsonAware;

			if (jsonObject.containsKey("fault") || jsonObject.containsKey("faultCode") || jsonObject.containsKey("faultString") || jsonObject.containsKey("error")) {

				StringBuilder errorString = new StringBuilder("RPC Problem: ");
				if (jsonObject.containsKey("fault")) errorString.append(jsonObject.get("fault") + " ");
				if (jsonObject.containsKey("faultCode")) errorString.append(jsonObject.get("faultCode") + " ");
				if (jsonObject.containsKey("faultString")) errorString.append(jsonObject.get("faultString") + " ");
				if (jsonObject.containsKey("error") && ((JSONObject) jsonObject.get("error")).containsKey("code")) errorString.append(((JSONObject) jsonObject.get("error")).get("code") + " ");
				if (jsonObject.containsKey("error") && ((JSONObject) jsonObject.get("error")).containsKey("message")) errorString.append(((JSONObject) jsonObject.get("error")).get("message") + " ");
				if (jsonObject.containsKey("error") && ((JSONObject) jsonObject.get("error")).containsKey("data")) errorString.append(((JSONObject) jsonObject.get("error")).get("data") + " ");

				throw new IOException("Error response: " + errorString.toString());
			}
		}

		// done

		return jsonAware;
	}

	private JSONObject sendAndExpectJSONObject(String method, Object[] params) throws IOException {

		JSONAware jsonAware = this.send(method, params);

		if (jsonAware instanceof JSONObject) return (JSONObject) jsonAware;
		if (jsonAware instanceof JSONArray && ((JSONArray) jsonAware).size() == 1 && ((JSONArray) jsonAware).get(0) instanceof JSONObject) return (JSONObject) ((JSONArray) jsonAware).get(0);

		throw new IOException("Not a JSON object response: " + jsonAware.getClass().getSimpleName());
	}

	private JSONArray sendAndExpectJSONArray(String method, Object[] params) throws IOException {

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
