import java.util.HashMap;

public class Router{
	private String routerid = "";
	private HashMap<String, RoutingPair> tablelist = new HashMap<String, RoutingPair>();
	
	public Router(String routerid) {
		/* num_routers excludes the current router */
		this.routerid = routerid;
	}
	
	public boolean updateTable(RoutingTblEntry[] rte, String sender_id) {
		boolean flag = false;
		for (int i = 0; i < rte.length; i++) {
			String dest_net_id = rte[i].getDestnetid();
			int ndist = rte[i].getDist();
			if (tablelist.get(dest_net_id) == null) {
				RoutingPair temprp =  new RoutingPair();
				temprp.modify(sender_id, ndist + 1);
				tablelist.put(dest_net_id, temprp);
				flag = true;
			}
			else if ((tablelist.get(dest_net_id)).getDist() > (ndist + 1)) {
				RoutingPair temprp =  new RoutingPair();
				temprp.modify(sender_id, ndist + 1);
				tablelist.put(dest_net_id, temprp);
				flag = true;
			}
		}
		return flag;
	}
	
	public void add(String destid, String hopid, int distance) {
		/* Modify the existing routing table 
		 * if the destination netid already exists */
		RoutingPair rt = new RoutingPair();		
		rt.modify(hopid, distance);
		tablelist.put(destid, rt);
	}
	
	public RoutingPair RoutingTable(String dest_net_id) {
		/* Return null if no such netid in the routing table */
		RoutingPair rt = tablelist.get(dest_net_id);
		return rt;
	}
	
	public RoutingTblEntry[] getTables() {
		int size = this.getSize();
		RoutingTblEntry[] rte = new RoutingTblEntry[size];
		String[] dest_net_ids = (tablelist.keySet()).toArray(new String[0]);
		
		for (int i = 0; i < size; i++) {
			rte[i] = new RoutingTblEntry();
			rte[i].modify(dest_net_ids[i], tablelist.get(dest_net_ids[i]));
		}
		
		return rte;
	}
	
	public int getDist(String dest_net_id) {
		/* Return -1 if no such netid in the routing table */
		RoutingPair rt = tablelist.get(dest_net_id);
		if(rt == null) return -1;
		return rt.getDist();
	}
	
	public String getId() {
		return routerid;
	}
	
	public int getSize() {
		return tablelist.size();
	}
	
	public void printTable() {
		System.out.println("*****************Routing table for router:" + routerid + "************************");
		System.out.println("Destination\tGateway\t\tDistance");
		RoutingTblEntry[] rte = this.getTables();
		
		for(int i = 0; i < rte.length; i++) {
			System.out.println(rte[i].getDestnetid() + "\t\t" + rte[i].getHopid() + "\t\t" + Integer.toString((rte[i].getDist())));
		}
		System.out.println("*******************************************************");
	}
}
