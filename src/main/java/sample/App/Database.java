package sample.App;

import javafx.concurrent.Task;

import java.sql.*;

public class Database {

    public void getDataFromDatabase() {
        String url = ConfigReader.getDataFromProperty("db_url");

        try {
            Connection conn = DriverManager.getConnection(url);
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery("SELECT \"Player\", \"Score\"\n" +
                    "FROM public.\"Score\"\n" +
                    "ORDER BY \"Score\" DESC;");
            Task task = new Task<Void>() {
                @Override
                protected Void call() throws Exception {
                    while (rs.next()) {
                        DataModel.nameList.add(rs.getString(1));
                        DataModel.scoreList.add(rs.getInt(2));
                    }
                    return null;
                }
            };
            new Thread(task).start();
            task.run();

            rs.close();
            st.close();

        } catch (SQLException e) {
            GUI.logger.error("SQLException while getting data from Database", e);
        }
    }

    void updateDatabase(String playerWin, int scoreWin, String playerLose, int scoreLose){
        String url = ConfigReader.getDataFromProperty("db_url");
        String updateScores = "INSERT INTO public.\"Score\"(\n" + "\t\"Player\", \"Score\")\n" + "\tVALUES (?, ?), \n" + "    (?, ?);";

        try {
            Connection conn = DriverManager.getConnection(url);
            PreparedStatement preparedStatement = conn.prepareStatement(updateScores);
            preparedStatement.setString(1, playerWin);
            preparedStatement.setInt(2, scoreWin);
            preparedStatement.setString(3, playerLose);
            preparedStatement.setInt(4, scoreLose);

            preparedStatement.executeUpdate();
            preparedStatement.close();
        }catch (Exception e){
            e.printStackTrace();
        }
    }

}
