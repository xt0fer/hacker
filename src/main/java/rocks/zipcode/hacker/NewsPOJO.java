package rocks.zipcode.hacker;

public class NewsPOJO {
    private final long id;
	private final String content;

	public NewsPOJO(long id, String content) {
		this.id = id;
		this.content = content;
	}

	public long getId() {
		return id;
	}

	public String getContent() {
		return content;
	}
}
