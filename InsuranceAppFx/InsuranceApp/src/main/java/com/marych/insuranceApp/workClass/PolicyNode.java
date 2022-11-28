package com.marych.insuranceApp.workClass;

public class PolicyNode {
    private int derivativeId;
    private int policyId;

    public PolicyNode(int derivativeNo, int policyNo) {
        this.derivativeId = derivativeNo;
        this.policyId = policyNo;
    }

    public int getDerivativeId() {
        return derivativeId;
    }

    public int getPolicyId() {
        return policyId;
    }
}
