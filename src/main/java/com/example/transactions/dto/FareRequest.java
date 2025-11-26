package com.example.transactions.dto;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

public class FareRequest {
    @NotNull
    @Min(0)
    private Double distanceKm;

    @NotNull
    @Min(0)
    private Double durationMin;

    private Double surge = 1.0;

    public FareRequest() {}

    public Double getDistanceKm() { return distanceKm; }
    public void setDistanceKm(Double distanceKm) { this.distanceKm = distanceKm; }

    public Double getDurationMin() { return durationMin; }
    public void setDurationMin(Double durationMin) { this.durationMin = durationMin; }

    public Double getSurge() { return surge; }
    public void setSurge(Double surge) { this.surge = surge; }
}
