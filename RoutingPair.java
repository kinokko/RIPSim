
public class RoutingPair {
	private String hopid = null;
	private int distance = -1;
	
	public RoutingPair() {
		
	}
	
	public void modify(String hopid, int distance) {
		this.hopid = hopid;
		this.distance = distance;
	}
	
	public String getHopid() {
		return hopid;
	}
	
	public int getDist() {
		return distance;
	}
}
