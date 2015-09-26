package com.danubetech.blockstore.client;

import java.io.IOException;

import org.json.simple.JSONObject;

import com.danubetech.blockstore.client.jsonrpc.JSONRPCClient;

public class BasicDHTMirrorClient extends JSONRPCClient implements DHTClient {

	public BasicDHTMirrorClient(String server, Integer port) {

		super(server, port);
	}

	/*
	 * Instance methods
	 */

	@Override
	public JSONObject get(String key) throws IOException {

		return this.sendAndExpectJSONObject("get", new Object[] { key });
	}
}