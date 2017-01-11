package board.dto;


public class BoardDTO {  //게시판 정보 DTO

    private int qnabdseq;
    private String qnabdtitle;
    private String qnabddate;
    private String qnabdcontent;
    private int qnabdpw;
    private int user_userseq;


    private String userName;

    public BoardDTO(String qnabdtitle, String qnabddate, String userName, int qnabdseq,int qnabdpw , String qnabdcontent,int user_userseq){
        this.setQnabdtitle(qnabdtitle);
        this.setQnabddate(qnabddate);
        this.setQnabdcontent(qnabdcontent);
        this.setQnabdpw(qnabdpw);
        this.setUserName(userName);
        this.setQnabdseq(qnabdseq);
        this.setUser_userseq(user_userseq);
    }

    public BoardDTO(String qnabdtitle, String qnabdcontent, int qnabdpw, int user_userseq){ //board 글 삽입때 사용
        this.setQnabdtitle(qnabdtitle);
        this.setQnabdcontent(qnabdcontent);
        this.setQnabdpw(qnabdpw);
        this.setUser_userseq(user_userseq);
    }
    public BoardDTO(String qnabdtitle,String qnabddate,String username,int qnabdseq){ //board 글 리스트 불러올때 사용
        this.setQnabdtitle(qnabdtitle);
        this.setQnabddate(qnabddate);
        this.setUserName(username);
        this.setQnabdseq(qnabdseq);
    }

    public BoardDTO(String qnabdtitle,int qnabdpw, String qnabdcontent){
        this.setQnabdtitle(qnabdtitle);
        this.setQnabdpw(qnabdpw);
        this.setQnabdcontent(qnabdcontent);
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getQnabdseq() {
        return qnabdseq;
    }

    public void setQnabdseq(int qnabdseq) {
        this.qnabdseq = qnabdseq;
    }

    public String getQnabdtitle() {
        return qnabdtitle;
    }

    public void setQnabdtitle(String qnabdtitle) {
        this.qnabdtitle = qnabdtitle;
    }

    public String getQnabddate() {
        return qnabddate;
    }

    public void setQnabddate(String qnabddate) {
        this.qnabddate = qnabddate;
    }

    public String getQnabdcontent() {
        return qnabdcontent;
    }

    public void setQnabdcontent(String qnabdcontent) {
        this.qnabdcontent = qnabdcontent;
    }

    public int getQnabdpw() {
        return qnabdpw;
    }

    public void setQnabdpw(int qnabdpw) {
        this.qnabdpw = qnabdpw;
    }

    public int getUser_userseq() {
        return user_userseq;
    }

    public void setUser_userseq(int user_userseq) {
        this.user_userseq = user_userseq;
    }
}
