package ai.ignosis.dto;

public class BankLocalStatusDao {
	
	private int tId;
	private int agId;
	private int bId;
	private boolean status;
	
	public BankLocalStatusDao() {
		super();
	}
	
	public BankLocalStatusDao(int tId, int agId, int bId, boolean status) {
		super();
		this.tId = tId;
		this.agId = agId;
		this.bId = bId;
		this.status = status;
	}
	
	public int gettId() {
		return tId;
	}
	public void settId(int tId) {
		this.tId = tId;
	}
	public int getAgId() {
		return agId;
	}
	public void setAgId(int agId) {
		this.agId = agId;
	}
	public int getbId() {
		return bId;
	}
	public void setbId(int bId) {
		this.bId = bId;
	}
	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return "BankLocalStatusDao [tId=" + tId + ", agId=" + agId + ", bId=" + bId + ", status=" + status + "]";
	}
	
	

}
