package ai.ignosis.entities;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.*;

@Entity
public class AccountAggregator {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	private String name;
	
	@ManyToMany(mappedBy="accountAggregators")
	private Set<Tenant> tenants = new HashSet<>();
	
	@OneToMany(mappedBy = "aggregator")
	private Set<AccountAggregatorBanks> accountAggregatorBanks = new HashSet<>();
	
	public AccountAggregator() {
		super();
	}
	
	public AccountAggregator(String name) {
		this.name = name;
	}
	
	public AccountAggregator(int id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
	public AccountAggregator(AccountAggregator aggregator) {
		this.id = aggregator.id;
		this.name = aggregator.name;
	}

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

	@Override
	public String toString() {
		return "AccountAggregator [id=" + id + ", name=" + name + ", tenants=" + tenants + ", accountAggregatorBanks="
				+ accountAggregatorBanks + "]";
	}
	
	
}
