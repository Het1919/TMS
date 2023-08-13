package ai.ignosis.entities;

public class Bank {

	private String bankName;
	private boolean status;
	private boolean globalStatus;
	
	public boolean isGlobalStatus() {
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
	public boolean isStatus() {
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
		return "Bank [bankName=" + bankName + ", status=" + status + "]";
	}
	public Bank(String bankName, boolean status, boolean globalStatus) {
		super();
		this.bankName = bankName;
		this.status = status;
		this.globalStatus = globalStatus;
	}
	
	
	
	
}