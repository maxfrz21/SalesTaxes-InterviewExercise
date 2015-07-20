package src.durando.utils;

import java.math.BigDecimal;
import java.util.ArrayList;

import src.durando.server.vo.GoodsVO;

/**
 * @author Massimo Durando
 *
 */

public class SalesUtils {

	/**
	 * Calculate the basic sales tax and the import duty for each goods. 
	 * @author Massimo Durando
	 * @param  goods
	 * @return the input list with the the tax calcultated for each input product.
	 *
	 */
	public static ArrayList<GoodsVO> calculateTax (ArrayList<GoodsVO> goods)
	{
		for(GoodsVO item: goods)
		{
			BigDecimal taxedAmount = new BigDecimal("0.00");
			if(item.isTaxApplicable())
			{
				taxedAmount = taxesRoundUp(item.getPrice().multiply(new BigDecimal("0.10")));
				item.setTaxedPrice(taxesRoundUp(item.getPrice().add(taxedAmount)));
			}
			else
			{
				item.setTaxedPrice(taxesRoundUp(item.getPrice().add(taxedAmount)));
			}
			if(item.isImported())
			{
				taxedAmount = taxedAmount.add(taxesRoundUp(item.getPrice().multiply(new BigDecimal("0.05"))));
				item.setTaxedPrice(taxesRoundUp(item.getPrice().add(taxedAmount)));
			}
		}
		return goods;
	}
	
	/**
	 * Print the receipt, including description and taxed price for each receipt's goods.
	 * At the end this method print the totals about sales taxes and total taxed price.
	 *    
	 * @author Massimo Durando
	 * @param  receipt
	 * @return Nothing.
	 *
	 */
	public static void printOutput(ArrayList<GoodsVO> receipt)
	{
		BigDecimal salesTaxes = new BigDecimal("0");
		BigDecimal total = new BigDecimal("0");
		for(GoodsVO item: receipt)
		{
			salesTaxes = salesTaxes.add(taxesRoundUp(item.getTaxedPrice().subtract(item.getPrice())));
			total = total.add(item.getTaxedPrice());
			System.out.println(describeItem(item));
		}
		System.out.println("Sales Taxes: "+taxesRoundUp(salesTaxes));
		System.out.println("Total: "+total.setScale(2, BigDecimal.ROUND_HALF_UP));
	}
	
	/**
	 * Rounds the input tax according to the rules.
	 * The rounding rules for sales tax are that for a tax rate of n%, a shelf price of p contains (np/100 rounded up to the nearest 0.05) amount of sales tax.
	 * Example:
	 * 12.63 -> 12.65
	 * 12.67 -> 12.67
	 *    
	 * @author Massimo Durando
	 * @param  val
	 * @return rounded value.
	 *
	 */
	public static BigDecimal taxesRoundUp(BigDecimal val)
	{
		BigDecimal retVal = val.setScale(2, BigDecimal.ROUND_HALF_UP);
		char c = retVal.toString().charAt(retVal.toString().length()-1);
		if(Character.getNumericValue(c) <5 && Character.getNumericValue(c) > 0)
		{
			BigDecimal roundFactor = new BigDecimal("0.05");
			val = val.divide(roundFactor);
			val= new BigDecimal(Math.ceil(val.doubleValue()));
			val = val.multiply(roundFactor);
			return val;
		}
		else
		{
			return retVal;
		}
	}
	
	private static String describeItem (GoodsVO item)
	{
		return item.getQty() + " " + item.getName() + " : " + SalesUtils.taxesRoundUp(item.getTaxedPrice());
	}
	
	
	
	
}
