package Data.ShoppingCart;

import java.util.ArrayList;
import java.util.List;

public class ShoppingCart
{
	private List<LineItems> lineitems = new ArrayList<LineItems>();

	public ShoppingCart() {
	}

	public void add(LineItems lItem) {
		lineitems.add(lItem);
	}

	public String showFirstItem() {
		return lineitems.get(0).getCupcake().getTopFlavor();
	}

	public List<LineItems> getList() {
		return lineitems;
	}

	public LineItems getItem(int index) {
		return lineitems.get(index);
	}

	public void clearShoppingCart() {
		lineitems.clear();
	}


}
