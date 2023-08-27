package ai.ignosis.entities;

import jakarta.persistence.*;

@Entity
public class AccountAggregatorBanks {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @ManyToOne
    @JoinColumn(name = "agId", referencedColumnName = "id")
    private AccountAggregator aggregator;

    @ManyToOne
    @JoinColumn(name = "bId", referencedColumnName = "bankId")
    private Bank bank;

    private boolean globalStatus = true;

	public AccountAggregatorBanks() {
		super();
	}

	public AccountAggregatorBanks(int id, AccountAggregator aggregator, Bank bank, boolean globalStatus) {
		super();
		this.id = id;
		this.aggregator = aggregator;
		this.bank = bank;
		this.globalStatus = globalStatus;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public boolean isGlobalStatus() {
		return globalStatus;
	}

	public void setGlobalStatus(boolean globalStatus) {
		this.globalStatus = globalStatus;
	}

	@Override
	public String toString() {
		return "AccountAggregatorBanks [id=" + id + ", aggregator=" + aggregator + ", bank=" + bank + ", globalStatus="
				+ globalStatus + "]";
	}
    
}



