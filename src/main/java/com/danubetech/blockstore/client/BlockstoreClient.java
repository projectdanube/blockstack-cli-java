package com.danubetech.blockstore.client;

import java.io.IOException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

/**
 * Client interface to the Blockstore server.
 * 
 * @author peacekeeper
 */
public interface BlockstoreClient {

	public JSONObject ping() throws IOException;
	public JSONObject getNameBlockchainRecord(String name) throws IOException;
	public JSONObject getinfo() throws IOException;
	public JSONObject preorder(String name, String privatekey, String register_addr) throws IOException;
	public JSONObject preorderTx(String name, String privatekey, String register_addr) throws IOException;
	public JSONObject preorderTxSubsidized(String name, String public_key, String register_addr, String subsidy_key) throws IOException;
	public JSONObject register(String name, String privatekey, String register_addr) throws IOException;
	public JSONObject registerTx(String name, String privatekey, String register_addr) throws IOException;
	public JSONObject registerTxSubsidized(String name, String public_key, String register_addr, String subsidy_key) throws IOException;
	public JSONObject update(String name, String data_hash, String privatekey) throws IOException;
	public JSONObject updateTx(String name, String data_hash, String privatekey) throws IOException;
	public JSONObject updateTxSubsidized(String name, String data_hash, String public_key, String subsidy_key) throws IOException;
	public JSONObject transfer(String name, String address, boolean keepdata, String privatekey) throws IOException;
	public JSONObject transferTx(String name, String address, boolean keepdata, String privatekey) throws IOException;
	public JSONObject transferTxSubsidized(String name, String address, boolean keepdata, String public_key, String subsidy_key) throws IOException;
	public JSONObject renew(String name, String privatekey) throws IOException;
	public JSONObject renewTx(String name, String privatekey) throws IOException;
	public JSONObject renewTxSubsidized(String name, String public_key, String subsidy_key) throws IOException;
	public JSONObject revoke(String name, String privatekey) throws IOException;
	public JSONObject revokeTx(String name, String privatekey) throws IOException;
	public JSONObject revokeTxSubsidized(String name, String public_key, String subsidy_key) throws IOException;
	public JSONObject nameImport(String name, String recipient_address, String update_hash, String privatekey) throws IOException;
	public JSONObject nameImportTx(String name, String recipient_address, String update_hash, String privatekey) throws IOException;
	public JSONObject namespacePreorder(String namespace_id, String reveal_addr, String privatekey) throws IOException;
	public JSONObject namespacePreorderTx(String namespace_id, String reveal_addr, String privatekey) throws IOException;
	public JSONObject namespaceReveal(String namespace_id, String reveal_addr, String lifetime, String coeff, String base, String bucket_exponents, String nonalpha_discount, String no_vowel_discount, String privatekey) throws IOException;
	public JSONObject namespaceRevealTx(String namespace_id, String reveal_addr, String lifetime, String coeff, String base, String bucket_exponents, String nonalpha_discount, String no_vowel_discount, String privatekey) throws IOException;
	public JSONObject namespaceReady(String namespace_id, String privatekey) throws IOException;
	public JSONObject namespaceReadyTx(String namespace_id, String privatekey) throws IOException;
	public JSONObject getNameCost(String name) throws IOException;
	public JSONObject getNamespaceCost(String namespace_id) throws IOException;
	public JSONObject getNamespaceBlockchainRecord(String namespace_id) throws IOException;
	public JSONArray getAllNames(Integer offset, Integer count) throws IOException;
	public JSONArray getNamesInNamespace(String namespace_id, Integer offset, Integer count) throws IOException;
	public JSONObject getConsensusAt(String block_id) throws IOException;
}
