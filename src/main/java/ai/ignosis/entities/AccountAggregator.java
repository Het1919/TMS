package ai.ignosis.entities;

import java.util.Set;

public class AccountAggregator {
	
	private int id;
	private String name;
	private Set<Bank> banks;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Set<Bank> getBanks() {
		return banks;
	}
	public void setBanks(Set<Bank> banks) {
		this.banks = banks;
	}
	@Override
	public String toString() {
		return "AccountAggregator [id=" + id + ", name=" + name + ", banks=" + banks + "]";
	}
	public AccountAggregator(int id, String name, Set<Bank> banks) {
		super();
		this.id = id;
		this.name = name;
		this.banks = banks;
	}
	public AccountAggregator() {
		super();
	}
	

}
