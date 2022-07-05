import java.sql.*;

public class P2_ReadData_Different {

        //MySql de day4 de..personel tablosu


        public static void main(String[] args) throws SQLException, ClassNotFoundException {
            Class.forName("com.mysql.cj.jdbc.Driver");
            Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch59?serverTimezone=UTC", "root", "26065403");

            Statement st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_READ_ONLY); // resultSet scroll duyarsiz, sadece okuma
            // ilk satirdaki ilk objeyi yazdiralim
            String query = "Select * from personel";
            ResultSet resultSet = st.executeQuery(query);
            System.out.println(resultSet.getRow());  //0
            resultSet.first(); // bizi ilk kayida goturuyor
            Object ilkSatirdakiObje = resultSet.getObject(1);
            System.out.println(ilkSatirdakiObje.toString());


            // ikinci satirdaki ilk objeyi yazdiralim
            resultSet.next();
            Object ikinciSatirdakiObje = resultSet.getObject(1);
            System.out.println(ikinciSatirdakiObje.toString());

            
            // ilk sutundaki 5.degeri yazdiralim
            resultSet.absolute(5);
            Object besinciDeger = resultSet.getObject(1);
            System.out.println(besinciDeger.toString());



            // 0.satira gitmemizin sebebi while sorgusu icerisinde bulunan resultSet.next() in bizi
            // birinci kayda goturmesi icindir. Eger 1.satira gidecek olsak while icindeki kod yuzunden yazdirmaya
            // 2.satirdan baslar, tum listeyi alamayiz

            // Tum listeyi yazdirmak istersek;

            resultSet.absolute(0);
            while (resultSet.next()){
                Object idler = resultSet.getObject(1); // 1.kolondaki butun objeleri getirir
                System.out.println(idler);
            }

            resultSet.absolute(0);
            while (resultSet.next()) {
                Object isimler = resultSet.getObject("isim");
                System.out.println(isimler);
            }


            resultSet.absolute(0);
            while (resultSet.next()){
                Object sehirler = resultSet.getObject("sehir");
                System.out.println(sehirler);
            }












        }

}

