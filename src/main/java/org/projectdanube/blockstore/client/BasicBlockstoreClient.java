package org.projectdanube.blockstore.client;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.projectdanube.blockstore.client.jsonrpc.JSONRPCClient;

public class BasicBlockstoreClient extends JSONRPCClient implements BlockstoreClient {

	public BasicBlockstoreClient(String server, Integer port) {

		super(server, port);
	}

	/*
	 * Instance methods
	 */

/*	@Override
	public JSONObject deleteImmutable(String name, String hash, String privateKey) throws IOException {

		return this.sendAndExpectJSONObject("delete_immutable", new Object[] { name, hash, privateKey });
	}*/

/*	@Override
	public JSONObject deleteMutable(String name, String dataId, String privateKey) throws IOException {

		return this.sendAndExpectJSONObject("delete_mutable", new Object[] { name, dataId, privateKey });
	}*/

	@Override
	public JSONArray getAllNames(Integer offset, Integer count) throws IOException {

		return this.sendAndExpectJSONArray("get_all_names", new Object[] { offset, count });
	}

	@Override
	public JSONObject getConsensusAt(String blockId) throws IOException {

		return this.sendAndExpectJSONObject("get_consensus_at", new Object[] { blockId });
	}

/*	@Override
	public JSONObject getImmutable(String name, String hash) throws IOException {

		return this.sendAndExpectJSONObject("get_immutable", new Object[] { name, hash });
	}

	@Override
	public JSONObject getMutable(String name, String dataId) throws IOException {

		return this.sendAndExpectJSONObject("get_mutable", new Object[] { name, dataId });
	}*/

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

/*	@Override
	public JSONObject getNameRecord(String name) throws IOException {

		return this.sendAndExpectJSONObject("get_name_record", new Object[] { name });
	}*/

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

/*	@Override
	public JSONObject putImmutable(String name) throws IOException {

		return this.sendAndExpectJSONObject("put_immutable", new Object[] { name });
	}*/

/*	@Override
	public JSONObject putMutable(String name, String dataId, String data, String privateKey, String nonce) throws IOException {

		return this.sendAndExpectJSONObject("put_mutable", new Object[] { name, dataId, data, privateKey, nonce });
	}*/

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
}
