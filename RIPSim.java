
public class RIPSim {
	public static void main(String args[]) {
		String[] routers = {"R1", "R2", "R3", "R4", "R5", "R6"};
		String[] nets = {"N1", "N2", "N3", "N4", "N5", "N6", "N7"};
		int num_router = 6;
		Router[] router = new Router[6];
		
		// initialize
		for (int i = 0; i < num_router; i++) {
			router[i] = new Router(routers[i]);
			router[i].add(nets[i], "*", 1);
			router[i].add(nets[i + 1], "*", 1);
			router[i].printTable();
		}
		
		// Update tables repeatedly until converge
		while (true) {
			boolean flag = false;
			boolean flag1 = false;
			boolean flag2 = false;
			for (int i = 0; i < num_router; i++) {
				RoutingTblEntry[] rtes = router[i].getTables();
				
				if (i == 0) {
					flag1 = router[1].updateTable(rtes, router[i].getId());
					if (flag1) router[1].printTable();
					flag |= flag1;
				}
				else if (i == 5) {
					flag1 = router[4].updateTable(rtes, router[i].getId());
					if (flag1) router[4].printTable();
					flag |= flag1;
				}
				else {
					
					flag1 = router[i - 1].updateTable(rtes, router[i].getId());
					if (flag1) router[i - 1].printTable();
					flag2 = router[i + 1].updateTable(rtes, router[i].getId());
					if (flag2) router[i + 1].printTable();
					flag = flag | flag1 | flag2;
				}
			}
			if (!flag) break;
		}
		
		System.out.println("ASDFGHJKJHGFDFGHJK");
		for (int i = 0; i < num_router; i++) {			
			router[i].printTable();
		}
	}
}
