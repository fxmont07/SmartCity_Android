package com.example.smartcity_android.data.model;

import com.example.smartcity_android.tool.AnswerCriterion;

public class OfferCriterion {

    private Offer offer;
    private Criterion criterion;
    private AnswerCriterion answerCriterion;

    public OfferCriterion(Offer offer, Criterion criterion, AnswerCriterion answerCriterion) {
        this.offer = offer;
        this.criterion = criterion;
        this.answerCriterion = answerCriterion;
    }

    public Offer getOffer() {
        return offer;
    }

    public Criterion getCriterion() {
        return criterion;
    }

    public AnswerCriterion getAnswerCriterion() {
        return answerCriterion;
    }
}
