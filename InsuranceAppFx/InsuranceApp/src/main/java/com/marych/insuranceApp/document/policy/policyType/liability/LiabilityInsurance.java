package com.marych.insuranceApp.document.policy.policyType.liability;

import com.marych.insuranceApp.document.policy.InsurancePolicy;

public abstract class LiabilityInsurance extends InsurancePolicy {

    public LiabilityInsurance(int policyId, boolean compulsory, int holderId, int insurerId, int companyId) {
        super(policyId, compulsory, holderId, insurerId, companyId);
    }
}
