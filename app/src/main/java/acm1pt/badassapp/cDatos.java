package acm1pt.badassapp;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


    public class cDatos {

        private String usrBD;
        private String passBD;
        private String urlBD;
        private String driverClassName;
        private Connection conn ;
        private Statement estancia;
        String accion1;

        public cDatos() {
            //poner los datos apropiados
            this.usrBD = "usrBadAss";
            this.passBD = "H4us3*";
            this.urlBD = "jdbc:mysql://badasshouse.prograbatiz.com:3306/prograbatiz_BadAssHouse";
            this.driverClassName = "com.mysql.jdbc.Driver";
        }

        //Conexion a la BD
        public void conectar() throws SQLException {
            try {
                Class.forName(this.driverClassName).newInstance();
                this.conn = DriverManager.getConnection(this.urlBD, this.usrBD, this.passBD);

            } catch (Exception err) {
                System.out.println("Error " + err.getMessage());
            }
        }

        //Cerrar la conexion de BD
        public void cierraConexion() throws SQLException {
            this.conn.close();
        }



        public void setAccion(String correo, String pass1, String clave) {
            this.accion1 = "call valida('" + correo + "',AES_ENCRYPT('" + pass1 + "','" + clave + "'));";
        }



        public void setAccion(String idUsuario, String idCasa) {
            this.accion1 = "call relacionUsrCasa('" + idUsuario + "','" + idCasa + "')";
        }

        public String getAccion() {
            return accion1;
        }

        //Metodos para ejecutar sentencias SQL
        public ResultSet consulta() throws SQLException {
            this.estancia = (Statement) conn.createStatement();
            return this.estancia.executeQuery(accion1);
        }

        public ResultSet consulta1(String txt) throws SQLException {
            this.estancia = (Statement) conn.createStatement();
            return this.estancia.executeQuery(txt);
        }

        public int modificacion() throws SQLException {
            this.estancia = (Statement) conn.createStatement();
            return this.estancia.executeUpdate(accion1);
        }

        public int modificacion1(String txt) throws SQLException {
            this.estancia = (Statement) conn.createStatement();
            return this.estancia.executeUpdate(txt);
        }

    }

