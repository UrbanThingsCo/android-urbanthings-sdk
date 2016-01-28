package io.urbanthings.datamodel;

import java.util.Date;

public class ResourceStatus {
    public String primaryCode;
    public Date timeStamp;
    public String statusText;
    public int availablePlaces;
    public int takenPlaces;
    public boolean isClosed;
    @ResourceTrendType.Enum
    public int trend;
    public int customStatusCode;
    public String attributionLabel;
    public String attributionImageURL;
    @VehicleType.Enum
    public int vehicleType;
}
