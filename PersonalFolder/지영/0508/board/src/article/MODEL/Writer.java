package article.MODEL;

//p630
//WriteRequest 클래스에서 사용하는 데이터를 제공하는 용도의 클래스

public class Writer {
	private String id;	//writer_id 컬럼용
	private String name;//writer_name 컬럼용
	
	public Writer(String id, String name) {
		this.id = id;
		this.name = name;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Writer [id=" + id + ", name=" + name + "]";
	}
	
}
