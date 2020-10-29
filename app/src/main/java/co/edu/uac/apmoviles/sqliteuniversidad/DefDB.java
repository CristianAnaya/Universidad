package co.edu.uac.apmoviles.sqliteuniversidad;

public class DefDB {

    public static final String nameDB = "Universidad.db";

    public static final String tabla_est = "Estudiante";
    public static final String col_codigo = "codigo";
    public static final String col_programa = "programa";
    public static final String col_internet = "internet";
    public static final String col_computadora = "computadora";
    public static final String col_telefono = "telefono";
    public static final String create_tabla_est = "CREATE TABLE IF NOT EXISTS " + DefDB.tabla_est + " ( " +
            DefDB.col_codigo + " text primary key," +
            DefDB.col_programa + " text," +
            DefDB.col_internet + " text," +
            DefDB.col_computadora + " text," +
            DefDB.col_telefono + " text" +

            ");";
}

