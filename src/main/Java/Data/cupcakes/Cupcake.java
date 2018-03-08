package Data.cupcakes;

public class Cupcake
{
	private Topping top;
	private Bottom  bot;

	public Cupcake(Topping top, Bottom bot) {
		this.top = top;
		this.bot = bot;
	}

	public int getTotalPrice()
	{
		return top.getCost() + bot.getCost();
	}

	public Bottom getBot() {
		return bot;
	}

	public Topping getTop() {
		return top;
	}

	public int getBotID() {
		return bot.getId();
	}

	public int getTopID() {
		return top.getId();
	}

	public String getBotFlavor()
	{
		return bot.getBottomFlavor();
	}

	public String getTopFlavor()
	{
		return top.getTopFlavor();
	}

	@Override
	public String toString() {
		return getTopFlavor() + "-" + getBotFlavor();
	}
}
