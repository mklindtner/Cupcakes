package Data.cupcakes;

public class Topping
{
	private int cost, id;
	private String topFlavor;

	public Topping(int id, int cost, String topFlavor) {
		this.cost = cost;
		this.topFlavor = topFlavor;
		this.id = id;
	}

	public int getCost()
	{
		return cost;
	}

	public String getTopFlavor()
	{
		return this.topFlavor;
	}

	public int getId() {
		return this.id;
	}

	@Override
	public String toString() {
		return "id: "+id +", flavor: " + topFlavor + ", cost: " + cost;
	}
}
