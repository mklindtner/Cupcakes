package Data.cupcakes;

public class Bottom
{
	private int cost, id;
	private String bottomFlavor;

	public Bottom(int id, int cost, String bottomFlavor) {
		this.id = id;
		this.cost = cost;
		this.bottomFlavor = bottomFlavor;
	}

	public String getBottomFlavor() {
		return bottomFlavor;
	}

	public int getCost()
	{
		return this.cost;
	}

	public int getId()
	{
		return this.id;
	}

	@Override
	public String toString() {
		return "id: " + id + ", flavor: " + bottomFlavor + ", cost:" + cost + "\n";
	}
}
