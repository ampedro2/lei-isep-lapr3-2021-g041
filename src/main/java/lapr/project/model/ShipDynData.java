package lapr.project.model;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;

public class ShipDynData implements Comparable<ShipDynData> {

    private Date baseDateTime;
    private String latitude;
    private String longitude;
    private float sog;
    private float cog;
    private String heading;
    private int position;
    private String cargo;
    private String transceiver;

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

        if (Float.parseFloat(cog) < 0)
            this.cog = Float.parseFloat(cog) + 360;
        else if (Float.parseFloat(cog) > 359)
            this.cog = Float.parseFloat(cog) - 360;
        else this.cog = Float.parseFloat(cog);

        if (Integer.parseInt(heading) < 0 || Integer.parseInt(heading) > 359)
            this.heading = "NA";
        else
            this.heading = heading;

        this.position = position;
        this.cargo = cargo;
        this.transceiver = transceiver;
    }

    public Date getBaseDateTime() {
        return baseDateTime;
    }

    @Override
    public int hashCode() {
        return cargo.hashCode();
    }

    @Override
    public String toString() {
        return this.latitude + ";" + this.longitude;
    }

    @Override
    public int compareTo(ShipDynData o) {
        return 0;
    }
}

