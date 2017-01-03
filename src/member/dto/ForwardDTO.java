package member.dto;

public class ForwardDTO { // 서블릿 처리 후 리디렉트 경로 담는 객체
	private String url = "";
	private boolean redirect = true;

	public ForwardDTO(String url) {
		this.url = url;
		this.redirect = true;
	}

	public boolean isRedirect() {
		return redirect;
	}

	public String getUrl() {
		return this.url;
	}
	public void setUrl(String url) {
		this.url = url;
	}

	@Override
	public String toString() {
		return "ForwardDTO [url=" + url + ", redirect=" + redirect + "]";
	}
}