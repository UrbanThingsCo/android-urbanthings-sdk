package io.urbanthings.datamodel;

import java.util.Date;

public class ResourceStatus {
    public String primaryCode;
    public Date timeStamp;
    public String statusText;
    public int availablePlaces;
    public int takenPlaces;
    public boolean isClosed;
    public int customStatusCode;
    @VehicleType.Enum
    public int vehicleType;
}
