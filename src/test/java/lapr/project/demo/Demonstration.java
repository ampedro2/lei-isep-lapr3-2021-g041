package lapr.project.demo;

import lapr.project.controller.*;
import lapr.project.data.*;
import lapr.project.model.*;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;

class Demonstration {

    @Test
    void Demonstration() throws IOException, ParseException {
        App.getInstance().setCompany(new Company());
        ImportCountriesBordersSeadists icontroller = new ImportCountriesBordersSeadists();
        icontroller.importFromCSVCountry("countries.csv");
        ImportShipsController importShipsController = new ImportShipsController();
        importShipsController.importFromCSV("sships.csv");
        ImportPortsController importPortsController = new ImportPortsController();
        importPortsController.importFromCSV("bports.csv");
        SearchShipController searchShipController = new SearchShipController();
        searchShipController.searchDetails("Input/US102.txt");
        PositionalMessagesController positionalMessagesController = new PositionalMessagesController();
        positionalMessagesController.message("Input/US103.txt");
        searchShipController.makeSummary("Input/US104.txt");
        PrintingShipsInfoController printingShipsInfoController = new PrintingShipsInfoController();
        printingShipsInfoController.getShips();
        TopNshipsPerKmController topNshipsPerKmController = new TopNshipsPerKmController();
        topNshipsPerKmController.printNshipsMostKm("Input/US106.txt");
        PairController pairController = new PairController();
        pairController.pair();
        NearestPortController nearestPortController = new NearestPortController();
        nearestPortController.getClosestPort("Input/US202.txt");
        BuildFreightNetworkController bcontroller = new BuildFreightNetworkController();
        icontroller.importFromDatabaseCountries();
        icontroller.importFromDatabaseBorders();
        icontroller.importFromDatabaseSeadists();
        bcontroller.BuildFreightNetwork(1);
        ColourMapController ccontroller = new ColourMapController();
        ccontroller.ColourMap();
        ClosenessPlacesController cpc = new ClosenessPlacesController();
        cpc.closenessPlacesByContinent(3);
        CriticalPortsController criticalPortsController = new CriticalPortsController();
        criticalPortsController.centrality(6);
        ShortestPathController shortestPathController = new ShortestPathController();
        shortestPathController.shortestPath("Athens", "Paris", 3);
        ShortestDistanceGreatestLocationCircuitController sdlccontroller = new ShortestDistanceGreatestLocationCircuitController();
        sdlccontroller.getCircuit("Madrid");
    }

    @Test
    void US204() throws IOException, SQLException {
        US204_SQL sql = new US204_SQL();
        sql.demo(3058855);
    }

    @Test
    void US205() throws IOException, SQLException {
        US205_SQL sql = new US205_SQL();
        sql.demo(212180000);
    }

    @Test
    void US206() throws IOException, SQLException {
        US206_SQL sql = new US206_SQL();
        sql.demo(212180000);
    }

    @Test
    void US207() throws IOException, SQLException {
        US207_SQL sql = new US207_SQL();
        sql.demo(212180000, 2021);
    }

    @Test
    void US208() throws IOException, SQLException {
        US208_SQL sql = new US208_SQL();
        sql.demo(212180000, 3);
    }

    @Test
    void US209() throws IOException, SQLException {
        US209_SQL sql = new US209_SQL();
        sql.demo(212180000, "2022-03-25 00:00:00");
    }

    @Test
    void US210() throws IOException, SQLException {
        US210_SQL sql = new US210_SQL();
        sql.demo();
    }

    @Test
    void US304() throws IOException, SQLException {
        US304_SQL sql = new US304_SQL();
        sql.demo(5984631, 2);
    }

    @Test
    void US305() throws IOException, SQLException {
        US305_SQL sql = new US305_SQL();
        sql.demo("client1", 3058855);
    }

    @Test
    void US306() throws IOException, SQLException {
        US306_SQL sql = new US306_SQL();
        sql.demo("18476");
    }

    @Test
    void US310() throws IOException, SQLException {
        US310_SQL sql = new US310_SQL();
        sql.demo("27248", 2, 2022);
    }

    @Test
    void US309() throws IOException, SQLException {
        US309_SQL sql = new US309_SQL();
        sql.demo(6, "210950000", "63215", 1);
    }
    @Test
    void US312() throws IOException, SQLException {
        US312_SQL sql = new US312_SQL();
        sql.demo(5323205, "client1");
    }
    @Test
    void US313() throws IOException, SQLException {
        CargoManifest_SQL writter = new CargoManifest_SQL();
        writter.demo(3);
    }

}
