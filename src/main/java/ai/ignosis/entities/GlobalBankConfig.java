package ai.ignosis.entities;

import java.util.Map;

public class GlobalBankConfig {
    private Map<String, Boolean> bankStatusMap;

    public GlobalBankConfig(Map<String, Boolean> bankStatusMap) {
        this.bankStatusMap = bankStatusMap;
    }

    public Map<String, Boolean> getBankStatusMap() {
        return bankStatusMap;
    }

    public void setBankStatusMap(Map<String, Boolean> bankStatusMap) {
        this.bankStatusMap = bankStatusMap;
    }
}

