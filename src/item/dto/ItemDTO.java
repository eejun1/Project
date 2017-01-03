package item.dto;

public class ItemDTO { // 아이템 정보 DTO

	private String itemSeq;
	private String itemName;
	private String itemPrice;
	private String itemCategory;
	private String itemSrc;
	private String user_userSeq;
	private String itemQuantity;
	private String itemTitleSrc;
	private String itemContent;

	public ItemDTO(String itemSeq, String itemName, String itemPrice, String itemCategory, String user_userSeq, String itemQuantity, String itemTitleSrc,
	 String itemContent,String itemSrc) {

		this.itemSeq = itemSeq;
		this.itemName = itemName;
		this.itemPrice = itemPrice;
		this.itemCategory = itemCategory;
		this.itemSrc = itemSrc;
		this.user_userSeq = user_userSeq;
		this.itemQuantity = itemQuantity;
		this.itemTitleSrc = itemTitleSrc;
		this.itemContent = itemContent;
	}

	public String getItemSeq() {
		return this.itemSeq;
	}

	public void setItemSeq(String itemSeq) {
		this.itemSeq = itemSeq;
	}

	public String getItemName() {
		return this.itemName;
	}

	public void setItemName(String itemName) {
		this.itemName = itemName;
	}

	public String getItemPrice() {
		return this.itemPrice;
	}

	public void setItemPrice(String itemPrice) {
		this.itemPrice = itemPrice;
	}

	public String getItemCategory() {
		return this.itemCategory;
	}

	public void setItemCategory(String itemCategory) {
		this.itemCategory = itemCategory;
	}

	public String getItemSrc() {
		return this.itemSrc;
	}

	public void setItemSrc(String itemSrc) {
		this.itemSrc = itemSrc;
	}

	public String getUser_UserSeq() {
		return this.user_userSeq;
	}

	public void setUser_UserSeq(String user_userSeq) {
		this.user_userSeq = user_userSeq;
	}

	public String getItemQuantity() {
		return this.itemQuantity;
	}

	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

	public String getItemTitleSrc() {
		return this.itemTitleSrc;
	}

	public void setItemTitleSrc(String itemTitleSrc) {
		this.itemTitleSrc = itemTitleSrc;
	}

	public String getItemContent() {
		return this.itemContent;
	}

	public void setItemContent(String itemContent) {
		this.itemContent = itemContent;
	}

}
