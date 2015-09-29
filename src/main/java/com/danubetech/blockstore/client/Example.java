package com.danubetech.blockstore.client;

public class Example {

	public static void main(String[] args) throws Exception {

		BlockstoreClient blockstoreClient = new BasicBlockstoreClient("localhost", Integer.valueOf(6264));
		DHTClient dhtClient = new BasicDHTMirrorClient("dht.blockstack.org", Integer.valueOf(6266));

		System.out.println(blockstoreClient.ping());
		System.out.println(blockstoreClient.getNameBlockchainRecord("christophera.id"));
		System.out.println(blockstoreClient.getNameCost("christophera.id"));
		System.out.println(blockstoreClient.getNamespaceCost("id"));
		System.out.println(blockstoreClient.getNamesInNamespace("id", 1000, 3));
		System.out.println(dhtClient.ping());
		System.out.println(dhtClient.get("dfe6e3f40a770c90fd8158f9c28c950631c4818b"));
	}
}
