package src.durando.server.vo;

import java.math.BigDecimal;

/**
 * @author Massimo Durando
 *
 */

public class GoodsVO {
	
	/*
	 * I choosed to use an enum Object because in this way i can define with precision each goods.
	 * */
	public static enum Category {BOOK, FOOD, MEDICAL_WITH_EXEMPTION, OTHER};
	
	private int qty = 1;
	private String name;
	private Category category;
	private boolean taxApplicable;
	private boolean imported;
	private BigDecimal price;
	private BigDecimal taxedPrice;
	
	public GoodsVO(String name, BigDecimal price, Category type, boolean isImported) {
		super();
		this.name = name;
		this.category = type;
		this.price = price;
		this.imported = isImported;
		this.taxApplicable = (category == Category.OTHER);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Category getCategory() {
		return category;
	}

	public boolean isTaxApplicable() {
		return taxApplicable;
	}


	public boolean isImported() {
		return imported;
	}

	public BigDecimal getPrice() {
		return price;
	}

	public void setPrice(BigDecimal price) {
		this.price = price;
	}


	public BigDecimal getTaxedPrice() {
		return taxedPrice;
	}

	public void setTaxedPrice(BigDecimal taxedPrice) {
		this.taxedPrice = taxedPrice;
	}

	public int getQty() {
		return qty;
	}

	public void setQty(int qty) {
		this.qty = qty;
	}
	
}
