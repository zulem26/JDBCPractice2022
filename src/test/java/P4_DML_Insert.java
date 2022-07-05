import java.sql.*;

public class P4_DML_Insert {
    //MySql de day5_4 da..
    public static void main(String[] args) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        Connection con = DriverManager.getConnection("jdbc:mysql://localhost:3306/batch59?serverTimezone=UTC", "root", "26065403");

        Statement st = con.createStatement();



        //SORU: Calisanlar tablosuna yeni bir kayit ((10005, 'Gokhan Agar' , 15000)  ekleyelim ve eklenen kaydi teyit icin sorgulayalim.
        String insertQuery = "insert into calisanlar values(10005, 'Zulkif Ergin', 15000)";
        System.out.println(st.execute(insertQuery));
        //intelij de gorelim
        ResultSet rs = st.executeQuery("select * from calisanlar");
        while (rs.next()){
            System.out.printf("%-6s %-15.15s %-8s\n", rs.getObject(1), rs.getObject(2), rs.getObject(3));
        }



        //SORU: Calisanlar tablosuna birden fazla yeni kayıt ekleyelim.

        // 1.YONTEM
        // Ayri ayri sorgular ile veritabanina tekrar tekrar ulasmak islemlerin yavas yapilmasina yol acar.
        // 10000 tane veri kaydi yapildigi dusunuldugunde  bu kotu bir yaklasimdir.
        String [] queries = {"insert into calisanlar values(10006, 'Alp Ergin', 15000)",
                "insert into calisanlar values(10007, 'Hamza Ergin', 18000)",
                "insert into calisanlar values(10008, 'Beren Ergin', 19000)"};

        int data = 0;
        for (String each : queries){
            data+= st.executeUpdate(each);
        }
        System.out.println(data + " data eklendi");


        // 2.YONTEM (addBath ve excuteBatch() metotlari ile)
        // addBatch metodu ile SQL ifadeleri gruplandirilabilir ve
        // exucuteBatch()  metodu ile veritabanina bir kere gonderilebilir.
        // ***!!!!**** excuteBatch() metodu bir int [] dizi dondurur.
        // Bu dizi her bir ifade sonucunda etkilenen satir sayisini gosterir.


        String [] queries2 = {"insert into calisanlar values(10009, 'Alp Ergin', 15000)",
                "insert into calisanlar values(10010, 'Hamza Ergin', 18000)",
                "insert into calisanlar values(10011, 'Beren Ergin', 19000)"};

        for (String each: queries2){
            st.addBatch(each);
        }
        int [] satir = st.executeBatch(); // satir bir array, eleman sayisi array in length idir
        System.out.println(satir.length + "satir eklendi");


        // 3. YONTEM
        //-----------------------------------------------------
        // batch metoduyla birlikte PreparedStatement kullanmak en efektif yontemdir.
        // SQL de guvenligi icin basvurulan bir yontemdir. Unutulmamalidir...

    }
}