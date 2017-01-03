package member.model.service;

import java.sql.SQLException;
import java.util.ArrayList;

import member.dto.MemberDTO;
import member.exception.DuplicatedUserNameException;
import member.exception.DuplicatedEmailException;
import member.model.dao.MemberDAO;

public class MemberManageService {
	private static MemberManageService instance;
	private MemberDAO memberDAO;

	private MemberManageService() {
		memberDAO = MemberDAO.getInstance();
	}

	public static MemberManageService getInstance() {
		if (instance == null) {
			instance = new MemberManageService();
		}
		return instance;
	}

	public void registerMember(MemberDTO memberDTO)
			throws SQLException, DuplicatedUserNameException, DuplicatedEmailException {

		if (memberDAO.selectMemberByEmail(memberDTO.getEmail()) != null) {
			throw new DuplicatedEmailException("이메일 주소 중복 : " + memberDTO.getEmail());
		}

		if (memberDAO.selectMemberByUserName(memberDTO.getUserName()) != null) {
			throw new DuplicatedUserNameException("사용자 이름 중복 : " + memberDTO.getUserName());
		}

		memberDAO.insertMember(memberDTO);

	}

	public ArrayList<MemberDTO> getMemberList() throws SQLException {
		return memberDAO.selectAllMember();
	}

	public MemberDTO selectMemberByEmail(String email) throws SQLException {
		return memberDAO.selectMemberByEmail(email);
	}

	public void modifyMember(MemberDTO memberDTO) throws SQLException, DuplicatedUserNameException {

//		if (memberDAO.selectMemberByUserName(memberDTO.getUserName()) != null) {
//			throw new DuplicatedUserNameException("사용자 이름 중복 : " + memberDTO.getUserName());
//		} //여기서 걸게 아니라 다른곳에서 걸어야쓰것는데

		memberDAO.updateMemberByEmail(memberDTO);

	}

	public void removeMemberByEmail(String email) throws SQLException {
		memberDAO.deleteMemberByEmail(email);

	}

	public void removeMemberByUserSeq(String userSeq) throws SQLException {
		memberDAO.deleteMemberByUserSeq(userSeq);
		
	}
}
