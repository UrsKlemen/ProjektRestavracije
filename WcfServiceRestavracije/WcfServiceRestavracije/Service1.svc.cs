using System;
using System.Collections.Generic;
using System.Data.SqlClient;

namespace WcfServiceRestavracije
{
    // NOTE: You can use the "Rename" command on the "Refactor" menu to change the class name "Service1" in code, svc and config file together.
    // NOTE: In order to launch WCF Test Client for testing this service, please select Service1.svc or Service1.svc.cs at the Solution Explorer and start debugging.

    public class Restavracija
    {
        public Restavracija() { }
        public int Id { get; set; }
        public string Ime { get; set; }
        public string Mesto { get; set; }
        public string Ulica { get; set; }
        public int StUlice { get; set; }
        public string Posta { get; set; }
        public int PostnaSt { get; set; }
        public int Ocena { get; set; }
        public bool Laktoza { get; set; }
        public bool Glukoza { get; set; }

    }
    public class Service1 : IService1
    {
        SqlConnection con = new SqlConnection(@"Data Source=(localdb)\MSSQLLocalDB;Initial Catalog=Restavracije;Integrated Security=True;Connect Timeout=30;Encrypt=False;TrustServerCertificate=True;ApplicationIntent=ReadWrite;MultiSubnetFailover=False");
        public List<Restavracija> GetRestavracije()
        {
            SqlCommand cmd = new SqlCommand("Select * FROM Restavracije", con);
            con.Open();
            List<Restavracija> restavracije = new List<Restavracija>();

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                Restavracija rest = new Restavracija();
                rest.Id = Convert.ToInt16(dr["ID"].ToString());
                rest.Ime = dr["Ime"].ToString();
                rest.Mesto = dr["Mesto"].ToString();
                rest.Ulica = dr["Ulica"].ToString();
                rest.StUlice = Convert.ToInt16(dr["StUlice"].ToString());
                rest.Posta = dr["Posta"].ToString();
                rest.PostnaSt = Convert.ToInt16(dr["PostnaSt"].ToString());
                rest.Ocena = Convert.ToInt16(dr["Ocena"].ToString());
                rest.Laktoza = Convert.ToBoolean(dr["Laktoza"].ToString());
                rest.Glukoza = Convert.ToBoolean(dr["Glukoza"].ToString());
                restavracije.Add(rest);
            }
            dr.Close();
            con.Close();
            return restavracije;
        }
        public List<Restavracija> GetRestavracijeMesto(string m)
        {
            SqlCommand cmd = new SqlCommand("Select * FROM Restavracije WHERE Mesto='" + m + "'", con);
            con.Open();
            List<Restavracija> restavracije = new List<Restavracija>();

            SqlDataReader dr = cmd.ExecuteReader();
            while (dr.Read())
            {
                Restavracija rest = new Restavracija();
                rest.Id = Convert.ToInt16(dr["ID"].ToString());
                rest.Ime = dr["Ime"].ToString();
                rest.Mesto = dr["Mesto"].ToString();
                rest.Ulica = dr["Ulica"].ToString();
                rest.StUlice = Convert.ToInt16(dr["StUlice"].ToString());
                rest.Posta = dr["Posta"].ToString();
                rest.PostnaSt = Convert.ToInt16(dr["PostnaSt"].ToString());
                rest.Ocena = Convert.ToInt16(dr["Ocena"].ToString());
                rest.Laktoza = Convert.ToBoolean(dr["Laktoza"].ToString());
                rest.Glukoza = Convert.ToBoolean(dr["Glukoza"].ToString());
                restavracije.Add(rest);
            }
            dr.Close();
            con.Close();
            return restavracije;
        }
    }
}

