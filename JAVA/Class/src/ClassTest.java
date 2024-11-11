public class ClassTest {
    public static void main(String[] args) throws Exception {
        MulDiv ob1 = new MulDiv();
        String ssum, sminus, smul, sdiv;
        ssum = ob1.plus(50, 30);
        sminus = ob1.minus(50, 30);
        smul = ob1.mul(50, 30);
        sdiv = ob1.div(50, 30);
        System.out.println(ssum);
        System.out.println(sminus);
        System.out.println(smul);
        System.out.println(sdiv);
    }
}

class MulDiv extends PlusMinus{
    int gop;
    double nanum;
    public String mul(int x, int y){
        gop = x * y;
        return "µÎ ¼öÀÇ °ö : " + gop;
    } 

    public String div(int x, int y){
        nanum = (double) x / y;
        return "µÎ ¼öÀÇ ³ª´°¼À : " +nanum;
    }
}