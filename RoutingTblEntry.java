
public class RoutingTblEntry extends RoutingPair{
	private String dest_net_id = "";
	
	public RoutingTblEntry() {
		
	}
	
	public void modify(String dest_net_id, String hopid, int distance) {
		super.modify(hopid, distance);
		this.dest_net_id = dest_net_id;
	}
	
	public RoutingTblEntry modify(String dest_net_id, RoutingPair rp) {
		this.dest_net_id = dest_net_id;
		super.modify(rp.getHopid(), rp.getDist());
		return this;
	}
	
	public String getDestnetid() {
		return dest_net_id;
	}
}
