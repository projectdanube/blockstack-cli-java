<a href="http://projectdanube.org/" target="_blank"><img src="http://projectdanube.github.com/xdi2/images/projectdanube_logo.png" align="right"></a>

## Blockstore Client (Java)

This is a **work-in-progress** Java client library for [Blockstore](https://github.com/blockstack/blockstore/). Pull requests welcome.

This is modeled after a snapshot version of the Python [Blockstore Client](https://github.com/blockstack/blockstore-client) and should not be considered stable! This is able to communicate with
a running instance of Blockstore, but it cannot currently query the DHT or other store of name records.

**Not ready for production use! Not all commands work properly! Use at your own risk!**

### How to build

Just run

    mvn clean install

### Example use

	package com.danubetech.blockstore.client;
	
	public class Example {
	
		public static void main(String[] args) throws Exception {
	
			BlockstoreClient blockstoreClient = new BasicBlockstoreClient("localhost", Integer.valueOf(6264));
			DHTClient dhtClient = new BasicDHTMirrorClient("dht.blockstack.org", Integer.valueOf(6266));
	
			System.out.println(blockstoreClient.ping());
			System.out.println(blockstoreClient.lookup("christophera.id"));
			System.out.println(blockstoreClient.getNameCost("christophera.id"));
			System.out.println(blockstoreClient.getNamespaceCost("id"));
			System.out.println(blockstoreClient.getNamesInNamespace("id", 1000, 3));
			System.out.println(dhtClient.get("dfe6e3f40a770c90fd8158f9c28c950631c4818b"));
		}
	}

Output:

	{"status":"alive"}
	{"first_registered":374085,"sender":"76a914358751cbc593450619bff9b03cd0698154a8a0fe88ac","sender_pubkey":"02eb1a3612b9e29b1770e76916a6226f72fc32025bab8061c5298483378f4b31f7","address":"15t2vLF87xZogitNRLzQSih5Kz18XWBMS1","value_hash":"dfe6e3f40a770c90fd8158f9c28c950631c4818b","last_renewed":374085,"revoked":false}
	{"satoshis":25000}
	{"satoshis":4000000000}
	[{"akulahalfi.id":{"first_registered":373806,"sender":"76a9146715c6cd61add469eb9b1c6ed993278bc01c6d6f88ac","sender_pubkey":"0411d88aa37a0eea476a5b63ca4b1cd392ded830865824c27dacef6bde9f9bc53fa13a0926533ef4d20397207e212c2086cbe13db5470fd29616abd35326d33090","address":"1AQ4gpdeQ7nQnWh76VyRb2zK2H3ner8Cif","value_hash":"c2ca9b83221e3028edafa1d4630fb7d73c36bd7d","last_renewed":373806,"revoked":false},"aku.id":{"first_registered":374080,"sender":"76a9142c3ea3c8c0c4211a1f36837b2459e334daed8aa788ac","sender_pubkey":"0411d88aa37a0eea476a5b63ca4b1cd392ded830865824c27dacef6bde9f9bc53fa13a0926533ef4d20397207e212c2086cbe13db5470fd29616abd35326d33090","address":"152wmjGzesppMoywYYpMAV4BGEiYtfKfYR","value_hash":"5f547e5be733275459d3f3a321cc753e4b3185d2","last_renewed":374080,"revoked":false}}]
	{"value":"{\"auth\": [{\"publicKeychain\": \"xpub6AcbMYd6rxrKB4faJDPMLsZ9jv7UP41FLdNaVG86WZsYPSuSJKCBMUobbGSm5M2UiLfft3wQwn8boL96GxK1TGMRm6tLbZD7ggCs2MBtnZx\"}], \"avatar\": {\"url\": \"https:\/\/s3.amazonaws.com\/kd4\/christophera\"}, \"bio\": \"Internet Cryptography Pioneer & CTO \\u2014 Co-author TLS Security Standard \\u2014 Blockchain Developer \\u2014 Collaborative Tools & Patterns \\u2014 Faculty for MBA in Sustainable Systems\", \"bitcoin\": {\"address\": \"16NpNep2SYbmp2CxpJiUdWjvsYLpK9k6wD\"}, \"cover\": {\"url\": \"https:\/\/s3.amazonaws.com\/97p\/ic1.jpeg\"}, \"facebook\": {\"proof\": {\"url\": \"https:\/\/facebook.com\/ChristopherRayAllen\/posts\/10153445966950540\"}, \"username\": \"ChristopherRayAllen\"}, \"github\": {\"proof\": {\"url\": \"https:\/\/gist.github.com\/ChristopherA\/7c427227b518504a713d\"}, \"username\": \"ChristopherA\"}, \"location\": {\"formatted\": \"Berkeley, CA\"}, \"name\": {\"formatted\": \"Christopher Allen\"}, \"pgp\": {\"fingerprint\": \"357405ED\", \"url\": \"https:\/\/s3.amazonaws.com\/pk9\/christophera\"}, \"twitter\": {\"proof\": {\"url\": \"https:\/\/twitter.com\/ChristopherA\/status\/585585560514879490\"}, \"username\": \"ChristopherA\"}, \"v\": \"0.2\", \"website\": \"http:\/\/www.LifeWithAlacrity.com\"}","key":"dfe6e3f40a770c90fd8158f9c28c950631c4818b"}
