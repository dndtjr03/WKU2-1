package ex_member;

public class member {
    
    private String Hakbun;
    private String Name;
    private String Grade;
    private int Age;
    private double Avg;

    //?��?�� ?��?��?�� ?��름�?? ?��?��?��?�� ?��름과 ?��?��
    member(String Hakbun, String Name, String Grade, int Age, double Avg){
        this.Hakbun = Hakbun;
        this.Name = Name;
        this.Grade = Grade;
        this.Age = Age;
        this.Avg = Avg;
    }
    member()
    {
        this.Hakbun = "";
        this.Name = "";
        this.Grade = "";
        this.Age = 0;
        this.Avg = 0.0;
    }

    public String setHakbun(String Hakbun){
        return this.Hakbun = Hakbun;
    }

    public String getHakbun()
    {
        return this.Hakbun;
    } 
}