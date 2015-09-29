package com.danubetech.blockstore.client;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.danubetech.blockstore.client.jsonrpc.JSONRPCClient;

public class BasicBlockstoreClient extends JSONRPCClient implements BlockstoreClient {

	public BasicBlockstoreClient(String server, Integer port) {

		super(server, port);
	}

	@Override
	public JSONObject ping() throws IOException {
		return this.sendAndExpectJSONObject("ping", new Object[] {  });
	}

	@Override
	public JSONObject getNameBlockchainRecord(String name) throws IOException {
		return this.sendAndExpectJSONObject("get_name_blockchain_record", new Object[] { name });
	}

	@Override
	public JSONObject getinfo() throws IOException {
		return this.sendAndExpectJSONObject("getinfo", new Object[] {  });
	}

	@Override
	public JSONObject preorder(String name, String privatekey, String register_addr) throws IOException {
		return this.sendAndExpectJSONObject("preorder", new Object[] { name, privatekey, register_addr });
	}

	@Override
	public JSONObject preorderTx(String name, String privatekey, String register_addr) throws IOException {
		return this.sendAndExpectJSONObject("preorder_tx", new Object[] { name, privatekey, register_addr });
	}

	@Override
	public JSONObject preorderTxSubsidized(String name, String public_key, String register_addr, String subsidy_key) throws IOException {
		return this.sendAndExpectJSONObject("preorder_tx_subsidized", new Object[] { name, public_key, register_addr, subsidy_key });
	}

	@Override
	public JSONObject register(String name, String privatekey, String register_addr) throws IOException {
		return this.sendAndExpectJSONObject("register", new Object[] { name, privatekey, register_addr });
	}

	@Override
	public JSONObject registerTx(String name, String privatekey, String register_addr) throws IOException {
		return this.sendAndExpectJSONObject("register_tx", new Object[] { name, privatekey, register_addr });
	}

	@Override
	public JSONObject registerTxSubsidized(String name, String public_key, String register_addr, String subsidy_key) throws IOException {
		return this.sendAndExpectJSONObject("register_tx_subsidized", new Object[] { name, public_key, register_addr, subsidy_key });
	}

	@Override
	public JSONObject update(String name, String data_hash, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("update", new Object[] { name, data_hash, privatekey });
	}

	@Override
	public JSONObject updateTx(String name, String data_hash, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("update_tx", new Object[] { name, data_hash, privatekey });
	}

	@Override
	public JSONObject updateTxSubsidized(String name, String data_hash, String public_key, String subsidy_key) throws IOException {
		return this.sendAndExpectJSONObject("update_tx_subsidized", new Object[] { name, data_hash, public_key, subsidy_key });
	}

	@Override
	public JSONObject transfer(String name, String address, boolean keepdata, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("transfer", new Object[] { name, address, keepdata, privatekey });
	}

	@Override
	public JSONObject transferTx(String name, String address, boolean keepdata, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("transfer_tx", new Object[] { name, address, keepdata, privatekey });
	}

	@Override
	public JSONObject transferTxSubsidized(String name, String address, boolean keepdata, String public_key, String subsidy_key) throws IOException {
		return this.sendAndExpectJSONObject("transfer_tx_subsidized", new Object[] { name, address, keepdata, public_key, subsidy_key });
	}

	@Override
	public JSONObject renew(String name, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("renew", new Object[] { name, privatekey });
	}

	@Override
	public JSONObject renewTx(String name, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("renew_tx", new Object[] { name, privatekey });
	}

	@Override
	public JSONObject renewTxSubsidized(String name, String public_key, String subsidy_key) throws IOException {
		return this.sendAndExpectJSONObject("renew_tx_subsidized", new Object[] { name, public_key, subsidy_key });
	}

	@Override
	public JSONObject revoke(String name, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("revoke", new Object[] { name, privatekey });
	}

	@Override
	public JSONObject revokeTx(String name, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("revoke_tx", new Object[] { name, privatekey });
	}

	@Override
	public JSONObject revokeTxSubsidized(String name, String public_key, String subsidy_key) throws IOException {
		return this.sendAndExpectJSONObject("revoke_tx_subsidized", new Object[] { name, public_key, subsidy_key });
	}

	@Override
	public JSONObject nameImport(String name, String recipient_address, String update_hash, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("name_import", new Object[] { name, recipient_address, update_hash, privatekey });
	}

	@Override
	public JSONObject nameImportTx(String name, String recipient_address, String update_hash, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("name_import_tx", new Object[] { name, recipient_address, update_hash, privatekey });
	}

	@Override
	public JSONObject namespacePreorder(String namespace_id, String reveal_addr, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("namespace_preorder", new Object[] { namespace_id, reveal_addr, privatekey });
	}

	@Override
	public JSONObject namespacePreorderTx(String namespace_id, String reveal_addr, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("namespace_preorder_tx", new Object[] { namespace_id, reveal_addr, privatekey });
	}

	@Override
	public JSONObject namespaceReveal(String namespace_id, String reveal_addr, String lifetime, String coeff, String base, String bucket_exponents, String nonalpha_discount, String no_vowel_discount, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("namespace_reveal", new Object[] { namespace_id, reveal_addr, lifetime, coeff, base, bucket_exponents, nonalpha_discount, no_vowel_discount, privatekey });
	}

	@Override
	public JSONObject namespaceRevealTx(String namespace_id, String reveal_addr, String lifetime, String coeff, String base, String bucket_exponents, String nonalpha_discount, String no_vowel_discount, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("namespace_reveal_tx", new Object[] { namespace_id, reveal_addr, lifetime, coeff, base, bucket_exponents, nonalpha_discount, no_vowel_discount, privatekey });
	}

	@Override
	public JSONObject namespaceReady(String namespace_id, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("namespace_ready", new Object[] { namespace_id, privatekey });
	}

	@Override
	public JSONObject namespaceReadyTx(String namespace_id, String privatekey) throws IOException {
		return this.sendAndExpectJSONObject("namespace_ready_tx", new Object[] { namespace_id, privatekey });
	}

	@Override
	public JSONObject getNameCost(String name) throws IOException {
		return this.sendAndExpectJSONObject("get_name_cost", new Object[] { name });
	}

	@Override
	public JSONObject getNamespaceCost(String namespace_id) throws IOException {
		return this.sendAndExpectJSONObject("get_namespace_cost", new Object[] { namespace_id });
	}

	@Override
	public JSONObject getNamespaceBlockchainRecord(String namespace_id) throws IOException {
		return this.sendAndExpectJSONObject("get_namespace_blockchain_record", new Object[] { namespace_id });
	}

	@Override
	public JSONArray getAllNames(Integer offset, Integer count) throws IOException {
		return this.sendAndExpectJSONArray("get_all_names", new Object[] { offset, count });
	}

	@Override
	public JSONArray getNamesInNamespace(String namespace_id, Integer offset, Integer count) throws IOException {
		return this.sendAndExpectJSONArray("get_names_in_namespace", new Object[] { namespace_id, offset, count });
	}

	@Override
	public JSONObject getConsensusAt(String block_id) throws IOException {
		return this.sendAndExpectJSONObject("get_consensus_at", new Object[] { block_id });
	}

}
