package item.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import item.dto.CartDTO;
import item.dto.ItemDTO;
import item.exception.DuplicatedItemException;
import item.model.dao.CartDAO;
import item.model.dao.ItemDAO;

public class ItemManageService {
	private static ItemManageService instance;
	private ItemDAO itemDAO;
	private CartDAO cartDAO;

	private ItemManageService() {
		itemDAO = ItemDAO.getInstance();
		cartDAO = CartDAO.getInstance();
	}

	public static ItemManageService getInstance() {
		if (instance == null) {
			instance = new ItemManageService();
		}
		return instance;
	}

	public void addToCart(CartDTO cartDTO) throws SQLException, DuplicatedItemException {
		if ((cartDAO.checkDuplicatedItem(cartDTO.getItem_itemSeq(), cartDTO.getUser_userSeq())) != null) {
			throw new DuplicatedItemException("장바구니에 중복되는 아이템 존재");
		}
		
		cartDAO.addToCart(cartDTO);
	}

	public CartDTO checkDuplicatedItem(String itemSeq, String userSeq) throws SQLException {
		return cartDAO.checkDuplicatedItem(itemSeq, userSeq);
	}

	public void postItem(ItemDTO itemDTO) throws SQLException {
		itemDAO.insertItem(itemDTO);
	}

	public void deleteItemByItemSeq(String itemSeq) throws SQLException {
		itemDAO.deleteItemByItemSeq(itemSeq);
	}

	public void updateItemByItemSeq(ItemDTO itemDTO) throws SQLException {
		itemDAO.updateItemByItemSeq(itemDTO);
	}

	public ArrayList<ItemDTO> getItemList() throws SQLException {
		return itemDAO.selectAllItem();
	}

	public ItemDTO selectItemByItemSeq(String itemSeq) throws SQLException {
		return itemDAO.selectItemByItemSeq(itemSeq);
	}
}
