package src.durando;

import java.math.BigDecimal;
import java.util.ArrayList;

import src.durando.server.vo.GoodsVO;
import src.durando.utils.SalesUtils;

/**
 * 
 * For the solution of this Interview exercise I suppose that Input Objects are populated from a web form that in the server side create GoodsVO objects.
 * GoodsVO is the goods value object and his constructor is only one: in this way i can prepare the receipt list with all information.  
 * The more important method is calculateTax, implemented on the SalesUtils. For this example i choosed to create a simple utils class, but in "production mode"
 * this method for me should be implemented sure as web service .
 * After I'll simply print out the calculated list.
 * 
 * @author Massimo Durando
 * @version 1.0
 * @since   2015-07-19 
 *
 */


public class Main {

	public static void main(String[] args) {
		
		ArrayList<GoodsVO> receipt = new ArrayList<GoodsVO>();
		receipt.add(new GoodsVO("book"          , new BigDecimal("12.49"), GoodsVO.Category.BOOK, false));
		receipt.add(new GoodsVO("music CD"      , new BigDecimal("14.99"), GoodsVO.Category.OTHER, false));
		receipt.add(new GoodsVO("chocolate bar" , new BigDecimal("0.85"), GoodsVO.Category.FOOD, false));
		receipt = SalesUtils.calculateTax(receipt); 
		System.out.println("\n\nOutpout 1:");
		SalesUtils.printOutput(receipt);
		
		receipt = new ArrayList<GoodsVO>();
		receipt.add(new GoodsVO("imported box of chocolates"  , new BigDecimal("10.00"), GoodsVO.Category.FOOD, true));
		receipt.add(new GoodsVO("imported bottle of perfume"  , new BigDecimal("47.50"), GoodsVO.Category.OTHER, true));
		receipt = SalesUtils.calculateTax(receipt);
		System.out.println("\n\nOutpout 2:");
		SalesUtils.printOutput(receipt);
		
		receipt = new ArrayList<GoodsVO>();
		receipt.add(new GoodsVO("imported bottle of perfume"  , new BigDecimal("27.99"), GoodsVO.Category.OTHER, true));
		receipt.add(new GoodsVO("bottle of perfume"           , new BigDecimal("18.99"), GoodsVO.Category.OTHER, false));
		receipt.add(new GoodsVO("packet of headache pills"    , new BigDecimal("9.75") , GoodsVO.Category.MEDICAL_WITH_EXEMPTION, false));
		receipt.add(new GoodsVO("imported box of chocolates" , new BigDecimal("11.25"), GoodsVO.Category.FOOD, true));
		receipt = SalesUtils.calculateTax(receipt);
		System.out.println("\n\nOutpout 3:");
		SalesUtils.printOutput(receipt);
	}

	
}
