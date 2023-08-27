package ai.ignosis.dto;

public class CreateBankDto {
	
	private String name;
	
	public CreateBankDto() {
		super();
	}
	public CreateBankDto(String name) {
		super();
		this.name = name;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	@Override
	public String toString() {
		return "CreateBankDto [name=" + name + "]";
	}

	

}
