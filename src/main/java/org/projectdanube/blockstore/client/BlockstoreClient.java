package org.projectdanube.blockstore.client;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Client interface to the Blockstore server.
 * 
 * @author peacekeeper
 */
public interface BlockstoreClient {

	//public JSONObject deleteImmutable(String name, String hash, String privateKey) throws IOException;
	//public JSONObject deleteMutable(String name, String dataId, String privateKey) throws IOException;
	public JSONArray getAllNames(Integer offset, Integer count) throws IOException;
	public JSONObject getConsensusAt(String blockId) throws IOException;
	//public JSONObject getImmutable(String name, String hash) throws IOException;
	//public JSONObject getMutable(String name, String dataId) throws IOException;
	public JSONObject getNameCost(String name) throws IOException;
	public JSONArray getNamesInNamespace(String namespaceId, Integer offset, Integer count) throws IOException;
	public JSONObject getNamespaceCost(String namespaceId) throws IOException;
	//public JSONObject getNameRecord(String name) throws IOException;
	public JSONObject getinfo() throws IOException;
	public JSONObject lookup(String name) throws IOException;
	public JSONObject lookupNamespace(String namespaceId) throws IOException;
	public JSONObject nameImport() throws IOException;
	public JSONObject namespacePreorder() throws IOException;
	public JSONObject namespaceReveal() throws IOException;
	public JSONObject namespaceReady() throws IOException;
	public JSONObject ping() throws IOException;
	public JSONObject preorder(String name, String privateKey) throws IOException;
	//public JSONObject putImmutable(String name) throws IOException;
	//public JSONObject putMutable(String name, String dataId, String data, String privateKey, String nonce) throws IOException;
	public JSONObject register(String name, String addr, String privateKey) throws IOException;
	public JSONObject renew(String name, String privateKey) throws IOException;
	public JSONObject revoke(String name, String privateKey) throws IOException;
	public JSONObject transfer() throws IOException;
	public JSONObject update() throws IOException;
}
