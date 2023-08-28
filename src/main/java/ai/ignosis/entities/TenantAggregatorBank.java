package ai.ignosis.entities;

import jakarta.persistence.*;

@Entity
public class TenantAggregatorBank {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "tId", referencedColumnName = "tenantId")
    private Tenant tenant;

    @ManyToOne
    @JoinColumn(name = "agId", referencedColumnName = "id")
    private AccountAggregator aggregator;

    @ManyToOne
    @JoinColumn(name = "bId", referencedColumnName = "bankId")
    private Bank bank;

    private boolean status;

	public TenantAggregatorBank() {
		super();
	}

	public TenantAggregatorBank(int id, Tenant tenant, AccountAggregator aggregator, Bank bank, boolean status) {
		super();
		this.id = id;
		this.tenant = tenant;
		this.aggregator = aggregator;
		this.bank = bank;
		this.status = status;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}


	public Tenant getTenant() {
		return tenant;
	}
	public void setTenant(Tenant tenant) {
		this.tenant = tenant;
	}


	public AccountAggregator getAggregator() {
		return aggregator;
	}
	public void setAggregator(AccountAggregator aggregator) {
		this.aggregator = aggregator;
	}

	public Bank getBank() {
		return bank;
	}
	public void setBank(Bank bank) {
		this.bank = bank;
	}

	public boolean isStatus() {
		return status;
	}
	public void setStatus(boolean status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "TenantAggregatorBank [id=" + id + ", tenant=" + tenant + ", aggregator=" + aggregator + ", bank=" + bank
				+ ", status=" + status + "]";
	}

}
