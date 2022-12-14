package lapr.project.data;

import lapr.project.controller.App;
import lapr.project.utils.FileOperation;


import java.io.File;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Types;

public class US204_SQL {

    private final Connection databaseConnection;
    private String container_location;

    public US204_SQL() throws SQLException, IOException {
        databaseConnection = App.getInstance().getDatabaseConnection().getConnection();
    }

    public void demo(int containerId) throws SQLException, IOException {
        try (CallableStatement statement = databaseConnection.prepareCall("{CALL US204(?,?)}")){
            statement.setInt(1, containerId);

            statement.registerOutParameter(2, Types.LONGVARCHAR);

            statement.executeUpdate();

            this.container_location = statement.getString(2);

            StringBuilder data = new StringBuilder();
            data.append(container_location);
            FileOperation.writeToAFile("Output/US204_" + containerId, data);

        }catch (Exception e){
            StringBuilder data = new StringBuilder();
            data.append("No results.");
            FileOperation.writeToAFile("Output/US204_" + containerId, data);
        }
    }


}