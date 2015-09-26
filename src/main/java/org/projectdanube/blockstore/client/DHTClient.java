package org.projectdanube.blockstore.client;

import java.io.IOException;

import org.json.simple.JSONObject;

/**
 * Client interface to the DHT mirror server.
 * 
 * @author peacekeeper
 */
public interface DHTClient {

	public JSONObject get(String key) throws IOException;
}
