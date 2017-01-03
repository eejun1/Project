package item.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import item.dto.ForwardDTO;

public interface Controller {
	ForwardDTO execute(HttpServletRequest request, HttpServletResponse response) throws Exception;
}
