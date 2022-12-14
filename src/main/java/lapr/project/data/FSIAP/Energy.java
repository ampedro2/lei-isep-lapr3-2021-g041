package lapr.project.data.FSIAP;

import lapr.project.controller.App;
import lapr.project.model.Container;


import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;


public class Energy {


    private final int EXT_TEMP;
    private final String tripTime;
    private double heatFlow;
    private double energy;
    private double totalEnergy = 0;
    private double pot = 75000;
    private int seconds;
    private List<Container> containerList;
    private String data = "";
    private int sun1;
    private int sun2;


    public Energy(){
        EXT_TEMP = 20;
        tripTime = "2:30:00";
        containerList = App.getInstance().getCompany().getContainerStore().getContainerList();
    }

    public Energy(int temp, String tripTime){
        EXT_TEMP = temp;
        this.tripTime = tripTime;
        containerList = App.getInstance().getCompany().getContainerStore().getContainerList();
    }

    public Energy(int temp, String tripTime, int sun1, int sun2){
        EXT_TEMP = temp;
        this.tripTime = tripTime;
        containerList = App.getInstance().getCompany().getContainerStore().getContainerList();
        this.sun1 = sun1;
        this.sun2 = sun2;
    }

    public String energyToSupply(int us){
        String [] time = tripTime.split(":");
        seconds = Integer.parseInt(time[0])*3600 + Integer.parseInt(time[1])*60 + Integer.parseInt(time[2]);

            for (Container c: containerList){
                    heatFlow = c.getArea() * (EXT_TEMP - c.getTemp()) / ((c.getL1()/c.getK1())+(c.getL2()/c.getK2())+(c.getL3()/c.getK3()));

                    energy = heatFlow * seconds;
                    totalEnergy += energy;
                    data+=String.format("Energy needed to supply for container %s: %.2f J\n", c.getContainerId(), energy);
                }

        if (us == 13){
            data+=String.format("\nTotal energy to be supplied to the set of containers: %.2f J\n", totalEnergy);
        }
        return data;
    }

    public String exposedSidesEnergy() {
        String [] time = tripTime.split(":");
        seconds = Integer.parseInt(time[0])*3600 + Integer.parseInt(time[1])*60 + Integer.parseInt(time[2]);
        int exposedSides = 3;
        Container[][][] disp = App.getInstance().getCompany().getContainerStore().getDisp();

        for (Container c: App.getInstance().getCompany().getContainerStore().getContainerList()) {
            if (c != null) {
                if (sun1 == 1 && sun2 == 1) {
                    if (c.getX() > 0 && disp[c.getX() - 1][c.getY()][c.getZ()] != null) {
                        exposedSides--;
                    }
                    if (c.getY() > 0 && disp[c.getX()][c.getY() - 1][c.getZ()] != null) {
                        exposedSides--;
                    }
                    if (c.getZ() < disp.length - 1 && (disp[c.getX()][c.getY()][c.getZ() + 1] != null)) {
                        exposedSides--;
                    }
                } else if (sun1 == 1 && sun2 == 2) {
                    if (c.getX() > 0 && disp[c.getX() - 1][c.getY()][c.getZ()] != null) {
                        exposedSides--;
                    }
                    if (c.getY() < disp.length - 1 && disp[c.getX()][c.getY() + 1][c.getZ()] != null) {
                        exposedSides--;
                    }
                    if (c.getZ() < disp.length - 1 && disp[c.getX()][c.getY()][c.getZ() + 1] != null) {
                        exposedSides--;
                    }
                } else if (sun1 == 2 && sun2 == 2) {
                    if (c.getX() < disp.length - 1 && disp[c.getX() + 1][c.getY()][c.getZ()] != null) {
                        exposedSides--;
                    }
                    if (c.getY() < disp.length - 1 && disp[c.getX()][c.getY() + 1][c.getZ()] != null) {
                        exposedSides--;
                    }
                    if (c.getZ() < disp.length - 1 && disp[c.getX()][c.getY()][c.getZ() + 1] != null) {
                        exposedSides--;
                    }
                } else if (sun1 == 2 && sun2 == 1) {
                    if (c.getX() < disp.length - 1 && disp[c.getX() + 1][c.getY()][c.getZ()] != null) {
                        exposedSides--;
                    }
                    if (c.getY() > 0 && disp[c.getX()][c.getY() - 1][c.getZ()] != null) {
                        exposedSides--;
                    }
                    if (c.getZ() < disp.length - 1 && disp[c.getX()][c.getY()][c.getZ() + 1] != null) {
                        exposedSides--;
                    }
                }
            }

            heatFlow = exposedSides * c.getArea() * (EXT_TEMP - c.getTemp()) / ((c.getL1() / c.getK1()) + (c.getL2() / c.getK2()) + (c.getL3() / c.getK3()));


            energy = heatFlow * seconds;
            totalEnergy += energy;
            data += String.format("Energy needed to supply for container %s with %d side(s) exposed to the sun: %.2f J\n", c.getContainerId(), exposedSides, energy);

            exposedSides = 3;
        }

        return data;

    }


    public String auxiliaryPowerEquipment(){
        String data2 = "";
        exposedSidesEnergy();
        heatFlow = totalEnergy/seconds;
        double x = heatFlow/pot;

        data2+=String.format("Number of auxiliar power equipment needed for the voyage: %.0f\n", x+1);

        return data2;
    }
}

