package item.dto;

public class CartDTO { // 장바구니 정보 DTO

	private String item_itemSeq;
	private String user_userSeq;
	private String itemQuantity;

	public CartDTO(String item_itemSeq, String user_userSeq, String itemQuantity) {
		this.item_itemSeq = item_itemSeq;
		this.user_userSeq = user_userSeq;
		this.itemQuantity = itemQuantity;
	}

	public String getItem_itemSeq() {
		return this.item_itemSeq;
	}

	public void setItem_itemSeq(String item_itemSeq) {
		this.item_itemSeq = item_itemSeq;
	}

	public String getUser_userSeq() {
		return this.user_userSeq;
	}

	public void setUser_userSeq(String user_userSeq) {
		this.user_userSeq = user_userSeq;
	}

	public String getItemQuantity() {
		return this.itemQuantity;
	}

	public void setItemQuantity(String itemQuantity) {
		this.itemQuantity = itemQuantity;
	}

}
