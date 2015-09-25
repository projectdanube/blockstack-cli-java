package org.projectdanube.blockstore.client;

public class Example {

	public static void main(String[] args) throws Exception {

		BlockstoreClient client = new BasicBlockstoreClient("localhost", Integer.valueOf(6264));

		System.out.println(client.ping());
		System.out.println(client.lookup("christophera.id"));
		System.out.println(client.getNameCost("christophera.id"));
		System.out.println(client.getNamespaceCost("id"));
		System.out.println(client.getNamesInNamespace("id", 1000, 3));
	}
}
