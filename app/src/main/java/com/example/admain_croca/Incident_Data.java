package com.example.admain_croca;

public class Incident_Data {
    private String vehicleCount;
    private String accidentNumber;
    private String trafficControl;
    private String pedestrianControl;
    private String serialNumber;
    private String speedLimits;
    private String accidentType;
    private String secondaryAccidentType;
    private String accidentShape;
    private String injuredCount;
    private String roadDirections;
    private String laneCount;
    private String roadSurfaceType;
    private String weatherCondition;
    private String lighting;
    private String policeDepartment;
    private String securityCenter;
    private String coordinates;

    // Constructor
    public Incident_Data(String vehicleCount, String accidentNumber, String trafficControl,
                         String pedestrianControl, String serialNumber, String speedLimits,
                         String accidentType, String secondaryAccidentType, String accidentShape,
                         String injuredCount, String roadDirections, String laneCount,
                         String roadSurfaceType, String weatherCondition, String lighting,
                         String policeDepartment, String securityCenter, String coordinates) {
        this.vehicleCount = vehicleCount;
        this.accidentNumber = accidentNumber;
        this.trafficControl = trafficControl;
        this.pedestrianControl = pedestrianControl;
        this.serialNumber = serialNumber;
        this.speedLimits = speedLimits;
        this.accidentType = accidentType;
        this.secondaryAccidentType = secondaryAccidentType;
        this.accidentShape = accidentShape;
        this.injuredCount = injuredCount;
        this.roadDirections = roadDirections;
        this.laneCount = laneCount;
        this.roadSurfaceType = roadSurfaceType;
        this.weatherCondition = weatherCondition;
        this.lighting = lighting;
        this.policeDepartment = policeDepartment;
        this.securityCenter = securityCenter;
        this.coordinates = coordinates;
    }

    public String getVehicleCount() {
        return vehicleCount;
    }

    public void setVehicleCount(String vehicleCount) {
        this.vehicleCount = vehicleCount;
    }

    public String getAccidentNumber() {
        return accidentNumber;
    }

    public void setAccidentNumber(String accidentNumber) {
        this.accidentNumber = accidentNumber;
    }

    public String getTrafficControl() {
        return trafficControl;
    }

    public void setTrafficControl(String trafficControl) {
        this.trafficControl = trafficControl;
    }

    public String getPedestrianControl() {
        return pedestrianControl;
    }

    public void setPedestrianControl(String pedestrianControl) {
        this.pedestrianControl = pedestrianControl;
    }

    public String getSerialNumber() {
        return serialNumber;
    }

    public void setSerialNumber(String serialNumber) {
        this.serialNumber = serialNumber;
    }

    public String getSpeedLimits() {
        return speedLimits;
    }

    public void setSpeedLimits(String speedLimits) {
        this.speedLimits = speedLimits;
    }

    public String getAccidentType() {
        return accidentType;
    }

    public void setAccidentType(String accidentType) {
        this.accidentType = accidentType;
    }

    public String getSecondaryAccidentType() {
        return secondaryAccidentType;
    }

    public void setSecondaryAccidentType(String secondaryAccidentType) {
        this.secondaryAccidentType = secondaryAccidentType;
    }

    public String getAccidentShape() {
        return accidentShape;
    }

    public void setAccidentShape(String accidentShape) {
        this.accidentShape = accidentShape;
    }

    public String getInjuredCount() {
        return injuredCount;
    }

    public void setInjuredCount(String injuredCount) {
        this.injuredCount = injuredCount;
    }

    public String getRoadDirections() {
        return roadDirections;
    }

    public void setRoadDirections(String roadDirections) {
        this.roadDirections = roadDirections;
    }

    public String getLaneCount() {
        return laneCount;
    }

    public void setLaneCount(String laneCount) {
        this.laneCount = laneCount;
    }

    public String getRoadSurfaceType() {
        return roadSurfaceType;
    }

    public void setRoadSurfaceType(String roadSurfaceType) {
        this.roadSurfaceType = roadSurfaceType;
    }

    public String getWeatherCondition() {
        return weatherCondition;
    }

    public void setWeatherCondition(String weatherCondition) {
        this.weatherCondition = weatherCondition;
    }

    public String getLighting() {
        return lighting;
    }

    public void setLighting(String lighting) {
        this.lighting = lighting;
    }

    public String getPoliceDepartment() {
        return policeDepartment;
    }

    public void setPoliceDepartment(String policeDepartment) {
        this.policeDepartment = policeDepartment;
    }

    public String getSecurityCenter() {
        return securityCenter;
    }

    public void setSecurityCenter(String securityCenter) {
        this.securityCenter = securityCenter;
    }

    public String getCoordinates() {
        return coordinates;
    }

    public void setCoordinates(String coordinates) {
        this.coordinates = coordinates;
    }

}
