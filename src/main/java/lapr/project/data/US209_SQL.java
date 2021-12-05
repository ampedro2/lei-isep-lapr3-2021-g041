package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US209_SQL {

    private Connection databaseConnection;
    private float ocRate;
    private FileOperation fileOperation;

    public US209_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
        fileOperation = new FileOperation();
    }

    public void demo(int mmsi, String date) throws SQLException, IOException {
        try {
            CallableStatement statement = databaseConnection.prepareCall("{CALL US209(?, ?, ?)}");


            statement.setString(1, String.valueOf(mmsi));

            statement.setString(2, date);

            statement.registerOutParameter(3, Types.FLOAT);

            statement.executeUpdate();

            this.ocRate = statement.getFloat(3);

            System.out.println(statement.getFloat(3));

            StringBuilder data = new StringBuilder();
            data.append(ocRate);
            fileOperation.writeToAFile("Output/US209_" + mmsi, data);

            statement.close();
        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            fileOperation.writeToAFile("Output/US209_" + mmsi, data);
        }
    }


}
