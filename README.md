<a href="http://projectdanube.org/" target="_blank"><img src="http://projectdanube.github.com/xdi2/images/projectdanube_logo.png" align="right"></a>
<br>

This is a **work-in-progress** Java client library for [Blockstore](https://github.com/blockstack/blockstore/),
which allows name registration using a combination of the Bitcoin blockchain and a Kademlia DHT.

This is modeled after a snapshot commit the Python [Blockstore Client](https://github.com/blockstack/blockstore-client) and should not be considered stable. This is currently only able to communicate with
a running instance of Blockstore, but cannot currently query the DHT.

**Not ready for production use! Not all commands work properly! Use at your own risk!**

Pull requests welcome.

### How to build

Just run

    mvn clean install

to build.

### Example use

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

### Community

Google Group: http://groups.google.com/group/xdi2

IRC: irc://irc.freenode.net:6667/xdi
