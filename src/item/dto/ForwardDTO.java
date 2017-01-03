package item.dto;

public class ForwardDTO {
	private String url;
	private boolean redirect;

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
