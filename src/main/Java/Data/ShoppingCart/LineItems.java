package Data.ShoppingCart;

import Data.cupcakes.Cupcake;

public class LineItems
{
	private int     quantity;
	private int invoice_id;
	private Cupcake cupcake;

	public LineItems(int quantity, Cupcake cupcake) {
		this.quantity = quantity;
		this.cupcake = cupcake;
	}

	public int getQuantity()
	{
		return this.quantity;
	}

	public int getInvoice_id()
	{
		return this.invoice_id;
	}

	public void createInvoice() {

	}


	public Cupcake getCupcake()
	{
		return this.cupcake;
	}

	@Override
	public String toString() {
		return "" + cupcake +", quantity: " + quantity;
	}
}
