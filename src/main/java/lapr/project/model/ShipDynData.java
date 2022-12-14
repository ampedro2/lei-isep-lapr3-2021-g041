package lapr.project.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ShipDynData implements Comparable<ShipDynData> {

    private final Date baseDateTime;
    private final String latitude;
    private final String longitude;
    private final float sog;
    private final float cog;
    private final String heading;
    private int position;
    private final String cargo;
    private final String transceiver;

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    public ShipDynData(String baseDateTime, String latitude, String longitude, String sog, String cog, String heading, String cargo, String transceiver) throws ParseException {
        this.baseDateTime = new SimpleDateFormat("dd/MM/yyyy HH:mm").parse(baseDateTime);

        if (Float.parseFloat(latitude) < -90 || Float.parseFloat(latitude) > 90)
            this.latitude = "NA";
        else
            this.latitude = latitude;

        if (Float.parseFloat(longitude) < -180 || Float.parseFloat(longitude) > 180)
            this.longitude = "NA";
        else
            this.longitude = longitude;

        this.sog = Float.parseFloat(sog);

        if (Double.parseDouble(cog) < 0)
            this.cog = Float.parseFloat(cog) + 360;
        else if (Double.parseDouble(cog) > 359)
            this.cog = Float.parseFloat(cog) - 360;
        else this.cog = Float.parseFloat(cog);

        if (Integer.parseInt(heading) < 0 || Integer.parseInt(heading) > 359)
            this.heading = "NA";
        else
            this.heading = heading;

        this.cargo = cargo;
        this.transceiver = transceiver;
    }
    
    public float getSog() {
        return sog;
    }

    public float getCog() {
        return cog;
    }

    public String getHeading() {
        return heading;
    }

    public Date getBaseDateTime() {
        return baseDateTime;
    }

    public int getPosition(){return position;}

    public String getCargo() {
        return cargo;
    }

    public String getTransceiver() {
        return transceiver;
    }

    @Override
    public String toString() {
        String pattern = "dd/MM/yyyy HH:mm";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        return simpleDateFormat.format(this.baseDateTime) + " | LATITUDE: " + this.latitude + "; " + "LONGITUDE: " + this.longitude;
    }
    @Override
    public int compareTo(ShipDynData o) {
            return 0;
    }


}

