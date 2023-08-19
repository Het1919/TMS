package ai.ignosis.entities;

public class Bank {

	private int bankId;
	private String bankName;
	private boolean status;
	private boolean globalStatus;
	
	
	public int getBankId() {
		return bankId;
	}
	public void setBankId(int bankId) {
		this.bankId = bankId;
	}
	public boolean getGlobalStatus() {
		return globalStatus;
	}
	public void setGlobalStatus(boolean globalStatus) {
		this.globalStatus = globalStatus;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public boolean getStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}
	public Bank(String bankName, boolean status) {
		super();
		this.bankName = bankName;
		this.status = status;
	}
	public Bank() {
		super();
		// TODO Auto-generated constructor stub
	}
	@Override
	public String toString() {
		return "Bank [bankId=" + bankId + ", bankName=" + bankName + ", status=" + status + ", globalStatus="
				+ globalStatus + "]";
	}
	public Bank(String bankName, boolean status, boolean globalStatus) {
		super();
		this.bankName = bankName;
		this.status = status;
		this.globalStatus = globalStatus;
	}
	
	public Bank(int bankId, String bankName, boolean status, boolean globalStatus) {
		super();
		this.bankId = bankId;
		this.bankName = bankName;
		this.status = status;
		this.globalStatus = globalStatus;
	}
	public Bank(Bank b) {
		this.bankId = b.bankId;
		this.bankName = b.bankName;
		this.globalStatus = b.globalStatus;
		this.status = b.status;
	}
	
	
	
	
	
	
}